package org.meta_environment.eclipse.modules;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nl.cwi.sen1.moduleapi.types.AttributeStore;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;

import aterm.AFun;
import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;
import aterm.pure.PureFactory;

public class ModuleDatabase{
	private final PureFactory factory;
	
	private final Map<String, Module> modules;
	
	private final AttributeUpdateRuleMap attributeUpdateRules;
	
	private final List<AttributeSetListener> listeners;
	
	private final Map<String, Set<String>> descendants;
	private final Map<String, Set<String>> ascendants;
	
	private Map<String, Set<String>> transDescendants;
	
	private final AFun modalAND;
	private final AFun modalOR;
	private final AFun modalNOT;
	private final AFun modalONE;
	private final AFun modalALL;
	private final AFun modalSET;
	
	private final QualifiedName dependenciesName;
	
	public ModuleDatabase(PureFactory factory) {
		super();
		
		this.factory = factory;
		
		modules = new HashMap<String, Module>();
		descendants = new HashMap<String, Set<String>>();
		transDescendants = new HashMap<String, Set<String>>();
		ascendants = new HashMap<String, Set<String>>();
		listeners = new LinkedList<AttributeSetListener>();
		attributeUpdateRules = new AttributeUpdateRuleMap();

		modalAND = factory.makeAFun("and", 2, false);
		modalOR = factory.makeAFun("or", 2, false);
		modalNOT = factory.makeAFun("not", 1, false);
		modalONE = factory.makeAFun("one", 1, false);
		modalALL = factory.makeAFun("all", 1, false);
		modalSET = factory.makeAFun("set", 3, false);
		dependenciesName = new QualifiedName("module-manager", "imports");
		
	}
	
	public void addModuleRecursive(Module module, String moduleId) {
		addModule(module, moduleId);
		getAllChildren(moduleId); // This will load the module's children (if any).
	}
	
	public void addModule(Module module, String moduleId){
		if(!modules.containsKey(moduleId)){ // Only add the module if it doesn't exist already.
			modules.put(moduleId, module);
			descendants.put(moduleId, new HashSet<String>());
			ascendants.put(moduleId, new HashSet<String>());
		}
	}
	
	public void updateModulePath(String oldModuleId, String newModuleId){
		Module module = modules.remove(oldModuleId);
		if(module == null) throw new IllegalArgumentException("Unknown moduleId: "+oldModuleId);
		
		modules.put(newModuleId, new Module(newModuleId, module));
		
		updateModuleDepenencies(oldModuleId, newModuleId);
	}
	
	private void updateModuleDepenencies(String oldModuleId, String newModuleId){
		/* Tell the children their parent changed it's name. */
		Set<String> children = descendants.get(oldModuleId);
		Iterator<String> childrenIterator = children.iterator();
		while(childrenIterator.hasNext()){
			String child = childrenIterator.next();
			Set<String> childsParents = ascendants.get(child);
			childsParents.remove(oldModuleId);
			childsParents.add(newModuleId);
		}
		
		/* Adopt you own children. */
		Set<String> parents = ascendants.remove(oldModuleId);
		ascendants.put(newModuleId, parents);
	}
	
	public void removeModule(String moduleId) {
		deleteAllDependencies(moduleId);
		modules.remove(moduleId);
		descendants.remove(moduleId);
		ascendants.remove(moduleId);
	}
	
	private void triggerAllAttributeUpdateRulesOnAllModules() {
		Iterator<String> iter = modules.keySet().iterator();
		while(iter.hasNext()){
			triggerAllAttributeUpdateRules(iter.next());
		}
	}
	
	private void triggerAllAttributeUpdateRules(String id) {
		Iterator<AttributeUpdateRule> iter = attributeUpdateRules.iterator();
		while(iter.hasNext()){
			propagate(iter.next(), id);
		}
	}
	
	public void setAttribute(String moduleId, ATerm namespace, ATerm key, ATerm value) {
		Module module = modules.get(moduleId);

		if (module == null) {
			System.err.println("MM - addAttribute: module [" + moduleId + "] doesn't exist");
			return;
		}

		updateAttribute(moduleId, namespace, key, value);
	}
	
	private void updateAttribute(String moduleId, ATerm namespace, ATerm key, ATerm value) {
		Module module = modules.get(moduleId);
		ATerm oldValue = module.getAttribute(namespace, key);
		ATerm oldPredicate = module.getPredicate(namespace, key);

		module.deletePredicate(namespace, key);

		if (oldValue == null || !oldValue.isEqual(value)) {
			module.setAttribute(namespace, key, value);
			if (oldPredicate != null) {
				fireAttributeSetListener(moduleId, namespace, key, oldPredicate, value);
			} else {
				fireAttributeSetListener(moduleId, namespace, key, oldValue, value);
			}
			propagateToParents(moduleId);
			triggerAllAttributeUpdateRules(moduleId);
		}
	}
	
	private void updatePredicate(String moduleId, ATerm namespace, ATerm key, ATerm predicate) {
		Module module = modules.get(moduleId);
		ATerm oldValue = module.getPredicate(namespace, key);
		// If there was no predicate get the old value. The listener gives the
		// new value as well as the old value.
		if (oldValue == null) {
			oldValue = module.getAttribute(namespace, key);
		}

		if (oldValue == null || !oldValue.isEqual(predicate)) {
			module.setPredicate(namespace, key, predicate);
			fireAttributeSetListener(moduleId, namespace, key, oldValue, predicate);
			propagateToParents(moduleId);
		}
	}
	
	public void addAttributeSetListener(AttributeSetListener l) {
		listeners.add(l);
	}
	
	public void removeAttributeSetListener(AttributeSetListener l) {
		listeners.remove(l);
	}
	
	private void fireAttributeSetListener(String id, ATerm namespace, ATerm key, ATerm oldValue, ATerm newValue) {
		for (AttributeSetListener l : listeners) {
		  l.attributeSet(id, namespace, key, oldValue, newValue);
		}
	}
	
	private void propagateToParents(String id) {
		Set<String> parents = getParents(id);
		Iterator<String> iter = parents.iterator();
		while(iter.hasNext()){
			triggerAllAttributeUpdateRules(iter.next());
		}
	}
	
	private void propagate(AttributeUpdateRule rule, String id) {
		Module module = modules.get(id);
		ATerm namespace = rule.getNamespace();
		ATerm key = rule.getKey();

		ATerm oldPredicate = module.getPredicate(namespace, key);
		ATerm newPredicate = rule.getPredicateValue();

		ATerm oldValue = module.getAttribute(namespace, key);

		ATerm formula = rule.getFormula();
		boolean result = innermostRuleEvaluation(formula, id);

		if (result) {
			updatePredicate(id, namespace, key, newPredicate);
		} else {
			/* Fall back to attribute value by removing predicate */
			if (oldPredicate != null && newPredicate.equals(oldPredicate)) {
				module.deletePredicate(namespace, key);
				fireAttributeSetListener(id, namespace, key, oldPredicate, oldValue);
				propagateToParents(id);
			}
		}
	}
	
	public ATerm getModuleAttribute(String moduleId, ATerm namespace, ATerm key) {
		Module module = modules.get(moduleId);

		if (module == null) {
			System.err.println("MM - getModuleAttribute: module [" + moduleId + "] doesn't exist");
			return null;
		}

		ATerm value = module.getPredicate(namespace, key);
		if (value == null) {
			value = module.getAttribute(namespace, key);
		}

		return value;
	}
	
	public String getModuleIdByAttribute(ATerm namespace, ATerm key, ATerm value) {
		Iterator<String> iter = modules.keySet().iterator();
		while(iter.hasNext()){
			String moduleId = iter.next();
			Module module = modules.get(moduleId);
			
			if (value.equals(module.getPredicate(namespace, key))) {
				return moduleId;
			} else if (value.equals(module.getAttribute(namespace, key))) {
				return moduleId;
			}
		}

		return null;
	}
	
	public Set<String> getAllModules() {
		Set<String> allModules = new HashSet<String>();
		Iterator<String> iter = modules.keySet().iterator();
		while(iter.hasNext()){
			allModules.add(iter.next());
		}

		return allModules;
	}
	
	public void deleteModuleAttribute(String moduleId, ATerm namespace, ATerm key) {
		Module module = modules.get(moduleId);

		if (module == null) {
			System.err.println("MM - deleteModuleAttribute: module [" + moduleId + "] doesn't exist");
			return;
		}

		module.deleteAttribute(namespace, key);
	}
	
	public AttributeStore getAllAttributes(String moduleId) {
		Module module = modules.get(moduleId);

		if (module == null) {
			throw new IllegalArgumentException("MM - getAllAttributes: module [" + moduleId + "] doesn't exist");
		}

		return module.getAttributes();
	}
	
	public void addDependency(String moduleFromId, String moduleToId) {
		Module moduleFrom = modules.get(moduleFromId);
		Module moduleTo = modules.get(moduleToId);

		if (moduleFrom == null) {
			System.err.println("MM - addDependency: module [" + moduleFromId + "] doesn't exist");
			return;
		}

		if (moduleTo == null) {
			System.err.println("MM - addDependency: module [" + moduleToId + "] doesn't exist");
			return;
		}

		Set<String> dependencies;
		dependencies = descendants.get(moduleFromId);
		dependencies.add(moduleToId);
		
		storePersisentDependencies(moduleFrom, dependencies);
		
		dependencies = ascendants.get(moduleToId);
		dependencies.add(moduleFromId);

		transDescendants = new HashMap<String, Set<String>>();
	}
	
	/**
	 * Stores dependencies persistently.
	 * @param moduleFrom
	 * @param dependencies
	 */
	private void storePersisentDependencies(Module moduleFrom, Set<String> dependencies) {
        ATermList deps = factory.getEmpty();
        
        for (String id : dependencies) {
        	deps = deps.insert(factory.make("<str>", id));
        }
    	
    	try {
    	    IFile file = moduleFrom.getFile();
    	    
    	    if (file != null) {
			  file.setPersistentProperty(dependenciesName, deps.toString());
    	    }
		} catch (CoreException e) {
			org.meta_environment.eclipse.Activator.getInstance().logException("could not set dependencies of " + moduleFrom, e);
		}
		
	}
	
	/**
	 * Retrieves dependencies from persistent storage and initializes
	 * dependencies cache as a side-effect.
	 * 
	 * @param moduleFrom
	 * @return
	 */
	private Set<String> getPersisentDependencies(String moduleFrom) {
		Module idFrom = modules.get(moduleFrom);
        Set<String> result = new HashSet<String>();
    	
    	try {
    		if (idFrom == null) {
    			return result;
    		}
    		
    	    IFile file = idFrom.getFile();
    	    
    	    if (file != null) {
			  String value = file.getPersistentProperty(dependenciesName);
			  
			  if (value != null) {
					ATermList list = (ATermList) factory.parse(value);

					while (!list.isEmpty()) {
						ATermAppl childTerm = (ATermAppl) list.getFirst();
						String child = childTerm.getName();
						result.add(child);
						
						ModuleManager.getInstance().loadSingleModule(child);
						
						list = list.getNext();
					}
					
					descendants.put(moduleFrom, result);
				}
			  return result;
    	    }
		} catch (CoreException e) {
			org.meta_environment.eclipse.Activator.getInstance().logException("could not set dependencies of " + moduleFrom, e);
		}
		
		return result;
	}
	
	/**
	 * Lookup dependencies, if not found, retrieve from persistent storage
	 * @param moduleId
	 * @return
	 */
	public Set<String> getChildren(String moduleId) {
		Set<String> result = descendants.get(moduleId);
		
		if (result == null || result.isEmpty()) {
			result = getPersisentDependencies(moduleId);
		}
		
		return result;
	}
	
	public Set<String> getAllChildren(String moduleId) {
		Set<String> dependencies = transDescendants.get(moduleId);

		if (dependencies == null) {
			dependencies = new HashSet<String>();
			LinkedList<String> temp = new LinkedList<String>();

			temp.addAll(getChildren(moduleId));

			while (!temp.isEmpty()) {
				String tempId = temp.getFirst();
				if (!dependencies.contains(tempId)) {
					dependencies.add(tempId);
					temp.addAll(getChildren(tempId));
				}
				temp.removeFirst();
			}

			transDescendants.put(moduleId, dependencies);
		}

		return dependencies;
	}
	
	public Set<String> getParents(String moduleId) {
		return ascendants.get(moduleId);
	}
	
	public void deleteDependency(String moduleFromId, String moduleToId) {
		LinkedList<String> deps;
		Module moduleFrom = modules.get(moduleFromId);

		if (moduleFrom == null) {
			System.err.println("MM - deleteDependency: module [" + moduleFromId + "] doesn't exist");
			return;
		}

		Module moduleTo = modules.get(moduleToId);

		if (moduleTo == null) {
			System.err.println("MM - deleteDependency: module [" + moduleToId + "] doesn't exist");
			return;
		}

		deps = new LinkedList<String>(descendants.get(moduleFromId));
		deps.remove(moduleToId);

		deps = new LinkedList<String>(ascendants.get(moduleToId));
		deps.remove(moduleFromId);

		transDescendants = new HashMap<String, Set<String>>();
	}
	
	public void deleteDependencies(String moduleId) {
		Set<String> dependencies;

		Module module = modules.get(moduleId);

		if (module == null) {
			System.err.println("MM - deleteDependencies: module [" + moduleId + "] doesn't exist");
			return;
		}

		for (Iterator<String> iter = modules.keySet().iterator(); iter.hasNext();) {
			String tempId = iter.next();
			dependencies = getParents(tempId);
			if (dependencies.contains(moduleId)) {
				dependencies.remove(moduleId);
			}
		}

		dependencies = descendants.get(moduleId);
		dependencies.clear();
	}
	
	private void deleteAllDependencies(String moduleId) {
		deleteDependencies(moduleId);

		for (Iterator<String> iter = modules.keySet().iterator(); iter.hasNext();) {
			Set<String> dependencies = getChildren(iter.next());
			if (dependencies.contains(moduleId)) {
				dependencies.remove(moduleId);
			}
		}
	}
	
	public Map<String, Set<String>> getDependencies() {
		return descendants;
	}
	
	public void printStatistics() {
		int sumDeps = 0;
		Iterator<Set<String>> iter = descendants.values().iterator();
		while(iter.hasNext()){
			sumDeps += iter.next().size();
		}
		System.err.println(modules.size() + " modules");
		System.err.println(sumDeps + " dependencies");
	}
	
	public void registerAttributeUpdateRule(ATerm namespace, ATerm key, ATerm rule, ATerm value) {
		Iterator<AttributeUpdateRule> iter = attributeUpdateRules.iterator();
		while(iter.hasNext()){
			AttributeUpdateRule check = iter.next();
			if (checkRuleViolation(check.getNamespace(), check.getKey(), check.getPredicateValue(), rule)) {
				System.err.println("Rule " + rule + " violates " + check);
				return;
			}
		}
		attributeUpdateRules.put(namespace, key, rule, value);
		triggerAllAttributeUpdateRulesOnAllModules();
	}
	
	private boolean checkRuleViolation(ATerm namespace, ATerm key, ATerm predicate, ATerm rule) {
		if (((ATermAppl) rule).getAFun().equals(modalSET)) {
			return (rule.getChildAt(0).equals(namespace) && rule.getChildAt(1).equals(key) && rule.getChildAt(2).equals(predicate));
		}

		boolean violation = false;
		for (int i = 0; i < rule.getChildCount(); i++) {
			violation = violation || checkRuleViolation(namespace, key, predicate, (ATerm) rule.getChildAt(i));
		}
		return violation;
	}
	
	private boolean innermostRuleEvaluation(ATerm rule, String id) {
		if (((ATermAppl) rule).getAFun().equals(modalAND)) {
			return evaluateAnd((ATerm) rule.getChildAt(0), (ATerm) rule.getChildAt(1), id);
		}
		if (((ATermAppl) rule).getAFun().equals(modalOR)) {
			return evaluateOr((ATerm) rule.getChildAt(0), (ATerm) rule.getChildAt(1), id);
		}
		if (((ATermAppl) rule).getAFun().equals(modalNOT)) {
			return evaluateNot((ATerm) rule.getChildAt(0), id);
		}
		if (((ATermAppl) rule).getAFun().equals(modalONE)) {
			return evaluateOne((ATerm) rule.getChildAt(0), id);
		}
		if (((ATermAppl) rule).getAFun().equals(modalALL)) {
			return evaluateAll((ATerm) rule.getChildAt(0), id);
		}
		if (((ATermAppl) rule).getAFun().equals(modalSET)) {
			return evaluateSet((ATerm) rule.getChildAt(2), id, (ATerm) rule.getChildAt(0), (ATerm) rule.getChildAt(1));
		}

		System.err.println("Error evaluating attribute update rule [" + rule + "]; forgot set?");
		return false;
	}
	
	private boolean evaluateAnd(ATerm op1, ATerm op2, String id) {
		return (innermostRuleEvaluation(op1, id) && innermostRuleEvaluation(op2, id));
	}
	
	private boolean evaluateOr(ATerm op1, ATerm op2, String id) {
		return (innermostRuleEvaluation(op1, id) || innermostRuleEvaluation(op2, id));
	}
	
	private boolean evaluateNot(ATerm op, String id) {
		return !(innermostRuleEvaluation(op, id));
	}
	
	private boolean evaluateOne(ATerm op, String id) {
		boolean result = false;
		Set<String> children = getAllChildren(id);
		Iterator<String> iter = children.iterator();

		while (iter.hasNext() && result == false) {
			String childId = iter.next();
			result = innermostRuleEvaluation(op, childId);
		}

		return result;
	}
	
	private boolean evaluateAll(ATerm op, String id) {
		boolean result = true;
		Set<String> children = getAllChildren(id);
		Iterator<String> iter = children.iterator();

		while (iter.hasNext() && result == true) {
			String childId = iter.next();
			result = innermostRuleEvaluation(op, childId);
		}

		return result;
	}
	
	private boolean evaluateSet(ATerm op, String id, ATerm namespace, ATerm key) {
		Module module = modules.get(id);
		
		if (module != null) {
		  ATerm value = module.getAttribute(namespace, key);

		  return op.equals(value);
		}
		return false;
	}
}

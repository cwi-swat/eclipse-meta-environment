package org.meta_environment.eclipse.modules;

import java.util.Iterator;
import java.util.Set;

import nl.cwi.sen1.moduleapi.Factory;
import toolbus.adapter.eclipse.EclipseTool;
import aterm.ATerm;
import aterm.ATermList;

public class ModuleManager extends EclipseTool implements AttributeSetListener {
	private static final String TOOL_NAME = "module-manager";

	private final Factory factory = Factory.getInstance(EclipseTool.factory);
	
	private final ModuleDatabase moduleDB;

	private static class InstanceKeeper {
		private static ModuleManager sInstance = new ModuleManager();
		static {
			sInstance.connect();
		}
	}

	public static ModuleManager getInstance() {
		return InstanceKeeper.sInstance;
	}
	
	public ModuleManager() {
		super(TOOL_NAME);
		
		moduleDB = new ModuleDatabase(EclipseTool.factory);
		moduleDB.addAttributeSetListener(this);
	}
	
	void loadSingleModule(String path){
		moduleDB.addModule(new Module(path, factory), path);
	}

	public ATerm createModule(String path) {
		moduleDB.addModuleRecursive(new Module(path, factory), path);
		return EclipseTool.factory.make("module-id(<str>)", path);
	}
	
	public ATerm updateModuleId(String oldModuleId, String newModuleId){
		try{
			moduleDB.updateModulePath(oldModuleId, newModuleId);
		}catch(IllegalArgumentException iaex){
			return EclipseTool.factory.parse("no-such-module");
		}
		return EclipseTool.factory.make("module-id(<str>)", newModuleId);
	}

	public ATerm getModuleIdByAttribute(ATerm namespace, ATerm key, ATerm value) {
		String moduleId = moduleDB.getModuleIdByAttribute(namespace, key, value);

		if (moduleId == null) {
			return EclipseTool.factory.parse("no-such-module");
		}

		return EclipseTool.factory.make("module-id(<str>)", moduleId);
	}

	public ATerm getAllModules() {
		Set<String> modules = moduleDB.getAllModules();

		return EclipseTool.factory.make("modules(<list>)", extractATermList(modules));
	}

	public void deleteModule(String id) {
		try {
			moduleDB.removeModule(id);
		} catch (IllegalArgumentException e) {
			System.err.println("warning:" + e);
		}
	}

	public void addAttribute(String id, ATerm namespace, ATerm key, ATerm value) {
		try {
			moduleDB.setAttribute(id, namespace, key, value);
		} catch (IllegalArgumentException e) {
			System.err.println("warning:" + e);
		}
	}

	public ATerm getAttribute(String id, ATerm namespace, ATerm key) {
		try {
			ATerm value = moduleDB.getModuleAttribute(id, namespace, key);
			if (value == null) {
				return EclipseTool.factory.parse("no-such-key");
			}

			return EclipseTool.factory.make("attribute(<term>)", value);
		} catch (IllegalArgumentException e) {
			System.err.println("warning:" + e);
			return EclipseTool.factory.parse("no-such-key");
		}
	}

	public void deleteAttribute(String id, ATerm namespace, ATerm key) {
		try {
			moduleDB.deleteModuleAttribute(id, namespace, key);
		} catch (IllegalArgumentException e) {
			System.err.println("warning:" + e);
		}
	}

	public void addDependency(String from, String to) {
		try {
			moduleDB.addDependency(from, to);
		} catch (IllegalArgumentException e) {
			System.err.println("warning:" + e);
		}
	}

	public ATerm getChildrenModules(String id) {
		try {
			Set<String> dependencies = moduleDB.getChildren(id);

			if (dependencies == null) {
				return EclipseTool.factory.parse("no-such-module");
			}

			return EclipseTool.factory.make("children-modules(<list>)", extractATermList(dependencies));
		} catch (IllegalArgumentException e) {
			System.err.println("warning:" + e);
			return EclipseTool.factory.make("children-modules([])");
		}
	}

	public ATerm getAllChildrenModules(String id) {
		try {
			Set<String> dependencies = moduleDB.getAllChildren(id);

			if (dependencies == null) {
				return EclipseTool.factory.parse("no-such-module");
			}

			return EclipseTool.factory.make("all-children-modules(<list>)", extractATermList(dependencies));
		} catch (IllegalArgumentException e) {
			System.err.println("warning:" + e);
			return EclipseTool.factory.parse("no-such-module");
		}
	}

	private ATermList extractATermList(Set<String> dependencies) {
		ATermList list = EclipseTool.factory.makeList();
		for (Iterator<String> iter = dependencies.iterator(); iter.hasNext();) {
			list = list.append(EclipseTool.factory.make("<str>", iter.next()));
		}
		return list;
	}

	public void deleteDependency(String from, String to) {
		moduleDB.deleteDependency(from, to);
	}

	public void deleteDependencies(String id) {
		try {
			moduleDB.deleteDependencies(id);
		} catch (IllegalArgumentException e) {
			System.err.println("warning:" + e);
		}
	}

	public void recTerminate(ATerm t0) {
		// Do nothing.
	}

	public void attributeSet(String id, ATerm namespace, ATerm key, ATerm oldValue, ATerm newValue) {
		if (oldValue == null) {
			/*
			 * The old value is unknown, so we construct a pattern that may mean
			 * any term
			 */
			oldValue = key.getFactory().parse("undefined");
		}
		
		if (newValue == null) {
			/*
			 * The new value is unknown, so we construct a pattern that may mean
			 * any term
			 */
			newValue = key.getFactory().parse("undefined");
		}
		
		sendEvent(EclipseTool.factory.make("attribute-changed(<str>,<term>,<term>,<term>,<term>)", id, namespace, key, oldValue, newValue));
	}

	public void registerAttributeUpdateRule(ATerm namespace, ATerm key, ATerm rule, ATerm value) {
		//ATerm namespace = Tool.factory.makeAppl(Tool.factory.makeAFun(namespaceStr, 0, true));
		//ATerm key = Tool.factory.makeAppl(Tool.factory.makeAFun(keyStr, 0, true));
		moduleDB.registerAttributeUpdateRule(namespace, key, rule, value);
	}

	public void recAckEvent(ATerm t0) {
		// intentionally empty
	}
}

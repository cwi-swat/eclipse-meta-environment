package org.meta_environment.eclipse.modules;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nl.cwi.sen1.moduleapi.Factory;
import nl.cwi.sen1.moduleapi.types.Attribute;
import nl.cwi.sen1.moduleapi.types.AttributeStore;
import nl.cwi.sen1.moduleapi.types.TableEntryTable;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.QualifiedName;
import org.meta_environment.eclipse.Activator;

import aterm.ATerm;

public class Module{
	private final Factory factory;
    
    private final Map<ATerm, AttributeTable> attributes;

    private final Map<ATerm, AttributeTable> predicates;
    
    private String path;
    private IFile file;

    public Module(String path, Factory factory) {
        this.path = path;
    	file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(path));
        
    	this.factory = factory;
        
    	attributes = new HashMap<ATerm, AttributeTable>();
        predicates = new HashMap<ATerm, AttributeTable>();
    }
    
    public Module(String path, Module module){
    	this.path = path;
    	file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(path));
    	
    	this.factory = module.factory;
        
    	attributes = module.attributes;
        predicates = module.predicates;
    }
    
    public int hashCode(){
    	return path.hashCode();
    }
    
    public boolean equals(Object obj){
    	return ((obj.getClass() == Module.class) && (((Module) obj).path.equals(path)));
    }

    public void setAttribute(ATerm namespace, ATerm key, ATerm value) {
    	AttributeTable table = getTable(namespace);

        if (table != null) {
            table.setEntry(key, value);
        } else {
            table = new AttributeTable(factory);
            table.setEntry(key, value);
            attributes.put(namespace, table);
        }
        
        if (file != null) {
        	storePersistentAttribute(namespace, key, value);
        }
    }

    private void storePersistentAttribute(ATerm namespace, ATerm key, ATerm value) {
    	QualifiedName name = new QualifiedName(namespace.toString(), key.toString());
    	
    	try {
			file.setPersistentProperty(name, value.toString());
		} catch (CoreException e) {
			Activator.getInstance().logException("could not set property " + key + " to " + value, e);
		}
	}

    private ATerm getPersistentAttribute(ATerm namespace, ATerm key) {
        QualifiedName name = new QualifiedName(namespace.toString(), key.toString());
    	
    	try {
    		String value = file.getPersistentProperty(name);
    		
    		if (value != null) {
    		   AttributeTable table = getTable(namespace);
    		   ATerm aval = key.getFactory().parse(value);
    		   table.setEntry(key, aval);
    		   return aval;
    		}
    		return null;
		} catch (CoreException e) {
			Activator.getInstance().logException("could not get property " + key, e);
		}
		
		return null;
	}
    
	public ATerm getAttribute(ATerm namespace, ATerm key) {
        AttributeTable table = getTable(namespace);

        if (table != null) {
            ATerm value = table.getValue(key);
            
            if (file != null) {
				if (value == null) {
			    	return getPersistentAttribute(namespace, key);
				}
				return value;
			}
        }
        return null;
    }

    public AttributeStore getAttributes() {
        AttributeStore store = factory.makeAttributeStore();
        
        Iterator<ATerm> iter = attributes.keySet().iterator();
        while (iter.hasNext()) {
            ATerm namespace = iter.next();
            TableEntryTable table = getTable(namespace).getTableEntryTable();

            Attribute attribute = factory.makeAttribute_Attribute(namespace, table);
            store = store.append(attribute);
        }

        return store;
    }

    public void deleteAttribute(ATerm namespace, ATerm key) {
        AttributeTable table = getTable(namespace);

        if (table != null) {
            table.deleteEntry(key);
        }
    }

    private AttributeTable getTable(ATerm namespace) {
        AttributeTable table = attributes.get(namespace);
        if(table == null){
        	table = new AttributeTable(factory);
            attributes.put(namespace, table);
        }
        return table;
    }

    private AttributeTable getPredicateTable(ATerm namespace) {
    	return predicates.get(namespace);
    }

    public void setPredicate(ATerm namespace, ATerm key, ATerm value) {
        AttributeTable table = getPredicateTable(namespace);

        if (table != null) {
            table.setEntry(key, value);
        } else {
            table = new AttributeTable(factory);
            table.setEntry(key, value);
            predicates.put(namespace, table);
        }
    }

    public ATerm getPredicate(ATerm namespace, ATerm key) {
        AttributeTable table = getPredicateTable(namespace);

        if (table != null) {
            return table.getValue(key);
        }
        return null;
    }

    public void deletePredicate(ATerm namespace, ATerm key) {
        AttributeTable table = getPredicateTable(namespace);

        if (table != null) {
            table.deleteEntry(key);
        }
    }
    
    /**
     * Get an IFile reference to this module
     * @return reference to IFile, if path attribute was set before, otherwise null
     */
    public IFile getFile() {
    	return file;
    }
}

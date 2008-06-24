package org.meta_environment.eclipse.viz.graph;

import java.util.HashMap;

import org.eclipse.imp.pdb.facts.IRelation;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IString;
import org.eclipse.imp.pdb.facts.ITuple;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.type.TupleType;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.facts.type.TypeFactory;
import org.meta_environment.eclipse.viz.prefusedot.DotAdapter;

import prefuse.data.Graph;
import prefuse.data.Node;

public class GraphBuilder {
    private static final TypeFactory types = org.eclipse.imp.pdb.facts.type.TypeFactory.getInstance();
	private HashMap<String, Node> fNodeCache = new HashMap<String, Node>();
	private static final Type locatedNodeType = types.tupleTypeOf(types.stringType(), types.sourceLocationType());

    public Graph computeGraph(IValue fact) {
        if (fact.getBaseType().isRelationType()) {
            if (((IRelation) fact).arity() == 2) {
                return convertRelToGraph((IRelation) fact);
            }
        }

        return new Graph();
    }

    private Graph convertRelToGraph(IRelation rel) {
        fNodeCache.clear();
        DotAdapter graph = new DotAdapter();

        for (ITuple tuple : rel) {
            Node from = getOrCreateNode(graph, tuple.get(0));
            Node to = getOrCreateNode(graph, tuple.get(1));

            if (from != null && to != null) {
                graph.addEdge(from, to);
            }
        }

        graph.doDotLayout();
        return graph;
    }

    private Node getOrCreateNode(Graph graph, IValue value) {
    	String nodeName = getNodeName(value);
        Node node;

        if (nodeName.length() == 0) {
            return null;
        }

        if (fNodeCache.containsKey(nodeName)) {
            node = fNodeCache.get(nodeName);
        } else {
            node = graph.addNode();
            node.setString(DotAdapter.DOT_ID, nodeName);
            node.setString(DotAdapter.DOT_LABEL, nodeName);
            
            String link = getNodeLink(value);
            if (link != null) {
              node.setString(DotAdapter.DOT_LINK, link);
            }
            fNodeCache.put(nodeName, node);
        }

        return node;
    }

	private String getNodeName(IValue value) {
		Type type = value.getBaseType();
		
		if (type == locatedNodeType) {
		    return ((IString) ((ITuple) value).get(0)).getValue();	
		}
		
		return value.toString();
	}
	
	private String getNodeLink(IValue value) {
		Type type = value.getBaseType();
		
		if (type == locatedNodeType) {
			return ((ISourceLocation) ((ITuple) value).get(1)).getPath();
		}
		
		return null;
	}
}

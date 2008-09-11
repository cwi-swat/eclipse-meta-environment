package org.meta_environment.eclipse.viz.graph;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.imp.pdb.facts.IRelation;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IString;
import org.eclipse.imp.pdb.facts.ITuple;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.type.RelationType;
import org.eclipse.imp.pdb.facts.type.StringType;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.facts.type.TypeFactory;
import org.meta_environment.eclipse.viz.prefusedot.DotAdapter;

import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Node;

public class GraphBuilder {
    private static final TypeFactory types = org.eclipse.imp.pdb.facts.type.TypeFactory.getInstance();
	private HashMap<String, Node> fNodeCache = new HashMap<String, Node>();
	private static final Type locatedNodeType = types.tupleTypeOf(types.stringType(), types.sourceLocationType());
	private static Type attributedGraphType;
	private static Map<String, String> attrKeyMap;
	
	public GraphBuilder() {
    	//create tuple type: <rel[str,rel[str,str]],rel[str,str]>
    	StringType st = types.stringType();
    	RelationType rss = types.relTypeOf(st, st);
    	RelationType rsr = types.relTypeOf(st, rss);
    	attributedGraphType = types.tupleTypeOf(rsr, rss);
    
    	attrKeyMap = new HashMap<String, String>();
    	attrKeyMap.put("label", DotAdapter.DOT_LABEL);
    	attrKeyMap.put("shape", DotAdapter.DOT_SHAPE);
    	attrKeyMap.put("level", DotAdapter.DOT_LEVEL);
    	attrKeyMap.put("origin", DotAdapter.DOT_LINK);
    	attrKeyMap.put("color", DotAdapter.DOT_COLOR);
    	attrKeyMap.put("fillcolor", DotAdapter.DOT_FILLCOLOR);
    	attrKeyMap.put("style", DotAdapter.DOT_STYLE);
	}

	public Graph computeGraph(IValue fact) {
    	if (fact.getBaseType().equals(attributedGraphType)) {
    		return createAttributedGraph((ITuple) fact);
    	} else if (fact.getBaseType().isRelationType()) {
            if (((IRelation) fact).arity() == 2) {
                return convertBinaryRelToGraph((IRelation) fact);
            }
        }

        return new Graph();
    }

    private Graph convertBinaryRelToGraph(IRelation rel) {
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

    private Graph createAttributedGraph(ITuple rel) {
    	fNodeCache.clear();
    	DotAdapter graph = new DotAdapter();
    	
    	IRelation nodes = (IRelation) rel.get(0);
    	IRelation edges = (IRelation) rel.get(1);
    	
    	for (ITuple tuple : nodes) {
    		Node node = getOrCreateNode(graph, tuple.get(0));
    		setAttributes(node, (IRelation) tuple.get(1));
    	}
    	
    	for (ITuple tuple : edges) {
    		createEdge(graph, tuple.get(0), tuple.get(1));
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
    
    private void setAttributes(Node node, IRelation attrs) {
    	for (ITuple attr : attrs) {
    		String attrKey = ((IString)attr.get(0)).getValue();
    		String dotKey = attrKeyMap.get(attrKey);
    		if (dotKey != null) {
    			String attrValue = ((IString)attr.get(1)).getValue();
    			node.setString(dotKey, attrValue);
    		} else {
    			System.err.println("Unknown node attribute: " + attrKey + ", on node: " + node.getString(DotAdapter.DOT_ID));
    		}    		
    	}        	
    }

    private Edge createEdge(Graph graph, IValue from, IValue to) {
    	Node fromNode = getOrCreateNode(graph, from);
        Node toNode = getOrCreateNode(graph, to);
        if (fromNode != null && toNode != null) {
        	return graph.addEdge(fromNode, toNode);
        }
        return null;
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

package org.eclipse.imp.pdb.viz.graph;

import java.util.HashMap;

import org.eclipse.imp.pdb.facts.IRelation;
import org.eclipse.imp.pdb.facts.ITuple;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.viz.prefusedot.DotAdapter;

import prefuse.data.Graph;
import prefuse.data.Node;

public class GraphBuilder {
    private HashMap<String, Node> fNodeCache = new HashMap<String, Node>();

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
            String nameId = tuple.get(0).toString();
            String nameLabel = tuple.get(1).toString();

            Node nodeId = getOrCreateNode(graph, nameId);
            Node nodeLabel = getOrCreateNode(graph, nameLabel);

            if (nodeLabel != null) {
                graph.addEdge(nodeId, nodeLabel);
            }
        }

        graph.doDotLayout();
        return graph;
    }

    private Node getOrCreateNode(Graph graph, String nodeName) {
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
            fNodeCache.put(nodeName, node);
        }

        return node;
    }
}

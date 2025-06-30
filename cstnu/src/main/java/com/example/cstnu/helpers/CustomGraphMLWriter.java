package com.example.cstnu.helpers;

import com.google.common.base.Function;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.io.GraphMLWriter;
import com.example.cstnu.model.CSTNUVertex;
import com.example.cstnu.model.CSTNUEdge;
import com.example.cstnu.model.ContingencyManager;
import com.example.cstnu.model.ObservedPropositionManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class CustomGraphMLWriter {

    public static final String NETWORK_TYPE_KEY = "NetworkType";
    public static final String EDGE_LABELED_LC_VALUE_KEY = "LowerCaseLabeledValues";
    public static final String EDGE_LABELED_UC_VALUE_KEY = "UpperCaseLabeledValues";
    public static final String EDGE_CASE_VALUE_KEY = "LabeledValue";
    public static final String EDGE_LABELED_VALUE_KEY = "LabeledValues";
    public static final String EDGE_VALUE_KEY = "Value";
    public static final String EDGE_TYPE_KEY = "Type";
    public static final String GRAPH_NAME_KEY = "Name";
    public static final String GRAPH_nCTG_KEY = "nContingent";
    public static final String GRAPH_nEDGES_KEY = "nEdges";
    public static final String GRAPH_nOBS_KEY = "nObservedProposition";
    public static final String GRAPH_nVERTICES_KEY = "nVertices";
    public static final String NODE_LABEL_KEY = "Label";
    public static final String NODE_OBSERVED_KEY = "Obs";
    public static final String NODE_POTENTIAL_KEY = "Potential";
    public static final String NODE_X_KEY = "x";
    public static final String NODE_Y_KEY = "y";


    public void save(DirectedSparseMultigraph directedGraph, String filename, String typeTCN, ContingencyManager contingencyManager, ObservedPropositionManager observedPropositionsManager) {


        GraphMLWriter<CSTNUVertex, CSTNUEdge> graphWriter = new GraphMLWriter<CSTNUVertex, CSTNUEdge>();

        graphWriter.addGraphData("NetworkType", "Network Type", "CSTNU", g -> typeTCN);


        graphWriter.addGraphData("nEdges", "Number of edges in the graph", "0", g -> String.valueOf(g.getEdgeCount()));

        // vertex means an angular point, in this case Jung uses it instead of Node
        graphWriter.addGraphData("nVertices", "Number of vertices in the graph", "0", g -> String.valueOf(g.getVertexCount()));

        graphWriter.addGraphData("Name", "Graph Name", "", g -> "diagram");

        graphWriter.addGraphData("nContingent", "Number of contingents in the graph", "0", g -> String.valueOf(contingencyManager.countContingencies()));

        graphWriter.addGraphData("nObservedProposition", "Number of observed propositions in the graph", "0", g -> String.valueOf(observedPropositionsManager.countObservedPropositions()));

        graphWriter.setVertexIDs(new Function<>() {
            @Override
            public String apply(final CSTNUVertex v) {
                return v.getId();
            }
        });

        graphWriter.setEdgeIDs(new Function<CSTNUEdge, String>() {
            @Override
            public String apply(final CSTNUEdge e) {
                return e.getId();
            }
        });

        graphWriter.addVertexData("x", "The x coordinate for the visualization. A positive value.", "0", new Function<CSTNUVertex, String>() {
            @Override
            public String apply(final CSTNUVertex v) {
                return Double.toString(( v.getX()));
            }
        });

        graphWriter.addVertexData("y", "The y coordinate for the visualization. A positive value.", "0", new Function<CSTNUVertex, String>() {
            @Override
            public String apply(final CSTNUVertex v) {
                return Double.toString(( v.getY()));
            }
        });

        graphWriter.addVertexData("Obs", "Proposition Observed. Value specification: [a-zA-F]", "", new Function<CSTNUVertex, String>() {
            @Override
            public String apply(final CSTNUVertex v) {
                if(v.getObs() != null){
                    return v.getObs();
                }
                return "";
            }
        });

        // TODO fix the return function
        graphWriter.addVertexData("Potential", "Labeled Potential Values. Format: {[('node name (no case modification)', 'integer', 'label') ]+}|{}", "", g -> null);

        graphWriter.addVertexData("Label", "Label. Format: [�[a-zA-F]|[a-zA-F]]+|?", "?", new Function<CSTNUVertex, String>() {
            @Override
            public String apply(final CSTNUVertex v) {
                if(v.getLabel() != null){
                    return v.getLabel();
                }
                return "?";
            }
        });

        graphWriter.addEdgeData("Type", "Type: Possible values: normal|contingent|constraint|derived|internal.", "normal", new Function<CSTNUEdge, String>() {
            @Override
            public String apply(final CSTNUEdge e) {
                return e.getType().toString();
            }
        });
        if (typeTCN == "CSTNU") {
            graphWriter.addEdgeData("LabeledValues", "Labeled Values. Format: {[('integer', 'label') ]+}|{}", "", new Function<CSTNUEdge, String>() {
                @Override
                public String apply(final CSTNUEdge e) {
                    return e.getLabeledValues();
                }
            });
        }
        graphWriter.addEdgeData("Value", "Value for STN edge. Format: 'integer'", "", new Function<CSTNUEdge, String>() {
            @Override
            public String apply(final CSTNUEdge e) {

                final int v = e.getValue();
                if (v == Integer.MIN_VALUE) {
                    return null;
                }
                return String.valueOf(v);
            }
        });


        graphWriter.addEdgeData("UpperCaseLabeledValues", "Labeled Upper-Case Values. Format: {[('node name (no case modification)', 'integer', 'label') ]+}|{}", "", new Function<CSTNUEdge, String>() {
                @Override
                public String apply(final CSTNUEdge e) {
                    return null;
                }
            });

        graphWriter.addEdgeData("LowerCaseLabeledValues", "Labeled Lower-Case Values. Format: {[('node name (no case modification)', 'integer', 'label') ]+}|{}", "", new Function<CSTNUEdge, String>() {
                @Override
                public String apply(final CSTNUEdge e) {
                    return null;
                }
            });

       



        PrintWriter out = null;
        try {
            out = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            graphWriter.save(directedGraph, out);
            System.out.println(":DONE");
            System.out.println(directedGraph);
        } catch (IOException e) {
            e.printStackTrace();
        }






    }
/*
    private static int calculateNumContingent(Hypergraph g){
        int nContigent = 0;

        CSTNUEdge cstnuEdge;
        for(Object edge: g.getEdges()){
            cstnuEdge = (CSTNUEdge)edge;
            nContigent++;
        }
        CSTNUVertex cstnuVertex;
        for(Object vertex: g.getVertices()){
            cstnuVertex = (CSTNUVertex)vertex;
            nContigent++;
        }
        return nContigent;
    }

 */
}







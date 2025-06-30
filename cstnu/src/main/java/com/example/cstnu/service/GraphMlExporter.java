package com.example.cstnu.service;


import com.example.cstnu.model.CSTNUEdge;
import com.example.cstnu.model.CSTNUVertex;
import org.jgrapht.nio.*;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.graphml.GraphMLExporter;

import java.util.HashMap;
import java.util.Map;

public class GraphMlExporter{

    public static GraphExporter<CSTNUVertex, CSTNUEdge> createExporter() {

        /*
         * Create the exporter. The constructor parameter is a function which generates for each
         * vertex a unique identifier.
         */
        GraphMLExporter<CSTNUVertex, CSTNUEdge> exporter =
                new GraphMLExporter<>(v -> v.getId());

        /*
         * Set to export the internal edge weights
         */
        exporter.setExportEdgeWeights(true);

        /*
         * The exporter may need to generate for each vertex a set of attributes.
         */

        exporter.setVertexAttributeProvider(v -> {
            Map<String, Attribute> m = new HashMap<>();
            if (v.getObs() != null) {
                m.put("Obs", DefaultAttribute.createAttribute(v.getObs().toString()));
            }
            if (v.getLabel() != null) {
                m.put("Label", DefaultAttribute.createAttribute(v.getLabel()));
            }
            m.put("x",DefaultAttribute.createAttribute(v.getX()));
            m.put("y", DefaultAttribute.createAttribute(v.getY()));
            //m.put("id", DefaultAttribute.createAttribute("node-" + v.getId()));
            return m;
        });

        /*
         * Set the edge id provider.
         *
         * The exporter needs to generate for each edge a unique identifier.
         */
        //exporter.setEdgeIdProvider(new IntegerIdProvider<DefaultWeightedEdge>(0));

        /*
         * The exporter may need to generate for each edge a set of attributes.
         */
        exporter.setEdgeAttributeProvider(e -> {
            Map<String, Attribute> m = new HashMap<>();
            //m.put("id", DefaultAttribute.createAttribute("edge-" + e.getId()));
            m.put("Value", DefaultAttribute.createAttribute(e.getValue()));
            m.put("Type", DefaultAttribute.createAttribute(e.getType().toString()));
           // m.put("LabeledValues", DefaultAttribute.createAttribute(e.getLabeledValues()));

            return m;
        });

        /*
         * Register additional attributes for vertices
         */
        exporter.registerAttribute("Obs", GraphMLExporter.AttributeCategory.NODE, AttributeType.STRING);
        exporter.registerAttribute("Label", GraphMLExporter.AttributeCategory.NODE, AttributeType.STRING);
        exporter.registerAttribute("x", GraphMLExporter.AttributeCategory.NODE, AttributeType.INT);
        exporter.registerAttribute("y", GraphMLExporter.AttributeCategory.NODE, AttributeType.INT);


        /*
         * Register additional name attribute for vertices and edges
         */
        //exporter.registerAttribute("name", GraphMLExporter.AttributeCategory.ALL, AttributeType.STRING);
        exporter.registerAttribute("Value", GraphMLExporter.AttributeCategory.EDGE, AttributeType.INT);
        exporter.registerAttribute("Type", GraphMLExporter.AttributeCategory.EDGE, AttributeType.STRING);
        exporter.registerAttribute("LabeledValues", GraphMLExporter.AttributeCategory.EDGE, AttributeType.STRING);

        return exporter;
    }

}





package com.example.cstnu.service;

import com.example.cstnu.model.*;
import com.example.cstnu.model.Type;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import com.example.cstnu.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CSTNUTranslationService {

   private ArrayList<String> invalidTaskManager = new ArrayList<String>();
   private ContingencyManager contingencyManager;
   private ObservedPropositionManager observedPropositionManager;

   public CSTNUTranslationService(ContingencyManager contingencyManager,
                                  ObservedPropositionManager observedPropositionManager){
      this.contingencyManager = contingencyManager;
      this.observedPropositionManager = observedPropositionManager;
   }

   public void addStart(DirectedSparseMultigraph directedGraph, CSTNUNodeDTO vertexDTO){
       // add z the beginning of the world
      CSTNUVertex zNode = new CSTNUVertex("Z", vertexDTO.getX()+1, vertexDTO.getY()+1);
      // add the start node
      CSTNUVertex startNode = new CSTNUVertex(vertexDTO.getId(), vertexDTO.getX(), vertexDTO.getY());
      directedGraph.addVertex(startNode);
      directedGraph.addVertex(zNode);

      // add 1 edge connecting z to start node
       String edgeId = zNode.getId().concat("_Start");

       CSTNUEdge zStartEdge = new CSTNUEdge(edgeId, startNode.getId(), zNode.getId(), 0, Type.CONTINGENT, "{}");

       directedGraph.addEdge(zStartEdge, startNode, zNode, EdgeType.DIRECTED);


   }

   public void addEnd(DirectedSparseMultigraph directedGraph, CSTNUNodeDTO CSTNUNodeDTO){

      CSTNUVertex endNode = new CSTNUVertex("Ω", CSTNUNodeDTO.getX(), CSTNUNodeDTO.getY());
      directedGraph.addVertex(endNode);

   }

   public void addActivity(DirectedSparseMultigraph directedGraph, CSTNUNodeDTO vertexDTO){

      // create Id for start and end node of tcn
      String startId = vertexDTO.getId().concat("_S");
      String endId = vertexDTO.getId().concat("_E");
      // create id for the edges connecting start and end
      String startEndId = vertexDTO.getId().concat("_S_E");
      String endStartId = vertexDTO.getId().concat("_E_S");

      // create the vertex(node) and edge
      CSTNUVertex startNode = new CSTNUVertex(startId, vertexDTO.getX(), vertexDTO.getY());
      CSTNUVertex endNode = new CSTNUVertex(endId, vertexDTO.getX(), vertexDTO.getY());

      CSTNUEdge startEndEdge = new CSTNUEdge(startEndId, startId, endId, 0, Type.CONTINGENT, "{}");
      CSTNUEdge endStartEdge = new CSTNUEdge(endStartId, endId, startId, 0, Type.CONTINGENT, "{}");


      // add the vertex and the edge to the graph
      directedGraph.addEdge(startEndEdge, startNode, endNode, EdgeType.DIRECTED);
      directedGraph.addEdge(endStartEdge, endNode, startNode, EdgeType.DIRECTED);

      directedGraph.addVertex(startNode);
      directedGraph.addVertex(endNode);

      //invalidTaskManager.add(vertexDTO.getId());

   }

   public void addSequenceFlow(DirectedSparseMultigraph directedGraph,  CSTNUEdgeDTO cstnuEdgeDTO){

      String targetId;
      String sourceId;
     if(cstnuEdgeDTO.getSource().contains("Event")){

        targetId = "Z";

        sourceId = cstnuEdgeDTO.getTarget().concat("_S");

     }
     else if(cstnuEdgeDTO.getTarget().contains("Event")){
        targetId = cstnuEdgeDTO.getSource().concat("_E");
        sourceId = "Ω";

     }
     else{
        targetId = cstnuEdgeDTO.getSource().concat("_E");
        sourceId = cstnuEdgeDTO.getTarget().concat("_S");

     }

      String sequenceFlowId = sourceId + "-" + targetId;

      CSTNUEdge sequenceFlowEdge = new CSTNUEdge(sequenceFlowId, sourceId, targetId, 0, Type.NORMAL, "{}");

      Collection<CSTNUVertex> vertices = directedGraph.getVertices();
      CSTNUVertex target=null;
      CSTNUVertex source=null;
      for (CSTNUVertex vertex: vertices){
         System.out.println(vertex.getId());
         if(vertex.getId().equals(targetId)){
            target = vertex;
         }
         else if(vertex.getId().equals(sourceId)){
            source = vertex;
         }
      }
      if(target != null && source != null){
         directedGraph.addEdge(sequenceFlowEdge, source, target, EdgeType.DIRECTED);
      }
      else{
         System.out.println("Cannot add. Missing target or source!" + sourceId + " " + targetId);
         //error
      }


   }





   public void addXOR(DirectedSparseMultigraph directedGraph, CSTNUNodeDTO vertexDTO){

       String startId = vertexDTO.getId().concat("_S");
       String endId = vertexDTO.getId().concat("_E");
       String startEndId = vertexDTO.getId().concat("_S_E");
       String endStartId = vertexDTO.getId().concat("_E_S");


       CSTNUVertex startNode = new CSTNUVertex(startId, vertexDTO.getX(), vertexDTO.getY());
       CSTNUVertex endNode = new CSTNUVertex(endId, vertexDTO.getX(), vertexDTO.getY());

       CSTNUEdge startEndEdge = new CSTNUEdge(startEndId, startId, endId, 0, Type.NORMAL, "{}" );
       CSTNUEdge endStartEdge = new CSTNUEdge(endStartId, endId, startId, 0, Type.NORMAL, "{}");

       directedGraph.addEdge(startEndEdge, startNode, endNode, EdgeType.DIRECTED);
       directedGraph.addEdge(endStartEdge, endNode, startNode, EdgeType.DIRECTED);

   }

   public void addAND(DirectedSparseMultigraph directedGraph, CSTNUNodeDTO vertexDTO){

       String startId = vertexDTO.getId().concat("_S");
       String endId = vertexDTO.getId().concat("_E");
       String startEndId = vertexDTO.getId().concat("_S_E");
       String endStartId = vertexDTO.getId().concat("_E_S");


       CSTNUVertex startNode = new CSTNUVertex(startId, vertexDTO.getX(), vertexDTO.getY());
       CSTNUVertex endNode = new CSTNUVertex(endId, vertexDTO.getX(), vertexDTO.getY());

       CSTNUEdge startEndEdge = new CSTNUEdge(startEndId, startId, endId, 0, Type.NORMAL, "{}");
       CSTNUEdge endStartEdge = new CSTNUEdge(endStartId, endId, startId, 0, Type.NORMAL, "{}");

       directedGraph.addEdge(startEndEdge, startNode, endNode, EdgeType.DIRECTED);
       directedGraph.addEdge(endStartEdge, endNode, startNode, EdgeType.DIRECTED);


   }



   public void addConstraint(DirectedSparseMultigraph directedGraph, CSTNUEdgeDTO cstnuEdgeDTO){

       String sourceId = cstnuEdgeDTO.getTarget();
       String targetId = cstnuEdgeDTO.getSource();

       CSTNUEdge constraint = new CSTNUEdge(cstnuEdgeDTO.getId().concat("_LBC"), sourceId, targetId, 0, Type.NORMAL, cstnuEdgeDTO.getLabelledValues());
       Collection<CSTNUVertex> vertices = directedGraph.getVertices();
       CSTNUVertex target=null;
       CSTNUVertex source=null;
       for (CSTNUVertex vertex: vertices){
           System.out.println(vertex.getId());
           if(vertex.getId().equals(targetId)){
               target = vertex;
           }
           else if(vertex.getId().equals(sourceId)){
               source = vertex;
           }
       }
       if(target != null && source != null){
           directedGraph.addEdge(constraint, source, target, EdgeType.DIRECTED);
       }
       else{
           System.out.println("Cannot add. Missing target or source!" + sourceId + " " + targetId);
           //error
       }

   }

   public void updateTask(DirectedSparseMultigraph directedGraph, TaskDTO taskDTO){

       String startEndId = taskDTO.getId().concat("_S_E");
       String endStartId = taskDTO.getId().concat("_E_S");

       String startId = taskDTO.getId().concat("_S");
       String endId = taskDTO.getId().concat("_E");


       Collection<CSTNUEdge> edges = directedGraph.getEdges();

       for (CSTNUEdge edge: edges){
           if(edge.getId().equals(startEndId)){
               edge.setValue(taskDTO.getMax());
               if(taskDTO.getC() == 1){
                   edge.setType(Type.CONTINGENT);
               }else {
                   edge.setType(Type.NORMAL);
               }

           }
           else if(edge.getId().equals(endStartId)){
               edge.setValue(taskDTO.getMin()*-1);
               if(taskDTO.getC() == 1){
                   edge.setType(Type.CONTINGENT);
               }else {
                   edge.setType(Type.NORMAL);
               }
           }
       }

       Collection<CSTNUVertex> vertices = directedGraph.getVertices();

       for (CSTNUVertex vertex: vertices) {
           if (vertex.getId().equals(startId)) {
               vertex.setLabel(taskDTO.getPrepLabel());

           } else if (vertex.getId().equals(endId)) {
               vertex.setLabel(taskDTO.getPrepLabel());
           }
       }

       invalidTaskManager.remove(taskDTO.getId());



   }

   public void updateConstraint(DirectedSparseMultigraph directedGraph, ConstraintDTO constraintDTO){

       CSTNUVertex target=null;
       CSTNUVertex source=null;

       if(constraintDTO.getConstraintType() == 1){

           String sourceId = constraintDTO.getTarget();
           String targetId = constraintDTO.getSource();


           String constraintId = constraintDTO.getId().concat("_LBC");

           String checkId = constraintDTO.getId().concat("_UBC");

           Collection<CSTNUEdge> edges = directedGraph.getEdges();

           CSTNUEdge ubc=null;
           CSTNUEdge lbc=null;
           for (CSTNUEdge edge: edges){
               if(edge.getId().equals(checkId)){
                   ubc=edge;

               }
               else if(edge.getId().equals(constraintId)){
                   lbc=edge;

               }
           }
           if(ubc!=null){


               if(lbc==null){
                   lbc=new CSTNUEdge(constraintId,sourceId,targetId,ubc.getValue(),ubc.getType(),ubc.getLabeledValues());
                   directedGraph.addEdge(lbc,directedGraph.getDest(ubc),directedGraph.getSource(ubc), EdgeType.DIRECTED);
               }
               directedGraph.removeEdge(ubc);

           }
           String updatedLabel = Integer.toString(-1*constraintDTO.getLabeledValues());
           lbc.setLabeledValues("{(" + updatedLabel + ",⊡)}");
           lbc.setSource(sourceId);
           lbc.setTarget(targetId);


       } else {
           String sourceId = constraintDTO.getSource();
           String targetId = constraintDTO.getTarget();

           String constraintId = constraintDTO.getId().concat("_UBC");

           String checkId = constraintDTO.getId().concat("_LBC");

           Collection<CSTNUEdge> edges = directedGraph.getEdges();

           CSTNUEdge lbc=null;
           CSTNUEdge ubc=null;
           for (CSTNUEdge edge: edges){
               if(edge.getId().equals(checkId)){
                   lbc=edge;

               }
               if(edge.getId().equals(constraintId)){
                   ubc=edge;

               }
           }
           if(lbc!=null){
               if(ubc==null){

                   ubc=new CSTNUEdge(constraintId,sourceId,targetId,lbc.getValue(),lbc.getType(),lbc.getLabeledValues());
                   directedGraph.addEdge(ubc,directedGraph.getDest(lbc),directedGraph.getSource(lbc), EdgeType.DIRECTED);
               }
               directedGraph.removeEdge(lbc);


           }
           ubc.setLabeledValues("{(" + constraintDTO.getLabeledValues() + ",⊡)}");
           ubc.setSource(sourceId);
           ubc.setTarget(targetId);
       }
   }

   public void updateXOR(DirectedSparseMultigraph directedGraph, XorDTO xorDTO){

       String endId = xorDTO.getId().concat("_E");

       Collection<CSTNUVertex> vertices = directedGraph.getVertices();

       for (CSTNUVertex vertex: vertices){
           if(vertex.getId().equals(endId)){
               vertex.setObs(xorDTO.getObs());

           }

       }


   }

   public void deleteShape(DirectedSparseMultigraph directedGraph, String id){

       String startId = id.concat("_S");
       String endId = id.concat("_E");
       String startEndId = id.concat("_S_E");
       String endStartId = id.concat("_E_S");

       Collection<CSTNUEdge> edges = directedGraph.getEdges();
       Collection<CSTNUVertex> vertices = directedGraph.getVertices();

       CSTNUVertex startNode = null;
       CSTNUVertex endNode = null;

       CSTNUEdge startEndEdge = null;
       CSTNUEdge endStartEdge = null;

       for (CSTNUVertex vertex: vertices) {
           if (vertex.getId().equals(startId)) {
               startNode = vertex;
           } else if (vertex.getId().equals(endId)) {
               endNode = vertex;
           }
       }

       directedGraph.removeVertex(startNode);
       directedGraph.removeVertex(endNode);


       for (CSTNUEdge edge: edges) {
           if (edge.getId().equals(startEndId)) {
               startEndEdge = edge;
           } else if (edge.getId().equals(endStartId)) {
               endStartEdge = edge;
           }
       }

       directedGraph.removeVertex(startEndEdge);
       directedGraph.removeVertex(endStartEdge);


   }

    public void deleteConstraint(DirectedSparseMultigraph directedGraph, String id){

        String lbcId = id.concat("_LBC");
        String ubcId = id.concat("_UBC");

        Collection<CSTNUEdge> edges = directedGraph.getEdges();

        CSTNUEdge lbc = null;
        CSTNUEdge ubc = null;


        for (CSTNUEdge edge: edges) {
            if (edge.getId().equals(lbcId)) {
                lbc = edge;
            } else if (edge.getId().equals(ubcId)) {
                ubc = edge;
            }
        }

        if(lbc != null) {
            directedGraph.removeEdge(lbc);
        }
        if(ubc != null) { directedGraph.removeEdge(ubc); }


    }

}

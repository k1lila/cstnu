package com.example.cstnu.model;

import edu.uci.ics.jung.graph.AbstractTypedGraph;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;


public class CSTNUGraph  extends AbstractTypedGraph<CSTNUVertex, CSTNUEdge>
implements DirectedGraph<CSTNUVertex, CSTNUEdge> {

    //private List<CSTNUVertex> CSTNUVertexes;
    //private List<CSTNUEdge> CSTNUEdges;

    private HashMap<String,CSTNUVertex> CSTNUVertexes;
    private HashMap<String,CSTNUEdge> CSTNUEdges;

    public CSTNUGraph(EdgeType edge_type) {
        super(edge_type);
    }


    public HashMap<String, CSTNUVertex> getCSTNUVertexes() {
        return CSTNUVertexes;
    }

    public void setCSTNUVertexes(HashMap<String, CSTNUVertex> CSTNUVertexes) {
        this.CSTNUVertexes = CSTNUVertexes;
    }

    public HashMap<String, CSTNUEdge> getCSTNUEdges() {
        return CSTNUEdges;
    }

    public void setCSTNUEdges(HashMap<String, CSTNUEdge> CSTNUEdges) {
        this.CSTNUEdges = CSTNUEdges;
    }

    public void initCSTNUGraph(){
        CSTNUVertex startNode = new CSTNUVertex("Z",1,1);

        this.CSTNUVertexes.put("Z",startNode);
    }

    public CSTNUEdge getCSTNUEdge (String key){
       return this.CSTNUEdges.get(key);
    }

    public CSTNUVertex getCSTNUVertex (String key){
        return this.CSTNUVertexes.get(key);
    }

    public void addCSTNUEdge (String key, CSTNUEdge value){
        this.CSTNUEdges.put(key, value);
    }

    public void addCSTNUVertex (String key, CSTNUVertex value){
        this.CSTNUVertexes.put(key, value);
    }

    public void removeCSTNUEdge (String key){
        this.CSTNUEdges.remove(key);
    }

    public void removeCSTNUVertex (String key){
        this.CSTNUVertexes.remove(key);
    }

    public void updateCSTNUEdge (String key, CSTNUEdge newValue){
          this.CSTNUEdges.replace(key, newValue);
    }

    public void updateCSTNUVertex (String key, CSTNUVertex newValue){
          this.CSTNUVertexes.replace(key, newValue);
    }

    public void display(){
        System.out.println(CSTNUVertexes);
        System.out.println(CSTNUEdges);
    }

    @Override
    public Collection<CSTNUEdge> getEdges() {
        return null;
    }

    @Override
    public Collection<CSTNUVertex> getVertices() {
        return null;
    }

    @Override
    public boolean containsVertex(CSTNUVertex vertex) {
        return false;
    }

    @Override
    public boolean containsEdge(CSTNUEdge cstnuEdge) {
        return false;
    }

    @Override
    public int getEdgeCount() {
        return 0;
    }

    @Override
    public int getVertexCount() {
        return 0;
    }

    @Override
    public Collection<CSTNUVertex> getNeighbors(CSTNUVertex vertex) {
        return null;
    }

    @Override
    public Collection<CSTNUEdge> getIncidentEdges(CSTNUVertex vertex) {
        return null;
    }

    @Override
    public Collection<CSTNUVertex> getIncidentVertices(CSTNUEdge cstnuEdge) {
        return null;
    }

    @Override
    public CSTNUEdge findEdge(CSTNUVertex vertex, CSTNUVertex v1) {
        return null;
    }

    @Override
    public Collection<CSTNUEdge> findEdgeSet(CSTNUVertex vertex, CSTNUVertex v1) {
        return null;
    }

    @Override
    public boolean addVertex(CSTNUVertex vertex) {
        return false;
    }

    @Override
    public boolean addEdge(CSTNUEdge cstnuEdge, Collection<? extends CSTNUVertex> collection) {
        return false;
    }

    @Override
    public boolean addEdge(CSTNUEdge cstnuEdge, Collection<? extends CSTNUVertex> collection, EdgeType edgeType) {
        return false;
    }

    @Override
    public boolean removeVertex(CSTNUVertex vertex) {
        return false;
    }

    @Override
    public boolean removeEdge(CSTNUEdge cstnuEdge) {
        return false;
    }

    @Override
    public boolean isNeighbor(CSTNUVertex vertex, CSTNUVertex v1) {
        return false;
    }

    @Override
    public boolean isIncident(CSTNUVertex vertex, CSTNUEdge cstnuEdge) {
        return false;
    }

    @Override
    public int degree(CSTNUVertex vertex) {
        return 0;
    }

    @Override
    public int getNeighborCount(CSTNUVertex vertex) {
        return 0;
    }

    @Override
    public int getIncidentCount(CSTNUEdge cstnuEdge) {
        return 0;
    }

    @Override
    public EdgeType getEdgeType(CSTNUEdge cstnuEdge) {
        return null;
    }

    @Override
    public EdgeType getDefaultEdgeType() {
        return null;
    }

    @Override
    public Collection<CSTNUEdge> getEdges(EdgeType edgeType) {
        return null;
    }

    @Override
    public int getEdgeCount(EdgeType edgeType) {
        return 0;
    }

    @Override
    public Collection<CSTNUEdge> getInEdges(CSTNUVertex vertex) {
        return null;
    }

    @Override
    public Collection<CSTNUEdge> getOutEdges(CSTNUVertex vertex) {
        return null;
    }

    @Override
    public Collection<CSTNUVertex> getPredecessors(CSTNUVertex vertex) {
        return null;
    }

    @Override
    public Collection<CSTNUVertex> getSuccessors(CSTNUVertex vertex) {
        return null;
    }

    @Override
    public int inDegree(CSTNUVertex vertex) {
        return 0;
    }

    @Override
    public int outDegree(CSTNUVertex vertex) {
        return 0;
    }

    @Override
    public boolean isPredecessor(CSTNUVertex vertex, CSTNUVertex v1) {
        return false;
    }

    @Override
    public boolean isSuccessor(CSTNUVertex vertex, CSTNUVertex v1) {
        return false;
    }

    @Override
    public int getPredecessorCount(CSTNUVertex vertex) {
        return 0;
    }

    @Override
    public int getSuccessorCount(CSTNUVertex vertex) {
        return 0;
    }

    @Override
    public CSTNUVertex getSource(CSTNUEdge cstnuEdge) {
        return null;
    }

    @Override
    public CSTNUVertex getDest(CSTNUEdge cstnuEdge) {
        return null;
    }

    @Override
    public boolean isSource(CSTNUVertex vertex, CSTNUEdge cstnuEdge) {
        return false;
    }

    @Override
    public boolean isDest(CSTNUVertex vertex, CSTNUEdge cstnuEdge) {
        return false;
    }

    @Override
    public boolean addEdge(CSTNUEdge cstnuEdge, CSTNUVertex vertex, CSTNUVertex v1) {
        return false;
    }

    @Override
    public boolean addEdge(CSTNUEdge cstnuEdge, CSTNUVertex vertex, CSTNUVertex v1, EdgeType edgeType) {
        return false;
    }

    @Override
    public boolean addEdge(CSTNUEdge cstnuEdge, Pair<? extends CSTNUVertex> pair, EdgeType edgeType) {
        return false;
    }

    @Override
    public Pair<CSTNUVertex> getEndpoints(CSTNUEdge cstnuEdge) {
        return null;
    }

    @Override
    public CSTNUVertex getOpposite(CSTNUVertex vertex, CSTNUEdge cstnuEdge) {
        return null;
    }

}

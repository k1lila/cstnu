package com.example.cstnu.model;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;

@Component
public class ObservedPropositionManager {

    private HashMap<String, Integer> observedPropositions = new HashMap<>();


    public void addObservedPropositions (String id, int value ){

        observedPropositions.put(id, value);
    }

    public void removeObservedPropositions (String id){

        observedPropositions.remove(id);
    }

    public int countObservedPropositions(){

        int count = observedPropositions.size();

        return count;

    }
}

package com.example.cstnu.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;

@Component
public class ContingencyManager {

    private HashMap<String, Integer> contingencies = new HashMap<>();


    public void addContingency (String id, int value ){

        contingencies.put(id, value);
    }

    public void removeContingency (String id){

        contingencies.remove(id);
    }

    public int countContingencies (){

        int count = Collections.frequency(contingencies.values(), 1);

        return count;

    }
}

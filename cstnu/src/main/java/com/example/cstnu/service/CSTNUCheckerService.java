package com.example.cstnu.service;

import com.example.cstnu.dto.ResultDTO;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

@Service
public class CSTNUCheckerService {

    private ArrayList<String> invalidTaskManager;

    public CSTNUCheckerService(ArrayList<String> invalidTaskManager){

        this.invalidTaskManager = invalidTaskManager;
    }


    public ResultDTO checkCSTNU(){
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");


        ProcessBuilder builder = new ProcessBuilder();
        if (isWindows) {
            /*
            builder.command("cmd.exe", "/c", "java", "-cp", "C:/Users/k1lil/OneDrive/Desktop/CSTNU-Tool-4.4.jar",
                    "it.univr.di.cstnu.algorithms.Checker","-type","cstnu","-numRepetitionDCCheck", "10",
                    "C:/Users/k1lil/IdeaProjects/microservices-demo/src/main/java/io/pivotal/microservices/helpers/testFile.cstnu");
              */
            builder.command("cmd.exe", "/c", "java", "-jar", "C:/Users/k1lil/IdeaProjects/TestLibrary/out/artifacts/TestLibrary_jar2/TestLibrary.jar");
        } else {
            builder.command("sh", "-c", "ls");
        }
        builder.directory(new File(System.getProperty("user.home")));
        //Process process = builder.start();
        // Read and print the standard output stream of the process
        try {
            // Start a new java process
            Process process = builder.start();


            // Read and print the standard output stream of the process
            try (BufferedReader input =
                         new BufferedReader(new
                                 InputStreamReader(process.getInputStream()))) {
                String line;
                StringBuilder result = new StringBuilder();
                ResultDTO resultDTO = new ResultDTO();

                int i = 0;
                while ((line = input.readLine()) != null) {
                    System.out.println(line );
                    if(i == 0){
                        resultDTO.setStatus(line);
                    }
                    else{
                        result.append(line);
                    }
                    i++;

                }
                resultDTO.setNegativeLoopId(result.toString());
                return resultDTO;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResultDTO();

    }

}

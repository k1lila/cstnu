package com.example.cstnu.helpers;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

class StreamGobbler implements Runnable {
    private InputStream inputStream;
    private Consumer<String> consumer;

    public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
        this.inputStream = inputStream;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        new BufferedReader(new InputStreamReader(inputStream)).lines()
                .forEach(consumer);
    }
}

public class ReadFile {
    public static void main(String[] args) throws InterruptedException, IOException {

        File myObj = new File ("src/main/java/io/pivotal/microservices/helpers/testFile.cstnu");

        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");

        String content = null;
        try {
            content = FileUtils.readFileToString(myObj, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(content);

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
                while ((line = input.readLine()) != null) {
                    System.out.println(line );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //int exitCode = process.waitFor();
        //System.out.println(":finished");
      //  assert exitCode == 0;
    }
}
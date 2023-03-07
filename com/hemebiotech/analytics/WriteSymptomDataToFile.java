package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class WriteSymptomDataToFile implements ISymptomWriter {
    private String filepath;

    /**
     * @param filepath a full or partial path to file with symptom strings in it, one per line
     */
    public WriteSymptomDataToFile(String filepath) {
        this.filepath = filepath;
    }

    public WriteSymptomDataToFile() {
        this.filepath = "result.out";
    }

    @Override
    public void writeSymptoms(Map<String, Integer> symptoms) {
        try (FileWriter fileWriter = new FileWriter(filepath);) {
            for (Map.Entry<String, Integer> symptomsCountEntry : symptoms.entrySet()) {
                fileWriter.write(symptomsCountEntry.getKey() + " : " + symptomsCountEntry.getValue() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
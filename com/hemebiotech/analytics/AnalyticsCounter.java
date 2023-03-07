package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsCounter {
  private final ISymptomReader reader;
  private final ISymptomWriter writer;
  private static int headacheCount = 0;
  private static int rashCount = 0;
  private static int pupilCount = 0;

  public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) {
    this.reader = reader;
    this.writer = writer;
  }

  /**
   * get list of symptoms from files.
   * @return list of symptoms
   */
  public List<String> getSymptoms() {
    return reader.getSymptoms();
  }

  /**
   * Create a maps with symptoms at key, and set for value the number of iteration.
   * @param symptoms list of symptoms
   * @return map of symptoms associated with their number of iteration
   */
  public Map<String, Integer> countSymptoms(List<String> symptoms) {
    final HashMap<String, Integer> symptomsCountMaps = new HashMap<>();
    symptoms.forEach(symptom -> {
      symptomsCountMaps.merge(symptom, 1, Integer::sum);
    });
    return symptomsCountMaps;
  }

  /**
   * Sort the maps for return a maps ordored by symptoms.
   * @param symptoms maps of symptoms
   * @return TreeMap of the previous maps given
   */
  public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
    return new TreeMap<>(symptoms);
  }

  /**
   * Write into a file the maps given in parameter.
   * @param symptoms maps of symptoms
   */
  public void writeSymptoms(Map<String, Integer> symptoms) {
    writer.writeSymptoms(symptoms);
  }

  public static void main(String[] args) throws Exception {
    // first get input
    BufferedReader reader = new BufferedReader(new FileReader("symptoms.txt"));
    String line = reader.readLine();
    while (line != null) {
      System.out.println("symptom from file: " + line);
      if (line.equals("headache")) {
        headacheCount++;
        System.out.println("number of headaches: " + headacheCount);
      } else if (line.equals("rash")) {
        rashCount++;
      } else if (line.equals("dialated pupils")) {
        pupilCount++;
      }

      line = reader.readLine();    // get another symptom
    }

    // next generate output
    FileWriter writer = new FileWriter("result.out");
    writer.write("headache: " + headacheCount + "\n");
    writer.write("rash: " + rashCount + "\n");
    writer.write("dialated pupils: " + pupilCount + "\n");
    writer.close();
  }
}
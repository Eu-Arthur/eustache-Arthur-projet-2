package com.hemebiotech.analytics;

public class Main {
  /**
   * Method for execute the process.
   * @param args List of arguments
   */
  public static void main(String[] args) {
    final ISymptomWriter writer = new WriteSymptomDataToFile();
    final ISymptomReader reader = new ReadSymptomDataFromFile("symptoms.txt");
    final AnalyticsCounter analyticsCounter = new AnalyticsCounter(reader, writer);
    analyticsCounter.writeSymptoms(
            analyticsCounter.sortSymptoms(
                    analyticsCounter.countSymptoms(
                            analyticsCounter.getSymptoms())));
  }
}
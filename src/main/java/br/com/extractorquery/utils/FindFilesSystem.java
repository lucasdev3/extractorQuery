package br.com.extractorquery.utils;

import java.io.File;
import java.util.List;

public class FindFilesSystem {

  public static void getFound() {
    String sourceInputDirectory = "/automation/merge/input/";
    String sourceOutputDirectory = "/automation/merge/output/";

    File folder = new File(sourceInputDirectory);
    for (File file : folder.listFiles()) {
      if (!file.isDirectory()) {
        System.out.println(file.getName());
      }
    }
  }

  public static void main(String[] args) {
    getFound();
  }
}

package br.com.extractorquery.utils;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class FindFilesSystem {

  public List<LinkedHashMap<String, String>> getFound() {
    String sourceInputDirectory = "/automation/merge/input/";

    List<LinkedHashMap<String, String>> list = new LinkedList<>();
    LinkedHashMap<String, String> map = new LinkedHashMap<>();

    File search = new File(sourceInputDirectory);
    File[] files = search.listFiles();
    if (files != null) {
      int count = 1;
      for (File file : files) {
        if (!file.isDirectory()) {
          System.out.println(file.getName());
          map.put("fileName" + count, file.getName());
          map.put("path" + count, file.getPath());
        }
        list.add(map);
        count++;
      }
    } else {
      System.out.println("Nenhum arquivo de entrada encontrado!");
    }
    return list;
  }

}

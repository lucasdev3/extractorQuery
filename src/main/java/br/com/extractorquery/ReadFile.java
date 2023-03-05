//package br.com.extractorquery;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class ReadFile {
//
//  private final String pathName;
//
//  public ReadFile(String pathName) {
//    this.pathName = pathName;
//  }
//
//  public String getPathName() {
//    return pathName;
//  }
//
//  public List<String> getContent() throws IOException {
//    List<String> contentFile = new ArrayList<>();
//    File file = new File(this.pathName);
//    FileReader fileReader = new FileReader(file);
//    BufferedReader bufferedReader = new BufferedReader(fileReader);
//    while (bufferedReader.ready()) {
//      contentFile.add(bufferedReader.readLine());
//    }
//    return contentFile;
//  }
//
//  public List<DataModel> extractQueryInsert(List<String> list) {
//    List<DataModel> listDataModel = new ArrayList<>();
//    int line = 1;
//    for (String linha : list) {
//      System.out.println("Analisando linha " + line + " ...");
////      linha.replace("")
//      String pattern = "\\bINSERT\\s+INTO\\s+(\\S+)\\s*\\(([^)]+)\\)\\s*VALUES\\s*\\(([^)]+\\)?)\\)";
//      Pattern regex = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
//      Matcher matcher = regex.matcher(linha);
//      if (matcher.find()) {
//        String queryInsert = matcher.group(0);
//        String tableName = matcher.group(1);
//        List<String> columns = Arrays.asList(matcher.group(2).replaceAll(" ", "").split(","));
//        List<String> values = Arrays.asList(matcher.group(3).replaceAll(" ", "").split(","));
//        if (columns.size() != values.size()) {
//          System.out.println("NO MATCH");
//          System.out.println("Query inválidada por inconsistência. Linha: " + line);
//          System.out.println("Query insert: " + queryInsert);
//          System.out.println("\n");
//        } else {
//          System.out.println("Query insert: " + queryInsert);
//          System.out.println("Table Name: " + tableName);
//          System.out.println("Columns insert: " + columns);
//          System.out.println("Values insert: " + values);
//          System.out.println("\n");
//          listDataModel.add(new DataModel(queryInsert, tableName, columns, values));
//        }
//      } else {
//        System.out.println("NO MATCH");
//        System.out.println("Query inválida. Linha: " + line);
//      }
//      line++;
//    }
//    return listDataModel;
//  }
//
//}

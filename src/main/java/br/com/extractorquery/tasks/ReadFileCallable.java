package br.com.extractorquery.tasks;

import br.com.extractorquery.DataModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.crypto.Data;

public class ReadFileCallable implements Callable<List<DataModel>> {

  private final String pathName;

  private final String fileName;

  public ReadFileCallable(String pathName, String fileName) {
    this.pathName = pathName;
    this.fileName = fileName;
  }

  public String getPathName() {
    return pathName;
  }

  public String getFileName() {
    return fileName;
  }

  @Override
  public List<DataModel> call() {
    String threadName = Thread.currentThread().getName();

    System.out.println("Thread ReadFileCallable: " + threadName);

    List<DataModel> listDataModel = new LinkedList<>();
    List<String> contentFile = new LinkedList<>();

    // LEITURA DE ARQUIVO
    try {
      File file = new File(this.pathName);
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while (bufferedReader.ready()) {
        contentFile.add(bufferedReader.readLine());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // EXTRAINDO DADOS E PROCESSANDO RETORNO
    int line = 1;
    for (String linha : contentFile) {
      System.out.println("Analisando linha " + line + " ...");
//      linha.replace("")
      String pattern = "\\bINSERT\\s+INTO\\s+(\\S+)\\s*\\(([^)]+)\\)\\s*VALUES\\s*\\(([^)]+\\)?)\\)";
      Pattern regex = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
      Matcher matcher = regex.matcher(linha);
      if (matcher.find()) {
        String queryInsert = matcher.group(0);
        String tableName = matcher.group(1);
        List<String> columns = Arrays.asList(matcher.group(2).replaceAll(" ", "").split(","));
        List<String> values = Arrays.asList(matcher.group(3).replaceAll(" ", "").split(","));
        if (columns.size() != values.size()) {
          System.out.println("NO MATCH");
          System.out.println("Query inválidada por inconsistência. Linha: " + line);
          System.out.println("Query insert: " + queryInsert);
          System.out.println("\n");
        } else {
          System.out.println("Query insert: " + queryInsert);
          System.out.println("Table Name: " + tableName);
          System.out.println("Columns insert: " + columns);
          System.out.println("Values insert: " + values);
          System.out.println("\n");
          listDataModel.add(new DataModel(queryInsert, tableName, columns, values));
        }
      } else {
        System.out.println("NO MATCH");
        System.out.println("Query inválida. Linha: " + line);
      }
      line++;
    }
    return listDataModel;
  }

}

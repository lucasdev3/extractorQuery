package br.com.extractorquery.tasks;

import br.com.extractorquery.DataModel;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class WriteFileCallable implements Callable<String> {

  private String pathName;

  private String fileName;

  private List<DataModel> dataModels;

  public WriteFileCallable(List<DataModel> dataModels) {
    this.dataModels = dataModels;
  }

  public String getPathName() {
    return pathName;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public List<DataModel> getDataModels() {
    return dataModels;
  }

  public void setDataModels(List<DataModel> dataModels) {
    this.dataModels = dataModels;
  }

  @Override
  public String call() {

    String threadName = Thread.currentThread().getName();

    System.out.println("Thread WriteFileCallable: " + threadName);

    // CONTEUDO DE SAIDA
    List<String> content = new ArrayList<>();
    // LINHA DO ARQUIVO
    System.out.println("Iniciando criação do arquivo de rollback...\nThread: " + threadName);
    for (DataModel dataModel : this.dataModels) {
      StringBuilder query = new StringBuilder("DELETE " + dataModel.getTableName() + " WHERE ");
      for (int i = 0; i < dataModel.getColumns().size(); i++) {
        String currentColumn = dataModel.getColumns().get(i);
        String currentValue = dataModel.getValues().get(i);
        boolean isLastArgumenth = i == dataModel.getColumns().size() - 1;
        if (i == 0) {
          query.append(currentColumn).append(" = ")
              .append(currentValue).append(isLastArgumenth ? "" : ", ");
        } else {
          query.append(" AND ").append(currentColumn).append(" = ")
              .append(currentValue).append(isLastArgumenth ? "" : ", ");
        }
      }
      query.append(";");
      content.add(String.valueOf(query));
      System.out.println(threadName + " - Query Rollback: " + query);
    }
    // CRIACAO DO ARQUIVO DE ROLLBACK
    try {
      File file = new File(
          this.getDataModels().get(0).getPath().replace("input", "output")
              .replace(".sql", "_rollback.sql"));
      FileWriter fw = new FileWriter(file);
      PrintWriter pw = new PrintWriter(fw);
      for (String linha : content) {
        pw.println(linha);
      }
      System.out.println("Arquivo criado! Thread: " + threadName);
      pw.close();
      fw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}

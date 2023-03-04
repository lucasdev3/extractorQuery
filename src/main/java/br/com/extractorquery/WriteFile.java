package br.com.extractorquery;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WriteFile {

  private String pathName;

  public WriteFile() {
  }

  public WriteFile(String pathName) {
    this.pathName = pathName;
  }

  public String getPathName() {
    return pathName;
  }

  public void generateFileRollbackOfInsert(List<DataModel> listQueryInsert) {
    List<String> content = new ArrayList<>();
    List<String> reservedKeysSql = Arrays.asList("PROCEDURE", "FUNCTION", "DECLARE", "TO_DATE");
    System.out.println("Carregando arquivo de entrada...");
    for (DataModel dataModel : listQueryInsert) {
      // LINHA DO ARQUIVO
      StringBuilder query = new StringBuilder("DELETE " + dataModel.getTableName() + " WHERE ");
      for (int i = 0; i < dataModel.getColumns().size(); i++) {
        String currentColumn = dataModel.getColumns().get(i);
        String currentValue = dataModel.getValues().get(i);
        boolean isNumber = currentValue.matches("\\d+");
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
    }
    System.out.println("Iniciando criação do arquivo de rollback...");
    try {
      File file = new File(this.pathName);
      FileWriter fw = new FileWriter(file);
      PrintWriter pw = new PrintWriter(fw);
//      BufferedWriter bw = new BufferedWriter(fw);
      for (String linha : content) {
        pw.println(linha);
      }
      System.out.println("Arquivo criado!");
      pw.close();
      fw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}

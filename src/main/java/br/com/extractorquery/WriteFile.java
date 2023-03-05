//package br.com.extractorquery;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//public class WriteFile {
//
//  private final String pathName;
//
//
//  public WriteFile(String pathName) {
//    this.pathName = pathName;
//  }
//
//  public String getPathName() {
//    return pathName;
//  }
//
//  public void generateFileRollbackOfInsert(List<DataModel> listQueryInsert) {
//    // CONTEUDO DE SAIDA
//    List<String> content = new ArrayList<>();
//    // LINHA DO ARQUIVO
//    System.out.println("Iniciando criação do arquivo de rollback...");
//    for (DataModel dataModel : listQueryInsert) {
//      StringBuilder query = new StringBuilder("DELETE " + dataModel.getTableName() + " WHERE ");
//      for (int i = 0; i < dataModel.getColumns().size(); i++) {
//        String currentColumn = dataModel.getColumns().get(i);
//        String currentValue = dataModel.getValues().get(i);
//        boolean isLastArgumenth = i == dataModel.getColumns().size() - 1;
//        if (i == 0) {
//          query.append(currentColumn).append(" = ")
//              .append(currentValue).append(isLastArgumenth ? "" : ", ");
//        } else {
//          query.append(" AND ").append(currentColumn).append(" = ")
//              .append(currentValue).append(isLastArgumenth ? "" : ", ");
//        }
//      }
//      query.append(";");
//      content.add(String.valueOf(query));
//      System.out.println("Query Rollback: " + query);
//    }
//    try {
//      File file = new File(this.pathName);
//      FileWriter fw = new FileWriter(file);
//      PrintWriter pw = new PrintWriter(fw);
//      for (String linha : content) {
//        pw.println(linha);
//      }
//      System.out.println("Arquivo criado!");
//      pw.close();
//      fw.close();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//
//}

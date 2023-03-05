package br.com.extractorquery;

import java.util.List;

public class DataModel {

  private String query;
  private String tableName;
  private List<String> columns;
  private List<String> values;

  private String fileName;

  private String path;

  public DataModel(String query, String tableName, List<String> columns,
      List<String> values, String fileName, String path) {
    this.query = query;
    this.tableName = tableName;
    this.columns = columns;
    this.values = values;
    this.fileName = fileName;
    this.path = path;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public List<String> getColumns() {
    return columns;
  }

  public void setColumns(List<String> columns) {
    this.columns = columns;
  }

  public List<String> getValues() {
    return values;
  }

  public void setValues(List<String> values) {
    this.values = values;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}

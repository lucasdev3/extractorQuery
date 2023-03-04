package br.com.extractorquery;

import java.util.List;

public class DataModel {

  private String query;
  private String tableName;
  private List<String> columns;
  private List<String> values;

  public DataModel(String query, String tableName, List<String> columns,
      List<String> values) {
    this.query = query;
    this.tableName = tableName;
    this.columns = columns;
    this.values = values;
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
}

package br.com.extractorquery;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {

    /*

    A APLICAÇÃO DEVERÁ BUSCAR NO DIRETÓRIO DE ENTRADA O ARQUIVO script.sql
    O QUAL DEVERÁ TER UMA LISTA DE INSERTS. O APLICAÇÃO IRÁ COLETAR OS INSERTS E
    CRIARÁ UM NOVO ARQUIVO NO DIRETÓRIO DE SAÍDA COM O FINAL _rollback.sql

    CERTIFIQUE-SE DO DIRETÓRIO E ARQUIVO DE ENTRADA EXISTIREM.

     */

    String pathNameInput = "C:\\DEV\\RollbackSql\\pasta1\\script.sql";
    String pathNameOutput = "C:\\DEV\\RollbackSql\\pasta2\\script_rollback.sql";

    ReadFile readFile = new ReadFile(pathNameInput);
    List<String> content = readFile.getContent();
    List<DataModel> list = readFile.extractQueryInsert(content);

    WriteFile writeFile = new WriteFile(pathNameOutput);
    writeFile.generateFileRollbackOfInsert(list);
  }
}
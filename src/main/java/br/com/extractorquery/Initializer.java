package br.com.extractorquery;

import br.com.extractorquery.tasks.ReadFileCallable;
import br.com.extractorquery.tasks.WriteFileCallable;
import br.com.extractorquery.utils.FindFilesSystem;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Initializer {

  public static void main(String[] args) throws InterruptedException {

    // Busca de arquivos no diretorio de entrada
    FindFilesSystem findFilesSystem = new FindFilesSystem();
    List<LinkedHashMap<String, String>> found = findFilesSystem.getFound();

    // create tasks
    List<Callable<List<DataModel>>> tasksReader = new LinkedList<>();
    List<Callable<String>> tasksWriter = new LinkedList<>();

    if (found != null) {
      int count = 1;
      for (LinkedHashMap<String, String> map : found) {
        String fileName = map.get("fileName" + count);
        String path = map.get("path" + count);

        ReadFileCallable readFileCallable = new ReadFileCallable(path, fileName);
        tasksReader.add(readFileCallable);
        count++;
      }
    } else {
      System.out.println("Nenhum arquivo encontrado!");

    }

    ExecutorService executorService = Executors.newFixedThreadPool(4);
    List<Future<List<DataModel>>> results = executorService.invokeAll(tasksReader);

    // Collect Results
    System.out.println("Result: ");
    try {
      for (Future<List<DataModel>> future : results) {
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss'.sql'").format(new Date());
        List<DataModel> dataModels = future.get();
        WriteFileCallable writeFile = new WriteFileCallable(dataModels);
        tasksWriter.add(writeFile);
        Thread.sleep(1000); // TIRAR O GARGALO DA THREAD
      }
      executorService.invokeAll(tasksWriter);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      executorService.shutdown();
    }

    System.out.println("Programa finalizado");

  }
}

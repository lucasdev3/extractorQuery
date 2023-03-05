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
      for (LinkedHashMap<String, String> map : found) {
        String fileName = map.get("fileName");
        String path = map.get("path");

        ReadFileCallable readFileCallable = new ReadFileCallable(path, fileName);
        tasksReader.add(readFileCallable);

      }
    }

    ReadFileCallable readFileCallable = new ReadFileCallable("/automation/merge/input/script.sql",
        "script.sql");
    ReadFileCallable readFileCallable2 = new ReadFileCallable("/automation/merge/input/script2.sql",
        "script2.sql");
    ReadFileCallable readFileCallable3 = new ReadFileCallable("/automation/merge/input/script3.sql",
        "script3.sql");
    ReadFileCallable readFileCallable4 = new ReadFileCallable("/automation/merge/input/script4.sql",
        "script4.sql");
    ReadFileCallable readFileCallable5 = new ReadFileCallable("/automation/merge/input/script5.sql",
        "script5.sql");
    tasksReader.add(readFileCallable);
    tasksReader.add(readFileCallable2);
    tasksReader.add(readFileCallable3);
    tasksReader.add(readFileCallable4);
    tasksReader.add(readFileCallable5);

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

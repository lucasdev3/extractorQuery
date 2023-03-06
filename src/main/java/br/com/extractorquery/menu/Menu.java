package br.com.extractorquery.menu;

import static br.com.extractorquery.utils.Validations.isNumber;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {

  private List<String> categories = Collections.singletonList("INSERT");

  private List<String> subCategories;

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public List<String> getSubCategories() {
    return subCategories;
  }

  public void setSubCategories(List<String> subCategories) {
    this.subCategories = subCategories;
  }


  public static void generateMenu() {
    int count = 1;
    Scanner scanner = new Scanner(System.in);
    System.out.println("- GENERATE ROLLBACK FILES SQL *LUCAS SOUZA* - ");
    System.out.println("-----------------------");
    System.out.println("Informe a opção desejada. Somente digitos... : ");
    List<String> categories = Collections.singletonList("INSERT");
    for (String category : categories) {
      System.out.println(count + ". " + category);
      count++;
    }
    System.out.println("-----------------------");
    String option = scanner.next();
    while (!isNumber(option) || Integer.parseInt(option) > categories.size()) {
      System.out.println("Opção inválida");
      option = scanner.next();
    }
    switch (option) {
      case "1":
        System.out.println("INSERT");
      case "2":
        System.out.println("DELETE");
    }

  }


}

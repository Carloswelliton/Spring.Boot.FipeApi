package spring.boot.fipe.principal;

import spring.boot.fipe.models.Vehicle;
import spring.boot.fipe.service.ApiConsume;
import spring.boot.fipe.service.DataConverter;

import java.util.*;

public class Principal {
  private Scanner readLine = new Scanner(System.in);
  private ApiConsume consume = new ApiConsume();
  private DataConverter conversor = new DataConverter();

  private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

  public void showMenu(){
    var menu = """
        ***Digite uma opção abaixo:***
        Motos
        Carros
        Caminhões
        """;
    System.out.println(menu);

    var option = readLine.nextLine();
    String address;

    Map<String, String> typeMap = new HashMap<>();
    typeMap.put("mot", "motos/marcas");
    typeMap.put("car", "carros/marcas");
    typeMap.put("cam", "caminhoes/marcas");

    String type = option.toLowerCase();
    String endpoint = typeMap.entrySet()
        .stream()
        .filter(entry -> type.contains(entry.getKey()))
        .map(Map.Entry::getValue)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Tipo de veiculo não identificado: "+option));

    address = URL_BASE+endpoint;
    System.out.println(address);
    var json = consume.ApiCall(address);
    System.out.println(json);

    var marcas = conversor.getList(json, Vehicle.class);
    marcas.stream()
        .sorted(Comparator.comparing(Vehicle::codigo))
        .forEach(System.out::println);
  }
}

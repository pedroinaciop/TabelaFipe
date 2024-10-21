package br.com.pedro.TabelaFipe.main;

import br.com.pedro.TabelaFipe.model.Dados;
import br.com.pedro.TabelaFipe.model.DadosPrecoVeiculo;
import br.com.pedro.TabelaFipe.services.ConsumoAPI;
import br.com.pedro.TabelaFipe.services.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Requisicoes {
    ConsumoAPI consumoAPI = new ConsumoAPI();
    Scanner leitura = new Scanner(System.in);
    private final ObjectMapper mapper = new ObjectMapper();
    private final String URL_BASE_API = "https://parallelum.com.br/fipe/api/v1/";
    ConverteDados conversor = new ConverteDados();

    public void buscarVeiculo(String veiculo) throws JsonProcessingException {

        var marcas = consumoAPI.obterDados(URL_BASE_API + veiculo + "/marcas");
        List<Dados> dadosVeiculos = conversor.obterLista(marcas, Dados.class);

        dadosVeiculos.stream()
                .sorted(Comparator.comparing(v -> Integer.parseInt(v.codigo())))
                .forEach(System.out::println);

        System.out.println("\nInforme o código da marca para consulta: ");
        String codigoMarca = leitura.nextLine();

        var jsonModelos = consumoAPI.obterDados(URL_BASE_API + veiculo + "/marcas/" + codigoMarca + "/modelos");
        JsonNode rootNode = mapper.readTree(jsonModelos);

        List<Dados> modelos = mapper.convertValue(rootNode.get("modelos"), new TypeReference<List<Dados>>() {});

        modelos.stream()
                .sorted(Comparator.comparing(v -> Integer.parseInt(v.codigo())))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do veículo para consulta: ");
        String nome = leitura.nextLine();

        modelos.stream()
                .sorted(Comparator.comparing(v -> Integer.parseInt(v.codigo())))
                .filter(e -> e.nome().toLowerCase().contains(nome.toLowerCase()))
                .forEach(System.out::println);

        System.out.println("\nDigite o código do modelo para consultar os valores: ");
        String modelo = leitura.nextLine();

        var jsonAnosVeiculos = consumoAPI.obterDados(URL_BASE_API + veiculo + "/marcas/" + codigoMarca + "/modelos/" + modelo + "/anos");
        List<Dados> dadosAnosVeiculos = conversor.obterLista(jsonAnosVeiculos, Dados.class);

        System.out.println("\nTodos os veículos com os valores por ano: ");
        dadosAnosVeiculos.stream()
                .map(v -> {
                    var json = consumoAPI.obterDados(URL_BASE_API + veiculo + "/marcas/" + codigoMarca + "/modelos/" + modelo + "/anos/" + v.codigo());
                    return conversor.obterDados(json, DadosPrecoVeiculo.class);
                }).forEach(System.out::println);
    }
}
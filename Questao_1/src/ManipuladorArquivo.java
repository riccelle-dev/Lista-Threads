
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ManipuladorArquivo {

    public List<String> leitor() {
        List<String> linhas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("texto.txt"))) {
            String linha = br.readLine();
            while (linha != null) {
                linhas.add(linha);
                linha = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return linhas;
    }

    public void exibirResumoFinal(ThreadGeral[] arrayDeThreads, int N) {
        Map<String, Integer> mapaGlobal = new HashMap<>();
        int palavras = 0;

        for (int i = 0; i < arrayDeThreads.length; i++) {
            palavras += arrayDeThreads[i].getContador();
            Map<String, Integer> mapaLocal = arrayDeThreads[i].getMapa();

            for (Map.Entry<String, Integer> entrada : mapaLocal.entrySet()) {
                String palavra = entrada.getKey();
                int quantidade = entrada.getValue();
                mapaGlobal.put(palavra, mapaGlobal.getOrDefault(palavra, 0) + quantidade);
            }
        }

        List<Map.Entry<String, Integer>> listaModa = new ArrayList<>(mapaGlobal.entrySet());
        listaModa.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        System.out.println("A quantidade total de palavras no arquivo é: " + palavras);
        for (int i = 0; i < Math.min(N, listaModa.size()); i++) {
            System.out.println(listaModa.get(i).getKey() + " : frequencia = " + listaModa.get(i).getValue());
        }
    }
}

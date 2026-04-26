
/*
Você deve desenvolver um programa que realiza a análise de dados em
paralelo. A ideia é processar um grande conjunto de dados de entrada e realizar
várias operações sobre esses dados de forma simultânea, aproveitando o poder
do processamento paralelo.
Você deve implementar um programa que conta o número de palavras no
arquivo texto. Ao final, o programa deve imprimir as N palavras que mais
aparecem no texto e a sua quantidade. Além disso, deve-se imprimir no fim da
execução a duração do programa (em segundos).
Você deve ter uma variável/constante N: que representa a quantidade de
palavras que serão impressas ao final do programa.
Você deve ter uma variável/constante T: que representa a quantidade de
threads que serão iniciadas no programa.
 */

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final int N = 10;
    public static final int T = 5;

    public static void main(String[] args) {
        long tempoInicial = System.currentTimeMillis();

        ManipuladorArquivo controle = new ManipuladorArquivo();
        List<String> linhas = controle.leitor();

        List<List<String>> pedacos = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            pedacos.add(new ArrayList<>());
        }

        for (int i = 0; i < linhas.size(); i++) {
            int indiceThread = i % T;
            pedacos.get(indiceThread).add(linhas.get(i));
        }

        ThreadGeral[] arrayT = new ThreadGeral[T];
        for (int i = 0; i < T; i++) {
            arrayT[i] = new ThreadGeral(pedacos.get(i));
            arrayT[i].start();
        }

        try{
            for(int i = 0; i<T; i++){
            arrayT[i].join();}
        }catch (Exception e){
            System.out.println("erro ao esperar threads terminarem");
        }

        controle.exibirResumoFinal(arrayT, N);

        long tempoFinal = System.currentTimeMillis();
        double duracao = (tempoFinal - tempoInicial) / 1000.0;
        System.out.println("Duração do programa: " + duracao + " segundos");
    }
}
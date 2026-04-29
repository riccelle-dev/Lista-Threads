package br.upe;

public class Jantar {
    public static final int N = 5;
    public static final int PENSANDO = 0;
    public static final int FOME = 1;
    public static final int COMENDO = 2;

    public int esquerda(int i){
        return (i + N - 1)%N;
    }

    public int direita(int i){
        return (i+1)%N;
    }

    public int[] estado = new int[N];

    public Jantar(){
        for(int i = 0; i<N; i++){
            estado[i] = PENSANDO;
        }
    }

    void pegaGarfos(int i) {
        synchronized (this){
            estado[i] = FOME;
            testa(i);

        while(estado[i]!=COMENDO){
            try {
                wait();
        } catch (Exception e) {
            System.out.println("n foi possivel pegar garfos" + e.getMessage());}}
        }
    }

    void poeGarfos(int i) {
        synchronized (this){
            estado[i] = PENSANDO;
            testa(esquerda(i));
            testa(direita(i));
        }
    }

    void testa(int i) {
        if (estado[i] == FOME && estado[esquerda(i)] != COMENDO && estado[direita(i)] != COMENDO) {
            estado[i] = COMENDO;
            notifyAll();
        }
    }

    public static void main(String[] args) {
        Jantar jantar = new Jantar();
        for (int i = 0; i < N; i++) {
            new ThreadFilosofos(i, jantar).start();
        }
    }
}

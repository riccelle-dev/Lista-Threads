package br.upe;

public class ThreadFilosofos extends Thread{
    public int i;
    public Jantar jantar;

    public ThreadFilosofos(int i, Jantar jantar){
        this.i = i;
        this.jantar = jantar;
    }

    public void run(){
        while(true) {
            pensa();
            jantar.pegaGarfos(i);
            come();
            jantar.poeGarfos(i);
        }
    }

    public void pensa(){
        System.out.println("Filosofo " + i + " pensando");
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println("deu erro no metodo pensar" + e.getMessage());
        }
    }

    public void come() {
        System.out.println("Filosofo " + i + " comendo");
        try { Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("deu erro no metodo pensar" + e.getMessage());
        }
    }



}




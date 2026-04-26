

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThreadGeral extends Thread{
    public Map<String, Integer> mapa = new HashMap<>();
    private List<String> linhas;
    int contador = 0;

    public ThreadGeral(List<String> linhas){
        this.linhas = linhas;
    }

    public void run(){
        Pattern p = Pattern.compile("(\\d+)|([a-záéíóúçãõêô]+)");

        for (String linha : linhas) {
            String minusculo = linha.toLowerCase();
            Matcher m = p.matcher(minusculo);

            while (m.find()) {
                String token = m.group();
                contador ++;

                mapa.put(token, mapa.getOrDefault(token, 0) + 1);
            }
        }
    }

    public int getContador() {
        return contador;
    }

    public Map<String, Integer> getMapa() {
        return mapa;
    }
}


public class Thread extends java.lang.Thread {

    private final int      id;
    private final int      totalThreads;
    private final long     senhaLong;
    private final String[] resultado;

    public Thread(int id, int totalThreads, String senha, String[] resultado) {
        this.id           = id;
        this.totalThreads = totalThreads;
        this.senhaLong    = Long.parseLong(senha);
        this.resultado    = resultado;
    }

    public void run() {
        for (long i = id; i <= 9999999999L; i += totalThreads) {

            if (resultado[0] != null) return;

            if (i == senhaLong) {
                String convertida = String.format("%010d", i);
                synchronized (resultado) {
                    if (resultado[0] == null) {
                        resultado[0] = convertida;
                        resultado[1] = String.valueOf(id);
                        resultado.notifyAll();
                    }
                }
                return;
            }
        }
    }
}
package Thread;

public class Estudo1 extends Thread {
    private final int numeroThread;

    Estudo1(int numeroThread) {
        this.numeroThread = numeroThread;

    }

    public void run() {
        System.out.println("Thread: " + numeroThread);
        Estudo1 p = new Estudo1(this.numeroThread);
        p.start();
    }

    public static void main(String[] args) {
        new Estudo1(1).start();
        new Estudo1(2).start();
    }
}



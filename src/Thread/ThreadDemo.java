package Thread;

public class ThreadDemo extends Thread {

    private String threadNome;

    public ThreadDemo(String nome){
        threadNome = nome;
    }


    public void run() {
        System.out.println("Sou a thread");
        for (int i = 4; i > 0; i--) {
            System.out.println("T: " + threadNome + " " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



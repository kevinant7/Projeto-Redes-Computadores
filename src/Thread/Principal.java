package Thread;

public class Principal {
    public static void main(String[] args) {
        String nomeTd1 = "1";
        String nomeTd2 = "2";
        ThreadDemo td1 = new ThreadDemo(nomeTd1);
        ThreadDemo td2 = new ThreadDemo(nomeTd2);
        td1.start();
        td2.start();
    }
}

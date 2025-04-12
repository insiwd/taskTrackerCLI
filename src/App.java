import commands.Comandos;

public class App {
    public static void main(String[] args) throws Exception {
        // Task task1 = new Task("comprar pão");
        // System.out.println(task1.getId() + " " + task1.getDescription());

        // Task task2 = new Task("comprar queijo");
        // System.out.println(task2.getId());

        Comandos comando = new Comandos();
        comando.add("pão");
        System.out.println(comando);

    }

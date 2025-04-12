
import task.Task;


public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("HELLO");

        Task task1 = new Task("comprar pão");
        System.out.println(task1.getId() + " " + task1.getDescription());

        Task task2 = new Task("comprar queijo");
        System.out.println(task2.getId());

    }
}

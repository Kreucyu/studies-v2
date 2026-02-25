import service.JogoService;
import repository.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("CRUD simples:\n");
        DatabaseManager manager = new DatabaseManager();
        manager.inicializarBanco();
        JogoService service = new JogoService();
        service.function();
    }
}
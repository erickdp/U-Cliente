package test;

import datos.ClienteDaoJDBC;
import dominio.Cliente;
import java.util.List;

public class NewMain {

    public static void main(String[] args) {
        List<Cliente> clientes = new ClienteDaoJDBC().read();
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }
    
    
    
}

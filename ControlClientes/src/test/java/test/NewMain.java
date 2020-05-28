package test;

import datos.ClienteDaoJDBC;
import dominio.Cliente;

public class NewMain {

    public static void main(String[] args) {
        Cliente cliente = new ClienteDaoJDBC().readByIdentifier(new Cliente(1));
        System.out.println(cliente);
    }
    
}

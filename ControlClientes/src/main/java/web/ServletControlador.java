package web;

import datos.CRUD;
import datos.ClienteDaoJDBC;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    private final static CRUD<Cliente> TRANSACCION;

    /*
    El bloque de inicializacion estatico sirve para inicializar miembros de clase una sola vez y soluciona el problema de
    no poder hacerlo en los constructores, se ejecuta una sola vez y mas antes que cualquier constructor.
     */
    static {
        TRANSACCION = new ClienteDaoJDBC();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarCliente(request, response);
                    break;

                case "eliminar":
                    this.eliminarCliente(request, response);
                    break;

                default:
                    accionDefault(request, response);
            }
        } else {
            accionDefault(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarCliente(request, response);
                    break;

                case "modificar":
                    this.modificarCliente(request, response);
                    break;

                default:
                    accionDefault(request, response);
            }
        } else {
            accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = TRANSACCION.read();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("totalClientes", clientes.size());
        sesion.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
        response.sendRedirect("clientes.jsp"); //Notifica al navegador del cambio de servlet, se pierden los datos por que solo esta en alcance de request
//        request.setAttribute("clientes", clientes);
//        request.setAttribute("totalClientes", clientes.size());
//        request.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
        //request.getRequestDispatcher("clientes.jsp").forward(request, response); Genera que el URL no cambie y se repite informacion
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Se  recupera los valores de formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;
        String saldoString = request.getParameter("saldo");

        if (saldoString != null && !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
        }

//        Creamos el objeto de cliente (modelo)
        Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);

//        Se lo inserta en la BBDD
        int registrosModificados = TRANSACCION.create(cliente);
        System.out.println("registrosModificados = " + registrosModificados);

//        Redirigimos hacia aacion por default
        accionDefault(request, response);
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = TRANSACCION.readByIdentifier(new Cliente(idCliente));
        request.setAttribute("cliente", cliente);
//        Como está en una carpeta oculta se tiene que usar ruta absoluta
        String editarJSP = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        request.getRequestDispatcher(editarJSP).forward(request, response);
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombreActualizado = request.getParameter("nombre");
        String apellidoActualizado = request.getParameter("apellido");
        String emailActualizado = request.getParameter("email");
        String telefonoActualizado = request.getParameter("telefono");
        double saldoActualizado = 0;
        String saldoParametro = request.getParameter("saldo");
        if (saldoParametro != null && !"".equals(saldoParametro)) {
            saldoActualizado = Double.parseDouble(saldoParametro);
        }

        Cliente clienteActualizado = new Cliente(idCliente, nombreActualizado,
                apellidoActualizado, emailActualizado, telefonoActualizado, saldoActualizado);

        System.out.println("Registros alterados: " + TRANSACCION.update(clienteActualizado));
        accionDefault(request, response);
    }

    private double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;
        for (Cliente c : clientes) {
            saldoTotal += c.getSaldo();
        }
        return saldoTotal;
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        System.out.println("Registros alterados: " + TRANSACCION.delete(new Cliente(idCliente)));
        accionDefault(request, response);
    }
}

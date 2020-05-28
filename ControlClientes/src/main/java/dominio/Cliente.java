package dominio;

public class Cliente {

    private int id_cliente;
    private String nombre;
    private String apellido;
    private String mail;
    private String tlf;
    private double saldo;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String mail, String tlf, double saldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.tlf = tlf;
        this.saldo = saldo;
    }

    public Cliente(int id_cliente, String nombre, String apellido, String mail, String tlf, double saldo) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.tlf = tlf;
        this.saldo = saldo;
    }

    public Cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id_cliente=" + id_cliente + ", nombre=" + nombre + ", apellido=" + apellido
                + ", mail=" + mail + ", tlf=" + tlf + ", saldo=" + saldo + '}';
    }

}

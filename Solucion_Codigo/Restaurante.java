package restaurante;
import java.util.ArrayList;
import java.util.List;
public class Restaurante {
    public static void main(String[] args) {
        Menu menuCarta = new MenuCarta("Arroz con Pollo", 7.00, 3.00, 1.00, 10);
        Menu menuDia = new MenuDia("Pollo con Ensalada", 6.00, 3.00, 1.50);
        Menu menuNinos = new MenuNinos("Papas con Pollo", 4.00, 3.00, 2.00);
        Menu menuEconomico = new MenuEconomico("Encebollado", 3.00, 20);
        CuentaPorPagar cuenta = new CuentaPorPagar("Josue Toledo");
        cuenta.agregarMenu(menuCarta);
        cuenta.agregarMenu(menuDia);
        cuenta.agregarMenu(menuNinos);
        cuenta.agregarMenu(menuEconomico);
        cuenta.calcularValores();
        System.out.println(cuenta);
    }
    
}

abstract class Menu {
    protected String nombrePlato;
    protected double valorInicial;
    protected double valorMenu;

    public Menu(String nombrePlato, double valorInicial) {
        this.nombrePlato = nombrePlato;
        this.valorInicial = valorInicial;
    }
    public abstract void calcularValorMenu();
    @Override
    public String toString() {
        return String.format("Plato: %s - Valor del menú: %.2f", nombrePlato, valorMenu);
    }

    public double getValorMenu() {
        return valorMenu;
    }
}

class MenuCarta extends Menu {
    private double valorGuarnicion;
    private double valorBebida;
    private double porcentajeAdicional;

    public MenuCarta(String nombrePlato, double valorInicial, double valorGuarnicion, double valorBebida, double porcentajeAdicional) {
        super(nombrePlato, valorInicial);
        this.valorGuarnicion = valorGuarnicion;
        this.valorBebida = valorBebida;
        this.porcentajeAdicional = porcentajeAdicional;
    }

    @Override
    public void calcularValorMenu() {
        valorMenu = valorInicial + valorGuarnicion + valorBebida + (valorInicial * porcentajeAdicional / 100);
    }
}

class MenuDia extends Menu {
    private double valorPostre;
    private double valorBebida;

    public MenuDia(String nombrePlato, double valorInicial, double valorPostre, double valorBebida) {
        super(nombrePlato, valorInicial);
        this.valorPostre = valorPostre;
        this.valorBebida = valorBebida;
    }

    @Override
    public void calcularValorMenu() {
        valorMenu = valorInicial + valorPostre + valorBebida;
    }
}

class MenuNinos extends Menu {
    private double valorHelado;
    private double valorPastel;

    public MenuNinos(String nombrePlato, double valorInicial, double valorHelado, double valorPastel) {
        super(nombrePlato, valorInicial);
        this.valorHelado = valorHelado;
        this.valorPastel = valorPastel;
    }

    @Override
    public void calcularValorMenu() {
        valorMenu = valorInicial + valorHelado + valorPastel;
    }
}
class MenuEconomico extends Menu {
    private double porcentajeDescuento;

    public MenuEconomico(String nombrePlato, double valorInicial, double porcentajeDescuento) {
        super(nombrePlato, valorInicial);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public void calcularValorMenu() {
        valorMenu = valorInicial - (valorInicial * porcentajeDescuento / 100);
    }
}

class CuentaPorPagar {
    private String nombreCliente;
    private List<Menu> listadoMenus;
    private double subtotal;
    private double iva;
    private double total;

    public CuentaPorPagar(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        this.listadoMenus = new ArrayList<>();
    }

    public void agregarMenu(Menu menu) {
        menu.calcularValorMenu();
        listadoMenus.add(menu);
    }

    public void calcularValores() {
        subtotal = 0;
        for (Menu menu : listadoMenus) {
            subtotal += menu.getValorMenu();
        }
        iva = subtotal * 0.12;
        total = subtotal + iva;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Cliente: %s\n", nombreCliente));
        sb.append("Listado de Menús:\n");
        for (Menu menu : listadoMenus) {
            sb.append(menu.toString()).append("\n");
        }
        sb.append(String.format("Subtotal: %.2f\n", subtotal));
        sb.append(String.format("IVA: %.2f\n", iva));
        sb.append(String.format("Total a cancelar: %.2f\n", total));
        return sb.toString();
    }
}
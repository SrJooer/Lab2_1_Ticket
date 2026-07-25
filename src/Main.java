import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;

public class Main {

    private static final int ANCHO = 800;
    private static final int ALTO = 800;

    private JFrame ventana;
    private JPanel root;

    private JPanel avion;

    private JPanel herramientas;
    private JPanel consola;
    private JPanel botones;

    private JTextField campoNombre;
    private JTextArea terminal;

    private PalindromoAir vuelo = new PalindromoAir();
    private JButton[] asientos = new JButton[30];

    public static void main(String[] args) {
        new Main().iniciar();
    }

    private void iniciar() {
        crearVentana();
        organizarPaneles();

        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    private void crearVentana() {
        ventana = new JFrame("PalindromoAir");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);

        root = new JPanel();
        root.setLayout(new BorderLayout());
        root.setPreferredSize(new Dimension(ANCHO, ALTO));
        ventana.setContentPane(root);
    }

    private void organizarPaneles() {

        avion = new JPanel();
        avion.setBackground(new Color(191, 188, 226));

        herramientas = new JPanel();
        herramientas.setPreferredSize(new Dimension(0, (int) (ALTO * 0.40)));
        herramientas.setLayout(new BorderLayout());

        root.add(avion, BorderLayout.CENTER);
        root.add(herramientas, BorderLayout.SOUTH);

        organizarAvion();
        organizarHerramientas();
    }

    private void organizarAvion() {
        avion.setLayout(new GridLayout(5, 6, 6, 6));
        avion.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int i = 0; i < asientos.length; i++) {
            asientos[i] = new JButton(String.valueOf(i + 1));
            asientos[i].setForeground(Color.BLACK);
            avion.add(asientos[i]);
        }

        actualizarAsientos();
    }

    public void actualizarAsientos() {
        for (int i = 0; i < asientos.length; i++) {
            Ticket t = vuelo.getSeat(i);

            if (t == null) {
                asientos[i].setBackground(Color.GREEN);
                asientos[i].setText(String.valueOf(i + 1));
            } else if (t.isPalindromo()) {
                asientos[i].setBackground(Color.ORANGE);
                asientos[i].setText(t.getNombre());
            } else {
                asientos[i].setBackground(Color.RED);
                asientos[i].setText(t.getNombre());
            }
        }
    }


    private void organizarHerramientas() {

        botones = new JPanel();
        botones.setBackground(new Color(162, 147, 196));
        botones.setPreferredSize(new Dimension((int) (ANCHO * 0.30), 0));

        consola = new JPanel();
        consola.setBackground(new Color(225, 223, 242));

        herramientas.add(botones, BorderLayout.WEST);
        herramientas.add(consola, BorderLayout.CENTER);
        organizarBotones();
        organizarConsola();
    }

    private void organizarConsola() {
        consola.setLayout(new BorderLayout());
        consola.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        terminal = new JTextArea();
        terminal.setEditable(false);
        terminal.setLineWrap(true);
        terminal.setWrapStyleWord(true);
        terminal.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        consola.add(new JScrollPane(terminal), BorderLayout.CENTER);
    }

    public void mostrarTexto(String t) {
        terminal.setText(t);
        terminal.setCaretPosition(0);
    }

    private void organizarBotones() {
        botones.setLayout(new BorderLayout());
        botones.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));

        JPanel contenido = new JPanel();
        contenido.setOpaque(false);
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        contenido.add(crearTitulo("PASAJERO"));
        contenido.add(Box.createVerticalStrut(5));

        campoNombre = crearCampoTexto();
        contenido.add(campoNombre);
        
        contenido.add(Box.createVerticalStrut(7));

        contenido.add(crearGrupo(
                crearBoton("Sell Ticket", () -> venderTicket() ),
                crearBoton("Cancel Ticket", () -> cancelarTicket() ),
                crearBoton("Search Passenger", () -> buscarPasajero() )));

        contenido.add(Box.createVerticalStrut(22));
        
        contenido.add(crearTitulo("VUELO"));
        contenido.add(Box.createVerticalStrut(5));

        contenido.add(crearGrupo(
                crearBoton("Print Passengers", () -> imprimirPasajeros() ),
                crearBoton("View Income", () -> verIngresos() ),
                crearBoton("Dispatch", () -> despacharVuelo() )));

        botones.add(contenido, BorderLayout.NORTH);
    }

    private String leerNombre() {
        String nombre = campoNombre.getText().trim();

        if (nombre.isEmpty()) {
            mostrarTexto("Escribe el nombre del pasajero en el campo de texto");
            return null;
        }
        return nombre;
    }

    private void venderTicket() {
        String nombre = leerNombre();
        if (nombre == null) {
            return;
        }

        mostrarTexto(vuelo.sellTicket(nombre));
        actualizarAsientos();
        campoNombre.setText("");
    }

    private void cancelarTicket() {
        String nombre = leerNombre();
        if (nombre == null) {
            return;
        }

        if (vuelo.cancelTicket(nombre)) {
            mostrarTexto("Ticket cancelado, el asiento de " + nombre + " quedo disponible");
        } else {
            mostrarTexto("Pasajero no encontrado: " + nombre);
        }
        actualizarAsientos();
        campoNombre.setText("");
    }

    private void buscarPasajero() {
        String nombre = leerNombre();
        if (nombre == null) {
            return;
        }

        int asiento = vuelo.searchPassenger(nombre, 0);

        if (asiento == -1) {
            mostrarTexto("Pasajero no encontrado: " + nombre);
        } else {
            mostrarTexto("Pasajero encontrado en el asiento " + (asiento + 1) + "\n\n"
                    + vuelo.getSeat(asiento).print());
        }
    }

    private void imprimirPasajeros() {
        String texto = vuelo.printPassengers(0);

        if (texto.isEmpty()) {
            mostrarTexto("Todavia no hay pasajeros registrados");
        } else {
            mostrarTexto("PASAJEROS DEL VUELO\n\n" + texto);
        }
    }

    private void verIngresos() {
        mostrarTexto("Ingresos totales: " + vuelo.income(0));
    }

    private void despacharVuelo() {
        mostrarTexto(vuelo.dispatch());
        actualizarAsientos();
    }
    
    private JTextField crearCampoTexto() {
        JTextField c = new JTextField();
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
        c.setPreferredSize(new Dimension(0, 26));
        c.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));
        return c;
    }

    private JPanel crearGrupo(JButton... bs) {
        JPanel grupo = new JPanel(new GridLayout(0, 1, 0, 5));
        grupo.setAlignmentX(Component.LEFT_ALIGNMENT);
        for (JButton b : bs) { grupo.add(b); }
        grupo.setMaximumSize(new Dimension(Integer.MAX_VALUE, grupo.getPreferredSize().height));
        return grupo;
    }

    private JLabel crearTitulo(String t) {
        JLabel l = new JLabel(t);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private JButton crearBoton(String t, Runnable r) {
        JButton b = new JButton(t);
        b.addActionListener(e -> r.run());
        return b;
    }

}

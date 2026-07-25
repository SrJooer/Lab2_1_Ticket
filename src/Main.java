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

    public void main(String[] args) {
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

        organizarHerramientas();
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
                crearBoton("Sell Ticket", () -> {} ),
                crearBoton("Cancel Ticket", () -> {} ),
                crearBoton("Search Passenger", () -> {} )));

        contenido.add(Box.createVerticalStrut(22));
        
        contenido.add(crearTitulo("VUELO"));
        contenido.add(Box.createVerticalStrut(5));

        contenido.add(crearGrupo(
                crearBoton("Print Passengers", () -> { } ),
                crearBoton("View Income", () -> { } ),
                crearBoton("Dispatch", () -> {} )));

        botones.add(contenido, BorderLayout.NORTH);
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

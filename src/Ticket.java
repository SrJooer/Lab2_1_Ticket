public class Ticket {
    
   private String Nombre;
   private double Montof;
   private double monto;
   private boolean Palindromo;

    public Ticket(String Nombre, double Montof, double monto, boolean Palindromo) {
        this.Nombre = Nombre;
        this.Montof = Montof;
        this.monto = monto;
        this.Palindromo = Palindromo;
    }

    public String getNombre() {
        return Nombre;
    }

    public double getMontof() {
        return Montof;
    }

    public double getMonto() {
        return monto;
    }

    public boolean isPalindromo() {
        return Palindromo;
    }
    
    public void print() {
        System.out.println("DATOS DEL TICKET:");
        System.out.println("Pasajero: " + Nombre);
        System.out.println("Monto Original: $" + monto);
        System.out.println("Monto Pagado: $" + Montof);
      if (Palindromo) {
        System.out.println("Si aplica descuento!");
    } else {
        System.out.println("No aplica descuento");
    }    }
        
}

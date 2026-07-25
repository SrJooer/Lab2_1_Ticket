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
    
    public String print() {
        String texto = "Pasajero: " + Nombre + "\n";
        texto += "Monto Original: " + monto + "\n";
        texto += "Monto Pagado: " + Montof + "\n";
      if (Palindromo) {
        texto += "Si aplica descuento!";
    } else {
        texto += "No aplica descuento";
    }
        return texto;
    }
        
}

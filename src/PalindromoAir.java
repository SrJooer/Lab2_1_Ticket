public class PalindromoAir {

    private Ticket[] seats = new Ticket[30];

    public int firstAvailable(int index) {
        
        if (index>=seats.length) {
            return -1;
        }
        if (seats[index]==null) {
            return index;
        }
        return firstAvailable(index+1);
    }

    public int searchPassenger(String name, int index) {
    if (index >= seats.length) {
        return -1;
    }
    
    if (seats[index] != null) {
        String nombrePasajero = seats[index].getNombre();
        
        if (nombrePasajero.equalsIgnoreCase(name)) {
            return index; 
        }
    }
    
    int siguientePosicion = searchPassenger(name, index + 1);
    return siguientePosicion;

    }   

    public boolean isPalindromo(String name,int inicio, int fin) {
        if (inicio>=fin) {
            return true;
        }
        if (name.charAt(inicio)!=name.charAt(fin)) {
            return false;
        }
        return isPalindromo(name, inicio+1, fin-1);
    }

    public void printPassengers(int index) {
    }

    public double income(int index) {
        return 0.0;
    }

    public void reset(int index) {
    }

    public void sellTicket(String name) {
        double monto = 1000;
        double descuento;
        double montof;
        boolean palindromo = isPalindromo(name,0,name.length()-1);
        int asiento=firstAvailable(0);
        if (asiento==-1) {
            System.out.println("No hay mas asientos");
            return;
        }
        if (palindromo) {
            descuento = monto*0.20;
        } else {
            descuento =0;
        }
        montof=monto-descuento;
        Ticket pasajero = new Ticket(name,montof,monto,palindromo);
        seats[asiento] = pasajero;
        System.out.println("Ticet vendido");
    }
    
    public boolean cancelTicket(String name) {
        return false;
    }
    
    public void dispatch() {
    }
}
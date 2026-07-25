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

    public boolean isPalindromo(String name) {
        return false;
    }

    public void printPassengers(int index) {
    }

    public double income(int index) {
        return 0.0;
    }

    public void reset(int index) {
    }

    public void sellTicket(String name) {
        int seat = firstAvailable(0);
        
    }
    
    public boolean cancelTicket(String name) {
        return false;
    }
    
    public void dispatch() {
    }
}
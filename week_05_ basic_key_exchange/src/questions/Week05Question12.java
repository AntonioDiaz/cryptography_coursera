package questions;


/** Calculate square root mod prime. */
public class Week05Question12 {
    
	public static void main (String[] args) {
	    boolean encontrado = false;
	    int myPrime = 23;
	    int myX = 12;
        for (int i=0; i<100 && !encontrado; i++) {
            if ((i*i)%myPrime==myX) {
                encontrado = true;
                System.out.println(i);
            }
        }
        System.out.println("encontrado" );
	}
}
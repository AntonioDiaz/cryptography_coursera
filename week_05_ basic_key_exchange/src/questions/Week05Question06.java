package questions;

public class Week05Question06 {
	public static void main (String[] args) {
	    boolean encontrado = false;
        for (int x=0; x<100 && !encontrado; x++) {
            int b = x*3+2;
            if (b%19==7) {
                encontrado = true;
                System.out.println(encontrado + " x=" + x);
            }
        }
	}
}
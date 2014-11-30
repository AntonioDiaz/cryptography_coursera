package questions;

public class Week05Question05 {
	public static void main (String[] args) {
	    boolean encontrado = false;
        for (int a=0; a<100 && !encontrado; a++) {
            int b = 1 - 7*a;
            System.out.println("a-->" + a + " b-->" + b);
            if (b%23==0) {
                encontrado = true;
                System.out.println(encontrado);
            }
        }
	}
}
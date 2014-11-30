package attempts;

public class Callenge01Simple {

	public static final double N = 323;
	
	public static void main(String[] args) {
		double a = Math.ceil(Math.sqrt(N));
		double diference = Math.pow(2 * N, 1/4);
		double p = -1;
		double q = -1;
		boolean encontrado = false;
		for (int x = 1; !encontrado && x<=diference; x++) {
			p = a - x;
			q = a + x;
			encontrado = p*q==N;
		}
		System.out.println("a " + a);
		System.out.println("diference " + diference);
		System.out.println("p " + p);
		System.out.println("p " + q);
		
		
	}
	
	
	
}

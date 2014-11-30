package attempts;

/** javac -d bin -sourcepath src src/com/DiscreteLogModulo.java  */
/** java -cp bin/ com.DiscreteLogModulo */

import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

/** Calculate Discrete Log Modulo. */

/**
 * 
 BigInteger p = ""; BigInteger g ; BigInteger h;
 * 
 * 
 * 
 * h/(g^x)
 */
public class DiscreteLogModulo {

	//	public static final Integer B = (int)Math.pow(2, 5); 
	//	public static final String P_STR = "17"; 
	//	public static final String G_STR = "3"; 
	//	public static final String H_STR = "12";

//	 public static final Integer B = (int)Math.pow(2, 10);
//	 public static final String P_STR = "110213";
//	 public static final String G_STR = "101033";
//	 public static final String H_STR = "100551";

	public static final Integer B = (int) Math.pow(2, 20);
	public static final String P_STR = "13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084171";
	public static final String G_STR = "11717829880366207009516117596335367088558084999998952205599979459063929499736583746670572176471460312928594829675428279466566527115212748467589894601965568";
	public static final String H_STR = "3239475104050450443565264378728065788649097520952449527834792452971981976143292558073856937958553180532878928001494706097394108577585732452307673444020333";

	public static void main(String[] args) {
		Map<BigInteger, Integer> myMap = new HashMap<BigInteger, Integer>();
		BigInteger p = new BigInteger(P_STR);
		BigInteger g = new BigInteger(G_STR);
		BigInteger h = new BigInteger(H_STR);
		BigInteger x;
		BigInteger temp;
		BigInteger function = g.modInverse(p);
		BigInteger anterior = BigInteger.valueOf(1);
		anterior = anterior.modInverse(p);
		anterior = anterior.multiply(h).mod(p);
		myMap.put(anterior, 0);
		Date begin = new Date();
		for (int x1 = 1; x1 < B; x1++) {
			temp = anterior.multiply(function).mod(p);
			myMap.put(temp, x1);
			anterior = temp;
			if (x1 % 1000 == 0) {
				Date end = new Date();
				long diff = (end.getTime() - begin.getTime()) / 1000;
				System.out.print("primer bucle i=" + x1);
				System.out.println("\t secs: " + diff);
				//System.out.println("temp size: " + temp.toString().length());
				begin = new Date();
			}
		}
		long solution = -1;
		for (int x0 = 0; x0 < B && solution == -1; x0++) {
			// long exponent = B*i;
			// temp = g.modPow(BigInteger.valueOf(exponent), p);
			temp = g.modPow(BigInteger.valueOf(B), p);
			temp = temp.modPow(BigInteger.valueOf(x0), p);
			// System.out.println(i + "\tRHS " + temp);
			Integer x1 = myMap.get(temp);
			if (x1 != null) {
				System.out.println("middle " + temp);
				System.out.println("x0 " + x0);
				System.out.println("x1 " + x1);
				solution = x0 * B + x1;
			}
			if (x0 % 1000 == 0) {
				Date end = new Date();
				long diff = (end.getTime() - begin.getTime()) / 1000;
				System.out.print("segundo bucle i=" + x0);
				System.out.println("\t secs: " + diff);
				begin = new Date();
			}
		}
		System.out.println("finito: " + solution);
		/*
		 * for (int i=0; i<B; i++) { temp = g.pow(i); temp = temp.modInverse(p);
		 * temp = temp.multiply(h).mod(p); myMap.put(temp, i); if (i%1000==0) {
		 * Date end = new Date(); long diff = (end.getTime() - begin.getTime())
		 * / 1000; System.out.print ("primer bucle i=" + i); System.out.println
		 * ("\t secs: " + diff); System.out.println("temp size: " +
		 * temp.toString().length()); begin = new Date(); } } for (int i=0; i<B;
		 * i++) { temp = g.modPow(BigInteger.valueOf(B), p); temp =
		 * temp.modPow(BigInteger.valueOf(i), p); System.out.println(i +
		 * "\tRHS " + temp); }
		 */
	}

	public static void mainOls(String[] args) {
		BigInteger p = new BigInteger(P_STR);
		BigInteger g = new BigInteger(G_STR);
		BigInteger h = new BigInteger(H_STR);
		BigInteger x;

		Map<BigInteger, Integer> myMap = new HashMap<BigInteger, Integer>();
		Date begin = new Date();
		x = h.divide(g.pow(0));
		for (int i = 0; i < B; i++) {
			myMap.put(x.mod(p), i);
			x = h.divide(g);
			if (i % 10 == 0) {
				Date end = new Date();
				long diff = (end.getTime() - begin.getTime()) / 1000;
				System.out.print("primer bucle i=" + i);
				System.out.println("\t secs: " + diff);
				begin = new Date();
			}
		}
		boolean encontrado = false;
		long solution = -1;
		for (int i = 0; i < B && !encontrado; i++) {
			long exponent = B * i;
			x = g.modPow(BigInteger.valueOf(exponent), p);
			Integer x1 = myMap.get(x);
			if (x1 != null) {
				System.out.println("x0 " + i);
				System.out.println("x1 " + x1);
				encontrado = true;
				solution = i * B + x1;
			}
			if (i % 1000 == 0) {
				Date end = new Date();
				long diff = (end.getTime() - begin.getTime()) / 1000;
				System.out.print("segundo bucle i=" + i);
				System.out.println("\t secs: " + diff);
				begin = new Date();
			}
		}
		System.out.println("solution: " + solution);
	}
}
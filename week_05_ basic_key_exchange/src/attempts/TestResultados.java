package attempts;

import java.math.BigInteger;
import java.util.Date;

public class TestResultados {

//	public static final String P_STR = "13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084171";
//	public static final String G_STR = "11717829880366207009516117596335367088558084999998952205599979459063929499736583746670572176471460312928594829675428279466566527115212748467589894601965568";
//	public static final String H_STR = "3239475104050450443565264378728065788649097520952449527834792452971981976143292558073856937958553180532878928001494706097394108577585732452307673444020333";
//	public static final String RESULT = "1712063078";
//	public static final String X0_STR = "357984";
//	public static final String X1_STR = "787046";
	
	public static final String P_STR = "110213";
	public static final String G_STR = "101033";
	public static final String H_STR = "100551";
	public static final String X0_STR = "5";
	public static final String X1_STR = "859";
	public static final Integer B = (int)Math.pow(2, 10);
	
//	public static final Integer B = (int)Math.pow(2, 5); 
//	public static final String P_STR = "17"; 
//	public static final String G_STR = "3"; 
//	public static final String H_STR = "12";
//	public static final String X0_STR = "5";
//	public static final String X1_STR = "859";

	
//	public static final String MIDDLE = "1514665690755317733976062484822790443518716440821123165374346884821466258735252188334799940590055744229602814470651184433499215002319025125450377832374754";
	public static void main(String[] args) {

    	BigInteger p = new BigInteger(P_STR);
    	BigInteger g = new BigInteger(G_STR);
    	BigInteger h = new BigInteger(H_STR);
    	BigInteger x0 = new BigInteger(X0_STR);
    	BigInteger x1 = new BigInteger(X1_STR);
//    	BigInteger r = new BigInteger(RESULT);
//    	BigInteger middle = new BigInteger(MIDDLE);
    	
    	//System.out.println(g.modPow(r, p));
    	
		BigInteger LS = BigInteger.valueOf(1);
		BigInteger function = g.modInverse(p);
		BigInteger anterior = BigInteger.valueOf(1);
		anterior = anterior.modInverse(p);
		anterior = anterior.multiply(h).mod(p);
		
		
		for (int i = 1; i < x1.intValue(); i++) {
			anterior = anterior.multiply(function).mod(p);
		}
    		
    	System.out.println("LS -->" + anterior);
    	
    	BigInteger RS = g.modPow(BigInteger.valueOf(B), p).modPow(x0, p);
    	System.out.println("RS -->" + RS);
    	
    	
    	//ls = ls
	}
}

package questions;

import java.math.BigInteger;


/** (Zn)* --> Set of invertible elements in Zn. */
public class Week05Question08 {
    
	public static void main (String[] args) {
	    BigInteger bigInteger = new BigInteger("2");
	    BigInteger exponent = new BigInteger("10001");
	    BigInteger myPrime = new BigInteger("11");
        System.out.println("solution=" + bigInteger.modPow(exponent, myPrime));
	}
}
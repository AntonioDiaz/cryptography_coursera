package attempts;

import java.math.BigInteger;

public class TestPrimitiveRoot {

	public static void main(String[] args) {
		BigInteger g = new BigInteger("101033");
		BigInteger p = new BigInteger("110213");
		BigInteger h = new BigInteger("10051");
		BigInteger x = new BigInteger("5979");
		BigInteger x0 = new BigInteger("5");
		BigInteger x1 = new BigInteger("859");
		BigInteger B = new BigInteger("1020");
		BigInteger temp = g.modPow(x, p);
		System.out.println("[" + temp.toString() + "]");
		System.out.println("[" + temp.toString().length() + "]");
					
		temp = g.modPow(B.multiply(x1), p);
		System.out.println("(g^B)^x0  in Zp =" + temp);
		
		temp = g.pow(x1.intValue());
		temp = temp.modInverse(p);
		temp = temp.multiply(h);
		temp = temp.mod(p);
		System.out.println(temp);
		
		temp = new BigInteger("8");
		BigInteger m = new BigInteger("3");
		System.out.println(temp.modInverse(m));
		
		
		
		
	}	
}

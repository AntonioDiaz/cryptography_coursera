import java.math.BigInteger;

public class Week06Question04 {
	public static void main(String[] args) {
		System.out.println("challenge01 [" + challenge01() + "]");
		//System.out.println("challenge02 [" + challenge02() + "]");
		//System.out.println("challenge03 [" + challenge03() + "]");
		String ch04 = challenge04();
		System.out.println("challenge04 hexadecimal [" +  ch04 + "]");
		System.out.println("challenge04 ascii[" + strHexadecimalToAscii(ch04) + "]");
		
	}
	
	public static final String C_STR = 
			"2209645186741038177630656113488341801741006978789283107173183914367613560012053800" + 
			"4282329650473509424343946219751512256465839967942889460764542040581564748988013734" +
			"8641204523252293201764879166664029975091887299716905260832220677716000193292608700" +
			"09579993724077458967773697817571267229951148662959627934791540";
	
	public static final String challenge04() {
		BigInteger one = BigInteger.valueOf(1);
		
		BigInteger c = new BigInteger(C_STR);
		
		BigInteger n = new BigInteger(N_01);
		BigInteger p = Week06Question04.challenge01();
		BigInteger q = n.divide(p);
		
		BigInteger phi = p.subtract(one).multiply(q.subtract(one));
		
		BigInteger e = BigInteger.valueOf(65537);
		BigInteger d = e.modInverse(phi);
		
		System.out.println(d.multiply(e).mod(phi));
		
		System.out.println("bitLength -->" + c.modPow(d, n).bitLength());
		System.out.println("Hex -->" + c.modPow(d, n).toString(2));
		System.out.println("Hex -->" + c.modPow(d, n).toString(16));
		String m = c.modPow(d, n).toString(16);		
		return m;
	}
	
	

    // recursive implementation
    public static int gcd(int p, int q) {
        if (q == 0) return p;
        else return gcd(q, p % q);
    }

    // non-recursive implementation
    public static int gcd2(int p, int q) {
        while (q != 0) {
            int temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }
	
	public static String strHexadecimalToAscii(String input) {
		
		if (input.length()%2==1) {
			input = "0" + input;
		}
		String output = "";
		for(int i=0; i<input.length(); i=i+2) {
			String subStrEncription = input.substring(i, i+2);
			//System.out.println("[" + subStrEncription + "]");
			Character ch = new Character((char)Integer.parseInt(subStrEncription, 16));
			output += ch.toString();
		}		
		return output;
	}
	
	//public static final String N_01 = "323";
	public static final String N_01 = 
			"17976931348623159077293051907890247336179769789423065727343008115" +
			"77326758055056206869853794492129829595855013875371640157101398586" +
			"47833778606925583497541085196591615128057575940752635007475935288" + 
			"71082364994994077189561705436114947486504671101510156394068052754" +  
			"0071584560878577663743040086340742855278549092581";
	
	public static final BigInteger challenge01() {
		BigInteger n;
		BigInteger a;
		BigInteger x;
		BigInteger p;
		BigInteger q;
		n = new BigInteger(N_01);
		/* a = ceil(sqrt(n)) */
		a = sqrt(n);
		a = a.add(BigInteger.valueOf(1));
		/* x = sqrt(a^2 - N) */
		x = sqrt(a.multiply(a).subtract(n));
		/* p = a - x */
		p = a.subtract(x);
		/* q = a + x; */
		q = a.add(x);
		return p;
	}
	
	
	public static final String N_02 = 
			"72006226374735042527956443552558373833808445147399984182665305798191" +
			"63556901883377904234086641876639384851752649940178970835240791356868" +
			"77441155132015188279331812309091996246361896836573643119174094961348" + 
			"52463970788523879939683923036467667022162701835329944324119217381272" +  
			"9276147530748597302192751375739387929";
	
	private static final BigInteger challenge02() {
		BigInteger n;
		BigInteger a;
		BigInteger x;
		BigInteger m;
		BigInteger p = BigInteger.valueOf(-1);
		BigInteger q = BigInteger.valueOf(-1);
		boolean encontrado = false;
		n = new BigInteger(N_02);
		a = sqrt(n);
		int cont = 0;
		while (!encontrado) {
			x = sqrt(a.multiply(a).subtract(n));
			p = a.subtract(x);
			q = a.add(x);
			m = p.multiply(q);
			//System.out.println("diference n - m: "+ n.subtract(m));
			if (m.compareTo(n)==0) {
				encontrado = true;
			} 
			a = a.add(BigInteger.valueOf(1));
			if (cont++%1000==0) {
				System.out.println(cont + " " + a);
				System.out.println(p.multiply(q));
			}
		}
		return p;
	}
	private static BigInteger sqrt(BigInteger n) {
		BigInteger a = BigInteger.ONE;
		BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
		while (b.compareTo(a)>=0) {
			BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
			if (mid.multiply(mid).compareTo(n) > 0) {
				b = mid.subtract(BigInteger.ONE);
			} else {
				a = mid.add(BigInteger.ONE);
			}
		}
		return a.subtract(BigInteger.ONE);
	}	
	
}

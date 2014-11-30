package questions;

import java.math.BigInteger;
import java.util.Set;
import java.util.HashSet;

/** (Zn)* --> Set of invertible elements in Zn. */
public class Week05Question07 {
    
    public static final int N = 13;
    
	public static void main (String[] args) {
	    Set<Integer> zPAsterisk = new HashSet<Integer>();
        for (int i=0; i<N; i++) {
            if (BigInteger.valueOf(N).gcd(BigInteger.valueOf(i)).intValue()==1) {
                zPAsterisk.add(i);    
            }
        }
        System.out.println("zPAsterisk -->" + zPAsterisk);
        System.out.println("contInvertibles=" + zPAsterisk.size());
	}
}
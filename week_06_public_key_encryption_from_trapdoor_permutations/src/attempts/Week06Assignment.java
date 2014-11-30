package attempts;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Week06Assignment {

	public static final String N_STR = "17976931348623159077293051907890247336179769789423065727343008115"
			+ "77326758055056206869853794492129829595855013875371640157101398586"
			+ "47833778606925583497541085196591615128057575940752635007475935288"
			+ "71082364994994077189561705436114947486504671101510156394068052754" + "0071584560878577663743040086340742855278549092581";

	// public static final String N_STR = "323";

	public static void main(String[] args) {
		BigInteger n = new BigInteger(N_STR);
		System.out.println(sqrt(n).multiply(sqrt(n)));
		BigInteger a = sqrt(n).add(BigInteger.valueOf(1));
		System.out.println(a);
		// BigInteger a = sqrt(n);
		System.out.println(a.multiply(a));

		BigInteger diference = a.multiply(a).multiply(BigInteger.valueOf(2));
		boolean encontrado = false;
		BigInteger p = BigInteger.valueOf(-1);
		BigInteger q = BigInteger.valueOf(-1);

		for (int x = 0; !encontrado && x <= diference.intValue() + 10000; x++) {
			p = a.subtract(BigInteger.valueOf(x));
			q = a.add(BigInteger.valueOf(x));
			BigInteger m = p.multiply(q);
			if (n.compareTo(m) == 0) {
				encontrado = true;
				System.out.println(p);
				System.out.println(q);
				System.out.println("m: " + m);
			}
		}
		System.out.println(encontrado);
		if (encontrado) {
			BigInteger multiply = p.multiply(q);
			System.out.println(multiply.subtract(n));
		}
		//
		// BigDecimal sqrt = BigDecimalMathUtils.sqrt(BigDecimal.valueOf(25));
		// System.out.println("sqrt: " + sqrt);
		// sqrt = BigDecimalMathUtils.sqrt(new BigDecimal(N_STR));
		// System.out.println("sqrt: " + sqrt);
		// System.out.println("sqrt: " + sqrt.pow(2));

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

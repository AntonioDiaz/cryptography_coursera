#!/usr/bin/python

def mod_inverse(a,P):
    x, px, b = 0, 1, P
    while b: x, px, a, b = px - (a // b) * x, x, b, a % b
    return px % P

def mod_pow(a, b, P):
    result = 1
    while b > 0:
        b, r = divmod(b, 2)
        if r: result = (result * a) % P
        a = (a * a) % P
    return result
    

c_str = '2209645186741038177630656113488341801741006978789283107173183914367613560012053800\
4282329650473509424343946219751512256465839967942889460764542040581564748988013734\
8641204523252293201764879166664029975091887299716905260832220677716000193292608700\
09579993724077458967773697817571267229951148662959627934791540'

p_str = '13407807929942597099574024998205846127479365820592393377723561443721764030073662768891111614362326998675040546094339320838419523375986027530441562135724301'
q_str = '13407807929942597099574024998205846127479365820592393377723561443721764030073778560980348930557750569660049234002192590823085163940025485114449475265364281'

c = int(c_str)
p = int(p_str)
q = int(q_str)
e = 65537
n = p * q
print str(n)
phi = (p-1)*(q-1)

d = mod_inverse(e, phi);

print 'c [' + str(c) + ']'
print 'd [' + str(d) + ']'
print 'n [' + str(n) + ']'
m = mod_pow(c, d, n);
print '-'
print str(m)
'''
		BigInteger phi = p.subtract(one).multiply(q.subtract(one));
		
		BigInteger e = BigInteger.valueOf(65537);
		BigInteger d = e.modInverse(phi);
		
		System.out.println(d.multiply(e).mod(phi));
		
		String m = c.modPow(d, n).toString();	
'''





    



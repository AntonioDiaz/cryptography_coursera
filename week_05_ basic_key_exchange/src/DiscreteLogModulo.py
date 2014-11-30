#!/usr/bin/python


n1 = '1340780792994259709957402499820584612747936582059239337772\
3561443721764030073546976801874298166903427690031858186486050853\
753882811946569946433649006084171'
n2 = '1171782988036620700951611759633536708855808499999895220559\
9979459063929499736583746670572176471460312928594829675428279466\
566527115212748467589894601965568'
n3 = '3239475104050450443565264378728065788649097520952449527834\
7924529719819761432925580738569379585531805328789280014947060973\
94108577585732452307673444020333'
B = pow(2,20)
'''

n1 = '110213'
n2 = '101033'
n3 = '100551'
B = pow(2,10)
'''
'''
n1 = '17'
n2 = '3'
n3 = '12'
B = pow(2,5)
'''
p,g,h = int(n1),int(n2),int(n3)





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

diccionario = {}
function = mod_inverse(g, p)
anterior = mod_inverse(1, p)
anterior = (anterior * h) % p
x1 = 0
diccionario[anterior] = x1

while x1 < B:
    x1 += 1
    temp = (anterior*function) % p
    # print "guarda temp=" + str(temp)
    diccionario[temp] = x1
    anterior = temp;
    # print "inverse g=" + str(g) + " p=" + str(p) + " modInverse=" + str(anterior)
    if x1 % 1000 == 0:
        print "pasa X1 -->" + str(x1)
print "termina X1 ----" + str(x1)

x0 = 0;
encontrado = -1;
solution = -1;
while x0 < B and encontrado==-1:
    #print "x0 ----" + str(x0)   
    x0 += 1
    temp = mod_pow(g, B, p)
    temp = mod_pow(temp, x0, p)
    # print "temp" + str(temp)
    default = -1
    encontrado = diccionario.get(temp, default)
    #print " ->" + str(default)
    if encontrado != -1:
        print "encontrado" + str(encontrado)
        solution = x0 * B + encontrado;
    if x0 % 1000 == 0:
        print "pasa X0 -->" + str(x0)

    '''
    temp = g.modPow(BigInteger.valueOf(B), p);
	temp = temp.modPow(BigInteger.valueOf(x0), p);
    '''
print "termina x0 ----" + str(x0)    
print "termina encontrado ----" + str(encontrado)
print "termina solution ----" + str(solution)
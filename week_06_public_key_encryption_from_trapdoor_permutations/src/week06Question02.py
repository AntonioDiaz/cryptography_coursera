#!/usr/bin/python
def isqrt(n):
    x = n
    y = (x + 1) // 2
    while y < x:
        x = y
        y = (x + n // x) // 2
    return x


n_str = '6484558428080716696628242653467722787263437207069762630604390703787\
9730861808111646271401527606141756919558732184025452065542490671989\
2428844841839353281972988531310511738648965962582821502504990264452\
1008852816733037111422964210278402893076574586452336833570778346897\
15838646088239640236866252211790085787877'

n = int(n_str)
B = pow(2, 20)
index = 0
encontrado = -1
a = isqrt(n);


while index < B and encontrado==-1:
    x = isqrt(a*a-n)
    p = a - x
    q = a + x
    if p*q==n:
        encontrado = 1;
    if index % 1000 == 0:
        print "pasa index -->" + str(index)        
    index = index + 1
    a = a + 1
print "p [" + str(p) + "]"    




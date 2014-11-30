#!/usr/bin/python
def isqrt(n):
    x = n
    y = (x + 1) // 2
    while y < x:
        x = y
        y = (x + n // x) // 2
    return x


n = '17976931348623159077293051907890247336179769789423065727343008115\
77326758055056206869853794492129829595855013875371640157101398586\
47833778606925583497541085196591615128057575940752635007475935288\
71082364994994077189561705436114947486504671101510156394068052754\
0071584560878577663743040086340742855278549092581'

a = isqrt(int (n)) + 1
x = isqrt(a*a - int(n))
p = a - x
print "p: " + str(p)

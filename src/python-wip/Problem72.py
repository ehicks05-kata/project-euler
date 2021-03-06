# Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1,
# it is called a reduced proper fraction.
#
# If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:
#
# 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
#
# It can be seen that there are 21 elements in this set.
#
# How many elements would be contained in the set of reduced proper fractions for d ≤ 1,000,000?
#
# Answer:

import time
import math


def main():
    start = time.time()

    fractions_between_third_and_half = 0
    for d in range(1, 1_000_001):
        if d % 1000 == 0:
            print("d=" + str(d) + ", fractions found=" + str(fractions_between_third_and_half))

        for n in range(d // 3, d):
            decimal = n / d
            if decimal <= (1 / 3):
                continue
            if decimal >= (1 / 2):
                break

            gcd = math.gcd(n, d)
            if gcd == 1:
                fractions_between_third_and_half += 1

    print("\nanswer: " + str(fractions_between_third_and_half))
    print("\ntook " + str(time.time() - start) + " seconds")


if __name__ == '__main__':
    main()

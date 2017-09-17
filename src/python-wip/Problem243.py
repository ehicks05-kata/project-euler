# A positive fraction whose numerator is less than its denominator is called a proper fraction.
# For any denominator, d, there will be d−1 proper fractions; for example, with d = 12:
#     1/12 , 2/12 , 3/12 , 4/12 , 5/12 , 6/12 , 7/12 , 8/12 , 9/12 , 10/12 , 11/12 .
#
# We shall call a fraction that cannot be cancelled down a resilient fraction.
# Furthermore we shall define the resilience of a denominator, R(d), to be the ratio of its proper fractions
# that are resilient; for example, R(12) = 4/11 .
# In fact, d = 12 is the smallest denominator having a resilience R(d) < 4/10 .
#
# Find the smallest denominator d, having a resilience R(d) < 15499/94744 .
#
# Answer:

import time
import math


def main():
    start = time.time()

    resilience_target = 15499 / 94744
    print("target=" + str(resilience_target))
    smallest_resilience = 1

    d = 9699690
    while True:
        resilience = get_resilience(d)

        if resilience < smallest_resilience:
            smallest_resilience = resilience
            print("d=" + str(d) + ", resilience=" + str(resilience))

        if resilience < resilience_target:
            break

        d += 9699690

    print("\nanswer: " + str(d))
    print("\ntook " + str(time.time() - start) + " seconds")


def get_resilience(n):
    resilience = 0
    for i in range(n):
        gcd = math.gcd(i, n)
        if gcd == 1:
            resilience += 1
    return resilience / (n - 1)


if __name__ == '__main__':
    main()

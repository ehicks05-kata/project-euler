# The prime factors of 13195 are 5, 7, 13 and 29. What is the largest prime factor of the number 600851475143 ?
import time
import math

primes = [2]


def is_prime(input):
    for prime in primes:
        if input % prime == 0:
            return False

    primes.append(input)
    return True


def is_a_factor(possibleFactor, target):
    if target % possibleFactor == 0:
        return True
    return False


start = time.clock()
target = 600851475143
maxTestNumber = int(math.sqrt(target))
for n in range(2, maxTestNumber):
    if n % 1000000 == 0:
        duration = time.clock()
        print(str(n) + ' items / ' + str(int(duration)) + ' sec = ' + str(n / duration) + " I/S")

    if is_a_factor(n, target) and is_prime(n):
        print(str(n))
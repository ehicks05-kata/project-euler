# By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
# What is the 10001st prime number?


def prime(testNum):
    if testNum < 2:
        return False

    if testNum == 2:
        return True

    for n in range(2, int(testNum ** .5) + 1):
        if testNum % n == 0:
            return False
    return True


def getNthPrime(n):
    primeCount = 0
    testNum = 0
    while primeCount < n:
        testNum += 1
        if prime(testNum):
            primeCount += 1

    return testNum

print(getNthPrime(10001))

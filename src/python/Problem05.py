# What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?

import math


def evenlyDivisible(testNum):
    for i in range(1, 21):
        if testNum % i != 0:
            return False

    return True


def main():
    testNum = math.factorial(20)

    lowestTerm = False
    while not lowestTerm:
        lowestTerm = True
        for i in range(2, 21):
            if testNum % i == 0 and evenlyDivisible(testNum / i):
                testNum = int(testNum / i)
                lowestTerm = False
                break

    print(testNum)

main()

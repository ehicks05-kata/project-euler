# The difference between the sum of the squares of the first ten natural numbers
# and the square of the sum is 3025  385 = 2640.
# Find the difference between the sum of the squares of the first one hundred natural numbers
# and the square of the sum.


def getSumOfSquares(testNum):
    sum = 0
    for i in range(1, testNum):
        sum += (i ** 2)
    return sum


def getSquareOfSums(testNum):
    sum = 0
    for i in range(1, testNum):
        sum += i
    return sum ** 2


def main():
    testNum = 101
    squareOfSums = getSquareOfSums(testNum)
    sumOfSquares = getSumOfSquares(testNum)

    print(str(squareOfSums) + ' - ' + str(sumOfSquares) + ' = ' + str(squareOfSums - sumOfSquares))


if __name__ == '__main__':
    main()

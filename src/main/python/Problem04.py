# Find the largest palindrome made from the product of two 3-digit numbers.


def isPal(testNum):
    original = list(testNum)
    rev = list(testNum)
    rev.reverse()
    return rev == original


def main():
    largest = 0

    for i in range(100, 1000):
        for j in range(100, 1000):
            testNum = i * j

            if isPal(str(testNum)) and testNum > largest:
                largest = testNum
                print(largest)

    print(largest)


if __name__ == "__main__":
    main()
# Working from left-to-right if no digit is exceeded by the digit to its left it is called an increasing number;
# for example, 134468.
#
# Similarly if no digit is exceeded by the digit to its right it is called a decreasing number; for example, 66420.
#
# We shall call a positive integer that is neither increasing nor decreasing a "bouncy" number; for example, 155349.
#
# Clearly there cannot be any bouncy numbers below one-hundred, but just over half of the numbers below one-thousand
# (525) are bouncy. In fact, the least number for which the proportion of bouncy numbers first reaches 50% is 538.
#
# Surprisingly, bouncy numbers become more and more common and by the time we reach 21780 the proportion of bouncy
# numbers is equal to 90%.
#
# Find the least number for which the proportion of bouncy numbers is exactly 99%.
#
# Answer:

import time


def main():
    start = time.time()

    bouncy = 0
    non_bouncy = 0

    i = 1
    while True:
        if is_bouncy(i):
            bouncy += 1
        else:
            non_bouncy += 1

        proportion = bouncy / (bouncy + non_bouncy)
        if proportion == .99:
            break

        i += 1

    print("\nanswer: " + str(i))
    print("\ntook " + str(time.time() - start) + " seconds")


def is_bouncy(n):
    inc = True
    dec = True
    n_str = str(n)
    for i in range(len(n_str)):
        if i == 0:
            continue

        if n_str[i] > n_str[i - 1]:
            dec = False
        if n_str[i] < n_str[i - 1]:
            inc = False

        if not inc and not dec:
            return True

    return False


if __name__ == '__main__':
    main()

# The number 145 is well known for the property that the sum of the factorial of its digits is equal to 145:
#
# 1! + 4! + 5! = 1 + 24 + 120 = 145
#
# Perhaps less well known is 169, in that it produces the longest chain of numbers that link back to 169;
# it turns out that there are only three such loops that exist:
#
# 169 → 363601 → 1454 → 169
# 871 → 45361 → 871
# 872 → 45362 → 872
#
# It is not difficult to prove that EVERY starting number will eventually get stuck in a loop. For example,
#
# 69 → 363600 → 1454 → 169 → 363601 (→ 1454)
# 78 → 45360 → 871 → 45361 (→ 871)
# 540 → 145 (→ 145)
#
# Starting with 69 produces a chain of five non-repeating terms, but the longest non-repeating chain with a starting
# number below one million is sixty terms.
#
# How many chains, with a starting number below one million, contain exactly sixty non-repeating terms?
#
# Answer:

import math
import time


def main():
    start = time.time()
    chains_with_60_non_repeating_terms = 0
    known_chain_lengths = {}

    for i in range(1_000_000):
        if i % 100000 == 0:
            print(i)
        update_known_chain_lengths(i, known_chain_lengths)

    for key, val in known_chain_lengths.items():
        if val == 60:
            chains_with_60_non_repeating_terms += 1

    print("\nanswer: " + str(chains_with_60_non_repeating_terms))
    print("\ntook " + str(time.time() - start) + " seconds")


def get_sum_of_factorial_digits(n):
    return sum(math.factorial(int(i)) for i in list(str(n)))


def update_known_chain_lengths(n, known_chain_lengths):
    if n in known_chain_lengths.keys():
        return known_chain_lengths[n]

    terms = []

    while n not in terms:
        terms.append(n)
        n = get_sum_of_factorial_digits(n)

        if n in known_chain_lengths.keys():
            for i in range(len(terms)):
                known_chain_lengths[terms[i]] = (len(terms) - i) + known_chain_lengths[n]

            return known_chain_lengths[n]

    for i in range(len(terms)):
        known_chain_lengths[terms[i]] = len(terms) - i

    return terms


if __name__ == '__main__':
    main()

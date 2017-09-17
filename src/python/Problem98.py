# By replacing each of the letters in the word CARE with 1, 2, 9, and 6 respectively,
# we form a square number: 1296 = 36^2. What is remarkable is that, by using the same digital substitutions,
# the anagram, RACE, also forms a square number: 9216 = 96^2. We shall call CARE (and RACE) a square anagram
# word pair and specify further that leading zeroes are not permitted, neither may a different letter have the
# same digital value as another letter.
#
# Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common
# English words, find all the square anagram word pairs (a palindromic word is NOT considered to be an anagram of
# itself).
#
# What is the largest square number formed by any member of such a pair?
#
# NOTE: All anagrams formed must be contained in the given text file.
#
# Answer:

import itertools
import time


def main():
    start = time.time()

    file = open('../data/Problem098Words.txt')
    words = file.read().replace("\"", "").split(",")

    anagram_groups, longest_anagram_word_length = get_anagram_groups(words)

    squares_by_length = get_square_length_to_squares(longest_anagram_word_length)

    largest_pair_member = 0
    for letter_count_dict, anagram_group_members in anagram_groups.items():
        anagram_length = len(anagram_group_members[0])

        for anagram_pair in itertools.combinations(anagram_group_members, 2):  # try each word combo in anagram group
            print(str(anagram_pair))
            first_word = anagram_pair[0]
            second_word = anagram_pair[1]
            unique_digits_in_word = len(set(list(first_word)))

            for square in squares_by_length[anagram_length]:
                unique_digits_in_square = len(set(list(str(square))))
                if unique_digits_in_square != unique_digits_in_word:
                    continue

                letter_to_number_dict = {}
                for i in range(anagram_length):
                    letter = list(first_word)[i]
                    letter_to_number_dict[letter] = str(square)[i]

                first_word_as_number = word_to_number(first_word, letter_to_number_dict)
                second_word_as_number = word_to_number(second_word, letter_to_number_dict)

                if second_word_as_number in squares_by_length[anagram_length]:
                    print("FOUND PAIR: " + str(first_word_as_number) + ", " + str(second_word_as_number) + "... " +
                          str(letter_to_number_dict))
                    largest_pair_member = max(largest_pair_member, first_word_as_number, second_word_as_number)

    print("\nanswer: " + str(largest_pair_member))
    print("\ntook " + str(time.time() - start) + " seconds")


def word_to_number(word, letter_to_number_dict):
    word_as_number = ""
    for letter in word:
        word_as_number += str(letter_to_number_dict[letter])

    return int(word_as_number)


def get_anagram_groups(words):
    anagram_groups = {}

    words = sorted(words, key=len, reverse=True)
    for word in words:
        letter_counts_as_str = get_letter_counts_as_str(word)

        if letter_counts_as_str in anagram_groups.keys():
            anagram_groups[letter_counts_as_str].append(word)
        else:
            anagram_groups[letter_counts_as_str] = [word]

    keys_to_remove = set()
    for key in anagram_groups.keys():
        if len(anagram_groups[key]) < 2:
            keys_to_remove.add(key)
        else:  # remove palindromes
            for permutation in itertools.permutations(anagram_groups[key]):
                if permutation[0] == permutation[1][::-1]:
                    keys_to_remove.add(key)

    for key in keys_to_remove:
        anagram_groups.pop(key)

    longest_anagram_word_length = 0
    for val in anagram_groups.values():
        anagram_length = len(val[0])
        if anagram_length > longest_anagram_word_length:
            longest_anagram_word_length = anagram_length

    return anagram_groups, longest_anagram_word_length


def get_letter_counts_as_str(word):
    letter_counts = {}
    for letter in sorted(list(word)):
        letter = letter.upper()
        if letter in letter_counts.keys():
            letter_counts[letter] += 1
        else:
            letter_counts[letter] = 1
    return str(letter_counts)


def get_square_length_to_squares(longest_word_length):
    square_lengths = {}
    for i in range(100_000):
        square = i * i
        length = len(str(square))

        if length > longest_word_length:
            break

        if length in square_lengths.keys():
            square_lengths[length].append(square)
        else:
            square_lengths[length] = [square]

    return square_lengths


if __name__ == '__main__':
    main()

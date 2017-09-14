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

import time
import itertools


class Word:
    def __init__(self, word):
        self.word = word
        self.letter_counts = Word.get_letter_counts(word)

    @staticmethod
    def get_letter_counts(word):
        letter_counts = {}
        for letter in sorted(list(word)):
            letter = letter.upper()
            if letter in letter_counts.keys():
                letter_counts[letter] += 1
            else:
                letter_counts[letter] = 1
        return letter_counts

    def letter_counts_as_str(self):
        return str(self.letter_counts)

    def is_anagram(self, other):
        return self.letter_counts == other.letter_counts


def main():
    start = time.time()

    file = open('../data/Problem098Words.txt')
    words = file.read().replace("\"", "").split(",")

    anagram_groups, longest_anagram_word_length = get_anagram_groups(words)

    squares_by_length = get_square_length_to_squares(longest_anagram_word_length)

    largest_pair_member = 0
    for k, v in anagram_groups.items():
        print(str(v))
        # todo: this currently only handles anagram groups with 2 numbers. it won't handle a 3rd number in the group
        words_in_anagram_group = v
        first_word = words_in_anagram_group[0]
        anagram_length = len(first_word)
        second_word = words_in_anagram_group[1]

        for permutation in itertools.permutations(range(1, 10), anagram_length):
            if permutation[anagram_length - 1] in (2, 3, 7, 8):
                continue

            letter_to_number_dict = {}
            for i in range(anagram_length):
                letter = list(first_word)[i]
                letter_to_number_dict[letter] = permutation[i]

            first_word_as_number = word_to_number(first_word, letter_to_number_dict)

            if first_word_as_number in squares_by_length[anagram_length]:

                second_word_as_number = word_to_number(second_word, letter_to_number_dict)
                if second_word_as_number in squares_by_length[anagram_length]:
                    print("FOUND PAIR: " + str(first_word_as_number) + ", " + str(second_word_as_number) + "... " +
                          str(letter_to_number_dict))
                    largest_pair_member = max(largest_pair_member, first_word_as_number, second_word_as_number)

        if largest_pair_member > 0:
            break

    print("\nanswer: " + str(largest_pair_member))
    print("\ntook " + str(time.time() - start) + " seconds")


def get_word_lengths(words):
    words = sorted(words, key=len, reverse=True)
    word_lengths = {}
    for word in words:
        if len(str(word)) in word_lengths.keys():
            word_lengths[len(str(word))].append(word)
        else:
            word_lengths[len(str(word))] = [word]
    return word_lengths


def word_to_number(word, letter_to_number_dict):
    word_as_number = ""
    for letter in word:
        word_as_number += str(letter_to_number_dict[letter])

    return int(word_as_number)


def get_anagram_groups(words):
    word_lengths = get_word_lengths(words)

    anagram_groups = {}
    for key, val in word_lengths.items():
        words_of_length = val
        for word_of_length in words_of_length:
            word = Word(word_of_length)
            letter_counts_as_str = word.letter_counts_as_str()

            if letter_counts_as_str in anagram_groups.keys():
                anagram_groups[letter_counts_as_str].append(word.word)
            else:
                anagram_groups[letter_counts_as_str] = [word.word]

    keys_to_remove = set()
    for key in anagram_groups.keys():
        if len(anagram_groups[key]) < 2:
            keys_to_remove.add(key)
        else: # remove palindromes
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


def get_square_length_to_squares(longest_word_length):
    square_lengths = {}
    for i in range(100_000):
        square = i * i
        length = len(str(square))
        if length in square_lengths.keys():
            square_lengths[length].append(square)
        else:
            if length > longest_word_length:
                break
            square_lengths[length] = [square]

    total_squares = 0
    print(len(square_lengths))
    for key, val in square_lengths.items():
        print(str(key) + ":" + str(len(val)))
        total_squares += len(val)
    print("total squares: " + str(total_squares))

    return square_lengths


if __name__ == '__main__':
    main()

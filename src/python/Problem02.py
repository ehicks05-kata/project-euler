# By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.

term1 = 1
term2 = 2
temp = 0
total = 0

while term2 <= 4000000:
    if term2 % 2 == 0:
        total += term2

    temp = term2
    term2 += term1
    term1 = temp

print('The sum is: ' + str(total))
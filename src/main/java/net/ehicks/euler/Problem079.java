package net.ehicks.euler;/*
A common security method used for online banking is to ask the user for three random characters from a passcode.
For example, if the passcode was 531278, they may ask for the 2nd, 3rd, and 5th characters; the expected reply would be: 317.

The text file, keylog.txt, contains fifty successful login attempts.

Given that the three characters are always asked for in order,
analyse the file so as to determine the shortest possible secret passcode of unknown length.
*/

import java.util.*;

public class Problem079
{
    public static void main(String[] args) throws Exception
    {
        Timer timer = new Timer();

        // note - no 4's or 5's
        List<Integer> successfulAttempts = Arrays.asList(319, 680, 180, 690, 129, 620, 762, 689, 762, 318, 368, 710, 720,
                710, 629, 168, 160, 689, 716, 731, 736, 729, 316, 729, 729, 710, 769, 290, 719, 680, 318, 389, 162, 289,
                162, 718, 729, 319, 790, 680, 890, 362, 319, 760, 316, 729, 380, 319, 728, 716);

        Map<String, PasscodeDigit> passcodeDigitMap = new HashMap<>();
        for (Integer successfulAttempt : successfulAttempts)
        {
            String[] chars = String.valueOf(successfulAttempt).split("");
            for (int i = 0; i < chars.length; i++)
            {
                String character = chars[i];
                PasscodeDigit passcodeDigit = passcodeDigitMap.get(character);
                if (passcodeDigit == null)
                {
                    passcodeDigit = new PasscodeDigit(Integer.parseInt(character));
                    passcodeDigitMap.put(character, passcodeDigit);
                }

                for (int j = 0; j < chars.length; j++)
                {
                    int digit = Integer.valueOf(chars[j]);
                    if (j < i && !passcodeDigit.precedents.contains(digit))
                        passcodeDigit.precedents.add(digit);
                    if (j > i && !passcodeDigit.antecedents.contains(digit))
                        passcodeDigit.antecedents.add(digit);
                }
            }
        }

        List<PasscodeDigit> passcodeDigits = new ArrayList<>(passcodeDigitMap.values());
        passcodeDigits.sort((o1, o2) -> o1.precedents.size() - o2.precedents.size());

        String answer = "";
        for (PasscodeDigit passcodeDigit : passcodeDigits)
            answer += passcodeDigit.digit;

        System.out.println("Determine the shortest possible secret passcode of unknown length: " + answer);
        timer.printDuration();
    }

    private static class PasscodeDigit
    {
        public int digit;
        public List<Integer> precedents = new ArrayList<>();
        public List<Integer> antecedents = new ArrayList<>();

        public PasscodeDigit(int digit)
        {
            this.digit = digit;
        }

        public String toString()
        {
            return this.digit + " has " + precedents.size() + " precedents, " + antecedents.size() + " antecedents";
        }
    }
}
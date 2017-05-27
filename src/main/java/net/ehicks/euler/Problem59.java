package net.ehicks.euler;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Each character on a computer is assigned a unique code and the preferred standard is ASCII (American Standard Code for Information Interchange).
For example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.

A modern encryption method is to take a text file, convert the bytes to ASCII, then XOR each byte with a given value, taken from a secret key.
The advantage with the XOR function is that using the same encryption key on the cipher text, restores the plain text; for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.

For unbreakable encryption, the key is the same length as the plain text message, and the key is made up of random bytes.
The user would keep the encrypted message and the encryption key in different locations, and without both "halves", it is impossible to decrypt the message.

Unfortunately, this method is impractical for most users, so the modified method is to use a password as a key.
If the password is shorter than the message, which is likely, the key is repeated cyclically throughout the message.
The balance for this method is using a sufficiently long password key for security, but short enough to be memorable.

Your task has been made easy, as the encryption key consists of three lower case characters.
Using cipher1.txt (right click and 'Save Link/Target As...'), a file containing the encrypted ASCII codes,
and the knowledge that the plain text must contain common English words, decrypt the message and find the sum of the ASCII values in the original text.
*/
public class Problem59
{
    public static void main(String[] args) throws Exception
    {
        long startTime = System.currentTimeMillis();

        String cipher = Files.readAllLines(Paths.get("problem59cipher.txt"), Charset.defaultCharset()).get(0);
        List<String> cypherBytes = Arrays.asList(cipher.split(","));

        List<List<Byte>> keys = getPossibleKeys();
        int keyListIndex = 0;
        int keyIndex = 0;

        StringBuilder message = new StringBuilder();
        int asciiValueSum = 0;

        while (!message.toString().contains(" the "))
        {
            if (keyIndex > keys.size()) break;
            message = new StringBuilder();
            asciiValueSum = 0;

            List<Byte> testKey = keys.get(keyListIndex);

            for (String character : cypherBytes)
            {
                if (keyIndex > 2) keyIndex = 0;

                byte byteValue = Byte.valueOf(character);
                byte decryptedByte = (byte) (byteValue ^ testKey.get(keyIndex));
                char decryptedChar = (char) decryptedByte;

                asciiValueSum += decryptedByte;
                message.append(decryptedChar);

                keyIndex++;
            }

            keyListIndex++;
        }

        System.out.println(message);

        System.out.println("Decrypt the message and find the sum of the ASCII values in the original text: " + asciiValueSum);
        System.out.println("Done in " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static List<List<Byte>> getPossibleKeys()
    {
        List<List<Byte>> possibleKeys = new ArrayList<>();
        int start = 97;  // lower case a
        int end = 123;   // lower case z

        for (int i = start; i < end; i++)
            for (int j = start; j < end; j++)
                for (int k = start; k < end; k++)
                    possibleKeys.add(Arrays.asList((byte) i, (byte) j, (byte) k));

        return possibleKeys;
    }
}
package net.ehicks.euler;

/*What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?*/
public class Problem005
{
    public static void main(String[] args)
    {
        System.out.println(findSolution());
    }

    public static int findSolution()
    {
        boolean doesItCheck = false; //initialize to false

        for(int testNum = 1; !doesItCheck; testNum++) //loop will increment testnum until doesitcheck becomes true
        {
            doesItCheck = true; //set to true before the test loop

            for(int i = 1; i <= 20; i++) //loop will run from 1 to 20
            {
                if(testNum % i != 0) //if our testnum divided by i has a remainder thats not zero, doesitcheck becomes false
                    doesItCheck = false;
            }

            if(doesItCheck) //if doesitcheck is ever true at this point, return testnum
                return testNum;
        }
        return -1; //as long as there is a solution this line won't be executed
    }
}
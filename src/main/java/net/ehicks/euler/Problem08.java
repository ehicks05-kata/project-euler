package net.ehicks.euler;

/*Find the greatest product of five consecutive digits in the 1000-digit number.*/
public class Problem08
{
    public static void main(String[] args)
    {
        String largeNumString = "73167176531330624919225119674426574742355349194934969835203127745063262395783180169" +
                "848018694788518438586156078911294949545950173795833195285320880551112540698747158523863050715693290" +
                "963295227443043557668966489504452445231617318564030987111217223831136222989342338030813533627661428" +
                "280644448664523874930358907296290491560440772390713810515859307960866701724271218839987979087922749" +
                "219016997208880937766572733300105336788122023542180975125454059475224352584907711670556013604839586" +
                "446706324415722155397536978179778461740649551492908625693219784686224828397224137565705605749026140" +
                "797296865241453510047482166370484403199890008895243450658541227588666881164271714799244429282308634" +
                "656748139191231628245861786645835912456652947654568284891288314260769004224219022671055626321111109" +
                "370544217506941658960408071984038509624554443629812309878799272442849091888458015616609791913387549" +
                "920052406368991256071760605886116467109405077541002256983155200055935729725716362695618826704282524" +
                "83600823257530420752963450";
        findGreatestProduct(largeNumString);
    }

    public static void findGreatestProduct(String longString)
    {
        int greatestProduct = 0, currentProduct;
        String string1, string2, string3, string4, string5;
        int digit1, digit2, digit3, digit4, digit5;

        for(int n = 0; n < (longString.length() - 5); n++) //This loop steps through each digit of the large number
        {
            string1 = longString.substring(n, n+1);
            digit1 = Integer.parseInt(string1);
            string2 = longString.substring(n+1, n+2);
            digit2 = Integer.parseInt(string2);
            string3 = longString.substring(n+2, n+3);
            digit3 = Integer.parseInt(string3);
            string4 = longString.substring(n+3, n+4);
            digit4 = Integer.parseInt(string4);
            string5 = longString.substring(n+4, n+5);
            digit5 = Integer.parseInt(string5);

            currentProduct = (digit1*digit2*digit3*digit4*digit5);
            if(currentProduct > greatestProduct)
                greatestProduct = currentProduct;
        }
        System.out.println(greatestProduct);
    }
}
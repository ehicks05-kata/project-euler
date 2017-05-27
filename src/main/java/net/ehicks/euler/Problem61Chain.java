package net.ehicks.euler;

import java.util.ArrayList;
import java.util.List;

public class Problem61Chain
{
    public int number1;
    public int number2;
    public int number3;
    public int number4;
    public int number5;
    public int number6;
    public int type1;
    public int type2;
    public int type3;
    public int type4;
    public int type5;
    public int type6;

    public Problem61Chain()
    {
    }

    public Problem61Chain(Problem61Chain source)
    {
        this.number1 = source.number1;
        this.number2 = source.number2;
        this.number3 = source.number3;
        this.number4 = source.number4;
        this.number5 = source.number5;
        this.number6 = source.number6;
        this.type1 = source.type1;
        this.type2 = source.type2;
        this.type3 = source.type3;
        this.type4 = source.type4;
        this.type5 = source.type5;
        this.type6 = source.type6;
    }

    public void shiftByOne()
    {
        if (number6 != 0) return;
        number6 = number5;
        number5 = number4;
        number4 = number3;
        number3 = number2;
        number2 = number1;
        number1 = 0;
        type6 = type5;
        type5 = type4;
        type4 = type3;
        type3 = type2;
        type2 = type1;
        type1 = 0;
    }

    public List<Integer> getTypesUsed()
    {
        List<Integer> typesUsed = new ArrayList<>();
        if (type1 > 0) typesUsed.add(type1);
        if (type2 > 0) typesUsed.add(type2);
        if (type3 > 0) typesUsed.add(type3);
        if (type4 > 0) typesUsed.add(type4);
        if (type5 > 0) typesUsed.add(type5);
        if (type6 > 0) typesUsed.add(type6);
        return typesUsed;
    }
}

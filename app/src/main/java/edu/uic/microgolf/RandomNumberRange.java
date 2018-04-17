package edu.uic.microgolf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class RandomNumberRange {
    private final List<Integer> range = new ArrayList<>();

    RandomNumberRange(int min, int max)
    {
        this.addRange(min, max);
    }

    final void addRange(int min, int max)
    {
        for(int i = min; i <= max; i++)
        {
            this.range.add(i);
        }
    }

    int getRandom()
    {
        return this.range.get(new Random().nextInt(this.range.size()));
    }

   /* public static void main(String[] args)
    {
        RandomNumberRange rir = new RandomNumberRange(1, 10);
        rir.addRange(50, 60);
        for(int i=0; i <10; i++)
        System.out.println(rir.getRandom());
    }*/

}



//    public static void main(String[] args) {
//
//
//        Main ob = new Main();
//        //System.out.println(ob.closeGroup(32));
//        System.out.println(sameGroup(32));
//        System.out.println(sameGroup(33));
//        System.out.println(sameGroup(38));
//        System.out.println(list);
//        System.out.println(list.size());
//    }
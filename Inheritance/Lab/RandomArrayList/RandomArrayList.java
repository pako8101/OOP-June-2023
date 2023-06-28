package Inheritance.Lab.RandomArrayList;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomArrayList<E> extends ArrayList<E> {


    private final static Random random = ThreadLocalRandom.current();

    public RandomArrayList() {
        super();

    }


    public E getRandomElement() {
       int randomIndex= random.nextInt(super.size());
        return super.get(randomIndex);
    }
//        if (size() == 0) {
//            throw new IllegalStateException();
//        }
//        int index = random.nextInt(size());
//        return remove(index);
//    }
}

package com.forrestdale.utils;

import java.util.function.IntConsumer;

/**
 * Created by forrest on 2/12/17.
 */
public class IntAverager implements IntConsumer {
    private int count = 0;
    private int total = 0;

    public double average() {
        return total / count;
    }

    @Override
    public void accept(int i) {
        count++;
        total += i;
    }
    public void combine(IntAverager avg) {
        count += avg.count;
        total += avg.total;
    }
}

package com.forrestdale.utils;

import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;

public class DoubleAverager implements DoubleConsumer {
    private double count = 0;
    private double total = 0;

    public double average() {
        return total / count;
    }

    @Override
    public void accept(double i) {
        count++;
        total += i;
    }
    public void combine(DoubleAverager avg) {
        count += avg.count;
        total += avg.total;
    }
}

package com.methodsecurity.controller;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Test {
    public static void main(String[] args) {
        List<String> names = Arrays.asList(
                "Geek", "GeeksQuiz", "g1", "QA", "Geek2");

        Predicate<String> p = s -> s.startsWith("G");
        for (String st : names) {
            if (p.test(st))
                System.out.println(st);
        }

        Consumer<String> display = a -> System.out.println(a);
        display.accept("10");

        Function<Integer, Double> half = a -> a / 2.0;
        half = half.andThen(a -> 3 * a);
        System.out.println(half.apply(10));

        Supplier<Double> randomValue = () -> Math.random();
        System.out.println(randomValue.get());

    }
}
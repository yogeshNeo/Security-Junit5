package com.methodsecurity;

public class PrivateClass {
    public static void main(String[] args) {
        String s = "   " + "s";
        string11Test(s);
    }

    private static void string11Test(String s) {
        System.out.println("====================");
        System.out.println(s.trim());
        System.out.println(s.isBlank());
        System.out.println(s.isEmpty());
        s.lines().forEach(System.out::println);
        System.out.println(s.strip());
        System.out.println(s.stripLeading());
        System.out.println(s.stripTrailing());
        System.out.println(s.repeat(2));
        System.out.println(s.isBlank());

        System.out.println("====================");
    }

    private static String repeat(String s) {
        return s.repeat(2);
    }

    private static boolean checkForBlankString(String s) {
        return s.isBlank();
    }


}

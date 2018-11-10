package com.example.springbootmongo.user;

public class Test {
    public static void main(String[] args){
        Test test1 = new Test();
        Test test2 = new Test();
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test1=test2);
        System.out.println(test1==(test1=test2));
       // System.out.println(test1==(test1));
    }
}

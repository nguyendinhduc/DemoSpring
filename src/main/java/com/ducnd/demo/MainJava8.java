package com.ducnd.demo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ducnd on 5/7/17.
 */
public class MainJava8 {
    public static void main(String[] args) {
        List<MyString> strs = Arrays.asList(
                new MyString("hihi"),
                new MyString("hello"),
               new MyString( "java")
        );
        final List<MyString> objectStream = strs.stream().map(new Function<MyString, MyString>() {

            @Override
            public MyString apply(MyString myString) {
                 myString.setValue(myString.getValue().toUpperCase());
                 return myString;
            }
        }).collect(Collectors.toList());
        MyString ss = strs.stream().filter(t->{
            t.setValue(t.getValue().toUpperCase());
            return true;
        }).findFirst().get();

        System.out.println(Arrays.toString(objectStream.toArray()));
        System.out.println(Arrays.toString(strs.toArray()));
    }
}

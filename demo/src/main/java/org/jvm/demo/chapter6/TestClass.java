package org.jvm.demo.chapter6;

/**
 * @author : cuixiuyin
 * @date : 2020/4/1
 */
public class TestClass {

    private int m;

    public int inc() {
        return m + 1;
    }

    /**
     Classfile /D:/JMCui/jvm-demo/demo/target/classes/org/jvm/demo/chapter6/TestClass.class
     Last modified 2020-4-1; size 397 bytes
     MD5 checksum 291f52e2b746bf6c338ece68fdf3dc08
     Compiled from "TestClass.java"
     public class org.jvm.demo.chapter6.TestClass
     minor version: 0
     major version: 52
     flags: ACC_PUBLIC, ACC_SUPER
     Constant                                                                          :
     #1 = Methodref          #4.#18         // java/lang/Object."<init>":()V
     #2 = Fieldref           #3.#19         // org/jvm/demo/chapter6/TestClass.m:I
     #3 = Class              #20            // org/jvm/demo/chapter6/TestClass
     #4 = Class              #21            // java/lang/Object
     #5 = Utf8               m
     #6 = Utf8               I
     #7 = Utf8               <init>
     #8 = Utf8               ()V
     #9 = Utf8               Code
     #10 = Utf8               LineNumberTable
     #11 = Utf8               LocalVariableTable
     #12 = Utf8               this
     #13 = Utf8               Lorg/jvm/demo/chapter6/TestClass;
     #14 = Utf8               inc
     #15 = Utf8               ()I
     #16 = Utf8               SourceFile
     #17 = Utf8               TestClass.java
     #18 = NameAndType        #7:#8          // "<init>":()V
     #19 = NameAndType        #5:#6          // m:I
     #20 = Utf8               org/jvm/demo/chapter6/TestClass
     #21 = Utf8               java/lang/Object
     {
     public org.jvm.demo.chapter6.TestClass();
     descriptor: ()V
     flags: ACC_PUBLIC
     Code:
     stack=1, locals=1, args_size=1
     0: aload_0
     1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     4: return
     LineNumberTable:
     line 7: 0
     LocalVariableTable:
     Start  Length  Slot  Name   Signature
     0       5     0  this   Lorg/jvm/demo/chapter6/TestClass;

     public int inc();
     descriptor: ()I
     flags: ACC_PUBLIC
     Code:
     stack=2, locals=1, args_size=1
     0: aload_0
     1: getfield      #2                  // Field m:I
     4: iconst_1
     5: iadd
     6: ireturn
     LineNumberTable:
     line 12: 0
     LocalVariableTable:
     Start  Length  Slot  Name   Signature
     0       7     0  this   Lorg/jvm/demo/chapter6/TestClass;
     }
     SourceFile: "TestClass.java"

     */
}

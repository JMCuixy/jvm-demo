package org.jvm.demo.chapter6;

/**
 * @author : cuixiuyin
 * @date : 2020/4/1
 */
public class TestClass2 {

    public int inc() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }

    /**
     Classfile /D:/JMCui/jvm-demo/demo/target/classes/org/jvm/demo/chapter6/TestClass2.class
     Last modified 2020-4-1; size 624 bytes
     MD5 checksum 9344f46e13068c4ac4700778b73b97a1
     Compiled from "TestClass2.java"
     public class org.jvm.demo.chapter6.TestClass2
     minor version: 0
     major version: 52
     flags: ACC_PUBLIC, ACC_SUPER
     Constant pool:
     #1 = Methodref          #4.#23         // java/lang/Object."<init>":()V
     #2 = Class              #24            // java/lang/Exception
     #3 = Class              #25            // org/jvm/demo/chapter6/TestClass2
     #4 = Class              #26            // java/lang/Object
     #5 = Utf8               <init>
     #6 = Utf8               ()V
     #7 = Utf8               Code
     #8 = Utf8               LineNumberTable
     #9 = Utf8               LocalVariableTable
     #10 = Utf8               this
     #11 = Utf8               Lorg/jvm/demo/chapter6/TestClass2;
     #12 = Utf8               inc
     #13 = Utf8               ()I
     #14 = Utf8               x
     #15 = Utf8               I
     #16 = Utf8               e
     #17 = Utf8               Ljava/lang/Exception;
     #18 = Utf8               StackMapTable
     #19 = Class              #24            // java/lang/Exception
     #20 = Class              #27            // java/lang/Throwable
     #21 = Utf8               SourceFile
     #22 = Utf8               TestClass2.java
     #23 = NameAndType        #5:#6          // "<init>":()V
     #24 = Utf8               java/lang/Exception
     #25 = Utf8               org/jvm/demo/chapter6/TestClass2
     #26 = Utf8               java/lang/Object
     #27 = Utf8               java/lang/Throwable
     {
     public org.jvm.demo.chapter6.TestClass2();
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
     0       5     0  this   Lorg/jvm/demo/chapter6/TestClass2;

     public int inc();
     descriptor: ()I
     flags: ACC_PUBLIC
     Code:
     stack=1, locals=5, args_size=1
     0: iconst_1
     1: istore_1
     2: iload_1
     3: istore_2
     4: iconst_3
     5: istore_1
     6: iload_2
     7: ireturn
     8: astore_2
     9: iconst_2
     10: istore_1
     11: iload_1
     12: istore_3
     13: iconst_3
     14: istore_1
     15: iload_3
     16: ireturn
     17: astore        4
     19: iconst_3
     20: istore_1
     21: aload         4
     23: athrow
     Exception table:
     from    to  target type
     0     4     8   Class java/lang/Exception
     0     4    17   any
     8    13    17   any
     17    19    17   any
     LineNumberTable:
     line 12: 0
     line 13: 2
     line 18: 4
     line 13: 6
     line 14: 8
     line 15: 9
     line 16: 11
     line 18: 13
     line 16: 15
     line 18: 17
     line 19: 21
     LocalVariableTable:
     Start  Length  Slot  Name   Signature
     2       6     1     x   I
     9       8     2     e   Ljava/lang/Exception;
     11       6     1     x   I
     0      24     0  this   Lorg/jvm/demo/chapter6/TestClass2;
     21       3     1     x   I
     StackMapTable: number_of_entries = 2
     frame_type = 72 // same_locals_1_stack_item
     stack = [ class java/lang/Exception ]
     frame_type = 72 // same_locals_1_stack_item
     stack = [ class java/lang/Throwable ]
     }
     SourceFile: "TestClass2.java"
     */

}

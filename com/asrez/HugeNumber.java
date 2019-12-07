package com.asrez;

import org.omg.CORBA.INTERNAL;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;

public class HugeNumber {
//    public int length;// We can use 'long'...
    public int[] array;
//    private ArrayList<Integer> array = new ArrayList<>();

    HugeNumber(String input) {
        array=new int[input.length()];
        int i;
        for(i=0;i<input.length();i++) {
            if(Character.isDigit(input.charAt(i))) {
//                array.add(Integer.parseInt(Character.toString(input.charAt(i))));
                array[i]=Integer.parseInt(Character.toString(input.charAt(i)));
            }
            else {
//                throw new Exception("The input is not valid!");
                break;
            }
        }
//        length=i;
    }

    HugeNumber(HugeNumber input) {
        this.array=input.array;
    }

    HugeNumber(int input) {
//        for(int i=getLengthOfNumber(input)-1;i>=0;i--) {}
//        while(input != 0) {
////            System.out.println(input % 10);
//            array.add(input % 10);
//            input /= 10;
//        }
//        for(int i=getLength(input)-1;input != 0;i--) {
//            array[i]=input % 10;
//            input /= 10;
//        }
        int i=0;
        int inputLength=getLength(input);
        array=new int[inputLength];
        while(input != 0) {
            array[inputLength - ++i]=(input % 10);
            input /= 10;
        }
//        array=reverseArrayList();
    }

    public int getLength(int input) {
        int i=0;
        while(input != 0) {
            i++;
            input/=10;
        }
        return i;
    }

    public int getLength() {
        return array.length;
    }

//    public ArrayList<Integer> getArray() {
//        return array;
//    }
    public int[] getArray() {
        return array;
    }

//    public ArrayList<Integer> reverseArrayList() {
//        ArrayList<Integer> revArrayList = new ArrayList<Integer>();
//        for (int i = array.length - 1; i >= 0; i--) {
//            revArrayList.add(array[i]);
//        }
//        return revArrayList;
//    }

    public void print() {
//        for(int i = 0; i < array.size(); i++) {
//            System.out.print(array.get(i) + " ");
//        }
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private static int[] resizeArray(int[] array, int size) {
        return resizeArray(array, size);
    }

    private static int[] resizeArray(int[] array, int size,boolean copy) {
        int newArray[] = new int[size];
        if(copy) {
            System.arraycopy(array, 0, newArray, 0, size);
        }
        return newArray;
    }

    //Add +
    public void add(HugeNumber input) {
        int max= Integer.max(input.array.length, array.length);
        for(int i=0;i<input.array.length;i++) {
            array[max-1-i]=array[max-1-i] + input.array[input.array.length-1-i];
            for(int j=0;array[max-1-i-j] >= 10;j++) {
                array[max-1-i-j]=0;
                array[max-1-i-1-j]=array[max-1-i-1-j] + 1;
            }
        }
    }

    //Subtract -
    //Multiplie *
    //Divide /
    //Remainder %

    public static void main(String arguments[]) {
        HugeNumber test=new HugeNumber(1398);
        System.out.println(test.getArray().toString());
        System.out.println(test.getLength());
        System.out.println(test.getLength(1398));
        test.print();
        test.add(new HugeNumber("110"));
        test.add(new HugeNumber("110"));
        test.print();
    }
}


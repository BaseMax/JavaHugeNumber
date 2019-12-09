package com.asrez;

import org.omg.CORBA.INTERNAL;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;

public class HugeNumber {
	public int[] array;

	HugeNumber(String input) {
		if(input == null || input.length() == 0) {
			System.err.println("Error: input of constructor is empty!");
			return;
		}
		array=new int[input.length()];
		int i;
		for(i=input.length()-1;i>=0;i--) {
			if(Character.isDigit(input.charAt(i))) {
				array[i]=Integer.parseInt(Character.toString(input.charAt(i)));
			}
			else {
				break;
			}
		}
	}

	HugeNumber(HugeNumber input) {
		this.array=input.array;
	}

	HugeNumber(int input) {
		if(input < 0) {
			System.err.println("Error: input of constructor is not valid!");
		}
		int inputLength=getLength(input);
		array=new int[inputLength];
		int i=0;
		while(input != 0) {
			array[i++]=(input % 10);
			input /= 10;
		}
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

	public int[] getArray() {
		return array;
	}

	public void print() {
		print(true);
	}

	public void print(boolean split) {
		if(split) {
			for(int i = array.length-1; i >= 0; i--) {
				System.out.print(array[i] + " ");
			}
		}
		else {
			for(int i = array.length-1; i >= 0; i--) {
				System.out.print(array[i]);
			}
		}
		System.out.println();
	}

	public String result() {
		String result="";
		for(int i = array.length-1; i >= 0; i--) {
			result+=array[i];
		}
		return result;
	}

	public String __toString() {
		return result();
	}

	private static int[] resizeArray(int[] array, int size, int to) {
		return resizeArray(array, size, to, true);
	}

	private static int[] resizeArray(int[] array, int size, int to, boolean copy) {
		int newArray[] = new int[size];
		if(copy) {
			System.arraycopy(array, 0, newArray, 0, size);
		}
		return newArray;
	}

	//Add +
	public void add(HugeNumber input) {
		int max= Integer.max(input.array.length, array.length);
		array=resizeArray(array, max, array.length-1);
		for(int i=0;i<input.array.length; i++) {
			array[i]+=input.array[i];
			// for(int j=0;array[max-1-i-j] >= 10;j++) {
			// 	array[max-1-i-j]=0;
			// 	array[max-1-i-1-j]=array[max-1-i-1-j] + 1;
			// }
		}
	}

	//Subtract -
	//Multiplie *
	//Divide /
	//Remainder %

	public static void main(String arguments[]) {
		HugeNumber test=new HugeNumber(1398);//8931
		// System.out.println(test.getLength());
		test.print(false);
		test.add(new HugeNumber("110"));//011
		// test.print(false);
		// test.add(new HugeNumber("110"));
		// test.print(false);
		// test.add(new HugeNumber("50000"));
		test.print(false);
	}
}

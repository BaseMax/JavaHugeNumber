package com.asrez;

import org.omg.CORBA.INTERNAL;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;

public class HugeNumber {
	public ArrayList<Integer> array=new ArrayList<Integer>();


	public void clear() {
		array=new ArrayList<Integer>();
	}
	HugeNumber(String input) {
		if(input == null || input.length() == 0) {
			System.err.println("Error: input of constructor is empty!");
			return;
		}
		int i;
		clear();
		for(i=input.length()-1;i>=0;i--) {
			if(Character.isDigit(input.charAt(i))) {
				array.add(Integer.parseInt(Character.toString(input.charAt(i))));
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
		clear();
		while(input != 0) {
			array.add(input % 10);
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
		return array.size();
	}

	public ArrayList getArray() {
		return array;
	}

	public void print() {
		print(true);
	}

	public void print(boolean split) {
		if(split) {
			for(int i = array.size()-1; i >= 0; i--) {
				System.out.print(array.get(i) + " ");
			}
		}
		else {
			for(int i = array.size()-1; i >= 0; i--) {
				System.out.print(array.get(i));
			}
		}
		System.out.println();
	}

	public String result() {
		String result="";
		for(int i = array.size()-1; i >= 0; i--) {
			result+=array.get(i);
		}
		return result;
	}

	public String __toString() {
		return result();
	}

	//Add +
	public void add(HugeNumber input) {
		int max= Integer.max(input.array.size(), array.size());
		for(int i=0;i<input.array.size(); i++) {
			if(i >= array.size()) {
				array.add(input.array.get(i));
			}
			else {
				array.set(i, (int)array.get(i) + (int)input.array.get(i));
			}
			for(int j=0;array.get(i+j) >= 10;j++) {
				array.set(i+j, 0);
				if(i+j+1 >= array.size()) {
					array.add(1);
				}
				else {
					array.set(i+j+1, array.get(i+j+1) + 1);
				}
			}
		}
	}

	//Subtract -
	public void subtract(HugeNumber input) {
		if(input.array.size() > array.size()) {
			System.err.println("Error: input of subtract is larger from your number!");
			return;
		}
		for(int i=0;i<input.array.size(); i++) {
			int result=(int)array.get(i) - (int)input.array.get(i);
			array.set(i, result);
			if(result < 0) {
				array.set(i, 9 - result -1);
				array.set(i+1, array.get(i+1) - 1);
			}
			// for(int j=0;array.get(i+j) >= 10;j++) {
			// 	array.set(i+j, 0);
			// 	if(i+j+1 >= array.size()) {
			// 		array.add(1);
			// 	}
			// 	else {
			// 		array.set(i+j+1, array.get(i+j+1) + 1);
			// 	}
			// }
		}
		for(int i=array.size()-1;i >= 0; i--) {
			if(array.get(i) != 0) {
				break;
			}
			array.remove(i);
		}
	}

	//Multiplie *
	//Divide /
	//Remainder %

	public static void main(String arguments[]) {
		HugeNumber test=new HugeNumber(1398+50000);//8931
		// System.out.println(test.getLength());
		test.print(false);
		test.add(new HugeNumber("110"));//011
		// test.print(false);
		// test.add(new HugeNumber("110"));
		// test.print(false);
		test.add(new HugeNumber("50000"));
		test=new HugeNumber(96);//69
		test.subtract(new HugeNumber("95"));
		test.print(false);
	}
}

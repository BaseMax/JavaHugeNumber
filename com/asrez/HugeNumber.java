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
	public void add(Integer input) {
		add(new HugeNumber(input));
	}

	public void add(HugeNumber input) {
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
	public void subtract(Integer input) {
		subtract(new HugeNumber(input));
	}

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
		}
		for(int i=array.size()-1;i >= 0; i--) {
			if(array.get(i) != 0) {
				break;
			}
			array.remove(i);
		}
	}

	//Multiplie *
	public void multiplie(Integer input) {
		multiplie(new HugeNumber(input));
	}

	public void multiplie(HugeNumber input) {
		// 6*6=36<>63
		// 69*6=414<>96*6=414
		// Why: We did used power operation only for `array` variable...
		if(input.array.size() > array.size()) {
			ArrayList<Integer> temp1=array;
			ArrayList<Integer> temp2=input.array;
			array=input.array;
			input.array=temp1;
			// input.array=array;
			// array=test.array;
			// input.array=temp;
			// input=test;
		}
		HugeNumber result=new HugeNumber(0);
		// for(int i=0;i<input.array.size(); i++) {
		for(int i=array.size()-1;i >= 0; i--) {
			// System.out.println("ItemTest: "+ array.get(i));
			// for(int k=0; k < input.array.size(); k++) {
			for(int k=input.array.size()-1; k >= 0; k--) {
				int index1=i;
				int index2=k-input.array.size()+2;
				// System.out.println(( array.get(i) * (int)Math.pow(10, index1)) +" / " + ( input.array.get(k) * (int)Math.pow(10, index2)));
				// System.out.println(array.get(i) +" / " + ( input.array.get(k) * (int)Math.pow(10, k)));
				// System.out.println("Item: "+ array.get(i) + " / " + ( input.array.get(k) * 1));
				int res=(array.get(i) * (int)Math.pow(10, index1)) * (input.array.get(k) * (int)Math.pow(10, index2));
				// System.out.println("--->"+res);
				result.add(res);
				// int result=input.array.get(i) * (array.get(k) * 1);
				// for(int j=0;result%10 != 0; j++) {
				// 	System.out.println(result%10);
				// 	// if(i+j >= array.size()) {
				// 	// 	array.add(result%10);
				// 	// }
				// 	// else {
				// 	// 	array.set(i+j, result%10);
				// 	// }
				// 	result/=10;
				// }
			}
		}
		array=result.array;
	}

	//Divide /
	//Remainder %

	public static void main(String arguments[]) {
		HugeNumber test;
		// test=new HugeNumber(1398+50000);//8931
		// // System.out.println(test.getLength());
		// test.print(false);
		// test.add(new HugeNumber("110"));//011
		// // test.print(false);
		// // test.add(new HugeNumber("110"));
		// // test.print(false);
		// test.add(new HugeNumber("50000"));

		// test=new HugeNumber(96);//69
		// test.subtract(new HugeNumber("95"));
		// // test.print(false);

		// test=new HugeNumber(6);//69
		// test.multiplie(new HugeNumber(6));
		// test.print(false);

		// test=new HugeNumber(69);//96
		// test.multiplie(new HugeNumber("6"));
		// test.print(false);

		// test=new HugeNumber(6);
		// test.multiplie(new HugeNumber("1564"));
		// test.print(false);

		// 1564 × 60 = 93840
		// 1564 × 60 = 93840
		test=new HugeNumber(1564);
		test.multiplie(new HugeNumber(60));
		test.print(false);

		test=new HugeNumber(60);
		test.multiplie(new HugeNumber(1564));
		test.print(false);

		test=new HugeNumber(12);
		test.multiplie(new HugeNumber("600"));
		test.print(false);
	}
}

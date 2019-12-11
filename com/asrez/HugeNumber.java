package com.asrez;
/**
 *
 * @Name : JavaHugeNumber
 * @File : com/asrez/JavaHugeNumber.java
 * @Version : 1.0
 * @Programmer : Max
 * @Date : 2019-12-08, 2019-12-10
 * @Released under : https://github.com/BaseMax/JavaHugeNumber/blob/master/LICENSE
 * @Repository : https://github.com/BaseMax/JavaHugeNumber
 * @Reference : https://github.com/BaseMax/JavaHugeNumber
 *
 **/
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

	HugeNumber(final HugeNumber input) {
		this.array=input.array;
	}

	HugeNumber() {
		array=new ArrayList<Integer>();
	}

	HugeNumber(final ArrayList<Integer> array) {
		this.array=array;
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

	public static int getLength(int input) {
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
			for(int i = array.size()-1;i >= 0;i--) {
				System.out.print(array.get(i) + " ");
			}
		}
		else {
			for(int i = array.size()-1;i >= 0;i--) {
				System.out.print(array.get(i));
			}
		}
		System.out.println();
	}

	public String result() {
		String result="";
		for(int i = array.size()-1;i >= 0;i--) {
			result+=array.get(i);
		}
		return result;
	}

	public String __toString() {
		return result();
	}

	// Addition
	// Add +
	public void add(Integer input) {
		add(new HugeNumber(input));
	}

	public void add(HugeNumber input) {
		for(int i=0;i<input.array.size();i++) {
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

	// Subtraction
	// Subtract -
	public void subtract(Integer input) {
		subtract(new HugeNumber(input));
	}

	public void subtract(HugeNumber input) {
		if(input.array.size() > array.size()) {
			System.err.println("Error: input of subtract is larger from your number!");
			return;
		}
		for(int i=0;i<input.array.size();i++) {
			int result=(int)array.get(i) - (int)input.array.get(i);
			array.set(i, result);
			if(result < 0) {
				array.set(i, 10+result);
				array.set(i+1, array.get(i+1) - 1);
			}
		}
		for(int i=array.size()-1;i >= 0;i--) {
			if(array.get(i) != 0) {
				break;
			}
			array.remove(i);
		}
		if(array.isEmpty()) {
			array.add(0);
		}
	}

	// Multiplication
	// Multiplie *
	public void multiplie(Integer input) {
		multiplie(new HugeNumber(input));
	}

	public void multiplie(HugeNumber input) {
		if(input.array.size() > array.size()) {
			ArrayList<Integer> temp1=array;
			array=input.array;
			input.array=temp1;
		}
		HugeNumber result=new HugeNumber();
		for(int i=array.size()-1;i >= 0;i--) {
			for(int k=input.array.size()-1;k >= 0;k--) {
				int index1=i;
				int index2=k-input.array.size()+2;
				int res=(array.get(i) * (int)Math.pow(10, index1)) * (input.array.get(k) * (int)Math.pow(10, index2));
				result.add(res);
			}
		}
		array=result.array;
		if(array.isEmpty()) {
			array.add(0);
		}
	}

	// Division
	// Divide /
	public void divide(Integer input) {
		divide(new HugeNumber(input));
	}

	public void divide(HugeNumber input) {
		String result = "";
		int divisor=Integer.parseInt(input.result());
		int number = array.size()-1;
		int temp = array.get(number);
		while(temp < divisor) {
			number--;
			temp = temp * 10 + (array.get(number));
		}
		number--;
		while(number >= -1) {
			result += (temp / divisor);
			int index=number--;
			if(index > -1) {
				temp = (temp % divisor) * 10 + array.get(index);
			}
		}
		HugeNumber answer=new HugeNumber(result);
		array=answer.array;
	}

	// Modulus
	public void modulus(Integer input) {
		modulus(new HugeNumber(input));
	}

	public void modulus(HugeNumber input) {
		HugeNumber temp=new HugeNumber(array);
		divide(input);
		multiplie(input);
		temp.subtract(this);
		array=temp.array;
	}

	public boolean setLastIndex(Integer index, Integer input) {
		if(array.size()-1 >= index) {
			if(index >= array.size()) {
				array.add(input);
			}
			else {
				array.set(index, input);
			}
			return true;
		}
		return false;
	}

	public boolean setIndex(Integer index, Integer input) {
		int size=array.size();
		// 0 .. 9
		// 9 - 0 = 9
		index=(size-1)-index;
		if(size-1 >= index) {
			if(index >= size) {
				array.add(input);
			}
			else {
				array.set(index, input);
			}
			return true;
		}
		return false;
	}

	public Integer getLastIndex(Integer index) {
		if(array.size()-1 >= index) {
			return array.get(index);
		}
		return -1;// false
	}

	public Integer getIndex(Integer index) {
		int size=array.size();
		// 0 .. 9
		// 9 - 0 = 9
		index=(size-1)-index;
		if(size-1 >= index) {
			return array.get(index);
		}
		return -1;// false
	}

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
		// test=new HugeNumber(1564);
		// test.multiplie(new HugeNumber(60));
		// test.print(false);

		// test=new HugeNumber(60);
		// test.multiplie(new HugeNumber(1564));
		// test.print(false);

		test=new HugeNumber("8750");
		test=new HugeNumber("0578");
		test=new HugeNumber("1248163264128256512");
		test=new HugeNumber("321456");
		// test.multiplie(new HugeNumber("600"));
		// test.print(false);
		// test.divide(125);
		// test.divide(16);//20091
		test.modulus(31711);
		test.setIndex(0, 9);
		test.setLastIndex(0, 8);
		test.print(false);

		// test=new HugeNumber(321456);
		// test.subtract(317110);
		// test.print(false);

		// test=new HugeNumber(60);
		// test.subtract(59);
		// test.print(false);

		// test=new HugeNumber(60);
		// test.subtract(60);
		// test.print(false);

		// test=new HugeNumber(60);
		// test.multiplie(0);
		// test.print(false);
	}
}

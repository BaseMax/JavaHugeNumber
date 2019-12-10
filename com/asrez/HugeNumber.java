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

	//Add +
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

	//Subtract -
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
				array.set(i, 9 - result -1);
				array.set(i+1, array.get(i+1) - 1);
			}
		}
		for(int i=array.size()-1;i >= 0;i--) {
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
		if(input.array.size() > array.size()) {
			ArrayList<Integer> temp1=array;
			array=input.array;
			input.array=temp1;
		}
		HugeNumber result=new HugeNumber(0);
		for(int i=array.size()-1;i >= 0;i--) {
			for(int k=input.array.size()-1;k >= 0;k--) {
				int index1=i;
				int index2=k-input.array.size()+2;
				int res=(array.get(i) * (int)Math.pow(10, index1)) * (input.array.get(k) * (int)Math.pow(10, index2));
				result.add(res);
			}
		}
		array=result.array;
	}

	//Divide /
	public void divide(Integer input) {
		divide(new HugeNumber(input));
	}

	static String test_div(String input, int divisor) {
		String ans = "";
		int number = 0;
		char []num = input.toCharArray();
		int temp = num[number] - '0';
		while(temp < divisor) 
			temp = temp * 10 + (num[++number] - '0');
		number +=1;
		while(num.length > number) {
			ans += (temp / divisor);
			temp = (temp % divisor) * 10 + num[number++] - '0';
		}
		if(ans.length() == 0) 
			return "0";
		return ans;
	}

	public void divide(HugeNumber input) {
		// 960 ÷ 6 = 160
		if(input.array.size() > array.size()) {
			System.err.println("Error: input of divide is larger from your number!");
			return;
		}
		HugeNumber result=new HugeNumber(0);
		for(int i=array.size()-1;i >= 0;i--) {
			for(int k=input.array.size()-1;k >= 0;k--) {
				int index1=i;
				int index2=k-input.array.size()+2;
				int res=(array.get(i) * (int)Math.pow(10, index1)) * (input.array.get(k) * (int)Math.pow(10, index2));
				result.add(res);
			}
		}
		array=result.array;
	}
	//Remainder %

	public String test_div_test(int divisor) {
		int[] array={1,2,4,8,1,6,3,2,6,4,1,2,8,2,5,6,5,1,2};
		String result = "";
		int number = 0;
		int temp = array[number];
		while(temp < divisor)
			temp = temp * 10 + (array[++number]);
		number++;
		while(array.length > number) {
			result += (temp / divisor);
			temp = (temp % divisor) * 10 + array[number++];
		}
		if(result.length() == 0) 
			return "0";
		return result;
	}

	public String test_div_test_final(int divisor) {
		// int number = 0;
		int number = array.size()-1;
		// System.out.println("#1:getIndex: " + number);
		int temp = array.get(number);
		while(temp < divisor) {
			// number++;
			number--;
			// System.out.println("#2:getIndex: " + number);
			temp = temp * 10 + (array.get(number));
		}
		// number++;
		number--;
		String result = "";
		// while(array.size() > number) {
		while(number >= 0) {
			result += (temp / divisor);
			// System.out.println("#3:getIndex: " + number);
			temp = (temp % divisor) * 10 + array.get(number);
			// number++;
			number--;
		}
		if(result.length() == 0) 
			return "0";
		return result;
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
		// test.multiplie(new HugeNumber("600"));
		// test.print(false);
		// System.out.println(longDivision("1564", 60));
		System.out.println(test.longDivision(125));
		System.out.println(test.longDivisionTest(125));
	}
}

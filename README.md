# JavaHugeNumber

# Java Huge Number

Big and Huge Number/Integer class implementation with supporting arithmetic operations + - * / %.

## Java Big Number

### Features

- No length limit
- Addition Operator
- Subtraction Operator
- Multiplication Operator
- Division Operator
- Modulus Operator

## Java Huge Number

### TODO

- [ ] Supports negative numbers
- [ ] Supports decimal numbers
- [ ] Improve

## Java Huge Decimal

### Example

```java
HugeNumber test;
test=new HugeNumber(1398+50000);//8931
// System.out.println(test.getLength());
test.print(false);
test.add(new HugeNumber("110"));//011
test.print(false);
test.add(new HugeNumber("110"));
test.print(false);
test.add(new HugeNumber("50000"));

test=new HugeNumber(96);//69
test.subtract(new HugeNumber("95"));
test.print(false);

test=new HugeNumber(6);//69
test.multiplie(new HugeNumber(6));
test.print(false);

test=new HugeNumber(69);//96
test.multiplie(new HugeNumber("6"));
test.print(false);

test=new HugeNumber(6);
test.multiplie(new HugeNumber("1564"));
test.print(false);

1564 × 60 = 93840
1564 × 60 = 93840
test=new HugeNumber(1564);
test.multiplie(new HugeNumber(60));
test.print(false);

test=new HugeNumber(60);
test.multiplie(new HugeNumber(1564));
test.print(false);

test=new HugeNumber("8750");
test=new HugeNumber("0578");
test=new HugeNumber("1248163264128256512");
test=new HugeNumber("321456");
test.multiplie(new HugeNumber("600"));
test.print(false);
test.divide(125);
test.divide(16);//20091
test.modulus(31711);//20091
test.print(false);

test=new HugeNumber(321456);
test.subtract(317110);
test.print(false);

test=new HugeNumber(60);
test.subtract(59);
test.print(false);

test=new HugeNumber(60);
test.subtract(60);
test.print(false);

test=new HugeNumber(60);
test.multiplie(0);
test.print(false);
```

### Build

```
javac com/asrez/HugeNumber.java
java com.asrez.HugeNumber
```

---------

# Max Base

My nickname is Max, Programming language developer, Full-stack programmer. I love computer scientists, researchers, and compilers.

## Asrez Team

A team includes some programmer, developer, designer, researcher(s) especially Max Base.

[Asrez Team](https://www.asrez.com/)

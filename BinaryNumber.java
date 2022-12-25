/**
 * CS 570 WS
 * Coding Assignment 1 Binary Numbers
 * Kuo Zhang
 */

import java.util.ArrayList;
import java.util.Arrays;


/**
 * BinaryNumber class
 * For the toDecimal method I used little endian to convert binary to decimal.
 */
public class BinaryNumber {

    //Field with DATA array and overflow flag
    private int[] data;
    private boolean overflow;

    /**
     * Constructor with Input array length create a binary number only consisting with 0;
     * with validation if length is positive number;
     *
     * @param length
     */
    public BinaryNumber(int length) {
        if (length < 0) {
            System.out.println("Please enter positive value for length");
        } else {
            this.data = new int[length];
            for (int i = 0; i < length; i++) {
                this.data[i] = 0;
            }
            System.out.println(Arrays.toString(data));
        }

    }

    /**
     * Constructor take input String convert back to int array and validation if input is binary name (1 and 0);
     *
     * @param str
     */
    public BinaryNumber(String str) {
        this.data = new int[str.length()];
        if (str instanceof String) {
            for (int i = 0; i < str.length(); i++) {
                int binNum = Character.getNumericValue(str.charAt(i));
                this.data[i] = binNum;
            }
            for (int index : this.data) {
                if (index != 0 && index != 1) {
                    System.out.println("Please enter valid binary number 1 or 0.");
                    System.exit(0);
                }
            }
        } else {
            System.out.println("Please enter valid string");
            System.exit(0);
        }
    }

    //method get binary number's length
    public int getLength() {
        return data.length;
    }

    //method get value of index. with validation if index is out of bounds;
    public int getDigit(int index) {

        if (!(index < data.length)) {
            System.out.println("Index out of bounds");
            System.exit(0);
        } else if (index < 0) {
            System.out.println("Please positive number for index.");
            System.exit(0);
        }
        return data[index];
    }


    //Shift right method add amount * 0 and leftmost. Implement by arraylist;
    public void shiftR(int amount) {
        ArrayList<Integer> shift0 = new ArrayList<>();
        if (amount < 0) {
            System.out.println("Please enter a positive amount to shift");
        } else {
            for (int i = 0; i < amount; i++) {
                shift0.add(i, 0);
            }
            for (int datum : data) {
                shift0.add(datum);
            }
            System.out.println(shift0);
        }
    }

    /**
     * Binary number addition. With validation if given two binary numbers are same length.
     *
     * @param aBinaryNumber
     */
    public void add(BinaryNumber aBinaryNumber) {
        // length validation
        if (data.length != aBinaryNumber.getLength()) {
            System.out.println("Length of numbers to be added is not same.");
        } else {
            int carry = 0; // Carry digits.

            //For loop for adding every digit together from the leftmost.
            for (int i = 0; i < data.length; i++) {
                int tempResult = data[i] + aBinaryNumber.getDigit(i) + carry;

                /**
                 * Condition while adding two binary number.
                 * 3 result 1 carry 1, 2 result 0 carry 1, 1 result 1 carry 0, 0 result 0 carry 0;
                 */
                if (tempResult == 2 && i == data.length - 1) {  //result 2 but at the last digit of binary number
                    data[i] = 0;
                    data = Arrays.copyOf(data, data.length + 1);
                    data[data.length - 1] = 1;
                    carry = 1;
                    break;
                } else if (tempResult == 2) {  //result 2
                    data[i] = 0;
                    carry = 1;
                } else if (tempResult == 3 && i == data.length - 1) { //result 3 but at the last digit of binary number
                    data[i] = 1;
                    data = Arrays.copyOf(data, data.length + 1);
                    data[data.length - 1] = 1;
                    carry = 1;
                    break;
                } else if (tempResult == 3) { //result 3
                    data[i] = 1;
                    carry = 1;
                } else { // rest result
                    data[i] = tempResult;
                    carry = 0;
                }

            }
//            System.out.println(Arrays.toString(data)); //Testing

            //After loop if carry not equal to 0 , overflow flag mark as true.
            if (carry != 0) {
                overflow = true;
            }
        }

    }

    /**
     * toString method return true if there is overflow.
     *
     * @return
     */
    @Override
    public String toString() {
        if (overflow) {
            return "BinaryNumber{" +
                    " data = " + Arrays.toString(data) +
                    ", overflow = " + true +
                    " }";
        } else {
            return "BinaryNumber{" +
                    " data = " + Arrays.toString(data) + " There is no overflow in this addition }";
        }
    }

    /**
     * toDecimal method. Implement with little endian from left to the right.
     *
     * @return Decimal number by little endian;
     */
    public int toDecimal() {
        int num1 = 0;
        int power = 0;
        for (Integer num : this.data) {
            num1 = (int) (num1 + (num * (Math.pow(2, power))));
            power++;
        }
        return num1;
    }

    //method reset overflow flag;
    public void clearOverflow() {
        if (overflow) {
            overflow = false;
        }
    }


    public static void main(String[] args) {
//        for testing;
        BinaryNumber a = new BinaryNumber("10110");
        BinaryNumber b = new BinaryNumber("11101");
//        BinaryNumber c = new BinaryNumber(-1);
//        System.out.println(a.getLength());
//        System.out.println(b.getLength());
//        System.out.println(a.toDecimal());
//        System.out.println(b.toDecimal());
//        System.out.println(a.getDigit(0));
//        System.out.println(b.getDigit(3));
//        a.shiftR(1);
//        b.shiftR(0);
//        a.add(b);
//        System.out.println(a.toString());

//        BinaryNumber d = new BinaryNumber("abc");
//        BinaryNumber e = new BinaryNumber("-1");
//        BinaryNumber f = new BinaryNumber(10);
//        System.out.println(d.getDigit(0));
//        System.out.println(e.getDigit(0));
//        System.out.println(d.getLength());
//        System.out.println(e.getLength());
//        System.out.println(d.toDecimal());
//        System.out.println(e.toDecimal());
//        d.shiftR(-1);
//        e.shiftR(4);
//        d.add(e);
//        System.out.println(d.toString());
    }
}

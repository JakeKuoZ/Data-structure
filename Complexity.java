
/**
 * @author Kuo Zhang
 */
public class Complexity {
    //o(n) one for loop
    public static void method0(int n) {
        int counter = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("Operation" + counter);
            counter++;
        }
    }

    //o(n^2) two for loop
    public static void method1(int n) {
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Operation" + counter);
                counter++;
            }
        }
    }

    //o(n^3) three for loop
    public static void method2(int n) {
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    System.out.println("Operation" + counter);
                    counter++;
                }
            }
        }
    }

    //o(logn) find log
    public static void method3(int n) {
        int counter = 0;
        while (n > 1) {
            System.out.println("Operation" + counter);
            counter++;
            n = n / 2;
        }
    }

    //o(nlogn) log base 2 * n
    public static void method4(int n) {
        int counter = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < n; j = j * 2) {
                System.out.println("Operation" + counter);
                counter++;
            }
        }
    }

    //o(loglogn) log(log(n))
    public static void method5(int n) {
        int counter = 0;
        int i = 2;
        while (i < n) {
            System.out.println("operation " + counter);
            counter += 1;
            i = i * i;
        }
    }

    //o(2^n)  Fibonacci number has complexity n^2
    public static int methodBouns(int n) {
        if (n <= 1) {
            return n;
        }
        return methodBouns(n - 1) + methodBouns(n - 2);
    }


    public static void main(String[] args) {


        method0(8);
        // method1(8);
        //method2(8);
        //method3(8);
        //method4(8);
        // method5(8);
        //methodBouns(8);
    }
}


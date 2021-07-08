package main;

public class PrimeNumber {

    public static void main (String args[]) {

        long startTime = System.nanoTime();

        isPrime(0b11000011010100000110000110101000);

        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);

    }

    /**
     * Check if given number is a prime-number
     * @param number
     * @return false if number is not a prime-number
     * @return true  if number is a prime-number
     */
    public static boolean isPrime(int number) {

        int i = 2;

        while(i <= (number / 2) ) {
            if (number % i == 0) {
                return false;
            }
            i++;
        }
        return true;
    }

}

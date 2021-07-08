import java.util.ArrayList;

/**
 * A simple class to calculate prime numbers.
 * 
 * @author hjawish , lcarmohn
 * @version 0.1
 */
public class Prime
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private ArrayList<String> primes;

    /**
     * Constructor for Prime
     */
    public Prime() {
        primes = new ArrayList<String>();
    }
    
    /**
     * Print all prime numbers until the maxNumber that is given
     * @param maxNumber for returning the primes.
     */
    public void countPrimeUntilOneThousand() {

      int number = 1; 
    
      while (number > 0 && number <= 1000) { 
        
          isPrime(number);
        
        if(isPrime (number) && number != 1) {
            
           primes.add(Integer.toString(number));
           System.out.println("number: " + number);
           
        }
        
        number++;
        
      }
    }
    
    
    /**
    * A simple method that calculate if the given number is prime
    * @param numberToCheck
    * @return true if number is prime,
    * @return false if number is not prime,
    */
    public boolean isPrime(int numberToCheck) {
    
       boolean isPrime = false;
       int i = 2;
    
       while (i <= numberToCheck / 2) {
           if (numberToCheck % i == 0) {
               isPrime = true;
               break;
           }
           ++i;
       }
       return isPrime;
    }

    public void printCountOfPrimes() {
        int index = 0;
        while (index < primes.size()) {
            System.out.println(primes.get(index));
            index++;
        }
        System.out.println("There are " + primes.size() + " prime numbers in your list.");
    }
}

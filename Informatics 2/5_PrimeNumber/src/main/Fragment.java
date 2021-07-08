package main;

public class Fragment {

    public static void main(String args[]) {

        int n = 10000; //10 100, 1000, , 100000
        int sum = 0;

        long startTime = System.nanoTime();

        fragmentExecutor(1, n, sum);

        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);

    }

    /**
     * Execute a fragment algorithm with given number
     * @param fragmentNumber  number of fragment
     * @param n value of data
     * @param sum
     */
    private static void fragmentExecutor (int fragmentNumber, int n, int sum) {

        switch(fragmentNumber){
            case 1:
                // main.Fragment #1
                for (int i = 0; i < n; i++)
                    sum++;
                break;
            case 2:
                // main.Fragment #2
                for ( int i = 0; i < n; i ++)
                    for ( int j = 0; j < n; j ++)
                        sum++;
                break;
            case 3:
                // main.Fragment #3
                for ( int i = 0; i < n; i ++)
                    for ( int j = i; j < n; j ++)
                        sum++;
                break;
            case 4:
                // main.Fragment #4
                for ( int i = 0; i < n; i ++)
                    sum ++;
                for ( int j = 0; j < n; j ++)
                    sum ++;
                break;
            case 5:
                // main.Fragment #5
                for ( int i = 0; i < n; i ++)
                    for ( int j = 0; j < n*n; j ++)
                        sum++;
                break;
            case 6:
                // main.Fragment #6
                for ( int i = 0; i < n; i ++)
                    for ( int j = 0; j < i; j ++)
                        sum++;
                break;
            case 7:
                // main.Fragment #7
                for ( int i = 1; i < n; i ++)
                    for ( int j = 0; j < n*n; j ++)
                        if (j % i == 0)
                            for (int k = 0; k < j; k++)
                                sum++;
                break;
            case 8:
                // main.Fragment #8
                int i = n;
                while (i > 1) {
                    i = i / 2;
                    sum++;
                }
                break;
            default:
                System.out.println("Please choose a number between 1-8");
                break;
        }
    }
}



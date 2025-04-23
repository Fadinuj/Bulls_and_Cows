import java.util.*;

/**
 * Introduction to Computer Science, Ariel University, Ex1 (manual Example + a Template for your solution)
 * See: <a href="https://docs.google.com/document/d/1C1BZmi_Qv6oRrL4T5oN9N2bBMFOHPzSI/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true">...</a>
 * <p>
 * Ex1 Bulls & Cows - Automatic solution.
 * **** Add a general readme text here ****
 * Add your explanation here:
 * <p>
 *
 * **** General Solution (algorithm) ****
 * Add your explanation here: In this algorithm we take boolean array with length 10 ^ num of digits and started with true
 * every time they send to the game guess we take the res(Bulls and Cows) and we compare the guess with matrix boundaries in
 * myBPTest ,he returned res (Bulls and Cows) if Bulls or Cows that returned from myBPTest differ from res that returned from the game
 * remove this limit(its value changes to false)and comeback to autoEx1Game and send to the game new guees that the stayed true and repeats
 * practical filter until the array have one limit that have true (if the number we want to guess was not one of the first guesses)
 * <p>
 *
 *
 * **** Results ****
 * Make sure to state the average required guesses
 * for 2,3,4,5,6-digit code:
 * <p>
 * Average required guesses 2: 7.07
 * Average required guesses 3: 8.07
 * Average required guesses 4: 8.84
 * Average required guesses 5: 9.27
 * Average required guesses 6: 9.67
 *
 */
public class Ex1 {
    public static final String Title = "Ex1 demo: manual Bulls & Cows game";


    public static void main(String[] args) {
        BP_Server game = new BP_Server();   // Starting the "game-server"
        long myID = 214766339;             // Your ID should be written here
        int numOfDigits = 6;                // Number of digits [2,6]
        game.startGame(myID, numOfDigits);// Starting a game
        System.out.println(Title + " with code of " + numOfDigits + " digits");
        autoEx1Game(game,numOfDigits);
    }
    public static void manualEx1Game(BP_Server game) {
        Scanner sc = new Scanner(System.in);
        int ind = 1;      // Index of the guess
        int numOfDigits = game.getNumOfDigits();
        double max = Math.pow(10, numOfDigits);
        while (game.isRunning()) {           // While the game is running (the code has not been found yet
            System.out.println(ind + ") enter a guess: ");
            int g = sc.nextInt();
            if (g >= 0 && g < max) {
                int[] guess = toArray(g, numOfDigits); // int to digit array
                int[] res = game.play(guess);// Playing a round and getting the B,C
                if (game.isRunning()) {     // While the game is running
                    System.out.println(ind + ") B: " + res[0] + ",  C: " + res[1]); // Prints the Bulls [0], and the Cows [1]
                    ind += 1;               // Increasing the index
                }
            } else {
                System.out.println("ERR: wrong input, try again");
            }
        }
        System.out.println(game.getStatus());
    }
    /**
     * Simple parsing function that gets an int and returns an array of digits
     *
     * @param a    - a natural number (as a guess)
     * @param size - number of digits (to handle the 00 case).
     * @return an array of digits
     */
    private static int[] toArray(int a, int size) {
        int[] c = new int[size];
        for (int j = 0; j < c.length; j += 1) {
            c[j] = a % 10;
            a = a / 10;
        }
        return c;
    }

    /**
     * This function solves the Bulls & Cows game automatically.
     * You should implement
     * **** Add a specific explanation for each function ****
     */
    public static void autoEx1Game(BP_Server game,int numOfDigits) {
        int[] res;
        int[] guess;
        double size = Math.pow(10,numOfDigits);
        boolean[] arr=new boolean[(int) size];// for example if num of digits was 2 the guess can be from {0,0}-{9,9} (100)
        Arrays.fill(arr,true);// We started all the  array with true
        while (game.isRunning())
        {
            guess=toArray(findMeA_ValidOption(arr),numOfDigits); //After we have the first true i, cange the i to be array
             res=game.play(guess); //Start the game with the guess
            Ex1Test.counter++;
             filter(arr,guess,res,numOfDigits); // In filter we cancel the similar capabilities of guess
        }
        System.out.println(game.getStatus());
    }

    /**
     *
     * @param arr The boolean array
     * @return The first i that have true after the filter
     */
    public static int findMeA_ValidOption(boolean[] arr)
    {
        int ans=-1;
        for (int i = 0; i < arr.length; i=i+1) {
            if (arr[i])
                ans = i;
        }
        return ans;

        }

    /**
     * we filter it and cancel the similar capabilities
     * @param arr The boolean array
     * @param guess The guess that we are send to start a game
     * @param res The result that back from the game when we send guess
     * @param numOfDigits The num of digits of the number in the game
     */

    public static void filter(boolean[]arr, int[]guess, int[]res, int numOfDigits)
    {
        int[] curr;
        int[] mr;
        for (int i = 0; i < arr.length; i=i+1) {
            if (arr[i]){
                 curr=toArray(i,numOfDigits); // send i to toArray to change the i to be array and save it in curr
                 mr=myBPTest(curr,guess); //myBPTest return res that have int[] with lenght(2) and we save it in array
                if(mr[0]!=res[0]||mr[1]!=res[1]) // if bulls or cows different than res we are canceled i
                    arr[i]=false;
            }
        }
    }

    /**
     * In myBPTest we check the curr compare the guess and we return the bulls and cows
     * @param curr The i that we want to check with the guess
     * @param guess The attempt that we sent it to the game
     * @return The return in myBPTest is res Bulls and Cows
     */
    public static int[]myBPTest(int[]curr,int[] guess)
    {
        int[] res = new int[2];// Index 0 for Bulls, Index 1 for Cows
        int[] guessCopied;
        guessCopied=Arrays.copyOf(guess,guess.length);
        // Matching numbers in the same position (Bulls)
        for (int i = 0; i < curr.length; i++) {
            if (curr[i] == guess[i]) {
                res[0]++;
                guessCopied[i]=-1;
                curr[i]=-2;
            }
        }
        // Check for matching numbers in different positions (Cows)
            for (int x = 0; x < guessCopied.length; x++) {
                for (int j = 0; j < guessCopied.length ; j++) {
                    if (x!=j&&curr[j]==guessCopied[x]) {
                        res[1]++;
                        curr[j]=-3;
                        guessCopied[x]=-4;
                    }
                }
            }
            return res;
    }

    /**
     *
     * @param numOfDigits The number of digits that we want to do average
     * @return The Average Rounds for 100 rounds for numOfDigits 2-6
     */
    public static double averageGuesses( int numOfDigits)
    {
        BP_Server game = new BP_Server();   // Starting the "game-server"
        for (int i = 0; i <100 ; i++) {
            long myID = 123456789L;             // Your ID should be written here
            game.startGame(myID, numOfDigits);// Starting a game
            System.out.println(Title + " with code of " + numOfDigits + " digits");
            autoEx1Game(game,numOfDigits);
        }
        Ex1Test.totalAvg=Ex1Test.counter/100; //The total average all the attempt / 100
        Ex1Test.counter=0; //After calculating the average the counter back to 0 for checking different number of digits
        return Ex1Test.totalAvg;

    }
}





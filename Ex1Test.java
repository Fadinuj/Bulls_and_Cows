import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.assertTrue;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Ex1Test {
    public static double counter=0;
    public static double totalAvg=0;
    @Test
    @Order(1)
    /**
     * Test the correctness of the avg guesses running the game 100 times for 2 digits
     */
    public void testAvgGuessesFor2Digits()
    {
        double average2Digits=Ex1.averageGuesses(2);
        System.out.println("The Average Rounds for 100 rounds for 2 digits is:"+average2Digits);
        assertTrue(average2Digits<10);
    }
    @Test
    @Order(2)
    /**
     *       Test the correctness of the avg guesses running the game 100 times for 3 digits
     */
    public void testAvgGuessesFor3Digits()
    {
        double average3Digits=Ex1.averageGuesses(3);
        System.out.println("The Average Rounds for 100 rounds for 3 digits is:"+average3Digits);
        assertTrue(average3Digits<10);
    }
    @Test
    @Order(3)
    /**
     *       Test the correctness of the avg guesses running the game 100 times for 4 digits
     */
    public void testAvgGuessesFor4Digits()
    {
        double average4Digits=Ex1.averageGuesses(4);
        System.out.println("The Average Rounds for 100 rounds for 4 digits is:"+average4Digits);
        assertTrue(average4Digits<10);
    }
    @Test
    @Order(4)
    /**
     *       Test the correctness of the avg guesses running the game 100 times for 5 digits
     */
    public void testAvgGuessesFor5Digits()
    {
        double average5Digits=Ex1.averageGuesses(5);
        System.out.println("The Average Rounds for 100 rounds for 5 digits is:"+average5Digits);
        assertTrue(average5Digits<10);
    }
    @Test
    @Order(5)
    /**
     *       Test the correctness of the avg guesses running the game 100 times for 6 digits
     */
    public void testAvgGuessesFor6Digits()
    {
        double average6Digits=Ex1.averageGuesses(6);
        System.out.println("The Average Rounds for 100 rounds for 6 digits is:"+average6Digits);
        assertTrue(average6Digits<10);
    }
    @Test
    @Order(6)
    public void averageAllDigits()
    {
        double average=0; //average of tries
        double[] currAverage={0,0,0,0,0}; //average of tries foe each number of digits
        for (int i = 2; i <=6 ; i++) //range of 2-6
        {
            currAverage[i-2]=Ex1.averageGuesses(i); //enter the value to the array
            average+=currAverage[i-2]; //sums up the value

        }
        System.out.println("The average is:");
        for (int i = 0; i <5 ; i++) // print the values
        {
            System.out.println((i+2)+")"+currAverage[i]+" ");
        }
        average=average/5; // calculate the general average
        System.out.println();
        System.out.println("Average all:"+average); //prints the general average
        assertTrue(average<10);
    }

}

package com.teamB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.teamB.Dice;
import org.junit.Test;

public class App16Test {
    Dice test = new Dice();


    @Test
    public void testingRoll() {
        int[] testArray= new int[3];
        testArray[0] = test.rollDice();
        testArray[1] = test.rollDice();
        testArray[2] = test.rollDice();

        assertTrue(testArray[0] > 0 && testArray[0] < 7);
        assertTrue(testArray[1] > 0 && testArray[1] < 7);
        assertTrue(testArray[2] > 0 && testArray[2] < 7);


    }
}

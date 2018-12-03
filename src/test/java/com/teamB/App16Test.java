package com.teamB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.teamB.Dice;
import org.junit.Test;

public class App16Test {
    Dice test = new Dice();


    @Test
    public void testRoll() {
        int[] testNum= new int[3];
        testNum[0] = test.rollDice();
        testNum[1] = test.rollDice();
        testNum[2] = test.rollDice();

        assertTrue(testNum[0] > 0 && testNum[0] < 7);
        assertTrue(testNum[1] > 0 && testNum[1] < 7);
        assertTrue(testNum[2] > 0 && testNum[2] < 7);

        assertEquals(2, test.rollDice());
        assertEquals(3, test.rollDice());


    }
}

package com.teamB;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class App6Test {

    @Test
    public void shouldAnswerWithTrue(){
        Player mike = new Player("Mike", 0);
        Player bryan = new Player("Bryan", 1);
        mike.buyCredits(mike, 100);
        mike.transferCredits(mike, bryan, 5);

        assertTrue(bryan.getCredits() == 5);

    }
}

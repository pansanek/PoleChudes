import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DrumTest {

    @Test
    void TestRoll() {
        int k = roll();
        assertEquals(0,k);
        assertEquals(1,k);
        assertEquals(2,k);
        assertEquals(3,k);
        assertEquals(0,k);
    }

    private int roll() {
        Random random = new Random();
        return  random.nextInt(5);
    }
}
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private char[] progress;
    @Test
    void TestInitializationOfProgress() {
        String Answer = initializationOfProgress(8);
        assertEquals("********", Answer);
    }

    private String initializationOfProgress(int size) {
        //Изначально прогресс отгадывания слова 0 и отображаются только *
        progress = new char[size];
        for (int i = 0; i < progress.length; i++) {
            progress[i] = '*';
        }
        String ProgressString = String.valueOf(progress);
        return ProgressString;
    }
}
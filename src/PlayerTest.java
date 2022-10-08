import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void getName() {
        Player player = new Player("Павел",10);
        Player player2 = new Player("Иван",10);
        assertEquals("Павел",player.getName());
    }

    @Test
    void setName() {
        Player player = new Player("Павел",10);
        player.setName("Иван");
        assertEquals("Иван",player.getName());
    }

    @Test
    void getScore() {
        Player player = new Player("Павел",10);
        assertEquals(10,player.getScore());

    }

    @Test
    void setScore() {
        Player player = new Player("Павел",10);
        player.setScore(300);
        assertEquals(300,player.getScore());
    }

    @Test
    void increaseScore() {
        Player player = new Player("Павел",2);
        player.increaseScore(10);
        assertEquals(12,player.getScore());
    }
}
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {

    @Test
    void getTask() {
        boolean difficult = false;
        Data data;
        data = new Data(difficult);
        String Answer = data.getTask(0);
        assertEquals("Под каким сценическим именем более известна популярная российская певица Дарья Сергеевна Шиханова",Answer);
        assertNotEquals("Фамилия ректора МИРЭА — Российский технологический университет",Answer);
    }

    @Test
    void getAnswer() {
        boolean difficult = false;
        Data data;
        data = new Data(difficult);
        String Answer = data.getAnswer(0);
        assertEquals("дора",Answer);
        assertNotEquals("кудж",Answer);
    }
}
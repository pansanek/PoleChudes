import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Character.toUpperCase;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private char[] progress;
    private Scanner scanner;
    private String answer;
    private Player[] players;
    @Test
    void TestInitializationOfProgress() {
        String Answer = initializationOfProgress(8);
        assertEquals("********", Answer);
    }
    @Test
    void TestPlayerStep() {
        String Answer = playerStep(3);
        assertEquals("Нет такого варианта. Попробуйте еще раз", Answer);
    }

    @Test
    void TestEnterLetter() {
        String Answer = enterLetter('а');
        assertEquals("а*******", Answer);
    }

    /*------------------------------------------------------------------------------------
       Далее идут методы созданные только для тестов, которые будут исправлены в обычном коде
       ------------------------------------------------------------------------------------*/

    private String initializationOfProgress(int size) {
        //Изначально прогресс отгадывания слова 0 и отображаются только *
        progress = new char[size];
        for (int i = 0; i < progress.length; i++) {
            progress[i] = '*';
        }
        String ProgressString = String.valueOf(progress);
        return ProgressString;
    }


    private String enterLetter(Character letter ) {
        int totalEntries = 0;
        answer = "апельсин";
        progress = new char[8];
        for (int i = 0; i < progress.length; i++) {
            progress[i] = '*';
        }
        //Проверяем наличие введенной буква в нашем слове
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == letter || answer.charAt(i) == toUpperCase(letter)) {
                totalEntries++;
                progress[i] = letter;
            }
        }
        String returnn = String.valueOf(progress);
        return returnn;
    }


    private String playerStep(int scan) {
        scanner = new Scanner(System.in);
        //System.out.println("Назовите слово или букву? Введите 1/2");
        int answer = 0;

        //Считываем вариант отгадывания слова
        boolean correct = false;
        while (correct == false) {
            try {
                answer = scan;

                //Если ответ вне диапазона доступных
                if (answer != 1 && answer != 2) {
                    return ("Нет такого варианта. Попробуйте еще раз");
                }
                correct = true;
            } catch (InputMismatchException e) {
                return ("Неправильный параметр. Попробуйте еще раз");
            }
        }
        return null;
    }
}
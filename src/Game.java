import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private int numberOfPlayers;

    private Drum drum;
    private Scanner scanner;


    public void start() {
        Random random = new Random();
        Data data;

        //Барабан
        drum = new Drum();

        //Сканер для получения введенных данных с консоли
        scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать! \nСколько игроков хочет играть?");

        //Получение количества игроков
        boolean correct = false;
        while (correct == false) {
            try {
                numberOfPlayers = scanner.nextInt();
                correct = true;
            } catch (InputMismatchException e) {
                System.out.println("Неправильный ввод. Попробуйте еще раз");
                scanner.next();
                continue;
            }
        }
    }
}

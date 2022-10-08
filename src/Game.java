import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class Game {
    private int numberOfPlayers;
    private Drum drum;
    private Scanner scanner;
    private boolean difficult;
    private Player[] players;
    private String task;
    private String answer;
    private char[] progress;
    private boolean endGame = false;
    private int winner = 0;

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
        //Инициализация массива игроков
        initializationOfPlayers(numberOfPlayers);

        //Определение сложности
        System.out.println("Хотите сложную игру? true/false");
        correct = false;
        while (correct == false) {
            try {
                difficult = scanner.nextBoolean();
                correct = true;
            } catch (InputMismatchException e) {
                System.out.println("Неправильный ввод. Попробуйте еще раз");
                scanner.next();
                continue;
            }
        }

        //Создание объекта, содержащего задания для игры с нужным нам уровнем сложности
        data = new Data(difficult);

        int randomTaskNumber = random.nextInt(3);

        //Получение задания
        task = data.getTask(randomTaskNumber);
        answer = data.getAnswer(randomTaskNumber);

        //Создание строки, в которой будет отображаться прогресс отгадывания слова
        initializationOfProgress(answer.length());

        //Игроки играют пока не настанет конец игры
        while (endGame == false) {

            for (int i = 0; i < players.length; i++) {
                System.out.println("Ваш ход " + players[i].getName());
                System.out.println("Крутите барабан!");

                //Определение выпавшего числа на барабане
                readDrum(drum.roll(), i);
                System.out.println(players[i].getScore() + " очков");

                //Напоминание задания и текущего погресса в слове
                printTaskAndProgress();

                //Ход игрока
                playerStep(i);
            }
        }

        //Конец игры
        System.out.println("Поздравляю " + players[winner].getName() + ", вы победили! Общее количество: " + players[winner].getScore());
        exit(1);
    }

    private void initializationOfPlayers(int number) {
        players = new Player[number];
        Scanner scanner = new Scanner(System.in);

        //Кладем пустые объекты игроков в массив
        for (int i = 0; i < number; i++) {
            players[i] = new Player();
        }

        //Заполняем имена и ставим начальный счет
        for (int i = 0; i < players.length; i++) {
            System.out.println("Введите имя игрока " + (i + 1));
            String name = scanner.next();
            players[i].setName(name);
            players[i].setScore(0);

        }
    }

    private void initializationOfProgress(int size) {
        //Изначально прогресс отгадывания слова 0 и отображаются только *
        progress = new char[size];
        for (int i = 0; i < progress.length; i++) {
            progress[i] = '*';
        }

    }
}

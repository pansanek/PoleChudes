import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Character.toUpperCase;
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
    private void readDrum(int numberOnDrum, int player) {
        switch (numberOnDrum) {
            case 0:
                System.out.println("10 очков!");
                players[player].increaseScore(10);
                break;
            case 1:
                System.out.println("30 очков!");
                players[player].increaseScore(30);
                break;
            case 2:
                System.out.println("X2!!!");
                int newScore = players[player].getScore();
                newScore = newScore * 2;
                players[player].setScore(newScore);
                break;
        }
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
    private void printTaskAndProgress() {
        System.out.println("Вопрос: " + task);
        System.out.print("Прогресс: ");
        for (int i = 0; i < progress.length; i++) {
            System.out.print(progress[i]);
        }
        System.out.println();
    }
    private void initializationOfProgress(int size) {
        //Изначально прогресс отгадывания слова 0 и отображаются только *
        progress = new char[size];
        for (int i = 0; i < progress.length; i++) {
            progress[i] = '*';
        }

    }
    private void playerStep(int player) {

        System.out.println("Назовите слово или букву? Введите 1/2");
        int answer = 0;

        //Считываем вариант отгадывания слова
        boolean correct = false;
        while (correct == false) {
            try {
                answer = scanner.nextInt();

                //Если ответ вне диапазона доступных
                if (answer != 1 && answer != 2) {
                    System.out.println("Нет такого варианта. Попробуйте еще раз");
                    continue;
                }
                correct = true;
            } catch (InputMismatchException e) {
                System.out.println("Неправильный параметр. Попробуйте еще раз");
                scanner.next();
                continue;
            }
        }

        if (answer == 1) {
            enterWord(player);
        } else if (answer == 2) {
            enterLetter(player);
        }
    }

    private void enterWord(int player) {
        System.out.println("Введите слово: ");
        String word = scanner.next();
        if (word.equalsIgnoreCase(answer)) {
            winner = player;
            endGame = true;
        } else {
            System.out.println("Неправильно!");
            System.out.println();
        }
    }

    private void enterLetter(int player) {
        System.out.println("Введите букву: ");
        Character letter = scanner.next().charAt(0);
        int totalEntries = 0;

        //Проверяем наличие введенной буква в нашем слове
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == letter || answer.charAt(i) == toUpperCase(letter)) {
                totalEntries++;
                progress[i] = letter;
            }
        }

        //Если ни одного совпадения - передаем ход следующему игроку
        //Иначе смотрим сколько закрытых букв осталось
        if (totalEntries == 0) {
            System.out.println("Неправильно!");
            System.out.println();
            return;
        } else {
            System.out.println("Правильно!");
            printTaskAndProgress();
            int totalStars = 0;

            //Проверяем сколько букв осталось закрыто
            for (int i = 0; i < progress.length; i++) {
                if (progress[i] == '*') {
                    totalStars++;
                }
            }

            //Если все буквы открыты то победа
            if (totalStars == 0) {
                winner = player;
                endGame = true;
            } else {
                playerStep(player);
            }
        }
    }
}

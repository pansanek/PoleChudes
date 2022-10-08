import java.util.ArrayList;

public class Data {
    private boolean difficult = false;

    //Коллекция с простыми заданиями и еще одна с ответами к ними
    private ArrayList<String> simpleTasks;
    private ArrayList<String> simpleTasksAnswers;

    //Так же для сложных заданий
    private ArrayList<String> complicatedTasks;
    private ArrayList<String> complicatedTasksAnswers;

    //Конструктор по-умолчанию private, для того, чтобы избежать создания данных для игры без выбора сложности
    private Data() {
    }

    public Data(boolean difficult) {

        this.difficult = difficult;

        if (difficult == false) {
            //Инициализируем простой массив
            simpleTasks = new ArrayList<>();
            simpleTasksAnswers = new ArrayList<>();

            simpleTasks.add("Под каким сценическим именем более известна популярная российская певица Дарья Сергеевна Шиханова");
            simpleTasksAnswers.add("Дора");

            simpleTasks.add("Фамилия ректора МИРЭА — Российский технологический университет");
            simpleTasksAnswers.add("Кудж");

            simpleTasks.add("Вам нужно пересечь широкую реку, которая кишит крокодилами. Как вы это сделаете?");
            simpleTasksAnswers.add("Вплавь");
        } else {
            //Инициализиция сложного
            complicatedTasks = new ArrayList<>();
            complicatedTasksAnswers = new ArrayList<>();

            complicatedTasks.add("Самый просматриваемый видео-хостинг интернета – это …");
            complicatedTasksAnswers.add("YouTube");

            complicatedTasks.add("Главное орудие хоккеиста – это …");
            complicatedTasksAnswers.add("Клюшка");

            complicatedTasks.add("С японского это слово переводится как «Божественный ветер»");
            complicatedTasksAnswers.add("Камикадзе");
        }
    }

    public String getTask(int i) {
        if (difficult == false)
            return simpleTasks.get(i);
        else
            return complicatedTasks.get(i);
    }

    public String getAnswer(int i) {
        if (difficult == false)
            return simpleTasksAnswers.get(i);
        else
            return complicatedTasksAnswers.get(i);
    }
}
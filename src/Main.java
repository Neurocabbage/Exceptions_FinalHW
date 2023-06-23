//Напишите приложение, которое будет запрашивать у пользователя следующие данные , разделенные пробелом:
//Фамилия Имя Отчество датарождения номертелефона пол
//
//Форматы данных:
//фамилия, имя, отчество - строки
//датарождения - строка формата dd.mm.yyyy
//номертелефона - целое беззнаковое число без форматирования
//пол - символ латиницей f или m.
//
//Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым,
// вернуть исключение, обработать его и показать пользователю сообщение, что он ввел меньше или больше данных,
// чем требуется.
//
//Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры.
// Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы.
// Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано,
// пользователю выведено сообщение с информацией, что именно неверно.
//
//Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку
// должны записаться полученные данные, вида
//
//<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
//
//Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
//
//Не забудьте закрыть соединение с файлом.
//
//При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано,
// пользователь должен увидеть стектрейс ошибки.

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите Фамилию, Имя, Отчество, дату рождения, номертелефона, пол, разделенные пробелом:");
        String[] testData = new String[]{sc.nextLine()};

        int expectedCount = 6;
        for (String stringContact : testData) {
            System.out.println("<");
            System.out.printf("Входная строка -> %s\n", stringContact);
            int itemsCount = Checker.CheckDataCount(stringContact, expectedCount);
            if (itemsCount == 0) {
                System.out.println("Количество введенных данных соответствует");
                try {
                    Contact contact = Parser.ContactParse(stringContact);
                    System.out.printf("Контакт успешно распознан -> %s\n", contact);
                    Path file = Paths.get(contact.getLastName() + ".contact");
                    byte[] data = (contact.toString() + '\n').getBytes(StandardCharsets.UTF_8);
                    try {
                        Files.write(file, data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        System.out.printf("Контакт успешно записан в файл %s", file.getFileName());
                    } catch (IOException e) {
                        System.out.println("Ошибка записи в файл! Контакт не сохранен!");
                        e.printStackTrace(System.out);
                    }
                } catch (IllegalArgumentException ex) {
                    System.out.println("Не удалось распознать контакт!");
                    ex.printStackTrace(System.out);
                }
            } else {
                System.out.println(String.format("Введено %s данных, чем требуется!",
                        itemsCount > 0 ? "больше" : "меньше"));
            }
            System.out.println(">");
        }
    }

}
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Parser {
    public static Contact ContactParse(String input) throws IllegalArgumentException {
        String firstName = null;
        String middleName = null;
        String lastName= null;
        Date birtDate = null;
        String phoneNumber = null;
        String gender = null;

        Queue<String> tokens = new LinkedList<>(Arrays.asList(input.split("\\s+")));
        while(!tokens.isEmpty()) {
            String token = tokens.poll();
            if(firstName == null) {
                if (Checker.IsName(token)) {
                    firstName = token;
                    continue;
                }
            }
            if(middleName == null) {
                if(token.endsWith("вич") || token.endsWith("вна")) {
                    middleName = token;
                    continue;
                }
            }
            if(birtDate == null) {
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                format.setLenient(false);
                try {
                    format.setLenient(false);
                    birtDate = format.parse(token);
                    continue;
                } catch (ParseException e) { }
            }
            if(phoneNumber == null) {
                String regex = "\\d+";
                if(token.matches(regex)) {
                    phoneNumber = token;
                    continue;
                }
            }
            if(gender == null) {
                if(token.equals("m") || token.equals("f")) {
                    gender = token;
                    continue;
                }
            }
            if(lastName == null) {
                lastName = token;
            }
        }

        if(firstName == null || middleName == null || lastName == null
                || birtDate == null || phoneNumber == null || gender == null) {
            throw new IllegalArgumentException("Неверный формат записи данных");
        }

        return new Contact(firstName, middleName, lastName, birtDate, phoneNumber, gender);
    }
}

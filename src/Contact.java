import java.text.SimpleDateFormat;
import java.util.Date;

public class Contact {
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birtDate;
    private String phoneNumber;
    private String gender;

    public Contact(String firstName, String middleName, String lastName,
                   Date birtDate, String phoneNumber, String gender) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birtDate = birtDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirtDate() {
        return birtDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return lastName + ' ' + firstName + ' ' + middleName +  ' '
                + new SimpleDateFormat("dd.MM.yyyy").format(birtDate) + ' '
                + phoneNumber + " " + gender;
    }
}
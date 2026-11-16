import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable {

    private String id;
    private String name;
    private int age;
    private String email;
    private LocalDate registrationDate;
    private List<Appointment> appointments;

    public Member(String id, String name, int age, String email, LocalDate registrationDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.registrationDate = registrationDate;
        this.appointments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                ", appointments=" + appointments +
                '}';
    }
}

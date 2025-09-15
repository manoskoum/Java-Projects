import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pet implements Serializable {

    //private static int nextId = 1;
    private String id;

    private String name;

    private String speciesOrBreed;

    private int age;

    private String ownerName;

    private String contactInfo;

    private LocalDate registrationDate;

    private List<Appointment> appointments;

    public Pet(String id, String name, String speciesOrBreed, int age,
               String ownerName, String contactInfo, LocalDate registrationDate) {

        this.id=id;
        this.name = name;
        this.speciesOrBreed = speciesOrBreed;
        this.age = age;
        this.ownerName = ownerName;
        this.contactInfo = contactInfo;
        this.registrationDate = registrationDate;
        this.appointments = new ArrayList<>(); // start empty
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpeciesOrBreed() {
        return speciesOrBreed;
    }

    public int getAge() {
        return age;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeciesOrBreed(String speciesOrBreed) {
        this.speciesOrBreed = speciesOrBreed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    // Add single appointment helper
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", speciesOrBreed='" + speciesOrBreed + '\'' +
                ", age=" + age +
                ", ownerName='" + ownerName + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", registrationDate=" + registrationDate +
                ", appointments=" + appointments.size() +
                '}';
    }
}

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class Main {

    private static Scanner scanner = new Scanner(System.in);


    private  Map<String,Pet> pets= new HashMap<>();

    List<Appointment> appointments= new ArrayList<>();

    public static void main(String[] args) {

        Main app = new Main();

        boolean running = true;
        while (running) {
            System.out.println("\n=== Pet Care Scheduler ===");
            System.out.println("1. Register new pet");
            System.out.println("2. Set appointment");
            System.out.println("3. Display data");
            System.out.println("4. Generate reports");
            System.out.println("5. Save and Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
       switch (choice) {
        case 1:
            app.registerNewPet();
            break;
        case 2:
            app.addAppointment();
            break;
        case 3:
            app.display();
            break;
        case 4:
            app.generateReport();
            break;
        case 5:
            app.storeData();
            running = false;
            System.out.println("Goodbye!");
            break;
            default:
            System.out.println("Invalid choice. Please try again.");
    }
    }}

    public static String chooseSpecies() {
        System.out.println("Choose the species/breed of the pet:");
        System.out.println("1. Dog");
        System.out.println("2. Cat");
        System.out.println("3. Rabbit");
        System.out.println("4. Bird");
        System.out.println("5. Other");

        int option = scanner.nextInt();
        scanner.nextLine(); // καθαρίζει το buffer

        switch (option) {
            case 1: return "Dog";
            case 2: return "Cat";
            case 3: return "Rabbit";
            case 4: return "Bird";
            case 5: return "Other";
            default: return "non valid option";
        }
    }

    private void registerNewPet(){

        System.out.print("Enter Pet ID: ");
        String id = scanner.nextLine().trim();

        if (pets.containsKey(id)) {
            System.out.println("Error: Pet ID already exists.");
            return ;
        }

        System.out.println("give the name of the pet :");
        String petName= scanner.nextLine();

        String species = chooseSpecies();

        System.out.println("give the age of the pet :");
        int age=scanner.nextInt();
        scanner.nextLine();

        System.out.println("give the owner name of th pet :");
        String ownerName= scanner.nextLine();

        System.out.println("give contact info :");
        String info=scanner.nextLine();

        LocalDate registrationDate = LocalDate.now();

        Pet newPet = new Pet(id,petName, species, age, ownerName, info, registrationDate);

        pets.put(id,newPet);

        System.out.println("Pet registered successfully on " + registrationDate);
    }

    private void addAppointment(){
        System.out.print("Enter Pet ID: ");
        String id = scanner.nextLine().trim();

        if (!pets.containsKey(id)) {
            System.out.println("Error: Pet ID doesnt exist.");
            return ;
        }

        Pet pet = pets.get(id);

        System.out.print("Enter appointment date and time (yyyy-MM-dd HH:mm): ");
        String input = scanner.nextLine().trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime appointmentDateTime=null;

        try {
             appointmentDateTime = LocalDateTime.parse(input, formatter);

            if (appointmentDateTime.isBefore(LocalDateTime.now())) {
                System.out.println("Error: Appointment must be set in the future.");
                return;
            }

            
            System.out.println("Valid appointment time: " + appointmentDateTime);

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format. Please use yyyy-MM-dd HH:mm");
            return;
        }

        String reasonFor = reason();

        Appointment newAppointment= new Appointment(reasonFor,appointmentDateTime);

        pet.addAppointment(newAppointment);
        appointments.add(newAppointment);
        System.out.println("Appointment added for pet " + pet.getName());

    }

    public static  String reason() {
        System.out.println("What is the main reason for the appointment");
        System.out.println("1. Vet Visit");
        System.out.println("2. Vaccination");
        System.out.println("3. Grooming");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                return "Vet Visit";
            case 2:
                return "Vaccination";
            case 3:
                return "Grooming";

            default: return "non valid option";
        }
    }

    public void storeData(){
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("data.dat"))) {
            out.writeObject(pets);
            out.writeObject(appointments);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
            e.printStackTrace(); // προσωρινά για debug
        }
    }

    public void displayAllRegisteredPets(){
        if(pets.isEmpty()){
            System.out.println("No pets registered.");
            return;
        }

        System.out.println("\nRegistered Pets:");
        for(Pet p : pets.values()){
            System.out.println(p);
        }
    }

    private void displayAppointmentsForAPet(String id) {
        Pet pet=pets.get(id);

        if(pet==null){
            System.out.println("Pet with the id: " + id + "doesn't exist");
            return;

        }

        if (pet.getAppointments().isEmpty()) {
            System.out.println("No appointments for " + pet.getName() + ".");
            return;
        }

        System.out.println("Appointments for pet: " + pet.getName());
        for(Appointment a : pet.getAppointments()){
            System.out.println(" - " + a);
        }
    }

    private void upcomingAppointments() {
        if (appointments == null || appointments.isEmpty()) {
            System.out.println("No upcoming appointments.");
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        System.out.println("Upcoming appointments:");
        for (Appointment up : appointments) {
            if (up.getDateTime().isAfter(now)) {
                System.out.println(" - " + up);
            }
        }
    }

    private void pastAppointments() {

        LocalDateTime now = LocalDateTime.now();
        for (Pet pe : pets.values()) {
            System.out.println("Past appointments for " + pe.getName() + ":");

            boolean found = false;
            for (Appointment ap : pe.getAppointments()) {
                if (ap.getDateTime().isBefore(now)) {
                    System.out.println(" - " + ap);
                    found = true;
                }
            }

            if (!found) {
                System.out.println(" No past appointments.");
            }
        }

    }

    public void display() {

        System.out.println("DISPLAY MENU");
        System.out.println("Choose display option:");
        System.out.println("1. All registered pets");
        System.out.println("2. Appointments for a specific pet");
        System.out.println("3. Upcoming appointments for all pets");
        System.out.println("4. Past appointment history for each pet");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                displayAllRegisteredPets();
                break;
            case 2:
                System.out.print("Enter pet ID: ");
                String id = scanner.nextLine().trim();
                displayAppointmentsForAPet(id);
                break;
            case 3:
                upcomingAppointments();
                break;
            case 4:
                pastAppointments();
                break;
            default: System.out.println("Invalid choice.");

        }

    }

    public void generateReport(){
        System.out.println("\nREPORTS MENU");
        System.out.println("1. Pets with upcoming appointments in the next week");
        System.out.println("2. Pets overdue for a vet visit (> 6 months)");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: reportUpcomingWeek(); break;
            case 2: reportOverdueVetVisit(); break;
            default: System.out.println("Invalid choice.");
        }
    }

    private void reportOverdueVetVisit() {
        LocalDateTime cutoff = LocalDateTime.now().minusMonths(6);
        List<String> overdue = new ArrayList<>();

        for (Pet p : pets.values()) {
            LocalDateTime lastVet = null;
            for (Appointment a : p.getAppointments()) {
                if (!"Vet Visit".equalsIgnoreCase(a.getType())) continue;
                LocalDateTime t = a.getDateTime();
                if (lastVet == null || t.isAfter(lastVet)) lastVet = t;
            }
            if (lastVet == null || lastVet.isBefore(cutoff)) overdue.add(p.getName());
        }

        if (overdue.isEmpty()) {
            System.out.println("\nNo pets are overdue for a vet visit.");
        } else {
            System.out.println("\nPets overdue for a vet visit (> 6 months):");
            for (String name : overdue) System.out.println(" - " + name);
        }

    }

    private void reportUpcomingWeek() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime until=now.plusDays(7);
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        boolean found = false;
        System.out.println("\nPets with upcoming appointments (next 7 days):");
        List<Pet> petList = new ArrayList<>(pets.values());
        for(int i=0;i<petList.size();i++){
            Pet p = petList.get(i);
            List<Appointment> appts = p.getAppointments();
            for(int j = 0; j < appts.size(); j++){
                Appointment a = appts.get(j);
                LocalDateTime t = a.getDateTime();
                if(!t.isBefore(now) && !t.isAfter(until)){
                    System.out.println(" - " + p.getName() + " — " + a.getType() + " @ " + t.format(formatter));
                    found=true;
                }
            }
        }
        if (!found) {
            System.out.println("No upcoming appointments in the next 7 days.");
        }
    }
}

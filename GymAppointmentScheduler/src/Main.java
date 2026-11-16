import java.io.*;
import java.sql.SQLOutput;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        Map<String, Member> members = loadMembers();

        Scanner sc = new Scanner(System.in);

        int choice = 0;

        while (choice != 9) {

            System.out.println("\n===== GYM APPOINTMENT SCHEDULER =====");

            System.out.println("1. Add Member");
            System.out.println("2. Display Members");
            System.out.println("3. Find Member");
            System.out.println("4. Remove Member");
            System.out.println("5. Update Member");
            System.out.println("6. Add Appointment");
            System.out.println("7. Display Appointments");
            System.out.println("8. Remove Appointment");
            System.out.println("9. Exit");

            choice = 0;

            while (choice <= 0 || choice > 9) {
                System.out.println("Choose an option (1-9):");

                try {
                    choice = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number");
                    sc.nextLine();
                }
            }

            sc.nextLine();

            switch (choice) {
                case 1:
                    addMember(members, sc);
                    break;
                case 2:
                    displayMembers(members);
                    break;
                case 3:
                    findMember(members, sc);
                    break;
                case 4:
                    removeMember(members, sc);
                    break;
                case 5:
                    updateMember(members, sc);
                    break;
                case 6:
                    addAppointmentToMember(members, sc);
                    break;
                case 7:
                    displayMemberAppointments(members, sc);
                    break;
                case 8:
                    removeMemberAppointment(members, sc);
                    break;
                case 9:
                    saveMembers(members);
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void addMember(Map<String, Member> members, Scanner scanner) {

        System.out.println("Give id");
        String id = scanner.nextLine();

        while (id.isBlank()) {
            System.out.println("Give valid ID");
            id = scanner.nextLine();
        }

        if (members.containsKey(id)) {
            System.out.println("ID already exists");
        } else {
            System.out.println("Give name");
            String name = scanner.nextLine();

            while (name.isBlank()) {
                System.out.println("Give valid name");
                name = scanner.nextLine();
            }

            int age = 0;

            while (age <= 0 || age > 90) {
                try {
                    System.out.println("Give age (1-90):");
                    age = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number");
                    scanner.nextLine();
                }
            }

            scanner.nextLine();

            String email;
            boolean emailExists = false;

            do {
                System.out.println("Give email");
                email = scanner.nextLine();

                emailExists = false;

                if (email.isBlank()) {
                    System.out.println("Email cannot be blank");

                } else if (!email.contains("@")) {
                    System.out.println("Email must contain @");

                } else {
                    for (Member m : members.values()) {
                        if (email.equalsIgnoreCase(m.getEmail())) {
                            emailExists = true;
                        }
                    }

                    if (emailExists) {
                        System.out.println("This email already exists");
                    }
                }

            } while (email.isBlank() || !email.contains("@") || emailExists);

            LocalDate localDate = LocalDate.now();

            Member member = new Member(id, name, age, email, localDate);

            members.put(id, member);

            System.out.println("Member added successfully");
        }
    }

    public static void displayMembers(Map<String, Member> members) {

        if (members.isEmpty()) {
            System.out.println("No members found");
            return;
        }

        for (Member m : members.values()) {
            System.out.println(m);
        }
    }

    public static void findMember(Map<String, Member> members, Scanner scanner) {

        System.out.println("Give id for search");
        String id = scanner.nextLine();

        if (members.containsKey(id)) {
            System.out.println(members.get(id));
        } else {
            System.out.println("Member doesn't exist");
        }
    }

    public static void removeMember(Map<String, Member> members, Scanner scanner) {

        System.out.println("Give id for search");
        String id = scanner.nextLine();

        if (members.containsKey(id)) {
            members.remove(id);
            System.out.println("Member removed successfully");
        } else {
            System.out.println("Member does not exist");
        }
    }

    public static void updateMember(Map<String, Member> members, Scanner scanner) {

        System.out.println("Give id for search");
        String id = scanner.nextLine();

        Member member = members.get(id);

        if (member != null) {

            System.out.println("Give name");
            String name = scanner.nextLine();

            while (name.isBlank()) {
                System.out.println("Give valid name");
                name = scanner.nextLine();
            }

            int age = 0;

            while (age <= 0 || age > 90) {
                try {
                    System.out.println("Give age (1-90):");
                    age = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number");
                    scanner.nextLine();
                }
            }

            scanner.nextLine();

            String email;
            boolean emailExists;

            do {
                System.out.println("Give email");
                email = scanner.nextLine();

                emailExists = false;

                if (email.isBlank()) {
                    System.out.println("Email cannot be blank");

                } else if (!email.contains("@")) {
                    System.out.println("Email must contain @");

                } else {
                    for (Member m : members.values()) {

                        if (!m.getId().equals(id) &&
                                email.equalsIgnoreCase(m.getEmail())) {
                            emailExists = true;
                            break;
                        }
                    }
                }

                if (emailExists) {
                    System.out.println("This email already exists");
                }

            } while (email.isBlank() || !email.contains("@") || emailExists);

            member.setName(name);
            member.setAge(age);
            member.setEmail(email);

            System.out.println("Member updated successfully");

        } else {
            System.out.println("Member does not exist");
        }
    }

    public static void addAppointmentToMember(Map<String, Member> members, Scanner scanner) {

        System.out.println("Give id for search");
        String id = scanner.nextLine();

        Member member = members.get(id);

        if (member != null) {

            System.out.println("Give type for appointment");
            String type = scanner.nextLine();

            while (type.isBlank()) {
                System.out.println("Give valid type for appointment");
                type = scanner.nextLine();
            }

            String date = "";
            LocalDate localDate = null;

            while (date.isBlank()) {
                try {
                    System.out.println("Give valid appointment date (yyyy-MM-dd)");
                    date = scanner.nextLine();

                    localDate = LocalDate.parse(date);

                    if (localDate.isBefore(LocalDate.now())) {
                        System.out.println("Appointment date cannot be in the past");
                        date = "";
                    }

                } catch (DateTimeParseException e) {
                    System.out.println("Wrong form of date");
                    date = "";
                }
            }

            String time = "";
            LocalDateTime localDateTime = null;

            while (time.isBlank()) {
                try {
                    System.out.println("Give valid appointment time (HH:mm)");
                    time = scanner.nextLine();

                    LocalTime localTime = LocalTime.parse(time);

                    localDateTime = LocalDateTime.of(localDate, localTime);

                    if (localDateTime.isBefore(LocalDateTime.now())) {
                        System.out.println("Appointment date and time cannot be in the past");
                        time = "";
                    }

                } catch (DateTimeParseException e) {
                    System.out.println("Wrong form of time");
                    time = "";
                }
            }

            System.out.println("Give appointment notes");
            String notes = scanner.nextLine();

            Appointment app = new Appointment(type, localDateTime, notes);

            member.addAppointment(app);

            System.out.println("Appointment added successfully");

        } else {
            System.out.println("Member does not exist");
        }
    }

    public static void displayMemberAppointments(Map<String, Member> members, Scanner scanner) {

        System.out.println("Give id for search");
        String id = scanner.nextLine();

        Member member = members.get(id);

        if (member == null) {
            System.out.println("Member does not exist");

        } else {
            List<Appointment> list = member.getAppointments();

            if (list.isEmpty()) {
                System.out.println("This member has no appointments");
                return;
            }

            for (Appointment a : list) {
                System.out.println(a);
            }
        }
    }

    public static void removeMemberAppointment(Map<String, Member> members, Scanner scanner) {

        System.out.println("Give id for search");
        String id = scanner.nextLine();

        Member member = members.get(id);

        if (member == null) {
            System.out.println("Member does not exist");
            return;
        }

        List<Appointment> appointmentsList = member.getAppointments();

        if (appointmentsList.isEmpty()) {
            System.out.println("This member has no appointments");
            return;
        }

        for (int i = 0; i < appointmentsList.size(); i++) {

            Appointment app = appointmentsList.get(i);

            System.out.println((i + 1) + " " + app);
        }

        int choice = 0;

        while (choice < 1 || choice > appointmentsList.size()) {

            try {
                System.out.println("Enter the number of the appointment you want to delete:");
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice < 1 || choice > appointmentsList.size()) {
                    System.out.println("Invalid appointment number");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                scanner.nextLine();
            }
        }

        appointmentsList.remove(choice - 1);

        System.out.println("Appointment removed successfully");
    }

    public static void saveMembers(Map<String, Member> members) {

        try (FileOutputStream fileOutputStream = new FileOutputStream("members.dat");
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {

            outputStream.writeObject(members);

            System.out.println("Members saved successfully");

        } catch (IOException e) {
            System.out.println("Error saving members: " + e.getMessage());
        }
    }

    public static Map<String, Member> loadMembers() {

        HashMap<String, Member> emptyMap = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream("members.dat");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Map<String, Member> members =
                    (Map<String, Member>) objectInputStream.readObject();

            return members;

        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting with an empty list.");

        } catch (IOException e) {
            System.out.println("Error loading members: " + e.getMessage());

        } catch (ClassNotFoundException e) {
            System.out.println("Error loading members: " + e.getMessage());
        }

        return emptyMap;
    }
}


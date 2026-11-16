# Gym Appointment Scheduler

A Java console-based application for managing gym members and their appointments.

The application allows users to register and manage gym members, schedule appointments, update member information, and store data locally using Java object serialization.

## Features

- Add new gym members
- Display all registered members
- Search for members by ID
- Remove members
- Update member information
- Schedule appointments for members
- Display appointments for a specific member
- Remove appointments
- Validate user input and prevent invalid data
- Save and load member data using Java object serialization

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Java Collections Framework (HashMap, ArrayList)
- Java Date and Time API (LocalDate, LocalTime, LocalDateTime)
- Java Object Serialization
- IntelliJ IDEA

## Project Structure

```text
GymAppointmentScheduler/
├── src/
│   ├── Main.java
│   ├── Member.java
│   └── Appointment.java
├── .gitignore
└── README.md
```

## How to Run

1. Clone or download this repository.

2. Open the project in IntelliJ IDEA.

3. Make sure a Java JDK is installed and configured.

4. Open Main.java.

5. Run the main() method.

The application runs in the console and automatically creates the members.dat file when saving member data.

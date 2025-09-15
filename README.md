# 🐾 Pet Care Scheduler

A simple **Java console application** for registering pets and managing their appointments (e.g., vaccinations, grooming, vet visits).  
This project was built to practice **object-oriented programming**, **collections**, **serialization**, and **menu-driven console apps** in Java.

## ✨ Features

- 📋 **Pet Registration**
  - Unique ID, name, species/breed, age, owner’s name, contact info, registration date
- 📅 **Appointment Scheduling**
  - Type (Vet Visit / Vaccination / Grooming)
  - Date & time (validated to be in the future)
  - Linked to both the pet and the global appointment list
- 👀 **Data Display**
  - All registered pets
  - All appointments for a specific pet
  - Upcoming appointments for all pets
  - Past appointment history for each pet
- 📊 **Reports**
  - Pets with upcoming appointments in the next 7 days
  - Pets overdue for a vet visit (> 6 months since last visit)
- 💾 **Data Persistence**
  - Save data to a file (`data.dat`) using Java serialization (`ObjectOutputStream`)

## 🛠️ Technologies

- **Java 17+**
- Classes: `LocalDate`, `LocalDateTime`, `DateTimeFormatter`
- Collections: `Map`, `List`, `ArrayList`, `HashMap`
- Serialization: `ObjectOutputStream` / `ObjectInputStream`

## ▶️ How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/pet-care-scheduler.git
   cd pet-care-scheduler
   javac Main.java Pet.java Appointment.java
   java Main


📸 Sample Output

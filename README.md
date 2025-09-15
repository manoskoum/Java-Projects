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
=== Pet Care Scheduler ===
1. Register new pet
2. Set appointment
3. Display data
4. Generate reports
5. Save and Exit
Choose an option: 1

Enter Pet ID: 101
give the name of the pet : Rex
Choose the species/breed of the pet:
1. Dog
2. Cat
...
Pet registered successfully on 2025-09-15

👤 Author Koumentakis Emmanouil

📧 manoskoume@gmail.com

🌐 [LinkedIn](https://www.linkedin.com/in/emmanouil-koumentakis-a382a8325) | [GitHub](https://github.com/YourGitHubUsername)

package org.example.project2;

import java.util.Scanner;

public class HospitalManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Create a hospital with capacity for 10 persons and 10 appointments
        Hospital hospital = new Hospital(10, 10);

        while (true) {
            System.out.println("\n--- Hospital Management System Menu ---");
            System.out.println("1. Register Person (Patient/Doctor)");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. List All Persons");
            System.out.println("4. List Appointments");
            System.out.println("5. Simulate Day");
            System.out.println("6. Print Hospital Statistics");
            System.out.println("7. Search Person by ID");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the remaining newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter type (1 for Patient, 2 for Doctor): ");
                        int type = scanner.nextInt();
                        scanner.nextLine();
                        if (type == 1) {
                            System.out.print("Enter Patient ID: ");
                            String id = scanner.nextLine();
                            System.out.print("Enter Name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter Age: ");
                            int age = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter Symptoms: ");
                            String symptoms = scanner.nextLine();
                            System.out.print("Enter Medical Record Number: ");
                            String medRecord = scanner.nextLine();
                            Patient patient = new Patient(id, name, age, symptoms, medRecord);
                            hospital.addPerson(patient);
                        } else if (type == 2) {
                            System.out.print("Enter Doctor ID: ");
                            String id = scanner.nextLine();
                            System.out.print("Enter Name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter Age: ");
                            int age = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter Specialty: ");
                            String specialty = scanner.nextLine();
                            System.out.print("Enter License Number: ");
                            String license = scanner.nextLine();
                            System.out.print("Enter Consultation Fee: ");
                            double fee = scanner.nextDouble();
                            scanner.nextLine();
                            Doctor doctor = new Doctor(id, name, age, specialty, license, fee);
                            hospital.addPerson(doctor);
                        } else {
                            System.out.println("Invalid type selected.");
                        }
                        break;
                    case 2:
                        // Schedule Appointment
                        System.out.print("Enter Patient ID: ");
                        String patientId = scanner.nextLine();
                        System.out.print("Enter Doctor ID: ");
                        String doctorId = scanner.nextLine();
                        System.out.print("Enter Date and Time (e.g., 2025-05-20 10:00): ");
                        String dateTime = scanner.nextLine();
                        System.out.print("Enter Reason for Appointment: ");
                        String reason = scanner.nextLine();

                        Person patient = hospital.searchPerson(patientId);
                        Person doctor = hospital.searchPerson(doctorId);
                        if (patient == null || doctor == null) {
                            System.out.println("Error: Patient or Doctor not found.");
                            break;
                        }

                        Appointment appointment = null;
                        // Patient schedules appointment with a Doctor
                        if (patient instanceof Patient && doctor instanceof Doctor) {
                            appointment = ((Patient) patient).scheduleAppointment(dateTime, doctor, reason);
                        }
                        if (appointment != null) {
                            hospital.addAppointment(appointment);
                        }
                        break;
                    case 3:
                        hospital.listAllPersons();
                        break;
                    case 4:
                        hospital.listAppointments();
                        break;
                    case 5:
                        hospital.simulateDay();
                        break;
                    case 6:
                        Hospital.printHospitalStatistics(hospital);
                        break;
                    case 7:
                        System.out.print("Enter Person ID to search: ");
                        String searchId = scanner.nextLine();
                        Person found = hospital.searchPerson(searchId);
                        if (found != null) {
                            System.out.println("Found: " + found.getDetails());
                        } else {
                            System.out.println("Person not found.");
                        }
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}


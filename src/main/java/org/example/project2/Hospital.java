package org.example.project2;

public class Hospital {
    // Arrays to manage persons and appointments
    private Person[] persons;
    private int personCount;

    private Appointment[] appointments;
    private int appointmentCount;

    // Constructor: specify capacity for persons and appointments
    public Hospital(int personCapacity, int appointmentCapacity) {
        persons = new Person[personCapacity];
        personCount = 0;
        appointments = new Appointment[appointmentCapacity];
        appointmentCount = 0;
    }

    /**
     * Adds a person (Patient or Doctor) to the hospital.
     * Throws an exception if the capacity is reached.
     */
    public void addPerson(Person p) throws Exception {
        if (personCount >= persons.length) {
            throw new Exception("Hospital capacity for persons reached!");
        }
        persons[personCount] = p;
        personCount++;
        System.out.println("Added person: " + p.getDetails());
    }

    /**
     * Searches for a person by their unique ID.
     */
    public Person searchPerson(String id) {
        for (int i = 0; i < personCount; i++) {
            if (persons[i].getId().equals(id)) {
                System.out.println("Found person: " + persons[i].getDetails());
                return persons[i];
            }
        }
        return null;
    }

    /**
     * Prints details of all persons registered in the hospital.
     */
    public void listAllPersons() {
        System.out.println("\n--- Hospital Persons List ---");
        for (int i = 0; i < personCount; i++) {
            System.out.println(persons[i].getDetails());
        }
    }

    /**
     * Adds an appointment. Throws an exception if capacity is reached.
     */
    public void addAppointment(Appointment appointment) throws Exception {
        if (appointmentCount >= appointments.length) {
            throw new Exception("Appointment capacity reached!");
        }
        appointments[appointmentCount] = appointment;
        appointmentCount++;
        System.out.println("Added appointment: " + appointment);
    }

    /**
     * Prints all scheduled appointments.
     */
    public void listAppointments() {
        System.out.println("\n--- Hospital Appointments List ---");
        for (int i = 0; i < appointmentCount; i++) {
            System.out.println(appointments[i]);
        }
    }

    /**
     * Simulates a day in the hospital by iterating through appointments,
     * having the doctor diagnose the patient, and printing appointment details.
     */
    public void simulateDay() {
        System.out.println("\nSimulating day in the hospital...");
        for (int i = 0; i < appointmentCount; i++) {
            Appointment appt = appointments[i];
            appt.getDoctor().diagnose(appt.getPatient());
            System.out.println("Appointment details: " + appt);
        }
    }

    /**
     * Prints hospital statistics: total persons, number of patients and doctors, and total appointments.
     */
    public static void printHospitalStatistics(Hospital hospital) {
        int numPatients = 0;
        int numDoctors = 0;
        for (int i = 0; i < hospital.personCount; i++) {
            if (hospital.persons[i] instanceof Patient) {
                numPatients++;
            } else if (hospital.persons[i] instanceof Doctor) {
                numDoctors++;
            }
        }
        System.out.println("\n--- Hospital Statistics ---");
        System.out.println("Total Persons: " + hospital.personCount);
        System.out.println("Number of Patients: " + numPatients);
        System.out.println("Number of Doctors: " + numDoctors);
        System.out.println("Total Appointments: " + hospital.appointmentCount);
    }
}


package org.example.project2;

public class Patient extends Person implements Schedulable {
    // Additional attributes specific to patients
    private String symptoms;
    private String medicalRecordNumber;

    public Patient(String id, String name, int age, String symptoms, String medicalRecordNumber) {
        super(id, name, age);
        this.symptoms = symptoms;
        this.medicalRecordNumber = medicalRecordNumber;
    }

    // Getters and setters for patient-specific attributes
    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    @Override
    public String getRole() {
        return "Patient";
    }

    /**
     * Patient schedules an appointment with a doctor.
     * If otherParty is not a Doctor, an error is printed.
     */
    @Override
    public Appointment scheduleAppointment(String dateTime, Person otherParty, String reason) {
        if (!(otherParty instanceof Doctor)) {
            System.out.println("Error: Patients can only schedule appointments with Doctors.");
            return null;
        }
        Doctor doctor = (Doctor) otherParty;
        Appointment appointment = new Appointment(Appointment.generateAppointmentId(), this, doctor, dateTime, reason);
        System.out.println("Patient " + getName() + " has scheduled an appointment with Doctor "
                + doctor.getName() + " at " + dateTime + ".");
        return appointment;
    }
}


package org.example.project2;

public class Appointment {
    // Static counter to generate unique appointment IDs
    private static int appointmentCounter = 0;

    private final String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String dateTime;
    private String reason;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String dateTime, String reason) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.reason = reason;
    }

    // Getters
    public String getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getReason() {
        return reason;
    }

    // Generates a unique appointment ID
    public static String generateAppointmentId() {
        appointmentCounter++;
        return "APT-" + appointmentCounter;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "ID='" + appointmentId + '\'' +
                ", Patient=" + patient.getName() +
                ", Doctor=" + doctor.getName() +
                ", DateTime='" + dateTime + '\'' +
                ", Reason='" + reason + '\'' +
                '}';
    }
}

package org.example.project2;

public class Doctor extends Person implements Schedulable {
    // Additional attributes specific to doctors
    private String specialty;
    private String licenseNumber;
    private double consultationFee;

    public Doctor(String id, String name, int age, String specialty, String licenseNumber, double consultationFee) {
        super(id, name, age);
        this.specialty = specialty;
        this.licenseNumber = licenseNumber;
        this.consultationFee = consultationFee;
    }

    // Getters and setters for doctor-specific attributes
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    /**
     * A sample method to simulate a diagnosis.
     */
    public void diagnose(Patient patient) {
        System.out.println("Dr. " + getName() + " is diagnosing patient " + patient.getName() + ".");
    }

    /**
     * Doctor schedules an appointment with a patient.
     * If otherParty is not a Patient, an error message is printed.
     */
    @Override
    public Appointment scheduleAppointment(String dateTime, Person otherParty, String reason) {
        if (!(otherParty instanceof Patient)) {
            System.out.println("Error: Doctors can only schedule appointments with Patients.");
            return null;
        }
        Patient patient = (Patient) otherParty;
        Appointment appointment = new Appointment(Appointment.generateAppointmentId(), patient, this, dateTime, reason);
        System.out.println("Doctor " + getName() + " has scheduled an appointment with Patient "
                + patient.getName() + " at " + dateTime + ".");
        return appointment;
    }
}


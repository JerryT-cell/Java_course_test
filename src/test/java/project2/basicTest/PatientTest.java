package project2.basicTest;

import org.example.project2.Appointment;
import org.example.project2.Doctor;
import org.example.project2.Patient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class PatientTest {

    private Patient patient;
    private Doctor doctor; // Needed for scheduling tests

    @BeforeEach
    void setUp() {
        patient = new Patient("P001", "Alice", 30, "Fever", "MRN123");
        doctor = new Doctor("D001", "Dr. Bob", 45, "Cardiology", "L12345", 150.0);
    }

    @Test
    void testPatientConstructorAndGetters() {
        assertEquals("P001", patient.getId());
        assertEquals("Alice", patient.getName());
        assertEquals(30, patient.getAge());
        assertEquals("Fever", patient.getSymptoms());
        assertEquals("MRN123", patient.getMedicalRecordNumber());
    }

    @Test
    void testPatientSetters() {
        patient.setName("Alicia");
        assertEquals("Alicia", patient.getName());
        patient.setAge(31);
        assertEquals(31, patient.getAge());
        patient.setSymptoms("Headache");
        assertEquals("Headache", patient.getSymptoms());
        patient.setMedicalRecordNumber("MRN456");
        assertEquals("MRN456", patient.getMedicalRecordNumber());
    }

    @Test
    void testGetRole() {
        assertEquals("Patient", patient.getRole());
    }

    @Test
    void testGetDetails() {
        // Basic check: ensure it runs and includes role
        assertTrue(patient.getDetails().contains("ID: P001"));
        assertTrue(patient.getDetails().contains("Name: Alice"));
        assertTrue(patient.getDetails().contains("Age: 30"));
        assertTrue(patient.getDetails().contains("Role: Patient"));
    }

    @Test
    void testScheduleAppointmentWithDoctorSuccessfully() {
        Appointment appointment = patient.scheduleAppointment("2024.01.15 10:00", doctor, "Checkup");
        assertNotNull(appointment);
        assertEquals(patient, appointment.getPatient());
        assertEquals(doctor, appointment.getDoctor());
        assertEquals("2024.01.15 10:00", appointment.getDateTime());
        assertEquals("Checkup", appointment.getReason());
    }

    @Test
    void testScheduleAppointmentWithNonDoctorFails() {
        Patient anotherPatient = new Patient("P002", "Charlie", 25, "Cough", "MRN789");
        // Expect null return based on current implementation
        Appointment appointment = patient.scheduleAppointment("2024.01.16 11:00", anotherPatient, "Consultation");
        assertNull(appointment);
    }
}


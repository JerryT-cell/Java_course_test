package project2.advancedTest;

import org.example.project2.Appointment;
import org.example.project2.Doctor;
import org.example.project2.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach; // For stream restoration
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream; // For capturing output
import java.io.PrintStream; // For capturing output

class PatientTest { // Inherit from BaseOutputCaptureTest or include methods directly

    private Patient patient;
    private Doctor doctor;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String EOL = System.lineSeparator(); // Platform independent newline

    @BeforeEach
    void setUp() {
        patient = new Patient("P001", "Alice", 30, "Fever", "MRN123");
        doctor = new Doctor("D001", "Dr. Bob", 45, "Cardiology", "L12345", 150.0);
        // Redirect System.out before each test
        System.setOut(new PrintStream(outContent));
        // For testing, we'll assume generateAppointmentId works sequentially from some point
    }

    @AfterEach
    void restoreStreams() {
        // Restore System.out after each test
        System.setOut(originalOut);
    }

    // Basic tests (can be kept or removed if redundant with previous suite)
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
        patient.setMedicalRecordNumber("MRN456");
        assertEquals("MRN456", patient.getMedicalRecordNumber());
        patient.setSymptoms("Headache");
        assertEquals("Headache", patient.getSymptoms());
    }

    @Test
    void testGetRole() {
        assertEquals("Patient", patient.getRole());
    }

    // --- Advanced Tests ---

    @Test
    void testGetDetailsOutputFormat() {
        // Test the exact string format from getDetails
        String expectedDetails = "ID: P001, Name: Alice, Age: 30, Role: Patient";
        assertEquals(expectedDetails, patient.getDetails());
    }

    @Test
    void testScheduleAppointmentWithDoctorPrintsConfirmation() {
        String dateTime = "2025.05.28 17:53";
        patient.scheduleAppointment(dateTime, doctor, "Consultation");
        String expectedOutput = "Patient Alice has scheduled an appointment with Doctor Dr. Bob at " + dateTime + ".";
        // Using contains might be safer if there's extra whitespace or minor variations
        assertTrue(getOutput().contains(expectedOutput), "Output did not contain expected scheduling confirmation.");
        // Or for exact match (ensure no leading/trailing spaces if using trim)
        assertEquals(expectedOutput, getOutput());
    }

    @Test
    void testScheduleAppointmentWithNonDoctorPrintsError() {
        Patient anotherPatient = new Patient("P002", "Charlie", 25, "Cough", "MRN789");
        Appointment appointment = patient.scheduleAppointment("2024.01.16 11:00", anotherPatient, "Consultation");

        assertNull(appointment, "Appointment should be null when scheduling with non-doctor");
        String expectedError = "Error: Patients can only schedule appointments with Doctors.";
        assertEquals(expectedError, getOutput(), "Output did not contain expected error message.");
    }

    // Helper to get captured output, normalized for line endings
    private String getOutput() {
        return outContent.toString().replace("\r\n", "\n").trim();
    }
}

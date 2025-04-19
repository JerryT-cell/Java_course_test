package project2.advancedTest;

import org.example.project2.Appointment;
import org.example.project2.Doctor;
import org.example.project2.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class DoctorTest { // Inherit or include stream capture methods

    private Doctor doctor;
    private Patient patient;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String EOL = System.lineSeparator();

    @BeforeEach
    void setUp() {
        doctor = new Doctor("D001", "Dr. Bob", 45, "Cardiology", "L12345", 150.0);
        patient = new Patient("P001", "Alice", 30, "Fever", "MRN123");
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    // Basic tests
    @Test
    void testDoctorConstructorAndGetters() {
        assertEquals("D001", doctor.getId());
        assertEquals("Dr. Bob", doctor.getName());
        assertEquals("Cardiology", doctor.getSpecialty());
        assertEquals(150.0, doctor.getConsultationFee());
    }

    @Test
    void testDoctorSetters() {
        doctor.setSpecialty("Neurology");
        assertEquals("Neurology", doctor.getSpecialty());
        doctor.setConsultationFee(200.0);
        assertEquals(200.0, doctor.getConsultationFee());
    }

    @Test
    void testGetRole() {
        assertEquals("Doctor", doctor.getRole());
    }

    // --- Advanced Tests ---

    @Test
    void testGetDetailsOutputFormat() {
        String expectedDetails = "ID: D001, Name: Dr. Bob, Age: 45, Role: Doctor";
        assertEquals(expectedDetails, doctor.getDetails());
    }

    @Test
    void testDiagnosePrintsCorrectMessage() {
        doctor.diagnose(patient);
        String expectedOutput = "Dr. Dr. Bob is diagnosing patient Alice.";
        assertEquals(expectedOutput, getOutput());
    }

    @Test
    void testScheduleAppointmentWithPatientPrintsConfirmation() {
        String dateTime = "2025.06.10 09:00";
        doctor.scheduleAppointment(dateTime, patient, "Follow-up");
        String expectedOutput = "Doctor Dr. Bob has scheduled an appointment with Patient Alice at " + dateTime + ".";
        assertEquals(expectedOutput, getOutput());
    }

    @Test
    void testScheduleAppointmentWithNonPatientPrintsError() {
        Doctor anotherDoctor = new Doctor("D002", "Dr. Eve", 50, "Pediatrics", "L54321", 160.0);
        Appointment appointment = doctor.scheduleAppointment("2024.01.17 09:00", anotherDoctor, "Discussion");

        assertNull(appointment, "Appointment should be null when scheduling with non-patient");
        String expectedError = "Error: Doctors can only schedule appointments with Patients.";
        assertEquals(expectedError, getOutput());
    }

    // Helper to get captured output, normalized for line endings
    private String getOutput() {
        return outContent.toString().replace("\r\n", "\n").trim();
    }
}

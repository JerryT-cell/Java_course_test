package project2.advancedTest;

import org.example.project2.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class HospitalTest { // Inherit or include stream capture methods

    private Hospital hospital;
    private Hospital smallHospital; // For capacity tests
    private Patient patient1, patient2;
    private Doctor doctor1, doctor2;
    private Appointment appointment1, appointment2;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String EOL = System.lineSeparator(); // Platform independent newline

    @BeforeEach
    void setUp() {
        hospital = new Hospital(10, 10); // Standard capacity
        smallHospital = new Hospital(1, 1); // Capacity 1 for limits

        patient1 = new Patient("P001", "Alice", 30, "Fever", "MRN123");
        patient2 = new Patient("P002", "Charlie", 25, "Cough", "MRN789");
        doctor1 = new Doctor("D001", "Dr. Bob", 45, "Cardiology", "L12345", 150.0);
        doctor2 = new Doctor("D002", "Dr. Eve", 50, "Pediatrics", "L54321", 160.0);

        // Create appointments (assuming IDs are handled elsewhere or using fixed IDs)
        appointment1 = new Appointment("APT-H1", patient1, doctor1, "2024.03.01 09:00", "Consultation");
        appointment2 = new Appointment("APT-H2", patient2, doctor2, "2024.03.01 10:00", "Checkup");

        System.setOut(new PrintStream(outContent)); // Capture output
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut); // Restore output stream
    }

    // --- Advanced Tests ---

    @Test
    void testAddPersonPrintsConfirmation() throws Exception {
        hospital.addPerson(patient1);
        String expectedOutput = "Added person: ID: P001, Name: Alice, Age: 30, Role: Patient";
        // Use contains because there might be leading/trailing whitespace/newlines depending on implementation
        assertTrue(getOutput().contains(expectedOutput), "Output missing add confirmation for patient");
        outContent.reset(); // Clear buffer for next add

        hospital.addPerson(doctor1);
        expectedOutput = "Added person: ID: D001, Name: Dr. Bob, Age: 45, Role: Doctor";
        assertTrue(getOutput().contains(expectedOutput), "Output missing add confirmation for doctor");
    }

    @Test
    void testAddPersonThrowsExceptionAndDoesNotPrintWhenFull() throws Exception {
        smallHospital.addPerson(patient1); // Fills capacity of 1
        outContent.reset(); // Clear output buffer

        Exception exception = assertThrows(Exception.class, () -> {
            smallHospital.addPerson(doctor1);
        });
        assertEquals("Hospital capacity for persons reached!", exception.getMessage());
        assertEquals("", getOutput(), "Should not print confirmation when add fails due to capacity."); // Check no output on failure
    }


    @Test
    void testSearchPersonPrintsFoundMessage() throws Exception {
        hospital.addPerson(patient1);
        outContent.reset(); // Clear output from addPerson

        Person found = hospital.searchPerson("P001");
        assertNotNull(found);
        assertSame(patient1, found);
        String expectedOutput = "Found person: ID: P001, Name: Alice, Age: 30, Role: Patient";
        assertTrue(getOutput().contains(expectedOutput), "Output missing search found confirmation.");
    }

    @Test
    void testSearchPersonNotFoundPrintsNothing() {
        // Don't add the person
        Person found = hospital.searchPerson("P001");
        assertNull(found);
        assertEquals("", getOutput(), "Should not print anything when person not found.");
    }

    @Test
    void testListAllPersonsEmpty() {
        hospital.listAllPersons();
        String expectedOutput = EOL + "--- Hospital Persons List ---"; // Check header even when empty
        assertEquals(expectedOutput, getRawOutput().trim()); // Use raw output check if needed
    }

    @Test
    void testListAllPersonsWithContent() throws Exception {
        hospital.addPerson(patient1);
        hospital.addPerson(doctor1);
        outContent.reset(); // Clear output from addPerson calls

        hospital.listAllPersons();

        String expectedOutput = EOL + "--- Hospital Persons List ---" + EOL +
                patient1.getDetails() + EOL +
                doctor1.getDetails();

        // Normalize line endings in captured output for comparison
        String actualOutput = getRawOutput().replace("\r\n", "\n");

        // Use assertEquals with normalized strings. Trim trailing whitespace from capture if necessary.
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }


    @Test
    void testAddAppointmentPrintsConfirmation() throws Exception {
        hospital.addAppointment(appointment1);
        String expectedOutput = "Added appointment: " + appointment1.toString();
        assertTrue(getOutput().contains(expectedOutput), "Output missing add appointment confirmation.");
    }

    @Test
    void testAddAppointmentThrowsExceptionAndDoesNotPrintWhenFull() throws Exception {
        smallHospital.addAppointment(appointment1); // Fills capacity of 1
        outContent.reset(); // Clear output buffer

        Exception exception = assertThrows(Exception.class, () -> {
            smallHospital.addAppointment(appointment2);
        });
        assertEquals("Appointment capacity reached!", exception.getMessage());
        assertEquals("", getOutput(), "Should not print confirmation when add appointment fails due to capacity.");
    }


    @Test
    void testListAppointmentsEmpty() {
        hospital.listAppointments();
        String expectedOutput = EOL + "--- Hospital Appointments List ---";
        assertEquals(expectedOutput, getRawOutput().trim());
    }

    @Test
    void testListAppointmentsWithContent() throws Exception {
        hospital.addAppointment(appointment1);
        hospital.addAppointment(appointment2);
        outContent.reset(); // Clear output from addAppointment calls

        hospital.listAppointments();

        String expectedOutput = EOL + "--- Hospital Appointments List ---" + EOL +
                appointment1.toString() + EOL +
                appointment2.toString();

        // Normalize line endings and compare
        String actualOutput = getRawOutput().replace("\r\n", "\n");
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    @Test
    void testSimulateDayEmpty() {
        hospital.simulateDay();
        String expectedOutput = EOL + "Simulating day in the hospital...";
        assertEquals(expectedOutput, getRawOutput().trim());
    }

    @Test
    void testSimulateDayWithAppointmentsPrintsDiagnosisAndDetails() throws Exception {
        hospital.addPerson(patient1); // Needed for diagnose
        hospital.addPerson(doctor1);
        hospital.addPerson(patient2);
        hospital.addPerson(doctor2);
        hospital.addAppointment(appointment1);
        hospital.addAppointment(appointment2);
        outContent.reset(); // Clear output buffer

        hospital.simulateDay();

        String expectedOutput = EOL + "Simulating day in the hospital..." + EOL +
                "Dr. " + doctor1.getName() + " is diagnosing patient " + patient1.getName() + "." + EOL +
                "Appointment details: " + appointment1.toString() + EOL +
                "Dr. " + doctor2.getName() + " is diagnosing patient " + patient2.getName() + "." + EOL +
                "Appointment details: " + appointment2.toString();

        String actualOutput = getRawOutput().replace("\r\n", "\n");
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }


    @Test
    void testPrintHospitalStatisticsEmpty() {
        Hospital.printHospitalStatistics(hospital);
        String expectedOutput = EOL + "--- Hospital Statistics ---" + EOL +
                "Total Persons: 0" + EOL +
                "Number of Patients: 0" + EOL +
                "Number of Doctors: 0" + EOL +
                "Total Appointments: 0";
        String actualOutput = getRawOutput().replace("\r\n", "\n");
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    @Test
    void testPrintHospitalStatisticsWithData() throws Exception {
        hospital.addPerson(patient1);
        hospital.addPerson(patient2);
        hospital.addPerson(doctor1);
        hospital.addAppointment(appointment1);
        hospital.addAppointment(appointment2); // Add two appointments
        outContent.reset(); // Clear output buffer

        Hospital.printHospitalStatistics(hospital);

        String expectedOutput = EOL + "--- Hospital Statistics ---" + EOL +
                "Total Persons: 3" + EOL +         // patient1, patient2, doctor1
                "Number of Patients: 2" + EOL +    // patient1, patient2
                "Number of Doctors: 1" + EOL +     // doctor1
                "Total Appointments: 2";           // appointment1, appointment2

        String actualOutput = getRawOutput().replace("\r\n", "\n");
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    // Helper to get captured output, normalized for line endings
    private String getOutput() {
        return outContent.toString().replace("\r\n", "\n").trim();
    }
    // Helper to get captured output with leading/trailing whitespace but normalized line endings
    private String getRawOutput() {
        return outContent.toString().replace("\r\n", "\n");
    }
}

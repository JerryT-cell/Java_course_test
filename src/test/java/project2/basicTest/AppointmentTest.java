package project2.basicTest;

import org.example.project2.Appointment;
import org.example.project2.Doctor;
import org.example.project2.Patient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


class AppointmentTest {

    private Patient patient;
    private Doctor doctor;
    private Appointment appointment;
    private final String dateTime = "2024.02.20 11:30";
    private final String reason = "Annual Checkup";
    private final String appointmentId = "APT-TEST"; // Use a fixed ID for testing constructor

    @BeforeEach
    void setUp() {
        patient = new Patient("P101", "Charlie", 40, "None", "MRN789");
        doctor = new Doctor("D101", "Dr. Davis", 55, "General Practice", "L98765", 120.0);
        // Use the constructor directly for controlled testing of getters
        appointment = new Appointment(appointmentId, patient, doctor, dateTime, reason);
    }

    @Test
    void testAppointmentConstructorAndGetters() {
        assertEquals(appointmentId, appointment.getAppointmentId());
        assertEquals(patient, appointment.getPatient());
        assertSame(patient, appointment.getPatient()); // Ensure it's the same object
        assertEquals(doctor, appointment.getDoctor());
        assertSame(doctor, appointment.getDoctor());   // Ensure it's the same object
        assertEquals(dateTime, appointment.getDateTime());
        assertEquals(reason, appointment.getReason());
    }

    @Test
    void testGenerateAppointmentIdFormatAndUniqueness() {
        // Reset static counter if possible/necessary (not easily done without modification)
        // Assume it starts from 0 or some value and increments
        String id1 = Appointment.generateAppointmentId();
        String id2 = Appointment.generateAppointmentId();

        assertNotNull(id1);
        assertNotNull(id2);
        assertTrue(id1.startsWith("APT-"));
        assertTrue(id2.startsWith("APT-"));
        assertNotEquals(id1, id2);

        // Check if the numeric part increments (basic check)
        try {
            int num1 = Integer.parseInt(id1.substring(4));
            int num2 = Integer.parseInt(id2.substring(4));
            assertEquals(num1 + 1, num2);
        } catch (NumberFormatException e) {
            fail("Appointment ID numeric part is not parsable.");
        }
    }

    @Test
    void testToString() {
        // Basic check that toString executes and contains key info
        String apptString = appointment.toString();
        assertTrue(apptString.contains("ID='" + appointmentId + "'"));
        assertTrue(apptString.contains("Patient=" + patient.getName()));
        assertTrue(apptString.contains("Doctor=" + doctor.getName()));
        assertTrue(apptString.contains("DateTime='" + dateTime + "'"));
        assertTrue(apptString.contains("Reason='" + reason + "'"));
    }
}


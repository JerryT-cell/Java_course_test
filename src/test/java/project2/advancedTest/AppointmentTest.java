package project2.advancedTest;

import org.example.project2.Appointment;
import org.example.project2.Doctor;
import org.example.project2.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    private Patient patient;
    private Doctor doctor;
    private Appointment appointment;
    private final String dateTime = "2024.02.20 11:30";
    private final String reason = "Annual Checkup";
    private final String appointmentId = "APT-TEST123"; // Fixed test ID

    @BeforeEach
    void setUp() {
        patient = new Patient("P101", "Charlie", 40, "None", "MRN789");
        doctor = new Doctor("D101", "Dr. Davis", 55, "General Practice", "L98765", 120.0);
        appointment = new Appointment(appointmentId, patient, doctor, dateTime, reason);
        // Note: Testing generateAppointmentId sequence across tests is unreliable due to static counter
        // Basic test already checks format and basic uniqueness.
    }

    // Basic tests
    @Test
    void testAppointmentConstructorAndGetters() {
        assertEquals(appointmentId, appointment.getAppointmentId());
        assertEquals(patient, appointment.getPatient());
        assertEquals(doctor, appointment.getDoctor());
        assertEquals(dateTime, appointment.getDateTime());
        assertEquals(reason, appointment.getReason());
    }

    // --- Advanced Tests ---

    @Test
    void testToStringOutputFormat() {
        // Test the exact string format from toString
        String expectedString = "Appointment{" +
                "ID='" + appointmentId + '\'' +
                ", Patient=" + patient.getName() + // Uses patient's name
                ", Doctor=" + doctor.getName() +   // Uses doctor's name
                ", DateTime='" + dateTime + '\'' +
                ", Reason='" + reason + '\'' +
                '}';
        assertEquals(expectedString, appointment.toString());
    }

    @Test
    void testGenerateAppointmentIdStartsCorrectly() {
        // This test assumes the counter starts near 0 or resets somehow
        // Or just checks format again for robustness
        String id = Appointment.generateAppointmentId();
        assertTrue(id.matches("APT-\\d+"), "Generated ID does not match format APT-<number>");
    }

    @Test
    void testGenerateAppointmentIdIncrements() {
        // Generate two IDs and check if the second is numerically greater than the first
        String id1 = Appointment.generateAppointmentId();
        String id2 = Appointment.generateAppointmentId();
        try {
            int num1 = Integer.parseInt(id1.substring(4));
            int num2 = Integer.parseInt(id2.substring(4));
            assertTrue(num2 > num1, "Second generated ID number should be greater than the first.");
        } catch (NumberFormatException | IndexOutOfBoundsException e){
            fail("Could not parse numbers from generated IDs: " + id1 + ", " + id2);
        }
    }
}

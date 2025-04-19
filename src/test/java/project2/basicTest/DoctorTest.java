package project2.basicTest;

import org.example.project2.Appointment;
import org.example.project2.Doctor;
import org.example.project2.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    private Doctor doctor;
    private Patient patient; // Needed for scheduling and diagnose tests

    @BeforeEach
    void setUp() {
        doctor = new Doctor("D001", "Dr. Bob", 45, "Cardiology", "L12345", 150.0);
        patient = new Patient("P001", "Alice", 30, "Fever", "MRN123");
    }

    @Test
    void testDoctorConstructorAndGetters() {
        assertEquals("D001", doctor.getId());
        assertEquals("Dr. Bob", doctor.getName());
        assertEquals(45, doctor.getAge());
        assertEquals("Cardiology", doctor.getSpecialty());
        assertEquals("L12345", doctor.getLicenseNumber());
        assertEquals(150.0, doctor.getConsultationFee());
    }

    @Test
    void testDoctorSetters() {
        doctor.setName("Dr. Robert");
        assertEquals("Dr. Robert", doctor.getName());
        doctor.setAge(46);
        assertEquals(46, doctor.getAge());
        doctor.setSpecialty("Neurology");
        assertEquals("Neurology", doctor.getSpecialty());
        doctor.setLicenseNumber("L67890");
        assertEquals("L67890", doctor.getLicenseNumber());
        doctor.setConsultationFee(175.0);
        assertEquals(175.0, doctor.getConsultationFee());
    }

    @Test
    void testGetRole() {
        assertEquals("Doctor", doctor.getRole());
    }

    @Test
    void testGetDetails() {
        // Basic check: ensure it runs and includes role
        assertTrue(doctor.getDetails().contains("ID: D001"));
        assertTrue(doctor.getDetails().contains("Name: Dr. Bob"));
        assertTrue(doctor.getDetails().contains("Age: 45"));
        assertTrue(doctor.getDetails().contains("Role: Doctor"));
    }

    @Test
    void testDiagnoseRunsWithoutError() {
        // Basic test: Just ensures the method can be called without throwing an exception
        assertDoesNotThrow(() -> doctor.diagnose(patient));
    }

    @Test
    void testScheduleAppointmentWithPatientSuccessfully() {
        Appointment appointment = doctor.scheduleAppointment("2024.01.15 14:00", patient, "Follow-up");
        assertNotNull(appointment);
        assertEquals(patient, appointment.getPatient());
        assertEquals(doctor, appointment.getDoctor());
        assertEquals("2024.01.15 14:00", appointment.getDateTime());
        assertEquals("Follow-up", appointment.getReason());
    }

    @Test
    void testScheduleAppointmentWithNonPatientFails() {
        Doctor anotherDoctor = new Doctor("D002", "Dr. Eve", 50, "Pediatrics", "L54321", 160.0);
        // Expect null return based on current implementation
        Appointment appointment = doctor.scheduleAppointment("2024.01.17 09:00", anotherDoctor, "Discussion");
        assertNull(appointment);
    }
}

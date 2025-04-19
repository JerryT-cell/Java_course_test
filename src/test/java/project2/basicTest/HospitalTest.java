package project2.basicTest;

import org.example.project2.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

class HospitalTest {

    private Hospital hospital;
    private Patient patient1;
    private Doctor doctor1;
    private Appointment appointment1;

    @BeforeEach
    void setUp() {
        // Create hospital with small capacity for testing limits
        hospital = new Hospital(2, 2); // 2 persons, 2 appointments
        patient1 = new Patient("P001", "Alice", 30, "Fever", "MRN123");
        doctor1 = new Doctor("D001", "Dr. Bob", 45, "Cardiology", "L12345", 150.0);
        appointment1 = new Appointment("APT-H1", patient1, doctor1, "2024.03.01 09:00", "Consultation");
    }

    @Test
    void testAddPersonSuccessfully() throws Exception {
        hospital.addPerson(patient1);
        // Use searchPerson to verify addition
        Person found = hospital.searchPerson("P001");
        assertNotNull(found);
        assertEquals(patient1, found);

        hospital.addPerson(doctor1);
        Person foundDoc = hospital.searchPerson("D001");
        assertNotNull(foundDoc);
        assertEquals(doctor1, foundDoc);
    }

    @Test
    void testAddPersonThrowsExceptionWhenFull() throws Exception {
        Patient patient2 = new Patient("P002", "Charlie", 25, "Cough", "MRN789");
        hospital.addPerson(patient1); // Add 1st person
        hospital.addPerson(doctor1);  // Add 2nd person (capacity reached)

        // Try to add 3rd person
        Exception exception = assertThrows(Exception.class, () -> {
            hospital.addPerson(patient2);
        });

        assertEquals("Hospital capacity for persons reached!", exception.getMessage());
    }

    @Test
    void testSearchPersonFound() throws Exception {
        hospital.addPerson(patient1);
        hospital.addPerson(doctor1);

        Person foundPatient = hospital.searchPerson("P001");
        assertNotNull(foundPatient);
        assertEquals("Alice", foundPatient.getName());
        assertSame(patient1, foundPatient); // Check if it's the same object

        Person foundDoctor = hospital.searchPerson("D001");
        assertNotNull(foundDoctor);
        assertEquals("Dr. Bob", foundDoctor.getName());
        assertSame(doctor1, foundDoctor); // Check if it's the same object
    }

    @Test
    void testSearchPersonNotFound() throws Exception {
        hospital.addPerson(patient1);
        Person found = hospital.searchPerson("P999"); // Non-existent ID
        assertNull(found);
    }
    @Test
    void testSearchPersonEmptyHospital() {
        Person found = hospital.searchPerson("P001");
        assertNull(found);
    }


    @Test
    void testAddAppointmentSuccessfully() throws Exception {
        // Prerequisite: Add patient and doctor to hospital if addAppointment checks existence (it doesn't in this code)
        hospital.addPerson(patient1);
        hospital.addPerson(doctor1);

        hospital.addAppointment(appointment1);
        // No public getter for appointments, basic test ensures no exception
        // Advanced test might need reflection or a getter method
        assertTrue(true); // Placeholder assertion if no exception
    }

    @Test
    void testAddAppointmentThrowsExceptionWhenFull() throws Exception {
        // Prerequisite: Add patient and doctor
        hospital.addPerson(patient1);
        hospital.addPerson(doctor1);

        Appointment appointment2 = new Appointment("APT-H2", patient1, doctor1, "2024.03.02 10:00", "Follow-up");
        Appointment appointment3 = new Appointment("APT-H3", patient1, doctor1, "2024.03.03 11:00", "Test Results");

        hospital.addAppointment(appointment1); // Add 1st appointment
        hospital.addAppointment(appointment2); // Add 2nd appointment (capacity reached)

        // Try to add 3rd appointment
        Exception exception = assertThrows(Exception.class, () -> {
            hospital.addAppointment(appointment3);
        });

        assertEquals("Appointment capacity reached!", exception.getMessage());
    }

    @Test
    void testSimulateDayRunsWithoutError() throws Exception {
        hospital.addPerson(patient1);
        hospital.addPerson(doctor1);
        hospital.addAppointment(appointment1);

        // Basic test: ensures the method executes without exception
        assertDoesNotThrow(() -> hospital.simulateDay());
    }

    @Test
    void testPrintHospitalStatisticsRunsWithoutError() throws Exception {
        // Basic test: ensures the static method executes without exception
        // Needs an instance, although it's static
        hospital.addPerson(patient1);
        hospital.addPerson(doctor1);
        hospital.addAppointment(appointment1);
        assertDoesNotThrow(() -> Hospital.printHospitalStatistics(hospital));
    }
}


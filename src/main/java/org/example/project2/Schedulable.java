package org.example.project2;

public interface Schedulable {
    /**
     * Schedule an appointment with the other party (must be of the complementary type).
     * @param dateTime The date and time for the appointment.
     * @param otherParty The other party (a Doctor for a Patient, or a Patient for a Doctor).
     * @param reason The reason for the appointment.
     * @return An Appointment object if scheduling is successful; otherwise, null.
     */
    Appointment scheduleAppointment(String dateTime, Person otherParty, String reason);
}


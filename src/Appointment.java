import exception.EmptyInfoException;
import exception.InvalidMobileException;

/**
 * Represents a medical appointment, containing patient information, appointment time,
 * and the associated doctor. It includes validation logic for all appointment details.
 */
public class Appointment {
    private String patientName;    // Name of the patient making the appointment
    private String patientMobile;  // Mobile phone number of the patient (for contact)
    private String timeSlot;       // Scheduled time of the appointment (format: HH:mm)
    private HealthProfessional doctor;  // The doctor assigned to this appointment

    /**
     * Default constructor for Appointment class
     */
    public Appointment() {}

    /**
     * Parameterized constructor to initialize an appointment with validation.
     *
     * @param patientName     Name of the patient
     * @param patientMobile   Mobile number of the patient
     * @param timeSlot        Scheduled time (HH:mm format)
     * @param doctor          The doctor for the appointment
     * @throws EmptyInfoException    If any required information is empty
     * @throws InvalidMobileException If the mobile number format is invalid
     */
    public Appointment(String patientName, String patientMobile, String timeSlot, HealthProfessional doctor)
            throws EmptyInfoException, InvalidMobileException {
        setPatientName(patientName);
        setPatientMobile(patientMobile);
        setTimeSlot(timeSlot);
        setDoctor(doctor);
    }

    /**
     * Gets the patient's name
     * @return The patient's name
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * Sets the patient's name with validation (cannot be empty).
     *
     * @param patientName The patient's name to set
     * @throws EmptyInfoException If the patient's name is null or blank
     */
    public void setPatientName(String patientName) throws EmptyInfoException {
        if (patientName == null || patientName.trim().isEmpty()) {
            throw new EmptyInfoException("The patient's name cannot be left blank");
        }
        this.patientName = patientName;
    }

    /**
     * Gets the patient's mobile number
     * @return The patient's mobile number
     */
    public String getPatientMobile() {
        return patientMobile;
    }

    /**
     * Sets the patient's mobile number with validation (must be 11-digit).
     *
     * @param patientMobile The mobile number to set
     * @throws InvalidMobileException If the mobile number format is invalid
     */
    public void setPatientMobile(String patientMobile) throws InvalidMobileException {
        if (!patientMobile.matches("^1[3-9]\\d{9}$")) {
            throw new InvalidMobileException("Invalid mobile phone number format (requires 11-digit valid number, current: " + patientMobile + ")");
        }
        this.patientMobile = patientMobile;
    }

    /**
     * Gets the appointment time slot
     * @return The scheduled time slot (HH:mm)
     */
    public String getTimeSlot() {
        return timeSlot;
    }

    /**
     * Sets the appointment time slot with validation (non-empty and HH:mm format).
     *
     * @param timeSlot The time slot to set (HH:mm)
     * @throws EmptyInfoException If the time slot is null or blank
     * @throws IllegalArgumentException If the time format is invalid
     */
    public void setTimeSlot(String timeSlot) throws EmptyInfoException {
        if (timeSlot == null || timeSlot.trim().isEmpty()) {
            throw new EmptyInfoException("The appointment time cannot be empty");
        }
        if (!timeSlot.matches("^([01]\\d|2[0-3]):[0-5]\\d$")) {
            throw new IllegalArgumentException("Invalid time format (required: HH:mm, e.g., 09:30, current: " + timeSlot + ")");
        }
        this.timeSlot = timeSlot;
    }

    /**
     * Gets the doctor assigned to this appointment
     * @return The associated HealthProfessional object
     */
    public HealthProfessional getDoctor() {
        return doctor;
    }

    /**
     * Sets the doctor for the appointment with validation (cannot be null).
     *
     * @param doctor The doctor to assign
     * @throws EmptyInfoException If the doctor is null
     */
    public void setDoctor(HealthProfessional doctor) throws EmptyInfoException {
        if (doctor == null) {
            throw new EmptyInfoException("An appointment cannot be created without selecting a doctor");
        }
        this.doctor = doctor;
    }

    /**
     * Prints all details of the appointment, including patient info, time, and doctor details.
     */
    public void printAppointmentInfo() {
        System.out.println("=== Appointment Details ===");
        System.out.println("Patient: " + patientName + " | Mobile: " + patientMobile);
        System.out.println("Appointment Time: " + timeSlot + " | Assigned Doctor:");
        doctor.printProfessionalInfo();  // Polymorphic call to doctor's info method
        System.out.println("=================");
    }
}
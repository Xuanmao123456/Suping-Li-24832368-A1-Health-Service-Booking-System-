import exception.AppointmentConflictException;
import exception.EmptyInfoException;
import exception.InvalidMobileException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class to demonstrate the health service appointment system.
 * Tests two core functionalities:
 * 1. Displaying details of different health professionals (polymorphism example)
 * 2. Managing appointments (create, print, cancel) with exception handling
 */
public class AssignmentOne {
    // List to store all appointments (simulates a simple database)
    private static final List<Appointment> appointments = new ArrayList<>();

    /**
     * Main method: entry point of the program.
     * Executes test cases for doctor information display and appointment management.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Part 1: Test doctor information display (polymorphism demonstration)
        System.out.println("===== Part 1: Health Professional Details =====");
        testDoctorDisplay();

        // Part 2: Test appointment management (create, print, cancel)
        System.out.println("\n===== Part 2: Appointment Management =====");
        testAppointmentManagement();
    }

    /**
     * Tests creation and display of different health professionals.
     * Demonstrates polymorphism by storing subclasses in a superclass array.
     */
    private static void testDoctorDisplay() {
        try {
            // Create 2 general practitioners with different attributes
            GeneralPractitioner gp1 = new GeneralPractitioner(
                    1, "Dr. Sarah Johnson", 8, "Community General Practice", true
            );
            GeneralPractitioner gp2 = new GeneralPractitioner(
                    2, "Dr. Michael Chen", 5, "Family Medicine", false
            );

            // Create 2 pediatricians with different specialties and age limits
            Pediatrician pedia1 = new Pediatrician(
                    3, "Dr. Emily Rodriguez", 6, "Pediatric Respiratory Medicine", 12
            );
            Pediatrician pedia2 = new Pediatrician(
                    4, "Dr. David Kim", 4, "Pediatric Gastroenterology", 10
            );

            // Polymorphism: store all doctors in a HealthProfessional array
            HealthProfessional[] doctors = {gp1, gp2, pedia1, pedia2};

            // Print details for each doctor (automatically uses subclass implementation)
            for (HealthProfessional doctor : doctors) {
                doctor.printProfessionalInfo();
                System.out.println("-----------------------------------");
            }
        } catch (EmptyInfoException | IllegalArgumentException e) {
            System.err.println("Error creating doctors: " + e.getMessage());
        }
    }

    /**
     * Tests appointment management workflows:
     * - Creating valid appointments
     * - Printing all appointments
     * - Canceling an appointment by mobile number
     * Handles exceptions for invalid data or conflicts.
     */
    private static void testAppointmentManagement() {
        try {
            // Reuse doctors from Part 1 (simulates selecting from a doctor database)
            GeneralPractitioner gp1 = new GeneralPractitioner(
                    1, "Dr. Sarah Johnson", 8, "Community General Practice", true
            );
            Pediatrician pedia1 = new Pediatrician(
                    3, "Dr. Emily Rodriguez", 6, "Pediatric Respiratory Medicine", 12
            );

            // Create 4 valid appointments (no time conflicts)
            createAppointment("Alex Taylor", "13800138000", "09:30", gp1);
            createAppointment("Jamie Lee", "13900139000", "10:15", gp1);
            createAppointment("Sam Wilson", "13700137000", "14:00", pedia1);
            createAppointment("Casey Zhang", "13600136000", "15:30", pedia1);

            // Uncomment to test time conflict (same doctor + same time)
            // createAppointment("Conflict Test", "13500135000", "09:30", gp1);

            // Print all appointments after creation
            System.out.println("\n[All Appointments After Creation]");
            printAllAppointments();

            // Cancel an appointment using patient's mobile number
            cancelAppointment("13800138000");

            // Print remaining appointments after cancellation
            System.out.println("\n[Appointments After Cancellation]");
            printAllAppointments();

        } catch (EmptyInfoException | InvalidMobileException | AppointmentConflictException e) {
            System.err.println("Appointment operation failed: " + e.getMessage());
        }
    }

    /**
     * Creates a new appointment after checking for time conflicts with existing ones.
     * A conflict occurs if the same doctor is booked at the same time.
     *
     * @param patientName     Patient's full name
     * @param mobile          Patient's mobile number (valid format)
     * @param timeSlot        Scheduled time (HH:mm)
     * @param doctor          Assigned doctor
     * @throws EmptyInfoException    If any required info is null/blank
     * @throws InvalidMobileException If mobile format is invalid
     * @throws AppointmentConflictException If doctor is already booked at the time
     */
    private static void createAppointment(String patientName, String mobile, String timeSlot, HealthProfessional doctor)
            throws EmptyInfoException, InvalidMobileException, AppointmentConflictException {
        // Check for time conflicts: same doctor + same time slot
        for (Appointment existingApp : appointments) {
            if (existingApp.getDoctor().getId() == doctor.getId()
                    && existingApp.getTimeSlot().equals(timeSlot)) {
                throw new AppointmentConflictException(
                        "Conflict: Dr. " + doctor.getName() + " is already booked at " + timeSlot
                );
            }
        }

        // No conflicts: create and store the new appointment
        Appointment newAppointment = new Appointment(patientName, mobile, timeSlot, doctor);
        appointments.add(newAppointment);
        System.out.println("Appointment created successfully for: " + patientName);
    }

    /**
     * Prints details of all existing appointments.
     * Displays a message if no appointments are found.
     */
    private static void printAllAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found in the system.");
            return;
        }
        for (Appointment app : appointments) {
            app.printAppointmentInfo();
        }
    }

    /**
     * Cancels an appointment by searching for the patient's mobile number.
     * Validates the mobile format before searching.
     *
     * @param mobile The patient's mobile number to find the appointment
     * @throws InvalidMobileException If the mobile number format is invalid
     */
    private static void cancelAppointment(String mobile) throws InvalidMobileException {
        // Validate mobile format first (consistent with Appointment's rules)
        if (!mobile.matches("^1[3-9]\\d{9}$")) {
            throw new InvalidMobileException("Cannot cancel: invalid mobile number format");
        }

        // Search for and remove the appointment with the matching mobile
        boolean isRemoved = false;
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getPatientMobile().equals(mobile)) {
                appointments.remove(i);
                isRemoved = true;
                break; // Stop after first match (assuming unique mobile per appointment)
            }
        }

        // Print result of cancellation attempt
        if (isRemoved) {
            System.out.println("Appointment canceled successfully (mobile: " + mobile + ")");
        } else {
            System.out.println("No appointment found with mobile number: " + mobile);
        }
    }
}
import exception.EmptyInfoException;

/**
 * A subclass of HealthProfessional representing a pediatrician (children's doctor).
 * Contains specific attributes for pediatric practice, including the maximum age of patients they treat
 * and their specialized field in pediatric medicine.
 */
public class Pediatrician extends HealthProfessional {
    private int maxAge;       // Maximum age of patients the pediatrician can treat (1-18 years)
    private String specialty; // Specialized field in pediatrics (e.g., "Pediatric Respiratory Medicine")

    /**
     * Default constructor for Pediatrician
     */
    public Pediatrician() {}

    /**
     * Parameterized constructor to initialize a Pediatrician with validation.
     *
     * @param id              Unique identifier of the pediatrician
     * @param name            Name of the pediatrician
     * @param workExperience  Years of work experience
     * @param specialty       Pediatric specialty (cannot be empty)
     * @param maxAge          Maximum patient age (must be 1-18 years)
     * @throws EmptyInfoException If required information (like specialty) is empty
     */
    public Pediatrician(int id, String name, int workExperience, String specialty, int maxAge) throws EmptyInfoException {
        super(id, name, workExperience);
        setSpecialty(specialty);
        setMaxAge(maxAge);
    }

    /**
     * Gets the maximum age of patients the pediatrician treats
     * @return Maximum patient age
     */
    public int getMaxAge() {
        return maxAge;
    }

    /**
     * Sets the maximum patient age with validation (must be between 1-18 years).
     *
     * @param maxAge The maximum age to set
     * @throws IllegalArgumentException If age is not in the 1-18 range
     */
    public void setMaxAge(int maxAge) {
        if (maxAge <= 0 || maxAge > 18) {
            throw new IllegalArgumentException("Pediatric patient age limit must be between 1-18 years (current value: " + maxAge + ")");
        }
        this.maxAge = maxAge;
    }

    /**
     * Gets the pediatrician's specialized field
     * @return The specialty (e.g., "Pediatric Respiratory Medicine")
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Sets the pediatric specialty with validation (cannot be empty).
     *
     * @param specialty The specialty to set
     * @throws EmptyInfoException If the specialty is null or blank
     */
    public void setSpecialty(String specialty) throws EmptyInfoException {
        if (specialty == null || specialty.trim().isEmpty()) {
            throw new EmptyInfoException("Pediatrician's specialty cannot be empty (e.g., 'Pediatric Respiratory Medicine')");
        }
        this.specialty = specialty;
    }

    /**
     * Returns a description of the services provided, tailored to the maximum patient age.
     *
     * @return Service description as a string
     */
    @Override
    public String getServiceDescription() {
        return "Provides diagnosis of common illnesses, vaccination guidance, and growth assessment for children under " + maxAge + " years old";
    }

    /**
     * Displays the scope of pediatric services, including work experience.
     */
    @Override
    public void displayServiceScope() {
        System.out.println("[Pediatric Service Scope]: " + getServiceDescription()
                + ", Work Experience: " + getWorkExperience() + " years");
    }

    /**
     * Prints detailed information about the pediatrician, including ID, name, specialty,
     * maximum patient age, and service scope.
     */
    @Override
    public void printProfessionalInfo() {
        System.out.println("=== Pediatrician Information ===");
        System.out.println("ID: " + getId() + " | Name: " + getName());
        System.out.println("Specialty: " + specialty + " | Maximum Patient Age: " + maxAge + " years");
        displayServiceScope();
    }
}
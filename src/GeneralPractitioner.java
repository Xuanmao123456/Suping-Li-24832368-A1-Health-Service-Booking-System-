import exception.EmptyInfoException;

/**
 * A subclass of HealthProfessional representing a general practitioner.
 * Contains specific attributes and methods for general practice, including
 * whether they accept child patients and their medical specialty.
 */
public class GeneralPractitioner extends HealthProfessional {
    private boolean acceptsChildren;  // Indicates if the practitioner treats children under 14
    private String specialty;         // Medical specialty (e.g., "Community General Practice")

    /**
     * Default constructor for GeneralPractitioner
     */
    public GeneralPractitioner() {}

    /**
     * Parameterized constructor to initialize a GeneralPractitioner with validation.
     *
     * @param id              Unique identifier of the practitioner
     * @param name            Name of the practitioner
     * @param workExperience  Years of work experience
     * @param specialty       Medical specialty (cannot be empty)
     * @param acceptsChildren Whether the practitioner accepts child patients
     * @throws EmptyInfoException If required information (like specialty) is empty
     */
    public GeneralPractitioner(int id, String name, int workExperience, String specialty, boolean acceptsChildren) throws EmptyInfoException {
        super(id, name, workExperience);
        setSpecialty(specialty);
        this.acceptsChildren = acceptsChildren;
    }

    /**
     * Checks if the practitioner accepts child patients
     * @return true if children are accepted, false otherwise
     */
    public boolean isAcceptsChildren() {
        return acceptsChildren;
    }

    /**
     * Sets whether the practitioner accepts child patients
     * @param acceptsChildren true to accept children, false otherwise
     */
    public void setAcceptsChildren(boolean acceptsChildren) {
        this.acceptsChildren = acceptsChildren;
    }

    /**
     * Gets the medical specialty of the practitioner
     * @return The specialty (e.g., "Community General Practice")
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Sets the medical specialty with validation (cannot be empty).
     *
     * @param specialty The specialty to set
     * @throws EmptyInfoException If the specialty is null or blank
     */
    public void setSpecialty(String specialty) throws EmptyInfoException {
        if (specialty == null || specialty.trim().isEmpty()) {
            throw new EmptyInfoException("General practitioner's specialty cannot be empty (e.g., 'Community General Practice')");
        }
        this.specialty = specialty;
    }

    /**
     * Returns a description of the services provided by the general practitioner.
     * Includes additional info if they accept child patients.
     *
     * @return Service description as a string
     */
    @Override
    public String getServiceDescription() {
        return "Provides general disease diagnosis, chronic disease management, health check-ups"
                + (acceptsChildren ? ", and can treat children under 14 years old" : "");
    }

    /**
     * Displays the scope of services provided, including work experience.
     */
    @Override
    public void displayServiceScope() {
        System.out.println("[General Practice Service Scope]: " + getServiceDescription()
                + ", Work Experience: " + getWorkExperience() + " years");
    }

    /**
     * Prints detailed information about the general practitioner, including ID, name,
     * specialty, child patient policy, and service scope.
     */
    @Override
    public void printProfessionalInfo() {
        System.out.println("=== General Practitioner Information ===");
        System.out.println("ID: " + getId() + " | Name: " + getName());
        System.out.println("Specialty: " + specialty + " | Accepts Children: " + (acceptsChildren ? "Yes" : "No"));
        displayServiceScope();
    }
}
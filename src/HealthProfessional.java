import exception.EmptyInfoException;

/**
 * An abstract base class representing a health professional, implementing the HealthService interface.
 * Contains core attributes and methods common to all health professionals, and enforces subclasses
 * to implement specific functionality through abstract methods.
 */
public abstract class HealthProfessional implements HealthService {
    private int id;                 // Unique identifier for the health professional
    private String name;            // Full name of the health professional
    private int workExperience;     // Number of years of work experience

    /**
     * Default constructor for HealthProfessional
     */
    public HealthProfessional() {}

    /**
     * Parameterized constructor to initialize a HealthProfessional with validation.
     *
     * @param id              Unique identifier (must be positive)
     * @param name            Professional's name (cannot be empty)
     * @param workExperience  Years of work experience (cannot be negative)
     * @throws EmptyInfoException If required information (like name) is empty
     */
    public HealthProfessional(int id, String name, int workExperience) throws EmptyInfoException {
        setId(id);
        setName(name);
        setWorkExperience(workExperience);
    }

    /**
     * Gets the unique ID of the health professional
     * @return The professional's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID with validation (must be a positive integer).
     *
     * @param id The ID to set
     * @throws IllegalArgumentException If the ID is non-positive
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Doctor ID must be a positive integer (current value: " + id + ")");
        }
        this.id = id;
    }

    /**
     * Gets the name of the health professional
     * @return The professional's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name with validation (cannot be null or blank).
     *
     * @param name The name to set
     * @throws EmptyInfoException If the name is null or blank
     */
    public void setName(String name) throws EmptyInfoException {
        if (name == null || name.trim().isEmpty()) {
            throw new EmptyInfoException("Doctor's name cannot be empty");
        }
        this.name = name;
    }

    /**
     * Gets the number of years of work experience
     * @return Years of experience
     */
    public int getWorkExperience() {
        return workExperience;
    }

    /**
     * Sets the work experience with validation (cannot be negative).
     *
     * @param workExperience Years of experience to set
     * @throws IllegalArgumentException If experience is negative
     */
    public void setWorkExperience(int workExperience) {
        if (workExperience < 0) {
            throw new IllegalArgumentException("Work experience cannot be a negative number (current value: " + workExperience + ")");
        }
        this.workExperience = workExperience;
    }

    /**
     * Abstract method to print detailed information about the health professional.
     * Must be implemented by all subclasses to provide profession-specific details.
     */
    public abstract void printProfessionalInfo();
}
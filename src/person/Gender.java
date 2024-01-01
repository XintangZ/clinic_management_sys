package src.person;

/**
 * Enum class Gender
 * 
 * @version 1.00
 * @since 2023-12-18
 * @author Team 6
 */

public enum Gender {
    F("Female"), M("Male");
    
    private String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }
}

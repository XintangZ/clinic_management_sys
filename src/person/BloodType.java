package src.person;

/**
 * Enum class BloodType
 * 
 * @version 1.00
 * @since 2023-12-25
 * @author Team 6
 */

public enum BloodType {
    A("Type A"), B("Type B"), AB("Type AB"), O("Type O");
    
    private String bloodType;

    private BloodType(String aBloodType) {
        this.bloodType = aBloodType;
    }

    public String getBloodType() {
        return this.bloodType;
    }
}

package nurse_db;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "NURSE_INFO".
 */
public class NurseInfo {

    /** Not-null value. */
    private String nurse_id;
    /** Not-null value. */
    private String nurse_gender;
    private int nurse_age;
    /** Not-null value. */
    private String nurse_major;
    /** Not-null value. */
    private String nurse_name;
    /** Not-null value. */
    private String nurse_photo;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public NurseInfo() {
    }

    public NurseInfo(String nurse_id) {
        this.nurse_id = nurse_id;
    }

    public NurseInfo(String nurse_id, String nurse_gender, int nurse_age, String nurse_major, String nurse_name, String nurse_photo) {
        this.nurse_id = nurse_id;
        this.nurse_gender = nurse_gender;
        this.nurse_age = nurse_age;
        this.nurse_major = nurse_major;
        this.nurse_name = nurse_name;
        this.nurse_photo = nurse_photo;
    }

    /** Not-null value. */
    public String getNurse_id() {
        return nurse_id;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNurse_id(String nurse_id) {
        this.nurse_id = nurse_id;
    }

    /** Not-null value. */
    public String getNurse_gender() {
        return nurse_gender;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNurse_gender(String nurse_gender) {
        this.nurse_gender = nurse_gender;
    }

    public int getNurse_age() {
        return nurse_age;
    }

    public void setNurse_age(int nurse_age) {
        this.nurse_age = nurse_age;
    }

    /** Not-null value. */
    public String getNurse_major() {
        return nurse_major;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNurse_major(String nurse_major) {
        this.nurse_major = nurse_major;
    }

    /** Not-null value. */
    public String getNurse_name() {
        return nurse_name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNurse_name(String nurse_name) {
        this.nurse_name = nurse_name;
    }

    /** Not-null value. */
    public String getNurse_photo() {
        return nurse_photo;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNurse_photo(String nurse_photo) {
        this.nurse_photo = nurse_photo;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}

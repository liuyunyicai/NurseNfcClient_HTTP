package nurse_db;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "HOUSE_INFO".
 */
public class HouseInfo {

    /** Not-null value. */
    private String house_id;
    /** Not-null value. */
    private String nurse_id;
    /** Not-null value. */
    private String house_state;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public HouseInfo() {
    }

    public HouseInfo(String house_id) {
        this.house_id = house_id;
    }

    public HouseInfo(String house_id, String nurse_id, String house_state) {
        this.house_id = house_id;
        this.nurse_id = nurse_id;
        this.house_state = house_state;
    }

    /** Not-null value. */
    public String getHouse_id() {
        return house_id;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setHouse_id(String house_id) {
        this.house_id = house_id;
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
    public String getHouse_state() {
        return house_state;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setHouse_state(String house_state) {
        this.house_state = house_state;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
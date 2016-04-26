package nurse_db;

import nurse_db.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "BED_INFO".
 */
public class BedInfo {

    /** Not-null value. */
    private String bed_id;
    /** Not-null value. */
    private String house_id;
    /** Not-null value. */
    private String patient_id;
    /** Not-null value. */
    private String bed_state;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient BedInfoDao myDao;

    private PatientInfo patientInfo;
    private String patientInfo__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public BedInfo() {
    }

    public BedInfo(String bed_id) {
        this.bed_id = bed_id;
    }

    public BedInfo(String bed_id, String house_id, String patient_id, String bed_state) {
        this.bed_id = bed_id;
        this.house_id = house_id;
        this.patient_id = patient_id;
        this.bed_state = bed_state;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBedInfoDao() : null;
    }

    /** Not-null value. */
    public String getBed_id() {
        return bed_id;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setBed_id(String bed_id) {
        this.bed_id = bed_id;
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
    public String getPatient_id() {
        return patient_id;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    /** Not-null value. */
    public String getBed_state() {
        return bed_state;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setBed_state(String bed_state) {
        this.bed_state = bed_state;
    }

    /** To-one relationship, resolved on first access. */
    public PatientInfo getPatientInfo() {
        String __key = this.patient_id;
        if (patientInfo__resolvedKey == null || patientInfo__resolvedKey != __key) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PatientInfoDao targetDao = daoSession.getPatientInfoDao();
            PatientInfo patientInfoNew = targetDao.load(__key);
            synchronized (this) {
                patientInfo = patientInfoNew;
            	patientInfo__resolvedKey = __key;
            }
        }
        return patientInfo;
    }

    public void setPatientInfo(PatientInfo patientInfo) {
        if (patientInfo == null) {
            throw new DaoException("To-one property 'patient_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.patientInfo = patientInfo;
            patient_id = patientInfo.getPatient_id();
            patientInfo__resolvedKey = patient_id;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}

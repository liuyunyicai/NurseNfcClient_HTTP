package nurse_db;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import nurse_db.NurseInfo;
import nurse_db.BedInfo;
import nurse_db.PatientInfo;
import nurse_db.HouseInfo;
import nurse_db.TemperatureInfo;

import nurse_db.NurseInfoDao;
import nurse_db.BedInfoDao;
import nurse_db.PatientInfoDao;
import nurse_db.HouseInfoDao;
import nurse_db.TemperatureInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig nurseInfoDaoConfig;
    private final DaoConfig bedInfoDaoConfig;
    private final DaoConfig patientInfoDaoConfig;
    private final DaoConfig houseInfoDaoConfig;
    private final DaoConfig temperatureInfoDaoConfig;

    private final NurseInfoDao nurseInfoDao;
    private final BedInfoDao bedInfoDao;
    private final PatientInfoDao patientInfoDao;
    private final HouseInfoDao houseInfoDao;
    private final TemperatureInfoDao temperatureInfoDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        nurseInfoDaoConfig = daoConfigMap.get(NurseInfoDao.class).clone();
        nurseInfoDaoConfig.initIdentityScope(type);

        bedInfoDaoConfig = daoConfigMap.get(BedInfoDao.class).clone();
        bedInfoDaoConfig.initIdentityScope(type);

        patientInfoDaoConfig = daoConfigMap.get(PatientInfoDao.class).clone();
        patientInfoDaoConfig.initIdentityScope(type);

        houseInfoDaoConfig = daoConfigMap.get(HouseInfoDao.class).clone();
        houseInfoDaoConfig.initIdentityScope(type);

        temperatureInfoDaoConfig = daoConfigMap.get(TemperatureInfoDao.class).clone();
        temperatureInfoDaoConfig.initIdentityScope(type);

        nurseInfoDao = new NurseInfoDao(nurseInfoDaoConfig, this);
        bedInfoDao = new BedInfoDao(bedInfoDaoConfig, this);
        patientInfoDao = new PatientInfoDao(patientInfoDaoConfig, this);
        houseInfoDao = new HouseInfoDao(houseInfoDaoConfig, this);
        temperatureInfoDao = new TemperatureInfoDao(temperatureInfoDaoConfig, this);

        registerDao(NurseInfo.class, nurseInfoDao);
        registerDao(BedInfo.class, bedInfoDao);
        registerDao(PatientInfo.class, patientInfoDao);
        registerDao(HouseInfo.class, houseInfoDao);
        registerDao(TemperatureInfo.class, temperatureInfoDao);
    }
    
    public void clear() {
        nurseInfoDaoConfig.getIdentityScope().clear();
        bedInfoDaoConfig.getIdentityScope().clear();
        patientInfoDaoConfig.getIdentityScope().clear();
        houseInfoDaoConfig.getIdentityScope().clear();
        temperatureInfoDaoConfig.getIdentityScope().clear();
    }

    public NurseInfoDao getNurseInfoDao() {
        return nurseInfoDao;
    }

    public BedInfoDao getBedInfoDao() {
        return bedInfoDao;
    }

    public PatientInfoDao getPatientInfoDao() {
        return patientInfoDao;
    }

    public HouseInfoDao getHouseInfoDao() {
        return houseInfoDao;
    }

    public TemperatureInfoDao getTemperatureInfoDao() {
        return temperatureInfoDao;
    }

}

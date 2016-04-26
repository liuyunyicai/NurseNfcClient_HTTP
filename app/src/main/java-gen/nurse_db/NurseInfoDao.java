package nurse_db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import nurse_db.NurseInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NURSE_INFO".
*/
public class NurseInfoDao extends AbstractDao<NurseInfo, String> {

    public static final String TABLENAME = "NURSE_INFO";

    /**
     * Properties of entity NurseInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Nurse_id = new Property(0, String.class, "nurse_id", true, "NURSE_ID");
        public final static Property Nurse_gender = new Property(1, String.class, "nurse_gender", false, "NURSE_GENDER");
        public final static Property Nurse_age = new Property(2, int.class, "nurse_age", false, "NURSE_AGE");
        public final static Property Nurse_major = new Property(3, String.class, "nurse_major", false, "NURSE_MAJOR");
        public final static Property Nurse_name = new Property(4, String.class, "nurse_name", false, "NURSE_NAME");
        public final static Property Nurse_photo = new Property(5, String.class, "nurse_photo", false, "NURSE_PHOTO");
    };


    public NurseInfoDao(DaoConfig config) {
        super(config);
    }
    
    public NurseInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NURSE_INFO\" (" + //
                "\"NURSE_ID\" TEXT PRIMARY KEY NOT NULL UNIQUE ," + // 0: nurse_id
                "\"NURSE_GENDER\" TEXT NOT NULL ," + // 1: nurse_gender
                "\"NURSE_AGE\" INTEGER NOT NULL ," + // 2: nurse_age
                "\"NURSE_MAJOR\" TEXT NOT NULL ," + // 3: nurse_major
                "\"NURSE_NAME\" TEXT NOT NULL ," + // 4: nurse_name
                "\"NURSE_PHOTO\" TEXT NOT NULL );"); // 5: nurse_photo
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NURSE_INFO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, NurseInfo entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getNurse_id());
        stmt.bindString(2, entity.getNurse_gender());
        stmt.bindLong(3, entity.getNurse_age());
        stmt.bindString(4, entity.getNurse_major());
        stmt.bindString(5, entity.getNurse_name());
        stmt.bindString(6, entity.getNurse_photo());
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public NurseInfo readEntity(Cursor cursor, int offset) {
        NurseInfo entity = new NurseInfo( //
            cursor.getString(offset + 0), // nurse_id
            cursor.getString(offset + 1), // nurse_gender
            cursor.getInt(offset + 2), // nurse_age
            cursor.getString(offset + 3), // nurse_major
            cursor.getString(offset + 4), // nurse_name
            cursor.getString(offset + 5) // nurse_photo
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, NurseInfo entity, int offset) {
        entity.setNurse_id(cursor.getString(offset + 0));
        entity.setNurse_gender(cursor.getString(offset + 1));
        entity.setNurse_age(cursor.getInt(offset + 2));
        entity.setNurse_major(cursor.getString(offset + 3));
        entity.setNurse_name(cursor.getString(offset + 4));
        entity.setNurse_photo(cursor.getString(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(NurseInfo entity, long rowId) {
        return entity.getNurse_id();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(NurseInfo entity) {
        if(entity != null) {
            return entity.getNurse_id();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}

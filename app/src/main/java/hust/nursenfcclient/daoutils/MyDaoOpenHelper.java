package hust.nursenfcclient.daoutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import nurse_db.DaoMaster;

/**
 * Created by admin on 2016/4/8.
 */
public class MyDaoOpenHelper extends DaoMaster.OpenHelper {
    public MyDaoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

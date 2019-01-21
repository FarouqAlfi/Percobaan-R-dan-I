package com.example.win10.percobaan.MyApp;

import android.app.Application;

import com.example.win10.percobaan.Table.DaoMaster;
import com.example.win10.percobaan.Table.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Akinsete on 6/30/17.
 */

public class MyAppController extends Application {

    public static final boolean ENCRYPTED = true;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();


        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "grocery-db"); //The users-db here is the name of our database.
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {

        return daoSession;
    }

}
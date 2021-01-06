package com.xing.manage.db;


import android.database.sqlite.SQLiteDatabase;

import com.xing.commonbase.base.BaseApplication;
import com.xing.manage.bean.device.Area;
import com.xing.manage.bean.device.Inspection;

public class DbManager {

    private static DbManager instance;
    private  DaoMaster.DevOpenHelper helper;
    private  SQLiteDatabase database;
    private  DaoSession daoSession;
    private  DaoMaster daoMaster;
     private  LineDao lineDao;
    private AreaDao areaDao;
    private InspectionDao inspectionDao;
    private  FacilityDao facilityDao;
    private  RecordDao recordDao;
    private  ResourceDao resourceDao;
     private DbManager() {
         helper = new DaoMaster.DevOpenHelper(BaseApplication.getApplication().getApplicationContext(), "WanAndroid");
         database = helper.getWritableDatabase();
         daoMaster = new DaoMaster(database);
         daoSession = daoMaster.newSession();
     }

    public static DbManager getInstance() {
        if (instance == null) {
            synchronized (DbManager.class) {
                if (instance == null) {
                    instance = new DbManager();
                }
            }
        }
        return instance;
    }


    public LineDao getLineDao() {
        return daoSession.getLineDao();
    }

    public AreaDao getAreaDao() {
        return daoSession.getAreaDao();
    }

    public InspectionDao getInspectionDao() {
        return daoSession.getInspectionDao();
    }

    public FacilityDao getFacilityDao() {
        return daoSession.getFacilityDao();
    }
    public RecordDao getRecordDao() {
        return daoSession.getRecordDao();
    }
    public ResourceDao getResourceDao() {
        return daoSession.getResourceDao();
    }
}

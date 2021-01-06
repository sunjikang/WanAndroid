package com.xing.manage.db;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.xing.manage.bean.device.Facility;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FACILITY".
*/
public class FacilityDao extends AbstractDao<Facility, Long> {

    public static final String TABLENAME = "FACILITY";

    /**
     * Properties of entity Facility.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Mmid = new Property(0, Long.class, "mmid", true, "_id");
        public final static Property LineId = new Property(1, Long.class, "lineId", false, "LINE_ID");
        public final static Property AreaId = new Property(2, Long.class, "areaId", false, "AREA_ID");
        public final static Property Id = new Property(3, Long.class, "id", false, "ID");
        public final static Property CreateBy = new Property(4, String.class, "createBy", false, "CREATE_BY");
        public final static Property CreateTime = new Property(5, String.class, "createTime", false, "CREATE_TIME");
        public final static Property UpdateBy = new Property(6, String.class, "updateBy", false, "UPDATE_BY");
        public final static Property UpdateTime = new Property(7, String.class, "updateTime", false, "UPDATE_TIME");
        public final static Property DelFlag = new Property(8, String.class, "delFlag", false, "DEL_FLAG");
        public final static Property OpenStatus = new Property(9, String.class, "openStatus", false, "OPEN_STATUS");
        public final static Property Code = new Property(10, String.class, "code", false, "CODE");
        public final static Property Name = new Property(11, String.class, "name", false, "NAME");
        public final static Property ParentId = new Property(12, String.class, "parentId", false, "PARENT_ID");
        public final static Property KksCode = new Property(13, String.class, "kksCode", false, "KKS_CODE");
        public final static Property IsMain = new Property(14, String.class, "isMain", false, "IS_MAIN");
        public final static Property Type = new Property(15, String.class, "type", false, "TYPE");
        public final static Property ScaleModel = new Property(16, String.class, "scaleModel", false, "SCALE_MODEL");
        public final static Property ProductCompany = new Property(17, String.class, "productCompany", false, "PRODUCT_COMPANY");
        public final static Property ProductTime = new Property(18, String.class, "productTime", false, "PRODUCT_TIME");
        public final static Property DebugCompany = new Property(19, String.class, "debugCompany", false, "DEBUG_COMPANY");
        public final static Property InstallNumber = new Property(20, String.class, "installNumber", false, "INSTALL_NUMBER");
        public final static Property InstallPosition = new Property(21, String.class, "installPosition", false, "INSTALL_POSITION");
        public final static Property TypeCode = new Property(22, String.class, "typeCode", false, "TYPE_CODE");
        public final static Property Specialty = new Property(23, String.class, "specialty", false, "SPECIALTY");
        public final static Property SysCode = new Property(24, String.class, "sysCode", false, "SYS_CODE");
    }

    private DaoSession daoSession;

    private Query<Facility> area_FacilityListQuery;
    private Query<Facility> line_FacilityListQuery;

    public FacilityDao(DaoConfig config) {
        super(config);
    }
    
    public FacilityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FACILITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: mmid
                "\"LINE_ID\" INTEGER," + // 1: lineId
                "\"AREA_ID\" INTEGER," + // 2: areaId
                "\"ID\" INTEGER," + // 3: id
                "\"CREATE_BY\" TEXT," + // 4: createBy
                "\"CREATE_TIME\" TEXT," + // 5: createTime
                "\"UPDATE_BY\" TEXT," + // 6: updateBy
                "\"UPDATE_TIME\" TEXT," + // 7: updateTime
                "\"DEL_FLAG\" TEXT," + // 8: delFlag
                "\"OPEN_STATUS\" TEXT," + // 9: openStatus
                "\"CODE\" TEXT," + // 10: code
                "\"NAME\" TEXT," + // 11: name
                "\"PARENT_ID\" TEXT," + // 12: parentId
                "\"KKS_CODE\" TEXT," + // 13: kksCode
                "\"IS_MAIN\" TEXT," + // 14: isMain
                "\"TYPE\" TEXT," + // 15: type
                "\"SCALE_MODEL\" TEXT," + // 16: scaleModel
                "\"PRODUCT_COMPANY\" TEXT," + // 17: productCompany
                "\"PRODUCT_TIME\" TEXT," + // 18: productTime
                "\"DEBUG_COMPANY\" TEXT," + // 19: debugCompany
                "\"INSTALL_NUMBER\" TEXT," + // 20: installNumber
                "\"INSTALL_POSITION\" TEXT," + // 21: installPosition
                "\"TYPE_CODE\" TEXT," + // 22: typeCode
                "\"SPECIALTY\" TEXT," + // 23: specialty
                "\"SYS_CODE\" TEXT);"); // 24: sysCode
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FACILITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Facility entity) {
        stmt.clearBindings();
 
        Long mmid = entity.getMmid();
        if (mmid != null) {
            stmt.bindLong(1, mmid);
        }
 
        Long lineId = entity.getLineId();
        if (lineId != null) {
            stmt.bindLong(2, lineId);
        }
 
        Long areaId = entity.getAreaId();
        if (areaId != null) {
            stmt.bindLong(3, areaId);
        }
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(4, id);
        }
 
        String createBy = entity.getCreateBy();
        if (createBy != null) {
            stmt.bindString(5, createBy);
        }
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(6, createTime);
        }
 
        String updateBy = entity.getUpdateBy();
        if (updateBy != null) {
            stmt.bindString(7, updateBy);
        }
 
        String updateTime = entity.getUpdateTime();
        if (updateTime != null) {
            stmt.bindString(8, updateTime);
        }
 
        String delFlag = entity.getDelFlag();
        if (delFlag != null) {
            stmt.bindString(9, delFlag);
        }
 
        String openStatus = entity.getOpenStatus();
        if (openStatus != null) {
            stmt.bindString(10, openStatus);
        }
 
        String code = entity.getCode();
        if (code != null) {
            stmt.bindString(11, code);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(12, name);
        }
 
        String parentId = entity.getParentId();
        if (parentId != null) {
            stmt.bindString(13, parentId);
        }
 
        String kksCode = entity.getKksCode();
        if (kksCode != null) {
            stmt.bindString(14, kksCode);
        }
 
        String isMain = entity.getIsMain();
        if (isMain != null) {
            stmt.bindString(15, isMain);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(16, type);
        }
 
        String scaleModel = entity.getScaleModel();
        if (scaleModel != null) {
            stmt.bindString(17, scaleModel);
        }
 
        String productCompany = entity.getProductCompany();
        if (productCompany != null) {
            stmt.bindString(18, productCompany);
        }
 
        String productTime = entity.getProductTime();
        if (productTime != null) {
            stmt.bindString(19, productTime);
        }
 
        String debugCompany = entity.getDebugCompany();
        if (debugCompany != null) {
            stmt.bindString(20, debugCompany);
        }
 
        String installNumber = entity.getInstallNumber();
        if (installNumber != null) {
            stmt.bindString(21, installNumber);
        }
 
        String installPosition = entity.getInstallPosition();
        if (installPosition != null) {
            stmt.bindString(22, installPosition);
        }
 
        String typeCode = entity.getTypeCode();
        if (typeCode != null) {
            stmt.bindString(23, typeCode);
        }
 
        String specialty = entity.getSpecialty();
        if (specialty != null) {
            stmt.bindString(24, specialty);
        }
 
        String sysCode = entity.getSysCode();
        if (sysCode != null) {
            stmt.bindString(25, sysCode);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Facility entity) {
        stmt.clearBindings();
 
        Long mmid = entity.getMmid();
        if (mmid != null) {
            stmt.bindLong(1, mmid);
        }
 
        Long lineId = entity.getLineId();
        if (lineId != null) {
            stmt.bindLong(2, lineId);
        }
 
        Long areaId = entity.getAreaId();
        if (areaId != null) {
            stmt.bindLong(3, areaId);
        }
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(4, id);
        }
 
        String createBy = entity.getCreateBy();
        if (createBy != null) {
            stmt.bindString(5, createBy);
        }
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(6, createTime);
        }
 
        String updateBy = entity.getUpdateBy();
        if (updateBy != null) {
            stmt.bindString(7, updateBy);
        }
 
        String updateTime = entity.getUpdateTime();
        if (updateTime != null) {
            stmt.bindString(8, updateTime);
        }
 
        String delFlag = entity.getDelFlag();
        if (delFlag != null) {
            stmt.bindString(9, delFlag);
        }
 
        String openStatus = entity.getOpenStatus();
        if (openStatus != null) {
            stmt.bindString(10, openStatus);
        }
 
        String code = entity.getCode();
        if (code != null) {
            stmt.bindString(11, code);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(12, name);
        }
 
        String parentId = entity.getParentId();
        if (parentId != null) {
            stmt.bindString(13, parentId);
        }
 
        String kksCode = entity.getKksCode();
        if (kksCode != null) {
            stmt.bindString(14, kksCode);
        }
 
        String isMain = entity.getIsMain();
        if (isMain != null) {
            stmt.bindString(15, isMain);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(16, type);
        }
 
        String scaleModel = entity.getScaleModel();
        if (scaleModel != null) {
            stmt.bindString(17, scaleModel);
        }
 
        String productCompany = entity.getProductCompany();
        if (productCompany != null) {
            stmt.bindString(18, productCompany);
        }
 
        String productTime = entity.getProductTime();
        if (productTime != null) {
            stmt.bindString(19, productTime);
        }
 
        String debugCompany = entity.getDebugCompany();
        if (debugCompany != null) {
            stmt.bindString(20, debugCompany);
        }
 
        String installNumber = entity.getInstallNumber();
        if (installNumber != null) {
            stmt.bindString(21, installNumber);
        }
 
        String installPosition = entity.getInstallPosition();
        if (installPosition != null) {
            stmt.bindString(22, installPosition);
        }
 
        String typeCode = entity.getTypeCode();
        if (typeCode != null) {
            stmt.bindString(23, typeCode);
        }
 
        String specialty = entity.getSpecialty();
        if (specialty != null) {
            stmt.bindString(24, specialty);
        }
 
        String sysCode = entity.getSysCode();
        if (sysCode != null) {
            stmt.bindString(25, sysCode);
        }
    }

    @Override
    protected final void attachEntity(Facility entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Facility readEntity(Cursor cursor, int offset) {
        Facility entity = new Facility( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // mmid
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // lineId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // areaId
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // id
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // createBy
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // createTime
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // updateBy
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // updateTime
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // delFlag
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // openStatus
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // code
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // name
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // parentId
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // kksCode
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // isMain
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // type
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // scaleModel
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // productCompany
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // productTime
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // debugCompany
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // installNumber
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // installPosition
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // typeCode
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // specialty
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24) // sysCode
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Facility entity, int offset) {
        entity.setMmid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLineId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setAreaId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setCreateBy(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCreateTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUpdateBy(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setUpdateTime(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setDelFlag(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setOpenStatus(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setCode(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setName(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setParentId(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setKksCode(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setIsMain(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setType(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setScaleModel(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setProductCompany(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setProductTime(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setDebugCompany(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setInstallNumber(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setInstallPosition(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setTypeCode(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setSpecialty(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setSysCode(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Facility entity, long rowId) {
        entity.setMmid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Facility entity) {
        if(entity != null) {
            return entity.getMmid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Facility entity) {
        return entity.getMmid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "facilityList" to-many relationship of Area. */
    public List<Facility> _queryArea_FacilityList(Long areaId) {
        synchronized (this) {
            if (area_FacilityListQuery == null) {
                QueryBuilder<Facility> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.AreaId.eq(null));
                area_FacilityListQuery = queryBuilder.build();
            }
        }
        Query<Facility> query = area_FacilityListQuery.forCurrentThread();
        query.setParameter(0, areaId);
        return query.list();
    }

    /** Internal query to resolve the "facilityList" to-many relationship of Line. */
    public List<Facility> _queryLine_FacilityList(Long lineId) {
        synchronized (this) {
            if (line_FacilityListQuery == null) {
                QueryBuilder<Facility> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.LineId.eq(null));
                line_FacilityListQuery = queryBuilder.build();
            }
        }
        Query<Facility> query = line_FacilityListQuery.forCurrentThread();
        query.setParameter(0, lineId);
        return query.list();
    }

}
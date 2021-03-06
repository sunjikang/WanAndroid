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

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "RESOURCE".
*/
public class ResourceDao extends AbstractDao<Resource, Long> {

    public static final String TABLENAME = "RESOURCE";

    /**
     * Properties of entity Resource.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Url = new Property(1, String.class, "url", false, "URL");
        public final static Property IsUpdate = new Property(2, Boolean.class, "isUpdate", false, "IS_UPDATE");
        public final static Property CheckId = new Property(3, Long.class, "checkId", false, "CHECK_ID");
    }

    private Query<Resource> checkNow_ResourceDaoListQuery;

    public ResourceDao(DaoConfig config) {
        super(config);
    }
    
    public ResourceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"RESOURCE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"URL\" TEXT," + // 1: url
                "\"IS_UPDATE\" INTEGER," + // 2: isUpdate
                "\"CHECK_ID\" INTEGER);"); // 3: checkId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"RESOURCE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Resource entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(2, url);
        }
 
        Boolean isUpdate = entity.getIsUpdate();
        if (isUpdate != null) {
            stmt.bindLong(3, isUpdate ? 1L: 0L);
        }
 
        Long checkId = entity.getCheckId();
        if (checkId != null) {
            stmt.bindLong(4, checkId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Resource entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(2, url);
        }
 
        Boolean isUpdate = entity.getIsUpdate();
        if (isUpdate != null) {
            stmt.bindLong(3, isUpdate ? 1L: 0L);
        }
 
        Long checkId = entity.getCheckId();
        if (checkId != null) {
            stmt.bindLong(4, checkId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Resource readEntity(Cursor cursor, int offset) {
        Resource entity = new Resource( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // url
            cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0, // isUpdate
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3) // checkId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Resource entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUrl(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIsUpdate(cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0);
        entity.setCheckId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Resource entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Resource entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Resource entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "resourceDaoList" to-many relationship of CheckNow. */
    public List<Resource> _queryCheckNow_ResourceDaoList(Long checkId) {
        synchronized (this) {
            if (checkNow_ResourceDaoListQuery == null) {
                QueryBuilder<Resource> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CheckId.eq(null));
                checkNow_ResourceDaoListQuery = queryBuilder.build();
            }
        }
        Query<Resource> query = checkNow_ResourceDaoListQuery.forCurrentThread();
        query.setParameter(0, checkId);
        return query.list();
    }

}

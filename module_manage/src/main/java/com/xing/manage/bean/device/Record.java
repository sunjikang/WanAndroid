package com.xing.manage.bean.device;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.xing.manage.db.DaoSession;
import com.xing.manage.db.RecordDao;
import com.xing.manage.db.ResourceDao;

/**
 * 上传记录
 */
@Entity
public class Record implements Parcelable {
    @Id(autoincrement = true)
     public  Long mmid;
     public  Long id;
     public String startTime;   //开始时间
     public String endTime;     //结束时间
     public String checker;   //检测人员
     public String type; //检测类型
     public String value;  //具体检测值 ,,,以分号隔开
     public String remark;    //备注
     public String checkState; //检测状态 0 正常  1异常
     public String upLoadState; //上传状态  0未上传  1 已上传  2 上传失败

    public Long lineId;//线路Id
    public Long areaId;//区域Id
    public Long facilityId;//设备Id
    public Long inspectionId;//巡检项Id



    @ToMany(referencedJoinProperty = "recordId")
    public List<Resource> resourceList; //资源list


    protected Record(Parcel in) {
        if (in.readByte() == 0) {
            mmid = null;
        } else {
            mmid = in.readLong();
        }
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        startTime = in.readString();
        endTime = in.readString();
        checker = in.readString();
        type = in.readString();
        value = in.readString();
        remark = in.readString();
        checkState = in.readString();
        upLoadState = in.readString();
        if (in.readByte() == 0) {
            lineId = null;
        } else {
            lineId = in.readLong();
        }
        if (in.readByte() == 0) {
            areaId = null;
        } else {
            areaId = in.readLong();
        }
        if (in.readByte() == 0) {
            facilityId = null;
        } else {
            facilityId = in.readLong();
        }
        if (in.readByte() == 0) {
            inspectionId = null;
        } else {
            inspectionId = in.readLong();
        }
        resourceList = in.createTypedArrayList(Resource.CREATOR);
    }

    @Generated(hash = 1712084423)
    public Record(Long mmid, Long id, String startTime, String endTime,
            String checker, String type, String value, String remark,
            String checkState, String upLoadState, Long lineId, Long areaId,
            Long facilityId, Long inspectionId) {
        this.mmid = mmid;
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.checker = checker;
        this.type = type;
        this.value = value;
        this.remark = remark;
        this.checkState = checkState;
        this.upLoadState = upLoadState;
        this.lineId = lineId;
        this.areaId = areaId;
        this.facilityId = facilityId;
        this.inspectionId = inspectionId;
    }

    @Generated(hash = 477726293)
    public Record() {
    }

    public static final Creator<Record> CREATOR = new Creator<Record>() {
        @Override
        public Record createFromParcel(Parcel in) {
            return new Record(in);
        }

        @Override
        public Record[] newArray(int size) {
            return new Record[size];
        }
    };
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 765166123)
    private transient RecordDao myDao;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mmid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mmid);
        }
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(checker);
        dest.writeString(type);
        dest.writeString(value);
        dest.writeString(remark);
        dest.writeString(checkState);
        dest.writeString(upLoadState);
        if (lineId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(lineId);
        }
        if (areaId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(areaId);
        }
        if (facilityId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(facilityId);
        }
        if (inspectionId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(inspectionId);
        }
        dest.writeTypedList(resourceList);
    }

    public Long getMmid() {
        return this.mmid;
    }

    public void setMmid(Long mmid) {
        this.mmid = mmid;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getChecker() {
        return this.checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCheckState() {
        return this.checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    public String getUpLoadState() {
        return this.upLoadState;
    }

    public void setUpLoadState(String upLoadState) {
        this.upLoadState = upLoadState;
    }

    public Long getLineId() {
        return this.lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Long getAreaId() {
        return this.areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getFacilityId() {
        return this.facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public Long getInspectionId() {
        return this.inspectionId;
    }

    public void setInspectionId(Long inspectionId) {
        this.inspectionId = inspectionId;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 404297703)
    public List<Resource> getResourceList() {
        if (resourceList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ResourceDao targetDao = daoSession.getResourceDao();
            List<Resource> resourceListNew = targetDao
                    ._queryRecord_ResourceList(mmid);
            synchronized (this) {
                if (resourceList == null) {
                    resourceList = resourceListNew;
                }
            }
        }
        return resourceList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 103441505)
    public synchronized void resetResourceList() {
        resourceList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1505145191)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRecordDao() : null;
    }
}

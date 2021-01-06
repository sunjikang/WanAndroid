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
import com.xing.manage.db.InspectionDao;
import com.xing.manage.db.FacilityDao;
import com.xing.manage.db.RecordDao;
import com.xing.manage.db.AreaDao;
import com.xing.manage.db.LineDao;

/**
 * 设备管理been
 */

@Entity
public class Line implements Parcelable {
    @Id(autoincrement = true)
    public Long mmid;
    public Long id;   //        "id":"1329014116379136000",
    public String createBy;//           "createBy":"admin",
    public String createTime;//         "createTime":"2020-11-18 18:50:31",
    public String updateBy;//          "updateBy":"admin",
    public String updateTime;//          "updateTime":"2020-11-18 18:50:31",
    public String delFlag;//          "delFlag":0,
    public String openStatus;//          "openStatus":0,
    public String title;//          "lineName":"测试线路1",
    public String lineCode;//          "lineCode":"CSXL1118",
    public String post;//           "post":"锅炉",
    public String taskType;//           "taskType":"定期巡检",
    public String startStatus;//           "startStatus":"启用",
    public String remark;//           "remark":"",
    public String standbyI;//           "standbyI":"",
    public String standbyII;//           "standbyII":"",
    public String standbyIII;//            "standbyIII":"",
    public String inspectionPeriod; //巡检周期
    public Long startCheckTime;  //开始巡检时间
    public Long endCheckTime;   //结束巡检时间

    @ToMany(referencedJoinProperty = "lineId")
    public List<Area> dmList;//区域列表

    @ToMany(referencedJoinProperty = "lineId")
    public List<Record> recordList;//记录列表


    @ToMany(referencedJoinProperty = "lineId")
    public List<Facility> facilityList;//设备列表

    @ToMany(referencedJoinProperty = "lineId")
    public List<Inspection> inspectionList;  //巡检项列表


    protected Line(Parcel in) {
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
        createBy = in.readString();
        createTime = in.readString();
        updateBy = in.readString();
        updateTime = in.readString();
        delFlag = in.readString();
        openStatus = in.readString();
        title = in.readString();
        lineCode = in.readString();
        post = in.readString();
        taskType = in.readString();
        startStatus = in.readString();
        remark = in.readString();
        standbyI = in.readString();
        standbyII = in.readString();
        standbyIII = in.readString();
        inspectionPeriod = in.readString();
        if (in.readByte() == 0) {
            startCheckTime = null;
        } else {
            startCheckTime = in.readLong();
        }
        if (in.readByte() == 0) {
            endCheckTime = null;
        } else {
            endCheckTime = in.readLong();
        }
        dmList = in.createTypedArrayList(Area.CREATOR);
        recordList = in.createTypedArrayList(Record.CREATOR);
        facilityList = in.createTypedArrayList(Facility.CREATOR);
        inspectionList = in.createTypedArrayList(Inspection.CREATOR);
    }

    @Generated(hash = 751128344)
    public Line(Long mmid, Long id, String createBy, String createTime,
            String updateBy, String updateTime, String delFlag, String openStatus,
            String title, String lineCode, String post, String taskType,
            String startStatus, String remark, String standbyI, String standbyII,
            String standbyIII, String inspectionPeriod, Long startCheckTime,
            Long endCheckTime) {
        this.mmid = mmid;
        this.id = id;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.delFlag = delFlag;
        this.openStatus = openStatus;
        this.title = title;
        this.lineCode = lineCode;
        this.post = post;
        this.taskType = taskType;
        this.startStatus = startStatus;
        this.remark = remark;
        this.standbyI = standbyI;
        this.standbyII = standbyII;
        this.standbyIII = standbyIII;
        this.inspectionPeriod = inspectionPeriod;
        this.startCheckTime = startCheckTime;
        this.endCheckTime = endCheckTime;
    }

    @Generated(hash = 1133511183)
    public Line() {
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
        dest.writeString(createBy);
        dest.writeString(createTime);
        dest.writeString(updateBy);
        dest.writeString(updateTime);
        dest.writeString(delFlag);
        dest.writeString(openStatus);
        dest.writeString(title);
        dest.writeString(lineCode);
        dest.writeString(post);
        dest.writeString(taskType);
        dest.writeString(startStatus);
        dest.writeString(remark);
        dest.writeString(standbyI);
        dest.writeString(standbyII);
        dest.writeString(standbyIII);
        dest.writeString(inspectionPeriod);
        if (startCheckTime == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(startCheckTime);
        }
        if (endCheckTime == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(endCheckTime);
        }
        dest.writeTypedList(dmList);
        dest.writeTypedList(recordList);
        dest.writeTypedList(facilityList);
        dest.writeTypedList(inspectionList);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getOpenStatus() {
        return this.openStatus;
    }

    public void setOpenStatus(String openStatus) {
        this.openStatus = openStatus;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLineCode() {
        return this.lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getPost() {
        return this.post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getStartStatus() {
        return this.startStatus;
    }

    public void setStartStatus(String startStatus) {
        this.startStatus = startStatus;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStandbyI() {
        return this.standbyI;
    }

    public void setStandbyI(String standbyI) {
        this.standbyI = standbyI;
    }

    public String getStandbyII() {
        return this.standbyII;
    }

    public void setStandbyII(String standbyII) {
        this.standbyII = standbyII;
    }

    public String getStandbyIII() {
        return this.standbyIII;
    }

    public void setStandbyIII(String standbyIII) {
        this.standbyIII = standbyIII;
    }

    public String getInspectionPeriod() {
        return this.inspectionPeriod;
    }

    public void setInspectionPeriod(String inspectionPeriod) {
        this.inspectionPeriod = inspectionPeriod;
    }

    public Long getStartCheckTime() {
        return this.startCheckTime;
    }

    public void setStartCheckTime(Long startCheckTime) {
        this.startCheckTime = startCheckTime;
    }

    public Long getEndCheckTime() {
        return this.endCheckTime;
    }

    public void setEndCheckTime(Long endCheckTime) {
        this.endCheckTime = endCheckTime;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 927837264)
    public List<Area> getDmList() {
        if (dmList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AreaDao targetDao = daoSession.getAreaDao();
            List<Area> dmListNew = targetDao._queryLine_DmList(mmid);
            synchronized (this) {
                if (dmList == null) {
                    dmList = dmListNew;
                }
            }
        }
        return dmList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1338713364)
    public synchronized void resetDmList() {
        dmList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 121132901)
    public List<Record> getRecordList() {
        if (recordList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RecordDao targetDao = daoSession.getRecordDao();
            List<Record> recordListNew = targetDao._queryLine_RecordList(mmid);
            synchronized (this) {
                if (recordList == null) {
                    recordList = recordListNew;
                }
            }
        }
        return recordList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1700181837)
    public synchronized void resetRecordList() {
        recordList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1412400628)
    public List<Facility> getFacilityList() {
        if (facilityList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FacilityDao targetDao = daoSession.getFacilityDao();
            List<Facility> facilityListNew = targetDao
                    ._queryLine_FacilityList(mmid);
            synchronized (this) {
                if (facilityList == null) {
                    facilityList = facilityListNew;
                }
            }
        }
        return facilityList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2120366010)
    public synchronized void resetFacilityList() {
        facilityList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 269398450)
    public List<Inspection> getInspectionList() {
        if (inspectionList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            InspectionDao targetDao = daoSession.getInspectionDao();
            List<Inspection> inspectionListNew = targetDao
                    ._queryLine_InspectionList(mmid);
            synchronized (this) {
                if (inspectionList == null) {
                    inspectionList = inspectionListNew;
                }
            }
        }
        return inspectionList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 211230371)
    public synchronized void resetInspectionList() {
        inspectionList = null;
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
    @Generated(hash = 360158775)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLineDao() : null;
    }

    public static final Creator<Line> CREATOR = new Creator<Line>() {
        @Override
        public Line createFromParcel(Parcel in) {
            return new Line(in);
        }

        @Override
        public Line[] newArray(int size) {
            return new Line[size];
        }
    };
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 447887085)
    private transient LineDao myDao;
}








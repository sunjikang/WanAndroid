package com.xing.manage.bean.device;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.xing.manage.db.DaoSession;
import com.xing.manage.db.RecordDao;
import com.xing.manage.db.InspectionDao;

@Entity
public   class Inspection implements Parcelable {
    @Id(autoincrement = true)
    public Long mmid;
    public Long areaId;
    public Long lineId;
    public Long facilityId;
    public Long  id;//      "id":"1329007301364944896",
    public String  createBy;//       "createBy":"admin",
    public String  createTime;//       "createTime":"2020-11-18 18:23:27",
    public String  updateBy;//       "updateBy":"admin",
    public String  updateTime;//       "updateTime":"2020-11-18 18:23:27",
    public String  delFlag;//       "delFlag":0,
    public String  openStatus;//       "openStatus":0,
    public String  inspectionItemName;//       "inspectionItemName":"阀门管线",
    public String  samplingNumber;//       "samplingNumber":"",
    public String  sampleFrequency;//             "sampleFrequency":"",
    public String  defaultSpeed;//      "defaultSpeed":"",
    public String  unit;//       "unit":"",
    public String  equipmentLevel;//      "equipmentLevel":"B类",
    public String  pollingType;//      "pollingType":"观察",
    public String  pollingStatus;//      "pollingStatus":"备用",
    public String  emissivity;//  发射频率      "emissivity":"",
    public String  upperUp;//     "upperUp":"",
    public String  upper;//       "upper":"",
    public String  floor;//       "floor":"",
    public String  floorFl;//        "floorFl":"",
    public String  remark;//       "remark":"泄漏/关闭不严",
    public String  standbyI;//       "standbyI":"",
    public String  standbyII;//       "standbyII":"",
    public String  standbyIII;//       "standbyIII":""

    /***检测状态  0未检测   1 已检测*/
    public Boolean isCheckOver;
    @ToMany(referencedJoinProperty = "inspectionId")
    public List<Record> recordList;

    @Override
    public String toString() {
        return "Inspection{" +
                "mmid=" + mmid +
                ", areaId=" + areaId +
                ", lineId=" + lineId +
                ", facilityId=" + facilityId +
                ", id=" + id +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", openStatus='" + openStatus + '\'' +
                ", inspectionItemName='" + inspectionItemName + '\'' +
                ", samplingNumber='" + samplingNumber + '\'' +
                ", sampleFrequency='" + sampleFrequency + '\'' +
                ", defaultSpeed='" + defaultSpeed + '\'' +
                ", unit='" + unit + '\'' +
                ", equipmentLevel='" + equipmentLevel + '\'' +
                ", pollingType='" + pollingType + '\'' +
                ", pollingStatus='" + pollingStatus + '\'' +
                ", emissivity='" + emissivity + '\'' +
                ", upperUp='" + upperUp + '\'' +
                ", upper='" + upper + '\'' +
                ", floor='" + floor + '\'' +
                ", floorFl='" + floorFl + '\'' +
                ", remark='" + remark + '\'' +
                ", standbyI='" + standbyI + '\'' +
                ", standbyII='" + standbyII + '\'' +
                ", standbyIII='" + standbyIII + '\'' +
                ", isCheckOver=" + isCheckOver +
                ", recordList=" + recordList +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                '}';
    }

    protected Inspection(Parcel in) {
        if (in.readByte() == 0) {
            mmid = null;
        } else {
            mmid = in.readLong();
        }
        if (in.readByte() == 0) {
            areaId = null;
        } else {
            areaId = in.readLong();
        }
        if (in.readByte() == 0) {
            lineId = null;
        } else {
            lineId = in.readLong();
        }
        if (in.readByte() == 0) {
            facilityId = null;
        } else {
            facilityId = in.readLong();
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
        inspectionItemName = in.readString();
        samplingNumber = in.readString();
        sampleFrequency = in.readString();
        defaultSpeed = in.readString();
        unit = in.readString();
        equipmentLevel = in.readString();
        pollingType = in.readString();
        pollingStatus = in.readString();
        emissivity = in.readString();
        upperUp = in.readString();
        upper = in.readString();
        floor = in.readString();
        floorFl = in.readString();
        remark = in.readString();
        standbyI = in.readString();
        standbyII = in.readString();
        standbyIII = in.readString();
        byte tmpIsCheckOver = in.readByte();
        isCheckOver = tmpIsCheckOver == 0 ? null : tmpIsCheckOver == 1;
        recordList = in.createTypedArrayList(Record.CREATOR);
    }

    @Generated(hash = 2034965472)
    public Inspection(Long mmid, Long areaId, Long lineId, Long facilityId, Long id,
            String createBy, String createTime, String updateBy, String updateTime,
            String delFlag, String openStatus, String inspectionItemName,
            String samplingNumber, String sampleFrequency, String defaultSpeed,
            String unit, String equipmentLevel, String pollingType,
            String pollingStatus, String emissivity, String upperUp, String upper,
            String floor, String floorFl, String remark, String standbyI,
            String standbyII, String standbyIII, Boolean isCheckOver) {
        this.mmid = mmid;
        this.areaId = areaId;
        this.lineId = lineId;
        this.facilityId = facilityId;
        this.id = id;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.delFlag = delFlag;
        this.openStatus = openStatus;
        this.inspectionItemName = inspectionItemName;
        this.samplingNumber = samplingNumber;
        this.sampleFrequency = sampleFrequency;
        this.defaultSpeed = defaultSpeed;
        this.unit = unit;
        this.equipmentLevel = equipmentLevel;
        this.pollingType = pollingType;
        this.pollingStatus = pollingStatus;
        this.emissivity = emissivity;
        this.upperUp = upperUp;
        this.upper = upper;
        this.floor = floor;
        this.floorFl = floorFl;
        this.remark = remark;
        this.standbyI = standbyI;
        this.standbyII = standbyII;
        this.standbyIII = standbyIII;
        this.isCheckOver = isCheckOver;
    }

    @Generated(hash = 56451862)
    public Inspection() {
    }

    public static final Creator<Inspection> CREATOR = new Creator<Inspection>() {
        @Override
        public Inspection createFromParcel(Parcel in) {
            return new Inspection(in);
        }

        @Override
        public Inspection[] newArray(int size) {
            return new Inspection[size];
        }
    };
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1239323056)
    private transient InspectionDao myDao;

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
        if (areaId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(areaId);
        }
        if (lineId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(lineId);
        }
        if (facilityId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(facilityId);
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
        dest.writeString(inspectionItemName);
        dest.writeString(samplingNumber);
        dest.writeString(sampleFrequency);
        dest.writeString(defaultSpeed);
        dest.writeString(unit);
        dest.writeString(equipmentLevel);
        dest.writeString(pollingType);
        dest.writeString(pollingStatus);
        dest.writeString(emissivity);
        dest.writeString(upperUp);
        dest.writeString(upper);
        dest.writeString(floor);
        dest.writeString(floorFl);
        dest.writeString(remark);
        dest.writeString(standbyI);
        dest.writeString(standbyII);
        dest.writeString(standbyIII);
        dest.writeByte((byte) (isCheckOver == null ? 0 : isCheckOver ? 1 : 2));
        dest.writeTypedList(recordList);
    }

    public Long getMmid() {
        return this.mmid;
    }

    public void setMmid(Long mmid) {
        this.mmid = mmid;
    }

    public Long getAreaId() {
        return this.areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getLineId() {
        return this.lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Long getFacilityId() {
        return this.facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
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

    public String getInspectionItemName() {
        return this.inspectionItemName;
    }

    public void setInspectionItemName(String inspectionItemName) {
        this.inspectionItemName = inspectionItemName;
    }

    public String getSamplingNumber() {
        return this.samplingNumber;
    }

    public void setSamplingNumber(String samplingNumber) {
        this.samplingNumber = samplingNumber;
    }

    public String getSampleFrequency() {
        return this.sampleFrequency;
    }

    public void setSampleFrequency(String sampleFrequency) {
        this.sampleFrequency = sampleFrequency;
    }

    public String getDefaultSpeed() {
        return this.defaultSpeed;
    }

    public void setDefaultSpeed(String defaultSpeed) {
        this.defaultSpeed = defaultSpeed;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getEquipmentLevel() {
        return this.equipmentLevel;
    }

    public void setEquipmentLevel(String equipmentLevel) {
        this.equipmentLevel = equipmentLevel;
    }

    public String getPollingType() {
        return this.pollingType;
    }

    public void setPollingType(String pollingType) {
        this.pollingType = pollingType;
    }

    public String getPollingStatus() {
        return this.pollingStatus;
    }

    public void setPollingStatus(String pollingStatus) {
        this.pollingStatus = pollingStatus;
    }

    public String getEmissivity() {
        return this.emissivity;
    }

    public void setEmissivity(String emissivity) {
        this.emissivity = emissivity;
    }

    public String getUpperUp() {
        return this.upperUp;
    }

    public void setUpperUp(String upperUp) {
        this.upperUp = upperUp;
    }

    public String getUpper() {
        return this.upper;
    }

    public void setUpper(String upper) {
        this.upper = upper;
    }

    public String getFloor() {
        return this.floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getFloorFl() {
        return this.floorFl;
    }

    public void setFloorFl(String floorFl) {
        this.floorFl = floorFl;
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

    public Boolean getIsCheckOver() {
        return this.isCheckOver;
    }

    public void setIsCheckOver(Boolean isCheckOver) {
        this.isCheckOver = isCheckOver;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1516219843)
    public List<Record> getRecordList() {
        if (recordList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RecordDao targetDao = daoSession.getRecordDao();
            List<Record> recordListNew = targetDao
                    ._queryInspection_RecordList(mmid);
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
    @Generated(hash = 687999565)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getInspectionDao() : null;
    }
}


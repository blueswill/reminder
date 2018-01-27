package app.fadai.supernote.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Try on 2018/1/27.
 */

public class NoteReminder extends DataSupport {
    private int id;
    private String RemindernoteId;
    private long createdTime;
    private long modifiedTime;

    private long reminderTime;//add thing
    private String RemindernoteContent;
    private int RemindernoteFolderId;

    //    是否是私密便签  1是 0不是
    private int isPrivacy;
    //    是否是废纸篓中便签，1是，0不是
    private int inRecycleBin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemindernoteId() {
        return RemindernoteId;
    }

    public void setRemindernoteId(String remindernoteId) {
        RemindernoteId = remindernoteId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public long getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(long reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getRemindernoteContent() {
        return RemindernoteContent;
    }

    public void setRemindernoteContent(String remindernoteContent) {
        RemindernoteContent = remindernoteContent;
    }

    public int getRemindernoteFolderId() {
        return RemindernoteFolderId;
    }

    public void setRemindernoteFolderId(int remindernoteFolderId) {
        RemindernoteFolderId = remindernoteFolderId;
    }

    public int getIsPrivacy() {
        return isPrivacy;
    }

    public void setIsPrivacy(int isPrivacy) {
        this.isPrivacy = isPrivacy;
    }

    public int getInRecycleBin() {
        return inRecycleBin;
    }

    public void setInRecycleBin(int inRecycleBin) {
        this.inRecycleBin = inRecycleBin;
    }
}

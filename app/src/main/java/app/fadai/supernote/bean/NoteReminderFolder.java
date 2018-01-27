package app.fadai.supernote.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Try on 2018/1/27.
 */

public class NoteReminderFolder extends DataSupport {
    private int id;
    private String ReminderfolderName;
    private int noteCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReminderfolderName() {
        return ReminderfolderName;
    }

    public void setReminderfolderName(String reminderfolderName) {
        ReminderfolderName = reminderfolderName;
    }

    public int getNoteCount() {
        return noteCount;
    }

    public void setNoteCount(int noteCount) {
        this.noteCount = noteCount;
    }
}

package ro.kepler.rominfo.model;

import org.primefaces.model.UploadedFile;

/**
 * Created by Dragos on 14.06.2017.
 */
public class Lecture {
    private long lectureId;
    private String name;
    private byte[] attachment;

    public long getLectureId() {
        return lectureId;
    }

    public void setLectureId(long lectureId) {
        this.lectureId = lectureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
}

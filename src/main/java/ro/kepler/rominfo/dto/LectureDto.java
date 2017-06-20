package ro.kepler.rominfo.dto;

import org.primefaces.model.UploadedFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Dragos on 13.06.2017.
 */
public class LectureDto {
    private String lectureName;
    private byte[] file;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }
}

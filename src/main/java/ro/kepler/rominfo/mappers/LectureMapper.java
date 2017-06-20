package ro.kepler.rominfo.mappers;

import org.apache.ibatis.annotations.Param;
import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Repository;
import ro.kepler.rominfo.model.Lecture;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Dragos on 13.06.2017.
 */

@Repository
public interface LectureMapper {
    void createLecture(@Param("courseCode") long courseCode, @Param("name")String name);
    List<Lecture> getLectures(@Param("courseCode") long courseCode);
    void uploadPDF(@Param("file") byte[] file, @Param("lectureId") long lectureId);
    Lecture getLectureByNameAndCourseCode(@Param("courseCode") long courseCode, @Param("lectureName") String lectureName);
}

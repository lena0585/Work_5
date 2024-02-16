package service;
import model.Student;
import model.StudyGroup;
import model.Teacher;
import model.Type;

import java.util.ArrayList;
import java.util.List;

public class StudyGroupService {
    private List<StudyGroup> studyGroupList;
    public  void create(String firstName, String lastName, String middleName, Type type) {
         int id = getFreeId(type);
         if (Type.STUDENT == type) {
             Student student = new Student(firstName, lastName, middleName, id);
             studyGroupList.add(student);
         }
        if (Type.TEACHER == type) {
            Teacher teacher = new Teacher(firstName, lastName, middleName, id);
            studyGroupList.add(teacher);
        }
    }
    public StudyGroup getStudyGroupById(Type type, int id){
        boolean itsStudent = Type.STUDENT == type;
        StudyGroup currentstudyGroup = null;

        for (StudyGroup studyGroup : studyGroupList) {
            if (studyGroup instanceof Teacher && !itsStudent && ((Teacher) studyGroup).getTeacherId() == id) {
                currentstudyGroup = studyGroup;
            }
            if (studyGroup instanceof Student && itsStudent && ((Student) studyGroup).getStudentId() == id) {
                currentstudyGroup = studyGroup;
            }
        }
        return  currentstudyGroup;
    }
    public List<StudyGroup> getAllStudyGroups(){
        return studyGroupList;
    }
    public List<StudyGroup> getAllStudent() {
        List<StudyGroup> students = new ArrayList<>();
        for (StudyGroup studyGroup : studyGroupList) {
            if (studyGroup instanceof Student) {
                students.add(studyGroup);
            }
        }
        return students;
    }
    private int getFreeId(Type type){
        boolean itsStudent = Type.STUDENT == type;
        int lastId = 1;
        for (StudyGroup studyGroup : studyGroupList) {
            if (studyGroup instanceof Teacher && !itsStudent) {
                lastId = ((Teacher) studyGroup).getTeacherId() + 1;
            }
            if (studyGroup instanceof Student && itsStudent) {
                lastId = ((Student) studyGroup).getStudentId() + 1;
            }
        }
        return lastId;
    }
}

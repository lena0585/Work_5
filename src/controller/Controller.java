package controller;

import model.Student;
import model.StudyGroup;
import model.Type;
import service.StudyGroupService;
import view.StudentView;

import java.util.List;

public class Controller {
    private final StudyGroupService service = new StudyGroupService();
    private final StudentView view = new StudentView();
    public void createStudent(String firstName, String lastName, String middleName){
        service.create(firstName, lastName, middleName, Type.STUDENT);
    }
    public void  getAllStudent() {
        List<StudyGroup> studyGroupList = service.getAllStudent();
        for (StudyGroup studyGroup : studyGroupList) {
            Student student = (Student) studyGroup;
            view.printOnConsole(student);
        }
    }

}

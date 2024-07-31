package model;


public class Teacher extends Person {
    private Long classId;


    public Teacher() {}

    public Teacher(Long id, String name, Long classId) {
        super(id, name);
        this.classId = classId;
    }


    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }


    @Override
    public String toString() {
        return "Teacher [classId=" + classId + ", " + super.toString() + "]";
    }
}

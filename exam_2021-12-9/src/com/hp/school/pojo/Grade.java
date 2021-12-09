package com.hp.school.pojo;

/**
 * 年级实体类
 */
public class Grade {
    private int gradeId;//年级编号
    private String gradeName;//年级名称

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", gradeName='" + gradeName + '\'' +
                '}';
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Grade() {
    }

    public Grade(int gradeId, String gradeName) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
    }
}

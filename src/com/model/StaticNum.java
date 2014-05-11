package com.model;

//某个班级的上课人数统计
public class StaticNum {
    //已签到上课的人数

    private int signedStudentNum;
    //未签到缺课的人数
    private int unSignedStudentNum;

    public int getSignedStudentNum() {
        return signedStudentNum;
    }

    public void setSignedStudentNum(int signedStudentNum) {
        this.signedStudentNum = signedStudentNum;
    }

    public int getUnSignedStudentNum() {
        return unSignedStudentNum;
    }

    public void setUnSignedStudentNum(int unSignedStudentNum) {
        this.unSignedStudentNum = unSignedStudentNum;
    }
}

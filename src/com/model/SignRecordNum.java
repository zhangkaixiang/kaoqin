 
package com.model;

 //针对某个学生的签到记录
public class SignRecordNum {
    //签到数
    private int signedNum;
    //缺课数
    private int lostSignedNum;
    //签到总数
    private int signedTotalNum;
    //缺课总数
    private int lostSignedTotalNum;

    public int getLostSignedNum() {
        return lostSignedNum;
    }

    public void setLostSignedNum(int lostSignedNum) {
        this.lostSignedNum = lostSignedNum;
    }

    public int getLostSignedTotalNum() {
        return lostSignedTotalNum;
    }

    public void setLostSignedTotalNum(int lostSignedTotalNum) {
        this.lostSignedTotalNum = lostSignedTotalNum;
    }

    public int getSignedNum() {
        return signedNum;
    }

    public void setSignedNum(int signedNum) {
        this.signedNum = signedNum;
    }

    public int getSignedTotalNum() {
        return signedTotalNum;
    }

    public void setSignedTotalNum(int signedTotalNum) {
        this.signedTotalNum = signedTotalNum;
    }
    
    
}

package com.action;

import com.model.ScoreView;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Score;
import com.service.ScoreService;
import com.service.ScoreServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;

public class ScoreAction extends ActionSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//引入服务
    ScoreService scoreService = new ScoreServiceImpl();
    //变量
    private int classid;
    private int courseid;
    private List<Score> allScores;
    private List<ScoreView> scoreViews;
private String msg;

    public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

	public List<ScoreView> getScoreViews() {
        return scoreViews;
    }

    public void setScoreViews(List<ScoreView> scoreViews) {
        this.scoreViews = scoreViews;
    }

    public List<Score> getAllScores() {
        return allScores;
    }

    public void setAllScores(List<Score> allScores) {
        this.allScores = allScores;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    //所有课程
    //默认查看 
    @Override
    public String execute() throws Exception {
        //列出该教师所教的所有课程

        return SUCCESS;
    }

    public String taskScore() throws Exception {
        //列出该教师所教的所有课程
        return "taskStatic";
    }
    //统计学生考勤成绩

    public String staticScore() throws Exception {
        if (scoreService.staticAttendScore(getClassid(), getCourseid())) {
            this.setMsg("统计学生成绩成功！您可以点击查看来查看该课程的学生成绩。");
            return SUCCESS;
        }else
        return ERROR;
    }
    //统计学生作业成绩

    public String staticTaskScore() throws Exception {
        if (scoreService.staticTaskrecordScore(getClassid(), getCourseid())) {
        	   this.setMsg("统计学生成绩成功！您可以点击查看来查看该课程的学生成绩。");
            return "taskStatic";
        }else
        return ERROR;
    }
    //查看所有成绩

    public String viewAll() throws Exception {
        List<Score> results = new ArrayList<Score>();
        results = scoreService.allStudentsAttendScore(getClassid(), getCourseid());
        if (results.isEmpty()) {
        	   this.setMsg("还未统计该课程成绩");
            return "unstatic";
        }
        setAllScores(results);
        return SUCCESS;
    }

    public String viewTaskAll() throws Exception {
        List<Score> results = new ArrayList<Score>();
        results = scoreService.allStudentsAttendScore(getClassid(), getCourseid());
        if (results.isEmpty()) {
        	   this.setMsg("还未统计该课程成绩");
            return "unstatic";
        }
        setAllScores(results);
        return "taskStatic";
    }

    public String prepareAllScore() throws Exception
    {
        return "scoreView";
    }
    
    public String staticAllScore() {
        try{
        if ( scoreService.staticTaskrecordScore(classid, courseid) ) {
        	   this.setMsg(" 统计成功！");
            return "scoreView";
        }}catch(Exception e){
            e.printStackTrace();
        }
        this.setMsg("统计失败！请联系管理员！");
        return "scoreView";


    }

    public String viewAllScore() throws Exception {
        int courseid = -1;
        if (ServletActionContext.getRequest().getParameter("courseid")!=null) {
            courseid = Integer.parseInt(ServletActionContext.getRequest().getParameter("courseid").toString());
        }
        if (!scoreService.allScoreViews(courseid).isEmpty()) {
            setScoreViews(scoreService.allScoreViews(courseid));
            return "scoreView";
        }
        this.setMsg("查询失败！请联系管理员！");
        return "scoreView";
    }
}

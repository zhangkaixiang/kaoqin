package com.action;

import com.model.SignRecordNum;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Sign;
import com.orm.Signrecord;
import com.orm.Student;
import com.service.SignRecordService;
import com.service.SignRecordServiceImpl;
import com.service.SignSettingService;
import com.service.SignSettingServiceImpl;
import com.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.struts2.ServletActionContext;

public class IndexAction extends ActionSupport {
    //服务引用

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SignSettingService signSettingService = new SignSettingServiceImpl();
    SignRecordService signRecordService = new SignRecordServiceImpl();
    //待签到列表
    private List<Sign> unSignedByWeek;
    private List<Sign> unSignedByDate;
    //已签到列表
    private List<Sign> signedList;
    //缺课没有签到的列表
    private List<Sign> loseSignList;
    //传递消息
    private String msg;
    //已经签到的记录
    private List<Signrecord> signedRecords;
    //签到和缺课数
    private SignRecordNum num;

    public SignRecordNum getNum() {
        return num;
    }

    public void setNum(SignRecordNum num) {
        this.num = num;
    }

    //已经签到的Sign的id
    public List<Signrecord> getSignedRecords() {
        return signedRecords;
    }

    public void setSignedRecords(List<Signrecord> signedRecords) {
        this.signedRecords = signedRecords;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Sign> getUnSignedByDate() {
        return unSignedByDate;
    }

    public void setUnSignedByDate(List<Sign> unSignedByDate) {
        this.unSignedByDate = unSignedByDate;
    }

    public List<Sign> getUnSignedByWeek() {
        return unSignedByWeek;
    }

    public void setUnSignedByWeek(List<Sign> unSignedByWeek) {
        this.unSignedByWeek = unSignedByWeek;
    }

    public List<Sign> getLoseSignList() {
        return loseSignList;
    }

    public void setLoseSignList(List<Sign> loseSignList) {
        this.loseSignList = loseSignList;
    }

    public List<Sign> getSignedList() {
        return signedList;
    }

    public void setSignedList(List<Sign> signedList) {
        this.signedList = signedList;
    }

    //初始化页面，签到，未签到，已签到，缺课
    @Override
    public String execute() throws Exception {
        //初始化待签到列表即考勤规则（根据星期和日期与当日比对，读取待签到列表）
        //获取当前星期几(int)，获取当前日期new Date();
        int dayOfWeek = DateUtil.getDayOfWeek();
        //获取当前学生
        Student currentStu = (Student) ActionContext.getContext().getSession().get("student");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = sf.format(new Date());
        /* 
        SimpleDateFormat sftoDate = new SimpleDateFormat("yyyy-MM-dd");
        //将字符串转换为日期用parse
        Date today = sftoDate.parse(todayDate);
         */
        /****获取签到列表，主页显示的签到列表，缺课，有关操作，从这里开始****/
        //根据星期几列出今日签到列表,仅列出该学生所属班级的今日全部签到列表
        List<Sign> listByWeek = signSettingService.findSignSettingByWeekClass(dayOfWeek, currentStu.getTClass().getId());
        //根据自定义日期列出今日签到列表
        List<Sign> listByDate = signSettingService.findSignSettingByDate(todayDate);

        //*核心，主页分三块，缺课，待签到，已签到*//
        //查出该生今天已经签过到的记录的签到规则。将该签到规则添加到已签到列表
        List<Signrecord> signrecords = signRecordService.listRecords(todayDate, currentStu.getId());
        List<Sign> preparefordel = new ArrayList<Sign>();
        //从签到规则中删除已签到的规则，无法使用remove，因为线程有锁，否则会引发并发修改异常
        for (Signrecord signrecord : signrecords) {
            for (Sign sign : listByWeek) {
                if (sign.getId().equals(signrecord.getSign().getId())) {
                    preparefordel.add(sign);
                }
            }
            for (Sign sign : listByDate) {
                if (sign.getId().equals(signrecord.getSign().getId())) {
                    preparefordel.add(sign);
                }
            }
        }
        //从签到规则中删除已签到的规则
        listByWeek.removeAll(preparefordel);
        listByDate.removeAll(preparefordel);

        //缺课记录，从带签到列表中删除已经缺课
        //判断待签到列表，如果签到规则时间的结束时间小于现在时间，则认定为缺课，存入数据库
        List<Sign> losts = new ArrayList<Sign>();
        //遍历，获取缺课记录sign，存入losts里
        for (Sign sign : listByWeek) {
            int endhour = Integer.parseInt(sign.getEndTime().substring(0, 2));
            int endminute = Integer.parseInt(sign.getEndTime().substring(3, 5));
            Calendar now = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), endhour, endminute, 0);

            long nowMillis = now.getTimeInMillis();
            long endMillis = endCalendar.getTimeInMillis();
            //插入缺课记录不仅要判断时间，还要判断日期。
            if (endMillis < nowMillis) {
                losts.add(sign);
                //向数据库插入缺课记录
                //判断是否已存在、获取该生今天所有缺课记录，如果当前student和sign规则已经存在，则不允许添加重复的。
                @SuppressWarnings("unused")
				List<Signrecord> lostrecords = signRecordService.listLostSignRecord(todayDate, currentStu.getId());
                if (!signRecordService.isExist(sign.getId(), todayDate, currentStu.getId())) {
                    Signrecord record = new Signrecord();
                    record.setSign(sign);
                    record.setStudent(currentStu);
                    record.setSigndate(todayDate);
                    record.setLost(true);
                    signRecordService.save(record);
                }
            }
        }
        for (Sign sign : listByDate) {
            int endhour = Integer.parseInt(sign.getEndTime().substring(0, 2));
            int endminute = Integer.parseInt(sign.getEndTime().substring(3, 5));
            Calendar now = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), endhour, endminute, 0);

            long nowMillis = now.getTimeInMillis();
            long endMillis = endCalendar.getTimeInMillis();
            if (endMillis < nowMillis) {
                //若为自定义日期规则，若今日的日期和规则日期不相等，跳出
                if (sign.getCustomDate() != null && !sign.getCustomDate().equals(todayDate)) {
                    break;
                }
                losts.add(sign);
                //向数据库插入缺课记录
                //判断是否已存在、获取该生今天所有缺课记录，如果当前student和sign规则已经存在，则不允许添加重复的。
                @SuppressWarnings("unused")
				List<Signrecord> lostrecords = signRecordService.listLostSignRecord(todayDate, currentStu.getId());
                if (!signRecordService.isExist(sign.getId(), todayDate, currentStu.getId())) {
                    Signrecord record = new Signrecord();
                    record.setSign(sign);
                    record.setStudent(currentStu);
                    record.setSigndate(todayDate);
                    record.setLost(true);
                    signRecordService.save(record);
                }
            }
        }
        //从签到规则中删除缺课的规则
        listByWeek.removeAll(losts);
        listByDate.removeAll(losts);

//        显示签到数，缺课数
        SignRecordNum sNum = new SignRecordNum();
        sNum.setSignedNum(signRecordService.countTodaySignedRecord(currentStu.getId()));
        sNum.setSignedTotalNum(signRecordService.countTotalSignedRecord(currentStu.getId()));
        sNum.setLostSignedNum(signRecordService.countTodayLostsSignRecord(currentStu.getId()));
        sNum.setLostSignedTotalNum(signRecordService.countTotalLostsSignRecord(currentStu.getId()));
        setNum(sNum);

        setLoseSignList(losts);
        setSignedRecords(signrecords);

        setUnSignedByWeek(listByWeek);
        setUnSignedByDate(listByDate);


        ActionContext.getContext().getSession().put("signedRecords", signrecords);
        ActionContext.getContext().getSession().put("unSignedByWeek", unSignedByWeek);
        ActionContext.getContext().getSession().put("unSignedByDate", unSignedByDate);
        ActionContext.getContext().getSession().put("loseSignList", loseSignList);
        ActionContext.getContext().getSession().put("num", sNum);
        return SUCCESS;
    }
    //签到逻辑

    public String signIn() throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = sf.format(new Date());
        int sid = 0;
        //根据id找到要签到的时间段 先获取sign
        if (ServletActionContext.getRequest().getParameter("sid") != null) {
            sid = Integer.parseInt(ServletActionContext.getRequest().getParameter("sid"));
        }
        //当前学生
        Student currentStu = (Student) ActionContext.getContext().getSession().get("student");
        //考虑输入输出，输入：当前时间 。 输出：判断结果，是否在指定时间之内,存入数据库签到记录。
        /*以当前星期几&&当前日期（与自定义日期比较）读取待签到列表*/
        //程序根据当前时间，取出当前班级今天所有待签到列表（包括自定义规则）显示给学生
        //循环判断当前时间是否在这些规则列表内，若存在，则签到成功，保存数据至服务器，令外将当前课程取出来。
        //如果已经过了签到截止时间，则显示未签到。单击签到，若处于签到时间则签到成功，若未到签到时间，则显示未到签到时间。
        //用户单击签到按钮，会提交当前时间至服务器。
        Sign sign = signSettingService.load(sid);
        Calendar now = Calendar.getInstance();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        //截取时分，转换为整数
        int starthour = Integer.parseInt(sign.getStartTime().substring(0, 2));
        int startminute = Integer.parseInt(sign.getStartTime().substring(3, 5));
        int endhour = Integer.parseInt(sign.getEndTime().substring(0, 2));
        int endminute = Integer.parseInt(sign.getEndTime().substring(3, 5));


        start.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), starthour, startminute, 0);
        end.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), endhour, endminute, 0);
        long nowMillis = now.getTimeInMillis();
        long startMillis = start.getTimeInMillis();
        long endMillis = end.getTimeInMillis();


        //判断针对某一门课的某一签到规则，是否同一个ip进行多次签到
        //取出当前签到的规则，获取客户端签到ip地址。如果针对该签到规则的记录中，已经存在相同的ip地址，说明已经有学生签到了（签到记录是定时器，为防止学生签到过快，需要定时器1秒执行一次）
        //判断是否在签到范围内
        if (nowMillis >= startMillis && nowMillis <= endMillis) {
            //添加记录
            @SuppressWarnings("rawtypes")
			Iterator it = sign.getSignrecords().iterator();
            while (it.hasNext()) {
                Signrecord record = (Signrecord) it.next();
                if (ServletActionContext.getRequest().getRemoteAddr().equals(record.getIp())) {
                	this.setMsg("您的IP已存在该课程签到记录，请勿为他人代签，否则后果自负！");
                    return "ipSame";
                }

                if (record.getStudent().getId().equals(currentStu.getId()) && record.getLost().equals(false)) {
                    System.out.println(record.getSign().getCourse().getName() + " 已经签过到了");
                    setMsg(record.getSign().getCourse().getName() + "已经签过到了");
                    addActionMessage(record.getSign().getCourse().getName() + "已经签过到了");
                    return "signed";
                }
            }
            Signrecord record = new Signrecord();
            record.setSign(sign);
            record.setStudent(currentStu);
            record.setSigndate(todayDate);
            record.setSignTime(new Date());
            record.setIp(ServletActionContext.getRequest().getRemoteAddr());
            record.setLost(false);
            if (signRecordService.save(record)) {
                addActionMessage(record.getSign().getCourse().getName() + "签到成功");
                setMsg(record.getSign().getCourse().getName() + "签到成功");
                System.out.println(record.getSign().getCourse().getName() + "签到成功");
                return "signok";
            }
        }
        System.out.println("不在签到时间段内！");
        setMsg("不在签到时间段内!");
        addActionMessage("不在签到时间段内");
        return "notIn";
    }
}

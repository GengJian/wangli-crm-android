package commonlyused.bean;

/**
 * ****************************************************************
 * 文件名称:ServerDate.java
 * 作    者:Created by zhengss
 * 创建时间:2019/5/10 15:47
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/5/10 1.00 初始版本
 * ****************************************************************
 */
public class ServerDate {
    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    private String today;
    private String todayNow;

    public String getTodayNow() {
        return todayNow;
    }

    public void setTodayNow(String todayNow) {
        this.todayNow = todayNow;
    }

    public String getTodayOnDutyTime() {
        return todayOnDutyTime;
    }

    public void setTodayOnDutyTime(String todayOnDutyTime) {
        this.todayOnDutyTime = todayOnDutyTime;
    }

    private String todayOnDutyTime;
}

package com.atqingke.schedule.dao.impl;

import com.atqingke.schedule.dao.BaseDao;
import com.atqingke.schedule.dao.SysScheduleDao;
import com.atqingke.schedule.pojo.SysSchedule;

import java.util.List;

public class SysScheduleDaoImpl extends BaseDao implements SysScheduleDao {

    @Override
    public List<SysSchedule> getAllByUid(int uid) {
        String sql = "select * from sys_schedule where uid = ?";
        List<SysSchedule> scheduleList = baseQuery(SysSchedule.class, sql, uid);
        return null != scheduleList && !scheduleList.isEmpty() ? scheduleList : null;
    }

    @Override
    public int saveDefault(SysSchedule sysSchedule) {
        String sql = "insert into sys_schedule(sid, uid, title, completed) VALUES(DEFAULT, ?, ?, ?)";
        return baseUpdate(sql, sysSchedule.getUid(), sysSchedule.getTitle(), sysSchedule.getCompleted());
    }

    @Override
    public int update(SysSchedule sysSchedule) {
        String sql = "update sys_schedule set title = ?, completed = ? where sid = ?";
        return baseUpdate(sql, sysSchedule.getTitle(), sysSchedule.getCompleted(), sysSchedule.getSid());
    }

    @Override
    public int delete(int sid) {
        String sql = "delete from sys_schedule where sid = ?";
        return baseUpdate(sql, sid);
    }
}

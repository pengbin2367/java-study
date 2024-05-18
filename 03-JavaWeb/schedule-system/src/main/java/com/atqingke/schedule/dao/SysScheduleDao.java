package com.atqingke.schedule.dao;

import com.atqingke.schedule.pojo.SysSchedule;

import java.util.List;

public interface SysScheduleDao {
    List<SysSchedule> getAllByUid(int uid);

    int saveDefault(SysSchedule sysSchedule);

    int update(SysSchedule sysSchedule);

    int delete(int sid);
}

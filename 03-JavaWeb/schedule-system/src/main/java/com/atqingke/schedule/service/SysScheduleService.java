package com.atqingke.schedule.service;

import com.atqingke.schedule.pojo.SysSchedule;

import java.util.List;

public interface SysScheduleService {
    List<SysSchedule> getAllByUid(int uid);

    int saveDefault(SysSchedule sysSchedule);

    int update(SysSchedule sysSchedule);

    int delete(int sid);
}

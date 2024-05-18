package com.atqingke.schedule.service.impl;

import com.atqingke.schedule.dao.SysScheduleDao;
import com.atqingke.schedule.dao.impl.SysScheduleDaoImpl;
import com.atqingke.schedule.pojo.SysSchedule;
import com.atqingke.schedule.service.SysScheduleService;

import java.util.List;

public class SysScheduleServiceImpl implements SysScheduleService {

    private final SysScheduleDao sysScheduleDao = new SysScheduleDaoImpl();

    @Override
    public List<SysSchedule> getAllByUid(int uid) {
        return sysScheduleDao.getAllByUid(uid);
    }

    @Override
    public int saveDefault(SysSchedule sysSchedule) {
        return sysScheduleDao.saveDefault(sysSchedule);
    }

    @Override
    public int update(SysSchedule sysSchedule) {
        return sysScheduleDao.update(sysSchedule);
    }

    @Override
    public int delete(int sid) {
        return sysScheduleDao.delete(sid);
    }
}

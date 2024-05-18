package com.atqingke.schedule.controller;

import com.atqingke.schedule.common.Result;
import com.atqingke.schedule.pojo.SysSchedule;
import com.atqingke.schedule.service.SysScheduleService;
import com.atqingke.schedule.service.impl.SysScheduleServiceImpl;
import com.atqingke.schedule.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/schedule/*")
public class SysScheduleController extends BaseController{

    private final SysScheduleService sysScheduleService = new SysScheduleServiceImpl();

    protected void getAllByUid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = Integer.parseInt(req.getParameter("uid"));
        List<SysSchedule> scheduleList = sysScheduleService.getAllByUid(uid);
        Result result = null;
        if (scheduleList != null) {
            result = Result.ok(scheduleList);
        }
        WebUtil.writeJson(resp, result);
    }

    protected void saveDefault(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = Integer.parseInt(req.getParameter("uid"));
        SysSchedule sysSchedule = new SysSchedule(null, uid, "", 0);
        int rows = sysScheduleService.saveDefault(sysSchedule);
        Result result = null;
        if (rows > 0) {
            result = Result.ok("success");
        }
        WebUtil.writeJson(resp, result);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SysSchedule sysSchedule = WebUtil.readJson(req, SysSchedule.class);
        int rows = sysScheduleService.update(sysSchedule);
        Result result = null;
        if (rows > 0) {
            result = Result.ok("success");
        }
        WebUtil.writeJson(resp, result);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sid = Integer.parseInt(req.getParameter("sid"));
        int rows = sysScheduleService.delete(sid);
        Result result = null;
        if (rows > 0) {
            result = Result.ok("success");
        }
        WebUtil.writeJson(resp, result);
    }
}

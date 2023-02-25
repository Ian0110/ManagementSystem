package com.example.managementsystem.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.managementsystem.Dao.*;
import com.example.managementsystem.Domain.*;
import com.example.managementsystem.Service.PersonnelManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class PersonnelManagementServiceImpl implements PersonnelManagementService {

    @Autowired
    private TUserDao tUserDao;

    @Autowired
    private TDeptDao tDeptDao;

    @Autowired
    private TEmployeeDao tEmployeeDao;

    @Autowired
    private THolidayDao tHolidayDao;

    @Autowired
    private TConfigDao tConfigDao;

    @Override
    public boolean login(TUser user) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("user_account",user.getUserAccount());
        map.put("user_pwd",user.getUserPwd());

        if(tUserDao.selectByMap(map).size() == 1)
            return true;
        else
            return false;
    }

    @Override
    public List<TUser> getUsers() {
        return tUserDao.selectList(null);
    }

    @Override
    public TUser getUserByAccount(String account) {
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account",account);
        return tUserDao.selectOne(queryWrapper);
    }

    @Override
    public List<TDept> getAllDepts() {
        return tDeptDao.selectList(null);
    }

    @Override
    public String deleteDeptById(int id) {
        List<TEmployee> tEmployees = new ArrayList<>();
        QueryWrapper<TEmployee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("emp_dept_id",id);
        if(tEmployeeDao.selectList(queryWrapper).size() != 0)
            return "该部门下还有员工关联，不允许删除！";
        else{
            if(tDeptDao.deleteById(id) == 1)
                return "删除成功";
            else
                return "删除失败";
        }

    }

    @Override
    public String updateDeptById(TDept tDept) {
        QueryWrapper<TDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id",tDept.getDeptId());
        if (!tDeptDao.selectById(tDept.getDeptId()).getDeptNo().equals(tDept.getDeptNo()))
            return "不允许修改部门编号！";
        if(tDeptDao.update(tDept,queryWrapper) == 1)
            return "修改成功";
        else
            return "修改失败";

    }

    @Override
    public List<TEmployee> getAllEmployee() {
        return tEmployeeDao.selectList(null);
    }

    @Override
    public List<TEmployee> getEmployeeByName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("emp_name",name);
        return tEmployeeDao.selectList(queryWrapper);
    }

    @Override
    public String addEmployee(TEmployee tEmployee) {
        if(tEmployeeDao.insert(tEmployee) == 1)
            return "添加成功！";
        else
            return "添加失败！";
    }

    @Override
    public String delEmployeeById(int id) {
        if(tEmployeeDao.deleteById(id) == 1)
            return "删除成功";
        else
            return "删除失败";
    }

    @Override
    public String updateEmployee(TEmployee tEmployee) {
        QueryWrapper<TEmployee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("emp_id",tEmployee.getEmpId());
        if (!tEmployeeDao.selectById(tEmployee.getEmpId()).getEmpNo().equals(tEmployee.getEmpNo()))
            return "不允许修改员工编号！";
        if(tEmployeeDao.update(tEmployee,queryWrapper) == 1)
            return "修改成功";
        else
            return "修改失败";
    }

    @Override
    public List<LeaveForm> getAllHolidays() {
        List <THoliday> tHolidays = tHolidayDao.selectList(null);
        List <LeaveForm> leaveForms = new ArrayList<>();
        for(THoliday tHoliday:tHolidays){
            LeaveForm leaveForm = new LeaveForm();
            leaveForm.setHolidayBz(tHoliday.getHolidayBz());
            leaveForm.setHolidayEndTime(tHoliday.getHolidayEndTime());
            leaveForm.setHolidayNo(tHoliday.getHolidayNo());
            leaveForm.setHolidayType(tHolidayDao.getHolidayType(tHoliday.getHolidayTypeId()));
            leaveForm.setHolidayStatus(tHoliday.getHolidayStatus());
            leaveForm.setHolidayStartTime(tHoliday.getHolidayStartTime());
            leaveForm.setHolidayUserNo(tHoliday.getHolidayUserNo());
            leaveForms.add(leaveForm);
        }
        return leaveForms;
    }

    @Override
    public List<LeaveForm> getHolidaysByCondition(String holidayUserNo, String holidayType, String holidayStatus) {
        List<LeaveForm> leaveForms = new ArrayList<>();
        if(holidayUserNo != null){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.like("holiday_user_no",holidayUserNo);
            List<THoliday> tHolidays = tHolidayDao.selectList(queryWrapper);
            for(THoliday tHoliday:tHolidays){
                LeaveForm leaveForm = new LeaveForm(tHoliday.getHolidayNo(),tHoliday.getHolidayUserNo(),tHolidayDao.getHolidayType(tHoliday.getHolidayId()),tHoliday.getHolidayBz(),tHoliday.getHolidayEndTime(),tHoliday.getHolidayStartTime(),tHoliday.getHolidayStatus());
                leaveForms.add(leaveForm);
            }
            return leaveForms;

        }
        else if(holidayType != null){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("config_name",holidayType);
            int holidayTypeId = tConfigDao.selectOne(queryWrapper).getConfigId();
            queryWrapper.clear();

            queryWrapper.eq("holiday_type_id",holidayTypeId);
            List<THoliday> tHolidays = tHolidayDao.selectList(queryWrapper);
            for(THoliday tHoliday:tHolidays){
                LeaveForm leaveForm = new LeaveForm(tHoliday.getHolidayNo(),tHoliday.getHolidayUserNo(),tHolidayDao.getHolidayType(tHoliday.getHolidayId()),tHoliday.getHolidayBz(),tHoliday.getHolidayEndTime(),tHoliday.getHolidayStartTime(),tHoliday.getHolidayStatus());
                leaveForms.add(leaveForm);
            }
            return leaveForms;
        }
        else if(holidayStatus != null){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("holiday_status",holidayStatus);
            List<THoliday> tHolidays = tHolidayDao.selectList(queryWrapper);
            for(THoliday tHoliday:tHolidays){
                LeaveForm leaveForm = new LeaveForm(tHoliday.getHolidayNo(),tHoliday.getHolidayUserNo(),tHolidayDao.getHolidayType(tHoliday.getHolidayId()),tHoliday.getHolidayBz(),tHoliday.getHolidayEndTime(),tHoliday.getHolidayStartTime(),tHoliday.getHolidayStatus());
                leaveForms.add(leaveForm);
            }
            return leaveForms;
        }
        return null;
    }

    @Override
    public String addHoliday(LeaveForm leaveForm) {
        THoliday tHoliday = new THoliday();
        Random random = new Random();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("config_name",leaveForm.getHolidayType());
        Date date = new Date(System.currentTimeMillis());
        String randomStr = "";
        for(int i = 0;i<5;i++)
            randomStr += random.nextInt(10);
        tHoliday.setHolidayNo("holiday-"+randomStr);
        tHoliday.setHolidayCreateTime(date);
        tHoliday.setHolidayUpdateTime(date);
        tHoliday.setHolidayTypeId(tConfigDao.selectOne(queryWrapper).getConfigId());
        tHoliday.setHolidayBz(leaveForm.getHolidayBz());
        tHoliday.setHolidayStatus(leaveForm.getHolidayStatus());
        tHoliday.setHolidayUserNo(leaveForm.getHolidayUserNo());
        tHoliday.setHolidayStartTime(leaveForm.getHolidayStartTime());
        tHoliday.setHolidayEndTime(leaveForm.getHolidayEndTime());

        if(tHolidayDao.insert(tHoliday) == 1)
            return "请假成功！";
        else
            return "请假失败!";
    }

    @Override
    public String updateHoliday(LeaveForm leaveForm,int id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("config_name",leaveForm.getHolidayType());
        int typeId = tConfigDao.selectOne(queryWrapper).getConfigId();
        queryWrapper.clear();
        THoliday tHoliday = new THoliday();
        queryWrapper.eq("holiday_id",id);
        tHoliday = tHolidayDao.selectOne(queryWrapper);
        if(tHoliday.getHolidayStatus().equals("已提交"))
            return "已提交的请假申请不允许被修改！";
        tHoliday.setHolidayStatus(leaveForm.getHolidayStatus());
        tHoliday.setHolidayTypeId(typeId);
        tHoliday.setHolidayBz(leaveForm.getHolidayBz());
        tHoliday.setHolidayStartTime(leaveForm.getHolidayStartTime());
        tHoliday.setHolidayEndTime(leaveForm.getHolidayEndTime());
        Date date = new Date(System.currentTimeMillis());
        tHoliday.setHolidayUpdateTime(date);

        if(tHolidayDao.update(tHoliday,queryWrapper) == 1)
            return "修改成功！";
        else
            return "修改失败！";
    }

    @Override
    public String delHolidayById(int id) {
        QueryWrapper<THoliday> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("holiday_id",id);
        if(tHolidayDao.selectOne(queryWrapper).getHolidayStatus().equals("草稿")) {
            if (tHolidayDao.deleteById(id) == 1)
                return "删除成功！";
            else
                return "删除失败！";
        }
        else
            return "非草稿状态不可以删除！";

    }
}

package com.example.managementsystem.Service;

import com.example.managementsystem.Domain.*;

import java.util.List;

public interface PersonnelManagementService {
    public boolean login(TUser user);
    public List<TUser> getUsers();
    public TUser getUserByAccount(String account);

    public List<TDept> getAllDepts();
    public String deleteDeptById(int id);
    public String updateDeptById(TDept tDept);

    public List<TEmployee> getAllEmployee();
    public List<TEmployee> getEmployeeByName(String name);
    public String addEmployee(TEmployee tEmployee);
    public String delEmployeeById(int id);
    public String updateEmployee(TEmployee tEmployee);

    public List<LeaveForm> getAllHolidays();
    public List<LeaveForm> getHolidaysByCondition(String holidayUserNo,String holidayType,String holidayStatus);
    public String addHoliday(LeaveForm leaveForm);
    public String updateHoliday(LeaveForm leaveForm,int id);
    public String delHolidayById(int id);

    public List<TRole> getAllRoles();
    public TRole getRoleById(int id);
    public String delRoleById(int id);
    public String updateRole(int id, String name);

    public List<TPermissions> getAllPermissions();
    public List<TPermissions> selectPermissionByCondition(Integer roleId, Integer menuId);
    public String addPermission(Integer roleId, Integer menuId);
    public String delPermissionById(int id);
    public String updatePermissionByCondition(int id, Integer roleId, Integer menuId);

}

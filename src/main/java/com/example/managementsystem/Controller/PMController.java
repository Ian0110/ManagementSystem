package com.example.managementsystem.Controller;

import com.example.managementsystem.Configration.KaptchaConfig;
import com.example.managementsystem.Domain.*;
import com.example.managementsystem.Service.PersonnelManagementService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/PM")
@CrossOrigin
public class PMController {

    @Autowired
    private PersonnelManagementService pms;

    @Autowired
    private KaptchaConfig captchaProducer;

    private String MD5(String pswd,String salt){
        return DigestUtils.md5DigestAsHex((pswd+salt).getBytes());
    }

    @GetMapping("/getVerificationCode")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaOutputStream = null;
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String verifyCode = captchaProducer.getDefaultKaptcha().createText();
            httpServletRequest.getSession().setAttribute("verifyCode", verifyCode);
            BufferedImage challenge = captchaProducer.getDefaultKaptcha().createImage(verifyCode);
            ImageIO.write(challenge, "jpg", imgOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        captchaOutputStream = imgOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
    @PostMapping(value = "/login")
    public String login(TUser tUser,@RequestParam("verifyCode") String verifyCode,
                         HttpSession session) throws UnsupportedEncodingException {

        TUser tUser1 = new TUser();
        if(pms.getUserByAccount(tUser.getUserAccount()) != null){
            tUser1 = pms.getUserByAccount(tUser.getUserAccount());
        }
        else
            return "用户不存在";
        String pwd =  DigestUtils.md5DigestAsHex((tUser.getUserPwd() + tUser1.getUserSalt()).getBytes("utf-8"));


        String kaptchaCode = session.getAttribute("verifyCode") + "";

        if(verifyCode.equals(kaptchaCode)){
            if(pwd.equals(tUser1.getUserPwd())){
                return "登录成功!";
            }
            else
                return "用户名、密码不匹配";
        }
        else
            return "验证码错误";
    }
    @PostMapping(value = "/getUsers")
    public List<TUser> getUser(){
        return pms.getUsers();
    }
    @GetMapping(value = "/getTDepts")
    public List<TDept> getTDepts(){
        return pms.getAllDepts();
    }
    @GetMapping(value = "/getTDeptsName")
    public List<String> getTDeptsName(){
        List<TDept> tDepts = new ArrayList<>();
        List<String> names = new ArrayList<>();
        tDepts = pms.getAllDepts();

        for(TDept tDept:tDepts)
            names.add(tDept.getDeptName());

        return names;
    }
    @PostMapping(value = "/delTDept")
    public String delTDeptById(int id){
        return pms.deleteDeptById(id);
    }
    @PostMapping(value = "/updateTDept")
    public String updateTDept(TDept tDept){
        return pms.updateDeptById(tDept);
    }
    @PostMapping(value = "/getEmployees")
    public List<TEmployee> getEmployees(){
        return pms.getAllEmployee();
    }
    @PostMapping(value = "/getEmployeeByName")
    public List<TEmployee> getEmployeeByName(String name){
        return pms.getEmployeeByName(name);
    }
    @PostMapping(value = "/addEmployee")
    public String addEmployee(TEmployee tEmployee){
        return pms.addEmployee(tEmployee);
    }
    @PostMapping(value = "/delEmployeeById")
    public String delEmployeeById(int id){
        return pms.delEmployeeById(id);
    }
    @PostMapping(value = "/updateEmployee")
    public String updateEmployee(TEmployee tEmployee){
        return pms.updateEmployee(tEmployee);
    }
    @PostMapping(value = "/getAllHolidays")
    public List<LeaveForm> getAllHolidays(){
        return pms.getAllHolidays();
    }
    @PostMapping(value = "/getHolidaysByCondition")
    public List<LeaveForm> getHolidaysByCondition(String holidayUserNo, String holidayType, String holidayStatus){
        return pms.getHolidaysByCondition(holidayUserNo,holidayType,holidayStatus);
    }
    @PostMapping(value = "/addHoliday")
    public String addHoliday(LeaveForm leaveForm){
        return pms.addHoliday(leaveForm);
    }
    @PostMapping(value = "/updateHoliday")
    public String updateHoliday(LeaveForm leaveForm,int id){
        return pms.updateHoliday(leaveForm,id);
    }
    @PostMapping(value = "/delHolidayById")
    public String delHolidayById(int id){
        return pms.delHolidayById(id);
    }
    /*
    *
    public List<LeaveForm> getAllHolidays();
    public List<THoliday> getHolidaysByCondition(String holidayUserNo,String holidayType,String holidayStatus);
    public String addHoliday(LeaveForm leaveForm);
    public String updateHoliday(LeaveForm leaveForm,int id);
    public String delHolidayById(int id);
    * */

    @PostMapping(value = "/getAllRoles")
    public List<TRole> getAllRoles(){return pms.getAllRoles();};
    @PostMapping(value = "/getRoleById")
    public TRole getRoleById(int id){return pms.getRoleById(id);};
    @PostMapping(value = "/delRoleById")
    public String delRoleById(int id){return pms.delRoleById(id);};
    @PostMapping(value = "/updateRole")
    public String updateRole(int id, String name){return pms.updateRole(id, name);};
    @PostMapping(value = "/getAllPermissions")
    public List<TPermissions> getAllPermissions(){return pms.getAllPermissions();};
    @PostMapping(value = "/selectPermissionByCondition")
    public List<TPermissions> selectPermissionByCondition(Integer roleId, Integer menuId){return pms.selectPermissionByCondition(roleId, menuId);};
    @PostMapping(value = "/addPermission")
    public String addPermission(Integer roleId, Integer menuId){return pms.addPermission(roleId, menuId);};
    @PostMapping(value = "delPermissionById")
    public String delPermissionById(int id){return pms.delPermissionById(id);};
    @PostMapping(value = "updatePermissionByCondition")
    public String updatePermissionByCondition(int id, Integer roleId, Integer menuId){return pms.updatePermissionByCondition(id, roleId, menuId);};
}

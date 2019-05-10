package com.lvr.springbootmanger.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lvr.springbootmanger.bean.User;
import com.lvr.springbootmanger.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class UserController {
    @Resource
    private IUserService iUserService;

    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping(value = "/user/index")
    public String questSelectUserList(User user, Integer pageNumber, Model model) {
        PageInfo<User> userList = iUserService.selectUserList(user, pageNumber);
        model.addAttribute("user", userList);
        return "admin/user/index";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String add() {

        return "admin/user/form";
    }

    @ResponseBody
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(User user) {
        int flag = iUserService.insertSelective(user);

        if (flag > 0) {
            return "{ msg: '添加成功'}";
        }
        return "{ msg: '添加失败'}";
    }

    @RequestMapping(value = "user/edit/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Integer id, Model model) {
        User user = iUserService.selectByPrimaryKey(id);
        model.addAttribute("userInfo", user);
        return "admin/user/form-upda";
    }

    @ResponseBody
    @RequestMapping(value = "user/edit", method = RequestMethod.POST)
    public String updateUser(User user) {
        int edit = iUserService.updateByPrimaryKeySelective(user);
        if (edit > 0) {
            return "{ msg: '修改成功'}";
        } else
            return "{ msg: '修改失败'}";

    }

    @ResponseBody
    @RequestMapping(value = "user/delete/{id}", method = RequestMethod.POST)
    public String delUser(@PathVariable("id") Integer id) throws IOException {
        int falg = iUserService.deleteUser(id);

        String msg;
        JSONObject jsonObject = new JSONObject();

        if (falg > 0) {
            msg = "删除成功";
        } else
            msg = "删除失败";

        jsonObject.put("msg", msg);

        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession session) {
        session.invalidate();
        return "admin/login";
    }
@RequestMapping(value="login",method = RequestMethod.POST)
    public String login(String userAccount, String userPassword, Model model, HttpSession session) {
        User user = iUserService.queryUserByUserAccountandPassword(userAccount, userPassword);
        if (user != null) {
            session.setAttribute("userInfo", user);
            return "admin/index";
        }else{
            model.addAttribute("message","用户名或密码错误");
            return  "admin/login";
        }

    }
}

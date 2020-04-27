package cn.zucc.edu.mansousou.controller;

import cn.zucc.edu.mansousou.service.inter.UserService;
import cn.zucc.edu.mansousou.util.Result;
import javax.validation.constraints.NotNull;
import java.lang.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 17:26
 */
@RestController
@RequestMapping("/api")
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value ="/getInfo",method = {RequestMethod.GET})
    public Result getInfo(){
        Result result = new Result();
        result.setCode(20000);
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put("roles",new String[]{"admin"});
        hashMap.put("introduction","I am a super administrator");
        hashMap.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        hashMap.put("name","Super Admin");
        result.setData(hashMap);
        return result;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestParam("username") @NotNull String username,
                        @RequestParam("password") @NotNull String password){
        if (username.isEmpty() || username == null ){
            return Result.build(400,"用户名为空");
        }
        if (password.isEmpty() || password == null){
            return Result.build(400,"密码为空");
        }
        Result result = userService.loginCheck(username,password);
        if (result.getCode() == 400){
            return Result.error("用户名或密码错误");
        }
        if (result.getCode() == 200){
            HashMap<String,Object> hashMap = new HashMap<String, Object>();
            hashMap.put("token","admin-token");
            result.setData(hashMap);
        }
        return result;
    }



    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    public Result reg(@RequestParam("username") @NotNull String username,
                      @RequestParam("password1") @NotNull String password1,
                      @RequestParam("password2") @NotNull String password2){
        if (username.isEmpty() || username == null){
            return Result.build(400,"用户名为空");
        }
        if (password1.isEmpty() || password1 == null){
            return Result.build(400,"密码为空");
        }
        if (password2.isEmpty() || password2 == null){
            return Result.build(400,"密码为空");
        }
        return userService.regUser(username,password1,password2);
    }
}

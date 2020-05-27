package cn.zucc.edu.mansousou.service.impl;

import cn.zucc.edu.mansousou.entity.jpa.User;
import cn.zucc.edu.mansousou.repository.jpa.UserJpaRepository;
import cn.zucc.edu.mansousou.service.inter.UserService;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 18:22
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {


    UserJpaRepository userJpaRepository;

    @Autowired
    public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Result loginCheck(String username, String password) {
        User user = userJpaRepository.findByUserName(username);
        if (user.getPassword().equalsIgnoreCase(password)){
            return Result.build(200,"success",user.getUserId());
        }else {
            return Result.error();
        }
    }

    @Override
    public Result regUser(String username, String password1, String password2) {
        if (userJpaRepository.findByUserName(username) == null){
            if (password1.equalsIgnoreCase(password2)){
                User user = new User();
                user.setUserName(username);
                user.setPassword(password1);
                user.setCreateTime(new Date());
                user.setUpdateTime(new Date());
                userJpaRepository.save(user);
                return Result.success("注册成功");
            }
            return Result.error("两次输入密码不一致");
        }
        return Result.error("该用户已存在");
    }

    @Override
    @Transactional
    public User getUserByUserId(Integer userId) {
        User user = userJpaRepository.findByUserId(userId);
        Object data = user;
        return user;
    }

    @Override
    public boolean isExist(Integer userId) {
        return userJpaRepository.findByUserId(userId).equals(null)?false:true;
    }
}

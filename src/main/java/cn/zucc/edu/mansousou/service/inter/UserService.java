package cn.zucc.edu.mansousou.service.inter;

import cn.zucc.edu.mansousou.entity.jpa.User;
import cn.zucc.edu.mansousou.util.Result;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 18:21
 */
public interface UserService {
    /**
     * 登录检查
     * @param username
     * @param password
     * @return
     */
    Result loginCheck(String username, String password);

    /**
     * 用户注册
     * @param username
     * @param password1
     * @param password2
     * @return
     */
    Result regUser(String username,String password1,String password2);

    /**
     * 判断用户是否存在
     * @param userId
     * @return
     */
    boolean isExist(Integer userId);
}

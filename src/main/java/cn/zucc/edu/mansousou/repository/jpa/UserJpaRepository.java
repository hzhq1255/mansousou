package cn.zucc.edu.mansousou.repository.jpa;

import cn.zucc.edu.mansousou.entity.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 18:18
 */
public interface UserJpaRepository extends JpaRepository<User,Integer> {

    /**
     * 通过用户名查找用户
     * @param name
     * @return
     */
    User findByUserName(String name);

    /**
     *
     * @param id
     * @return
     */
    User findByUserId(Integer id);
}

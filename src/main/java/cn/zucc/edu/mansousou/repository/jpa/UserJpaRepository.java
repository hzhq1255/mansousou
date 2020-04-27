package cn.zucc.edu.mansousou.repository.jpa;

import cn.zucc.edu.mansousou.entity.jpa.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
     * 通过id查找用户
     * @param id
     * @return
     */
    User findByUserId(Integer id);

    /**
     * 分页获取所有用户
     * @param pageable
     * @return
     */
    @Query("select user from User user order by user.userId desc ")
    Page<User> selectAll(Pageable pageable);
}

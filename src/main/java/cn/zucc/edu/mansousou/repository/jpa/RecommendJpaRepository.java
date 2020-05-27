package cn.zucc.edu.mansousou.repository.jpa;

import cn.zucc.edu.mansousou.entity.jpa.Recommend;
import cn.zucc.edu.mansousou.entity.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/25 20:20
 * @desc:
 */
@Repository
public interface RecommendJpaRepository extends JpaRepository<Recommend,Integer> {

    List<Recommend> findAllByUser(User user);


}

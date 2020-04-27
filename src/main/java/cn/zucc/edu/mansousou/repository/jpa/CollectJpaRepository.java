package cn.zucc.edu.mansousou.repository.jpa;

import cn.zucc.edu.mansousou.entity.jpa.Collect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 20:24
 */
public interface CollectJpaRepository extends JpaRepository<Collect,Integer> {

    /**
     * 通过用户 id 得到收藏记录
     * @param userId
     * @param pageable
     * @return
     */
    @Query("select collect from Collect collect where collect.userId =:userId order by collect.createTime desc ")
    Page<Collect> selectAllByUserId(@Param("userId") Integer userId, Pageable pageable);

    /**
     * 通过用户 名字 得到收藏记录
     * @param userName
     * @param pageable
     * @return
     */
    @Query("select collect from Collect collect where collect.userId =:userName order by collect.createTime desc ")
    Page<Collect> selectAllByUserName(@Param("userName") String userName, Pageable pageable);
}

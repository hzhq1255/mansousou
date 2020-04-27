package cn.zucc.edu.mansousou.repository.jpa;

import cn.zucc.edu.mansousou.entity.jpa.Read;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 23:02
 */
public interface ReadJpaRepository extends JpaRepository<Read,Integer> {

    /**
     * 通过用户 id 获取所有的浏览记录
     * @param userId
     * @param pageable
     * @return
     */
    @Query("select read from Read read where read.userId =:userId order by read.updateTime desc ")
    Page<Read> selectAllByUserId(Integer userId, Pageable pageable);
}
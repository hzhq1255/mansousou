package cn.zucc.edu.mansousou.service.inter;

import cn.zucc.edu.mansousou.entity.jpa.Collect;
import org.springframework.data.domain.Page;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 20:30
 */
public interface CollectService {

    /**
     * 分页通过用户 名字 获取收藏并分页
     * @param username
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Collect> getAllByUserName(String username,Integer currentPage,Integer pageSize);

    /**
     * 分页通过用户 id 获取收藏并分页
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Collect> getAllByUserId(Integer userId, Integer currentPage, Integer pageSize);
}

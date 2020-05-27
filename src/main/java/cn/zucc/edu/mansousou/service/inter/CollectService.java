package cn.zucc.edu.mansousou.service.inter;

import cn.zucc.edu.mansousou.entity.jpa.Collect;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.data.domain.Page;

import java.util.List;

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


    /**
     * 获取所有的收藏
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Collect> getAll(Integer currentPage,Integer pageSize);

    /**
     * 通过 userId 和 comicId 获取 Collect
     * @param userId
     * @param comicId
     * @return
     */
    Result getCollectByUserIdAndComicId(Integer userId,String comicId);

    /**
     * 添加收藏
     * @param collect
     * @return
     */
    Result addCollect(Collect collect);

    /**
     * 修改收藏
     * @param collect
     * @return
     */
    Result updateCollect(Collect collect);

    /**
     * 删除收藏
     * @param collectId
     * @return
     */
    Result deleteCollectByCollectId(Integer collectId);

    /**
     * 删除用户所有收藏
     * @param userId
     * @return
     */
    Result deleteAllCollectByUserId(Integer userId);
}

package cn.zucc.edu.mansousou.service.inter;

import cn.zucc.edu.mansousou.entity.jpa.Read;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 23:13
 */
public interface ReadService {
    /**
     * 通过 userId 获取所有浏览记录
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Read> getAllReadByUserId(Integer userId, Integer currentPage,Integer pageSize);

    /**
     * 获取所有阅读信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Read> getAllRead(Integer currentPage,Integer pageSize);

    /**
     * 通过 Id 删除浏览记录
     * @param readId
     * @return
     */
    Result deleteReadByReadId(Integer readId);

    /**
     * 删除某一用下的所有浏览历史
     * @param userId
     * @return
     */
    Result deleteAllReadByUserId(Integer userId);

    /**
     * 修改浏览历史
     * @param read
     * @return
     */
    Result updateRead(Read read);

    /**
     * 批量修改记录
     * @param reads
     * @return
     */
    Result updateAllRead(List<Read> reads);
    /**
     * 增加阅读信息
     * @param read
     * @return
     */
    Result addRead(Read read);

    /**
     * 判断浏览记录是否存在
     * @param readId
     * @return
     */
    boolean isExist(Integer readId);
}

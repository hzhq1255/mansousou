package cn.zucc.edu.mansousou.service.inter;

import cn.zucc.edu.mansousou.entity.jpa.Read;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
}

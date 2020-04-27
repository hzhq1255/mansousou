package cn.zucc.edu.mansousou.service.impl;

import cn.zucc.edu.mansousou.entity.jpa.Read;
import cn.zucc.edu.mansousou.repository.jpa.ReadJpaRepository;
import cn.zucc.edu.mansousou.service.inter.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 23:17
 */
public class ReadServiceImpl implements ReadService {

    ReadJpaRepository readJpaRepository;

    @Autowired
    public void setReadJpaRepository(ReadJpaRepository readJpaRepository) {
        this.readJpaRepository = readJpaRepository;
    }

    @Override
    public Page<Read> getAllReadByUserId(Integer userId, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return readJpaRepository.selectAllByUserId(userId,pageable);
    }
}

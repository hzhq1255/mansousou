package cn.zucc.edu.mansousou.service.impl;

import cn.zucc.edu.mansousou.entity.jpa.Collect;
import cn.zucc.edu.mansousou.repository.jpa.CollectJpaRepository;
import cn.zucc.edu.mansousou.service.inter.CollectService;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 20:35
 */
@Service
public class CollectServiceImpl implements CollectService {

    CollectJpaRepository collectJpaRepository;

    @Autowired
    public void setCollectJpaRepository(CollectJpaRepository collectJpaRepository) {
        this.collectJpaRepository = collectJpaRepository;
    }

    @Override
    public Page<Collect> getAllByUserName(String username, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return collectJpaRepository.selectAllByUserName(username,pageable);
    }

    @Override
    public Page<Collect> getAllByUserId(Integer userId, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return collectJpaRepository.selectAllByUserId(userId,pageable);
    }

    @Override
    public Page<Collect> getAll(Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return collectJpaRepository.selectAll(pageable);
    }

    @Override
    public Result addCollect(Collect collect) {
        collectJpaRepository.save(collect);
        return Result.success(collect.getCollectId());
    }

    @Override
    public Result updateCollect(Collect collect) {
        collectJpaRepository.updateCollect(collect.getCollectId(),
                collect.getComicId(),collect.getTitle(),
                collect.getUrl(),collect.getUpdateTime());
        return Result.success(collect.getCollectId());
    }

    @Override
    public Result deleteCollectByCollectId(Integer collectId) {
        collectJpaRepository.deleteByCollectId(collectId);
        return Result.success(collectId);
    }

    @Override
    public Result deleteAllCollectByUserId(Integer userId) {
        List<Integer> collectIds = collectJpaRepository.deleteAllByUserId(userId).stream().map(e -> e.getCollectId()).collect(Collectors.toList());
        return Result.success(collectIds);
    }
}

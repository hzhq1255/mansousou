package cn.zucc.edu.mansousou.service.impl;

import cn.zucc.edu.mansousou.entity.jpa.Read;
import cn.zucc.edu.mansousou.repository.jpa.ReadJpaRepository;
import cn.zucc.edu.mansousou.service.inter.ReadService;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 23:17
 */
@Service
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

    @Override
    public Page<Read> getAllRead(Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return readJpaRepository.selectAll(pageable);
    }

    @Override
    public Result deleteReadByReadId(Integer readId) {
        readJpaRepository.deleteByReadId(readId);
        return Result.success(readId);
    }

    @Override
    public Result deleteAllReadByUserId(Integer userId) {
        List<Integer> readeIds = readJpaRepository.deleteAllByUserId(userId).stream().map(e -> e.getReadId()).collect(Collectors.toList());
        return Result.success(readeIds);
    }

    @Override
    public Result updateRead(Read read) {
        readJpaRepository.updateRead(read.getReadId(),read.getChapterId(),read.getChapter(),
                read.getUrl(),read.getUpdateTime());
        return Result.success(read.getReadId());
    }


    @Override
    public Result addRead(Read read) {
        readJpaRepository.save(read);
        return Result.success(read.getReadId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result recordRead(Read read) {
        Read needRecord = readJpaRepository.findByUserIdAndComicId(read.getUserId(),read.getComicId());
        if (needRecord != null){
            if (read.getChapter() == null && read.getChapterId() == null && read.getUrl() == null){
                readJpaRepository.updateReadTime(read.getUserId(),read.getComicId(),read.getUpdateTime());
            }else if (read.getChapter() != null && read.getChapterId() != null && read.getUrl() != null){
                readJpaRepository.updateRead(needRecord.getReadId(),read.getChapterId(),read.getChapter(),
                        read.getUrl(),read.getUpdateTime());
            }else {
                return Result.error("参数错误");
            }
            return Result.success("update "+needRecord.getUserId()+needRecord.getComicId());
        }else {
            if (read.getTitle() == null){
                return Result.success("请增加漫画名");
            }
            readJpaRepository.save(read);
            return Result.success("add "+needRecord.getUserId()+needRecord.getComicId());
        }
    }

    @Override
    public boolean isExist(Integer readId) {
        return readJpaRepository.findById(readId).equals(null) ? false:true;
    }
}

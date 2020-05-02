package cn.zucc.edu.mansousou.service.impl;

import cn.zucc.edu.mansousou.entity.es.ComicEs;
import cn.zucc.edu.mansousou.entity.jpa.HotSearch;
import cn.zucc.edu.mansousou.entity.jpa.Search;
import cn.zucc.edu.mansousou.repository.jpa.SearchJpaRepository;
import cn.zucc.edu.mansousou.service.inter.SearchService;
import cn.zucc.edu.mansousou.util.PageUtil;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.net.ssl.HostnameVerifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/4/29 13:55
 * @description:
 */
@Service
public class SearchServiceImpl implements SearchService {

    SearchJpaRepository searchJpaRepository;

    @Autowired
    public void setSearchJpaRepository(SearchJpaRepository searchJpaRepository) {
        this.searchJpaRepository = searchJpaRepository;
    }

    @Override
    public Page<HotSearch> getHotSearch(Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        List<HotSearch> hotSearchList = new ArrayList<>();
        List<List<Object>> objectList = searchJpaRepository.selectHotSearch();
        for (List<Object> o: objectList){
            HotSearch hotSearch = new HotSearch();
            hotSearch.setKeyword((String) o.get(0));
            hotSearch.setCount(((Long)o.get(1)).intValue());
            hotSearchList.add(hotSearch);
        }
        Page<HotSearch> hotSearchPage = PageUtil.listConvertToPage(hotSearchList,pageable);
        return hotSearchPage;
    }

    @Override
    public Page<Search> getAllSearch(Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return searchJpaRepository.selectAllSearch(pageable);
    }

    @Override
    public Page<Search> getAllSearchByUserId(Integer userId, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return searchJpaRepository.selectAllSearchByUserId(userId,pageable);
    }

    @Override
    public Result clearBySearchId(Integer searchId) {
        searchJpaRepository.clearBySearchId(searchId);
        return Result.success(searchId);
    }

    @Override
    public Result clearAllSearchByUserId(Integer userId) {
        searchJpaRepository.clearAllByUserId(userId);
        return Result.success(userId);
    }

    @Override
    public Page<ComicEs> advancedSearchComic(ComicEs comicEs) {
        return null;
    }

    @Override
    public Result addSearch(Search search) {
        searchJpaRepository.save(search);
        return Result.success("add a Search");
    }
}

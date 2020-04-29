package cn.zucc.edu.mansousou.controller;

import cn.zucc.edu.mansousou.entity.jpa.Search;
import cn.zucc.edu.mansousou.service.inter.SearchService;
import cn.zucc.edu.mansousou.util.PageUtil;
import cn.zucc.edu.mansousou.util.Result;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/4/29 14:03
 * @description:
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class SearchController {

    SearchService searchService;

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/getHotSearch",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getHotSearch(@RequestParam("currentPage") @NotNull Integer currentPage,
                               @RequestParam("pageSize") @NotNull Integer pageSize){
        if (currentPage <= 0 || pageSize <= 0){
            return Result.error("参数错误");
        }
        pageSize = pageSize <= PageUtil.DEFAULT_PAGE_SIZE ? PageUtil.DEFAULT_PAGE_SIZE: pageSize;
        Object data = PageUtil.getPageData(searchService.getHotSearch(currentPage-1, pageSize));
        return Result.success(data);
    }

    @RequestMapping(value = "/getAllSearch",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAllSearch(@RequestParam("currentPage") @NotNull Integer currentPage,
                               @RequestParam("pageSize") @NotNull Integer pageSize){
        if (currentPage <= 0 || pageSize <= 0){
            return Result.error("参数错误");
        }
        pageSize = pageSize <= PageUtil.DEFAULT_PAGE_SIZE ? PageUtil.DEFAULT_PAGE_SIZE: pageSize;
        Object data = PageUtil.getPageData(searchService.getAllSearch(currentPage-1, pageSize));
        return Result.success(data);
    }

    @RequestMapping(value = "/getAllSearchByUserId",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAllSearchByUserId(@RequestParam("userId") @NotNull Integer userId,
                                       @RequestParam("currentPage") @NotNull Integer currentPage,
                                       @RequestParam("pageSize") @NotNull Integer pageSize){
        if (currentPage <= 0 || pageSize <= 0){
            return Result.error("参数错误");
        }
        pageSize = pageSize <= PageUtil.DEFAULT_PAGE_SIZE ? PageUtil.DEFAULT_PAGE_SIZE: pageSize;
        Object data = PageUtil.getPageData(searchService.getAllSearchByUserId(userId,currentPage-1, pageSize));
        return Result.success(data);
    }


}

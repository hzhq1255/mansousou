package cn.zucc.edu.mansousou.controller;


import cn.zucc.edu.mansousou.entity.es.ComicEs;
import cn.zucc.edu.mansousou.service.inter.ComicService;
import cn.zucc.edu.mansousou.util.PageUtil;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/4/27 16:46
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ComicController {

    ComicService comicService;

    @Autowired
    public void setComicService(ComicService comicService) {
        this.comicService = comicService;
    }

    @RequestMapping(value = "/searchComic",method = {RequestMethod.GET,RequestMethod.POST})
    public Result searchComic(@RequestParam("keyword")  String keyword,
                              @RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize) throws IOException {
        if (keyword.isEmpty() || keyword == null ){
            return Result.build(400,"查询关键字不能为空");
        }else if (currentPage == null || currentPage == 0) {
            return Result.build(400, "当前页面参数不能为空");
        }else if (pageSize == null || pageSize == 0 ){
            return Result.build(400,"页面尺寸不能为空");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE ){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        HashMap<String,Object> data = PageUtil.getPageData(comicService.searchComic(keyword,currentPage-1,pageSize));
        stopWatch.stop();
        data.put("searchTime",stopWatch.getTotalTimeMillis());
        return Result.success(data);
    }

    @RequestMapping(value = "/advancedSearch",method = {RequestMethod.POST,RequestMethod.GET})
    public Result advancedSearch(@RequestParam(value = "title",required = false) String title,
                                 @RequestParam(value = "author",required = false) String author,
                                 @RequestParam(value = "status",required = false) String status,
                                 @RequestParam(value = "desc",required = false) String desc,
                                 @RequestParam(value = "genre",required = false) String genre,
                                 @RequestParam(value = "isExactMatch",required = false) Integer isExactMatch,
                                 @RequestParam(value = "currentPage") @NotNull Integer currentPage,
                                 @RequestParam(value = "pageSize") @NotNull Integer pageSize){
        if (currentPage <= 0){
            return Result.build(400, "当前页面参数不能为空");
        }
        if (pageSize <= 0){
            return Result.build(400,"页面尺寸不能为空");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        if (title == null || "".equals(title)){
            if (author == null || "".equals(title)){
                if (status == null || "".equals(status)){
                    if (desc == null || "".equals(desc)){
                        if (genre == null || "".equals(genre)){
                            return Result.error("请至少输入一个搜索参数");
                        }
                    }
                }
            }
        }
        ComicEs comicEs = new ComicEs();
        comicEs.setTitle(title);
        comicEs.setAuthor(author);
        comicEs.setDesc(desc);
        comicEs.setGenre(genre);
        comicEs.setStatus(status);
        Object data = null;
        try{
           data = PageUtil.getPageData(comicService.advancedSearch(comicEs,isExactMatch,currentPage-1,pageSize));
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return Result.error("请至少输入一个搜索参数");
        }

        return Result.success(data);
    }

    @RequestMapping(value = "/getAllComic",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAllComics(@RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize){
        if (currentPage == null || currentPage == 0){
            return Result.build(400, "当前页面参数不能为空");
        }
        if (pageSize == null || pageSize == 0){
            return Result.build(400,"页面尺寸不能为空");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        Object data = PageUtil.getPageData(comicService.getAllComics(currentPage-1,pageSize));
        return Result.success(data);
    }

    @RequestMapping(value = "/getComicByComicId",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getComicByComicId(@RequestParam("comicId") @NotNull String comicId){
        return Result.success(comicService.getComicByComicId(comicId));
    }
}

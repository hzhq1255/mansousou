package cn.zucc.edu.mansousou.controller;

import cn.zucc.edu.mansousou.entity.jpa.Collect;
import cn.zucc.edu.mansousou.entity.jpa.User;
import cn.zucc.edu.mansousou.service.inter.CollectService;
import cn.zucc.edu.mansousou.util.PageUtil;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/4/27 21:32
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class CollectController {

    CollectService collectService;

    @Autowired
    public void setCollectService(CollectService collectService) {
        this.collectService = collectService;
    }

    @RequestMapping(value = "/getAllCollectByUserId",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAllCollectByUserId(@RequestParam("userId") @NotNull Integer userId,
                                        @RequestParam("currentPage") @NotNull Integer currentPage,
                                        @RequestParam("pageSize") @NotNull Integer pageSize){
        if (userId <= 0 ){
            return Result.build(400,"用户名不能为空");
        }else if (currentPage <= 0) {
            return Result.build(400, "当前页面参数不能为空");
        }else if ( pageSize <= 0 ){
            return Result.build(400,"页面尺寸不能为空");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE ){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        Object data = PageUtil.getPageData(collectService.getAllByUserId(userId, currentPage-1, pageSize));
        return Result.success(data);
    }

    @RequestMapping(value = "/getAllCollectByUserName",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAllByUserName(@RequestParam("userName") @NotNull String userName,
                                   @RequestParam("currentPage") @NotNull Integer currentPage,
                                   @RequestParam("pageSize") @NotNull Integer pageSize){
        if (userName.isEmpty() ){
            return Result.build(400,"用户名不能为空");
        }else if ( currentPage <= 0) {
            return Result.build(400, "当前页面参数不能为空");
        }else if ( pageSize <= 0 ){
            return Result.build(400,"页面尺寸不能为空");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE ){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        Object data = PageUtil.getPageData(collectService.getAllByUserName(userName, currentPage-1, pageSize));
        return Result.success(data);
    }

    @RequestMapping(value = "/getAllCollect",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAllCollect(@RequestParam("currentPage") @NotNull Integer currentPage,
                         @RequestParam("pageSize") @NotNull Integer pageSize){
        if (currentPage <= 0 || pageSize <= 0){
            return Result.error("参数错误");
        }
        Object data = PageUtil.getPageData(collectService.getAll(currentPage-1, pageSize));
        return Result.success(data);
    }

    @RequestMapping(value = "/getCollectByUserIdAndComicId",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getCollectByUserIdAndComicId(@RequestParam("userId") @NotNull Integer userId,
                                               @RequestParam("comicId") @NotNull String comicId){
        return collectService.getCollectByUserIdAndComicId(userId, comicId);
    }


    @RequestMapping(value = "/isComicCollectedByUser",method = {RequestMethod.GET,RequestMethod.POST})
    public Result isComicCollectedByUser(@RequestParam("userId") @NotNull Integer userId,
                                         @RequestParam("comicId") @NotNull String comicId){
        Result result = collectService.getCollectByUserIdAndComicId(userId,comicId);
        if (result.getData() == null){
            result.setData("no");
        }else {
            result.setData("yes");
        }
        return result;
    }

    @RequestMapping(value = "/addCollect",method = {RequestMethod.POST})
    public Result addCollect(@RequestParam("userId") @NotNull Integer userId,
                             @RequestParam("comicId") @NotNull String comicId,
                             @RequestParam("title") @NotNull String title,
                             @RequestParam("url") @NotNull String url,
                             @RequestParam("pics") @NotNull String pics){
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setComicId(comicId);
        collect.setTitle(title);
        collect.setUrl(url);
        collect.setPics(pics);
        collect.setCreateTime(new Date());
        collect.setUpdateTime(new Date());
        return collectService.addCollect(collect);
    }

    @RequestMapping(value = "/updateCollect",method = {RequestMethod.POST})
    public Result updateCollect(@RequestParam("collectId") @NotNull Integer collectId,
                                @RequestParam("comicId") @NotNull String comicId,
                                @RequestParam("title") @NotNull String title,
                                @RequestParam("url") @NotNull String url,
                                @RequestParam("pics") @NotNull String pics){
        Collect collect = new Collect();
        collect.setCollectId(collectId);
        collect.setComicId(comicId);
        collect.setTitle(title);
        collect.setUrl(url);
        collect.setPics(pics);
        collect.setUpdateTime(new Date());
        return collectService.updateCollect(collect);
    }

    @RequestMapping(value = "/deleteCollectByCollectId",method = {RequestMethod.POST})
    public Result deleteCollectByCollectId(@RequestParam("collectId") @NotNull Integer collectId){
        return collectService.deleteCollectByCollectId(collectId);
    }

    @RequestMapping(value = "/deleteAllCollectByUserId",method = {RequestMethod.POST})
    public Result deleteAllCollectByUserId(@RequestParam("userId") @NotNull Integer userId){
        return collectService.deleteAllCollectByUserId(userId);
    }
}

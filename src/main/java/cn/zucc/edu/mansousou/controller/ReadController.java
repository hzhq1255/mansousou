package cn.zucc.edu.mansousou.controller;

import cn.zucc.edu.mansousou.entity.jpa.Read;
import cn.zucc.edu.mansousou.service.inter.ReadService;
import cn.zucc.edu.mansousou.util.PageUtil;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/4/28 11:04
 * @description:
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReadController {


    ReadService readService;

    @Autowired
    public void setReadService(ReadService readService) {
        this.readService = readService;
    }

    @RequestMapping(value = "/getAllReadByUserId",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAllReadByUserId(@RequestParam("userId") @NotNull Integer userId,
                                     @RequestParam("currentPage") @NotNull Integer currentPage,
                                     @RequestParam("pageSize") @NotNull Integer pageSize){
        if (userId <= 0 || currentPage <= 0 || pageSize <= 0){
            return Result.error("参数错误");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        Object data = PageUtil.getPageData(readService.getAllReadByUserId(userId, currentPage-1, pageSize));
        return Result.success(data);
    }

    @RequestMapping(value = "/getAllRead",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAll(@RequestParam("currentPage") @NotNull Integer currentPage,
                         @RequestParam("pageSize") @NotNull Integer pageSize){
        if (currentPage <= 0 || pageSize <= 0 ){
            return Result.error("参数错误");
        }
        pageSize = pageSize < PageUtil.DEFAULT_PAGE_SIZE ? PageUtil.DEFAULT_PAGE_SIZE : pageSize;
        Object data = PageUtil.getPageData(readService.getAllRead(currentPage-1, pageSize));
        return Result.success(data);
    }

    @RequestMapping(value = "/deleteReadByReadId",method = {RequestMethod.POST})
    public Result deleteReadByReadId(@RequestParam("readId") @NotNull Integer readId){
        return readService.deleteReadByReadId(readId);
    }

    @RequestMapping(value = "/deleteAllReadByUserId",method = {RequestMethod.POST})
    public Result deleteAllReadByUserId(@RequestParam("userId") @NotNull Integer userId){
        return readService.deleteAllReadByUserId(userId);
    }

    @RequestMapping(value = "/recordRead",method = {RequestMethod.POST})
    public Result recordRead(@RequestParam("userId") @NotNull Integer userId,
                             @RequestParam("comicId") @NotNull String comicId,
                             @RequestParam(value = "title",required = false) String title,
                             @RequestParam(value = "chapterId",required = false) String chapterId,
                             @RequestParam(value = "chapter",required = false) String chapter,
                             @RequestParam(value = "url",required = false) String url,
                             @RequestParam(value = "pics",required = false) String pics){
        Read read = new Read();
        read.setUserId(userId);
        read.setComicId(comicId);
        read.setChapterId(chapterId);
        read.setTitle(title);
        read.setChapter(chapter);
        read.setUrl(url);
        read.setPics(pics);
        read.setCreateTime(new Date());
        read.setUpdateTime(new Date());
        return readService.recordRead(read);
    }

    @RequestMapping(value = "/updateRead",method = {RequestMethod.POST})
    public Result updateRead(@RequestParam(value = "readId") @NotNull Integer readId,
                             @RequestParam(value = "chapterId") @NotNull String chapterId,
                             @RequestParam(value = "chapter") @NotNull String chapter,
                             @RequestParam(value = "url") @NotNull String url){
        Read read = new Read();
        read.setReadId(readId);
        read.setChapterId(chapterId);
        read.setChapter(chapter);
        read.setUrl(url);
        read.setUpdateTime(new Date());
        return readService.updateRead(read);
    }

    @RequestMapping(value = "/addRead",method = {RequestMethod.POST})
    public Result addRead(@RequestParam(value = "userId",required = true) @NotNull Integer userId,
                          @RequestParam(value = "comicId",required = true) @NotNull String comicId,
                          @RequestParam(value = "pics",required = true) String pics ,
                          @RequestParam(value = "title",required = true) @NotNull String title,
                          @RequestParam(value = "chapterId",required = false) String chapterId,
                          @RequestParam(value = "chapter",required = false) String chapter,
                          @RequestParam(value = "url",required = false) String url){
        Read read = new Read();
        read.setUserId(userId);
        read.setComicId(comicId);
        read.setTitle(title);
        read.setChapterId(chapterId);
        read.setChapter(chapter);
        read.setUrl(url);
        read.setPics(pics);
        read.setCreateTime(new Date());
        read.setUpdateTime(new Date());
        return readService.addRead(read);
    }


}

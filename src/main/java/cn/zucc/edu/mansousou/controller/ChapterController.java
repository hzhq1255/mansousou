package cn.zucc.edu.mansousou.controller;

import cn.zucc.edu.mansousou.service.inter.ChapterService;
import cn.zucc.edu.mansousou.util.PageUtil;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/4/27 23:55
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ChapterController {
    ChapterService chapterService;

    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @RequestMapping(value = "/getAllChapterByComicId",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAllChapterByComicId(@RequestParam("comicId") @NotNull String comicId,
                                            @RequestParam("currentPage") @NotNull Integer currentPage,
                                            @RequestParam("pageSize") @NotNull Integer pageSize){
        if (comicId.isEmpty() || currentPage == 0  || pageSize == 0 ){
            return Result.error("无效参数");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        Object data = PageUtil.getPageData(chapterService.getAllChapterByComicId(comicId, currentPage-1, pageSize));
        return Result.success(data);
    }

    @RequestMapping(value = "/getAllChapter",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAllChapter(@RequestParam("currentPage") @NotNull Integer currentPage,
                                @RequestParam("pageSize") @NotNull Integer pageSize){
        if (currentPage == 0 || pageSize == 0 ){
            return Result.error("无效参数");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        Object data = PageUtil.getPageData(chapterService.getAllChapter(currentPage-1, pageSize));
        return Result.success(data);
    }

}

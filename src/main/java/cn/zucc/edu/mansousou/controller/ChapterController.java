package cn.zucc.edu.mansousou.controller;

import cn.zucc.edu.mansousou.service.inter.ChapterService;
import cn.zucc.edu.mansousou.util.PageUtil;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 23:55
 */
@RestController
@RequestMapping("/api")
public class ChapterController {
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    ChapterService chapterService;

    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @RequestMapping(value = "/searchAllChapterByComicId",method = {RequestMethod.GET,RequestMethod.POST})
    public Result searchAllChapterByComicId(@RequestParam("comicId") @NotNull String comicId,
                                            @RequestParam("currentPage") @NotNull Integer currentPage,
                                            @RequestParam("pageSize") @NotNull Integer pageSize){
        if (comicId.isEmpty() || comicId == null ||
                currentPage == 0 || currentPage == null ||
                pageSize == 0 || pageSize == null){
            return Result.error("无效参数");
        }
        if (pageSize < DEFAULT_PAGE_SIZE){
            pageSize = DEFAULT_PAGE_SIZE;
        }
        Object data = PageUtil.getPageData(chapterService.searchAllChapterByComicId(comicId, currentPage-1, pageSize));
        return Result.success(data);
    }

    @RequestMapping(value = "/getAllChapter",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAllChapter(@RequestParam("currentPage") @NotNull Integer currentPage,
                                @RequestParam("pageSize") @NotNull Integer pageSize){
        if (currentPage == 0 || currentPage == null
                || pageSize == 0 || pageSize == null){
            return Result.error("无效参数");
        }
        if (pageSize < DEFAULT_PAGE_SIZE){
            pageSize = DEFAULT_PAGE_SIZE;
        }
        Object data = PageUtil.getPageData(chapterService.getAllChapter(currentPage-1, pageSize));
        return Result.success(data);
    }

}

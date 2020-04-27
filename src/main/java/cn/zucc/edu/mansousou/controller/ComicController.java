package cn.zucc.edu.mansousou.controller;

import cn.zucc.edu.mansousou.entity.es.ComicEs;
import cn.zucc.edu.mansousou.entity.jpa.Comic;
import cn.zucc.edu.mansousou.service.impl.ComicServiceImpl;
import cn.zucc.edu.mansousou.service.inter.ComicService;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 16:46
 */
@RestController
@RequestMapping("/api")
public class ComicController {

    ComicService comicService;

    @Autowired
    public void setComicService(ComicService comicService) {
        this.comicService = comicService;
    }

    @RequestMapping(value = "/searchComic",method = {RequestMethod.GET,RequestMethod.POST})
    public Result searchComic(@RequestParam("keyword") String keyword,
                              @RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize) throws IOException {
        if (keyword.isEmpty() || keyword.equalsIgnoreCase("")){
            return Result.build(400,"查询关键字不能为空");
        }else if (currentPage == null || currentPage == 0) {
            return Result.build(400, "当前页面参数不能为空");
        }else if (pageSize == null || pageSize == 0 ){
            return Result.build(400,"页面尺寸不能为空");
        }
        if (pageSize < 10 ){
            pageSize = 10;
        }
        HashMap<String,Object> hashMap = new HashMap<>();
        Page<ComicEs> comics = comicService.searchComic(keyword,currentPage-1,pageSize);
        hashMap.put("currentPage",currentPage);
        hashMap.put("pageSize",pageSize);
        hashMap.put("totalElements",comics.getTotalElements());
        hashMap.put("totalPages",comics.getTotalPages());
        hashMap.put("content",comics.getContent());
        return Result.success(hashMap);
    }

    @RequestMapping(value = "/getAllComics",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAllComics(@RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize){
        if (currentPage == null || currentPage == 0){
            return Result.build(400, "当前页面参数不能为空");
        }
        if (pageSize == null || pageSize == 0){
            return Result.build(400,"页面尺寸不能为空");
        }
        if (pageSize < 10){
            pageSize = 10;
        }
        HashMap<String,Object> hashMap = new HashMap<>();
        Page<Comic> comics = comicService.getAllComics(currentPage-1,pageSize);
        hashMap.put("currentPage",currentPage);
        hashMap.put("pageSize",pageSize);
        hashMap.put("totalComics",comics.getTotalElements());
        hashMap.put("totalPages",comics.getTotalPages());
        hashMap.put("content",comics.getContent());
        return Result.success(hashMap);
    }
}

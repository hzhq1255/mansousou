package cn.zucc.edu.mansousou.controller;

import cn.zucc.edu.mansousou.entity.jpa.Read;
import cn.zucc.edu.mansousou.service.inter.ReadService;
import cn.zucc.edu.mansousou.util.PageUtil;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/4/28 11:04
 * @description:
 */
@RestController
@RequestMapping("/api/")
@CrossOrigin
public class ReadController {


    ReadService readService;

    @Autowired
    public void setReadService(ReadService readService) {
        this.readService = readService;
    }

    @RequestMapping(value = "/getAllReadByUserId",method = {RequestMethod.GET})
    public Result getAllReadByUserId(@RequestParam("userId") @NotNull Integer userId,
                                     @RequestParam("currentPage") @NotNull Integer currentPage,
                                     @RequestParam("pageSize") @NotNull Integer pageSize){
        if (userId <= 0 || currentPage <= 0 || pageSize <= 0){
            return Result.error("参数错误");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        Object data = readService.getAllReadByUserId(userId, currentPage, pageSize);
        return Result.success(data);
    }

    @RequestMapping(value = "/getAllRead",method = {RequestMethod.GET})
    public Result getAll(@RequestParam("currentPage") @NotNull Integer currentPage,
                         @RequestParam("pageSize") @NotNull Integer pageSize){
        if (currentPage <= 0 || pageSize <= 0 ){
            return Result.error("参数错误");
        }
        pageSize = pageSize < PageUtil.DEFAULT_PAGE_SIZE ? PageUtil.DEFAULT_PAGE_SIZE : pageSize;
        Object data = readService.getAllRead(currentPage, pageSize);
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

    @RequestMapping(value = "/updateRead",method = {RequestMethod.POST})
    public Result updateRead(@RequestBody @NotNull HashMap<String,Object> hashMap){
        return Result.success();

    }



}

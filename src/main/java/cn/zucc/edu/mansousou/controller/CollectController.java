package cn.zucc.edu.mansousou.controller;

import cn.zucc.edu.mansousou.entity.jpa.Collect;
import cn.zucc.edu.mansousou.service.inter.CollectService;
import cn.zucc.edu.mansousou.util.PageUtil;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/4/27 21:32
 */
@RestController
@RequestMapping("/api")
public class CollectController {

    private static final Integer DEFAULT_PAGE_SIZE = 10;
    CollectService collectService;

    @Autowired
    public void setCollectService(CollectService collectService) {
        this.collectService = collectService;
    }

    @RequestMapping(value = "/getAllCollectByUserId",method = {RequestMethod.GET})
    public Result getAllCollectByUserId(@RequestParam("userId") @NotNull Integer userId,
                                        @RequestParam("currentPage") @NotNull Integer currentPage,
                                        @RequestParam("pageSize") @NotNull Integer pageSize){
        if (userId <= 0 ){
            return Result.build(400,"用户名不能为空");
        }else if (currentPage == 0) {
            return Result.build(400, "当前页面参数不能为空");
        }else if ( pageSize == 0 ){
            return Result.build(400,"页面尺寸不能为空");
        }
        if (pageSize < DEFAULT_PAGE_SIZE ){
            pageSize = DEFAULT_PAGE_SIZE;
        }
        Object data = PageUtil.getPageData(collectService.getAllByUserId(userId, currentPage-1, pageSize));
        return Result.success(data);
    }

    @RequestMapping(value = "/getAllCollectByUserName",method = {RequestMethod.GET})
    public Result getAllByUserName(@RequestParam("userName") @NotNull String userName,
                                   @RequestParam("currentPage") @NotNull Integer currentPage,
                                   @RequestParam("pageSize") @NotNull Integer pageSize){
        if (userName.isEmpty() ){
            return Result.build(400,"用户名不能为空");
        }else if ( currentPage == 0) {
            return Result.build(400, "当前页面参数不能为空");
        }else if ( pageSize == 0 ){
            return Result.build(400,"页面尺寸不能为空");
        }
        if (pageSize < DEFAULT_PAGE_SIZE ){
            pageSize = DEFAULT_PAGE_SIZE;
        }
        Object data = PageUtil.getPageData(collectService.getAllByUserName(userName, currentPage-1, pageSize));
        return Result.success(data);
    }
}

package cn.zucc.edu.mansousou.controller;

import cn.zucc.edu.mansousou.entity.jpa.Comic;
import cn.zucc.edu.mansousou.entity.jpa.Recommend;
import cn.zucc.edu.mansousou.service.inter.RecommendService;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/27 11:19
 * @desc:
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    @RequestMapping(value = "/getRecommendByUser",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getRecommendByUser(@RequestParam(value = "userId") @NotNull Integer userId){
        List<Recommend> recommends = recommendService.getRecommendByUser(userId);
        return Result.success(recommends);
    }

    @RequestMapping(value = "/getRandRecommend",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getRandRecommend(@RequestParam(value = "limit") @NotNull Integer limit){
        if (limit <= 0){
            return Result.error("limit 不能小于等于 0");
        }
        List<Comic> comics = recommendService.getRandComic(limit);
        return Result.success(comics);
    }

    @RequestMapping(value = "/saveRecommendAllUser",method = {RequestMethod.POST})
    public Result saveRecommendAllUser(){
        recommendService.saveRecommendAllUser();
        return Result.success("保存成功");
    }
}

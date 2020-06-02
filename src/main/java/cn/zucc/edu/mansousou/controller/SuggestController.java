package cn.zucc.edu.mansousou.controller;

import cn.zucc.edu.mansousou.entity.es.SuggestText;
import cn.zucc.edu.mansousou.service.inter.SuggestService;
import cn.zucc.edu.mansousou.util.Result;
import org.elasticsearch.search.suggest.Suggest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/24 23:56
 * @desc:
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class SuggestController {

    @Autowired
    SuggestService suggestService;

    @RequestMapping(value = "/getSuggest",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getSuggest(@RequestParam(value = "keyword") @NotNull String keyword){
        if (keyword == null || "".equals(keyword)){
            return Result.error("搜索关键字不能为空");
        }
        List<SuggestText> result = suggestService.getSuggest(keyword);
        return Result.success(result);
    }
}

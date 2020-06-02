package cn.zucc.edu.mansousou.service.inter;

import cn.zucc.edu.mansousou.entity.es.SuggestEs;
import cn.zucc.edu.mansousou.entity.es.SuggestText;

import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/23 21:28
 * @desc:
 */
public interface SuggestService {

    /**
     * 获取搜索建议
     * @param keyword 搜索关键字
     * @return 返回搜索建议
     */
    List<SuggestText> getSuggest(String keyword);


}

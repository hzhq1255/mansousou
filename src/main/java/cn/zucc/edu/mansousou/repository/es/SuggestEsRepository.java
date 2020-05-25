package cn.zucc.edu.mansousou.repository.es;

import cn.zucc.edu.mansousou.entity.es.SuggestEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/23 21:21
 * @desc:
 */
@Repository
public interface SuggestEsRepository extends ElasticsearchRepository<SuggestEs,String> {
}

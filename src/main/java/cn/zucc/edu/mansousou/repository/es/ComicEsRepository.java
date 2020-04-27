package cn.zucc.edu.mansousou.repository.es;

import cn.zucc.edu.mansousou.entity.es.ComicEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 16:43
 */
public interface ComicEsRepository extends ElasticsearchRepository<ComicEs,String> {

}

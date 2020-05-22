package cn.zucc.edu.mansousou.entity.es;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.SneakyThrows;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/22 0:48
 * @desc:
 */
public class HighlightResultMapper implements SearchResultMapper {
    @SneakyThrows
    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
        long totalHits = response.getHits().getTotalHits();
        List<T> list = new ArrayList<>();
        SearchHits hits = response.getHits();
        if (hits.getHits().length > 0){
            for (SearchHit searchHit : hits){
                Map<String, HighlightField> highlightFieldMap = searchHit.getHighlightFields();
                ObjectMapper mapper = new ObjectMapper();
                T item = mapper.readValue(searchHit.getSourceAsString(),clazz);
                Field[] fields = clazz.getDeclaredFields();
                for (Field field: fields){
                    field.setAccessible(true);
                    if (highlightFieldMap.containsKey(field.getName())){
                        try {
                            field.set(item,highlightFieldMap.get(field.getName()).fragments()[0].toString());
                        }catch (IllegalArgumentException | IllegalAccessException e){
                            e.printStackTrace();
                        }
                    }
                }
                list.add(item);
            }
        }
        return new AggregatedPageImpl<>(list,pageable,totalHits);
    }

    @Override
    public <T> T mapSearchHit(SearchHit searchHit, Class<T> type) {
        return null;
    }
}

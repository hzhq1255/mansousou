package cn.zucc.edu.mansousou.util;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 21:57
 */
@Data
@Component
public class PageUtil extends HashMap<String,Object>{

    public static final Integer DEFAULT_PAGE_SIZE = 10;
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalElements;
    private Integer totalPages;
    private Object content;

    public static HashMap<String, Object> getPageData(Page<?> page){
        HashMap<String,Object> hashMap = new HashMap<>(5);
        hashMap.put("currentPage",page.getPageable().getPageNumber()+1);
        hashMap.put("pageSize",page.getPageable().getPageSize());
        hashMap.put("totalElements",page.getTotalElements());
        hashMap.put("totalPages",page.getTotalPages());
        hashMap.put("content",page.getContent());
        return hashMap;
    }

    public static  <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : ( start + pageable.getPageSize());
        return new PageImpl<T>(list.subList(start, end), pageable, list.size());
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}

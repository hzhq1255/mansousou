package cn.zucc.edu.mansousou.util;

import cn.zucc.edu.mansousou.entity.jpa.Comic;
import cn.zucc.edu.mansousou.repository.jpa.CollectJpaRepository;
import cn.zucc.edu.mansousou.repository.jpa.ComicJpaRepository;
import cn.zucc.edu.mansousou.repository.jpa.ReadJpaRepository;
import cn.zucc.edu.mansousou.repository.jpa.RecommendJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/25 20:27
 * @desc:
 */
public class DataUtil {

    @Autowired
    public static ComicJpaRepository comicJpaRepository;

    @Autowired
    public static RecommendJpaRepository recommendJpaRepository;

    @Autowired
    public static CollectJpaRepository collectJpaRepository;

    @Autowired
    public static ReadJpaRepository readJpaRepository;

    public static String[] tags =
            new String[]{"爆笑","热血","冒险","科幻","魔幻","玄幻",
            "校园","推理","萌系","穿越","后宫","都市","恋爱","武侠",
            "格斗","战争","历史","剧情","同人","竞技","励志","治愈",
            "机甲","纯爱","美食","血腥","僵尸","恶搞","虐心","生活",
            "动作","惊险","唯美","震撼","复仇","侦探","其他","脑洞",
            "奇幻","宫斗","运动","青春","穿越","灵异","古风","权谋",
            "节操","明星","暗黑","社会","浪漫","连载中","已完结"};
    public Map<String,int[]> vectorMap = new HashMap<String, int[]>();

    public static void main(String[] args){
        List<Comic> comics = comicJpaRepository.findAll();
        List<Comic> collectComics = comicJpaRepository.findAllByUserIdAtCollect(1);
        List<Comic> readComics = comicJpaRepository.findAllByUserIdAtRead(1);

        List<String> comicTags = new ArrayList<>();
        List<String> userTags = new ArrayList<>();
        //List<Integer> collectIds = collectJpaRepository.deleteAllByUserId(userId).stream().map(e -> e.getCollectId()).collect(Collectors.toList());
        comicTags = comicJpaRepository.findAll().stream().map(e -> e.getGenre()).collect(Collectors.toList());
    }
}

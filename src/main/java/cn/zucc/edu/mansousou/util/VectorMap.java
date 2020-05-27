package cn.zucc.edu.mansousou.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/25 23:00
 * @desc:
 */
public class VectorMap {

    public Map<String,int[]> vectorMap = new HashMap<String, int[]>();
    public int[] tempArray = null;

    public VectorMap(String source, String target){
        for (String tag : source.split(",")){
            if (vectorMap.containsKey(tag)){
                vectorMap.get(tag)[0]++;
            }else {
                tempArray = new int[2];
                tempArray[0] = 1;
                tempArray[1] = 0;
                vectorMap.put(tag,tempArray);
            }
        }

        for (String tag : target.split(",")){
            if (vectorMap.containsKey(tag)){
                vectorMap.get(tag)[1]++;
            }else {
                tempArray = new int[2];
                tempArray[0] = 0;
                tempArray[1] = 1;
                vectorMap.put(tag,tempArray);
            }
        }
    }


    /**
     * 求平方和
     * @param paramMap
     * @return
     */
    public double squares(Map<String,int[]> paramMap){
        double result1 = 0;
        double result2 = 0;
        Set<String> keySet = paramMap.keySet();
        for (String tag : keySet){
            int temp[] = paramMap.get(tag);
            result1 += (temp[0] * temp[0]);
            result2 += (temp[1] * temp[1]);
        }
        return result1 * result2;
    }

    /**
     * 点乘
     * @param paramMap 向量
     * @return
     */
    public double pointMulti(Map<String,int[]> paramMap){
        double result = 0;
        Set<String> keySet = paramMap.keySet();
        for (String tag : keySet){
            int temp[] = paramMap.get(tag);
            result += (temp[0] * temp[1]);
        }
        return result;
    }

    /**
     * 求距离
     * @param paramMap
     * @return
     */
    public double sqrtMulti(Map<String,int[]> paramMap){
        double result = 0;
        result = squares(paramMap);
        result = Math.sqrt(result);
        return result;
    }

    public double sim(){
        double result = 0;
        result = pointMulti(vectorMap) / sqrtMulti(vectorMap);
        return result;
    }

    public static void main(String[] args) {
        String str1 = "爆笑,都市,恋爱,冒险";
        String str2 = "爆笑,都市,恋爱,爆笑,冒险,古风";
        String str3 = "都市,都市,都市,草";
        VectorMap vectorMap1 = new VectorMap(str1,str2);
        VectorMap vectorMap2 = new VectorMap(str1,str3);
        System.out.println(vectorMap1.sim());
        System.out.println(vectorMap2.sim());
    }
}


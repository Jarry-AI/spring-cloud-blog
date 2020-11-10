package com.example.easyexcel;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2020-09-19
 */
public class Test {

    @org.junit.jupiter.api.Test
    public void test1(){

        System.out.println(System.getProperties().getProperty("user.home"));
//        List<Integer> list = Arrays.asList(11,22,3,4,60,7);
//        System.out.println(sort2(list));

        


    }




    // 冒泡排序
    public List<Integer> sort1(List<Integer> list) {

//        Integer change;
//        for(int i=0;i<list.size();i++){
//            for(int j=0;j<list.size()-i-1;j++){
//                if (list.get(j) > list.get(j+1)) {
//                    change = list.get(j);
//                    list.set(j,list.get(j+1));
//                    list.set(j+1,change);
//                }
//            }
//
//        }
        //        return list;
        return list.stream().sorted().collect(Collectors.toList());

    }

    // 选择排序
    public List<Integer> sort2(List<Integer> list){

        for (int i=0;i<list.size()-1;i++) {
            int min = i;
            for(int j=i+1;j<list.size();j++) {
                if (list.get(min) >list.get(j) ) {
                    min=j;
                }
            }
            Integer temp = list.get(min);
            list.set(min,list.get(i));
            list.set(i,temp);
        }
        return list;
    }

    // 插入排序
    public static int[] sort3(int[] array){
        if (array.length == 0) {
            return array;
        }
        // 定义当前要排的对象
        int current;
        // 遍历数组,已排的所有数据,不包含最后一个
        for (int i=0; i<array.length-1; i++){
            // 第二个开始
            current = array[i+1];
            // 前一个索引
            int preIndex = i;
            // 前一个已排数据大于当前
            while (preIndex >= 0 && current < array[preIndex]){
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            // 换位
            array[preIndex + 1] = current;
        }
        return array;
    }


}

package com.example.myjava8.page4;

import com.example.myjava8.entity.Dish;
import com.example.myjava8.entity.Myentity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class StreamController {
    //进行筛选,卡路里>400并且故居卡路里排序
    @RequestMapping("nihao")
    public List<Dish> nihao() {
        List<Dish> dishList = Myentity.menu;
        //1.首先变成stream
        List<Dish> dishList2 = dishList.stream().
                //筛选卡路里大于400的
                        filter(d -> d.getCalories() > 400).//排序
                sorted(Comparator.comparing(Dish::getName))
                //变为list
                .collect(Collectors.toList());
        return dishList2;
    }
    @RequestMapping("selectvegetarian")
    public List<Dish> selectvegetarian() {
        List<Dish> dishList = Myentity.menu;
        //1.首先变成stream
        List<Dish> dishList2 = dishList.stream()
                //选 出素材
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        return dishList2;
    }
    @RequestMapping("nihao3")
    public List<Dish> nihao3() {
        List<Dish> dishList = Myentity.menu;
        //1.首先变成stream
        List<Dish> dishList2 = dishList.stream()
                //选 出素材
                .distinct()
                .collect(Collectors.toList());
        return dishList2;
    }
    @RequestMapping("yinyonghanshu")
    public List<Integer> yinyonghanshu() {
        List<Dish> dishList = Myentity.menu;
        //1.首先变成stream
        List<Integer> dishList2 = dishList.stream()
               .map(Dish::getName)
                .map(word->word.substring(0,2))
                .map(String::length)
                .collect(Collectors.toList());
        return dishList2;
    }

    @RequestMapping("match")
    public Boolean match(){
        List<Dish> dishList = Myentity.menu;
      Boolean myboolean=  dishList.stream().allMatch(b->b.getCalories()>400);
      return myboolean;
    }
    @RequestMapping("reduce")
    public int reduce(){
        List<Integer> dishList = Arrays.asList(1,2,3,1,4);
       int i= dishList.stream().reduce(0,(a,b)->a+b);
        return i;
    }
    @RequestMapping("reduceMax")
    public Dish reduceMax(){
        List<Dish> dishList = Myentity.menu;
        Optional<Dish>  dish= dishList.stream().filter(b->b.getCalories()>400).reduce((a, b)->a.getCalories()<b.getCalories()?a:b);
        return dish.get();
    }
}

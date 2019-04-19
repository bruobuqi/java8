package com.example.myjava8.page6;

import com.example.myjava8.constant.Caloriclevel;
import com.example.myjava8.entity.Dish;
import com.example.myjava8.entity.Myentity;
import com.example.myjava8.service.Page6Service;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/page6")
public class Page6Controller {
    @Autowired
    Page6Service page6Service;

    @RequestMapping("ceshi")
    public String nihao() {
        return page6Service.nihao();
    }

    @RequestMapping("max")
    public Object max() {
        List<Dish> menu = Myentity.menu;
        Comparator comparatorsofmy = Comparator.comparingInt(Dish::getCalories);
        Object collect = menu.stream().collect(maxBy(comparatorsofmy));
        return collect;
    }

    @RequestMapping("sum")
    public Object sum() {
        List<Dish> menu = Myentity.menu;

        Object collect1 = menu.stream().collect(summingInt(Dish::getCalories));
        Object collect = menu.stream().collect(averagingInt(Dish::getCalories));
        IntSummaryStatistics avgsummax = menu.stream().collect(summarizingInt(Dish::getCalories));
        return avgsummax.getMax();
    }

    @RequestMapping("join")
    public String join() {
        List<Dish> menu = Myentity.menu;
        Object object = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        return object.toString();
    }

    @RequestMapping("groupingBY")
    public Object groupingBypage6() {
        List<Dish> menu = Myentity.menu;
        Map<Dish.Type, List<Dish>> dishMap = menu.stream()//变为流
                .collect(groupingBy(Dish::getType));
        return dishMap;
    }

    @RequestMapping("groupingBY2")
    public Object groupingBy2() {
        List<Dish> menu = Myentity.menu;
        Map<Caloriclevel, List<Dish>> dishMap = menu.stream()//变为流
                .collect(groupingBy(dish -> {
                    if (dish.getCalories() > 700) {
                        return Caloriclevel.FAT;
                    } else if (dish.getCalories() <= 400) {
                        return Caloriclevel.DIET;
                    } else {
                        return Caloriclevel.nOMAL;
                    }
                }));
        return dishMap;
    }

    @RequestMapping("groupingBY3")
    public Object groupingBy3() {
        List<Dish> menu = Myentity.menu;
        Map<Dish.Type, Map<Caloriclevel, List<Dish>>> dishMap = menu.stream().collect(//返回结果是俩个map
                groupingBy(Dish::getType,//一级分组
                        groupingBy(dish -> {//二级分组
                                    if (dish.getCalories() > 700) {
                                        return Caloriclevel.FAT;
                                    } else if (dish.getCalories() <= 400) {
                                        return Caloriclevel.DIET;
                                    } else {
                                        return Caloriclevel.nOMAL;
                                    }
                                }
                        )
                )
        );
        return dishMap;
    }
    //partition分区
    @RequestMapping("partition")
    public Object partition() {
        List<Dish> menu = Myentity.menu;
        //第一种方式,分区函数
        Map<Boolean,List<Dish>> listMap=menu.stream().collect(partitioningBy(Dish::isVegetarian));//分区函数
        //第二种方式 filter
        List<Dish> dishList=menu.stream().filter(Dish::isVegetarian).collect(toList());
        return listMap;
    }

}

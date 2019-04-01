package com.example.myjava8.page6;

import com.example.myjava8.entity.Dish;
import com.example.myjava8.entity.Myentity;
import com.example.myjava8.service.Page6Service;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/page6")
public class Page6Controller {
    @Autowired
    Page6Service page6Service;
    @RequestMapping("ceshi")
    public String nihao(){
        return page6Service.nihao();
    }
    @RequestMapping("max")
    public Object max(){
        List<Dish> menu = Myentity.menu;
        Comparator comparatorsofmy=Comparator.comparingInt(Dish::getCalories);
        Object collect = menu.stream().collect(maxBy(comparatorsofmy));
        return collect;
    }
    @RequestMapping("sum")
    public Object sum(){
        List<Dish> menu = Myentity.menu;

        Object collect1 = menu.stream().collect(summingInt(Dish::getCalories));
        Object collect = menu.stream().collect(averagingInt(Dish::getCalories));
        IntSummaryStatistics avgsummax=menu.stream().collect(summarizingInt(Dish::getCalories));
        return avgsummax.getMax();
    }
    @RequestMapping("join")
    public String join(){
        List<Dish> menu = Myentity.menu;
      Object object=  menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        return object.toString() ;
    }
}

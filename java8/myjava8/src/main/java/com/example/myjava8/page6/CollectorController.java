package com.example.myjava8.page6;

import com.example.myjava8.entity.Dish;
import com.example.myjava8.entity.Myentity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collector;

@RestController
@RequestMapping("page6")
public class CollectorController {
    @RequestMapping("collect")
public Object collectPage6(){
        List<Dish> menu = Myentity.menu;
      List<Dish> dishList=  menu.stream().filter(dish -> dish.getCalories()>700).collect(new MyTolistCollector<Dish>());
      
        return dishList;
    }

}

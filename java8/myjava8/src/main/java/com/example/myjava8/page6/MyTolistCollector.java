package com.example.myjava8.page6;

import com.sun.scenario.effect.Identity;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MyTolistCollector<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;//创建集合的起始点
    }

    @Override
    public BiConsumer<List<T>,T> accumulator() {//累计遍历项目远未修改累加器  主:此方法的泛型是俩个参数
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>>  combiner() {//修改第一个累加器,将其与第二个累加器的内容合并
        return (list,list2)->{
            list.addAll(list2);
            return list;
        };
    }

    @Override
    public Function finisher() {//恒等函数
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {//为收集器添加IDENTITY_FINISH和CONCURRENT标志
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
    }
}

package com.example.myjava8.page5;

import com.example.myjava8.entity.Myentity;
import com.example.myjava8.entity.Trader;
import com.example.myjava8.entity.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Page5Controller {
    @RequestMapping("ceshi")
    public List ceshi(){
        List<Transaction> list=Myentity.traction();
   List list1=     list.stream().filter(b->b.getYear()==2011).sorted(Comparator.comparing(b->b.getValue())).collect(Collectors.toList());
        return list1;
    }
    @RequestMapping("ceshi2")
    public List ceshi2(){
        List<Transaction> list=Myentity.traction();
        List list1=     list.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(Collectors.toList());
        return list1;
    }
    @RequestMapping("ceshi3")
    public List ceshi3(){
        List<Transaction> list=Myentity.traction();
        List list1=     list.stream().map(Transaction::getTrader).filter(transaction -> transaction.getCity().equals("Cambridge")).distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        return list1;
    }
    @RequestMapping("ceshi4")
    public Transaction ceshi4(){
        List<Transaction> list=Myentity.traction();
        Transaction list1=     list.stream().reduce((a,b)->a.getValue()>b.getValue()?a:b).get();
        return list1;
    }
}

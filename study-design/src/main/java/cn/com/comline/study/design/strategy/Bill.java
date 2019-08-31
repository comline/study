package cn.com.comline.study.design.strategy;

import java.util.ArrayList;
import java.util.List;

public class Bill {

    public List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public int getSumCents(){
        return items.stream().mapToInt(item->item.getCents()).sum();
    }

    public void pay(PaymentMethod paymentMethod){
        paymentMethod.pay(getSumCents());
    }
}

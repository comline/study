package cn.com.comline.study.design.strategy;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Card implements PaymentMethod {

    static Map<String,PaymentMethod> paymentMethodMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        paymentMethodMap.put(getType(),this);
    }

    @Override
    public void pay(int cents) {
        System.out.println("use " + getType() + "-> payed" + cents + " cents");
        excectuTransaction(cents);//具体执行支付操作
    }

    protected  abstract  String getType();


    protected abstract void excectuTransaction(int cents);

}

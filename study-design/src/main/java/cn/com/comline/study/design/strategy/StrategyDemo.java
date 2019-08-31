package cn.com.comline.study.design.strategy;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StrategyDemo {
    public static void main(String[] args) {
        Bill bill = new Bill();
        bill.addItem(new Item("JVM书籍", 50000));
        bill.addItem(new Item("皮带", 100000));

//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        context.start();
        bill.pay(PaymentMethodFactory.getPaymentMethod("credit"));
        bill.pay(Card.paymentMethodMap.get("credit"));
    }
}

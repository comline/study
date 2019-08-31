package cn.com.comline.study.design.strategy;

import org.springframework.stereotype.Service;

@Service
public class CreditCard extends Card {
    @Override
    protected String getType() {
        return "CreditCard";
    }

    @Override
    protected void excectuTransaction(int cents) {
        System.out.println("doTrsaction with credit:" + cents);
    }
}

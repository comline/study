package cn.com.comline.study.design.strategy;

import org.springframework.stereotype.Service;

@Service
public class DebitCard extends Card {
    @Override
    protected String getType() {
        return "DebitCard";
    }

    @Override
    protected void excectuTransaction(int cents) {
        System.out.println("doTrsaction with debit:" + cents);
    }
}

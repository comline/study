package cn.com.comline.study.design.strategy.promotion;

public class CashbackStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("返现促销，返回金额转到支付宝账户");
    }
}

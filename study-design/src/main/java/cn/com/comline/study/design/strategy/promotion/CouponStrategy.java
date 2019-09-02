package cn.com.comline.study.design.strategy.promotion;

public class CouponStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("领取优惠卷，课程的价格直接减去优惠卷面值抵扣");
    }
}

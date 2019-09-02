package cn.com.comline.study.design.strategy.promotion;

public class PromotionAvtivity {
    PromotionStrategy promotionStrategy;

    public PromotionAvtivity(PromotionStrategy promotionStrategy){
        this.promotionStrategy = promotionStrategy;
    }

    public void execute(){
        this.promotionStrategy.doPromotion();
    }
}

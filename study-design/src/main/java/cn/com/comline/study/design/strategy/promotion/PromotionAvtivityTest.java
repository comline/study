package cn.com.comline.study.design.strategy.promotion;

public class PromotionAvtivityTest {
//    public static void main(String[] args) {
//        PromotionAvtivity avtivity618 = new PromotionAvtivity(new CouponStrategy());
//        PromotionAvtivity avtivity1111 = new PromotionAvtivity(new CashbackStrategy());
//
//        avtivity618.execute();
//        avtivity1111.execute();
//    }

//    public static void main(String[] args) {
//        PromotionAvtivity promotionAvtivity = null;
//        String promotionkey = "CASHBACK";
//
//        if(StringUtils.equals(promotionkey,"COUPON")){
//            promotionAvtivity = new PromotionAvtivity(new CouponStrategy());
//        } else if(StringUtils.equals(promotionkey,"CASHBACK")){
//            promotionAvtivity = new PromotionAvtivity(new CashbackStrategy());
//        } //....
//        promotionAvtivity.execute();
//    }

    public static void main(String[] args) {
        String promotionKey = "GROUPBUY";
        PromotionAvtivity promotionAvtivity = new PromotionAvtivity(PromotionStrategyFactory.getPromotionStrategy(promotionKey));
        promotionAvtivity.execute();
    }
}

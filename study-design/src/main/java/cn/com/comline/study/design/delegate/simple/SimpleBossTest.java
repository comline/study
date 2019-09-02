package cn.com.comline.study.design.delegate.simple;

public class SimpleBossTest {
    public static void main(String[] args) {

        new Boss().command("架构", new Leader());
    }
}

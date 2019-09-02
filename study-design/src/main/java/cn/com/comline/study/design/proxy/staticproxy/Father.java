package cn.com.comline.study.design.proxy.staticproxy;

import cn.com.comline.study.design.proxy.Person;

public class Father {
    private Person person;

    public Father(Person person){
        this.person = person;
    }
    public void findLove(){
        System.out.println("物色对象");
        this.person.findLove();
        System.out.println("双方父母同意，确立关系");
    }
}

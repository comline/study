package cn.com.comline.study.design.prototype.simple;

import java.util.List;

public class ConCreatePrototypeA implements Prototype {

    private int age;

    private String name;

    private List hobbies;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getHobbies() {
        return hobbies;
    }

    public void setHobbies(List hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public Prototype clone() {
        ConCreatePrototypeA conCreatePrototypeA = new ConCreatePrototypeA();
        conCreatePrototypeA.setName(this.name);
        conCreatePrototypeA.setAge(this.age);
        conCreatePrototypeA.setHobbies(this.hobbies);
        return conCreatePrototypeA;
    }
}

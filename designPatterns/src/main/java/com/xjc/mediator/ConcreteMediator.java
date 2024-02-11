package com.xjc.mediator;

public class ConcreteMediator extends Mediator {
    public void setColleague1(Colleague1 colleague1) {
        this.colleague1 = colleague1;
    }

    public void setColleague2(Colleague2 colleague2) {
        this.colleague2 = colleague2;
    }

    private Colleague1 colleague1;
    private Colleague2 colleague2;


    @Override
    void changed(Colleague colleague) {
        System.out.println("ConcreteMediator do something");
        if (colleague instanceof Colleague1) {
            colleague2.colleague2Do();
        } else if (colleague instanceof Colleague2) {
            colleague1.colleague1Do();
        }
    }
}

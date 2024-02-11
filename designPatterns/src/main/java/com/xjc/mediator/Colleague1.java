package com.xjc.mediator;

public class Colleague1 extends Colleague {
    public Colleague1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void action() {
        System.out.println("Colleague1 action");
        getMediator().changed(this);
    }

    public void colleague1Do() {
        System.out.println("colleague1 do something");
    }
}

package com.xjc.mediator;

public class Colleague2 extends Colleague {
    public Colleague2(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void action() {
        System.out.println("Colleague2 action");
        getMediator().changed(this);
    }

    public void colleague2Do() {
        System.out.println("colleague2 do something");
    }

}

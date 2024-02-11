package com.xjc.mediator;

/**
 * 抽象同事类
 */
public abstract class Colleague {

    private final Mediator mediator;

    public Colleague(Mediator m) {
        mediator = m;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public abstract void action();

}
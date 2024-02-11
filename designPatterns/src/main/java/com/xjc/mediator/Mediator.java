package com.xjc.mediator;

/**
 * 抽象调停者类
 */
public abstract class Mediator {
    //事件方法，由子类实现(调停者关键逻辑，负责协调)
    abstract void changed(Colleague colleague);

}
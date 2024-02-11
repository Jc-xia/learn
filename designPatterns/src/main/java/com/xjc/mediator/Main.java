package com.xjc.mediator;

public class Main {

    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();
        Colleague1 colleague1 = new Colleague1(mediator);
        mediator.setColleague1(colleague1);
        mediator.setColleague2(new Colleague2(mediator));
        colleague1.action();

    }
}

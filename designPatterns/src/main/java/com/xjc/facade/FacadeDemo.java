package com.xjc.facade;

public class FacadeDemo {

    // 客户端
    public static void main(String[] args) {
        // 创建门面对象
        Facade facade = new Facade();

        // 客户端通过门面对象调用子系统的方法
        facade.operation();
    }


    // 子系统类A
    static class SubsystemA {
        public void operationA() {
            System.out.println("SubsystemA: operationA");
        }
    }

    // 子系统类B
    static class SubsystemB {
        public void operationB() {
            System.out.println("SubsystemB: operationB");
        }
    }

    // 子系统类C
    static class SubsystemC {
        public void operationC() {
            System.out.println("SubsystemC: operationC");
        }
    }

    // 门面类
    static class Facade {
        private SubsystemA subsystemA;
        private SubsystemB subsystemB;
        private SubsystemC subsystemC;

        public Facade() {
            this.subsystemA = new SubsystemA();
            this.subsystemB = new SubsystemB();
            this.subsystemC = new SubsystemC();
        }

        // 提供一个简化的接口供客户端使用
        public void operation() {
            subsystemA.operationA();
            subsystemB.operationB();
            subsystemC.operationC();
        }
    }


}

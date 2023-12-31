package singleton;

/**
 * 饿汉式
 * 类加载到内存后，就实例化一个单例，jvm保证线程安全
 * 简单实用
 * 唯一缺点：不管是否用到都会实例化
 *
 * @author JC
 * @create 2023/12/31
 */
public class Mgr01 {
    private static final Mgr01 INSTANCE = new Mgr01();

    private Mgr01() {
    }

    public static Mgr01 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(Mgr01.getInstance().hashCode())).start();
        }
    }

}

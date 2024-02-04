package strategy;

/**
 * @author JC
 * @create 2024/2/4
 */
public class Main {
    public static void main(String[] args) {
        Cat[] cats = new Cat[]{new Cat(3, 5), new Cat(1, 2), new Cat(5, 1)};
        Sorter<Cat> catsSorter = new Sorter<>();
//        catsSorter.sort(cats,new CatHeightComparator());
        // 函数式编程，当接口只有一个抽象方法时，可用匿名函数的形式，lambda表达式，直接创建
        // runnable ，callable都是这种接口，也叫做函数式接口，可以加@FunctionalInterface ，这个注解不是强制的，只是为了校验是否符合函数式接口
        // 抽象方法，不包括static方法，default方法，和object的方法（tostring、hash）
        catsSorter.sort(cats, (Cat cat1, Cat cat2) -> {
            if (cat1.getWeight() < cat2.getWeight()) {
                return -1;
            } else if (cat1.getWeight() > cat2.getWeight()) {
                return 1;
            }
            return 0;
        });
        for (Cat cat : cats) {
            System.out.println(cat.toString());
        }

        Dog[] dogs = new Dog[]{new Dog(3), new Dog(1), new Dog(5)};
        Sorter<Dog> dogSorter = new Sorter<>();
        dogSorter.sort(dogs, new DogComparator());
        for (Dog dog : dogs) {
            System.out.println(dog.toString());
        }


    }
}

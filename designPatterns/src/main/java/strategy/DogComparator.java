package strategy;

/**
 * @author JC
 * @create 2024/2/4
 */
public class DogComparator implements Comparator<Dog> {
    @Override
    public int compare(Dog dog1, Dog dog2) {
        if (dog1.getFood() < dog2.getFood()) {
            return -1;
        } else if (dog1.getFood() > dog2.getFood()) {
            return 1;
        }
        return 0;
    }
}

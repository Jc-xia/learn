package strategy;

/**
 * @author JC
 * @create 2024/2/4
 */
public class Sorter<T> {
    public void sort(T[] a, Comparator<T> comparator) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (comparator.compare(a[i], a[j]) == -1) {
                    swap(a, i, j);
                }
            }
        }
    }

    private void swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

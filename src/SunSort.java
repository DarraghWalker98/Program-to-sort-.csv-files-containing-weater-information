import java.util.Comparator;

public class SunSort implements Comparator<Weather> {
    @Override
    public int compare(Weather w1, Weather w2) {
        if (w1.getSun() > w2.getSun())      //sorts based on sun time
            return 1;
        else if (w1.getSun() < w2.getSun())
            return -1;
        else
            return 0;
    }
}

import java.util.Comparator;

public class RainSort implements Comparator<Weather> {
    @Override
    public int compare(Weather w1, Weather w2) {
        if (w1.getRain() < w2.getRain())            //sorts based on rainfall
            return 1;
        else if (w1.getRain() > w2.getRain())
            return -1;
        else
            return 0;
    }
}

import java.util.Comparator;

public class WindSort implements Comparator<Weather> {
    @Override
    public int compare(Weather w1, Weather w2) {
        if (w1.getWdsp() < w2.getWdsp())                //sorts based on windspeed
            return 1;
        else if (w1.getWdsp() > w2.getWdsp())
            return -1;
        else
            return 0;
    }
}


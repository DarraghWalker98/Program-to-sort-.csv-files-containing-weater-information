public class Weather implements Comparable<Weather>{
    private double year;           //private members
    private double month;
    private double meant;
    private double maxtp;
    private double mintp;
    private double mnmax;
    private double mnmin;
    private double rain;
    private double gmin;
    private double wdsp;
    private double maxgt;
    private double sun;

    public Weather(double year, double month, double meant, double maxtp, double mintp, double mnmax, double mnmin, double rain, double gmin, double wdsp, double maxgt, double sun) {
        this.year = year;
        this.month = month;
        this.meant = meant;
        this.maxtp = maxtp;
        this.mintp = mintp;
        this.mnmax = mnmax;
        this.mnmin = mnmin;
        this.rain = rain;
        this.gmin = gmin;
        this.wdsp = wdsp;
        this.maxgt = maxgt;
        this.sun = sun;
    }

    public double getYear() {
        return year;
    }

    public void setYear(double year) {
        this.year = year;
    }

    public double getMonth() {
        return month;
    }

    public void setMonth(double month) {
        this.month = month;
    }

    public double getMeant() {
        return meant;
    }

    public void setMeant(double meant) {
        this.meant = meant;
    }

    public double getMaxtp() {
        return maxtp;
    }

    public void setMaxtp(double maxtp) {
        this.maxtp = maxtp;
    }

    public double getMintp() {
        return mintp;
    }

    public void setMintp(double mintp) {
        this.mintp = mintp;
    }

    public double getMnmax() {
        return mnmax;
    }

    public void setMnmax(double mnmax) {
        this.mnmax = mnmax;
    }

    public double getMnmin() {
        return mnmin;
    }

    public void setMnmin(double mnmin) {
        this.mnmin = mnmin;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public double getGmin() {
        return gmin;
    }

    public void setGmin(double gmin) {
        this.gmin = gmin;
    }

    public double getWdsp() {
        return wdsp;
    }

    public void setWdsp(double wdsp) {
        this.wdsp = wdsp;
    }

    public double getMaxgt() {
        return maxgt;
    }

    public void setMaxgt(double maxgt) {
        this.maxgt = maxgt;
    }

    public double getSun() {
        return sun;
    }

    public void setSun(double sun) {
        this.sun = sun;
    }

    @Override
    public String toString() {
        return  "  " + year +
                "  " + month +
                "  " + meant +
                "  " + maxtp +
                "  " + mintp +
                "  " + mnmax +
                "  " + mnmin +
                "  " + rain +
                "  " + gmin +
                "  " + wdsp +
                "  " + maxgt +
                "  " + sun + "\n";
    }

    @Override
    public int compareTo(Weather o) {           //function to sort arraylist by temp high to low
        if (maxtp < o.maxtp)
            return -1;
        else if (maxtp > o.maxtp)
            return 1;
        else
            return 0;
    }
}

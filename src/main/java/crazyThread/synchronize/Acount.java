package crazyThread.synchronize;

public class Acount {
    private String num; //编号
    private double count; //余额

    public Acount(String num, double count) {
        this.num = num;
        this.count = count;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        return num.hashCode();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && o.getClass() == Acount.class) {
            Acount target = (Acount) o;
            return target.getNum().equals(num);
        }
        return false;
    }
}

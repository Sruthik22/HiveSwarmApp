package hiveswarm.hiveswarm;

public class Hive {
    protected String name;
    protected int star_rating;
    protected int hive_number;
    protected String last_check_date;

    public Hive() {
    }

    public Hive(String name, int hive_number) {
        this.name = name;
        this.hive_number = hive_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHiveNumber() {
        return hive_number;
    }

    public void setHive_number(int hive_number) {
        this.hive_number = hive_number;
    }
}

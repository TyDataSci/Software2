package model;

public class GroupBy {
    String valueName;
    int count;

    public GroupBy(String valueName, int count) {
        this.valueName = valueName;
        this.count = count;
    }

    public String getValueName() {
        return valueName;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString(){
        return valueName;
    }

}

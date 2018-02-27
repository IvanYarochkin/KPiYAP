package by.bsac.practical3.entity;

public class Engine {
    private String name;
    private int maxCapacity;
    private int occupiedCapacity;

    public Engine() {
    }

    public Engine(String name, int maxCapacity, int occupiedCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.occupiedCapacity = occupiedCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getOccupiedCapacity() {
        return occupiedCapacity;
    }

    public void setOccupiedCapacity(int occupiedCapacity) {
        this.occupiedCapacity = occupiedCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof Engine) ) return false;

        Engine engine = (Engine) o;

        if ( maxCapacity != engine.maxCapacity ) return false;
        if ( occupiedCapacity != engine.occupiedCapacity ) return false;
        return name != null ? name.equals(engine.name) : engine.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + maxCapacity;
        result = 31 * result + occupiedCapacity;
        return result;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "name='" + name + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", occupiedCapacity=" + occupiedCapacity +
                '}';
    }
}

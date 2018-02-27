package by.bsac.practical3.entity;

public class Wheel implements Cloneable {
    private String name;
    private int depreciation;

    public Wheel() {
    }

    public Wheel(String name, int depreciation) {
        this.name = name;
        this.depreciation = depreciation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(int depreciation) {
        this.depreciation = depreciation;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof Wheel) ) return false;

        Wheel wheel = (Wheel) o;

        if ( depreciation != wheel.depreciation ) return false;
        return name != null ? name.equals(wheel.name) : wheel.name == null;
    }

    @Override
    public Wheel clone() throws CloneNotSupportedException {
        return (Wheel) super.clone();
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + depreciation;
        return result;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "name='" + name + '\'' +
                ", depreciation=" + depreciation +
                '}';
    }
}

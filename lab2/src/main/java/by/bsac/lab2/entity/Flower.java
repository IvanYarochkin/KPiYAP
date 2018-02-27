package by.bsac.lab2.entity;

public class Flower extends Entity implements Cloneable {
    private String name;
    private int stemLength;
    private String color;
    private MultiplyingType multiplying;

    public Flower() {
    }

    public Flower(long id, String name, int stemLength, String color, MultiplyingType multiplying) {
        super(id);
        this.name = name;
        this.stemLength = stemLength;
        this.color = color;
        this.multiplying = multiplying;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStemLength() {
        return stemLength;
    }

    public void setStemLength(int stemLength) {
        this.stemLength = stemLength;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public MultiplyingType getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(MultiplyingType multiplying) {
        this.multiplying = multiplying;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof Flower) ) return false;

        Flower flower = (Flower) o;

        if ( stemLength != flower.stemLength ) return false;
        if ( name != null ? !name.equals(flower.name) : flower.name != null ) return false;
        if ( color != null ? !color.equals(flower.color) : flower.color != null ) return false;
        return multiplying == flower.multiplying;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + stemLength;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (multiplying != null ? multiplying.hashCode() : 0);
        return result;
    }

    @Override
    public Flower clone() {
        return (Flower) super.clone();
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", stemLength=" + stemLength +
                ", color='" + color + '\'' +
                ", multiplying=" + multiplying +
                '}';
    }
}

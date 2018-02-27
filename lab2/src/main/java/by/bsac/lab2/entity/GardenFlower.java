package by.bsac.lab2.entity;

public class GardenFlower extends Flower implements Cloneable {
    private SoilType soil;

    public GardenFlower() {
    }

    public GardenFlower(long id, String name, int stemLength, String color, MultiplyingType multiplying, SoilType soil) {
        super(id, name, stemLength, color, multiplying);
        this.soil = soil;
    }

    public SoilType getSoil() {
        return soil;
    }

    public void setSoil(SoilType soil) {
        this.soil = soil;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof GardenFlower) ) return false;
        if ( !super.equals(o) ) return false;

        GardenFlower that = (GardenFlower) o;

        return soil == that.soil;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (soil != null ? soil.hashCode() : 0);
        return result;
    }

    @Override
    public GardenFlower clone() {
        return (GardenFlower) super.clone();
    }

    @Override
    public String toString() {
        return "GardenFlowerService{name='" + getName() + '\'' +
                ", stemLength=" + getStemLength() +
                ", color='" + getColor() + '\'' +
                ", multiplying=" + getMultiplying() +
                "soil=" + soil +
                '}';
    }
}

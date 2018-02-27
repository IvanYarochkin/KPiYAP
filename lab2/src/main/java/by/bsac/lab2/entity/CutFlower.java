package by.bsac.lab2.entity;

import by.bsac.lab2.exception.FlowerException;

public class CutFlower extends Flower implements Cloneable {
    private static final String FRESHNESS_EXCEPTION_MESSAGE = " freshness is less then 0;";
    private static final String PRICE_EXCEPTION_MESSAGE = " price is less then 0;";

    private int freshness;
    private double price;

    public CutFlower() {
    }

    public CutFlower(long id, String name, int stemLength, String color, MultiplyingType multiplying, int freshness, double price) throws FlowerException {
        super(id, name, stemLength, color, multiplying);
        if ( freshness < 0 ) {
            throw new FlowerException(freshness + FRESHNESS_EXCEPTION_MESSAGE);
        }
        if ( price < 0 ) {
            throw new FlowerException(price + PRICE_EXCEPTION_MESSAGE);
        }
        this.freshness = freshness;
        this.price = price;
    }

    public int getFreshness() {
        return freshness;
    }

    public void setFreshness(int freshness) throws FlowerException {
        if ( freshness < 0 ) {
            throw new FlowerException(freshness + FRESHNESS_EXCEPTION_MESSAGE);
        }
        this.freshness = freshness;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws FlowerException {
        if ( price < 0 ) {
            throw new FlowerException(price + PRICE_EXCEPTION_MESSAGE);
        }
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof CutFlower) ) return false;
        if ( !super.equals(o) ) return false;

        CutFlower cutFlower = (CutFlower) o;

        if ( freshness != cutFlower.freshness ) return false;
        return Double.compare(cutFlower.price, price) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + freshness;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public CutFlower clone() {
        return (CutFlower) super.clone();
    }

    @Override
    public String toString() {
        return "CutFlower{name='" + getName() + '\'' +
                ", stemLength=" + getStemLength() +
                ", color='" + getColor() + '\'' +
                ", multiplying=" + getMultiplying() +
                ", freshness=" + freshness +
                ", price=" + price +
                '}';
    }
}

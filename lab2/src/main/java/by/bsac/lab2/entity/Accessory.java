package by.bsac.lab2.entity;

import by.bsac.lab2.exception.FlowerException;


public class Accessory extends Entity implements Cloneable {
    private static final String PRICE_EXCEPTION_MESSAGE = " price is less then 0;";

    private String name;
    private double price;

    public Accessory() {
    }

    public Accessory(long id, String name, double price) throws FlowerException {
        super(id);
        this.name = name;

        if ( price < 0 ) {
            throw new FlowerException(price + PRICE_EXCEPTION_MESSAGE);
        }

        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if ( !(o instanceof Accessory) ) return false;

        Accessory accessory = (Accessory) o;

        if ( Double.compare(accessory.price, price) != 0 ) return false;
        return name != null ? name.equals(accessory.name) : accessory.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public Accessory clone() {
        return (Accessory) super.clone();
    }

    @Override
    public String toString() {
        return "AccessoryService{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

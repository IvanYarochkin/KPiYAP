package by.bsac.lab2.entity;

import java.util.ArrayList;
import java.util.List;

public class Bouquet extends Entity {
    private String name;
    private List<CutFlower> flowers = new ArrayList<CutFlower>();
    private List<Accessory> accessories = new ArrayList<Accessory>();

    public Bouquet(long id) {
        super(id);
    }

    public Bouquet(long id, String name) {
        super(id);
        this.name = name;
    }

    public Bouquet(long id, String name, List<CutFlower> flowers, List<Accessory> accessories) {
        super(id);
        this.name = name;
        this.flowers = flowers;
        this.accessories = accessories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CutFlower> getFlowers() {
        final List<CutFlower> cloneFlowers = new ArrayList<CutFlower>();
        flowers.forEach(flower -> cloneFlowers.add(flower.clone()));
        return cloneFlowers;
    }

    public void setFlowers(List<CutFlower> flowers) {
        this.flowers = flowers;
    }

    public List<Accessory> getAccessories() {
        final List<Accessory> cloneAccessories = new ArrayList<Accessory>();
        accessories.forEach(accessory -> cloneAccessories.add(accessory.clone()));
        return cloneAccessories;
    }

    public void setAccessories(List<Accessory> accessories) {
        this.accessories = accessories;
    }

    public void addFlower(CutFlower flower) {
        flowers.add(flower);
    }

    public void addAccessory(Accessory accessory) {
        accessories.add(accessory);
    }

    public double calculateBouquetPrice() {
        double price = 0;
        for (CutFlower flower : flowers) {
            price += flower.getPrice();
        }
        for (Accessory accessory : accessories) {
            price += accessory.getPrice();
        }

        return price;
    }

    @Override
    public String toString() {
        return "Bouquet{" +
                "name='" + name + '\'' +
                ", flowers=" + flowers +
                ", accessories=" + accessories +
                '}';
    }
}

package by.bsac.practical3.main;

import by.bsac.practical3.entity.Car;
import by.bsac.practical3.entity.Engine;
import by.bsac.practical3.entity.Wheel;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Wheel wheel = new Wheel("wheel", 0);
        Engine engine = new Engine("engine", 40, 20);
        Car car = new Car(engine, wheel, wheel.clone(), wheel.clone(), wheel.clone(), "Audi");
        System.out.println(car);
        car.fuil();
        System.out.println(car);
        car.showModel();
        car.go();
    }
}

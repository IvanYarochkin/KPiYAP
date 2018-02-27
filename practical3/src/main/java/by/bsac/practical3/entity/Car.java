package by.bsac.practical3.entity;

public class Car {
    private Engine engine;
    private Wheel firstWheel;
    private Wheel secondWheel;
    private Wheel thirdWheel;
    private Wheel fourthWheel;
    private String model;

    public Car() {
    }

    public Car(Engine engine, Wheel firstWheel, Wheel secondWheel, Wheel thirdWheel, Wheel fourthWheel, String model) {
        this.engine = engine;
        this.firstWheel = firstWheel;
        this.secondWheel = secondWheel;
        this.thirdWheel = thirdWheel;
        this.fourthWheel = fourthWheel;
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Wheel getFirstWheel() {
        return firstWheel;
    }

    public void setFirstWheel(Wheel firstWheel) {
        this.firstWheel = firstWheel;
    }

    public Wheel getSecondWheel() {
        return secondWheel;
    }

    public void setSecondWheel(Wheel secondWheel) {
        this.secondWheel = secondWheel;
    }

    public Wheel getThirdWheel() {
        return thirdWheel;
    }

    public void setThirdWheel(Wheel thirdWheel) {
        this.thirdWheel = thirdWheel;
    }

    public Wheel getFourthWheel() {
        return fourthWheel;
    }

    public void setFourthWheel(Wheel fourthWheel) {
        this.fourthWheel = fourthWheel;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void go() {
        System.out.println("Car is going");
    }

    public void fuil() {
        engine.setOccupiedCapacity(engine.getMaxCapacity());
    }

    public void showModel() {
        System.out.println(model);
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof Car) ) return false;

        Car car = (Car) o;

        if ( engine != null ? !engine.equals(car.engine) : car.engine != null ) return false;
        if ( firstWheel != null ? !firstWheel.equals(car.firstWheel) : car.firstWheel != null ) return false;
        if ( secondWheel != null ? !secondWheel.equals(car.secondWheel) : car.secondWheel != null ) return false;
        if ( thirdWheel != null ? !thirdWheel.equals(car.thirdWheel) : car.thirdWheel != null ) return false;
        if ( fourthWheel != null ? !fourthWheel.equals(car.fourthWheel) : car.fourthWheel != null ) return false;
        return model != null ? model.equals(car.model) : car.model == null;
    }

    @Override
    public int hashCode() {
        int result = engine != null ? engine.hashCode() : 0;
        result = 31 * result + (firstWheel != null ? firstWheel.hashCode() : 0);
        result = 31 * result + (secondWheel != null ? secondWheel.hashCode() : 0);
        result = 31 * result + (thirdWheel != null ? thirdWheel.hashCode() : 0);
        result = 31 * result + (fourthWheel != null ? fourthWheel.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", firstWheel=" + firstWheel +
                ", secondWheel=" + secondWheel +
                ", thirdWheel=" + thirdWheel +
                ", fourthWheel=" + fourthWheel +
                ", model='" + model + '\'' +
                '}';
    }
}

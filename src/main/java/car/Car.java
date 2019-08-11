package car;

public abstract class Car {
    protected final int tripDistance;

    public Car(int tripDistance){
        this.tripDistance = tripDistance;
    }

    public abstract int distancePerLiter();

    public int tripDistance() {
        return this.tripDistance;
    }

    public String name() {
        return this.getClass().toString().substring(10);
    }

    public int chargeQuantity() {
        return this.tripDistance() / distancePerLiter();
    }
}
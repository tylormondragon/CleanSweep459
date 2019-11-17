package com.cleansweep.ControlSystem;

public class VacuumBag {
    Double bag;

    public Double addDirtToBag(double dirt){
        bag = 0.0;
        bag = bag + dirt;
        return bag;
    }
    public VacuumBag(Double bag) {
        this.bag = bag;
    }

    public Double getBag() {
        return bag;
    }

    public void setBag(Double bag) {
        this.bag = bag;
    }
}

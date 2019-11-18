package com.cleansweep.ControlSystem;

import com.cleansweep.Logger;

import java.util.Scanner;

public class VacuumBag {
    Double bag;

    public Double addDirtToBag(double dirt){
        bag = bag + dirt;
        return bag;
    }
    public VacuumBag(Double bag) {
        this.bag = bag;
    }

    public Double getBag() {
        if (bag >= 47){
            Logger.logInfo("\n EMPTY ME!.");
            Logger.logInfo("\n Press RETURN key when emptied!.");
            Scanner scanner = new Scanner(System.in);
            String readString = scanner.nextLine();
            System.out.println(readString);
            if (readString != null)
                setBag(0.0);

            //emptyBag
        }
        return bag;
    }

    public void setBag(Double bag) {
        this.bag = bag;
    }
}

package model;

public class Passenger {
    private TypeOfPassenger typeOfPassenger;

    public Passenger() {
    }

    public Passenger(TypeOfPassenger typeOfPassenger) {
        this.typeOfPassenger = typeOfPassenger;
    }

    public TypeOfPassenger getTypeOfPassenger() {
        return typeOfPassenger;
    }

    public void setTypeOfPassenger(TypeOfPassenger typeOfPassenger) {
        this.typeOfPassenger = typeOfPassenger;
    }
}

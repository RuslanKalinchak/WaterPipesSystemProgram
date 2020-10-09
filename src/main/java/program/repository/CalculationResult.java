package program.repository;

import program.repository.interfaces.CSVFileObject;

public class CalculationResult implements CSVFileObject {
    private boolean routeExists;
    private int minLength;

    public CalculationResult(boolean routeExists, int minLength) {
        this.routeExists = routeExists;
        this.minLength = minLength;
    }

    public boolean isRouteExists() {
        return routeExists;
    }

    public void setRouteExists(boolean routeExists) {
        this.routeExists = routeExists;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }
}

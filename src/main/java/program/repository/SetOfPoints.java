package program.repository;

import program.repository.interfaces.CSVFileObject;

public class SetOfPoints implements CSVFileObject {
    private int idA;
    private int idB;

    public SetOfPoints(int idA, int idB) {
        this.idA = idA;
        this.idB = idB;
    }

    public int getIdA() {
        return idA;
    }

    public int getIdB() {
        return idB;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public void setIdB(int idB) {
        this.idB = idB;
    }
}

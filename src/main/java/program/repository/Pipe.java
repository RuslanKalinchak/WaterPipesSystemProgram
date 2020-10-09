package program.repository;

import program.repository.interfaces.CSVFileObject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name  = "PIPES")
public class Pipe implements CSVFileObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "ID")
    private Long id;

    @Column (name = "idX")
    private int idX;

    @Column (name = "idY")
    private int idY;

    @Column (name = "Length")
    private int length;

    public Pipe() {
    }

    public Pipe(int idX, int idY, int length) {
        this.idX = idX;
        this.idY = idY;
        this.length = length;
    }

    public Long getId() {
        return id;
    }

    public int getIdX() {
        return idX;
    }

    public int getIdY() {
        return idY;
    }

    public int getLength() {
        return length;
    }

    public void setIdX(int idX) {
        this.idX = idX;
    }

    public void setIdY(int idY) {
        this.idY = idY;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pipe)) return false;
        Pipe pipe = (Pipe) o;
        return idX == pipe.idX &&
                idY == pipe.idY &&
                length == pipe.length &&
                Objects.equals(id, pipe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idX, idY, length);
    }

    @Override
    public String toString() {
        return "Pipe{" +
                "id=" + id +
                ", idX=" + idX +
                ", idY=" + idY +
                ", length=" + length +
                '}';
    }
}

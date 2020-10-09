package program.services.interfaces;

import program.repository.interfaces.CSVFileObject;
import java.util.List;

public interface ICSVReader {
    public List<CSVFileObject> readCSVFile (String path);
}

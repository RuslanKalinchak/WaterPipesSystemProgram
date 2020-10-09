package program.services.interfaces;

import program.repository.CalculationResult;
import java.util.List;

public  interface ICSVWriter {
    public void writeResultsToCSVFile(List<CalculationResult> calculationResult, String path);
}

package program.services;

import java.io.*;
import program.repository.CalculationResult;
import program.services.interfaces.ICSVWriter;
import java.util.List;

public  class CSVWriter implements ICSVWriter {

    @Override
    public void writeResultsToCSVFile(List<CalculationResult> calculationResult, String path) {
        try(FileWriter fileWriter = new FileWriter(path, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            printWriter.println("ROUTE EXISTS;MIN LENGTH");
            printWriter.flush();
            for (int i = 0; i < calculationResult.size(); i++) {
                boolean routeExists = calculationResult.get(i).isRouteExists();
                if (routeExists==false){
                    printWriter.println(routeExists+";");
                    printWriter.flush();
                } else if (routeExists){
                    printWriter.println(routeExists+";"+calculationResult.get(i).getMinLength());
                    printWriter.flush();
                }
            }
            System.out.println("********************************************************************************");
            System.out.println("Writing to file was successful");
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

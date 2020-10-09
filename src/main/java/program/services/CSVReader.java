package program.services;

import program.repository.Pipe;
import program.repository.SetOfPoints;
import program.repository.interfaces.CSVFileObject;
import program.services.interfaces.ICSVReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements ICSVReader {

    @Override
    public List<CSVFileObject> readCSVFile(String path) {
        List <CSVFileObject> pipes = new ArrayList<>();
        List <CSVFileObject> setOfPoints = new ArrayList<>();
        {
            try (FileReader fileReader = new FileReader(path);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String line = "";

                while ((line=bufferedReader.readLine())!=null){
                    if (line.matches("[^a-zA-Z]+")){
                        String [] parameters = line.trim().split(";");
                        if (parameters.length==3){
                            pipes.add(new Pipe(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2])));
                        } else if (parameters.length==2){
                            setOfPoints.add(new SetOfPoints(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1])));
                        }
                    }
                }
            }  catch (FileNotFoundException exception1){
                exception1.printStackTrace();
            }
            catch (IOException exception2) {
                exception2.printStackTrace();
            }
        }
        if (setOfPoints.isEmpty()){
            System.out.println("********************************************************************************");
            System.out.println("Reading the file with the parameters of the water pipeline system was successful");
            System.out.println("");
            return pipes;
        } else if (pipes.isEmpty()){
            System.out.println("********************************************************************************");
            System.out.println("Reading the file with the set of points parameters was successful");
            System.out.println("");
            return setOfPoints;
        } else
            return new ArrayList<>();
    }
}


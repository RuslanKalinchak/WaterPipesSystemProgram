package program;

import program.model.CalculateOperations;
import program.model.Edge;
import program.model.Graph;
import program.repository.CalculationResult;
import program.repository.Pipe;
import program.repository.interfaces.CSVFileObject;
import program.services.CSVReader;
import program.services.CSVWriter;

import java.sql.*;
import java.util.*;

public class StartProgram {
    public static void main(String[] args) {
        String urlPath = "jdbc:h2:~/WaterPipes";
        String user = "";
        String password = "";

        Scanner scanner = new Scanner(System.in) ;
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ HELLO! @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Please write the path to the file with the parameters of the water pipeline system");
        String waterPipesSystemFilePath = scanner.nextLine();
        System.out.println("");
        System.out.println("********************************************************************************");
        System.out.println("Please write the path to the file with the set of points parameters");
        String setOfPointsFilePath = scanner.nextLine();
        System.out.println("");
        System.out.println("********************************************************************************");
        System.out.println("Please write the path to the file in which the calculation result will be recorded");
        String resultFilePath = scanner.nextLine();
        scanner.close();
        System.out.println("");
        System.out.println("@@@@@@@@@@@@@@@@@@ Thank you, wait for the calculation results @@@@@@@@@@@@@@@@@");
        System.out.println("");

        CSVReader csvReader = new CSVReader();

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<CSVFileObject> pipes = csvReader.readCSVFile(waterPipesSystemFilePath);
        Connection connection =null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO PIPES(idX, idY, Length) VALUES (?,?,?)";
        try {
            connection = DriverManager.getConnection (urlPath, user,password);
            statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS PIPES");
            statement.execute("CREATE TABLE PIPES(ID LONG NOT NULL AUTO_INCREMENT, idX INT, idY INT, Length INT)");

            for (int i = 0; i < pipes.size() ; i++) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ((Pipe)(pipes.get(i))).getIdX());
            preparedStatement.setInt(2, ((Pipe)(pipes.get(i))).getIdY());
            preparedStatement.setInt(3, ((Pipe)(pipes.get(i))).getLength());
            preparedStatement.execute();
            }
            System.out.println("********************************************************************************");
            System.out.println("Data has been successfully added to the table");
            System.out.println("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        List<CSVFileObject> setOfPoints = csvReader.readCSVFile(setOfPointsFilePath);

        TreeSet<Integer> nodes = new TreeSet<>();
        for (int i = 0; i < pipes.size(); i++) {
            nodes.add(((Pipe)(pipes.get(i))).getIdX());
            nodes.add(((Pipe)(pipes.get(i))).getIdY());
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < pipes.size(); i++) {
            edges.add(Edge.of(((Pipe)(pipes.get(i))).getIdX(), ((Pipe)(pipes.get(i))).getIdY()));
        }

        int N = nodes.last()+1;

        Graph graph = new Graph(edges, N);

        boolean[] discovered = new boolean[N];

        Stack<Integer> path = new Stack<>();

        List<CalculationResult> calculationResults = CalculateOperations.getCalculationResult(graph, discovered, path, setOfPoints, pipes);

        CSVWriter csvWriter = new CSVWriter();

        csvWriter.writeResultsToCSVFile(calculationResults, resultFilePath);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ GOODBYE! @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
}


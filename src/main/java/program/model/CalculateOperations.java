package program.model;

import program.repository.CalculationResult;
import program.repository.Pipe;
import program.repository.SetOfPoints;
import program.repository.interfaces.CSVFileObject;
import java.util.*;

public class CalculateOperations {

    public static boolean isConnected(Graph graph, int src, int dest,
                                      boolean[] discovered, Stack<Integer> path)
    {
        discovered[src] = true;
        path.add(src);

        if (src == dest) {
            return true;
        }

        for (int i: graph.adjList.get(src))
        {
            if (!discovered[i])
            {
                // return true if destination is found
                if (isConnected(graph, i, dest, discovered, path)) {
                    return true;
                }
            }
        }

        path.pop();
        return false;
    }

    public static Integer calculateShortestPath(List<CSVFileObject> pipes, Stack<Integer> path){
        int result = 0;
        ArrayList<Integer> transitionList = new ArrayList<>();
        ArrayList<int[]> coordinatesOfVertex = new ArrayList<>();
        for (Integer pathVariable: path) {
            transitionList.add(pathVariable);
        }
        for (int i = 1; i < transitionList.size(); i++) {
            coordinatesOfVertex.add(new int[]{transitionList.get(i-1), transitionList.get(i)});
        }
        for (int i = 0; i < coordinatesOfVertex.size(); i++) {
            int [] coordinates = coordinatesOfVertex.get(i);
            int x = coordinates[0];
            int y = coordinates[1];
            for (int j = 0; j < pipes.size(); j++) {
                if (((Pipe)(pipes.get(j))).getIdX()==x&&((Pipe)(pipes.get(j))).getIdY()==y){
                    result = result + ((Pipe)(pipes.get(j))).getLength();               }
            }
        }
        return result;
    }

    public static List<CalculationResult> getCalculationResult (Graph graph, boolean[] discovered,
                                                                Stack<Integer> path, List<CSVFileObject> setOfPoints,
                                                                List<CSVFileObject> pipes){
        List<CalculationResult> calculationResults = new ArrayList<>();

        boolean routeExists = false;
        for (int i = 0; i < setOfPoints.size(); i++) {
            int src = ((SetOfPoints)(setOfPoints.get(i))).getIdA();
            int dest = ((SetOfPoints)(setOfPoints.get(i))).getIdB();
            int shortestPathLength;
            if (isConnected(graph, src, dest, discovered, path))
            {
                System.out.println("============TASK №"+(i+1)+"============");
                System.out.println("Path exists from vertex " + src + " to vertex " + dest);
                System.out.println("The complete path is: " + path);
                shortestPathLength = calculateShortestPath(pipes, path);
                routeExists = true;
                calculationResults.add(new CalculationResult(routeExists, shortestPathLength));
                System.out.println("The shortest length is " + shortestPathLength);
                System.out.println("");
                path = new Stack<Integer>();
            }
            else {
                System.out.println("============TASK №"+(i+1)+"============");
                System.out.println("No path exists between vertices " + src + " and " + dest);
                routeExists = false;
                calculationResults.add(new CalculationResult(routeExists, 0));
                System.out.println("");
                path = new Stack<Integer>();
            }
        }
        return calculationResults;
    }
}

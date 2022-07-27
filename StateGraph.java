import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class represents the adjacency relationships between US states. (More
 * generally, it could be used to represent any unweighted directed graph.)
 * 
 * @author Nathan Sprague
 * @version V2.0, 04/2021
 */
public class StateGraph {

    private HashMap<String, State> stateMap;
    private ArrayList<State> stateList;

    /**
     * Build a StateGraph from a file. The file format is one state per line
     * followed by a comma separated list of neighboring states.
     * 
     * For example, the first two lines might look like the following:
     * 
     * <pre>
     * Alaska
     * Alabama,Mississippi,Tennessee,Georgia,Florida
     * </pre>
     * 
     * No state may be listed as a neighbor if it does not begin some line in
     * the file.
     * 
     * @param fileName The name of the file containing the graph specification.
     * @throws FileNotFoundException If the file cannot be opened for reading.
     */
    public StateGraph(String fileName) throws FileNotFoundException {
        stateMap = new HashMap<String, State>();
        stateList = new ArrayList<State>();

        File file = new File(fileName);
        Scanner scanner;
        String curLine;
        String[] curEntries;
        State curState;

        // Scan once through the file to build all of the State objects.
        scanner = new Scanner(file);
        while (scanner.hasNext()) {
            curLine = scanner.nextLine();
            curEntries = curLine.split(",");
            curState = new State(curEntries[0]);
            stateMap.put(curEntries[0], curState);
            stateList.add(curState);
        }
        scanner.close();

        // Scan through the file a second time to build the adjacency lists.
        scanner = new Scanner(file);
        while (scanner.hasNext()) {
            curLine = scanner.nextLine();
            curEntries = curLine.split(",");
            curState = stateMap.get(curEntries[0]);
            for (int i = 1; i < curEntries.length; i++) {
                curState.addNeighbor(stateMap.get(curEntries[i]));
            }
        }
        scanner.close();
    }

    /**
     * Return a list of all vertices in the graph.
     * 
     * @return The list of vertices.
     */
    public ArrayList<State> getStates() {
        return stateList;
    }

    /**
     * Return the vertex with the indicated name.
     * 
     * @param name The name of the desired State.
     * @return The requested State, or null if it does not exist.
     */
    public State getState(String name) {
        return stateMap.get(name);
    }

    /**
     * Clear the visited flag from all states.
     */
    public void clearVisited() {
        for (State state : stateList) {
            state.setVisited(false);
        }
    }

}

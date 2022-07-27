import java.util.ArrayList;

/**
 * This class represents a single vertex in a graph of states.
 * 
 * @author Nathan Sprague
 * @version V2.0, 4/2021
 */
public class State {

    private ArrayList<State> neighbors;
    private String name;
    private boolean visited;

    /**
     * Construct a vertex. (The name should be unique!)
     * 
     * @param name The name associated with this vertex.
     */
    public State(String name) {
        this.neighbors = new ArrayList<State>();
        this.name = name;
        this.visited = false;
    }

    /**
     * Add a neighbor to the adjacency list for this vertex.
     * 
     * @param neighbor A vertex to add to the adjacency list.
     */
    public void addNeighbor(State neighbor) {
        neighbors.add(neighbor);
    }

    /**
     * Return the adjacency list for this vertex.
     * 
     * @return The list of adjacent vertices.
     */
    public ArrayList<State> getNeighbors() {
        return neighbors;
    }

    /**
     * Check to see if the current vertex has been visited. This value is not
     * set automatically -- it must be set by by calling setVisited. (This could
     * be useful in conducting recursive graph traversals.)
     * 
     * @return True if the vertex has been visited, false otherwise.
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Set or un-set the visited flag for this vertex.
     * 
     * @param visited New value for the visited flag.
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Return the name associated with this vertex.
     * 
     * @return The name.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}

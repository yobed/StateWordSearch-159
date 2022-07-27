import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This class contains methods inspired by the following puzzle, presented by
 * Will Shortz on NPR's weekend edition on February 19th, 2012.
 * 
 * <pre>
 * The word marten, as in the animal, consists of the beginning 
 * letters of Mississippi, Arkansas, Texas, and New Mexico. And 
 * you can actually drive from Mississippi to Arkansas to Texas 
 * to New Mexico, in that order. What is the longest common 
 * English word you can spell by taking the beginning letters 
 * of consecutive states in order, as you travel through them? 
 * My answer has eight letters. Maybe you can better mine. 
 * The longest answer will win.
 * </pre>
 * 
 * @author CS 159 Instructors and Patrick DeBoy
 * @version 4/19/2021
 * 
 *     Help from TA Ryan Showalter.
 */
public class StateSpeller {

    private StateGraph graph;

    /**
     * The constructor sets up the state graph.
     * 
     * @param stateFile A file containing the state adjacency information
     * @throws FileNotFoundException If the file cannot be opened for reading
     */
    public StateSpeller(String stateFile) throws FileNotFoundException {
        this.graph = new StateGraph(stateFile);
    }

    /**
     * Returns true if the provided word can be spelled by starting in any state
     * and using only the first letters of state names. Repeated states are
     * allowed. For example, "mom" can be spelled by moving from Michigan to
     * Ohio, then back to Michigan.
     * 
     * @param word The word to spell
     * @return true if it can be spelled, false otherwise
     */
    public boolean isSpellableFirstLetters(String word) {

        return helper(graph.getStates(), word);
    }

    /**
     * Returns an ArrayList containing the names of states than can be used to
     * spell the provided word using the first letters of visited states.
     * Returns null if the word cannot be spelled by moving between states.
     * Repeated states are NOT allowed. While there may be multiple ways of
     * spelling the word, this method will return only one.
     * 
     * For example, the return value for "amok" is ["Arkansas", "Missouri",
     * "Oklahoma", "Kansas"].
     * 
     * @param word The word to spell
     * @return ArrayList of state names used to spell the word, or null if the
     *     word cannot be spelled
     */
    public ArrayList<String> howToSpellFirstLetters(String word) {

        ArrayList<String> path = new ArrayList<String>();
        for (State state : graph.getStates()) {
            if (spell(state, word.toUpperCase(), path)) {
                return path;

            } else {
                path.clear();
            }
            graph.clearVisited();
        }

        if (path.isEmpty()) {
            path = null;
        }

        return path;

    }

    /**
     * Return an ArrayList containing the names of states than can be used to
     * spell the provided word using any number of letters taken from the
     * beginnings of the names of visited states. Return null if the word cannot
     * be spelled by moving between states. Repeated states are NOT allowed.
     * While there may be multiple ways of spelling the word, this method will
     * return only one.
     * 
     * For example, the return value for "omission" is ["Oklahoma", "Missouri",
     * "Iowa", "Nebraska"].
     * 
     * @param word The word to spell
     * @return ArrayList of state names used to spell the word, or null if the
     *     word cannot be spelled
     */
    public ArrayList<String> howToSpell(String word) {
        ArrayList<String> path = new ArrayList<String>();
        for (State state : graph.getStates()) {
            if (spellContain(state, word.toUpperCase(), path)) {
                return path;

            } else {
                path.clear();
            }
            graph.clearVisited();
        }

        if (path.isEmpty()) {
            path = null;
        }

        return path;
    }

    /**
     * Helper method for isSpellableFirstLetters.
     * 
     * @param states states
     * @param word word
     * @return boolean
     */
    public static boolean helper(ArrayList<State> states, String word) {
        boolean check = false;
        if (word.length() == 0) { // base case
            return true;
        } else if (word.length() > 0) {
            ArrayList<State> tempStates = new ArrayList<State>();
            ArrayList<State> tempNeighbors = new ArrayList<State>();
            for (State state : states) {
                if (word.toUpperCase().charAt(
                        0) == state.getName().toUpperCase().charAt(0)) {

                    tempStates.add(state);

                }
            }
            for (State stateNeighbors : tempStates) {
                tempNeighbors.addAll(stateNeighbors.getNeighbors());
                if (helper(tempNeighbors, word.substring(1))) {
                    check = true;
                }
            }
        }

        return check;
    }

    /**
     * Spell helper for HowToSpellFirstLetters.
     * Help from TA Ryan Showalter.
     * @param state state
     * @param word word
     * @param path arraylist
     * @return boolean boolean
     */
    public boolean spell(State state, String word, ArrayList<String> path) {
        boolean check = false;
        if (word.length() == 0) { // base case
            check = true;
        } else if (word.length() > 0) {
            ArrayList<State> tempNeighbors = new ArrayList<State>();
            tempNeighbors.addAll(state.getNeighbors());

            for (State stateNeighbor : tempNeighbors) { // check neighbor
                if (!stateNeighbor.isVisited() && word.charAt(
                        0) == stateNeighbor.getName().toUpperCase().charAt(0)) {
                    stateNeighbor.setVisited(true);
                    if (spell(stateNeighbor, word.substring(1), path)) {
                        path.add(0, stateNeighbor.getName());
                        return true;
                    }
                }
            }
        }
        return check;

    }

    /**
     * Spell Helper for howToSpell (first containing).
     * 
     * @param state state
     * @param word word
     * @param path arraylist
     * @return boolean boolean
     */
    public boolean spellContain(State state, String word,
            ArrayList<String> path) {
        boolean check = false;
        if (word.length() == 0) { // base case
            check = true;
        } else if (word.length() > 0) {
            ArrayList<State> tempNeighbors = new ArrayList<State>();
            tempNeighbors.addAll(state.getNeighbors());
            int index = 0;
            boolean contain = false;
            for (State stateNeighbor : tempNeighbors) { // check neighbor
                index = 0;
                contain = false;
                for (int i = 0; i < word.length(); i++) {
                    if (stateNeighbor.getName().toUpperCase().charAt(
                            i) == word.charAt(i)) {
                        index++;
                        contain = true;
                    } else {
                        break;
                    }

                }
                if (contain) {
                    stateNeighbor.setVisited(true);
                    if (spellContain(stateNeighbor, word.substring(index),
                            path)) {
                        path.add(0, stateNeighbor.getName());
                        return true;
                    } else {
                        stateNeighbor.setVisited(false);
                    }

                }
            }

        }
        return check;
    }
}

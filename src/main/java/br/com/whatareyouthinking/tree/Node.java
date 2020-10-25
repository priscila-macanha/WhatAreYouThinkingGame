package br.com.whatareyouthinking.tree;

/**
 *
 * @author Priscila
 */
public class Node {

    private String value;
    public Node ifNot;
    public Node ifYes;

    public Node(String value) {
        this.value = value;
        ifNot = null;
        ifYes = null;
    }

    public String getValue() {
        return value;
    }
}

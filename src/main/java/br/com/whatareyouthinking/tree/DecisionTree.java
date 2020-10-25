package br.com.whatareyouthinking.tree;

import javax.swing.JOptionPane;

/**
 *
 * @author Priscila
 */
public class DecisionTree {

    public Node root;
    
    private final Node endPoint;

    public DecisionTree(String endPoint) {
        this.endPoint = new Node(endPoint);
    }

    public void add(String value) {
        root = addRecursive(value, root);
    }

    public boolean start() {
        boolean response = decide(root);

        if (!response) {
            String newDish = getDish();
            String newFeature = getFeatureOfDish(newDish);
             
            add(newFeature);
            add(newDish);
        }
        
        return response;
    }

    private Node addRecursive(String value, Node current) {
        if (current == null) {
            return new Node(value);
        }
        
        if (current.ifYes == null){
            if (current == endPoint){
                return new Node(value);
            }
            current.ifYes = addRecursive(value, current.ifYes);
            current.ifNot = endPoint;
        } else {
            current.ifNot = addRecursive(value, current.ifNot);
        }

        return current;
    }
    
    private boolean decide(Node current) {
        if (current == null) {
            return false;
        }
        
        boolean response = attempQuestion(current.getValue());

        if (response) {
            if (current.ifYes == null) {
                attempSuccess();
                return true;
            } else {
                return decide(current.ifYes);
            }
        } else {
            return decide(current.ifNot);
        }
    }
    
    protected boolean attempQuestion(String value) {
        int response = JOptionPane.showConfirmDialog(
                null,
                "O prato que você pensou é " + value + "?",
                "Confirm",
                JOptionPane.YES_NO_OPTION);
        
        return response == JOptionPane.YES_OPTION;
    }
    
    protected void attempSuccess() {
         JOptionPane.showMessageDialog(
                 null, 
                 "Acertei de novo!", 
                 "Jogo Gourmet", 
                 JOptionPane.INFORMATION_MESSAGE
         );
    }
    
    protected String getFeatureOfDish(String dish){
        return JOptionPane.showInputDialog(
                    null, 
                    dish + " é ______ mas Bolo de Chocolate não.",
                    "Complete",
                    JOptionPane.QUESTION_MESSAGE
        );
    }
    
    protected String getDish() {
        return JOptionPane.showInputDialog(
                    null, 
                    "Qual prato você pensou?",
                    "Desisto",
                    JOptionPane.QUESTION_MESSAGE
        );
    }
}

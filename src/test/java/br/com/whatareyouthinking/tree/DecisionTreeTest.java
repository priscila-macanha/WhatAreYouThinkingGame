
package br.com.whatareyouthinking.tree;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mockito;

/**
 *
 * @author Priscila
 */
public class DecisionTreeTest {
    
    @Test
    public void testAddRootNode() {
        DecisionTree instance = new DecisionTree("final");
        instance.add("root");
        assertEquals("root", instance.root.getValue());
    }

    @Test
    public void testAddLeafNode() {
        DecisionTree instance = new DecisionTree("final");
        instance.add("feature");
        instance.add("dish");

        assertEquals("dish", instance.root.ifYes.getValue());
        assertEquals("final", instance.root.ifNot.getValue());
    }

    @Test
    public void testStartAttempSuccessful() {
        DecisionTree instance = new DecisionTree("final");
        instance.add("feature");
        instance.add("dish");

        DecisionTree mock = Mockito.spy(instance);
        Mockito.doReturn(true).when(mock).attempQuestion(anyString());
        Mockito.doNothing().when(mock).attempSuccess();

        assertEquals(true, mock.start());
    }

    @Test
    public void testStartAttempUnsuccessfully() {
        DecisionTree instance = new DecisionTree("final");
        instance.add("feature");
        instance.add("dish");

        DecisionTree mock = Mockito.spy(instance);
        Mockito.doReturn(false).when(mock).attempQuestion(anyString());
        Mockito.doReturn("newDish").when(mock).getDish();
        Mockito.doReturn("newFeature").when(mock).getFeatureOfDish(anyString());

        Mockito.verify(mock, Mockito.never()).attempSuccess();
        assertEquals(false, mock.start());
        assertEquals("newFeature", instance.root.ifNot.getValue());
        assertEquals("newDish", instance.root.ifNot.ifYes.getValue());
    }
}

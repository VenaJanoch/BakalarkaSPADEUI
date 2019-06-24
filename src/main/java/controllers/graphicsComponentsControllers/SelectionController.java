package controllers.graphicsComponentsControllers;

import graphics.canvas.CanvasItem;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Trida predstavujici controller pro vyber vice prvku na platne
 *
 * @author Václav Janoch
 */
public class SelectionController {

    /** Kolekce uchovavajici instace objekty vybranych na platne**/
    private Set<CanvasItem> selection = new HashSet<>();

    /**
     * Metoda pro pridani prvku do vyberu
     *
     * @param node CanvaItem
     */
    public void add(CanvasItem node) {
        node.setContourViseble(Color.BLACK);
        selection.add(node);
    }

    /**
     * Metoda pro odebrani prvku z vybeeru
     *
     * @param node CanvasItem
     */
    public void remove(CanvasItem node) {
        node.setContourViseble(Color.TRANSPARENT);
        selection.remove(node);
    }

    /**
     * Metoda pro smazani vsech prvku z vyberove mnoziny
     */
    public void clear() {

        while (!selection.isEmpty()) {
            remove(selection.iterator().next());
        }

    }

    /**
     * Metoda pro porovani instanci ulozenych v mnozine
     *
     * @param node CanvaItem
     * @return Node
     */
    public boolean contains(Node node) {
        return selection.contains(node);
    }

    public void log() {
        System.out.println("Items in model: " + Arrays.asList(selection.toArray()));
    }

    /**
     * Getters and Setters
     */
    public Set<CanvasItem> getSelection() {
        return selection;
    }
}

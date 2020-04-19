package com.avatarduel.view.loader;

import javafx.scene.layout.Pane;

/**
 * Interface for Loader
 */
public interface Loader {
    /**
     * Get pane from specific loader
     * @return pane
     */
    Pane getPane();

    /**
     * Render stage in Loader
     */
    void render();
}

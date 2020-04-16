package com.avatarduel.game;

public interface Phase {
    void run() throws Exception;
    void next() throws Exception;
}

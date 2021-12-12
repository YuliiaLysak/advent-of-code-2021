package edu.lysak.day12;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private String name;
    private List<Vertex> edges;
    private boolean reEntry;

    public Vertex(String name, boolean reEntry) {
        this.name = name;
        this.edges = new ArrayList<>();
        this.reEntry = reEntry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vertex> getEdges() {
        return edges;
    }

    public void setEdges(List<Vertex> edges) {
        this.edges = edges;
    }

    public boolean isReEntry() {
        return reEntry;
    }

    public void setReEntry(boolean reEntry) {
        this.reEntry = reEntry;
    }

    @Override
    public String toString() {
        return name;
    }
}

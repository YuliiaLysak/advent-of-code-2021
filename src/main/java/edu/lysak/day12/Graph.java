package edu.lysak.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Graph {
    private final Vertex start;
    private final Vertex end;

    public Graph(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
    }

    public List<List<Vertex>> findAllPaths() {
        return findPaths(start, List.of());
    }

    private List<List<Vertex>> findPaths(Vertex from, List<Vertex> visited) {
        if (from.equals(end)) {
            return List.of(List.of(end));
        }

        if (visited.contains(from) && !from.isReEntry()) {
            return List.of();
        }

        List<Vertex> visitedX = new ArrayList<>(visited);
        visitedX.add(from);

        List<List<Vertex>> result = new ArrayList<>();
        for (Vertex to : from.getEdges()) {
            result.addAll(findPaths(to, visitedX));
        }

        return result.stream()
                .map(it -> {
                    ArrayList<Vertex> vertices = new ArrayList<>(List.of(from));
                    vertices.addAll(it);
                    return List.copyOf(vertices);
                })
                .collect(Collectors.toUnmodifiableList());
    }
}

package edu.lysak.day12;

import edu.lysak.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day12PassagePathing {
    private static final String FILE_PATH = "src/main/resources/day12/input-day12.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Paths through cave system = " + getPart1Result(FILE_PATH));
    }

    public static int getPart1Result(String filePath) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        List<String> verticesName = getAllVerticesNames(input);
        List<Vertex> vertices = convertToVertices(verticesName);
        fillEdges(input, vertices);
//        printVertexEdges(vertices);
        Vertex start = findVertex("start", vertices);
        Vertex end = findVertex("end", vertices);
        Graph graph = new Graph(start, end);
        List<List<Vertex>> allPaths = graph.findAllPaths();
        return allPaths.size();
    }

    private static List<String> getAllVerticesNames(List<String> input) {
        return input.stream()
                .map(line -> line.split("-"))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    private static List<Vertex> convertToVertices(List<String> verticesName) {
        List<Vertex> vertices = new ArrayList<>();
        for (String v : verticesName) {
            vertices.add(new Vertex(v, Character.isUpperCase(v.charAt(0))));
        }
        return vertices;
    }

    private static void fillEdges(List<String> input, List<Vertex> vertices) {
        for (Vertex vertex : vertices) {
            List<Vertex> edges = vertex.getEdges();
            edges.addAll(getAllEdgesForVertex(vertex, input, vertices));
        }
    }

    private static List<Vertex> getAllEdgesForVertex(
            Vertex vertex,
            List<String> input,
            List<Vertex> vertices
    ) {
        List<Vertex> edges = new ArrayList<>();
        String currentVertexName = vertex.getName();
        List<String> otherVerticesName = input.stream()
                .map(line -> line.split("-"))
                .filter(pair -> currentVertexName.equals(pair[0])
                        || currentVertexName.equals(pair[1]))
                .flatMap(Arrays::stream)
                .distinct()
                .filter(name -> !name.equals(currentVertexName))
                .collect(Collectors.toList());

        for (Vertex v : vertices) {
            if (otherVerticesName.contains(v.getName())) {
                edges.add(v);
            }
        }
        return edges;
    }

    private static Vertex findVertex(String vertexName, List<Vertex> vertices) {
        return vertices.stream()
                .filter(v -> vertexName.equals(v.getName()))
                .findFirst()
                .orElseThrow();
    }

    private static void printVertexEdges(List<Vertex> vertices) {
        for (Vertex vertex : vertices) {
            System.out.print(vertex.getName() + " : ");
            System.out.println(vertex.getEdges());
        }
    }
}

import java.util.*;

public class Main {
    // find the shortest paths from the start node
    public static Map<String, Integer> dijkstra(Map<String, Map<String, Integer>> graph, String start) {
        // Priority queue to select the node with the smallest distance
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        
        // Map to store the shortest distances to each node
        Map<String, Integer> distances = new HashMap<>();
        
        // Initialize distances with infinity
        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0); // Distance to the start node is 0
        
        // Add the start node to the priority queue
        priorityQueue.add(new Node(start, 0));
        
        while (!priorityQueue.isEmpty()) {
            // Get the node with the smallest distance
            Node currentNode = priorityQueue.poll();
            
            // Skip nodes that have already been processed with a shorter distance
            if (currentNode.distance > distances.get(currentNode.name)) {
                continue;
            }
            
            // Explore neighbors
            Map<String, Integer> neighbors = graph.get(currentNode.name);
            for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
                String neighborName = neighbor.getKey();
                int edgeWeight = neighbor.getValue();
                
                // Calculate new distance
                int newDistance = currentNode.distance + edgeWeight;
                
                // Update the distance if it's shorter
                if (newDistance < distances.get(neighborName)) {
                    distances.put(neighborName, newDistance);
                    priorityQueue.add(new Node(neighborName, newDistance));
                }
            }
        }
        
        return distances;
    }

    // Helper class to represent a node in the priority queue
    static class Node {
        String name;
        int distance;

        Node(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        // Example graph representation
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        
        graph.put("A", Map.of("B", 1, "C", 4));
        graph.put("B", Map.of("A", 1, "C", 2, "D", 5));
        graph.put("C", Map.of("A", 4, "B", 2, "D", 1));
        graph.put("D", Map.of("B", 5, "C", 1));
        
        // Find shortest paths from node A
        Map<String, Integer> shortestPaths = dijkstra(graph, "A");
        
        // Print the results
        System.out.println(shortestPaths);
    }
}

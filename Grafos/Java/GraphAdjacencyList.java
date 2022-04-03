import java.util.ArrayList;
import java.util.List;

class Edge {
    private Vertex next;
    private double weight;

    Edge() {
        this.next = null;
        this.weight = 0;
    }

    Edge(Vertex destination, double weight) {
        this.weight = weight;
        this.next = destination;
    }

    Edge(Vertex destination) {
        this.next = destination;
        this.weight = 0;
    }

    Edge(double weight) {
        this.weight = weight;
        this.next = null;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Vertex getNext() {
        return this.next;
    }

    public void setNext(Vertex destination) {
        this.next = destination;
    } 
}

class Vertex {
    private int label;
    private int degree;
    private boolean visited;
    private List<Edge> edges;

    Vertex (int label) {
        this.label = label;
        this.edges = new ArrayList<Edge>();
        this.degree = 0;
        this.visited = false;
    }

    public int getLabel() {
        return this.label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public List<Edge> getEdges() {
        return this.edges;
    }

    public void increaseDegree() {
        this.degree++;
    }

    public int getDegree() {
        return this.degree;
    }

    public boolean getVisited() {
        return this.visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}

class Graph {
    private List<Vertex> vertexes;
    private int numberOfVertex;
    private int numberOfEdges;
    private boolean directed;
    private boolean weighted;

    Graph(boolean directed, boolean weighted) {
        this.vertexes = new ArrayList<Vertex>();
        this.numberOfVertex = 0;
        this.numberOfEdges = 0;
        this.directed = directed;
        this.weighted = weighted;
    }

    public int getnumberOfVertex() {
        return this.numberOfVertex;
    }

    public int getnumberOfEdges() {
        return this.numberOfEdges;
    }

    public List<Integer> getLabels() {
        List<Integer> v = new ArrayList<Integer>();
        
        for (int i = 0; i < this.vertexes.size(); i++) {
            v.add(this.vertexes.get(i).getLabel());
        }

        return v;
    }

    public void createVertex(int v1) {
        if (!this.getLabels().contains(v1)) {
            Vertex vertex = new Vertex(v1);
            this.vertexes.add(vertex);
            numberOfVertex++;
        }
    }

    public void connect(int v1, int v2, double peso) {
        if (this.weighted) {
            // Search for vertexes, if they don't exist create them
            Vertex aux1 = this.searchVertex(v1);
            Vertex aux2 = this.searchVertex(v2);

            this.connectVertex(aux1, aux2, peso);
        } else {
            System.out.println("[ERROR]: Unweighted graph, it's not necessary to pass a weight");
        }
    }

    public void connect(int v1, int v2) {
        if (!this.weighted) {
            // Search for vertexes, if they don't exist create them
            Vertex aux1 = this.searchVertex(v1);
            Vertex aux2 = this.searchVertex(v2);

            this.connectVertex(aux1, aux2);
        } else {
            System.out.println("[ERROR]: Weighted graph, it's necessary to pass a weight!");
        }
    }

    public Vertex searchVertex(int v1) {
        for (int i = 0; i < this.vertexes.size(); i++) {
            // If it finds vertex within the list, it just returns it
            if (this.vertexes.get(i).getLabel() == v1) {
                return this.vertexes.get(i);
            }
        }

        // If you can't find vertex, create it
        Vertex vertex = new Vertex(v1);
        this.vertexes.add(vertex);
        numberOfVertex++;

        return vertex;
    }

    public void connectVertex(Vertex v1, Vertex v2, double peso) {
        if (this.directed) {
            if (!this.existAdjacent(v1, v2, peso)) {
                v1.getEdges().add(new Edge(v2, peso));
                v1.increaseDegree();
                this.numberOfEdges++;
            }
        } else {
            if (!this.existAdjacent(v1, v2, peso)) {
                v1.getEdges().add(new Edge(v2, peso));
                v2.getEdges().add(new Edge(v1, peso));
                v1.increaseDegree();
                this.numberOfEdges++;
            }
        }
    }

    public void connectVertex(Vertex v1, Vertex v2) {
        if (this.directed) {
            if (!this.existAdjacent(v1, v2)) {
                v1.getEdges().add(new Edge(v2));
                v1.increaseDegree();
                this.numberOfEdges++;
            }
        } else {
            if (!this.existAdjacent(v1, v2)) {
                v1.getEdges().add(new Edge(v2));
                v2.getEdges().add(new Edge(v1));
                v1.increaseDegree();
                this.numberOfEdges++;
            }
        }
    }

    public boolean existAdjacent(Vertex v1, Vertex v2) {
        for (int i = 0; i < this.vertexes.size(); i++) {
            if (this.vertexes.get(i).getLabel() == v1.getLabel()) {
                for (int j = 0; j < this.vertexes.get(i).getEdges().size(); j++) {
                    if (this.vertexes.get(i).getEdges().get(j).getNext().getLabel() == v2.getLabel()) {
                        return true;
                    }
                }
                break;
            }
        }

        return false;
    }

    public boolean existAdjacent(Vertex v1, Vertex v2, double peso) {
        for (int i = 0; i < this.vertexes.size(); i++) {
            if (this.vertexes.get(i).getLabel() == v1.getLabel()) {
                for (int j = 0; j < this.vertexes.get(i).getEdges().size(); j++) {
                    if (this.vertexes.get(i).getEdges().get(j).getNext().getLabel() == v2.getLabel()) {
                        if (this.vertexes.get(i).getEdges().get(j).getWeight() == peso) {
                            return true;
                        }
                    }
                }
                break;
            }
        }

        return false;
    }
    
    public void print() {
        if (this.weighted == true) {
            for (Vertex v: vertexes) {
                System.out.print("Vertex: " + v.getLabel() + " -> ");
                for (Edge e: v.getEdges()) {
                    System.out.print("[" + e.getNext().getLabel() + ", " + e.getWeight() + "]   ");
                }
                System.out.println("\n");
            }
        } else {
            for (Vertex v: vertexes) {
                System.out.print("Vertex: " + v.getLabel() + " -> ");
                for (Edge e: v.getEdges()) {
                    System.out.print("[" + e.getNext().getLabel() + "]   ");
                }
                System.out.println("\n");
            }
        }
    }

    public void breadthFirstSearch(int start) {
        // Check if starting vertex exists
        if (this.getLabels().contains(start)) {
            List<Vertex> fila = new ArrayList<Vertex>();
            Vertex initial = this.searchVertex(start);
            initial.setVisited(true); // Mark initial vertex as visited
            System.out.println(initial.getLabel());
            fila.add(initial);

            while(fila.size() > 0) {
                Vertex currently = fila.get(0); // Get the first vertex in the queue

                for (int i = 0; i < currently.getEdges().size(); i++) {
                    Vertex adjacent = currently.getEdges().get(i).getNext();

                    // If vertex has not been visited, mark it
                    if (adjacent.getVisited() == false) {
                        System.out.println(adjacent.getLabel());
                        adjacent.setVisited(true);
                        fila.add(adjacent); // Add vertex to my list to visit later
                    }

                }
                fila.remove(0);
            }
        } else {
            System.out.println("[ERROR]: the vertex { " + start + " } doesn't exist.");
        }
    }

    public void depthFirstSearch(int start) {
        // Check if starting vertex exists
        if (this.getLabels().contains(start)) {
            Vertex initial = this.searchVertex(start);
            initial.setVisited(true);
            System.out.println(initial.getLabel());
            
            for (int i = 0; i < initial.getEdges().size(); i++) {
                Vertex actually = initial.getEdges().get(i).getNext();

                if (actually.getVisited() == false) {
                    depthFirstSearch(actually.getLabel());
                }
            }
        } else {
            System.out.println("[ERROR]: the vertex { " + start + " } doesn't exist.");
        }
    }

    public void depthFirstSearchQueue(int start) {
        if (this.getLabels().contains(start)) {
            List<Vertex> pilha = new ArrayList<Vertex>();
            Vertex initial = this.vertexes.get(start);
            pilha.add(initial);

            while (pilha.size() > 0) {
                Vertex actually = pilha.remove(pilha.size() - 1);
                System.out.println(actually.getLabel());

                if (actually.getVisited() == false ) {
                    actually.setVisited(true); // Marcar como visitado

                    for (int i = 0; i < actually.getEdges().size(); i++) {
                        Vertex adjacent = actually.getEdges().get(i).getNext();

                        if (adjacent.getVisited() == false) {
                            pilha.add(adjacent);
                        }
                    }
                }
            }
        } else {
            System.out.println("[ERROR]: the vertex { " + start + " } doesn't exist.");
        }
    }
}

public class GraphAdjacencyList {
    public static void main(String[] args) {
        // Params: (Directed, Weighted)
        Graph graph = new Graph(true, true);
        graph.createVertex(3);
        graph.connect(0, 1, 0.3);
        graph.connect(0, 2, 0.2);
        graph.connect(2, 0, 0.3);
        
        graph.print();
    }
}
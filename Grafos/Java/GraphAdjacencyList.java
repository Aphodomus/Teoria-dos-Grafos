import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; 

class Edge {
    private Vertex next;
    private Vertex predecessor;
    private Vertex successor;
    private double weight;

    Edge() {
        this.next = null;
    }

    Edge(Vertex destination, double weight) {
        this.weight = weight;
        this.next = destination;
    }

    Edge(Vertex destination) {
        this.next = destination;
    }

    Edge(double weight) {
        this.weight = weight;
        this.next = null;
    }

    Edge(Vertex predecessor, Vertex successor, double weight) {
        this.predecessor = predecessor;
        this.successor = successor;
        this.weight = weight;
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

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }

    public Vertex getPredecessor() {
        return this.predecessor;
    }

    public void setSuccessor(Vertex successor) {
        this.successor = successor;
    }

    public Vertex getSuccessor() {
        return this.successor;
    }
}

class Vertex {
    private int label;
    private int degree;
    private boolean visited;
    private List<Edge> edges;
    private Vertex precedent;
    private double distance;
    private int group;

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

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setPrecedent(Vertex precedent) {
        this.precedent = precedent;
    }

    public Vertex getPrecedent() {
        return this.precedent;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getGroup() {
        return this.group;
    }
}

class Graph  {
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

    public void connectNew(int v1, int v2, double weight) {
        if (this.weighted) {
            // Search for vertexes, if they don't exist create them
            Vertex aux1 = this.searchVertex(v1);
            Vertex aux2 = this.searchVertex(v2);

            this.connectVertexNew(aux1, aux2, weight);
        } else {
            System.out.println("[ERROR]: Unweighted graph, it's not necessary to pass a weight");
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

    public void connectVertexNew(Vertex v1, Vertex v2, double peso) {
        if (this.directed) {
            if (!this.existAdjacentNew(v1, v2, peso)) {
                v1.getEdges().add(new Edge(v1, v2, peso));
                v1.increaseDegree();
                this.numberOfEdges++;
            }
        } else {
            if (!this.existAdjacentNew(v1, v2, peso)) {
                v1.getEdges().add(new Edge(v1, v2, peso));
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

    public boolean existAdjacentNew(Vertex v1, Vertex v2, double peso) {
        for (int i = 0; i < this.vertexes.size(); i++) {
            if (this.vertexes.get(i).getLabel() == v1.getLabel()) {
                for (int j = 0; j < this.vertexes.get(i).getEdges().size(); j++) {
                    if (this.vertexes.get(i).getEdges().get(j).getSuccessor().getLabel() == v2.getLabel()) {
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
        System.out.println("\n====== Graph ======\n");

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

        System.out.println("====== End ======\n");
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
            System.out.println("[ERROR]: The vertex { " + start + " } doesn't exist.");
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
            System.out.println("[ERROR]: The vertex { " + start + " } doesn't exist.");
        }
    }

    public void depthFirstSearchQueue(int start) {
        if (this.getLabels().contains(start)) {
            List<Vertex> pilha = new ArrayList<Vertex>();
            Vertex initial = this.searchVertex(start);
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
            System.out.println("[ERROR]: The vertex { " + start + " } doesn't exist.");
        }
    }

    public boolean existNegativeEdge() {
        for (int i = 0; i < this.numberOfVertex; i++) {
            for (int j = 0; j < this.vertexes.get(i).getEdges().size(); j++) {
                if (this.vertexes.get(i).getEdges().get(j).getWeight() < 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public void setDistanceInfinity() {
        for (int i = 0; i < this.numberOfVertex; i++) {
            this.vertexes.get(i).setDistance(Double.POSITIVE_INFINITY);
        }
    }

    public void Dikjstra(int init) {
        if (this.weighted) {
            if (!this.existNegativeEdge()) {
                this.setDistanceInfinity(); // Set all distances vertex to infinity
                this.searchVertex(init).setDistance(0); // Set distance of initial vertex to 0;
                int smallerDistance = 0;
                List<Vertex> vertexOpen = this.vertexes;
                int i = 0;
                int j = 0;
                double predecessorValue = 0.0;
                double weight = 0.0;
                
                while (vertexOpen.size() > 0) {
                    for (i = 0; i < vertexOpen.size(); i++) {
                        if (vertexOpen.get(i).getDistance() < smallerDistance) {
                            smallerDistance = i;
                        }
                    } // Get smaller distance
                    System.out.println("OIEE");
                    System.out.println(i);
                    System.out.println(smallerDistance);
                    for (j = smallerDistance; j < vertexOpen.get(i).getEdges().size(); j++) {
                        System.out.println("1");
                        predecessorValue = this.vertexes.get(i).getDistance();
                        System.out.println("2");
                        weight = this.vertexes.get(i).getEdges().get(j).getWeight();
                        System.out.println("3");
                        this.vertexes.get(i).getEdges().get(j).getSuccessor().setDistance(predecessorValue + weight);
                    }

                    vertexOpen.get(i).setVisited(true);
                    vertexOpen.remove(i);
                    i = 0;

                    System.out.println("oi");
                }
            } else {
                System.out.println("[ERROR]: The graph can't have edges with negative weights");
            }
        } else {
            System.out.println("[ERROR]: The graph isn't weighted");
        }
    }

    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<Edge>();

        for (int i = 0; i < this.numberOfVertex; i++) {
            for (int j = 0; j < this.vertexes.get(i).getEdges().size(); j++) {
                edges.add(this.vertexes.get(i).getEdges().get(j));
            }
        }

        return edges;
    }

    public void Kruskal() {
        if (this.weighted) {
            List<Edge> edges = this.getEdges(); // Get list of edges and order

        } else {
            System.out.println("[ERROR]: The graph isn't weighted");
        }
    }
}

public class GraphAdjacencyList {
    public static void main(String[] args) {
        // Params: (Directed, Weighted)
        Graph graph = new Graph(false, true);
        graph.connectNew(0, 1, 0.2);
        graph.connectNew(0, 4, 0.1);
        graph.connectNew(2, 4, 0.2);
        graph.connectNew(2, 0, 0.4);
        graph.connectNew(2, 3, 0.1);
        graph.connectNew(4, 1, 0.3);
        graph.connectNew(4, 5, 0.2);
        graph.connectNew(3, 4, 0.1);
        graph.connectNew(3, 5, 0.2);
        graph.connectNew(5, 1, 0.4);

        graph.print();
    }
}
import java.util.ArrayList;
import java.util.List;

class Edge <T, U> {
    private Vertex<T, U> next;
    private U weight;

    Edge() {
        this.next = null;
    }

    Edge(Vertex<T, U> destination, U weight) {
        this.weight = weight;
        this.next = destination;
    }

    Edge(Vertex<T, U> destination) {
        this.next = destination;
    }

    Edge(U weight) {
        this.weight = weight;
        this.next = null;
    }

    public U getWeight() {
        return this.weight;
    }

    public void setWeight(U weight) {
        this.weight = weight;
    }

    public Vertex<T, U> getNext() {
        return this.next;
    }

    public void setNext(Vertex<T, U> destination) {
        this.next = destination;
    } 
}

class Vertex <T, U> {
    private T label;
    private int degree;
    private boolean visited;
    private List<Edge<T, U>> edges;
    private Vertex<T, U> precedent;
    private U distance;

    Vertex (T label) {
        this.label = label;
        this.edges = new ArrayList<Edge<T, U>>();
        this.degree = 0;
        this.visited = false;
    }

    public T getLabel() {
        return this.label;
    }

    public void setLabel(T label) {
        this.label = label;
    }

    public List<Edge<T, U>> getEdges() {
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

class Graph <T, U> {
    private List<Vertex<T, U>> vertexes;
    private int numberOfVertex;
    private int numberOfEdges;
    private boolean directed;
    private boolean weighted;

    Graph(boolean directed, boolean weighted) {
        this.vertexes = new ArrayList<Vertex<T, U>>();
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

    public List<T> getLabels() {
        List<T> v = new ArrayList<T>();
        
        for (int i = 0; i < this.vertexes.size(); i++) {
            v.add(this.vertexes.get(i).getLabel());
        }

        return v;
    }

    public void createVertex(T v1) {
        if (!this.getLabels().contains(v1)) {
            Vertex<T, U> vertex = new Vertex<T, U>(v1);
            this.vertexes.add(vertex);
            numberOfVertex++;
        }
    }

    public void connect(T v1, T v2, U peso) {
        if (this.weighted) {
            // Search for vertexes, if they don't exist create them
            Vertex<T, U> aux1 = this.searchVertex(v1);
            Vertex<T, U> aux2 = this.searchVertex(v2);

            this.connectVertex(aux1, aux2, peso);
        } else {
            System.out.println("[ERROR]: Unweighted graph, it's not necessary to pass a weight");
        }
    }

    public void connect(T v1, T v2) {
        if (!this.weighted) {
            // Search for vertexes, if they don't exist create them
            Vertex<T, U> aux1 = this.searchVertex(v1);
            Vertex<T, U> aux2 = this.searchVertex(v2);

            this.connectVertex(aux1, aux2);
        } else {
            System.out.println("[ERROR]: Weighted graph, it's necessary to pass a weight!");
        }
    }

    public Vertex<T, U> searchVertex(T v1) {
        for (int i = 0; i < this.vertexes.size(); i++) {
            // If it finds vertex within the list, it just returns it
            if (this.vertexes.get(i).getLabel() == v1) {
                return this.vertexes.get(i);
            }
        }

        // If you can't find vertex, create it
        Vertex<T, U> vertex = new Vertex<T, U>(v1);
        this.vertexes.add(vertex);
        numberOfVertex++;

        return vertex;
    }

    public void connectVertex(Vertex<T, U> v1, Vertex<T, U> v2, U peso) {
        if (this.directed) {
            if (!this.existAdjacent(v1, v2, peso)) {
                v1.getEdges().add(new Edge<T, U>(v2, peso));
                v1.increaseDegree();
                this.numberOfEdges++;
            }
        } else {
            if (!this.existAdjacent(v1, v2, peso)) {
                v1.getEdges().add(new Edge<T, U>(v2, peso));
                v2.getEdges().add(new Edge<T, U>(v1, peso));
                v1.increaseDegree();
                this.numberOfEdges++;
            }
        }
    }

    public void connectVertex(Vertex<T, U> v1, Vertex<T, U> v2) {
        if (this.directed) {
            if (!this.existAdjacent(v1, v2)) {
                v1.getEdges().add(new Edge<T, U>(v2));
                v1.increaseDegree();
                this.numberOfEdges++;
            }
        } else {
            if (!this.existAdjacent(v1, v2)) {
                v1.getEdges().add(new Edge<T, U>(v2));
                v2.getEdges().add(new Edge<T, U>(v1));
                v1.increaseDegree();
                this.numberOfEdges++;
            }
        }
    }

    public boolean existAdjacent(Vertex<T, U> v1, Vertex<T, U> v2) {
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

    public boolean existAdjacent(Vertex<T, U> v1, Vertex<T, U> v2, U peso) {
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
        System.out.println("\n====== Graph ======\n");

        if (this.weighted == true) {
            for (Vertex<T, U> v: vertexes) {
                System.out.print("Vertex: " + v.getLabel() + " -> ");
                for (Edge<T, U> e: v.getEdges()) {
                    System.out.print("[" + e.getNext().getLabel() + ", " + e.getWeight() + "]   ");
                }
                System.out.println("\n");
            }
        } else {
            for (Vertex<T, U> v: vertexes) {
                System.out.print("Vertex: " + v.getLabel() + " -> ");
                for (Edge<T, U> e: v.getEdges()) {
                    System.out.print("[" + e.getNext().getLabel() + "]   ");
                }
                System.out.println("\n");
            }
        }

        System.out.println("====== End ======\n");
    }

    public void breadthFirstSearch(T start) {
        // Check if starting vertex exists
        if (this.getLabels().contains(start)) {
            List<Vertex<T, U>> fila = new ArrayList<Vertex<T, U>>();
            Vertex<T, U> initial = this.searchVertex(start);
            initial.setVisited(true); // Mark initial vertex as visited
            System.out.println(initial.getLabel());
            fila.add(initial);

            while(fila.size() > 0) {
                Vertex<T, U> currently = fila.get(0); // Get the first vertex in the queue

                for (int i = 0; i < currently.getEdges().size(); i++) {
                    Vertex<T, U> adjacent = currently.getEdges().get(i).getNext();

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

    public void depthFirstSearch(T start) {
        // Check if starting vertex exists
        if (this.getLabels().contains(start)) {
            Vertex<T, U> initial = this.searchVertex(start);
            initial.setVisited(true);
            System.out.println(initial.getLabel());
            
            for (int i = 0; i < initial.getEdges().size(); i++) {
                Vertex<T, U> actually = initial.getEdges().get(i).getNext();

                if (actually.getVisited() == false) {
                    depthFirstSearch(actually.getLabel());
                }
            }
        } else {
            System.out.println("[ERROR]: the vertex { " + start + " } doesn't exist.");
        }
    }

    public void depthFirstSearchQueue(T start) {
        if (this.getLabels().contains(start)) {
            List<Vertex<T, U>> pilha = new ArrayList<Vertex<T, U>>();
            Vertex<T, U> initial = this.searchVertex(start);
            pilha.add(initial);

            while (pilha.size() > 0) {
                Vertex<T, U> actually = pilha.remove(pilha.size() - 1);
                System.out.println(actually.getLabel());

                if (actually.getVisited() == false ) {
                    actually.setVisited(true); // Marcar como visitado

                    for (int i = 0; i < actually.getEdges().size(); i++) {
                        Vertex<T, U> adjacent = actually.getEdges().get(i).getNext();

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

    public boolean existNegativeEdge() {
        U edgeType = this.vertexes.get(0).getEdges().get(0).getWeight();
        
        for (int i = 0; i < this.numberOfVertex; i++) {
            for (int j = 0; j < this.vertexes.get(i).getEdges().size(); j++) {
                if (this.vertexes.get(i).getEdges().get(j).getWeight() < 0) {
                    return true;
                }
            }
        }
        

        return false;
    }

    public void Dikjstra(T init) {
        if (this.weighted) {
            U edgeType = this.vertexes.get(0).getEdges().get(0).getWeight();
            this.existNegativeEdge();
            if (edgeType.getClass() == Integer.class) {
                System.out.println("yep");
            } else {
                System.out.println("nop");
            }
        } else {
            System.out.println("[ERROR]: the graph isn't weighted");
        }
    }
}

public class GenericGraph {
    public static void main(String[] args) {
        // Params: (Directed, Weighted)
        GraphAdjacencyList<Integer, String> graph = new GraphAdjacencyList<Integer, String>(true, true);
        graph.connect(0, 1, "Mals");
        graph.connect(0, 4, "Whale");
        graph.connect(2, 4, "Balon");
        graph.connect(2, 0, "Alquer");
        graph.connect(2, 3, "Four");
        graph.connect(4, 1, "Ther");
        graph.connect(4, 5, "Qhuer");
        graph.connect(3, 4, "Xawl");
        graph.connect(3, 5, "Cosh");
        graph.connect(5, 1, "Pows");
        
        graph.Dikjstra(0);
    }
}
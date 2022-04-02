class Graph<T, G> {
    // private List<Vertex> vertexList;
    private boolean weighted;
    private boolean directed;

    Graph(boolean directed, boolean weighted) {
        this.directed = directed;
        this.weighted = weighted;
        //this.vertexList = new List<Vertex>();
    }

    // Inserir um vertice isolado
    public void add(T value) {

    }

    // Inserir dois vertices ou liga-los
    public void add(T value, T value) {

    }

    // Inserir dois vertices e colocar um peso entre eles
    public void add(T value, T value, G value) {

    }

    // 
}

public class GraphListaDeAdjacenciaGenerico {
    public static void main(String[] args) {
        // Params (Direcionado = boolean, Ponderado = boolean)
        Graph<Integer, Double> graph = new Graph<Integer, Double>(true, false);

        graph.add(2);
    }
}

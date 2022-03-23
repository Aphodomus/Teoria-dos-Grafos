class Grafo {
    private int numVertices;
    private int numArestas;
    private boolean direcionado;
    private boolean ponderado;
    private double[][] matriz;
    private boolean[][] matrizBooleano;

    public Grafo(int numVertices, boolean direcionado, boolean ponderado) {
        this.numVertices = numVertices;
        this.direcionado = direcionado;
        this.ponderado = ponderado;

        matriz = new double[numVertices][numVertices];
        matrizBooleano = new boolean[numVertices][numVertices];
    }

    // Nao ponderado
    public boolean inserir(int origem, int destino) {
        if (origem > numVertices || origem < 0 || destino > numVertices || destino < 0) {
            System.out.println("Nao foi possivel inserir");
            return false;
        }
        // Se o grafo nao for ponderado adicionar 1
        int valor = 1;

        // Se o grafo for ponderado adicionar 0
        if (this.ponderado == true) {
            valor = 0;
        }

        matriz[origem][destino] = valor;
        matrizBooleano[origem][destino] = true;
        this.numArestas++;

        // Se o grafo for nao direcionado, fazer a simetria na matriz
        if (this.direcionado == false) {
            matriz[destino][origem] = valor;
            matrizBooleano[destino][origem] = true;
            this.numArestas++;
        }

        return true;
    }

    // Ponderado
    public boolean inserir(int origem, int destino, double peso) {
        if (origem > numVertices || origem < 0 || destino > numVertices || destino < 0) {
            System.out.println("Nao foi possivel inserir");
            return false;
        }
        // Se o grafo for ponderado adicionar o peso
        double valor = peso;

        // Se o grafo nao for ponderado e for passado um peso, adicionar 1
        if (this.ponderado == false) {
            valor = 1;
        }

        matriz[origem][destino] = valor;
        matrizBooleano[origem][destino] = true;
        this.numArestas++;

        // Se o grafo for nao direcionado, fazer a simetria na matriz
        if (this.direcionado == false) {
            matriz[destino][origem] = valor;
            matrizBooleano[destino][origem] = true;
            this.numArestas++;
        }

        return true;
    }

    // Imprimir matriz
    public void printMatriz() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                // Printar apenas valores onde na matriz de booleanos for igual a true
                if (matrizBooleano[i][j] == true) {
                    System.out.print(String.valueOf(matriz[i][j]) + "    ");
                } else {
                    System.out.print("*" + "    ");
                }
            }
            System.out.println();
        }
    }

    // Imprimir grafo
    public void print() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print("Vertice " + i + " esta conectado a: ");
            for (int j = 0; j < numVertices; j++) {
                if (matrizBooleano[i][j] == true) {
                    System.out.print(j + " (" + matriz[i][j] + ") | ");
                }
            }
            System.out.println();
        }
    }

    // Verificar se existe uma aresta entre dois vertices (sao adjacentes)
    public boolean adjacentes(int origem, int destino) {
        return matrizBooleano[origem][destino];
    }

    // Pegar o valor da aresta entre dois vertices
    public Double getEdgeValue(int origem, int destino) {
        if (this.ponderado == false || matrizBooleano[origem][destino] == false) {
            return null;
        } else {
            return matriz[origem][destino];
        }
    }
}

public class GrafoMatrizDeAdjacencia {
    public static void main(String[] args) {
        // Construtor (numVertices, direcionado, ponderado)
        Grafo grafo = new Grafo(4, true, true);

        grafo.inserir(0, 1, 0.45);
        grafo.inserir(2, 3, 0.34);
        grafo.inserir(3, 1, 0.21);
        grafo.inserir(1, 3, 0.98);
        grafo.inserir(2, 1, 0.87);
        grafo.inserir(1, 3, 0.72);
        grafo.inserir(2, 2, 0.23);

        grafo.printMatriz();
        grafo.print();

        System.out.println("\n1 e adjacente a 2? " + grafo.adjacentes(1, 2));
        System.out.println("Valor entre a aresta [0, 1]: " + grafo.getEdgeValue(0, 1));
        System.out.println("Valor entre a aresta [1, 0]: " + grafo.getEdgeValue(1, 0));

    }
}
import java.util.ArrayList;
import java.util.List;

class Aresta {
    private Vertice next;
    private float weight;

    Aresta() {
        this.next = null;
        this.weight = 0;
    }

    Aresta(Vertice dest, float weight) {
        this.weight = weight;
        this.next = dest;
    }

    Aresta(Vertice dest) {
        this.next = dest;
        this.weight = 1;
    }

    Aresta(float weight) {
        this.weight = weight;
        this.next = null;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Vertice getNext() {
        return this.next;
    }

    public void setNext(Vertice dest) {
        this.next = dest;
    } 
}

class Vertice {
    private int label;
    private int grau;
    private List<Aresta> arestas;

    Vertice (int label) {
        this.label = label;
        this.arestas = new ArrayList<Aresta>();
    }

    public int getLabel() {
        return this.label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public List<Aresta> getEdges() {
        return this.arestas;
    }

    public void increaseDegree() {
        this.grau++;
    }

    public int getDegree() {
        return this.grau;
    }
}

class Grafo {
    private List<Vertice> vertices;
    private int numVertices;
    private int numArestas;

    Grafo() {
        this.vertices = new ArrayList<Vertice>();
        this.numVertices = 0;
        this.numArestas = 0;
    }

    public void inserir(int v1, int v2, float peso) {
        Vertice vertice1 = new Vertice(v1);
        Vertice vertice2 = new Vertice(v2);

        // Verificar se vertice1 já existe no grafo, se não existir criar
        if (vertices.contains(vertice1) == false) {
            this.vertices.add(vertice1);
            this.numVertices++;
        }

        // Verificar se vertice2 já existe no grafo, se não existir criar
        if (vertices.contains(vertice2) == false) {
            this.vertices.add(vertice2);
            this.numVertices++;
        }

        // Obrigatoriamente pegar os vertices da lista para fazer a ligação entre eles
        Vertice aux1 = this.vertices.get(vertices.indexOf(vertice1));
        Vertice aux2 = this.vertices.get(vertices.indexOf(vertice2));

        this.ligarArestas(aux1, aux2, peso);
    }

    public void ligarArestas(Vertice v1, Vertice v2, float peso) {
        v1.getEdges().add(new Aresta(v2, peso));
        v1.increaseDegree();
        this.numArestas++;
    }
    
    public void print() {
        for (Vertice v: vertices) {
            System.out.print("Vertice: " + v.getLabel() + "\n");
            for (Aresta e: v.getEdges()) {
                System.out.print("Aponta para: " + e.getNext().getLabel() + ", Peso: " + e.getWeight() + "\n");
            }
            System.out.print("================================\n");
            System.out.print("\n");
        }
    }
}

public class ListaDeAdjacencia {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
    
        grafo.inserir(0, 1, 0.45f);
    
        grafo.print();
    }
}
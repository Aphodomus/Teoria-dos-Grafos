import java.util.ArrayList;
import java.util.List;

class Aresta {
    private Vertice next;
    private double weight;

    Aresta() {
        this.next = null;
        this.weight = 0;
    }

    Aresta(Vertice dest, double weight) {
        this.weight = weight;
        this.next = dest;
    }

    Aresta(Vertice dest) {
        this.next = dest;
        this.weight = 1;
    }

    Aresta(double weight) {
        this.weight = weight;
        this.next = null;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
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
    private boolean visitado;
    private List<Aresta> arestas;

    Vertice (int label) {
        this.label = label;
        this.arestas = new ArrayList<Aresta>();
        this.grau = 0;
        this.visitado = false;
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

    public boolean getVisited() {
        return this.visitado;
    }

    public void setVisited(boolean visited) {
        this.visitado = visited;
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

    public int getNumVertices() {
        return this.numVertices;
    }

    public int getNumArestas() {
        return this.numArestas;
    }

    public void inserir(int v1, int v2, double peso) {
        // Verificar se vertice1 já existe no grafo, se não existir criar
        Vertice aux1 = this.procurarVertice(v1, peso);

        // Verificar se vertice2 já existe no grafo, se não existir criar
        Vertice aux2 = this.procurarVertice(v2, peso);

        // Obrigatoriamente pegar os vertices da lista para fazer a ligação entre eles
        this.ligarArestas(aux1, aux2, peso);
    }

    public Vertice procurarVertice(int v1, double peso) {
        for (int i = 0; i < this.vertices.size(); i++) {
            // Se ele encontrar o vertice dentro da lista, apenas retorna-lo
            if (this.vertices.get(i).getLabel() == v1) {
                return this.vertices.get(i);
            }
        }

        // Se percorrer toda a lista e nao achar o vertice, cria-lo
        Vertice vertice1 = new Vertice(v1);
        this.vertices.add(vertice1);
        numVertices++;

        return vertice1;
    }

    public void ligarArestas(Vertice v1, Vertice v2, double peso) {
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

    public void breadthFirstSearch(int start) {
        List<Vertice> fila = new ArrayList<Vertice>();
        Vertice initial = this.vertices.get(start);
        initial.setVisited(true); // Marcar o vertice inicial como visitado
        System.out.println(initial.getLabel());
        fila.add(initial);

        while(fila.size() > 0) {
            Vertice currently = fila.get(0); // Pegar o primeiro vertice da fila

            for (int i = 0; i < currently.getEdges().size(); i++) {
                Vertice adjacent = currently.getEdges().get(i).getNext();

                // Se o vertice não foi visitado, marca-lo
                if (adjacent.getVisited() == false) {
                    System.out.println(adjacent.getLabel());
                    adjacent.setVisited(true);
                    fila.add(adjacent); // Adicionar o vertice a minha lista para visita-lo depois
                }

            }
            fila.remove(0);
        }
    }

    public void depthFirstSearch(int start) {
        
    }
}

public class ListaDeAdjacencia {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
    
        grafo.inserir(0, 1, 0.5);
        grafo.inserir(0, 2, 0.3);
        grafo.inserir(1, 3, 0.2);
        grafo.inserir(2, 3, 0.1);
        grafo.inserir(4, 2, 0.6);
        grafo.inserir(4, 3, 0.7);
        grafo.breadthFirstSearch(4);
    }
} 
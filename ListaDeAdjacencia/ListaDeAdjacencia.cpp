#include <iostream>
#include <iterator>
#include <list>

typedef int TypeWeightEdge;
typedef int TypeWeightVertex;

typedef struct Aresta {
    TypeWeightEdge peso;
    struct Vertice *prox;
} Aresta;

typedef struct Vertice {
    TypeWeightVertex valor;
    int grau;
    std::list<Aresta> *arestas;
} Vertice;

typedef struct Grafo {
    bool ponderado;
    int numVertices;
    int numArestas;
    std::list<Vertice> *adj;
} Grafo;

// Exemplo: inserirAresta(2, 3)
void inserirAresta(TypeWeightVertex v1, TypeWeightVertex v2) {
    // Se o primeiro vertice não existir, criar ele
    if (Grafo.Vertices(v1) == false) {
        // Criando o vertice e inserindo ele dentro do grafo
        Grafo.Inserir(criarVertice(v1));
    } else if (Grafo.Vertices(v2) == false) { // Se o segundo vertice não existir, criar ele
        // Criando o vertice e inserindo ele dentro do grafo
        Grafo.Inserir(criarVertice(v2));
    }

    // Bsucar o primeiro vertice dentro do grafo e retorna-lo para depois fazer a ligacao entre eles
    Vertice tmp1 = Grafo.BuscarVertice(v1);
    Vertice tmp2 = Grafo.Buscarvertice(v2);

    // Se o primeiro vertice nao estiver ligado ao segundo vertice eles podem fazer uma ligação, TESTAR O PESO DA ARESTA
    if (tmp1.buscarArestas(v2) == true) { 
        tmp1.prox = tmp2;
    }
}

bool BuscarArestas(TypeWeightEdge v1) {
    // Se o vertice já estiver apontando para o outro
    if (this.peso != v1.peso) { // Se o peso dos vertices forem diferentes entao uma nova aresta pode ser criada para liga-los 
        return true;
    }

    return false;
}

Vertice BuscarVertice(TypeWeightVertex v1) {
    // Buscar na lista de vertices o vertice desejado e retorna-lo
    return Grafo.adj.search(v1);
}

void criarVertice(TypeWeightVertex v1) {
    Vertice v1 = new Vertice(v1);
    Grafo.adj.push(v1);
}


/*
Grafo
-> Lista de Vertices
-> ponderado
-> numVertices
-> numArestas

Vertices
-> Lista de Arestas
-> valor
-> grau

Arestas
-> aponta para um vertice
-> peso

*/
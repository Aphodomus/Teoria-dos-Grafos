/* 

===== Lista de adjacencia =====

- As buscas sao mais rapidas com as listas de adjacencia
- O espaço ocupado por uma lista de adjacencia e proporcional ao tamanho do grafo (V + A).
- Para grafos esparsos, uma boa representacao e a lista de adjacencia.
- Operacoes que tenham como base um caminho de um vertice a outro (buscas).

- Grafo denso: possui muitas arestas em relacao ao numero de vertices
- Grafo esparso: possui poucas arestas em relacao ao numero de vertices

*/

#include <stdio.h>
#include <stdlib.h>
typedef int TypeWeight;

// No para armazenar o vertice e um apontador
typedef struct Node {
    TypeWeight vertice;
    struct Node *prox, *ant;
} Node;

// Criar um novo no, recebendo como parametro um no e para onde ele vai apontar
Node* new_node(TypeWeight v1) {
    Node *tmp = (Node*) malloc(sizeof(Node));
    tmp->vertice = v1;
    tmp->prox = NULL;
    tmp->ant = NULL;

    return tmp;
}

// Estrutura do grafo
typedef struct Grafo {
    int numVertice;
    int numArestas;
    Node **adj;
} Grafo;

// Criar lista de adjacencia
Node** criar_lista(Grafo *grafo, int vertices) {
    grafo->adj = (Node**) malloc(vertices*sizeof(Node*));

    for (int i = 0; i < vertices; i++) {
        grafo->adj[i] = new_node(i);
    }

    return grafo->adj;
}

// Construtor do grafo, apenas vertices sem ligação entre eles (sem arestas = 0)
Grafo* new_grafo(int vertices) {
    Grafo *tmp = (Grafo*) malloc(sizeof(Grafo));
    tmp->numVertice = vertices;
    tmp->numArestas = 0;
    tmp->adj = criar_lista(tmp, vertices);

    return tmp;
}

// Inserir uma aresta, colocar uma aresta entre 2 vertices
void inserirAresta(Grafo *grafo, TypeWeight v1, TypeWeight v2) {
    Node *newNode = new_node(v2);

    Node *i;
    for (i = grafo->adj[v1]; i->prox != NULL; i = i->prox);
    i->prox = newNode;
    i->prox->ant = i;
    i = i->prox;

    grafo->numArestas++;
}

// Remover uma aresta entre dois vertices
void removerAresta(Grafo *grafo, TypeWeight v1, TypeWeight v2) {
    // Remover tanto do comeco quanto do meio
    Node *i;
    for (i = grafo->adj[v1]; i->prox != NULL; i = i->prox) {
        if (i->vertice == v2) {
            i->ant->prox = i->prox;
            i->prox->ant = i->ant;
            i->prox = NULL;
            i->ant = NULL;
            grafo->numArestas--;
            return;
        }
    }

    // Caso o elemento a ser retirado esteja na ultima posicao
    if (i->vertice == v2) {
        i->ant->prox = NULL;
        i->ant = NULL;
        grafo->numArestas--;
        return;
    }
}

// Mostrar grafo, todos os seus vertices adjacentes
void mostrarGrafo(Grafo *grafo) {
    for (int i = 0; i < grafo->numVertice; i++) {
        printf("%d: ", grafo->adj[i]->vertice);
        for (Node *j = grafo->adj[i]->prox; j != NULL; j = j->prox) {
            printf("%d  ", j->vertice);
        }
        printf("\n");
    }
    printf("\n");
}

int main() {
    Grafo *grafo = new_grafo(10);
    inserirAresta(grafo, 1, 2);
    inserirAresta(grafo, 2, 6);
    inserirAresta(grafo, 6, 4);
    inserirAresta(grafo, 3, 8);
    inserirAresta(grafo, 8, 1);
    inserirAresta(grafo, 6, 0);
    inserirAresta(grafo, 9, 4);
    inserirAresta(grafo, 7, 5);
    inserirAresta(grafo, 9, 1);
    inserirAresta(grafo, 9, 5);
    mostrarGrafo(grafo);
    removerAresta(grafo, 6, 0);
    mostrarGrafo(grafo);
}
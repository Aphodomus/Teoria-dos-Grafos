/* 

===== Matriz de adjacencia =====

- O espaço ocupado por uma matriz e proporcional ao numero de vertices^2.
- Para grafos densos, uma boa representacao e a matriz de adjacencia.
- Testas a existencia de uma aresta entre dois vertices e mais rápido na matriz de adjacencia
- Encontrar os predecessores de um vertice e mais rapido na matriz de adjacencia, basta ver a coluna


- Grafo denso: possui muitas arestas em relacao ao numero de vertices
- Grafo esparso: possui poucas arestas em relacao ao numero de vertices

*/

#include <stdio.h>
#include <stdlib.h>
typedef int TypeWeight;

// Estrutura do grafo
typedef struct Grafo {
    int numVertices;
    int numArestas;
    TypeWeight **adj; // Ponteiro para matriz de adjacencia do grafo
} Grafo;

// Criar uma matriz de adjacencia
TypeWeight** criar_matriz(int linhas, int colunas, int valor) {
    TypeWeight **tmp = (TypeWeight**) malloc(linhas*(sizeof(TypeWeight*)));

    for (int i = 0; i < linhas; i++) {
        tmp[i] = (TypeWeight*) malloc(colunas*sizeof(TypeWeight));
    }

    for (int i = 0; i < linhas; i++) {
        for (int j = 0; j < colunas; j++) {
            tmp[i][j] = valor;
        }
    }

    return tmp;
}

// Construtor do grafo, apenas vertices sem ligação entre eles (sem arestas = 0)
Grafo* new_grafo(int vertices) {
    Grafo *tmp = (Grafo*) malloc(sizeof(Grafo));
    tmp->numVertices = vertices;
    tmp->numArestas = 0;
    tmp->adj = criar_matriz(vertices, vertices, 0); // Inicialmente todo valor da matriz e 0

    return tmp;
}

// Inserir uma aresta, colocar uma aresta entre 2 vertices
void inserirAresta(Grafo *grafo, TypeWeight v1, TypeWeight v2) {
    if (grafo->adj[v1][v2] == 0) { // Não tem ligação entre eles, pode fazer
        grafo->adj[v1][v2] = 1;
        grafo->numArestas++;
    } else {
        printf("\nJa exite uma ligação entre as arestas %d e %d.\n", v1, v2);
    }
}

// Remover uma aresta, remover uma aresta entre 2 vertices
void removerAresta(Grafo *grafo, TypeWeight v1, TypeWeight v2) {
    if (grafo->adj[v1][v2] == 1) {
        grafo->adj[v1][v2] = 0;
        grafo->numArestas--;
    } else {
        printf("\nNao exite uma ligação entre as arestas %d e %d.\n\n", v1, v2);
    }
}

// Mostrar grafo, todos os seus vertices adjacentes
void mostrarGrafo(Grafo *grafo) {
    printf(" V:    Aponta para\n");
    printf("=====================\n\n");
    for (int i = 0; i < grafo->numVertices; i++) {
        printf("%2d:", i);
        for (int j = 0; j < grafo->numVertices; j++) {
            if (grafo->adj[i][j] == 1) {
                printf(" %2d", j);
            }
        }
        printf("\n");
    }
    printf("\n");
}

void mostrarMatriz(Grafo *grafo) {
    for (int i = 0; i < grafo->numVertices; i++) {
        for (int j = 0; j < grafo->numVertices; j++) {
            printf("%2d  ", grafo->adj[i][j]);
        }
        printf("\n");
    }
}

int main() {
    Grafo *grafo = (Grafo*) malloc(sizeof(Grafo));
    grafo = new_grafo(10);
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
    mostrarMatriz(grafo);
}
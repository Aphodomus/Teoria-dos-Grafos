#include <iostream>
#include "../datastructures/ListaSimples.h"

class Aresta {
    public:
        int weight;
        Vertice *aponta;

        Aresta() {
            this->weight = 0;
            this->aponta = nullptr;
        }

        Aresta(int weight) {
            this->weight = weight;
            this->aponta = nullptr;
        }

        Aresta(int weight, Vertice *v1) {
            this->weight = weight;
            this->aponta = v1;
        }

        int getWeight () {
            return this->weight;
        }

        void setWeight(int weight) {
            this->weight = weight;
        }

        Vertice* getAponta() {
            return this->aponta;
        }

        void setAponta(Vertice *v1) {
            this->aponta = v1;
        }
};

class Vertice {
    public:
        int label;
        int grau;
        ListaSimples<Aresta> *arestas;

        Vertice() {
            this->label = 0;
            this->grau = 0;
            this->arestas = new ListaSimples<Aresta>();
        }

        Vertice(int label) {
            this->label = label;
            this->grau = 0;
            this->arestas = new ListaSimples<Aresta>();
        }

        int getLabel() {
            return this->label;
        }

        void setLabel(int label) {
            this->label = label;
        }
};

class Grafo {
    public:
        ListaSimples<Vertice> *vertices;
        int numVertices;
        int numArestas;

        Grafo() {
            this->vertices = new ListaSimples<Vertice>();
            this->numVertices = 0;
            this->numArestas = 0;
        }

        void inserirVerticeIsolado()  {
            // Verificar se o vertice ja existe
        }
        Vertice procurarVertice(int v1) {
            for (int i = 0; i < this->numVertices; i++) {
                if (this->vertices->get(i).getLabel() == v1) {
                    return this->vertices->get(i);
                }
            }
        }
};

int main() {
    Grafo grafo = Grafo();
    grafo.inserirVerticeIsolado();


    return 0;
}
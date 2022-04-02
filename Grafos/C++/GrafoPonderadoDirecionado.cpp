#include <iostream>
#include "List.h"

using namespace std;

class Aresta {
    public:
        Vertice* next;
        double weight;

        Aresta() {
            this->next = nullptr;
            this->weight = 0.0;
        }

        Aresta(Vertice* dest, double weight) {
            this->weight = weight;
            this->next = dest;
        }

        Aresta(Vertice* dest) {
            this->next = dest;
            this->weight = 0;
        }

        double getWeight() {
            return this->weight;
        }

        void setWeight(double weight) {
            this->weight = weight;
        }

        Vertice* getNext() {
            return this->next;
        }

        void setNext(Vertice *dest) {
            this->next = dest;
        }
};

class Vertice {
    public:
        int label;
        int grau;
        bool visitado;
        List<Aresta>* arestas;

        Vertice(int label) {
            this->label = label;
            this->grau = 0;
            this->visitado = false;
            this->arestas = new List<Aresta>();
        }

        int getLabel() {
            return this->label;
        }

        void setLabel(int label) {
            this->label = label;
        }

        List<Aresta>* getEdges() {
            return this->arestas;
        }

        void increaseDegree() {
            this->grau++;
        }

        int getDegree() {
            return this->grau;
        }

        bool getVisited() {
            return this->visitado;
        }

        void setVisitado(bool visited) {
            this->visitado = visited;
        }
};

class Grafo {
    public:
        List<Vertice>* vertices;
        int numVertices;
        int numArestas;

        Grafo() {
            this->vertices = new List<Vertice>();
            this->numArestas = 0;
            this->numVertices = 0;
        }

        int getNumVertices() {
            return this->numVertices;
        }

        int getNumArestas() {
            return this->numArestas;
        }

        List<int> getLabels() {
            List<int> v = List<int>();
            int value = 0;

            for (int i = 0; i > this->vertices->length(); i++) {
                v.add(this->vertices->get(i).getLabel());
            }

            return v;
        }

        Vertice procurarVertice(int v1) {
            for (int i = 0; i < this->vertices->length(); i++) {
                // Se encontrar o vertice dentro da lista, apenas retorna-lo
                if (this->vertices->get(i).getLabel() == v1) {
                    return this->vertices->get(i);
                }
            }

            // Se percorrer toda a lista e nao achar o vertice, cria-lo
            Vertice* v2 = new Vertice(v1);
            this->vertices->add(*v2);
            numVertices++;

            return *v2;
        }

        void ligarVertices(int v1, int v2, double peso) {
            // Verificar se vertice1 já existe no grafo, se não existir criar
            Vertice aux1 = this->procurarVertice(v1);

            // Verificar se vertice2 já existe no grafo, se não existir criar
            Vertice aux2 = this->procurarVertice(v2);

            // Obrigatoriamente pegar os vertices da lista para fazer a ligação entre eles
            this->ligarArestas(aux1, aux2, peso);
        }

        void ligarArestas(Vertice v1, Vertice v2, double weight) {
            Aresta* aux = new Aresta(v2, weight);
            v1.getEdges()->add(*aux);
            v1.increaseDegree();
            numArestas++;
        }

};
#include <iostream>
#include "../datastructures/ListaSimples.h"

// Vertice que contem uma lista de inteiros (vertices que ele esta ligado)
class VerticeInt {
    public:
        int label;
        int grau;
        ListaSimples<int> *lista;

        VerticeInt() {
            this->grau = 0;
            this->lista = new ListaSimples<int>();
        }

        VerticeInt(int label) {
            this->label = label;
            this->grau = 0;
            this->lista = new ListaSimples<int>();
        }

        int getLabel() {
            return this->label;
        }

        int getGrau() {
            return this->grau;
        }

        void print() {
            lista->print();
        }
};

// Estrutura principal que vai conter o grafo e todas as suas operações
class Grafo {
    public:
        ListaSimples<VerticeInt> *vertices;
        int numVertices;
        int numArestas;

        Grafo() {
            this->vertices = new ListaSimples<VerticeInt>();
            this->numArestas = 0;
            this->numVertices = 0;
        }
        
        void inserirVerticeIsolado(int v1) {
            // Verificar se vertice v1 existe, se nao cria-lo e inserir no grafo (vertice isolado)
            this->procurarVertice(v1);
        }

        void conectarVertices(int v1, int v2) {
            // Verificar se vertice v1 existe, se nao cria-lo e inserir no grafo
            VerticeInt aux1 = this->procurarVertice(v1);

            // Verificar se vertice v2 existe, se nao cria-lo e inserir no grafo
            VerticeInt aux2 = this->procurarVertice(v2);

            // Ligar os vertices
            this->ligarVertices(aux1, aux2);
        }

        VerticeInt procurarVertice(int v1) {
            // Procurar o vertice na minha lista de vertices
            for (int i = 0; i < this->numVertices; i++) {
                // Se o vertice ja existir, apenas retornar ele
                if (this->vertices->get(i).getLabel() == v1) {
                    return this->vertices->get(i);
                }
            }

            // Se o vertice nao existir, criar ele
            VerticeInt vertice = VerticeInt(v1);
            this->vertices->inserir(vertice);
            numVertices++;

            return vertice;
        }

        void ligarVertices(VerticeInt v1, VerticeInt v2) {
            // Vertice v1 vai apontar para o vertice v2
            v1.lista->inserir(v2.getLabel());
        }

        void print() {
            for (int i = 0; i < this->numVertices; i++) {
                cout << "Vertice: " << this->vertices->get(i).getLabel() << endl;
                this->vertices->get(i).print();
            }
        }
};

int main() {
    Grafo grafo = Grafo();
    grafo.inserirVerticeIsolado(5);
    grafo.conectarVertices(1, 2);
    grafo.conectarVertices(1, 4);
    grafo.conectarVertices(4, 3);
    grafo.conectarVertices(5, 6);
    grafo.print();

    return 0;
}
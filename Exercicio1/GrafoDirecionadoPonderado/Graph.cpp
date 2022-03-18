#include <iostream>
#include "../datastructures/ListaSimples.h"
#include "../datastructures/Vertice.h"
#include "../datastructures/Aresta.h"

using namespace std;

// Estrutura principal que vai conter o grafo e todas as suas operações
class Grafo {
    public:
        // Atributos
         ListaSimples<Vertice> *vertices;
         int numVertices;
         int numArestas;

         // Metodos especiais
         Grafo() {
             this->vertices = new ListaSimples<Vertice>();
             this->numVertices = 0;
             this->numArestas = 0;
         }

        int getNumVertices() {
            return this->numVertices;
        }  
        int getNumArestas() {
            return this->numArestas;
        }

        // Metodos e funcoes
        // Retorna todos os labels dos vertices pertencentes a este grafo
        ListaSimples<int> getLabels() {
            ListaSimples<int> v = ListaSimples<int>();
            int value = 0;

            for (int i = 0; i > this->vertices->getSize(); i++) {
                v.inserir(this->vertices->get(i).getLabel());
            }

            return v;
        }

        // Dado dois valores, verificar se existem vertices com esses valores, se não cria-los e liga-los
        void ligarVertices(int v1, int v2, double peso) {
            // Verificar se vertice1 já existe no grafo, se não existir criar
            Vertice aux1 = this->procurarVertice(v1);

            // Verificar se vertice2 já existe no grafo, se não existir criar
            Vertice aux2 = this->procurarVertice(v2);

            // Obrigatoriamente pegar os vertices da lista para fazer a ligação entre eles
            this->ligarArestas(aux1, aux2, peso);
        }

        // Procurar o vertice
        Vertice procurarVertice(int v1) {
            for (int i = 0; i < this->vertices->getSize(); i++) {
                // Se encontrar o vertice dentro da lista, apenas retorna-lo
                if (this->vertices->get(i).getLabel() == v1) {
                    return this->vertices->get(i);
                }
            }

            // Se percorrer toda a lista e nao achar o vertice, cria-lo
            Vertice v2 = Vertice(v1);
            this->vertices->inserir(v2);
            numVertices++;

            return v2;
        }

        // Dado um vertice v1, fazer uma ligacao com v2 e com um peso da aresta entre eles (weight)
        void ligarArestas(Vertice v1, Vertice v2, double weight) {
            Aresta aux = Aresta(&v2, weight);
            v1.getEdges()->inserir(aux);
            v1.increaseDegree();
            numArestas++;
        }
};

int main() {
    Grafo grafo = Grafo();
    grafo.ligarVertices(0, 1, 0.2);
    grafo.ligarVertices(0, 4, 0.1);
    grafo.ligarVertices(2, 0, 0.4);
    grafo.ligarVertices(2, 4, 0.2);
    grafo.ligarVertices(2, 3, 0.1);
    grafo.ligarVertices(3, 4, 0.1);
    grafo.ligarVertices(3, 5, 0.2);
    grafo.ligarVertices(4, 1, 0.3);
    grafo.ligarVertices(4, 5, 0.2);
    grafo.ligarVertices(5, 1, 0.4);

    return 0;
};
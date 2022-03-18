#include <iostream>
#include "ListaSimples.h"
#include "Vertice.h"
#include "Aresta.h"

using namespace std;

class Grafo {
    public:
        // Atributos
         ListaDupla<Vertice> *vertices;
         int numVertices;
         int numArestas;

         // Metodos especiais
         Grafo() {
             this->vertices = new ListaDupla<Vertice>();
             this->numVertices = 0;
             this->numArestas = 0;
         }

        int getNumVertices() {
            return this->numVertices;
        }  
        int getNumArestas() {
            return this->numArestas;
        }

        ListaDupla<int> getLabels() {
            ListaDupla<int> v = ListaDupla<int>();
            int value = 0;

            for (int i = 0; i > this->vertices->getSize(); i++) {
                v.inserir(this->vertices->get(i).getLabel());
            }

            return v;
        }

        Vertice procurarVertice(int v1) {
            for (int i = 0; i < this->vertices->getSize(); i++) {
                // Se ele encontrar o vertice dentro da lista, apenas retorna-lo
                if (this->vertices->get(i).getLabel() == v1) {
                    return this->vertices->get(i);
                }
            }

            // Se percorrer toda a lista e nao achar o vertice, cria-lo
            Vertice vertice1 = Vertice(v1);
            this->vertices->inserir(vertice1);
            numVertices++;

            return vertice1;
        }

        void ligarVertices(int v1, int v2, double peso) {
            // Verificar se vertice1 já existe no grafo, se não existir criar
            Vertice aux1 = this->procurarVertice(v1);

            // Verificar se vertice2 já existe no grafo, se não existir criar
            Vertice aux2 = this->procurarVertice(v2);

            // Obrigatoriamente pegar os vertices da lista para fazer a ligação entre eles
            this->ligarArestas(aux1, aux2, peso);
        }

        void ligarArestas(Vertice v1, Vertice v2, double peso) {
            v1.getEdges()->inserir();
        }
};

int main() {
    Grafo grafo = Grafo();
    grafo.ligarVertices(3, 2, 0.45);
    
    return 0;
};
#ifndef VERTICE_H
#define VERTICE_H

#include <iostream>
#include "ListaSimples.h"
#include "Aresta.h"

class Aresta;

class Vertice {
    public:
        // Atributos
        int label;
        int grau;
        bool visitado;
        ListaSimples<Aresta> *arestas;

        // Metodos especiais
        Vertice() {
            this->label = 0;
            this->arestas = new ListaSimples<Aresta>();
            this->grau = 0;
            this->visitado = false;
        }

        Vertice(int label) {
            this->label = label;
            this->arestas = new ListaSimples<Aresta>();
            this->grau = 0;
            this->visitado = false;
        }

        int getLabel() {
            return this->label;
        }

        void setLabel(int label) {
            this->label = label;
        }

        ListaSimples<Aresta>* getEdges() {
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

        void printArestas() {
            for (int i = 0; i < arestas->getSize(); i++) {
                cout << "Aponta para: " << arestas->get(i).getNext()->getLabel();
            }
        }
};

#endif
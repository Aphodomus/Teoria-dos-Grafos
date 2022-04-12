#ifndef VERTICE_H
#define VERTICE_H

#include <iostream>
#include "List.h"
#include "Aresta.h"

class Aresta;

class Vertice {
    public:
        // Atributos
        int label;
        int grau;
        bool visitado;
        List<Aresta> *arestas;

        // Metodos especiais
        Vertice() {
            this->label = 0;
            this->arestas = new List<Aresta>();
            this->grau = 0;
            this->visitado = false;
        }

        Vertice(int label) {
            this->label = label;
            this->arestas = new List<Aresta>();
            this->grau = 0;
            this->visitado = false;
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

        bool operator==(const Vertice &vertice) const {
            return vertice.label == this->label;
        }
};

#endif
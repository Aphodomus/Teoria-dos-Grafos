#ifndef ARESTA_H
#define ARESTA_H

#include <iostream>
#include "Vertice.h"

class Vertice;

class Aresta {
    public:
        // Atributos
        Vertice *next;
        double weight;

        // Metodos especiais
        Aresta() {
            this->next = nullptr;
            this->weight = 0;
        }

        Aresta(Vertice *dest, double weight) {
            this->weight = weight;
            this->next = dest;
        }

        Aresta(Vertice *dest) {
            this->next = dest;
            this->weight = 1;
        }

        Aresta(double weight) {
            this->weight = weight;
            this->next = nullptr;
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

        bool operator==(const Aresta &aresta) const {
            return aresta.weight == this->weight;
        }
};

#endif
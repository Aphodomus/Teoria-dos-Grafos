#ifndef CELULA_DUPLA_H
#define CELULA_DUPLA_H

#include <iostream>
using namespace std;

template <class T>
class CelulaDupla {
    public:
        // Atributos
        T value;
        CelulaDupla *prox;
        CelulaDupla *ant;

        // Metodos especiais
        CelulaDupla() {
            this->prox = nullptr;
            this->ant = nullptr;
        }

        CelulaDupla(T value) {
            this->value = value;
            this->prox = nullptr;
            this->ant = nullptr;
        }

        CelulaDupla(T value, CelulaDupla prox, CelulaDupla ant) {
            this->value = value;
            this->prox = prox;
            this->ant = ant;
        }
};

#endif
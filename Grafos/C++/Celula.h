#ifndef CELULA_H
#define CELULA_H

#include <iostream>
using namespace std;

template <typename T>
class Celula {
    public:
        // Atributos
        T value;
        Celula *prox;

        // Metodos especiais
        Celula() {
            this->prox = nullptr;
        }

        Celula(T value) {
            this->value = value;
            this->prox = nullptr;
        }

        Celula(T value, Celula prox) {
            this->value = value;
            this->prox = prox;
        }
};

#endif
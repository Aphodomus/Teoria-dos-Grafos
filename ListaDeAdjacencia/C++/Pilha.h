#ifndef PILHA_H
#define PILHA_H

#include <iostream>
#include "Celula.h"
using namespace std;

template <class T>
class Pilha {
    public:
        // Atributos
        int size;
        Celula<T> *topo;

        // Metodos especiais
        Pilha() {
            this->topo = nullptr;
        }

        int getSize() {
            return this->size;
        }

        // Metodos e funcoes
        void push(T value) {
            Celula<T> *tmp = new Celula<T>(value);
            tmp->prox = this->topo;
            this->topo = tmp;

            tmp = nullptr;

            this->size++;
        }

        T pop() {
            if (this->topo == nullptr) {
                cout << "Erro: A pilha esta vazia" << endl;
            }

            T result = this->topo->value;
            Celula<T> *tmp = this->topo;
            this->topo = this->topo->prox;

            tmp->prox = nullptr;
            tmp = nullptr;

            this->size--;
            return result;
        }

        void print() {
            for (Celula<T> *i = this->topo; i != nullptr; i = i->prox) {
                cout << i->value << endl;
            }
            cout << endl;
        }

        bool empty() {
            if (this->topo == nullptr) {
                return true;
            } else {
                return false;
            }
        }

        bool search(T value) {
            for (Celula<T> *i = this->topo; i != nullptr; i = i->prox) {
                if (i->value == value) {
                    return true;
                }
            }

            return false;
        }

        T peek() {
            T result;

            if (this->topo == nullptr) {
                cout << "Erro: A pilha esta vazia" << endl;
                return result;
            } else {
                result = this->topo->value;
                return result;
            }
        }


};

#endif
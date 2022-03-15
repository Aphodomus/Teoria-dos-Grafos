#ifndef FILA_H
#define FILA_H

#include <iostream>
#include "Celula.h"
using namespace std;

template <class T>
class Fila { 
    public:
        // Atributos
        int size;
        Celula<T> *primeiro;
        Celula<T> *ultimo;

        // Metodos especiais
        Fila() {
            this->primeiro = new Celula<T>();
            this->ultimo = this->primeiro;
        }

        // Metodos e funcoes
        void enqueue(T value) {
            this->ultimo->prox = new Celula<T>(value);
            this->ultimo = this->ultimo->prox;

            this->size++;
        }

        T dequeue() {
            if (this->primeiro == this->ultimo) {
                cout << "Erro: A fila esta vazia" << endl;
            }

            Celula<T> *tmp = this->primeiro;
            this->primeiro = tmp->prox;
            T result = tmp->value;

            tmp->prox = nullptr;
            tmp = nullptr;

            this->size--;
            return result;
        }

        void print() {
            for (Celula<T> *i = this->primeiro->prox; i != nullptr; i = i->prox) {
                cout << i->value << endl;
            }
            cout << endl;
        }

        T peek() {
            T result;

            if (this->primeiro == this->ultimo) {
                cout << "Erro: A fila esta vazia" << endl;
                return result;
            } else {
                result = this->primeiro->value;
                return result;
            }
        }
};

#endif
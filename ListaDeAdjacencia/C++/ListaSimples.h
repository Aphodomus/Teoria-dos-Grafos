#ifndef LISTA_SIMPLES_H
#define LISTA_SIMPLES_H

#include <iostream>
#include "Celula.h"
using namespace std;

template <class T>
class ListaSimples {
    public:
        // Atributos
        int size;
        Celula<T> *primeiro;
        Celula<T> *ultimo;

        // Metodos especiais
        ListaSimples() {
            this->primeiro = new Celula<T>();
            this->ultimo = this->primeiro;
        }

        int getSize() {
            return this->size;
        }

        // Funcoes e metodos
        void inserirInicio(T value) {
            Celula<T> *tmp = new Celula<T>(value);
            tmp->prox = this->primeiro->prox;
            this->primeiro->prox = tmp;

            if (this->primeiro == this->ultimo) {
                this->ultimo = tmp;
            }

            tmp = nullptr;
            this->size++;
        }

        void inserir(T value) {
            this->ultimo->prox = new Celula<T>(value);
            this->ultimo = this->ultimo->prox;

            this->size++;
        }

        void inserirPosition(T value, int position) {
            int length = this->size;

            if (position < 0 || position > length) {
                cout << "Erro: nao e possivel inserir na posicao { " << position << " }" << endl;
            } else if (position == 0) {
                inserirInicio(value);
            } else if (position == length) {
                inserir(value);
            } else {
                Celula<T> *i = this->primeiro;

                for (int j = 0; j < position; j++, i = i.prox);

                Celula<T> *tmp = new Celula<T>(value);
                tmp->prox = i->prox;
                i->prox = tmp;

                tmp = nullptr;
                i = nullptr;
                this->size++;
            }
        }

        T removerInicio() {
            if (this->primeiro == this->ultimo) {
                cout << "Erro: a lista esta vazia" << endl;
            }

            Celula<T> *tmp = this->primeiro;
            this->primeiro = this->primeiro->prox;
            T result = this->primeiro->value;

            tmp->prox = nullptr;
            tmp = nullptr;

            this->size--;
            return result;
        }

        T removerFim() {
            if (this->primeiro == this->ultimo) {
                cout << "Erro: a lista esta vazia" << endl;
            }

            Celula<T> *i;
            for (i = this->primeiro; i->prox != this->ultimo; i = i->prox);

            T result = this->ultimo->value;
            this->ultimo = i;
            
            i = nullptr;
            ultimo->prox = nullptr;

            this->size--;
            return result;
        }

        T removerPosition(int position) {
            T result;
            int length = this->size;

            if (this->primeiro == this->ultimo) {
                cout << "Erro: a lista esta vazia" << endl;
            } else if (position < 0 || position >= length) {
                cout << "Erro: nao e possivel remover na posicao { " << position << " }" << endl;
            } else if (position == 0) {
                result = removerInicio();
            } else if (position == length - 1) {
                result = removerFim();
            } else {
                Celula<T> *i = this->primeiro;
                for (int j = 0; j < position; j++, i = i->prox);

                Celula<T> *tmp = i->prox;
                result = tmp->value;
                i->prox = tmp->prox;

                tmp->prox = nullptr;
                i = nullptr;
                tmp = nullptr;

                this->size--;
            }

            return result;
        }

        T remover(T value) {
            T result;
            int j = 0;
            int length = this->size;

            if (this->primeiro == this->ultimo) {
                cout << "Erro: a lista esta vazia" << endl;
            } else {
                for (Celula<T> *i = this->primeiro->prox; i != nullptr; i = i->prox, j++) {
                    if (i->value == value) {
                        if (j == 0) {
                            result = removerInicio();
                        } else if (j == length - 1) {
                            result = removerFim();
                        } else {
                            Celula<T> *tmp = i->prox;
                            result = tmp->value;
                            i->prox = tmp->prox;

                            tmp->prox = nullptr;
                            i = nullptr;
                            tmp = nullptr;

                            this->size--;
                        }
                        break;
                    }
                }
            }

            return result;
        }

        void print() {
            for (Celula<T> *i = this->primeiro->prox; i != nullptr; i = i->prox) {
                cout << i->value << endl;
            }
            cout << endl;
        }

        bool contains(T value) {
            bool result = false;

            for (Celula<T> *i = this->primeiro->prox; i != nullptr; i = i->prox) {
                if (i->value == value) {
                    result = true;
                    i = this->ultimo;
                }
            }

            return result;
        }

        T get(int position) {
            T result;
            int length = this->size;

            if (this->primeiro == this->ultimo) {
                cout << "Erro: a lista esta vazia" << endl;
            } else if (position < 0 || position >= length) {
                cout << "Erro: nao e possivel pegar na posicao { " << position << " }" << endl;
            } else {
                Celula<T> *i = this->primeiro->prox;
                for (int j = 0; j < position; j++, i = i->prox);

                result = i->value;

                i = nullptr;
            }

            return result;
        }

        int indexOf(T value) {
            int result = -1;
            int j = 0;

            for (Celula<T> *i = this->primeiro->prox; i != nullptr; i = i->prox, j++) {
                if (i->value == value) {
                    result = j;
                    i = this->ultimo;
                }
            }

            return result;
        }
};

#endif
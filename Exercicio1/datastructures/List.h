#include <iostream>

#ifndef LIST_H
#define LIST_H

using namespace std;

template<typename T>
class Node {
    private:
        T data;
        Node<T>* next;
        template<typename U> friend class List;

    public:
        Node() {
            this->next = nullptr;
        }
};

template<typename T>
class List {
    private:
        Node<T>* head;
        int size;
    
    public:
        List() {
            this->head = nullptr;
            this->size = 0;
        }

        void add(T value) {
            Node<T>* node = new Node<T>[1];
            node->data = value;

            if (this->head == nullptr){
                this->head = node;
                this->size++;
                return;
            }

            Node<T>* temp = this->head;
            while (temp->next != nullptr){
                temp = temp->next;
            }

            temp->next = node;
            this->size++;
        }

        void addFront(T value){
            Node<T>* node = new Node<T>[1];
            node->data = value;

            if (this->head == nullptr){
                this->head = node;
                this->size++;
                return;
            }

            node->next = this->head;
            this->head = node;
            this->size++;
        }

        void add(int index, T value){
            if (index > length() || index < 0){
                cout << "[ERROR]: Index out of bound!" << endl;
                return;
            }

            Node<T>* node = new Node<T>[1];
            node->data = value;

            int count = 0;
            Node<T>* temp = this->head;

            while (temp != nullptr && count < index){
                if (count == index-1){
                    if (temp->next != nullptr){
                        node->next = temp->next;
                    }

                    temp->next = node;
                    this->size++;
                    break;
                }

                count++;
                temp = temp->next;
            }
        }

        void print() {
            if (this->head == nullptr){
                cout << "[ERROR]: List is empty" << endl;
                return;
            }

            Node<T>* node = new Node<T>();

            for (node = this->head; node != nullptr; node = node->next) {
                cout << node->data << "\t";
            }
        }

        int length(){
            int len = 0;
            Node<int>* temp = this->head;

            while (temp != nullptr){
                len++;
                temp = temp->next;
            }

            return len;
        }

        void remove(){
            if (this->head == nullptr){
                cout << "[ERROR]: List is empty" << endl;
                return;
            }

            if (this->head->next == nullptr){
                this->head = nullptr;
                this->size--;
                return;
            }

            Node<T>* temp = this->head;
            while (temp != nullptr){
                if (temp->next->next == nullptr){
                    temp->next = nullptr;
                    this->size--;
                    break;
                }
                temp = temp->next;
            }
        }

        void removeFront(){
            if (this->head == nullptr){
                cout << "[ERROR]: List is empty" << endl;
                return;
            }

            this->head = this->head->next;
            this->size--;
        }

        void remove(int index){
            if (this->head == nullptr){
                cout << "[ERROR]: List is empty" << endl;
                return;
            }

            if (index > this->length() || index < 0){
                cout << "[ERROR]: Index out of bound!" << endl;
                return;
            }

            if (index == 0){
                this->removeFront();
                return;
            }

            int count = 0;
            Node<T>* temp = this->head;
            while (temp != NULL){
                if (count == index - 1){
                    temp->next = temp->next->next;
                    this->size--;
                    break;
                }
                count++;
                temp = temp->next;
            }
        }

        T get(int index){
            if (this->head == nullptr){
                cout << "[ERROR]: List is empty" << endl;
                return nullptr;
            }

            if (index > this->length() || index < 0){
                cout << "[ERROR]: Index out of bound!" << endl;
                return nullptr;
            }

            if (index == 0){
                this->removeFront();
                return this->head->data;
            }

            int count = 0;
            T res;
            Node<T>* temp = head;
            while (temp != nullptr){
                if (count++ == index){
                    res = temp->data;
                    break;
                }
                temp = temp->next;
            }
            
            return res;
        }
};

#endif
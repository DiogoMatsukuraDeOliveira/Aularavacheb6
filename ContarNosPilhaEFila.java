import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class ContarNosPilhaEFila {
    public static void main(String[] args) {
        No noA = new No('A');
        No noB = new No('B');
        No noC = new No('C');
        No noD = new No('D');
        No noE = new No('E');
        No noF = new No('F');

        noA.esquerdo = noB;
        noA.direito = noC;
        noB.esquerdo = noD;
        noB.direito = noE;
        noC.direito = noF;

        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.raiz = noA;

        System.out.println("Total de nós (pilha): " + contarNosComPilha(arvore));
        System.out.println("Total de nós (fila): " + contarNosComFila(arvore));
    }

    static int contarNosComPilha(ArvoreBinaria arvore) {
        if (arvore == null || arvore.raiz == null) return 0;
        Stack<No> pilha = new Stack<>();
        pilha.push(arvore.raiz);
        int contador = 0;

        while (!pilha.isEmpty()) {
            No atual = pilha.pop();
            contador++;
            if (atual.direito != null) pilha.push(atual.direito);
            if (atual.esquerdo != null) pilha.push(atual.esquerdo);
        }
        return contador;
    }

    static int contarNosComFila(ArvoreBinaria arvore) {
        if (arvore == null || arvore.raiz == null) return 0;
        Queue<No> fila = new LinkedList<>();
        fila.add(arvore.raiz);
        int contador = 0;

        while (!fila.isEmpty()) {
            No atual = fila.poll();
            contador++;
            if (atual.esquerdo != null) fila.add(atual.esquerdo);
            if (atual.direito != null) fila.add(atual.direito);
        }
        return contador;
    }
}

class No {
    char valor;
    No esquerdo, direito;

    No(char valor) {
        this.valor = valor;
        this.esquerdo = null;
        this.direito = null;
    }
}

class ArvoreBinaria {
    No raiz;
}

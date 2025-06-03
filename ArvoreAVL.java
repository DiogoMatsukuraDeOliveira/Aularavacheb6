import java.util.*;

public class ArvoreAVLTeste {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();

        // Inserção de nós
        arvore.inserir('A');
        arvore.inserir('B');
        arvore.inserir('C');
        arvore.inserir('D');
        arvore.inserir('E');
        arvore.inserir('F');

        // Remoção de um nó
        arvore.remover('B');

        // Impressão visual da árvore (horizontal)
        System.out.println("Arvore AVL:");
        arvore.imprimirBonito();
    }
}

class No {
    char valor;
    int altura;
    No esquerdo, direito;

    No(char valor) {
        this.valor = valor;
        this.altura = 1;
    }
}

class ArvoreAVL {
    No raiz;

    int altura(No no) {
        return no == null ? 0 : no.altura;
    }

    int fatorBalanceamento(No no) {
        return no == null ? 0 : altura(no.esquerdo) - altura(no.direito);
    }

    No rotacaoDireita(No y) {
        No x = y.esquerdo;
        No T2 = x.direito;

        x.direito = y;
        y.esquerdo = T2;

        y.altura = Math.max(altura(y.esquerdo), altura(y.direito)) + 1;
        x.altura = Math.max(altura(x.esquerdo), altura(x.direito)) + 1;

        return x;
    }

    No rotacaoEsquerda(No x) {
        No y = x.direito;
        No T2 = y.esquerdo;

        y.esquerdo = x;
        x.direito = T2;

        x.altura = Math.max(altura(x.esquerdo), altura(x.direito)) + 1;
        y.altura = Math.max(altura(y.esquerdo), altura(y.direito)) + 1;

        return y;
    }

    No balancear(No no) {
        int balance = fatorBalanceamento(no);

        if (balance > 1 && fatorBalanceamento(no.esquerdo) >= 0)
            return rotacaoDireita(no);

        if (balance > 1 && fatorBalanceamento(no.esquerdo) < 0) {
            no.esquerdo = rotacaoEsquerda(no.esquerdo);
            return rotacaoDireita(no);
        }

        if (balance < -1 && fatorBalanceamento(no.direito) <= 0)
            return rotacaoEsquerda(no);

        if (balance < -1 && fatorBalanceamento(no.direito) > 0) {
            no.direito = rotacaoDireita(no.direito);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    No inserir(No no, char valor) {
        if (no == null) return new No(valor);

        if (valor < no.valor)
            no.esquerdo = inserir(no.esquerdo, valor);
        else if (valor > no.valor)
            no.direito = inserir(no.direito, valor);
        else
            return no;

        no.altura = 1 + Math.max(altura(no.esquerdo), altura(no.direito));
        return balancear(no);
    }

    void inserir(char valor) {
        raiz = inserir(raiz, valor);
    }

    No minValorNo(No no) {
        while (no.esquerdo != null)
            no = no.esquerdo;
        return no;
    }

    No remover(No no, char valor) {
        if (no == null) return null;

        if (valor < no.valor)
            no.esquerdo = remover(no.esquerdo, valor);
        else if (valor > no.valor)
            no.direito = remover(no.direito, valor);
        else {
            if (no.esquerdo == null || no.direito == null)
                return (no.esquerdo != null) ? no.esquerdo : no.direito;

            No temp = minValorNo(no.direito);
            no.valor = temp.valor;
            no.direito = remover(no.direito, temp.valor);
        }

        no.altura = 1 + Math.max(altura(no.esquerdo), altura(no.direito));
        return balancear(no);
    }

    void remover(char valor) {
        raiz = remover(raiz, valor);
    }

    void imprimirBonito() {
        int maxLevel = altura(raiz);
        imprimirBonito(Collections.singletonList(raiz), 1, maxLevel);
    }

    void imprimirBonito(List<No> nos, int nivel, int maxLevel) {
        if (nos.isEmpty() || todosNulos(nos)) return;

        int espaço = (int) Math.pow(2, maxLevel - nivel + 1);

        for (No no : nos) {
            System.out.print(" ".repeat(espaço));
            if (no == null) {
                System.out.print(" ");
            } else {
                System.out.print(no.valor);
            }
            System.out.print(" ".repeat(espaço));
        }
        System.out.println();

        List<No> proxNivel = new ArrayList<>();
        for (No no : nos) {
            if (no == null) {
                proxNivel.add(null);
                proxNivel.add(null);
            } else {
                proxNivel.add(no.esquerdo);
                proxNivel.add(no.direito);
            }
        }

        imprimirBonito(proxNivel, nivel + 1, maxLevel);
    }

    boolean todosNulos(List<No> lista) {
        for (No no : lista) {
            if (no != null) return false;
        }
        return true;
    }
}

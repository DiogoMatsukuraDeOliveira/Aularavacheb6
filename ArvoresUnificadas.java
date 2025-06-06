import java.util.*;

public class ArvoresUnificadas {

    static class ArvoreBinariaSimples {

        static class NoBinario {
            int valor;
            NoBinario esquerdo;
            NoBinario direito;

            public NoBinario(int valor) {
                this.valor = valor;
                this.esquerdo = null;
                this.direito = null;
            }
        }

        static class ArvoreBinaria {
            private NoBinario raiz;

            public ArvoreBinaria() {
                this.raiz = null;
            }

            public void inserir(int valor) {
                raiz = inserirRecursivo(raiz, valor);
            }

            private NoBinario inserirRecursivo(NoBinario atual, int valor) {
                if (atual == null) {
                    return new NoBinario(valor);
                }
                if (valor < atual.valor) {
                    atual.esquerdo = inserirRecursivo(atual.esquerdo, valor);
                } else if (valor > atual.valor) {
                    atual.direito = inserirRecursivo(atual.direito, valor);
                }
                return atual;
            }

            public void emOrdem() {
                emOrdemRecursivo(raiz);
                System.out.println();
            }

            private void emOrdemRecursivo(NoBinario atual) {
                if (atual != null) {
                    emOrdemRecursivo(atual.esquerdo);
                    System.out.print(atual.valor + " ");
                    emOrdemRecursivo(atual.direito);
                }
            }
        }
    }

    static class ArvoreAVL {

        static class NoAVL {
            char valor;
            int altura;
            NoAVL esquerdo, direito;

            NoAVL(char valor) {
                this.valor = valor;
                this.altura = 1;
            }
        }

        NoAVL raiz;

        int altura(NoAVL no) {
            return no == null ? 0 : no.altura;
        }

        int fatorBalanceamento(NoAVL no) {
            return no == null ? 0 : altura(no.esquerdo) - altura(no.direito);
        }

        NoAVL rotacaoDireita(NoAVL y) {
            NoAVL x = y.esquerdo;
            NoAVL T2 = x.direito;
            x.direito = y;
            y.esquerdo = T2;
            y.altura = Math.max(altura(y.esquerdo), altura(y.direito)) + 1;
            x.altura = Math.max(altura(x.esquerdo), altura(x.direito)) + 1;
            return x;
        }

        NoAVL rotacaoEsquerda(NoAVL x) {
            NoAVL y = x.direito;
            NoAVL T2 = y.esquerdo;
            y.esquerdo = x;
            x.direito = T2;
            x.altura = Math.max(altura(x.esquerdo), altura(x.direito)) + 1;
            y.altura = Math.max(altura(y.esquerdo), altura(y.direito)) + 1;
            return y;
        }

        NoAVL balancear(NoAVL no) {
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

        NoAVL inserir(NoAVL no, char valor) {
            if (no == null) return new NoAVL(valor);
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

        NoAVL minValorNo(NoAVL no) {
            while (no.esquerdo != null)
                no = no.esquerdo;
            return no;
        }

        NoAVL remover(NoAVL no, char valor) {
            if (no == null) return null;
            if (valor < no.valor)
                no.esquerdo = remover(no.esquerdo, valor);
            else if (valor > no.valor)
                no.direito = remover(no.direito, valor);
            else {
                if (no.esquerdo == null || no.direito == null)
                    return (no.esquerdo != null) ? no.esquerdo : no.direito;
                NoAVL temp = minValorNo(no.direito);
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

        void imprimirBonito(List<NoAVL> nos, int nivel, int maxLevel) {
            if (nos.isEmpty() || todosNulos(nos)) return;
            int espaço = (int) Math.pow(2, maxLevel - nivel + 1);
            for (NoAVL no : nos) {
                System.out.print(" ".repeat(espaço));
                System.out.print(no == null ? " " : no.valor);
                System.out.print(" ".repeat(espaço));
            }
            System.out.println();
            List<NoAVL> proxNivel = new ArrayList<>();
            for (NoAVL no : nos) {
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

        boolean todosNulos(List<NoAVL> lista) {
            for (NoAVL no : lista) if (no != null) return false;
            return true;
        }
    }

    static class BinaryTree {

        static class NodeString {
            String value;
            NodeString left, right;

            NodeString(String value) {
                this.value = value;
                left = right = null;
            }
        }

        public static void inOrder(NodeString node) {
            if (node != null) {
                inOrder(node.left);
                System.out.print(node.value + " ");
                inOrder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        ArvoreBinariaSimples.ArvoreBinaria arvoreBin = new ArvoreBinariaSimples.ArvoreBinaria();
        arvoreBin.inserir(50);
        arvoreBin.inserir(30);
        arvoreBin.inserir(70);
        arvoreBin.inserir(20);
        arvoreBin.inserir(40);
        arvoreBin.inserir(60);
        arvoreBin.inserir(80);
        System.out.print("Árvore Binária Simples (em ordem): ");
        arvoreBin.emOrdem();

        ArvoreAVL arvoreAVL = new ArvoreAVL();
        arvoreAVL.inserir('A');
        arvoreAVL.inserir('B');
        arvoreAVL.inserir('C');
        arvoreAVL.inserir('D');
        arvoreAVL.inserir('E');
        arvoreAVL.inserir('F');
        arvoreAVL.remover('B');
        System.out.println("Árvore AVL (impressão horizontal):");
        arvoreAVL.imprimirBonito();

        BinaryTree.NodeString root = new BinaryTree.NodeString("A");
        root.left = new BinaryTree.NodeString("B");
        root.right = new BinaryTree.NodeString("C");
        root.left.left = new BinaryTree.NodeString("D");
        root.left.right = new BinaryTree.NodeString("E");
        root.right.right = new BinaryTree.NodeString("F");
        System.out.print("Árvore Binária com Strings (em ordem): ");
        BinaryTree.inOrder(root);
        System.out.println();
    }
}

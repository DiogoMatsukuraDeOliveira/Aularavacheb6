public class ArvoreBinariaSimples {

    // Classe que representa um nó da árvore
    static class No {
        int valor;
        No esquerdo;
        No direito;

        public No(int valor) {
            this.valor = valor;
            this.esquerdo = null;
            this.direito = null;
        }
    }

    // Classe que representa a árvore binária
    static class ArvoreBinaria {
        private No raiz;

        public ArvoreBinaria() {
            this.raiz = null;
        }

        public void inserir(int valor) {
            raiz = inserirRecursivo(raiz, valor);
        }

        private No inserirRecursivo(No atual, int valor) {
            if (atual == null) {
                return new No(valor);
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

        private void emOrdemRecursivo(No atual) {
            if (atual != null) {
                emOrdemRecursivo(atual.esquerdo);
                System.out.print(atual.valor + " ");
                emOrdemRecursivo(atual.direito);
            }
        }
    }

    // Método principal para testes
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();

        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(60);
        arvore.inserir(80);

        System.out.print("Elementos em ordem: ");
        arvore.emOrdem();  // Saída esperada: 20 30 40 50 60 70 80
    }
}

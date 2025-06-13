import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Unificacao {

    // Nó da árvore binária
    static class No {
        int valor;
        No esquerdo, direito;
        No(int valor) {
            this.valor = valor;
            this.esquerdo = this.direito = null;
        }
    }

    // Árvore binária com métodos recursivos e não recursivos
    static class Arvore {
        private No raiz;

        // inserção recursiva
        public void inserir(int valor) {
            raiz = inserirRec(raiz, valor);
        }
        private No inserirRec(No node, int valor) {
            if (node == null) return new No(valor);
            if (valor < node.valor)
                node.esquerdo = inserirRec(node.esquerdo, valor);
            else if (valor > node.valor)
                node.direito = inserirRec(node.direito, valor);
            return node;
        }

        // altura
        public int altura() {
            return alturaRec(raiz);
        }
        private int alturaRec(No node) {
            if (node == null) return 0;
            return 1 + Math.max(alturaRec(node.esquerdo), alturaRec(node.direito));
        }

        // contagem de nós (recursivo)
        public int contarNosRecursivo() {
            return contarNosRecursivo(raiz);
        }
        private int contarNosRecursivo(No node) {
            if (node == null) return 0;
            return 1 + contarNosRecursivo(node.esquerdo) + contarNosRecursivo(node.direito);
        }

        // contagem de nós (iterativo com stack)
        public int contarNosIterativoStack() {
            if (raiz == null) return 0;
            Stack<No> stack = new Stack<>();
            stack.push(raiz);
            int count = 0;
            while (!stack.isEmpty()) {
                No cur = stack.pop();
                count++;
                if (cur.direito != null) stack.push(cur.direito);
                if (cur.esquerdo != null) stack.push(cur.esquerdo);
            }
            return count;
        }

        // contagem de nós (iterativo com queue)
        public int contarNosIterativoQueue() {
            if (raiz == null) return 0;
            Queue<No> queue = new LinkedList<>();
            queue.add(raiz);
            int count = 0;
            while (!queue.isEmpty()) {
                No cur = queue.poll();
                count++;
                if (cur.esquerdo != null) queue.add(cur.esquerdo);
                if (cur.direito != null) queue.add(cur.direito);
            }
            return count;
        }

        // contagem de folhas (recursivo)
        public int contarFolhasRecursivo() {
            return contarFolhasRecursivo(raiz);
        }
        private int contarFolhasRecursivo(No node) {
            if (node == null) return 0;
            if (node.esquerdo == null && node.direito == null) return 1;
            return contarFolhasRecursivo(node.esquerdo) + contarFolhasRecursivo(node.direito);
        }

        // contagem de folhas (iterativo com stack)
        public int contarFolhasIterativoStack() {
            if (raiz == null) return 0;
            Stack<No> stack = new Stack<>();
            stack.push(raiz);
            int folhas = 0;
            while (!stack.isEmpty()) {
                No cur = stack.pop();
                if (cur.esquerdo == null && cur.direito == null) folhas++;
                if (cur.direito != null) stack.push(cur.direito);
                if (cur.esquerdo != null) stack.push(cur.esquerdo);
            }
            return folhas;
        }

        // contagem de folhas (iterativo com queue)
        public int contarFolhasIterativoQueue() {
            if (raiz == null) return 0;
            Queue<No> queue = new LinkedList<>();
            queue.add(raiz);
            int folhas = 0;
            while (!queue.isEmpty()) {
                No cur = queue.poll();
                if (cur.esquerdo == null && cur.direito == null) folhas++;
                if (cur.esquerdo != null) queue.add(cur.esquerdo);
                if (cur.direito != null) queue.add(cur.direito);
            }
            return folhas;
        }

        // busca recursiva
        public boolean buscar(int valor) {
            return buscarRec(raiz, valor);
        }
        private boolean buscarRec(No node, int valor) {
            if (node == null) return false;
            if (valor == node.valor) return true;
            return valor < node.valor
                ? buscarRec(node.esquerdo, valor)
                : buscarRec(node.direito, valor);
        }

        // pré-ordem recursivo
        public void preOrdemRecursivo() {
            preOrdemRecursivo(raiz);
            System.out.println();
        }
        private void preOrdemRecursivo(No node) {
            if (node != null) {
                System.out.print(node.valor + " ");
                preOrdemRecursivo(node.esquerdo);
                preOrdemRecursivo(node.direito);
            }
        }

        // pré-ordem iterativo
        public void preOrdemIterativo() {
            if (raiz == null) { System.out.println(); return; }
            Stack<No> stack = new Stack<>();
            stack.push(raiz);
            while (!stack.isEmpty()) {
                No cur = stack.pop();
                System.out.print(cur.valor + " ");
                if (cur.direito != null) stack.push(cur.direito);
                if (cur.esquerdo != null) stack.push(cur.esquerdo);
            }
            System.out.println();
        }

        // em-ordem recursivo
        public void emOrdemRecursivo() {
            emOrdemRecursivo(raiz);
            System.out.println();
        }
        private void emOrdemRecursivo(No node) {
            if (node != null) {
                emOrdemRecursivo(node.esquerdo);
                System.out.print(node.valor + " ");
                emOrdemRecursivo(node.direito);
            }
        }

        // em-ordem iterativo
        public void emOrdemIterativo() {
            Stack<No> stack = new Stack<>();
            No cur = raiz;
            while (cur != null || !stack.isEmpty()) {
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.esquerdo;
                }
                cur = stack.pop();
                System.out.print(cur.valor + " ");
                cur = cur.direito;
            }
            System.out.println();
        }

        // pós-ordem recursivo
        public void posOrdemRecursivo() {
            posOrdemRecursivo(raiz);
            System.out.println();
        }
        private void posOrdemRecursivo(No node) {
            if (node != null) {
                posOrdemRecursivo(node.esquerdo);
                posOrdemRecursivo(node.direito);
                System.out.print(node.valor + " ");
            }
        }

        // pós-ordem iterativo (com duas pilhas)
        public void posOrdemIterativo() {
            if (raiz == null) { System.out.println(); return; }
            Stack<No> stack1 = new Stack<>();
            Stack<No> stack2 = new Stack<>();
            stack1.push(raiz);
            while (!stack1.isEmpty()) {
                No cur = stack1.pop();
                stack2.push(cur);
                if (cur.esquerdo != null) stack1.push(cur.esquerdo);
                if (cur.direito != null) stack1.push(cur.direito);
            }
            while (!stack2.isEmpty()) {
                System.out.print(stack2.pop().valor + " ");
            }
            System.out.println();
        }

        // nível recursivo
        public void emNivelRecursivo() {
            int h = altura();
            for (int i = 0; i < h; i++) {
                percorrerNivel(raiz, i);
            }
            System.out.println();
        }
        private void percorrerNivel(No node, int nivel) {
            if (node == null) return;
            if (nivel == 0) {
                System.out.print(node.valor + " ");
            } else {
                percorrerNivel(node.esquerdo, nivel - 1);
                percorrerNivel(node.direito, nivel - 1);
            }
        }

        // nível iterativo
        public void emNivelIterativo() {
            if (raiz == null) { System.out.println(); return; }
            Queue<No> queue = new LinkedList<>();
            queue.add(raiz);
            while (!queue.isEmpty()) {
                No cur = queue.poll();
                System.out.print(cur.valor + " ");
                if (cur.esquerdo != null) queue.add(cur.esquerdo);
                if (cur.direito != null) queue.add(cur.direito);
            }
            System.out.println();
        }
    }

    // Nó para árvore AVL
    static class NoAVL {
        int valor, altura;
        NoAVL esquerdo, direito;
        NoAVL(int valor) {
            this.valor = valor;
            this.altura = 1;
            this.esquerdo = this.direito = null;
        }
    }

    // Árvore AVL com inserção, remoção, busca, contagem e percursos
    static class ArvoreAVL {
        private NoAVL raiz;

        // altura e fator de balanceamento
        private int altura(NoAVL n) {
            return (n == null) ? 0 : n.altura;
        }
        private int fatorBalanceamento(NoAVL n) {
            return (n == null) ? 0 : altura(n.esquerdo) - altura(n.direito);
        }

        // rotações
        private NoAVL rotacaoDireita(NoAVL y) {
            NoAVL x = y.esquerdo;
            NoAVL T2 = x.direito;
            x.direito = y;
            y.esquerdo = T2;
            y.altura = Math.max(altura(y.esquerdo), altura(y.direito)) + 1;
            x.altura = Math.max(altura(x.esquerdo), altura(x.direito)) + 1;
            return x;
        }
        private NoAVL rotacaoEsquerda(NoAVL x) {
            NoAVL y = x.direito;
            NoAVL T2 = y.esquerdo;
            y.esquerdo = x;
            x.direito = T2;
            x.altura = Math.max(altura(x.esquerdo), altura(x.direito)) + 1;
            y.altura = Math.max(altura(y.esquerdo), altura(y.direito)) + 1;
            return y;
        }

        // inserção
        public void inserir(int valor) {
            raiz = inserirRec(raiz, valor);
        }
        private NoAVL inserirRec(NoAVL node, int valor) {
            if (node == null) return new NoAVL(valor);
            if (valor < node.valor)
                node.esquerdo = inserirRec(node.esquerdo, valor);
            else if (valor > node.valor)
                node.direito = inserirRec(node.direito, valor);
            else
                return node;
            node.altura = 1 + Math.max(altura(node.esquerdo), altura(node.direito));
            int balance = fatorBalanceamento(node);
            if (balance > 1 && valor < node.esquerdo.valor)
                return rotacaoDireita(node);
            if (balance < -1 && valor > node.direito.valor)
                return rotacaoEsquerda(node);
            if (balance > 1 && valor > node.esquerdo.valor) {
                node.esquerdo = rotacaoEsquerda(node.esquerdo);
                return rotacaoDireita(node);
            }
            if (balance < -1 && valor < node.direito.valor) {
                node.direito = rotacaoDireita(node.direito);
                return rotacaoEsquerda(node);
            }
            return node;
        }

        // remoção
        public void remover(int valor) {
            raiz = removerRec(raiz, valor);
        }
        private NoAVL removerRec(NoAVL node, int valor) {
            if (node == null) return null;
            if (valor < node.valor)
                node.esquerdo = removerRec(node.esquerdo, valor);
            else if (valor > node.valor)
                node.direito = removerRec(node.direito, valor);
            else {
                if (node.esquerdo == null || node.direito == null) {
                    node = (node.esquerdo != null) ? node.esquerdo : node.direito;
                } else {
                    NoAVL temp = menorValor(node.direito);
                    node.valor = temp.valor;
                    node.direito = removerRec(node.direito, temp.valor);
                }
            }
            if (node == null) return null;
            node.altura = 1 + Math.max(altura(node.esquerdo), altura(node.direito));
            int balance = fatorBalanceamento(node);
            if (balance > 1 && fatorBalanceamento(node.esquerdo) >= 0)
                return rotacaoDireita(node);
            if (balance > 1 && fatorBalanceamento(node.esquerdo) < 0) {
                node.esquerdo = rotacaoEsquerda(node.esquerdo);
                return rotacaoDireita(node);
            }
            if (balance < -1 && fatorBalanceamento(node.direito) <= 0)
                return rotacaoEsquerda(node);
            if (balance < -1 && fatorBalanceamento(node.direito) > 0) {
                node.direito = rotacaoDireita(node.direito);
                return rotacaoEsquerda(node);
            }
            return node;
        }
        private NoAVL menorValor(NoAVL node) {
            NoAVL atual = node;
            while (atual.esquerdo != null) atual = atual.esquerdo;
            return atual;
        }

        // busca
        public boolean buscar(int valor) {
            return buscarRec(raiz, valor);
        }
        private boolean buscarRec(NoAVL node, int valor) {
            if (node == null) return false;
            if (valor == node.valor) return true;
            return valor < node.valor
                ? buscarRec(node.esquerdo, valor)
                : buscarRec(node.direito, valor);
        }

        // contagem de nós
        public int contarNos() {
            return contarNosRec(raiz);
        }
        private int contarNosRec(NoAVL node) {
            if (node == null) return 0;
            return 1 + contarNosRec(node.esquerdo) + contarNosRec(node.direito);
        }

        // altura da árvore
        public int altura() {
            return altura(raiz);
        }

        // fator de balanceamento da raiz
        public int getBalance() {
            return fatorBalanceamento(raiz);
        }

        // pré-ordem recursivo
        public void preOrdemRecursivo() {
            preOrdemRecursivo(raiz);
            System.out.println();
        }
        private void preOrdemRecursivo(NoAVL node) {
            if (node != null) {
                System.out.print(node.valor + " ");
                preOrdemRecursivo(node.esquerdo);
                preOrdemRecursivo(node.direito);
            }
        }

        // em-ordem recursivo
        public void emOrdemRecursivo() {
            emOrdemRecursivo(raiz);
            System.out.println();
        }
        private void emOrdemRecursivo(NoAVL node) {
            if (node != null) {
                emOrdemRecursivo(node.esquerdo);
                System.out.print(node.valor + " ");
                emOrdemRecursivo(node.direito);
            }
        }

        // pós-ordem recursivo
        public void posOrdemRecursivo() {
            posOrdemRecursivo(raiz);
            System.out.println();
        }
        private void posOrdemRecursivo(NoAVL node) {
            if (node != null) {
                posOrdemRecursivo(node.esquerdo);
                posOrdemRecursivo(node.direito);
                System.out.print(node.valor + " ");
            }
        }

        // nível iterativo
        public void emNivelIterativo() {
            if (raiz == null) { System.out.println(); return; }
            Queue<NoAVL> queue = new LinkedList<>();
            queue.add(raiz);
            while (!queue.isEmpty()) {
                NoAVL cur = queue.poll();
                System.out.print(cur.valor + " ");
                if (cur.esquerdo != null) queue.add(cur.esquerdo);
                if (cur.direito != null) queue.add(cur.direito);
            }
            System.out.println();
        }
    }
    // Classe NoRB representa um nó da Árvore Rubro-Negra
static class NoRB {
    static final boolean VERMELHO = true;
    static final boolean PRETO = false;

    int valor;
    boolean cor;
    NoRB esquerdo, direito, pai;

    NoRB(int valor) {
        this.valor = valor;
        this.cor = VERMELHO; // novos nós são vermelhos
    }

    boolean ehVermelho() {
        return this != null && this.cor == VERMELHO;
    }

    boolean ehPreto() {
        return this == null || this.cor == PRETO;
    }
}

// Classe ArvoreRubroNegra com inserção, remoção, balanceamento e rotações
static class ArvoreRubroNegra {
    private NoRB raiz;

    public void inserir(int valor) {
        NoRB novoNo = new NoRB(valor);
        raiz = inserirRec(raiz, novoNo);
        balancearInsercao(novoNo);
    }

    private NoRB inserirRec(NoRB atual, NoRB novoNo) {
        if (atual == null) return novoNo;

        if (novoNo.valor < atual.valor) {
            atual.esquerdo = inserirRec(atual.esquerdo, novoNo);
            atual.esquerdo.pai = atual;
        } else {
            atual.direito = inserirRec(atual.direito, novoNo);
            atual.direito.pai = atual;
        }
        return atual;
    }

    public void remover(int valor) {
        NoRB no = buscarNo(raiz, valor);
        if (no != null) removerNo(no);
    }

    private NoRB buscarNo(NoRB node, int valor) {
        if (node == null || node.valor == valor) return node;
        return valor < node.valor ? buscarNo(node.esquerdo, valor) : buscarNo(node.direito, valor);
    }

    private void balancearInsercao(NoRB no) {
        while (no != raiz && no.pai.ehVermelho()) {
            if (no.pai == no.pai.pai.esquerdo) {
                NoRB tio = no.pai.pai.direito;
                if (tio != null && tio.ehVermelho()) {
                    no.pai.cor = NoRB.PRETO;
                    tio.cor = NoRB.PRETO;
                    no.pai.pai.cor = NoRB.VERMELHO;
                    no = no.pai.pai;
                } else {
                    if (no == no.pai.direito) {
                        no = no.pai;
                        rotacaoEsquerda(no);
                    }
                    no.pai.cor = NoRB.PRETO;
                    no.pai.pai.cor = NoRB.VERMELHO;
                    rotacaoDireita(no.pai.pai);
                }
            } else {
                NoRB tio = no.pai.pai.esquerdo;
                if (tio != null && tio.ehVermelho()) {
                    no.pai.cor = NoRB.PRETO;
                    tio.cor = NoRB.PRETO;
                    no.pai.pai.cor = NoRB.VERMELHO;
                    no = no.pai.pai;
                } else {
                    if (no == no.pai.esquerdo) {
                        no = no.pai;
                        rotacaoDireita(no);
                    }
                    no.pai.cor = NoRB.PRETO;
                    no.pai.pai.cor = NoRB.VERMELHO;
                    rotacaoEsquerda(no.pai.pai);
                }
            }
        }
        raiz.cor = NoRB.PRETO;
    }

    private void removerNo(NoRB no) {
        NoRB substituto = (no.esquerdo != null) ? no.esquerdo : no.direito;
        if (substituto != null) substituto.pai = no.pai;

        if (no.pai == null) {
            raiz = substituto;
        } else if (no == no.pai.esquerdo) {
            no.pai.esquerdo = substituto;
        } else {
            no.pai.direito = substituto;
        }

        if (no.cor == NoRB.PRETO) {
            balancearRemocao(substituto);
        }
    }

    private void balancearRemocao(NoRB no) {
        while (no != raiz && no.ehPreto()) {
            if (no == no.pai.esquerdo) {
                NoRB irmao = no.pai.direito;
                if (irmao.ehVermelho()) {
                    irmao.cor = NoRB.PRETO;
                    no.pai.cor = NoRB.VERMELHO;
                    rotacaoEsquerda(no.pai);
                    irmao = no.pai.direito;
                }
                if ((irmao.esquerdo == null || irmao.esquerdo.ehPreto()) &&
                    (irmao.direito == null || irmao.direito.ehPreto())) {
                    irmao.cor = NoRB.VERMELHO;
                    no = no.pai;
                } else {
                    if (irmao.direito == null || irmao.direito.ehPreto()) {
                        if (irmao.esquerdo != null) irmao.esquerdo.cor = NoRB.PRETO;
                        irmao.cor = NoRB.VERMELHO;
                        rotacaoDireita(irmao);
                        irmao = no.pai.direito;
                    }
                    irmao.cor = no.pai.cor;
                    no.pai.cor = NoRB.PRETO;
                    if (irmao.direito != null) irmao.direito.cor = NoRB.PRETO;
                    rotacaoEsquerda(no.pai);
                    no = raiz;
                }
            } else {
                NoRB irmao = no.pai.esquerdo;
                if (irmao.ehVermelho()) {
                    irmao.cor = NoRB.PRETO;
                    no.pai.cor = NoRB.VERMELHO;
                    rotacaoDireita(no.pai);
                    irmao = no.pai.esquerdo;
                }
                if ((irmao.direito == null || irmao.direito.ehPreto()) &&
                    (irmao.esquerdo == null || irmao.esquerdo.ehPreto())) {
                    irmao.cor = NoRB.VERMELHO;
                    no = no.pai;
                } else {
                    if (irmao.esquerdo == null || irmao.esquerdo.ehPreto()) {
                        if (irmao.direito != null) irmao.direito.cor = NoRB.PRETO;
                        irmao.cor = NoRB.VERMELHO;
                        rotacaoEsquerda(irmao);
                        irmao = no.pai.esquerdo;
                    }
                    irmao.cor = no.pai.cor;
                    no.pai.cor = NoRB.PRETO;
                    if (irmao.esquerdo != null) irmao.esquerdo.cor = NoRB.PRETO;
                    rotacaoDireita(no.pai);
                    no = raiz;
                }
            }
        }
        if (no != null) no.cor = NoRB.PRETO;
    }

    private void rotacaoEsquerda(NoRB no) {
        NoRB novoNo = no.direito;
        no.direito = novoNo.esquerdo;
        if (novoNo.esquerdo != null) novoNo.esquerdo.pai = no;
        novoNo.pai = no.pai;
        if (no.pai == null) {
            raiz = novoNo;
        } else if (no == no.pai.esquerdo) {
            no.pai.esquerdo = novoNo;
        } else {
            no.pai.direito = novoNo;
        }
        novoNo.esquerdo = no;
        no.pai = novoNo;
    }

    private void rotacaoDireita(NoRB no) {
        NoRB novoNo = no.esquerdo;
        no.esquerdo = novoNo.direito;
        if (novoNo.direito != null) novoNo.direito.pai = no;
        novoNo.pai = no.pai;
        if (no.pai == null) {
            raiz = novoNo;
        } else if (no == no.pai.direito) {
            no.pai.direito = novoNo;
        } else {
            no.pai.esquerdo = novoNo;
        }
        novoNo.direito = no;
        no.pai = novoNo;
    }
    public void imprimirPreOrdem() {
        imprimirPreOrdem(raiz);
        System.out.println();
    }

    private void imprimirPreOrdem(NoRB no) {
        if (no != null) {
            String cor = no.cor == NoRB.VERMELHO ? "V" : "P";
            System.out.print(no.valor + cor + " ");
            imprimirPreOrdem(no.esquerdo);
            imprimirPreOrdem(no.direito);
        }
    }
}


    public static void main(String[] args) {
        // Demonstração da Árvore binária
        Arvore bin = new Arvore();
        int[] vBin = {50, 30, 70, 20, 40, 60, 80};
        for (int v : vBin) bin.inserir(v);
        System.out.println("=== Arvore Binária ===");
        System.out.println("Altura: " + bin.altura());
        System.out.println("Contar nós (rec): " + bin.contarNosRecursivo());
        System.out.println("Contar nós (it.Stack): " + bin.contarNosIterativoStack());
        System.out.println("Contar nós (it.Queue): " + bin.contarNosIterativoQueue());
        System.out.println("Contar folhas (rec): " + bin.contarFolhasRecursivo());
        System.out.println("Contar folhas (it.Stack): " + bin.contarFolhasIterativoStack());
        System.out.println("Contar folhas (it.Queue): " + bin.contarFolhasIterativoQueue());
        System.out.print("Pré-ordem rec: "); bin.preOrdemRecursivo();
        System.out.print("Pré-ordem it:  "); bin.preOrdemIterativo();
        System.out.print("Em-ordem rec:  "); bin.emOrdemRecursivo();
        System.out.print("Em-ordem it:   "); bin.emOrdemIterativo();
        System.out.print("Pós-ordem rec: "); bin.posOrdemRecursivo();
        System.out.print("Pós-ordem it:  "); bin.posOrdemIterativo();
        System.out.print("Nível rec:    "); bin.emNivelRecursivo();
        System.out.print("Nível it:     "); bin.emNivelIterativo();
        System.out.println("Buscar 60: " + bin.buscar(60));
        System.out.println("Buscar 25: " + bin.buscar(25));

        // Demonstração da Árvore AVL
        ArvoreAVL avl = new ArvoreAVL();
        int[] vAVL = {10, 20, 30, 40, 50, 25};
        for (int v : vAVL) avl.inserir(v);
        System.out.println("\n=== Arvore AVL ===");
        System.out.println("Altura: " + avl.altura());
        System.out.println("Contar nós: " + avl.contarNos());
        System.out.println("Fator balanceamento (raiz): " + avl.getBalance());
        System.out.print("Pré-ordem:    "); avl.preOrdemRecursivo();
        System.out.print("Em-ordem:     "); avl.emOrdemRecursivo();
        System.out.print("Pós-ordem:    "); avl.posOrdemRecursivo();
        System.out.print("Em nível:     "); avl.emNivelIterativo();
        System.out.println("Buscar 30: " + avl.buscar(30));
        avl.remover(20);
        System.out.print("Após remover 20, em-ordem: "); avl.emOrdemRecursivo();

         // Demonstração da Árvore Rubro-Negra
        System.out.println("\n=== Arvores Rubro-Negras ===");
        ArvoreRubroNegra arvore = new ArvoreRubroNegra();
        System.out.println("=== INSERÇOES ===");
        int[] inserir = {10, 20, 30, 15, 25, 5};
        for (int valor : inserir) {
            System.out.println("Inserindo: " + valor);arvore.inserir(valor);
        }
        System.out.println("\nEstado da árvore (pré-ordem com cor):");arvore.imprimirPreOrdem();
        System.out.println("\n=== REMOÇOES ===");
        int[] remover = {15, 10};
        for (int valor : remover) {
            System.out.println("Removendo: " + valor);arvore.remover(valor);
        }
        System.out.println("\nEstado da árvore após remoçoes (pré-ordem com cor):");arvore.imprimirPreOrdem();
    }
    }

package simulacao;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
    
/**
 * Representa o mapa da simulação
 */
public class Mapa {
    private List<Object> itens;
    private int largura;
    private int altura;
    
    private static final int LARGURA_PADRAO = 35;
    private static final int ALTURA_PADRAO = 35;

    /**
     * Cria mapa para alocar itens da simulacao.
     * @param largura Largura da área de simulação.
     * @param altura Altura da área de simulação.
     * @throws IllegalArgumentException Se a altura ou a largura for menor que 1.
     */
    public Mapa(int largura, int altura) {
        if(largura < 1) {
            throw new IllegalArgumentException( "a largura deve ser positiva: " + largura);
        }
        if(altura < 1) {
            throw new IllegalArgumentException( "a altura deve ser positiva: " + altura);
        }
        this.largura = largura;
        this.altura = altura;
        itens = new LinkedList<Object>();
    }
    
    /**
     * Cria mapa com tamanho padrão.
     */
    public Mapa() {
        this(LARGURA_PADRAO, ALTURA_PADRAO);
    }
    
    /**
     * @return Um iterator de itens.
     */
    public Iterator getItens() {
        return itens.iterator();
    }

    /**
     * Adiciona um item a coleção de itens.
     * @param item O item a ser adicionado.
     * @throws IllegalArgumentException Se o item já existir
     */
    public void adicionarItem(Object item) {
        if(itens.contains(item)) {
            throw new IllegalArgumentException(item + " já existe no mapa.");
        }
        itens.add(item);
    }

    /**
     * Remove um item da coleção de itens.
     * @param item O item a ser removido.
     * @throws IllegalArgumentException Se o item não for encontrado.
     */
    public void removerItem(Object item) {
        if(!itens.remove(item)) {
            throw new IllegalArgumentException(item + " não está no mapa.");
        }
    }
    
    /**
     * Pega a largura do mapa.
     * @return A largura.
     */
    public int getLargura() {
        return largura;
    }
    
    /**
     * Pega a altura do mapa.
     * @return A altura.
     */
    public int getAltura() {
        return altura;
    }
}

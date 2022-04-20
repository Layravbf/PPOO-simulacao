package simulacao;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa os vinhos da simulação
 */
public class Vinho implements ItemDesenhavel {
    private Localizacao retirada;
    private Localizacao destino;
    private Image imagemVinho;

    /**
     * Construtor da classe Vinho.
     * @param retirada A localização de retirada.
     * @param destino A localização de destino.
     * @throws NullPointerException Se a localização de retirada ou a de destino for nula.
     */
    public Vinho(Localizacao retirada, Localizacao destino) {
        if(retirada == null) {
            throw new NullPointerException("Localização de retirada é nula!");
        }
        if(destino == null) {
            throw new NullPointerException("Localização de destino é nula!");
        }
        this.retirada = retirada;
        this.destino = destino;
        imagemVinho = new ImageIcon(getClass().getResource("imagens/vinho.jpg")).getImage();
    }
    
    /**
     * @return A string representation of this person.
     */
    /*public String toString()
    {
        return "vinho travelling from " +
        retirada + " to " + destino;
    }*/

    /**
     * Pega a imagem do vinho que será exibida.
     * @return A imagem do vinho.
     */
    public Image getImagem() {
        return imagemVinho;
    }
    
    /**
     * Pega a localização atual do vinho.
     * @return A localização de retirada.
     */
    public Localizacao getLocalizacaoAtual() {
        return retirada;
    }

    /**
     * Pega a localização de retirada do vinho.
     * @return A localização de retirada.
     */
    public Localizacao getLocalizacaoRetirada() {
        return retirada;
    }
    
    /**
     * Pega a localização de destino do vinho.
     * @return A localização de destino.
     */
    public Localizacao getDestino() {
        return destino;
    }
}

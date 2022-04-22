package simulacao;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa os bancos de areia da simulação
 */
public class BancoAreia implements ItemDesenhavel, Ator {
    private Localizacao localizacao;
    private Image imagemBancoAreia;

    /**
     * Construtor da classe BancoAreia.
     *
     * @param localizacao A localização do banco de areia.
     * @throws NullPointerException Se a localização for nula.
     */
    public BancoAreia(Localizacao localizacao) {
        if(localizacao == null) {
            throw new NullPointerException("Localização é nula!");
        }
        this.localizacao = localizacao;
        this.imagemBancoAreia = new ImageIcon(getClass().getResource("imagens/areia.png")).getImage();
    }

    /**
     * Pega a imagem do banco de areia que será exibida.
     * @return A imagem do banco de areia.
     */
    @Override
    public Image getImagem() {
        return imagemBancoAreia;
    }

    /**
     * Ações do banco de areia
    */
    @Override
    public void executarAcao(){ }
    
    /**
     * Pega a localização atual do banco de areia
     * @return A localização.
     */
    @Override
    public Localizacao getLocalizacaoAtual() {
        return localizacao;
    }
}

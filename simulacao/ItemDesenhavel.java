package simulacao;
import java.awt.Image;
    
/**
 * O item tem a imagem de si próprio.
 */

public interface ItemDesenhavel extends Item {
    
    /**
     * Pega a imagem do item.
     * @return A imagem do item.
     */
    public Image getImagem();
}

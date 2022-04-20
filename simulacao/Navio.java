package simulacao;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * O tipo de veículo Navio.
 */
public class Navio implements ItemDesenhavel, Ator {
    private Vinho vinho;
    private Image imagemVazia, imagemNavioComVinho;
    private EmpresaNavio empresa;
    private Localizacao localizacaoAtual;
    private Localizacao localizacaoDestino;
    
    /**
     * Construtor da classe Navio
     * @param empresa A empresa de navios.
     * @param localizacao O ponto de partida do navio. 
     * @throws NullPointerException Se a empresa ou a localização for nula.
     */
    public Navio(EmpresaNavio empresa, Localizacao localizacao) {
        imagemVazia = new ImageIcon(getClass().getResource( "imagens/navio.jpg")).getImage();
        imagemNavioComVinho = new ImageIcon(getClass().getResource("imagens/navio+vinho.jpg")).getImage();
        this.empresa = empresa;
        this.localizacaoAtual = localizacao;
        localizacaoDestino = null;
    }
    
    /**
     * Movimenta o navio até o local de destino.
     */
    public void executarAcao() {
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null) {
            Localizacao prox = getLocalizacaoAtual().proximaLocalizacao(destino);
            setLocalizacaoAtual(prox);
            if(prox.equals(destino)) {
                if(vinho != null) {
                    notificarChegadaVinho(vinho);
                    descarregarVinho();
                }
                else {
                   notificarChegadaRetirada();
                }
            }
        }
    }

    /**
     * Pega a localização atual do veículo.
     * @return A localização atual.
     */
    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    /**
     * Define a localização atual do veículo.
     * @param localizacao A localização que está.
     * @throws NullPointerException Se a localização é nula.
     */
    public void setLocalizacaoAtual(Localizacao localizacao) {
        if(localizacao != null) {
            this.localizacaoAtual = localizacao;
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Notifica a empresa da chegada ao local de retirada.
     */
    public void notificarChegadaRetirada(){
        empresa.chegouNaRetirada(this);
    }

    /**
     * Notifica a empresa da chegada ao destino do vinho.
     * @param vinho Vinho que chegou no destino.
     */
    public void notificarChegadaVinho(Vinho vinho) {
        empresa.chegouNoDestino(this, vinho);
    }

    /**
     * Verifica se o navio está disponível.
     * @return True se estiver disponível ou false se não estiver..
     */
    public boolean estaDisponivel() {
        return getLocalizacaoDestino() == null && vinho == null;
    }
    
    /**
     * Recebe uma localização de retirada e salva ela como localização de destino.
     * @param localizacao A localização de retirada.
     */
    public void setLocalizacaoRetirada(Localizacao localizacao) {
        setLocalizacaoDestino(localizacao);
    }
    
    /**
     * Recebe um vinho e define seu destino como local de destino.
     * @param vinho O vinho.
     */
    public void retirada(Vinho vinho) {
        this.vinho = vinho;
        setLocalizacaoDestino(vinho.getDestino());
    }

    /**
     * Descarrega o vinho.
     */
    public void descarregarVinho() {
        vinho = null;
        limparLocalizacaoDestino();
    }

    /**
     * Pega a localização de destino do veículo.
     * @return A localização de destino do navio ou null se ele não estiver indo pra lugar nenhum.
     */
    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    /**
     * Salva a localização de destino do veículo.
     * @param localizacao Para onde o veículo está indo.
     * @throws NullPointerException Se a localização for nula.
     */
    public void setLocalizacaoDestino(Localizacao localizacao) {
        if(localizacao != null) {
            localizacaoDestino = localizacao;
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Apaga a localização de destino.
     */
    public void limparLocalizacaoDestino() {
        localizacaoDestino = null;
    }
    
    /**
     * Define qual imagem é a do estado atual: imagem do navio com o vinho  dentro ou imagem vazia.
     */
    public Image getImagem() {
        if(vinho != null) {
            return imagemNavioComVinho;
        }
        else {
            return imagemVazia;
        }
    }

    /**
     * Return details of the taxi, such as where it is.
     * @return A string representation of the taxi.
     */
   // public String toString()
   // {
   //     return "Vinho at " + getLocalizacaoAtual();
  //  }
}

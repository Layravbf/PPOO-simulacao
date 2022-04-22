package simulacao;

import java.util.Iterator;
/**
 * Representa uma localização no mapa
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Localizacao {

    private int x;
    private int y;

      /**
     * Construtor da classe Localizacao.
     * Representa uma localização na cidade
     * @param x Coordenada x: deve ser maior ou igual a 0.
     * @param y Coordenada y: deve ser maior ou igual a 0.
     * @throws IllegalArgumentException Se a coordenada for negativa.
     */
    public Localizacao(int x, int y) {
        if(x < 0) {
            throw new IllegalArgumentException("Coordenada x negativa: " + x);
        }
        if(y < 0) {
             throw new IllegalArgumentException("Coordenada y negativa: " + y);
        }
        this.x = x;
        this.y = y;
    }
    
    /**
     * Gera a localizacao para se mover visando alcançar o destino e desviando de navios e bancos de areia
     * @param localizacaoDestino Localizacao que se deseja alcancar.
     * @return Localizacao para onde se deve ir
     */
    public Localizacao proximaLocalizacao(Localizacao localizacaoDestino) {
        int destX = localizacaoDestino.getX();
        int destY = localizacaoDestino.getY();
        int offsetX = x < destX ? 1 : x > destX ? -1 : 0;
        int offsetY = y < destY ? 1 : y > destY ? -1 : 0;
        if(offsetX != 0 || offsetY != 0) {
            Localizacao local = new Localizacao(x + offsetX, y + offsetY);
            Iterator<Ator> itens = Simulacao.getAtores().iterator();
            while(itens.hasNext()) {
                Object obj = itens.next();
                if(obj instanceof BancoAreia){
                    BancoAreia banco = (BancoAreia)obj;
                    if((banco.getLocalizacaoAtual().x == local.x) && (banco.getLocalizacaoAtual().y == local.y)){
                        if(offsetX == 1){
                            local = new Localizacao(x + offsetX, y + offsetY + 1);
                        }else if(offsetX == -1){
                            local = new Localizacao(x + offsetX, y + offsetY + 1);
                        }else if(offsetY == 1){
                            local = new Localizacao(x + offsetX + 1 , y + offsetY);
                        }else if(offsetY == -1){
                            local = new Localizacao(x + offsetX + 1 , y + offsetY);
                        }
                    }
                }else if(obj instanceof Navio){
                    Navio navio = (Navio)obj;
                    if((navio.getLocalizacaoAtual().getX() == local.x) && (navio.getLocalizacaoAtual().getY() == local.y)){
                        local = new Localizacao(x + offsetX - 1, y + offsetY + 1);
                    }
                }
            }
            return local;
        }
        else {
            return localizacaoDestino;
        }
    }

    /**
     * Verificacao de igualdade de conteudo de objetos do tipo Localizacao.
     * @return true: se a localizacao é igual.
     *         false: caso contrario.
     */
    public boolean equals(Object outra) {
        if(outra instanceof Localizacao) {
            Localizacao outraLocalizacao = (Localizacao) outra;
            return x == outraLocalizacao.getX() && y == outraLocalizacao.getY();
        }
        else {
            return false;
        }
    }

    /**
     * Pega o valor da coordenada x.
     * @return A coordenada x.
     */
    public int getX() {
        return x;
    }

    /**
     * Pega o valor da coordenada y.
     * @return A coordenada y.
     */
    public int getY() {
        return y;
    }
}

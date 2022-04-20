package simulacao;

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
     * Gera a localizacao para se mover visando alcançar o destino
     * @param localizacaoDestino Localizacao que se deseja alcancar.
     * @return Localizacao para onde se deve ir
     */
    public Localizacao proximaLocalizacao(Localizacao localizacaoDestino) {
    
        int destX = localizacaoDestino.getX();
        int destY = localizacaoDestino.getY();
        int offsetX = x < destX ? 1 : x > destX ? -1 : 0;
        int offsetY = y < destY ? 1 : y > destY ? -1 : 0;
        if(offsetX != 0 || offsetY != 0) {
            return new Localizacao(x + offsetX, y + offsetY);
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
    @Override
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
     * @return A representacao da localizacao.
     
    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }*/

    
    
    /**
     * Calcula o número de movimentos necessários para chegar ao destino.
     * @param destino O destino que se deseja alcançar.
     * @return O número de movimentos necessários.
     */
    public int distancia(Localizacao destino) {
        int xDist = Math.abs(destino.getX() - x);
        int yDist = Math.abs(destino.getY() - y);
        return Math.max(xDist, yDist);
    }
    

    /**
     * Use the top 16 bits for the y value and the bottom for the x.
     * Except for very big grids, this should give a unique hash code
     * for each (x, y) pair.
     * @return A hashcode for the location.
     */
 //   public int hashCode()
  //  {
 //       return (y << 16) + x;
 //   }

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

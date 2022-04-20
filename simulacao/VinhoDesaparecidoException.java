package simulacao;

/**
 * Indica que não há um vinho no local de retirada.
 */
public class VinhoDesaparecidoException extends RuntimeException {
    private Navio navio;

    /**
     * Construtor da classe de exceção VinhoPerdidoException.
     * @param navio O navio que está esperando pelo vinho.
     */
    public VinhoDesaparecidoException(Navio navio) {
        super("Vinho desaparecido no local de retirada!");
    }

    /**
     * Pega o veículo que não havia vinho para ele retirar.
     * @return O veículo que não havia vinho para retirar.
     */
    public Navio getNavio() {
        return navio;
    }
}

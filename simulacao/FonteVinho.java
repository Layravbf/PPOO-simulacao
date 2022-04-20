package simulacao;
import java.util.Random;

/**
 * Gerar os vinhos no mapa durante a execução.
 */
public class FonteVinho implements Ator {
    private Mapa mapa;
    private EmpresaNavio empresa;
    private Random rand;
    private static final double CREATION_PROBABILITY = 0.06;

    /**
     * Construtor da classe FonteVinho.
     * @param mapa O mapa a ser usado.
     * @param empresa A empresa a ser usada.
     * @throws NullPointerException Se o mapa ou a empresa forem nulos.
     */
    public FonteVinho(Mapa mapa, EmpresaNavio empresa) {
        if(mapa == null) {
            throw new NullPointerException("O mapa é nulo.");
        }
        if(empresa == null) {
            throw new NullPointerException("A empresa é nula.");
        }
        this.mapa = mapa;
        this.empresa = empresa;
        rand = new Random(12345);
    }

    /**
     * Gera vinhos aleatoriamente
     * Conta as coletas perdidas
     */
    public void executarAcao()
    {
        if(rand.nextDouble() <= CREATION_PROBABILITY) {
            Vinho vinho = criarVinho();
            if(empresa.solicitacaoRetirada(vinho)) {
                mapa.adicionarItem(vinho);
            }
        }
    }

    /**
     * Cria um novo vinho em locais de retirada e destino diferentes.
     * @return O vinho criado.
     */
    private Vinho criarVinho() {
        int mapaLargura = mapa.getLargura();
        int mapaAltura = mapa.getAltura();

        Localizacao localizacaoRetirada = new Localizacao(rand.nextInt(mapaLargura), rand.nextInt(mapaAltura));
        Localizacao localizacaoDestino;
        do {
            localizacaoDestino = new Localizacao(rand.nextInt(mapaLargura), rand.nextInt(mapaAltura));
        } while(localizacaoRetirada.equals(localizacaoDestino));
        
        return new Vinho(localizacaoRetirada, localizacaoDestino);
    }
}

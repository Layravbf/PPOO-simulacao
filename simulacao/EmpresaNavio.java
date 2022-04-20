package simulacao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;


/**
 * Modelo da operação da companhia de navios.
 * 
 */
public class EmpresaNavio  {
   
    private List<Navio> navios;
    private Mapa mapa;
    private Map<Navio, Vinho> tarefas;

    private static final int NUMBER_OF_NAVIOS = 3;

    /**
     * @param mapa O mapa usado.
     */
    public EmpresaNavio(Mapa mapa) {
        this.mapa = mapa;
        navios = new LinkedList<Navio>();
        tarefas = new HashMap<Navio, Vinho>();
        configurarNavios();
    }

    /**
     * Solicita a retirada de um vinho em questão.
     * @param vinho O vinho que necessita de coleta.
     * @return true ou false, que indica se um navio está disponivel.
     */
    public boolean solicitacaoRetirada(Vinho vinho) {
        Navio navio = agendarNavio();
        if(navio != null) {
            tarefas.put(navio, vinho);
            navio.setLocalizacaoRetirada(vinho.getLocalizacaoRetirada());
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Um navio chegou ao ponto de coleta.
     * @param navio O navio que está no ponto de coleta.
     * @throws MissingPassengerException Se não tiver vinho esperando para ser retirado.
     */
    public void chegouNaRetirada(Navio navio) {
        Vinho vinho = tarefas.remove(navio);
        if(vinho == null) {
            throw new VinhoDesaparecidoException(navio);
        }
        mapa.removerItem(vinho);
        navio.retirada(vinho);
    }
    
    /**
     * Um navio chegou no destino de um vinho.
     * @param navio O navio que está no destino.
     * @param vinho O vinho que está sendo desembarcado.
     */
    public void chegouNoDestino(Navio navio, Vinho vinho) { }

    /**
     * @return A lista de navios.
     */
    public List<Navio> getNavios() {
        return navios;
    }
    
    /**
     * Encontra um navio livre se houver.
     * @return Um navio livre, ou null se não houver nenhum.
     */
    private Navio agendarNavio() {
        Iterator<Navio> it = navios.iterator();
        while(it.hasNext()) {
            Navio navio = it.next();
            if(navio.estaDisponivel()) {
                return navio;
            }
        }
        return null;
    }

    /**
     *  Cria e configura os navios da empresa. 
     * Os navios começam em posições aleatórias do mapa.
     */
    private void configurarNavios() {
        int mapaLargura = mapa.getLargura();
        int mapaAltura = mapa.getAltura();
        Random rand = new Random(12345);

        for(int i = 0; i < NUMBER_OF_NAVIOS; i++){
            Navio navio = new Navio(this, new Localizacao(rand.nextInt(mapaLargura), rand.nextInt(mapaAltura)));
            navios.add(navio);
            mapa.adicionarItem(navio);
        }
   }
}

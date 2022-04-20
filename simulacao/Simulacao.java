package simulacao;

import java.util.LinkedList;
import java.util.List;

/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private List<Ator> atores;

   /**
    * Construtor da classe simulação
    */
    public Simulacao() {
        atores = new LinkedList<Ator>();
        Mapa mapa = new Mapa();
        EmpresaNavio empresa = new EmpresaNavio(mapa);
        FonteVinho fonte = new FonteVinho(mapa, empresa);
        
        atores.addAll(empresa.getNavios());
        atores.add(fonte);
        atores.add(new JanelaSimulacao(mapa));
    }
    
    /**
     * Executa a simulação usando um número de passos.
     * @param numPassos Número de passos
     */
    public void executarSimulacao(int numPassos) {
        for(int i = 0; i < numPassos; i++){
            executarUmPasso();
            esperar(100);
        }
    }

    /**
     * Executa um passo na simulação de cada ator do mapa.
     */
    public void executarUmPasso() {
        for(Ator ator : atores) {
            ator.executarAcao();
        }
    }
    
    /**
     * Espera por um tempo em milisegundos especificado.
     * @param millisegundos O número de milissegundos que se deve aguardar.
     */
    private void esperar(int millisegundos) {
        try {
            Thread.sleep(millisegundos);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

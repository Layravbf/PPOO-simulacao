package simulacao;
import java.util.Iterator;
import java.awt.*;
import javax.swing.*;
    
/**
 * Fornece a visualizacao da simulacao
 * 
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class JanelaSimulacao extends JFrame implements Ator {
    private Mapa mapa;
    private VisaoMapa visaoMapa;
    
    /**
     * Construtor da classe JanelaSimulacao.
     * @param mapa O mapa que será usado.
     */
    public JanelaSimulacao(Mapa mapa) {
        this.mapa = mapa;
        visaoMapa = new VisaoMapa(mapa.getLargura(), mapa.getAltura());
        getContentPane().add(visaoMapa);
        setTitle("Simulator");
        setSize(1000,1000);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        visaoMapa.preparePaint();
        visaoMapa.repaint();    
    }
    
     /**
     * Mostra o estado atual do mapa.
     */
    public void executarAcao() {
        visaoMapa.preparePaint();
        Iterator itens = mapa.getItens();
        while(itens.hasNext()) {
            Object obj = itens.next();
            if(obj instanceof ItemDesenhavel){
                ItemDesenhavel item = (ItemDesenhavel) obj;
                Localizacao location = item.getLocalizacaoAtual();
                visaoMapa.desenharImagem(location.getX(), location.getY(), item.getImagem());
            }
        }
        visaoMapa.repaint();    
    }
    
    /**
     * Fornece uma visualizacao grafica do mapa. Esta eh 
     * uma classe interna que define os componentes da GUI.
     * Ela contém alguns detalhes mais avancados sobre GUI 
     * que voce pode ignorar para realizacao do seu trabalho.
     */ 
    private class VisaoMapa extends JPanel {

        private final int VIEW_SCALING_FACTOR = 6;

        private int mapaLargura, mapaAltura;
        private int xScale, yScale;
        private Dimension tamanho;
        private Graphics g;
        private Image imagemMapa;

        /**
         * Cria um novo componente VisaoMapa.
         */
        public VisaoMapa(int largura, int altura) {
            mapaLargura = largura;
            mapaAltura = altura;
            setBackground(Color.white);
            tamanho = new Dimension(0, 0);
        }

        /**
         * Informa para o gerenciador GUI o tamanho.
         */
        public Dimension getPreferredSize() {
            return new Dimension(mapaLargura * VIEW_SCALING_FACTOR, mapaAltura * VIEW_SCALING_FACTOR);
        }
        
        /**
         * Prepara para um novo ciclo de exibicao. Uma vez que o componente
         * pode ser redimensionado, calcula o "fator de escala" novamente.
         */
        public void preparePaint() {
            if(!tamanho.equals(getSize())) {  // se o tamanho mudou...
                tamanho = getSize();
                imagemMapa = visaoMapa.createImage(tamanho.width, tamanho.height);
                g = imagemMapa.getGraphics();

                xScale = tamanho.width / mapaLargura;
                if(xScale < 1) {
                    xScale = VIEW_SCALING_FACTOR;
                }
                yScale = tamanho.height / mapaAltura;
                if(yScale < 1) {
                    yScale = VIEW_SCALING_FACTOR;
                }
            }
            g.setColor(Color.cyan);
            g.fillRect(0, 0, tamanho.width, tamanho.height);
            g.setColor(Color.gray);
            for(int i = 0, x = 0; x < tamanho.width; i++, x = i * xScale) {
                g.drawLine(x, 0, x, tamanho.height - 1);
            }
            for(int i = 0, y = 0; y < tamanho.height; i++, y = i * yScale) {
                g.drawLine(0, y, tamanho.width - 1, y);
            }
        }
        
         /**
         * Desenha a imagem para um determinado item.
         */
        public void desenharImagem(int x, int y, Image image)  {
            g.drawImage(image, x * xScale + 1, y * yScale + 1,
                        xScale - 1, yScale - 1, this);
        }

        /**
         * O componente VisaoMapa precisa ser reexibido. Copia a
         * imagem interna para a tela.
         */
        public void paintComponent(Graphics g) {
            if(imagemMapa != null) {
                g.drawImage(imagemMapa, 0, 0, null);
            }
        }
    }
}

package pso;

import geracao.ConfiguracaoTeste;
import geracao.Simulador;
import java.util.Random;

public class ParticulaPSO {

    private double[][] velocidade;
    private double[][] posicao;
    private final double[] velocidadeMax;
    private final double[] velocidadeMin;
    private double[][] vetorpBest;
    private double pBest;
    private double avaliacao;
    private Simulador simulacao;
    private ConfiguracaoTeste ct;

    
    public ParticulaPSO(Simulador simulacao, ConfiguracaoTeste ct) {
        //a variavel volume vazao é porque as usinas terao como valores os volumes finais e vazaoes defluentes em sequencia
        int numUsinas = ct.getCascata().size();
        int numIntervalos = ct.getQuantidadeDeAnos()*12;
        
        posicao = new double[numUsinas][numIntervalos];
        velocidade = new double[numUsinas][numIntervalos];
        vetorpBest = new double[numUsinas][numIntervalos];
        pBest = Double.MAX_VALUE;
        this.simulacao = simulacao;
        this.ct = ct;
        
        velocidadeMax = new double[ct.getCascata().size()];
        velocidadeMin = new double[ct.getCascata().size()];
        for (int i = 0; i < ct.getCascata().size(); i++) {
            velocidadeMin[i] = (ct.getCascata().get(i).getVolumeMinimo() - ct.getCascata().get(i).getVolumeMaximo());
            velocidadeMax[i] = (ct.getCascata().get(i).getVolumeMaximo() - ct.getCascata().get(i).getVolumeMinimo());
        }
    }

    public void inicializaParticula() {
        for (int i = 0; i < posicao.length; i++) {
            for (int j = 0; j < posicao[0].length; j++) {
                posicao[i][j] = ct.getCascata().get(i).getVolumeMaximo();
            }
        }
    }

    public void atualizaVelocidade(double c1, double c2, double[][] gBest) {
        Random r = new Random();
        double r1 = r.nextDouble();
        double r2 = r.nextDouble();

        int numUsinas = posicao.length;
        int numIntervalos = posicao[0].length;

        for (int i = 0; i < numUsinas; i++) {
            for (int j = 0; j < numIntervalos; j++) {
                velocidade[i][j] = velocidade[i][j] + c1 * r1 * (vetorpBest[i][j] - posicao[i][j]) + c2 * r2 * (gBest[i][j] - posicao[i][j]);
            
                if(velocidade[i][j] < (velocidadeMin[i] - velocidadeMax[i])/10.0){
                    velocidade[i][j] = (velocidadeMin[i] - velocidadeMax[i])/10.0;
                } else {
                    if(velocidade[i][j] > (velocidadeMax[i] - velocidadeMin[i])/10){
                        velocidade[i][j] = (velocidadeMax[i] - velocidadeMin[i])/10;
                    }
                }
            }
        }
    }

    
    public void atualizaPosicao() {
        int numUsinas = posicao.length;
        int numIntervalos = posicao[0].length;
        for (int i = 0; i < numUsinas; i++) {
            for (int j = 0; j < numIntervalos; j++) {
                posicao[i][j] = posicao[i][j] + velocidade[i][j];
                
                if (posicao[i][j] > ct.getCascata().get(i).getVolumeMaximo()) {
                    posicao[i][j] = ct.getCascata().get(i).getVolumeMaximo();
                }
                
                if (posicao[i][j] < ct.getCascata().get(i).getVolumeMinimo()) {
                    posicao[i][j] = ct.getCascata().get(i).getVolumeMinimo();
                }
            }
        }
    }

    public void avaliaParticula() {
        int numUsinas = posicao.length;
        int numIntervalos = posicao[0].length;
        simulacao = new Simulador(ct, posicao);
        avaliacao = simulacao.simulacaoOperacaoEnergeticaCascataPSO();
        
        if (avaliacao < pBest) {
            pBest = avaliacao;
            for (int i = 0; i < posicao.length; i++) {
                System.arraycopy(posicao[i], 0, vetorpBest[i], 0, numIntervalos);
            }
        }
    }
    
    //inicializo a velocidade com valores aleatórios pequenos
    public void inicializaVelocidade() {
        Random r = new Random();

        for (int i = 0; i < posicao.length; i++) {
            for (int j = 0; j < posicao[0].length; j++) {
                velocidade[i][j] = (-2)*r.nextDouble();
            }
        }
    }
    
    
    public void imprimePosicao(){
        System.out.println("POSICAO");
        for (int i = 0; i < posicao.length; i++) {
            if(i != 0){
                System.out.println();
            }
            for (int j = 0; j < posicao[0].length; j++) {
//                if(j == posicao[0].length - 1){
                    System.out.println(String.format("%.10f", posicao[i][j]));
//                } else {
//                    System.out.print(posicao[i][j] + ", ");
//                }
            }
        }
        System.out.println("\n");
    }
    
    public void imprimePosicaoFinal(double[] volumesMaximos, double[] volumesMinimos){
        System.out.println("POSICAO");
        for (int i = 0; i < posicao.length; i++) {
            System.out.println();
            for (int j = 0; j < posicao[0].length; j++) {
                System.out.println(String.format("%.10f", ((posicao[i][j] - volumesMinimos[i])/(volumesMaximos[i] - volumesMinimos[i]))));
            }
        }
        System.out.println("\n");
    }
    
    
    
    public void imprimeVolumes() {
        System.out.println("Partícula");
        for (int i = 0; i < posicao.length; i++) {
            if (i != 0) {
                System.out.print("\n{");
            } else {
                System.out.print("{{");
            }

            for (int j = 0; j < posicao[0].length / 2; j++) {
                if (j == posicao[0].length / 2 - 1 && i != posicao.length - 1) {
                    System.out.print(posicao[i][j] + "},");
                } else if (j == posicao[0].length / 2 - 1 && i == posicao.length - 1) {
                    System.out.print(posicao[i][j] + "}}");
                } else {
                    System.out.print(posicao[i][j] + ", ");
                }
            }
        }

        System.out.print("\n\nDefluências");
        for (int i = 0; i < posicao.length; i++) {
            System.out.println();
            for (int j = posicao[0].length / 2; j < posicao[0].length; j++) {
                System.out.println(posicao[i][j]);
            }
        }
        System.out.println();
    }
    
    
    
    
    public void imprimeVelocidade(){
        System.out.println("\nVELOCIDADE");
        for (int i = 0; i < velocidade.length; i++) {
            if(i != 0){
                System.out.println();
            }
            for (int j = 0; j < velocidade[0].length; j++) {
                if(j == velocidade[0].length - 1){
                    System.out.print(velocidade[i][j]);
                } else {
                    System.out.print(velocidade[i][j] + ", ");
                }
            }
        }
        System.out.println("\n");
    }
    
    public void imprimePosicaoPBest(){
        for (int i = 0; i < vetorpBest.length; i++) {
            if(i != 0){
                System.out.println();
            }
            for (int j = 0; j < vetorpBest[0].length; j++) {
                if(j == vetorpBest[0].length - 1){
                    System.out.print(vetorpBest[i][j]);
                } else {
                    System.out.print(vetorpBest[i][j] + ", ");
                }
            }
        }
        System.out.println();
    }
    
    
    
    public double[][] getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double[][] velocidade) {
        this.velocidade = velocidade;
    }

    public double[][] getPosicao() {
        return posicao;
    }

    public void setPosicao(double[][] posicao) {
        this.posicao = posicao;
    }

    public double[][] getVetorpBest() {
        return vetorpBest;
    }

    public void setVetorpBest(double[][] vetorpBest) {
        this.vetorpBest = vetorpBest;
    }

    public double getpBest() {
        return pBest;
    }

    public void setpBest(double pBest) {
        this.pBest = pBest;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Simulador getSimulacao() {
        return simulacao;
    }

    public void setSimulacao(Simulador simulacao) {
        this.simulacao = simulacao;
    }

    public ConfiguracaoTeste getCt() {
        return ct;
    }

    public void setCt(ConfiguracaoTeste ct) {
        this.ct = ct;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso;

import geracao.ConfiguracaoTeste;
import geracao.Simulador;

/**
 *
 * @author Anderson Aragao
 */
public class Otimizador {
    
    public static void main(String[] args) {
        ConfiguracaoTeste ct = new ConfiguracaoTeste(2, 0.8, 1);
        Simulador s = new Simulador();
        int numeroIteracoes = 5000;
        PSO pso = new PSO(s, 4500, ct, 24, 2, 2);
        
        pso.inicializaParticulas();
        for (int i = 0; i < numeroIteracoes; i++) {
           // System.out.println("=====================================");
            pso.avaliaParticulas();
            pso.ObterGbest();
            System.out.println(String.format("%.10f", pso.getgBest().getAvaliacao()));
            pso.atualizaVelocidade();
            pso.atualizaPosicao();
        }
        
        System.out.println(String.format("%.10f", pso.getgBest().getAvaliacao()));
        pso.getgBest().imprimeVelocidade();
        double[] volumesMaximos = {ct.getCascata().get(0).getVolumeMaximo(), ct.getCascata().get(1).getVolumeMaximo(), ct.getCascata().get(2).getVolumeMaximo()};
        double[] volumesMinimos = {ct.getCascata().get(0).getVolumeMinimo(), ct.getCascata().get(1).getVolumeMinimo(), ct.getCascata().get(2).getVolumeMinimo()};
        pso.getgBest().imprimePosicaoFinal(volumesMaximos, volumesMinimos);
    }
}

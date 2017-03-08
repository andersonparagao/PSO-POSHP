/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geracao;

import java.util.List;
import java.util.Random;
import usina.Hidroeletrica;

/**
 *
 * @author Anderson Aragao
 */
public class IdeaProfRicardo {

    ConfiguracaoTeste ct = new ConfiguracaoTeste(5, 1, 1);
    Simulador s = new Simulador();

//    //1936 - 1941 5 anos
//    double[][] vazoesNaturais = {{371, 251, 227, 172, 145, 152, 206, 335, 599, 207, 367, 454,
//                                  304, 271, 201, 155, 139, 196, 387, 664, 793, 539, 557, 434,
//                                  262, 224, 211, 151, 125, 142, 210, 566, 844, 882, 388, 328,
//                                  284, 240, 178, 158, 126, 141, 267, 326, 582, 962, 925, 465,
//                                  365, 272, 211, 159, 128, 153, 444, 389, 789, 504, 422, 511},
//
//                                 {1255, 850, 768, 582, 491, 513, 695, 1134, 2023, 699, 1242, 1535,
//                                  1029, 915, 679, 523, 469, 661, 1308, 2244, 2679, 1820, 1882, 1466,
//                                  885, 756, 714, 510, 421, 480, 711, 1911, 2852, 2981, 1310, 1107,
//                                  961, 809, 601, 533, 426, 477, 901, 1100, 1967, 3251, 3127, 1571,
//                                  1234, 919, 713, 538, 431, 517, 1500, 1315, 2668, 1702, 1426, 1726},
//
//                                 {1723, 1166, 1054, 799, 674, 704, 954, 1556, 2776, 960, 1704, 2106,
//                                  1412, 1256, 932, 718, 644, 908, 1795, 3079, 3677, 2498, 2583, 2013,
//                                  1215, 1038, 980, 701, 578, 659, 976, 2623, 3914, 4091, 1798, 1520,
//                                  1319, 1111, 825, 731, 585, 655, 1237, 1510, 2700, 4461, 4292, 2156,
//                                  1693, 1262, 979, 739, 592, 710, 2058, 1805, 3661, 2335, 1957, 2369}};
//
//    
    //mlt 2 anos
    double[][] vazoesNaturais = {{399, 306, 245, 196, 173, 206, 356, 656, 894, 904, 863, 623, 399, 306, 245, 196, 173, 206, 356, 656, 894, 904, 863, 623},
    {1331, 1024, 817, 655, 584, 704, 1147, 1971, 2727, 2850, 2765, 2043, 1331, 1024, 817, 655, 584, 704, 1147, 1971, 2727, 2850, 2765, 2043},
    {2084, 1623, 1309, 1067, 974, 1148, 1766, 2951, 4055, 4253, 4183, 3156, 2084, 1623, 1309, 1067, 974, 1148, 1766, 2951, 4055, 4253, 4183, 3156}};

    double[][] volumesFinais = new double[vazoesNaturais.length][vazoesNaturais[0].length];
    double[][] volumesIniciais = new double[vazoesNaturais.length][vazoesNaturais[0].length];
    double[][] volumesMedios = new double[vazoesNaturais.length][vazoesNaturais[0].length];
    double[][] vazoesDefluentes = new double[vazoesNaturais.length][vazoesNaturais[0].length];
    double[][] cotasMontante = new double[vazoesNaturais.length][vazoesNaturais[0].length];
    double[][] cotasJusante = new double[vazoesNaturais.length][vazoesNaturais[0].length];
    double[][] alturasDeQuedaLiquida = new double[vazoesNaturais.length][vazoesNaturais[0].length];
    double[][] engolimentoMaximo = new double[vazoesNaturais.length][vazoesNaturais[0].length];
    double[][] alturaQuedaLiquidaEngolimento = new double[vazoesNaturais.length][vazoesNaturais[0].length];
    double[][] potenciaMaxima = new double[vazoesNaturais.length][vazoesNaturais[0].length];
    double[][] geracaoHidraulicaMaxContinua = new double[vazoesNaturais.length][vazoesNaturais[0].length];
    double[][] limiteMaxVazaoTurbinada = new double[vazoesNaturais.length][vazoesNaturais[0].length];
    double[][] vazaoTurbinada = new double[vazoesNaturais.length][vazoesNaturais[0].length];
    double[][] vazaoVertida = new double[vazoesNaturais.length][vazoesNaturais[0].length];
    double[][] geracaoHidraulica = new double[vazoesNaturais.length][vazoesNaturais[0].length];
    double[] geracoesHidraulicaConjunto = new double[vazoesNaturais[0].length];
    double[] complementacaoTermica = new double[vazoesNaturais[0].length];
    double mediaComplementacaoTermica = 0;
    double mediaComplementacaoTermicaAnterior = 0;
    double custoDaOperacao = 0;
    double[][] vazoesIncrementais = calculaVazaoIncremental(ct.getCascata(), vazoesNaturais);

    double demanda = ((ct.getCascata().get(0).getPotenciaEfetiva()[0] * ct.getCascata().get(0).getNumueroDeConjuntoDeMaquinas()[0])
            + (ct.getCascata().get(1).getPotenciaEfetiva()[0] * ct.getCascata().get(1).getNumueroDeConjuntoDeMaquinas()[0])
            + (ct.getCascata().get(2).getPotenciaEfetiva()[0] * ct.getCascata().get(2).getNumueroDeConjuntoDeMaquinas()[0]));

    public ConfiguracaoTeste getCt() {
        return ct;
    }

    public void setCt(ConfiguracaoTeste ct) {
        this.ct = ct;
    }

    public double[][] calculaVazaoIncremental(List<Hidroeletrica> cascata, double[][] vazoesNaturais) {
        double[][] vazoesIncrementais = new double[vazoesNaturais.length][vazoesNaturais[0].length];
        for (int i = 0; i < vazoesNaturais.length; i++) {
            for (int j = 0; j < vazoesNaturais[0].length; j++) {
                if (i == 0) {
                    vazoesIncrementais[i][j] = vazoesNaturais[i][j];
                } else {
                    vazoesIncrementais[i][j] = vazoesNaturais[i][j] - vazoesNaturais[i - 1][j];
                }
            }
        }

        return vazoesIncrementais;
    }

    public void corrigeConflitos(int usina, int indice) {

        if (volumesFinais[usina][indice] >= ct.getCascata().get(usina).getVolumeMaximo()) {
            volumesFinais[usina][indice] = ct.getCascata().get(usina).getVolumeMaximo();
        }

        if (volumesFinais[usina][indice] <= ct.getCascata().get(usina).getVolumeMinimo()) {
            volumesFinais[usina][indice] = ct.getCascata().get(usina).getVolumeMinimo();
        }

        if (indice == 0) {
            volumesIniciais[usina][indice] = ct.getCascata().get(usina).getVolumeMaximo();
        } else {
            volumesIniciais[usina][indice] = volumesFinais[usina][indice - 1];
        }

        if (usina == 0) {
            vazoesDefluentes[usina][indice] = s.calculaVazaoDefluente(ct.getCascata().get(usina), volumesIniciais[usina][indice], volumesFinais[usina][indice], 0, vazoesIncrementais[usina][indice], 0);
        } else {
            vazoesDefluentes[usina][indice] = s.calculaVazaoDefluente(ct.getCascata().get(usina), volumesIniciais[usina][indice], volumesFinais[usina][indice], vazoesDefluentes[usina - 1][indice], vazoesIncrementais[usina][indice], 0);
        }
    }

    public void simulaOperacaoEnergeticaInicial() {
        Random r = new Random();
        for (int i = 0; i < vazoesNaturais.length; i++) {
            for (int j = 0; j < vazoesNaturais[0].length; j++) {
                // caso seja o mês inicial
                if (j == 0) {
                    volumesIniciais[i][j] = ct.getCascata().get(i).getVolumeMaximo();
                } else {
                    volumesIniciais[i][j] = volumesFinais[i][j - 1];
                }

                //calculo dos limites max e min da vazão defluente
                double engolimentoMaximoVolumeInicial = s.calculaEngolimentoMaximo(ct.getCascata().get(i), volumesIniciais[i][j], ct.getCascata().get(i).getVazaoDefluenteMinima(), 1);
                double vazaoDefluenteMaxima = engolimentoMaximoVolumeInicial + 0.1 * engolimentoMaximoVolumeInicial;

                if (volumesIniciais[i][j] >= ct.getCascata().get(i).getVolumeMaximo()) {
                    //gero um número aleatório entre a vazao natural afluente e a vazaoDefluenteMáxima para ser a vazao defluente
                    vazoesDefluentes[i][j] = r.nextDouble() * ((vazaoDefluenteMaxima - vazoesNaturais[i][j])) + vazoesNaturais[i][j];
                } else {
                    //gero um número aleatório entre a vazao defluente mínima e a vazaoDefluenteMáxima para ser a vazao defluente
                    vazoesDefluentes[i][j] = r.nextDouble() * ((vazaoDefluenteMaxima - ct.getCascata().get(i).getVazaoDefluenteMinima())) + ct.getCascata().get(i).getVazaoDefluenteMinima();
                }

                if (i == 0) {
                    volumesFinais[i][j] = volumesIniciais[i][j] + (vazoesIncrementais[i][j] - vazoesDefluentes[i][j]) * (2628000.0 / 1000000.0);
                } else {
                    volumesFinais[i][j] = volumesIniciais[i][j] + (vazoesIncrementais[i][j] + vazoesDefluentes[i - 1][j] - vazoesDefluentes[i][j]) * (2628000.0 / 1000000.0);
                }
                corrigeConflitos(i, j);

                volumesMedios[i][j] = (volumesIniciais[i][j] + volumesFinais[i][j]) / 2.0;
                cotasMontante[i][j] = s.calculaCotaMontante(ct.getCascata().get(i), volumesMedios[i][j]);
                cotasJusante[i][j] = s.calculaCotaJusante(ct.getCascata().get(i), vazoesDefluentes[i][j]);
                alturasDeQuedaLiquida[i][j] = s.calculaAlturaQuedaLiquida(ct.getCascata().get(i), cotasMontante[i][j], cotasJusante[i][j]);
                engolimentoMaximo[i][j] = s.calculaEngolimentoMaximo(ct.getCascata().get(i), volumesMedios[i][j], vazoesDefluentes[i][j], 1);
                alturaQuedaLiquidaEngolimento[i][j] = s.calculaAlturaQuedaLiquidaDoEngolimentoMaximo(ct.getCascata().get(i), volumesMedios[i][j], vazoesDefluentes[i][j], 1);
                potenciaMaxima[i][j] = s.calculaPotenciaMaxima(ct.getCascata().get(i), alturaQuedaLiquidaEngolimento[i][j]);
                geracaoHidraulicaMaxContinua[i][j] = s.calculaGeracaoHidraulicaMaximaContinua(ct.getCascata().get(i), potenciaMaxima[i][j]);
                limiteMaxVazaoTurbinada[i][j] = s.calculaLimiteMaximoVazaoTurbinada(ct.getCascata().get(i), volumesMedios[i][j], geracaoHidraulicaMaxContinua[i][j]);
                vazaoTurbinada[i][j] = s.calculaVazaoTurbinada(vazoesDefluentes[i][j], limiteMaxVazaoTurbinada[i][j], engolimentoMaximo[i][j]);
                vazaoVertida[i][j] = s.calculaVazaoVertida(vazoesDefluentes[i][j], limiteMaxVazaoTurbinada[i][j], engolimentoMaximo[i][j]);
                geracaoHidraulica[i][j] = s.calculaGeracaoHidraulica(ct.getCascata().get(i), alturasDeQuedaLiquida[i][j], vazaoTurbinada[i][j]);
                custoDaOperacao = s.calculaCustoOperacaoCascata(ct.getCascata(), demanda, geracoesHidraulicaConjunto, 0.08);
            }
        }

        geracoesHidraulicaConjunto = new double[vazoesNaturais[0].length];
        for (int i = 0; i < geracaoHidraulica[0].length; i++) {
            for (int j = 0; j < geracaoHidraulica.length; j++) {
                geracoesHidraulicaConjunto[i] += geracaoHidraulica[j][i];
            }
        }

        System.out.println("\nGeração Hidráulica");
        for (int i = 0; i < geracoesHidraulicaConjunto.length; i++) {
            System.out.println(String.format("%.10f", geracoesHidraulicaConjunto[i]));
        }

        double soma = 0;
        System.out.println("\nComplementação Térmica");
        for (int i = 0; i < geracoesHidraulicaConjunto.length; i++) {
            complementacaoTermica[i] = demanda - geracoesHidraulicaConjunto[i];
            soma += complementacaoTermica[i];
            System.out.println(String.format("%.10f", complementacaoTermica[i]));
        }

        System.out.println("Custo da Operação = " + String.format("%.10f", custoDaOperacao));

        mediaComplementacaoTermica = soma / geracoesHidraulicaConjunto.length;
        System.out.println("Media DA CT = " + mediaComplementacaoTermica);

        System.out.println("\nVolumes Finais");
        for (int i = 0; i < volumesFinais.length; i++) {
            for (int j = 0; j < volumesFinais[0].length; j++) {
                System.out.println(String.format("%.10f", volumesFinais[i][j]));
            }
            System.out.println();
        }

        System.out.println("\nVolumes Iniciais");
        for (int i = 0; i < volumesFinais.length; i++) {
            for (int j = 0; j < volumesFinais[0].length; j++) {
                System.out.println(String.format("%.10f", volumesIniciais[i][j]));
            }
            System.out.println();
        }

        System.out.println("\nVazões Defluentes");
        for (int i = 0; i < volumesFinais.length; i++) {
            for (int j = 0; j < volumesFinais[0].length; j++) {
                System.out.println(String.format("%.10f", vazoesDefluentes[i][j]));
            }
            System.out.println();
        }

    }

    public void simulaOperacaoEnergetica() {

        for (int i = 0; i < vazoesNaturais.length; i++) {
            for (int j = 0; j < vazoesNaturais[0].length; j++) {
                // caso seja o mês inicial
                if (j == 0) {
                    volumesIniciais[i][j] = ct.getCascata().get(i).getVolumeMaximo();
                } else {
                    volumesIniciais[i][j] = volumesFinais[i][j - 1];
                }

                volumesMedios[i][j] = (volumesIniciais[i][j] + volumesFinais[i][j]) / 2.0;
                cotasMontante[i][j] = s.calculaCotaMontante(ct.getCascata().get(i), volumesMedios[i][j]);
                cotasJusante[i][j] = s.calculaCotaJusante(ct.getCascata().get(i), vazoesDefluentes[i][j]);
                alturasDeQuedaLiquida[i][j] = s.calculaAlturaQuedaLiquida(ct.getCascata().get(i), cotasMontante[i][j], cotasJusante[i][j]);
                engolimentoMaximo[i][j] = s.calculaEngolimentoMaximo(ct.getCascata().get(i), volumesMedios[i][j], vazoesDefluentes[i][j], 1);
                alturaQuedaLiquidaEngolimento[i][j] = s.calculaAlturaQuedaLiquidaDoEngolimentoMaximo(ct.getCascata().get(i), volumesMedios[i][j], vazoesDefluentes[i][j], 1);
                potenciaMaxima[i][j] = s.calculaPotenciaMaxima(ct.getCascata().get(i), alturaQuedaLiquidaEngolimento[i][j]);
                geracaoHidraulicaMaxContinua[i][j] = s.calculaGeracaoHidraulicaMaximaContinua(ct.getCascata().get(i), potenciaMaxima[i][j]);
                limiteMaxVazaoTurbinada[i][j] = s.calculaLimiteMaximoVazaoTurbinada(ct.getCascata().get(i), volumesMedios[i][j], geracaoHidraulicaMaxContinua[i][j]);
                vazaoTurbinada[i][j] = s.calculaVazaoTurbinada(vazoesDefluentes[i][j], limiteMaxVazaoTurbinada[i][j], engolimentoMaximo[i][j]);
                vazaoVertida[i][j] = s.calculaVazaoVertida(vazoesDefluentes[i][j], limiteMaxVazaoTurbinada[i][j], engolimentoMaximo[i][j]);
                geracaoHidraulica[i][j] = s.calculaGeracaoHidraulica(ct.getCascata().get(i), alturasDeQuedaLiquida[i][j], vazaoTurbinada[i][j]);
                custoDaOperacao = s.calculaCustoOperacaoCascata(ct.getCascata(), demanda, geracoesHidraulicaConjunto, 0.08);
            }
        }

        geracoesHidraulicaConjunto = new double[vazoesNaturais[0].length];;
        for (int i = 0; i < geracaoHidraulica[0].length; i++) {
            for (int j = 0; j < geracaoHidraulica.length; j++) {
                geracoesHidraulicaConjunto[i] += geracaoHidraulica[j][i];
            }
        }

        System.out.println("\nGeração Hidráulica");
        for (int i = 0; i < geracoesHidraulicaConjunto.length; i++) {
            System.out.println(String.format("%.10f", geracoesHidraulicaConjunto[i]));
        }

        double soma = 0;
        System.out.println("\nComplementação Térmica");
        for (int i = 0; i < geracoesHidraulicaConjunto.length; i++) {
            complementacaoTermica[i] = demanda - geracoesHidraulicaConjunto[i];
            soma += complementacaoTermica[i];
            System.out.println(String.format("%.10f", complementacaoTermica[i]));
        }

        System.out.println("Custo da Operação = " + String.format("%.10f", custoDaOperacao));

        mediaComplementacaoTermica = soma / geracoesHidraulicaConjunto.length;
        System.out.println("Media DA CT = " + mediaComplementacaoTermica);

        System.out.println("\nVolumes Finais");
        for (int i = 0; i < volumesFinais.length; i++) {
            for (int j = 0; j < volumesFinais[0].length; j++) {
                System.out.println(String.format("%.10f", volumesFinais[i][j]));
            }
            System.out.println();
        }
    }

    public double simulaOperacaoEnergetica(double[][] volumesFinais) {
        this.volumesFinais = volumesFinais;
        for (int i = 0; i < vazoesNaturais.length; i++) {
            for (int j = 0; j < vazoesNaturais[0].length; j++) {
                // caso seja o mês inicial
                if (j == 0) {
                    volumesIniciais[i][j] = ct.getCascata().get(i).getVolumeMaximo();
                } else {
                    volumesIniciais[i][j] = this.volumesFinais[i][j - 1];
                }

                if (i == 0) {
                    vazoesDefluentes[i][j] = s.calculaVazaoDefluente(ct.getCascata().get(i), volumesIniciais[i][j], volumesFinais[i][j], 0, vazoesIncrementais[i][j], 0);
                } else {
                    vazoesDefluentes[i][j] = s.calculaVazaoDefluente(ct.getCascata().get(i), volumesIniciais[i][j], volumesFinais[i][j], vazoesDefluentes[i - 1][j], vazoesIncrementais[i][j], 0);
                }

                volumesMedios[i][j] = (volumesIniciais[i][j] + this.volumesFinais[i][j]) / 2.0;
                cotasMontante[i][j] = s.calculaCotaMontante(ct.getCascata().get(i), volumesMedios[i][j]);
                cotasJusante[i][j] = s.calculaCotaJusante(ct.getCascata().get(i), vazoesDefluentes[i][j]);
                alturasDeQuedaLiquida[i][j] = s.calculaAlturaQuedaLiquida(ct.getCascata().get(i), cotasMontante[i][j], cotasJusante[i][j]);
                engolimentoMaximo[i][j] = s.calculaEngolimentoMaximo(ct.getCascata().get(i), volumesMedios[i][j], vazoesDefluentes[i][j], 1);
                alturaQuedaLiquidaEngolimento[i][j] = s.calculaAlturaQuedaLiquidaDoEngolimentoMaximo(ct.getCascata().get(i), volumesMedios[i][j], vazoesDefluentes[i][j], 1);
                potenciaMaxima[i][j] = s.calculaPotenciaMaxima(ct.getCascata().get(i), alturaQuedaLiquidaEngolimento[i][j]);
                geracaoHidraulicaMaxContinua[i][j] = s.calculaGeracaoHidraulicaMaximaContinua(ct.getCascata().get(i), potenciaMaxima[i][j]);
                limiteMaxVazaoTurbinada[i][j] = s.calculaLimiteMaximoVazaoTurbinada(ct.getCascata().get(i), volumesMedios[i][j], geracaoHidraulicaMaxContinua[i][j]);
                vazaoTurbinada[i][j] = s.calculaVazaoTurbinada(vazoesDefluentes[i][j], limiteMaxVazaoTurbinada[i][j], engolimentoMaximo[i][j]);
                vazaoVertida[i][j] = s.calculaVazaoVertida(vazoesDefluentes[i][j], limiteMaxVazaoTurbinada[i][j], engolimentoMaximo[i][j]);
                geracaoHidraulica[i][j] = s.calculaGeracaoHidraulica(ct.getCascata().get(i), alturasDeQuedaLiquida[i][j], vazaoTurbinada[i][j]);
                custoDaOperacao = s.calculaCustoOperacaoCascata(ct.getCascata(), demanda, geracoesHidraulicaConjunto, 0.08);
            }
        }

        geracoesHidraulicaConjunto = new double[vazoesNaturais[0].length];;
        for (int i = 0; i < geracaoHidraulica[0].length; i++) {
            for (int j = 0; j < geracaoHidraulica.length; j++) {
                geracoesHidraulicaConjunto[i] += geracaoHidraulica[j][i];
            }
        }

        System.out.println("\nGeração Hidráulica");
        for (int i = 0; i < geracoesHidraulicaConjunto.length; i++) {
            System.out.println(String.format("%.10f", geracoesHidraulicaConjunto[i]));
        }

        double soma = 0;
        System.out.println("\nComplementação Térmica");
        for (int i = 0; i < geracoesHidraulicaConjunto.length; i++) {
            complementacaoTermica[i] = demanda - geracoesHidraulicaConjunto[i];
            soma += complementacaoTermica[i];
            System.out.println(String.format("%.10f", complementacaoTermica[i]));
        }

        System.out.println("Custo da Operação = " + String.format("%.10f", custoDaOperacao));

        mediaComplementacaoTermica = soma / geracoesHidraulicaConjunto.length;
        System.out.println("Media DA CT = " + mediaComplementacaoTermica);

        System.out.println("\nVolumes Finais");
        for (int i = 0; i < volumesFinais.length; i++) {
            for (int j = 0; j < volumesFinais[0].length; j++) {
                System.out.println(String.format("%.10f", volumesFinais[i][j]));
            }
            System.out.println();
        }

        return custoDaOperacao;
    }

    public void operadorMutacao() {
        double[][] volumesFinaisNovo = new double[vazoesNaturais.length][vazoesNaturais[0].length];;
        double porcentagem1 = 0.03;
        double porcentagem2 = 0.02;
        double porcentagem3 = 0.01;

        for (int i = 0; i < volumesFinaisNovo.length; i++) {
            for (int j = 1; j < volumesFinaisNovo[0].length - 1; j++) {
                if (complementacaoTermica[j] > mediaComplementacaoTermica) {
                    // ct > media + 60% media
                    if (complementacaoTermica[j] >= (mediaComplementacaoTermica + 0.6 * mediaComplementacaoTermica)) {
                        volumesFinaisNovo[i][j] = volumesFinais[i][j] - porcentagem1 * volumesFinais[i][j];
                    } else // media + 30% media < ct < media + 60% media
                    {
                        if ((complementacaoTermica[j] >= (mediaComplementacaoTermica + 0.3 * mediaComplementacaoTermica)) && (complementacaoTermica[j] < (mediaComplementacaoTermica + 0.6 * mediaComplementacaoTermica))) {
                            volumesFinaisNovo[i][j] = volumesFinais[i][j] - porcentagem2 * volumesFinais[i][j];
                        } else // media + 10% < ct < media + 30%    
                        {
                            if ((complementacaoTermica[j] >= (mediaComplementacaoTermica + 0.1 * mediaComplementacaoTermica)) && (complementacaoTermica[j] < (mediaComplementacaoTermica + 0.3 * mediaComplementacaoTermica))) {
                                volumesFinaisNovo[i][j] = volumesFinais[i][j] - porcentagem3 * volumesFinais[i][j];
                            } else {
                                // caso não se enquadre em nenhum dos anteriores, eu não altero o volume da usina
                                volumesFinaisNovo[i][j] = volumesFinais[i][j];
                            }
                        }
                    }

                    volumesFinais[i][j] = volumesFinaisNovo[i][j];
                    corrigeConflitos(i, j);
                } else {
                    // ct < media - 60% media
                    if (complementacaoTermica[j] <= (mediaComplementacaoTermica - 0.6 * mediaComplementacaoTermica)) {
                        volumesFinaisNovo[i][j] = volumesFinais[i][j] + porcentagem1 * volumesFinais[i][j];
                    } else // media - 60% media < ct < media - 30% media
                    {
                        if ((complementacaoTermica[j] <= (mediaComplementacaoTermica - 0.3 * mediaComplementacaoTermica)) && (complementacaoTermica[j] > (mediaComplementacaoTermica - 0.6 * mediaComplementacaoTermica))) {
                            volumesFinaisNovo[i][j] = volumesFinais[i][j] + porcentagem2 * volumesFinais[i][j];
                        } else // media - 30% < ct < media - 10%
                        {
                            if ((complementacaoTermica[j] <= (mediaComplementacaoTermica - 0.1 * mediaComplementacaoTermica)) && (complementacaoTermica[j] > (mediaComplementacaoTermica - 0.3 * mediaComplementacaoTermica))) {
                                volumesFinaisNovo[i][j] = volumesFinais[i][j] + porcentagem3 * volumesFinais[i][j];
                            } else {
                                // caso não se enquadre em nenhum dos anteriores, eu não altero o volume da usina
                                volumesFinaisNovo[i][j] = volumesFinais[i][j];
                            }
                        }
                    }
                    volumesFinais[i][j] = volumesFinaisNovo[i][j];
                    corrigeConflitos(i, j);
                }
            }
        }
    }

    
    public void executa(int quantidade) {
        simulaOperacaoEnergeticaInicial();
        for (int i = 0; i < quantidade; i++) {
            operadorMutacao();
            simulaOperacaoEnergetica();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
    //novas funções para utilizar no AG
    public double[][] inicializaIndividuo() {
        Random r = new Random();
        for (int i = 0; i < vazoesNaturais.length; i++) {
            for (int j = 0; j < vazoesNaturais[0].length; j++) {
                // caso seja o mês inicial
                if (j == 0) {
                    volumesIniciais[i][j] = ct.getCascata().get(i).getVolumeMaximo();
                } else {
                    volumesIniciais[i][j] = volumesFinais[i][j - 1];
                }

                //calculo dos limites max e min da vazão defluente
                double engolimentoMaximoVolumeInicial = s.calculaEngolimentoMaximo(ct.getCascata().get(i), volumesIniciais[i][j], ct.getCascata().get(i).getVazaoDefluenteMinima(), 1);
                double vazaoDefluenteMaxima = engolimentoMaximoVolumeInicial + 0.1 * engolimentoMaximoVolumeInicial;

                if (volumesIniciais[i][j] >= ct.getCascata().get(i).getVolumeMaximo()) {
                    //gero um número aleatório entre a vazao natural afluente e a vazaoDefluenteMáxima para ser a vazao defluente
                    vazoesDefluentes[i][j] = r.nextDouble() * ((vazaoDefluenteMaxima - vazoesNaturais[i][j])) + vazoesNaturais[i][j];
                } else {
                    //gero um número aleatório entre a vazao defluente mínima e a vazaoDefluenteMáxima para ser a vazao defluente
                    vazoesDefluentes[i][j] = r.nextDouble() * ((vazaoDefluenteMaxima - ct.getCascata().get(i).getVazaoDefluenteMinima())) + ct.getCascata().get(i).getVazaoDefluenteMinima();
                }

                if (i == 0) {
                    volumesFinais[i][j] = volumesIniciais[i][j] + (vazoesIncrementais[i][j] - vazoesDefluentes[i][j]) * (2628000.0 / 1000000.0);
                } else {
                    volumesFinais[i][j] = volumesIniciais[i][j] + (vazoesIncrementais[i][j] + vazoesDefluentes[i - 1][j] - vazoesDefluentes[i][j]) * (2628000.0 / 1000000.0);
                }
                corrigeConflitos(i, j);

                volumesMedios[i][j] = (volumesIniciais[i][j] + volumesFinais[i][j]) / 2.0;
                cotasMontante[i][j] = s.calculaCotaMontante(ct.getCascata().get(i), volumesMedios[i][j]);
                cotasJusante[i][j] = s.calculaCotaJusante(ct.getCascata().get(i), vazoesDefluentes[i][j]);
                alturasDeQuedaLiquida[i][j] = s.calculaAlturaQuedaLiquida(ct.getCascata().get(i), cotasMontante[i][j], cotasJusante[i][j]);
                engolimentoMaximo[i][j] = s.calculaEngolimentoMaximo(ct.getCascata().get(i), volumesMedios[i][j], vazoesDefluentes[i][j], 1);
                alturaQuedaLiquidaEngolimento[i][j] = s.calculaAlturaQuedaLiquidaDoEngolimentoMaximo(ct.getCascata().get(i), volumesMedios[i][j], vazoesDefluentes[i][j], 1);
                potenciaMaxima[i][j] = s.calculaPotenciaMaxima(ct.getCascata().get(i), alturaQuedaLiquidaEngolimento[i][j]);
                geracaoHidraulicaMaxContinua[i][j] = s.calculaGeracaoHidraulicaMaximaContinua(ct.getCascata().get(i), potenciaMaxima[i][j]);
                limiteMaxVazaoTurbinada[i][j] = s.calculaLimiteMaximoVazaoTurbinada(ct.getCascata().get(i), volumesMedios[i][j], geracaoHidraulicaMaxContinua[i][j]);
                vazaoTurbinada[i][j] = s.calculaVazaoTurbinada(vazoesDefluentes[i][j], limiteMaxVazaoTurbinada[i][j], engolimentoMaximo[i][j]);
                vazaoVertida[i][j] = s.calculaVazaoVertida(vazoesDefluentes[i][j], limiteMaxVazaoTurbinada[i][j], engolimentoMaximo[i][j]);
                geracaoHidraulica[i][j] = s.calculaGeracaoHidraulica(ct.getCascata().get(i), alturasDeQuedaLiquida[i][j], vazaoTurbinada[i][j]);
                custoDaOperacao = s.calculaCustoOperacaoCascata(ct.getCascata(), demanda, geracoesHidraulicaConjunto, 0.08);
            }
        }

        geracoesHidraulicaConjunto = new double[vazoesNaturais[0].length];
        for (int i = 0; i < geracaoHidraulica[0].length; i++) {
            for (int j = 0; j < geracaoHidraulica.length; j++) {
                geracoesHidraulicaConjunto[i] += geracaoHidraulica[j][i];
            }
        }


        double soma = 0;
        for (int i = 0; i < geracoesHidraulicaConjunto.length; i++) {
            complementacaoTermica[i] = demanda - geracoesHidraulicaConjunto[i];
            soma += complementacaoTermica[i];
        }

        mediaComplementacaoTermica = soma / geracoesHidraulicaConjunto.length;

        double[][] cromossomo = s.converteCromossomo(ct.getCascata(), volumesFinais);
        
        for (int i = 0; i < cromossomo.length; i++) {
            System.out.println("\n");
            for (int j = 0; j < cromossomo[0].length; j++) {
                System.out.println(String.format("%.10f", cromossomo[i][j]));
                
            }
            
        }

        return cromossomo;
    }

    public double calculaMedia(double[] complementacaoTermicaMensal) {
        double media;
        double soma = 0;
        for (int i = 0; i < complementacaoTermicaMensal.length; i++) {
            soma += complementacaoTermicaMensal[i];
        }
        media = soma/complementacaoTermicaMensal.length;
        
        return media;
    }

    public double[][] operadorMutacaoIndividuo(double[][] volumesFinais, double[] complementacaoTermica) {
        Random r = new Random();
        double[][] volumesFinaisNovo = new double[vazoesNaturais.length][vazoesNaturais[0].length];
        double porcentagem1 = 0.2;  //0.03
        double porcentagem2 = 0.1;  //0.02
        double porcentagem3 = 0.05;  //0.01
        double mediaComplementacaoTermica = calculaMedia(complementacaoTermica);

        for (int i = 0; i < volumesFinaisNovo.length; i++) {
            for (int j = 0; j < volumesFinaisNovo[0].length; j++) {
                if (complementacaoTermica[j] > mediaComplementacaoTermica) {
                    // ct > media + 60% media
                    if (complementacaoTermica[j] >= (mediaComplementacaoTermica + 0.6 * mediaComplementacaoTermica)) {
                        volumesFinaisNovo[i][j] = volumesFinais[i][j] - r.nextDouble() * porcentagem1 * volumesFinais[i][j];
                    } else // media + 30% media < ct < media + 60% media
                    {
                        if ((complementacaoTermica[j] >= (mediaComplementacaoTermica + 0.3 * mediaComplementacaoTermica)) && (complementacaoTermica[j] < (mediaComplementacaoTermica + 0.6 * mediaComplementacaoTermica))) {
                            volumesFinaisNovo[i][j] = volumesFinais[i][j] - r.nextDouble() * porcentagem2 * volumesFinais[i][j];
                        } else // media + 10% < ct < media + 30%    
                        {
                            if ((complementacaoTermica[j] >= (mediaComplementacaoTermica + 0.1 * mediaComplementacaoTermica)) && (complementacaoTermica[j] < (mediaComplementacaoTermica + 0.3 * mediaComplementacaoTermica))) {
                                volumesFinaisNovo[i][j] = volumesFinais[i][j] - r.nextDouble() * porcentagem3 * volumesFinais[i][j];
                            } else {
                                // caso não se enquadre em nenhum dos anteriores, eu não altero o volume da usina
                                volumesFinaisNovo[i][j] = volumesFinais[i][j];
                            }
                        }
                    }

                    if (volumesFinaisNovo[i][j] > ct.getCascata().get(i).getVolumeMaximo()) {
                        volumesFinaisNovo[i][j] = ct.getCascata().get(i).getVolumeMaximo();
                    }

                    if (volumesFinaisNovo[i][j] < ct.getCascata().get(i).getVolumeMinimo()) {
                        volumesFinaisNovo[i][j] = ct.getCascata().get(i).getVolumeMaximo();
                    }

                    volumesFinais[i][j] = volumesFinaisNovo[i][j];
                } else {
                    // ct < media - 60% media
                    if (complementacaoTermica[j] <= (mediaComplementacaoTermica - 0.6 * mediaComplementacaoTermica)) {
                        volumesFinaisNovo[i][j] = volumesFinais[i][j] + r.nextDouble() * porcentagem1 * volumesFinais[i][j];
                    } else // media - 60% media < ct < media - 30% media
                    {
                        if ((complementacaoTermica[j] <= (mediaComplementacaoTermica - 0.3 * mediaComplementacaoTermica)) && (complementacaoTermica[j] > (mediaComplementacaoTermica - 0.6 * mediaComplementacaoTermica))) {
                            volumesFinaisNovo[i][j] = volumesFinais[i][j] + r.nextDouble() * porcentagem2 * volumesFinais[i][j];
                        } else // media - 30% < ct < media - 10%
                        {
                            if ((complementacaoTermica[j] <= (mediaComplementacaoTermica - 0.1 * mediaComplementacaoTermica)) && (complementacaoTermica[j] > (mediaComplementacaoTermica - 0.3 * mediaComplementacaoTermica))) {
                                volumesFinaisNovo[i][j] = volumesFinais[i][j] + r.nextDouble() * porcentagem3 * volumesFinais[i][j];
                            } else {
                                // caso não se enquadre em nenhum dos anteriores, eu não altero o volume da usina
                                volumesFinaisNovo[i][j] = volumesFinais[i][j];
                            }
                        }
                    }

                    if (volumesFinaisNovo[i][j] > ct.getCascata().get(i).getVolumeMaximo()) {
                        volumesFinaisNovo[i][j] = ct.getCascata().get(i).getVolumeMaximo();
                    }

                    if (volumesFinaisNovo[i][j] < ct.getCascata().get(i).getVolumeMinimo()) {
                        volumesFinaisNovo[i][j] = ct.getCascata().get(i).getVolumeMaximo();
                    }

                    volumesFinais[i][j] = volumesFinaisNovo[i][j];
                }
            }
        }

        return volumesFinais;
    }

    
    public double[][] operadorMutacaoIndividuoNovo(double[][] volumesFinais, double[] complementacaoTermica) {
        Random r = new Random();
        double[][] volumesFinaisNovo = new double[vazoesNaturais.length][vazoesNaturais[0].length];
        double porcentagem1 = 0.03;  //0.03
        double porcentagem2 = 0.02;  //0.02
        double porcentagem3 = 0.01;  //0.01
        double mediaComplementacaoTermica = calculaMedia(complementacaoTermica);

        for (int i = 0; i < volumesFinaisNovo.length; i++) {
            for (int j = 0; j < volumesFinaisNovo[0].length; j++) {
                if (complementacaoTermica[j] > mediaComplementacaoTermica) {
                    // ct > media + 60% media
                    if (complementacaoTermica[j] >= (mediaComplementacaoTermica + 0.6*mediaComplementacaoTermica)) {
                        volumesFinaisNovo[i][j] = volumesFinais[i][j] - r.nextDouble() * porcentagem1 * volumesFinais[i][j];
                    } else // media + 30% media < ct < media + 60% media
                    {
                        if ((complementacaoTermica[j] >= (mediaComplementacaoTermica + 0.3*mediaComplementacaoTermica)) && (complementacaoTermica[j] < (mediaComplementacaoTermica + 0.6 * mediaComplementacaoTermica))) {
                            volumesFinaisNovo[i][j] = volumesFinais[i][j] - r.nextDouble() * porcentagem2 * volumesFinais[i][j];
                        } else // media + 10% < ct < media + 30%    
                        {
                            if ((complementacaoTermica[j] >= (mediaComplementacaoTermica + 0.1*mediaComplementacaoTermica)) && (complementacaoTermica[j] < (mediaComplementacaoTermica + 0.3 * mediaComplementacaoTermica))) {
                                volumesFinaisNovo[i][j] = volumesFinais[i][j] - r.nextDouble() * porcentagem3 * volumesFinais[i][j];
                            } else {
                                // caso não se enquadre em nenhum dos anteriores, eu não altero o volume da usina
                                volumesFinaisNovo[i][j] = volumesFinais[i][j];
                            }
                        }
                    }

                    if (volumesFinaisNovo[i][j] > ct.getCascata().get(i).getVolumeMaximo()) {
                        volumesFinaisNovo[i][j] = ct.getCascata().get(i).getVolumeMaximo();
                    }

                    if (volumesFinaisNovo[i][j] < ct.getCascata().get(i).getVolumeMinimo()) {
                        volumesFinaisNovo[i][j] = ct.getCascata().get(i).getVolumeMaximo();
                    }

                    volumesFinais[i][j] = volumesFinaisNovo[i][j];
                } else {
                    // ct < media - 60% media
                    if (complementacaoTermica[j] <= (mediaComplementacaoTermica - 0.6 * mediaComplementacaoTermica)) {
                        volumesFinaisNovo[i][j] = volumesFinais[i][j] + r.nextDouble() * porcentagem1 * volumesFinais[i][j];
                    } else // media - 60% media < ct < media - 30% media
                    {
                        if ((complementacaoTermica[j] <= (mediaComplementacaoTermica - 0.3 * mediaComplementacaoTermica)) && (complementacaoTermica[j] > (mediaComplementacaoTermica - 0.6 * mediaComplementacaoTermica))) {
                            volumesFinaisNovo[i][j] = volumesFinais[i][j] + r.nextDouble() * porcentagem2 * volumesFinais[i][j];
                        } else // media - 30% < ct < media - 10%
                        {
                            if ((complementacaoTermica[j] <= (mediaComplementacaoTermica - 0.1 * mediaComplementacaoTermica)) && (complementacaoTermica[j] > (mediaComplementacaoTermica - 0.3 * mediaComplementacaoTermica))) {
                                volumesFinaisNovo[i][j] = volumesFinais[i][j] + r.nextDouble() * porcentagem3 * volumesFinais[i][j];
                            } else {
                                // caso não se enquadre em nenhum dos anteriores, eu não altero o volume da usina
                                volumesFinaisNovo[i][j] = volumesFinais[i][j];
                            }
                        }
                    }

                    if (volumesFinaisNovo[i][j] > ct.getCascata().get(i).getVolumeMaximo()) {
                        volumesFinaisNovo[i][j] = ct.getCascata().get(i).getVolumeMaximo();
                    }

                    if (volumesFinaisNovo[i][j] < ct.getCascata().get(i).getVolumeMinimo()) {
                        volumesFinaisNovo[i][j] = ct.getCascata().get(i).getVolumeMaximo();
                    }

                    volumesFinais[i][j] = volumesFinaisNovo[i][j];
                }
            }
        }

        return volumesFinais;
    }
    
    
    public double[] calculaComplementacaoTermica(double[][] cromossomo) {
        double[][] volumesFinais = s.converteCromossomoIdeaProfRicardo(ct.getCascata(), cromossomo);

        this.volumesFinais = volumesFinais;
        for (int i = 0; i < vazoesNaturais.length; i++) {
            for (int j = 0; j < vazoesNaturais[0].length; j++) {
                // caso seja o mês inicial
                if (j == 0) {
                    volumesIniciais[i][j] = ct.getCascata().get(i).getVolumeMaximo();
                } else {
                    volumesIniciais[i][j] = this.volumesFinais[i][j - 1];
                }

                if (i == 0) {
                    vazoesDefluentes[i][j] = s.calculaVazaoDefluente(ct.getCascata().get(i), volumesIniciais[i][j], volumesFinais[i][j], 0, vazoesIncrementais[i][j], 0);
                } else {
                    vazoesDefluentes[i][j] = s.calculaVazaoDefluente(ct.getCascata().get(i), volumesIniciais[i][j], volumesFinais[i][j], vazoesDefluentes[i - 1][j], vazoesIncrementais[i][j], 0);
                }

                volumesMedios[i][j] = (volumesIniciais[i][j] + this.volumesFinais[i][j]) / 2.0;
                cotasMontante[i][j] = s.calculaCotaMontante(ct.getCascata().get(i), volumesMedios[i][j]);
                cotasJusante[i][j] = s.calculaCotaJusante(ct.getCascata().get(i), vazoesDefluentes[i][j]);
                alturasDeQuedaLiquida[i][j] = s.calculaAlturaQuedaLiquida(ct.getCascata().get(i), cotasMontante[i][j], cotasJusante[i][j]);
                engolimentoMaximo[i][j] = s.calculaEngolimentoMaximo(ct.getCascata().get(i), volumesMedios[i][j], vazoesDefluentes[i][j], 1);
                alturaQuedaLiquidaEngolimento[i][j] = s.calculaAlturaQuedaLiquidaDoEngolimentoMaximo(ct.getCascata().get(i), volumesMedios[i][j], vazoesDefluentes[i][j], 1);
                potenciaMaxima[i][j] = s.calculaPotenciaMaxima(ct.getCascata().get(i), alturaQuedaLiquidaEngolimento[i][j]);
                geracaoHidraulicaMaxContinua[i][j] = s.calculaGeracaoHidraulicaMaximaContinua(ct.getCascata().get(i), potenciaMaxima[i][j]);
                limiteMaxVazaoTurbinada[i][j] = s.calculaLimiteMaximoVazaoTurbinada(ct.getCascata().get(i), volumesMedios[i][j], geracaoHidraulicaMaxContinua[i][j]);
                vazaoTurbinada[i][j] = s.calculaVazaoTurbinada(vazoesDefluentes[i][j], limiteMaxVazaoTurbinada[i][j], engolimentoMaximo[i][j]);
                vazaoVertida[i][j] = s.calculaVazaoVertida(vazoesDefluentes[i][j], limiteMaxVazaoTurbinada[i][j], engolimentoMaximo[i][j]);
                geracaoHidraulica[i][j] = s.calculaGeracaoHidraulica(ct.getCascata().get(i), alturasDeQuedaLiquida[i][j], vazaoTurbinada[i][j]);
            }
        }

        geracoesHidraulicaConjunto = new double[vazoesNaturais[0].length];;
        for (int i = 0; i < geracaoHidraulica[0].length; i++) {
            for (int j = 0; j < geracaoHidraulica.length; j++) {
                geracoesHidraulicaConjunto[i] += geracaoHidraulica[j][i];
            }
        }

        double soma = 0;
        for (int i = 0; i < geracoesHidraulicaConjunto.length; i++) {
            complementacaoTermica[i] = demanda - geracoesHidraulicaConjunto[i];
            soma += complementacaoTermica[i];
        }

        mediaComplementacaoTermica = soma / geracoesHidraulicaConjunto.length;

        return complementacaoTermica;
    }

    public double[][] converteCromossomoEmVolume(double[][] cromossomoSegundaFase) {

        return s.converteCromossomoIdeaProfRicardo(ct.getCascata(), cromossomoSegundaFase);
    }

}

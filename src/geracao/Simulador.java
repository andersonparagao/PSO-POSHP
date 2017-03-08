/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geracao;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import usina.Hidroeletrica;

/**
 *
 * @author Anderson Aragao
 */
public class Simulador {
    // dados do AG

    String caminho;
    double[][] cromossomo;
    
    // primeira fase do AG em que a fita de cromossomo é o conjunto de ENAS dos períodos do planejamento
    double[] cromossomoEAS; 

    // dados do Simulador
    double porcentagemPotenciaInstalada;
    double porcentagemVazaoNatural;
    double quantidadeDeAnos;
    double FATOR_DE_CONVERSAO = 1000000.0/2628000.0;

    // professor Ricardo MLT
//    double[] vazaoNaturalEmborcacao = {399, 306, 245, 196, 173, 206, 356, 656, 894, 904, 863, 623,
//                                       399, 306, 245, 196, 173, 206, 356, 656, 894, 904, 863, 623,
//                                       399, 306, 245, 196, 173, 206, 356, 656, 894, 904, 863, 623,
//                                       399, 306, 245, 196, 173, 206, 356, 656, 894, 904, 863, 623,
//                                       399, 306, 245, 196, 173, 206, 356, 656, 894, 904, 863, 623};
    
    double[] vazaoNaturalEmborcacao = {399, 306, 245, 196, 173, 206, 356, 656, 894, 904, 863, 623,
                                       399, 306, 245, 196, 173, 206, 356, 656, 894, 904, 863, 623};

    
    // professor Ricardo MLT
//    double[] vazaoNaturalItumbiara = {1331, 1024, 817, 655, 584, 704, 1147, 1971, 2727, 2850, 2765, 2043,
//                                      1331, 1024, 817, 655, 584, 704, 1147, 1971, 2727, 2850, 2765, 2043,
//                                      1331, 1024, 817, 655, 584, 704, 1147, 1971, 2727, 2850, 2765, 2043,
//                                      1331, 1024, 817, 655, 584, 704, 1147, 1971, 2727, 2850, 2765, 2043,
//                                      1331, 1024, 817, 655, 584, 704, 1147, 1971, 2727, 2850, 2765, 2043};
    
    // professor Ricardo MLT
    double[] vazaoNaturalItumbiara = {1331, 1024, 817, 655, 584, 704, 1147, 1971, 2727, 2850, 2765, 2043,
                                      1331, 1024, 817, 655, 584, 704, 1147, 1971, 2727, 2850, 2765, 2043};

    // professor Ricardo MLT
//    double[] vazaoNaturalSaoSimao = {2084, 1623, 1309, 1067, 974, 1148, 1766, 2951, 4055, 4253, 4183, 3156,
//                                     2084, 1623, 1309, 1067, 974, 1148, 1766, 2951, 4055, 4253, 4183, 3156,
//                                     2084, 1623, 1309, 1067, 974, 1148, 1766, 2951, 4055, 4253, 4183, 3156,
//                                     2084, 1623, 1309, 1067, 974, 1148, 1766, 2951, 4055, 4253, 4183, 3156,
//                                     2084, 1623, 1309, 1067, 974, 1148, 1766, 2951, 4055, 4253, 4183, 3156};
    
    // professor Ricardo MLT
//    double[] vazaoNaturalSaoSimao = {2084, 1623, 1309, 1067, 974, 1148, 1766, 2951, 4055, 4253, 4183, 3156,
//                                     2084, 1623, 1309, 1067, 974, 1148, 1766, 2951, 4055, 4253, 4183, 3156,
//                                     2084, 1623, 1309, 1067, 974, 1148, 1766, 2951, 4055, 4253, 4183, 3156,
//                                     2084, 1623, 1309, 1067, 974, 1148, 1766, 2951, 4055, 4253, 4183, 3156,
//                                     2084, 1623, 1309, 1067, 974, 1148, 1766, 2951, 4055, 4253, 4183, 3156};
    
     double[] vazaoNaturalSaoSimao = {2084, 1623, 1309, 1067, 974, 1148, 1766, 2951, 4055, 4253, 4183, 3156,
                                     2084, 1623, 1309, 1067, 974, 1148, 1766, 2951, 4055, 4253, 4183, 3156};
     
     
//    // dados ONS
//    double[] vazaoNaturalEmborcacao =  {393.1744186, 302.4651163, 241.6395349, 193.6511628, 170.5348837, 202.2209302, 347.8604651, 648.7790698, 887.8023256, 881.4767442, 847.4767442, 618.6744186, 
//                                        393.1744186, 302.4651163, 241.6395349, 193.6511628, 170.5348837, 202.2209302, 347.8604651, 648.7790698,	887.8023256, 881.4767442, 847.4767442, 618.6744186};
//
//    // dados ONS
//    double[] vazaoNaturalItumbiara = {1318.267442, 1017.139535, 808.7906977, 647.8139535, 574.3837209, 693.4302326, 1122.965116, 1948.94186, 2696.151163, 2779.802326, 2725.302326, 2041.744186, 
//                                      1318.267442, 1017.139535, 808.7906977, 647.8139535, 574.3837209, 693.4302326, 1122.965116, 1948.94186, 2696.151163, 2779.802326, 2725.302326, 2041.744186};
//    
//    // dados da ONS
//    double[] vazaoNaturalSaoSimao = {2096.337209, 1638.313953, 1317.418605, 1073.05814, 970.5697674, 1144.395349, 1749.627907, 2951.94186, 4047.930233, 4209.418605, 4197.162791, 3210.069767, 
//                                     2096.337209, 1638.313953, 1317.418605, 1073.05814, 970.5697674, 1144.395349, 1749.627907, 2951.94186, 4047.930233, 4209.418605, 4197.162791, 3210.069767};
//
     
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //1936 - 1941
//    double[] vazaoNaturalEmborcacao = {371, 251, 227, 172, 145, 152, 206, 335, 599, 207, 367, 454,
//                                       304, 271, 201, 155, 139, 196, 387, 664, 793, 539, 557, 434,
//                                       262, 224, 211, 151, 125, 142, 210, 566, 844, 882, 388, 328,
//                                       284, 240, 178, 158, 126, 141, 267, 326, 582, 962, 925, 465,
//                                       365, 272, 211, 159, 128, 153, 444, 389, 789, 504, 422, 511};
//
////     1936 - 1941
//    double[] vazaoNaturalItumbiara = {1255, 850, 768, 582, 491, 513, 695, 1134, 2023, 699, 1242, 1535,
//                                      1029, 915, 679, 523, 469, 661, 1308, 2244, 2679, 1820, 1882, 1466,
//                                      885, 756, 714, 510, 421, 480, 711, 1911, 2852, 2981, 1310, 1107,
//                                      961, 809, 601, 533, 426, 477, 901, 1100, 1967, 3251, 3127, 1571,
//                                      1234, 919, 713, 538, 431, 517, 1500, 1315, 2668, 1702, 1426, 1726};
//
////     1936 - 1941
//    double[] vazaoNaturalSaoSimao = {1723, 1166, 1054, 799, 674, 704, 954, 1556, 2776, 960, 1704, 2106,
//                                     1412, 1256, 932, 718, 644, 908, 1795, 3079, 3677, 2498, 2583, 2013,
//                                     1215, 1038, 980, 701, 578, 659, 976, 2623, 3914, 4091, 1798, 1520,
//                                     1319, 1111, 825, 731, 585, 655, 1237, 1510, 2700, 4461, 4292, 2156,
//                                     1693, 1262, 979, 739, 592, 710, 2058, 1805, 3661, 2335, 1957, 2369};
///////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // 1951 - 1956
//    double[] vazaoNaturalEmborcacao = {405, 322, 249, 202, 152, 162, 184, 298, 623, 1082, 2244, 738,
//                                         474, 362, 285, 220, 193, 183, 342, 683, 377, 349, 867, 764,
//                                         392, 285, 220, 166, 162, 391, 388, 856, 471, 863, 659, 455, 
//                                         385, 266, 173, 124, 90, 73, 380, 531, 683, 634, 484, 677, 
//                                         280, 217, 162, 116, 81, 208, 314, 1168, 788, 686, 889, 427};
//
//    // 1951 - 1956
//    double[] vazaoNaturalItumbiara = {1422, 1073, 848, 685, 518, 565, 573, 1025, 1904, 2794, 5878, 2410,
//                                      1491, 1132, 875, 678, 609, 665, 1102, 1703, 1285, 1198, 2297, 2309,
//                                      1317, 895, 711, 539, 527, 993, 1046, 2153, 1435, 2478, 1326, 1125,
//                                      912, 624, 450, 337, 284, 261, 864, 1521, 2111, 1684, 1396, 1724, 
//                                      862, 646, 459, 342, 268, 594, 866, 2288, 2344, 1513, 2368, 1328};
//
//    // 1951 - 1956
//    double[] vazaoNaturalSaoSimao = {2121, 1674, 1387, 1178, 963, 1024, 1033, 1613, 2739, 3880, 7831, 3387,
//                                     2209, 1750, 1420, 1169, 1080, 983, 1527, 2275, 1747, 1646, 3027, 3066,
//                                     1802, 1269, 1035, 821, 804, 1370, 1465, 2892, 2017, 3480, 1862, 1599,
//                                     1315, 949, 727, 573, 482, 450, 1214, 2175, 2904, 2354, 1941, 2409, 
//                                     1246, 974, 751, 583, 469, 912, 1300, 3075, 3153, 2062, 3093, 1851};

/////////////////////////////////////////////////////////////////////////////////////////////////////////// 
//    // 1971 - 1976
//    double[] vazaoNaturalEmborcacao = {141, 127, 92, 80, 87, 205, 427, 1083, 571, 640, 614, 513,
//                                       311, 232, 208, 153, 124, 354, 917, 772, 837, 843, 826, 878,
//                                       456, 336, 271, 206, 170, 287, 596, 611, 679, 432, 1141, 969,
//                                       549, 390, 304, 248, 184, 265, 219, 414, 760, 676, 404, 415,
//                                       287, 206, 183, 133, 99, 161, 380, 423, 401, 410, 471, 325};
//
//    // 1971 - 1976
//    double[] vazaoNaturalItumbiara = {525, 466, 350, 312, 329, 598, 1303, 2657, 1585, 1900, 1823, 1557,
//                                      957, 754, 663, 514, 447, 1089, 2212, 2193, 2342, 2509, 2556, 2809,
//                                      1544, 1114, 919, 722, 623, 1000, 1838, 1919, 2145, 1521, 2988, 2729,
//                                      1772, 1296, 1019, 869, 706, 950, 817, 1423, 2241, 1933, 1278, 1544,
//                                      1043, 828, 774, 596, 455, 662, 1315, 1469, 1529, 1505, 1884, 1370};
//
//    // 1971 - 1976
//    double[] vazaoNaturalSaoSimao = {912, 759, 576, 512, 506, 845, 1664, 3769, 2660, 3209, 3125, 2413,
//                                     1459, 1107, 1007, 813, 698, 1655, 3262, 3707, 4227, 4113, 4217, 4603,
//                                     2425, 1780, 1431, 1090, 945, 1532, 2860, 3158, 3700, 2445, 4821, 4485,
//                                     2707, 1945, 1510, 1273, 948, 1335, 1186, 2291, 3382, 2824, 1936, 2434, 
//                                     1533, 1089, 1001, 729, 548, 814, 1764, 2084, 2118, 2396, 2747, 1993};
    /////////////////////////////////////////////////////////////////////////////////////////////////////// 
//    // 1980 - 1985
//    double[] vazaoNaturalEmborcacao = {529, 408, 338, 251, 224, 213, 422, 958, 1480, 743, 697, 690,
//                                       451, 381, 285, 222, 167, 267, 891, 1063, 1516, 1040, 1629, 1034,
//                                       674, 529, 418, 304, 258, 284, 281, 443, 1623, 2329, 1454, 1141,
//                                       701, 545, 449, 371, 366, 476, 708, 1297, 845, 506, 450, 590,
//                                       330, 233, 207, 191, 249, 180, 227, 522, 1210, 909, 952, 582};
//
//    // 1985 - 1980
//    double[] vazaoNaturalItumbiara = {1534, 1219, 989, 774, 697, 680, 1187, 2484, 3442, 1755, 1920, 1915,
//                                      1173, 994, 775, 626, 496, 997, 2457, 3034, 5108, 3419, 4957, 3308,
//                                      2083, 1587, 1198, 976, 795, 1081, 1100, 1813, 4965, 6649, 4858, 3695,
//                                      2265, 1773, 1365, 1056, 1082, 1593, 2098, 3819, 2742, 1812, 1694, 1907,
//                                      1251, 837, 706, 662, 773, 635, 723, 1628, 3816, 3049, 3134, 1982};
//
//    // 1980 - 1985
//    double[] vazaoNaturalSaoSimao = {2534, 1956, 1669, 1283, 1244, 1228, 1882, 3909, 4927, 2948, 3086, 3203,
//                                     1959, 1637, 1323, 1079, 898, 1717, 3961, 5117, 7857, 6585, 7998, 5594,
//                                     3617, 2855, 2247, 1858, 1630, 2115, 2322, 3398, 7680, 9931, 7276, 5774,
//                                     3708, 2995, 2406, 1916, 2082, 2639, 3247, 5707, 4699, 3417, 3097, 3684,
//                                     2484, 1697, 1410, 1416, 1532, 1355, 1447, 2853, 5835, 5336, 5489, 3859};
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    
//    // 2000 - 2005
//    double[] vazaoNaturalEmborcacao = {314, 235, 193, 158, 200, 93, 302, 623, 444, 272, 388, 263,
//                                       193, 142, 111, 85, 95, 145, 283, 511, 817, 1098, 698, 415,
//                                       295, 227, 181, 131, 140, 84, 123, 318, 1149, 674, 665, 565,
//                                       325, 271, 222, 191, 184, 155, 227, 348, 794, 1409, 1148, 970,
//                                       518, 357, 286, 240, 215, 179, 210, 525, 1020, 756, 1047, 525};
//
//    // 2000 - 2005
//    double[] vazaoNaturalItumbiara = {1074, 820, 684, 618, 800, 477, 1133, 1980, 1495, 1038, 1479, 1077, 
//                                      794, 602, 469, 389, 418, 558, 973, 1669, 2540, 3323, 2152, 1375, 
//                                      977, 736, 612, 485, 499, 337, 607, 1077, 2832, 2195, 2135, 1867, 
//                                      1094, 859, 697, 591, 582, 485, 863, 1279, 2391, 4321, 3563, 2881, 
//                                      1757, 1217, 966, 762, 605, 649, 829, 1678, 3290, 2379, 3048, 1621};
//
//    // 2000 - 2005
//    double[] vazaoNaturalSaoSimao = {1884, 1471, 1272, 1159, 1476, 907, 2076, 3513, 2815, 2049, 2740, 2072,
//                                     1560, 1236, 958, 831, 858, 1078, 1777, 2750, 4551, 5534, 3765, 2402,
//                                     1766, 1397, 1148, 901, 935, 602, 1140, 1804, 4265, 3877, 3667, 3370,
//                                     1975, 1510, 1266, 1019, 949, 935, 1492, 2344, 3815, 7087, 5763, 4585,
//                                     2802, 2060, 1698, 1324, 1034, 1186, 1432, 2789, 5138, 3832, 4907, 2831};

    // Penalizações do Sistema
    double[] somaVazoesDefluentesMinimas;
    double[] somaVazoesVertidas;
    double[] quantidadeDeVazaoVertida;

    List<Hidroeletrica> cascata = new ArrayList<>();
    double[][] volumesMedios;
    double[][] volumesIniciais;
    double[][] volumesFinais;
    double[][] vazoesDefluentes;
    double[][] vazoesIncrementais;
    double[][] cotasMontanteCascata;
    double[][] cotasJusanteCascata;
    double[][] alturasDeQuedaLiquidaCascata;
    double[][] engolimentosMaximoCascata;
    double[][] geracaoHidraulicaMaxContinua;
    double[][] limitesMaxVazaoTurbinada;
    double[][] vazoesTurbinadas;
    double[][] vazoesVertidas;
    double[][] geracaoHidraulicaCascata;
    private double[] geracaoHidraulicaMensal;
    double[][] alturaQuedaLiquidaEngolimento;
    double[][] potenciaMaxima;
    private double[] complementacaoTermicaMensal;
    private double custoDaOperacao;
    double complementacaoTermicaTotal;
    double[] energiaArmazenadaNoSistema;

    public Simulador() {
    }

    public Simulador(double[][] cromossomo) {
        this.cromossomo = cromossomo;
    }

    // construtor da Segunda Fase
    public Simulador(ConfiguracaoTeste parametrosDoTeste, double[][] cromossomo) {
        this.porcentagemVazaoNatural = parametrosDoTeste.getPorcentagemVazaoNatural();
        this.porcentagemPotenciaInstalada = parametrosDoTeste.getPorcentagemPotenciaInstalada();
        this.cascata = parametrosDoTeste.getCascata();
        this.quantidadeDeAnos = parametrosDoTeste.getQuantidadeDeAnos();
        this.cromossomo = cromossomo;
    }
    
    // construtor da Primeira Fase
    public Simulador(ConfiguracaoTeste parametrosDoTeste, double[] cromossomoEAS) {
        this.porcentagemVazaoNatural = parametrosDoTeste.getPorcentagemVazaoNatural();
        this.porcentagemPotenciaInstalada = parametrosDoTeste.getPorcentagemPotenciaInstalada();
        this.cascata = parametrosDoTeste.getCascata();
        this.quantidadeDeAnos = parametrosDoTeste.getQuantidadeDeAnos();
        this.cromossomoEAS = cromossomoEAS;
    }

    public Simulador(ConfiguracaoTeste parametrosDoTeste) {
        this.porcentagemVazaoNatural = parametrosDoTeste.getPorcentagemVazaoNatural();
        this.porcentagemPotenciaInstalada = parametrosDoTeste.getPorcentagemPotenciaInstalada();
        this.cascata = parametrosDoTeste.getCascata();
        this.quantidadeDeAnos = parametrosDoTeste.getQuantidadeDeAnos();
    }

    public double[] getVazaoNaturalEmborcacao() {
        return vazaoNaturalEmborcacao;
    }

    public void setVazaoNaturalEmborcacao(double[] vazaoNaturalEmborcacao) {
        this.vazaoNaturalEmborcacao = vazaoNaturalEmborcacao;
    }

    public double[] getVazaoNaturalItumbiara() {
        return vazaoNaturalItumbiara;
    }

    public void setVazaoNaturalItumbiara(double[] vazaoNaturalItumbiara) {
        this.vazaoNaturalItumbiara = vazaoNaturalItumbiara;
    }

    public double[] getVazaoNaturalSaoSimao() {
        return vazaoNaturalSaoSimao;
    }

    public void setVazaoNaturalSaoSimao(double[] vazaoNaturalSaoSimao) {
        this.vazaoNaturalSaoSimao = vazaoNaturalSaoSimao;
    }

    public double[][] getCromossomo() {
        return cromossomo;
    }

    public void setCromossomo(double[][] cromossomo) {
        this.cromossomo = cromossomo;
    }

    public double[] getCromossomoEAS() {
        return cromossomoEAS;
    }

    public void setCromossomoEAS(double[] cromossomoEAS) {
        this.cromossomoEAS = cromossomoEAS;
    }
    
    public double[] porcentagemVazaoNatural(double[] vazaoNatural) {
        double[] porcentagemVazao = new double[vazaoNatural.length];
        for (int i = 0; i < vazaoNatural.length; i++) {
            porcentagemVazao[i] = porcentagemVazaoNatural * vazaoNatural[i];
        }

        return porcentagemVazao;
    }

    public double calculaCotaMontante(Hidroeletrica hidroeletrica, double volumeMedio) {
        double cotaMontante = 0;
        double[] polinomioCotaVolume = hidroeletrica.getPolinomioCotaVolume();

        for (int i = 0; i < polinomioCotaVolume.length; i++) {
            if (i == 0) {
                cotaMontante = polinomioCotaVolume[i];
            } else {
                cotaMontante = cotaMontante + Math.pow(volumeMedio, i) * polinomioCotaVolume[i];
            }
        }

        return cotaMontante;
    }

    public double calculaCotaJusante(Hidroeletrica hidroeletrica, double defluencia) {
        double cotaJusante = 0;
        double[] polinomioCotaJusante = hidroeletrica.getListaDePolinomiosJusante().get(0);

        for (int i = 0; i < polinomioCotaJusante.length; i++) {
            if (i == 0) {
                cotaJusante = polinomioCotaJusante[i];
            } else {
                cotaJusante = cotaJusante + Math.pow(defluencia, i) * polinomioCotaJusante[i];
            }
        }

        return cotaJusante;
    }

    public double calculaAlturaQuedaLiquidaONS(Hidroeletrica hidroeletrica, double cotaMontante, double cotaJusante) {
        double alturaQuedaLiquida;
        
        // 1%, 2 metros
        if(hidroeletrica.getTipoPerda() == 1){
            alturaQuedaLiquida = (cotaMontante - cotaJusante) * (1 - (hidroeletrica.getPerdaValor()/100.0));
        } else {
            alturaQuedaLiquida = (cotaMontante - cotaJusante - hidroeletrica.getPerdaValor());
        }
        

        return alturaQuedaLiquida;
    }
    
    
    public double calculaAlturaQuedaLiquida(Hidroeletrica hidroeletrica, double cotaMontante, double cotaJusante) {
        double alturaQuedaLiquida;

        alturaQuedaLiquida = (cotaMontante - cotaJusante) * (1 - hidroeletrica.getPerdaValor());

        return alturaQuedaLiquida;
    }

    public double calculaCotaMediaMontante(Hidroeletrica hidroeletrica, double volumeMedio) {
        double cotaMediaDeMontante;
        double[] polinomioCotaVolume = hidroeletrica.getPolinomioCotaVolume();
        double volumeMinimo = hidroeletrica.getVolumeMinimo();
        double alturaDeMontanteDoVolumeMedio = 0;
        double alturaDeMontanteDoVolumeMinimo = 0;

        for (int i = 0; i < 5; i++) {
            alturaDeMontanteDoVolumeMedio += polinomioCotaVolume[i] * (Math.pow(volumeMedio, i + 1) / (i + 1));
            alturaDeMontanteDoVolumeMinimo += polinomioCotaVolume[i] * (Math.pow(volumeMinimo, i + 1) / (i + 1));
        }
        cotaMediaDeMontante = (1 / (volumeMedio - volumeMinimo)) * (alturaDeMontanteDoVolumeMedio - alturaDeMontanteDoVolumeMinimo);

        return cotaMediaDeMontante;
    }

    public double calculaAlturaDeQuedaLiquidaMediaONS(Hidroeletrica hidroeletrica, double alturaMediaDeMontante) {
        double cotaMediaDoCanalDeFuga = hidroeletrica.getCotaCanalFuga();
        double alturaDeQuedaLiquidaMedia = 0;

        if(hidroeletrica.getTipoPerda() == 1){
            alturaDeQuedaLiquidaMedia = (alturaMediaDeMontante - cotaMediaDoCanalDeFuga) * (1 - (hidroeletrica.getPerdaValor()/100.0));
        } else {
            alturaDeQuedaLiquidaMedia = (alturaMediaDeMontante - cotaMediaDoCanalDeFuga - hidroeletrica.getPerdaValor());
        }

        return alturaDeQuedaLiquidaMedia;
    }
    
    public double calculaAlturaDeQuedaLiquidaMedia(Hidroeletrica hidroeletrica, double alturaMediaDeMontante) {
        double cotaMediaDoCanalDeFuga = hidroeletrica.getCotaCanalFuga();
        double alturaDeQuedaLiquidaMedia = 0;

        if (hidroeletrica.getTipoPerda() == 1) {
            alturaDeQuedaLiquidaMedia = (alturaMediaDeMontante - cotaMediaDoCanalDeFuga) * (1 - hidroeletrica.getPerdaValor());
        }

        return alturaDeQuedaLiquidaMedia;
    }

    public double calculaProdutibilidadeMedia(Hidroeletrica hidroeletrica, double alturaDeQuedaLiquidaMedia) {
        double produtibilidadeDaUsina = hidroeletrica.getProdutibilidadeEspecifica();

        return produtibilidadeDaUsina * alturaDeQuedaLiquidaMedia;
    }

    public double calculaEnergiaArmazenadaNoSistema(Hidroeletrica hidroeletrica, double volumeMedio) {
        double alturaMediaDeMontante = calculaCotaMediaMontante(hidroeletrica, volumeMedio);
        double alturaDeQuedaLiquidaMedia = calculaAlturaDeQuedaLiquidaMedia(hidroeletrica, alturaMediaDeMontante);
        double produtibilidadeMedia = calculaProdutibilidadeMedia(hidroeletrica, alturaDeQuedaLiquidaMedia);
        double volumeMinimo = hidroeletrica.getVolumeMinimo();

        double energiaArmazenadaNoSistema = FATOR_DE_CONVERSAO * produtibilidadeMedia * (volumeMedio - volumeMinimo);

        return energiaArmazenadaNoSistema;
    }

    public double calculaVolumeMedio(double volumeInicial, double volumeFinal) {
        return (volumeInicial + volumeFinal) / 2;
    }

    public double calculaGeracaoHidraulica(Hidroeletrica hidroeletrica, double alturaDeQuedaLiquida, double vazaoTurbinada) {
        return hidroeletrica.getProdutibilidadeEspecifica() * alturaDeQuedaLiquida * vazaoTurbinada;
    }

    public double calculaEngolimentoMaximo(Hidroeletrica hidroeletrica, double volumeMedio, double paramVazaoDefluente, double toleranciaMaximaParaEngolimentoMaximo) {
        double engolimentoMaximoUsinaAnterior;
        double engolimentoMaximoUsinaAtual;
        double vazaoDefluente;
        double nivelMontante;
        double nivelJusante;
        boolean convergencia;
        double alturaQuedaLiquida;

        engolimentoMaximoUsinaAnterior = hidroeletrica.getEngolimentoEfetivo()[0];
        vazaoDefluente = paramVazaoDefluente;
        nivelMontante = calculaCotaMontante(hidroeletrica, volumeMedio);

        do {
            nivelJusante = calculaCotaJusante(hidroeletrica, vazaoDefluente);
            alturaQuedaLiquida = calculaAlturaQuedaLiquida(hidroeletrica, nivelMontante, nivelJusante);
            engolimentoMaximoUsinaAtual = calculaEngolimento(hidroeletrica, alturaQuedaLiquida);

            if (engolimentoMaximoUsinaAtual > paramVazaoDefluente) {
                vazaoDefluente = engolimentoMaximoUsinaAtual;
            }

            if (Math.abs(engolimentoMaximoUsinaAtual - engolimentoMaximoUsinaAnterior) < toleranciaMaximaParaEngolimentoMaximo) {
                convergencia = true;
            } else {
                convergencia = false;
                engolimentoMaximoUsinaAnterior = engolimentoMaximoUsinaAtual;
            }
        } while (convergencia == false);

        return engolimentoMaximoUsinaAtual;
    }

    public double calculaAlturaQuedaLiquidaDoEngolimentoMaximo(Hidroeletrica hidroeletrica, double volumeMedio, double paramVazaoDefluente, double toleranciaMaximaParaEngolimentoMaximo) {
        double engolimentoMaximoUsinaAnterior;
        double engolimentoMaximoUsinaAtual;
        double vazaoDefluente;
        double nivelMontante;
        double nivelJusante;
        boolean convergencia;
        double alturaQuedaLiquida;

        engolimentoMaximoUsinaAnterior = hidroeletrica.getEngolimentoEfetivo()[0];
        vazaoDefluente = paramVazaoDefluente;
        nivelMontante = calculaCotaMontante(hidroeletrica, volumeMedio);

        do {
            nivelJusante = calculaCotaJusante(hidroeletrica, vazaoDefluente);
            alturaQuedaLiquida = calculaAlturaQuedaLiquida(hidroeletrica, nivelMontante, nivelJusante);
            engolimentoMaximoUsinaAtual = calculaEngolimento(hidroeletrica, alturaQuedaLiquida);

            if (engolimentoMaximoUsinaAtual > paramVazaoDefluente) {
                vazaoDefluente = engolimentoMaximoUsinaAtual;
            }

            if (Math.abs(engolimentoMaximoUsinaAtual - engolimentoMaximoUsinaAnterior) < toleranciaMaximaParaEngolimentoMaximo) {
                convergencia = true;
            } else {
                convergencia = false;
                engolimentoMaximoUsinaAnterior = engolimentoMaximoUsinaAtual;
            }
        } while (convergencia == false);

        return alturaQuedaLiquida;
    }

    public double calculaEngolimento(Hidroeletrica hidroeletrica, double alturaDeQuedaLiquida) {
        double engolimento;

        if (alturaDeQuedaLiquida < hidroeletrica.getAlturaEfetiva()[0] && hidroeletrica.getTipoTurbina() == 0) {
            engolimento = hidroeletrica.getNumueroDeConjuntoDeMaquinas()[0] * hidroeletrica.getEngolimentoEfetivo()[0] * (Math.pow((alturaDeQuedaLiquida / hidroeletrica.getAlturaEfetiva()[0]), 0.5));
        } else if (alturaDeQuedaLiquida < hidroeletrica.getAlturaEfetiva()[0] && hidroeletrica.getTipoTurbina() == 1) {
            engolimento = hidroeletrica.getNumueroDeConjuntoDeMaquinas()[0] * hidroeletrica.getEngolimentoEfetivo()[0] * (Math.pow((alturaDeQuedaLiquida / hidroeletrica.getAlturaEfetiva()[0]), 0.2));
        } else {
            engolimento = hidroeletrica.getNumueroDeConjuntoDeMaquinas()[0] * hidroeletrica.getEngolimentoEfetivo()[0] * (Math.pow((alturaDeQuedaLiquida / hidroeletrica.getAlturaEfetiva()[0]), -1.0));
        }
        
        return engolimento;
    }

    public double calculaPotenciaMaxima(Hidroeletrica hidroeletrica, double alturaDeQuedaLiquida) {
        double potenciaMaxima;

        if (alturaDeQuedaLiquida < hidroeletrica.getAlturaEfetiva()[0] && hidroeletrica.getTipoTurbina() == 0) {
            potenciaMaxima = hidroeletrica.getNumueroDeConjuntoDeMaquinas()[0] * hidroeletrica.getPotenciaEfetiva()[0] * (Math.pow((alturaDeQuedaLiquida / hidroeletrica.getAlturaEfetiva()[0]), 1.5));
        } else if (alturaDeQuedaLiquida < hidroeletrica.getAlturaEfetiva()[0] && hidroeletrica.getTipoTurbina() == 1) {
            potenciaMaxima = hidroeletrica.getNumueroDeConjuntoDeMaquinas()[0] * hidroeletrica.getPotenciaEfetiva()[0] * (Math.pow((alturaDeQuedaLiquida / hidroeletrica.getAlturaEfetiva()[0]), 1.2));
        } else {
            potenciaMaxima = hidroeletrica.getNumueroDeConjuntoDeMaquinas()[0] * hidroeletrica.getPotenciaEfetiva()[0] * (Math.pow((alturaDeQuedaLiquida / hidroeletrica.getAlturaEfetiva()[0]), 0.0));
        }

        return potenciaMaxima;
    }

    public double calculaVazaoDefluente(Hidroeletrica hidroeletrica, double volumeInicial, double volumeFinal,
            double somaDasVazoesDefluentes, double vazaoIncremental, double volumeEvaporado) {

        double vazaoDefluente = FATOR_DE_CONVERSAO * (volumeInicial - volumeFinal + ((vazaoIncremental + somaDasVazoesDefluentes) * (1.0 / FATOR_DE_CONVERSAO)));

        if (vazaoDefluente > hidroeletrica.getVazaoDefluenteMaxima()) {
            vazaoDefluente = hidroeletrica.getVazaoDefluenteMaxima();
        }

        if (vazaoDefluente < hidroeletrica.getVazaoDefluenteMinima()) {
            vazaoDefluente = hidroeletrica.getVazaoDefluenteMinima();
        }

        return vazaoDefluente;
    }

    public double calculaGeracaoHidraulicaMaximaContinua(Hidroeletrica hidroeletrica, double potenciaMaxima) {
        double taxaDeIndisponibilidadeForcada = hidroeletrica.getTaxaEif();
        double taxaDeIndisponibilidadeProgramada = hidroeletrica.getIndisponibilidadeProgramada();

        return potenciaMaxima * (1 - taxaDeIndisponibilidadeForcada) * (1 - taxaDeIndisponibilidadeProgramada);  // fcmax = 1 (100%) por isso não coloquei na fórmula
    }

    public double calculaLimiteMaximoVazaoTurbinada(Hidroeletrica hidroeletrica, double volumeMedio, double geracaoHidraulicaMaxContinua) {
        double alturaMediaMontante = calculaCotaMediaMontante(hidroeletrica, volumeMedio);
        double alturaDeQuedaLiquidaMedia = calculaAlturaDeQuedaLiquidaMedia(hidroeletrica, alturaMediaMontante);
        double produtibilidadeMedia = calculaProdutibilidadeMedia(hidroeletrica, alturaDeQuedaLiquidaMedia);

        return (geracaoHidraulicaMaxContinua / produtibilidadeMedia);
    }

    public double calculaVazaoTurbinada(double vazaoDefluente, double limiteMaxVazaoTurbinada, double engolimentoMaximo) {
        double vazaoTurbinada;

        if (vazaoDefluente > Math.min(limiteMaxVazaoTurbinada, engolimentoMaximo)) {
            vazaoTurbinada = (Math.min(limiteMaxVazaoTurbinada, engolimentoMaximo));
        } else {
            vazaoTurbinada = vazaoDefluente;
        }

        return vazaoTurbinada;
    }

    public double calculaVazaoVertida(double vazaoDefluente, double limiteMaxVazaoTurbinada, double engolimentoMaximo) {
        double vazaoVertida;

        if (vazaoDefluente > Math.min(limiteMaxVazaoTurbinada, engolimentoMaximo)) {
            vazaoVertida = Math.abs((Math.min(limiteMaxVazaoTurbinada, engolimentoMaximo)) - vazaoDefluente);
        } else {
            vazaoVertida = 0;
        }

        return vazaoVertida;
    }

    public double[] calculaVolumesIniciais(Hidroeletrica h, double[] cromossomo) {
        double[] volumesIniciais = new double[cromossomo.length];

        for (int i = 0; i < cromossomo.length; i++) {
            if (i == 0) {
                volumesIniciais[i] = h.getVolumeMaximo();
            } else {
                volumesIniciais[i] = cromossomo[i - 1];
            }
        }

        return volumesIniciais;
    }

    public double calculaEnergiaArmazenadaNoSistema(List<Hidroeletrica> hidroeletricas, double[] volumesMedios) {
        double somaProdutibilidadeMedia = 0;
        double alturaMediaDeMontante;
        double alturaDeQuedaLiquidaMedia;
        double volumeMinimo;
        double energiaArmazenadaNoSistema = 0;

        for (int i = 0; i < hidroeletricas.size(); i++) {
            alturaMediaDeMontante = calculaCotaMediaMontante(hidroeletricas.get(hidroeletricas.size() - i - 1), volumesMedios[hidroeletricas.size() - i - 1]);
            alturaDeQuedaLiquidaMedia = calculaAlturaDeQuedaLiquidaMedia(hidroeletricas.get(hidroeletricas.size() - i - 1), alturaMediaDeMontante);
            somaProdutibilidadeMedia += calculaProdutibilidadeMedia(hidroeletricas.get(hidroeletricas.size() - i - 1), alturaDeQuedaLiquidaMedia);
            volumeMinimo = hidroeletricas.get(hidroeletricas.size() - i - 1).getVolumeMinimo();
            energiaArmazenadaNoSistema += FATOR_DE_CONVERSAO * somaProdutibilidadeMedia * ((volumesMedios[hidroeletricas.size() - i - 1]) - volumeMinimo);
        }

        return energiaArmazenadaNoSistema;
    }

    public double calculaEnergiaArmazenadaMaximaNoSistema(List<Hidroeletrica> hidroeletricas) {
        double somaProdutibilidadeMedia = 0;
        double alturaMediaDeMontante;
        double alturaDeQuedaLiquidaMedia;
        double volumeMinimo;
        double energiaArmazenadaNoSistema = 0;

        for (int i = 0; i < hidroeletricas.size(); i++) {
            alturaMediaDeMontante = calculaCotaMediaMontante(hidroeletricas.get(hidroeletricas.size() - i - 1), hidroeletricas.get(hidroeletricas.size() - i - 1).getVolumeMaximo());
            alturaDeQuedaLiquidaMedia = calculaAlturaDeQuedaLiquidaMedia(hidroeletricas.get(hidroeletricas.size() - i - 1), alturaMediaDeMontante);
            somaProdutibilidadeMedia += calculaProdutibilidadeMedia(hidroeletricas.get(hidroeletricas.size() - i - 1), alturaDeQuedaLiquidaMedia);
            volumeMinimo = hidroeletricas.get(hidroeletricas.size() - i - 1).getVolumeMinimo();
            energiaArmazenadaNoSistema += FATOR_DE_CONVERSAO * somaProdutibilidadeMedia * (hidroeletricas.get(hidroeletricas.size() - i - 1).getVolumeMaximo() - volumeMinimo);
        }

        //System.out.println("EAMaxS = " + casasDeciamaisFloat(energiaArmazenadaNoSistema));
        return energiaArmazenadaNoSistema;
    }

    public double[][] calculaVolumesMediosCascata(List<Hidroeletrica> listaDeHidroeletricas, double[][] volumesIniciais, double[][] volumesFinais) {
        double[][] volumesMedios = new double[volumesIniciais.length][volumesFinais[0].length];

        for (int i = 0; i < volumesIniciais.length; i++) {
            for (int j = 0; j < volumesIniciais[0].length; j++) {
                volumesMedios[i][j] = calculaVolumeMedio(volumesIniciais[i][j], volumesFinais[i][j]);
            }
        }

        return volumesMedios;
    }

    public double[][] calculaCotaMontanteCascata(List<Hidroeletrica> cascata, double[][] volumesMedios) {
        double[][] cotasMontanteCascata = new double[cromossomo.length][cromossomo[0].length];

        for (int i = 0; i < cromossomo.length; i++) {
            for (int j = 0; j < cromossomo[0].length; j++) {
                cotasMontanteCascata[i][j] = calculaCotaMontante(cascata.get(i), volumesMedios[i][j]);
            }
        }

        return cotasMontanteCascata;
    }

    public double[][] calculaCotaJusanteCascata(List<Hidroeletrica> cascata, double[][] vazoesDefluentes) {
        double[][] cotasJusanteCascata = new double[cromossomo.length][cromossomo[0].length];

        for (int i = 0; i < cromossomo.length; i++) {
            for (int j = 0; j < cromossomo[0].length; j++) {
                cotasJusanteCascata[i][j] = calculaCotaJusante(cascata.get(i), vazoesDefluentes[i][j]);
            }
        }

        return cotasJusanteCascata;
    }

    public double[][] calculaAlturaDeQuedaLiquidaCascata(List<Hidroeletrica> cascata, double[][] cotaMontanteCascata, double[][] cotaJusanteCascata) {
        double[][] alturaDeQuedaLiquidaCascata = new double[cromossomo.length][cromossomo[0].length];

        for (int i = 0; i < cromossomo.length; i++) {
            for (int j = 0; j < cromossomo[0].length; j++) {
                alturaDeQuedaLiquidaCascata[i][j] = calculaAlturaQuedaLiquida(cascata.get(i), cotaMontanteCascata[i][j], cotaJusanteCascata[i][j]);
            }
        }

        return alturaDeQuedaLiquidaCascata;
    }

    public double[][] calculaEngolimentoMaximoCascata(List<Hidroeletrica> cascata, double[][] volumesMedios, double[][] vazoesDefluentes, double toleranciaMaximaParaEngolimentoMaximo) {
        double[][] engolimentosMaximos = new double[cromossomo.length][cromossomo[0].length];

        for (int i = 0; i < cromossomo.length; i++) {
            for (int j = 0; j < cromossomo[0].length; j++) {
                engolimentosMaximos[i][j] = calculaEngolimentoMaximo(cascata.get(i), volumesMedios[i][j], vazoesDefluentes[i][j], toleranciaMaximaParaEngolimentoMaximo);
            }
        }

        return engolimentosMaximos;
    }

    public double[][] calculaAlturaEngolimentoMaximoCascata(List<Hidroeletrica> cascata, double[][] volumesMedios, double[][] vazoesDefluentes, double toleranciaMaximaParaEngolimentoMaximo) {
        double[][] alturasEngolimentosMaximos = new double[cromossomo.length][cromossomo[0].length];

        for (int i = 0; i < cromossomo.length; i++) {
            for (int j = 0; j < cromossomo[0].length; j++) {
                alturasEngolimentosMaximos[i][j] = calculaAlturaQuedaLiquidaDoEngolimentoMaximo(cascata.get(i), volumesMedios[i][j], vazoesDefluentes[i][j], 1);

            }
        }

        return alturasEngolimentosMaximos;
    }

    public double[][] calculaPotenciaMaximaCascata(List<Hidroeletrica> cascata, double[][] alturaQuedaLiquidaEngolimento) {
        double[][] potenciasMaximas = new double[cromossomo.length][cromossomo[0].length];

        for (int i = 0; i < cromossomo.length; i++) {
            for (int j = 0; j < cromossomo[0].length; j++) {
                potenciasMaximas[i][j] = calculaPotenciaMaxima(cascata.get(i), alturaQuedaLiquidaEngolimento[i][j]);
            }
        }

        return potenciasMaximas;
    }

    public double[][] calculaGeracaoHidraulicaMaxContinuaCascata(List<Hidroeletrica> cascata, double[][] potenciasMaximas) {
        double[][] geracoesHidraulicaMaxContinua = new double[cromossomo.length][cromossomo[0].length];

        for (int i = 0; i < cromossomo.length; i++) {
            for (int j = 0; j < cromossomo[0].length; j++) {
                geracoesHidraulicaMaxContinua[i][j] = calculaGeracaoHidraulicaMaximaContinua(cascata.get(i), potenciasMaximas[i][j]);
            }
        }

        return geracoesHidraulicaMaxContinua;
    }

    public double[][] calculaLimiteMaxVazaoTurbinada(List<Hidroeletrica> cascata, double[][] volumeMedio, double[][] geracaoHidraulicaMaxContinua) {
        double[][] limiteMaxVazaoTurbinada = new double[cromossomo.length][cromossomo[0].length];

        for (int i = 0; i < cromossomo.length; i++) {
            for (int j = 0; j < cromossomo[0].length; j++) {
                limiteMaxVazaoTurbinada[i][j] = calculaLimiteMaximoVazaoTurbinada(cascata.get(i), volumeMedio[i][j], geracaoHidraulicaMaxContinua[i][j]);
            }
        }

        return limiteMaxVazaoTurbinada;
    }

    public double[][] calculaVazaoIncremental(List<Hidroeletrica> cascata, List<double[]> vazoesNaturais) {
        double[][] vazoesIncrementais = new double[volumesFinais.length][volumesFinais[0].length];

        for (int i = 0; i < vazoesIncrementais.length; i++) {
            for (int j = 0; j < vazoesIncrementais[0].length; j++) {
                if (i == 0) {
                    vazoesIncrementais[i][j] = vazoesNaturais.get(i)[j];
                } else {
                    vazoesIncrementais[i][j] = vazoesNaturais.get(i)[j] - vazoesNaturais.get(i - 1)[j];
                }
            }
        }

        return vazoesIncrementais;
    }
    
    
    public double[][] calculaVazaoIncremental(List<Hidroeletrica> cascata, double[][] vazoesNaturais) {
        double[][] vazoesIncrementais = new double[vazoesNaturais.length][vazoesNaturais[0].length];

        for (int i = 0; i < vazoesIncrementais.length; i++) {
            for (int j = 0; j < vazoesIncrementais[0].length; j++) {
                if (i == 0) {
                    vazoesIncrementais[i][j] = vazoesNaturais[i][j];
                } else {
                    vazoesIncrementais[i][j] = vazoesNaturais[i][j] - vazoesNaturais[i - 1][j];
                }
            }
        }

        return vazoesIncrementais;
    }

    public double[][] calculaVazaoDefluenteCascata(List<Hidroeletrica> cascata, double[][] volumesIniciais, double[][] volumesFinais, double[][] vazoesIncrementais) {
        double[][] vazoesDefluentes = new double[cromossomo.length][cromossomo[0].length];
        this.somaVazoesDefluentesMinimas = new double[cromossomo[0].length];

        for (int i = 0; i < vazoesDefluentes.length; i++) {
            for (int j = 0; j < vazoesDefluentes[0].length; j++) {
                if (i == 0) {
                    vazoesDefluentes[i][j] = FATOR_DE_CONVERSAO * (volumesIniciais[i][j] - volumesFinais[i][j] + ((vazoesIncrementais[i][j]) * (1.0 / FATOR_DE_CONVERSAO)));
                } else {
                    vazoesDefluentes[i][j] = FATOR_DE_CONVERSAO * (volumesIniciais[i][j] - volumesFinais[i][j] + ((vazoesIncrementais[i][j] + vazoesDefluentes[i - 1][j]) * (1.0 / FATOR_DE_CONVERSAO)));
                }

                if (vazoesDefluentes[i][j] > cascata.get(i).getVazaoDefluenteMaxima()) {
                    vazoesDefluentes[i][j] = cascata.get(i).getVazaoDefluenteMaxima();
                }

                if (vazoesDefluentes[i][j] < cascata.get(i).getVazaoDefluenteMinima()) {
                    somaVazoesDefluentesMinimas[i]++;
                    vazoesDefluentes[i][j] = cascata.get(i).getVazaoDefluenteMinima();
                }
            }
        }

        return vazoesDefluentes;
    }

    public double[][] calculaVazaoTurbinada(List<Hidroeletrica> cascata, double[][] vazoesDefluentes, double[][] limitesMaxVazaoTurbinada, double[][] engolimentosMaximos) {
        double[][] vazoesTurbinadas = new double[cromossomo.length][cromossomo[0].length];

        for (int i = 0; i < cromossomo.length; i++) {
            for (int j = 0; j < cromossomo[0].length; j++) {
                vazoesTurbinadas[i][j] = calculaVazaoTurbinada(vazoesDefluentes[i][j], limitesMaxVazaoTurbinada[i][j], engolimentosMaximos[i][j]);
            }
        }

        return vazoesTurbinadas;
    }

    public double[][] calculaVazaoVertida(List<Hidroeletrica> cascata, double[][] vazoesDefluentes, double[][] limitesMaxVazaoTurbinada, double[][] engolimentosMaximos) {
        somaVazoesVertidas = new double[cascata.size()];
        double[][] vazoesVertidas = new double[cromossomo.length][cromossomo[0].length];

        for (int i = 0; i < cromossomo.length; i++) {
            for (int j = 0; j < cromossomo[0].length; j++) {
                vazoesVertidas[i][j] = calculaVazaoVertida(vazoesDefluentes[i][j], limitesMaxVazaoTurbinada[i][j], engolimentosMaximos[i][j]);
                somaVazoesVertidas[i] += vazoesVertidas[i][j];
            }
        }

        return vazoesVertidas;
    }

    public double[][] calculaGeracaoHidraulicaCascata(List<Hidroeletrica> cascata, double[][] alturasDeQuedaLiquida, double[][] vazoesTurbinadas) {
        double[][] geracaoHidraulicaCascata = new double[cromossomo.length][cromossomo[0].length];

        for (int i = 0; i < cromossomo.length; i++) {
            for (int j = 0; j < cromossomo[0].length; j++) {
                geracaoHidraulicaCascata[i][j] = calculaGeracaoHidraulica(cascata.get(i), alturasDeQuedaLiquida[i][j], vazoesTurbinadas[i][j]);
            }
        }

        return geracaoHidraulicaCascata;
    }

    public double[] calculaGeracaoHidraulicaMensalCascata(double[][] geracaoHidraulicaCascata) {
        double[] geracaoHidraulicaMensal = new double[cromossomo[0].length];

        for (int i = 0; i < cromossomo[0].length; i++) {
            for (int k = 0; k < cromossomo.length; k++) {
                geracaoHidraulicaMensal[i] += geracaoHidraulicaCascata[k][i];
            }
        }

        return geracaoHidraulicaMensal;
    }

    public double[][] calculaVolumesIniciaisCascata(List<Hidroeletrica> cascata, double[][] cromossomoReal) {
        double[][] volumesIniciais = new double[cromossomo.length][cromossomo[0].length];

        for (int i = 0; i < cromossomoReal.length; i++) {
            for (int j = 0; j < cromossomoReal[0].length; j++) {
                if (j == 0) {
                    volumesIniciais[i][j] = cascata.get(i).getVolumeMaximo();
                } else {
                    volumesIniciais[i][j] = cromossomoReal[i][j - 1];
                }
            }
        }

        return volumesIniciais;
    }

    public double calculaCustoOperacaoCascata(List<Hidroeletrica> cascata, double DEMANDA, double[] geracaoHidraulicaMensal, double taxaDeJuros) {
        this.custoDaOperacao = 0;
        double correcaoMonetaria = 0.21;
        double complementacaoTermicaMes;
        double somaComplementacaoTermica = 0;

        for (int i = 0; i < geracaoHidraulicaMensal.length; i++) {
            if (geracaoHidraulicaMensal[i] < DEMANDA) {
                complementacaoTermicaMes = DEMANDA - geracaoHidraulicaMensal[i];
                somaComplementacaoTermica += complementacaoTermicaMes;
                //custoDaOperacao += Math.pow(complementacaoTermicaMes, 2)/Math.pow((1.0 + taxaDeJuros), ((i+1)/12.0));
                custoDaOperacao += Math.pow(complementacaoTermicaMes, 2);
            }

        }

        // return custoDaOperacao * correcaoMonetaria;
        return custoDaOperacao/2.0;
    }

    public double calculaCustoOperacaoCascataResultado(List<Hidroeletrica> cascata, double DEMANDA, double[] geracaoHidraulicaMensal, double taxaDeJuros) {
        this.complementacaoTermicaMensal = new double[geracaoHidraulicaMensal.length];
        this.complementacaoTermicaTotal = 0;
        this.custoDaOperacao = 0;
        System.out.println("");
        System.out.println("COMPLEMENTACAO TERMICA");

        for (int i = 0; i < geracaoHidraulicaMensal.length; i++) {
            if (geracaoHidraulicaMensal[i] < DEMANDA) {
                complementacaoTermicaMensal[i] = DEMANDA - geracaoHidraulicaMensal[i];
                complementacaoTermicaTotal += complementacaoTermicaMensal[i];
                System.out.println(String.format("%,10f", complementacaoTermicaMensal[i]));
                //custoDaOperacao += Math.pow(complementacaoTermicaMensal[i], 2)/Math.pow((1.0 + taxaDeJuros), ((i+1)/12.0));
                custoDaOperacao += Math.pow(complementacaoTermicaMensal[i], 2);
            } else {
                System.out.println(String.format("%,10f", 0.0));
            }
        }
        System.out.println("\nComplementacao Térmica no Período = " + complementacaoTermicaTotal + " MW");
        //  System.out.println("Custo da Operação: " + custoDaOperacao * correcaoMonetaria);
        System.out.println("Custo da Operação: " + custoDaOperacao/2.0);

        //return custoDaOperacao * correcaoMonetaria;
        return custoDaOperacao/2.0;
    }

    public double[] calculaComplementacaoTermicaMensal(double demanda, double[] geracaoHidraulicaMensal) {
        double[] complementacaoTermicaMensal = new double[geracaoHidraulicaMensal.length];

        for (int i = 0; i < geracaoHidraulicaMensal.length; i++) {
            if (demanda < geracaoHidraulicaMensal[i]) {
                complementacaoTermicaMensal[i] = 0;
            } else {
                complementacaoTermicaMensal[i] = (demanda - geracaoHidraulicaMensal[i]);
            }
        }

        return complementacaoTermicaMensal;
    }

    public int calculaIndicePico(double[] complementacaoTermicaMensal) {
        int indiceMaior = 0;
        for (int i = 1; i < complementacaoTermicaMensal.length; i++) {
            if (complementacaoTermicaMensal[i] > complementacaoTermicaMensal[indiceMaior]) {
                indiceMaior = i;
            }
        }

        return indiceMaior;
    }

    public double calculaMediaComplementacaoTermica(double[] complementacaoTermicaMensal) {
        int soma = 0;
        for (int i = 1; i < complementacaoTermicaMensal.length; i++) {
            soma += complementacaoTermicaMensal[i];
        }

        return soma / complementacaoTermicaMensal.length;
    }

    
    //resolução de conflitos
    public void corrigeConflitos() {
        for (int mesPlanejamento = 0; mesPlanejamento < cromossomo[0].length; mesPlanejamento++) {
            for (int usina = 0; usina < cromossomo.length; usina++) {
                // se a vazao defluente for a mínima
                if (vazoesDefluentes[usina][mesPlanejamento] == cascata.get(usina).getVazaoDefluenteMinima()) {
                    //calculo os volumes finais das usinas em cada período em que a vd minima ocorrer
                    calculaVolumesFinaisConflitoPeriodo(cascata, usina, mesPlanejamento);
                    //calculo os volumes iniciais das usinas em cada período em que a vd minima ocorrer
                    calculaVolumesIniciaisConflitoPeriodo(cascata, usina, mesPlanejamento);
                    //calculo os volumes medios das usinas em cada período em que a vd minima ocorrer
                    calculaVolumesMediosConflitoPeriodo(cascata, usina, mesPlanejamento);
                    //calculo as vazoes defluentes das usinas em cada período em que a vd minima ocorrer
                    calculaVazaoDefluenteConflito(cascata, usina, mesPlanejamento);
                }
            }
        }
    }

    public void calculaVolumesFinaisConflitoPeriodo(List<Hidroeletrica> cascata, int usina, int mesPlanejamento) {
        for (int i = usina; i < cascata.size(); i++) {
            if (i == 0) {
                volumesFinais[i][mesPlanejamento] = volumesIniciais[i][mesPlanejamento] + (vazoesIncrementais[i][mesPlanejamento] - vazoesDefluentes[i][mesPlanejamento]) * (1.0 / FATOR_DE_CONVERSAO);
            } else {
                volumesFinais[i][mesPlanejamento] = volumesIniciais[i][mesPlanejamento] + (vazoesIncrementais[i][mesPlanejamento] + vazoesDefluentes[i - 1][mesPlanejamento] - vazoesDefluentes[i][mesPlanejamento]) * (1.0 / FATOR_DE_CONVERSAO);
            }

            if (volumesFinais[i][mesPlanejamento] >= cascata.get(i).getVolumeMaximo()) {
                volumesFinais[i][mesPlanejamento] = cascata.get(i).getVolumeMaximo();
            }

            if (volumesFinais[i][mesPlanejamento] <= cascata.get(i).getVolumeMinimo()) {
                volumesFinais[i][mesPlanejamento] = cascata.get(i).getVolumeMinimo();
            }
        }
    }

    public void calculaVolumesIniciaisConflitoPeriodo(List<Hidroeletrica> cascata, int usina, int mesPlanejamento) {
        for (int i = usina; i < cascata.size(); i++) {
            if (mesPlanejamento != 0) {
                volumesIniciais[i][mesPlanejamento] = volumesFinais[i][mesPlanejamento - 1];
            } else {
                volumesIniciais[i][mesPlanejamento] = cascata.get(i).getVolumeMaximo();
            }
        }
    }

    public void calculaVolumesMediosConflitoPeriodo(List<Hidroeletrica> cascata, int usina, int mesPlanejamento) {
        for (int i = usina; i < cascata.size(); i++) {
            volumesMedios[i][mesPlanejamento] = calculaVolumeMedio(volumesIniciais[i][mesPlanejamento], volumesFinais[i][mesPlanejamento]);
        }
    }

    public void calculaVazaoDefluenteConflito(List<Hidroeletrica> cascata, int usina, int mesPlanejamento) {
        for (int i = usina; i < cascata.size(); i++) {
            if (i == 0) {
                vazoesDefluentes[i][mesPlanejamento] = FATOR_DE_CONVERSAO * (volumesIniciais[i][mesPlanejamento] - volumesFinais[i][mesPlanejamento] + (vazoesIncrementais[i][mesPlanejamento] - vazoesDefluentes[i][mesPlanejamento]));
            } else {
                vazoesDefluentes[i][mesPlanejamento] = FATOR_DE_CONVERSAO * (volumesIniciais[i][mesPlanejamento] - volumesFinais[i][mesPlanejamento] + (vazoesIncrementais[i][mesPlanejamento] + vazoesDefluentes[i - 1][mesPlanejamento] - vazoesDefluentes[i][mesPlanejamento]));
            }

            if (vazoesDefluentes[i][mesPlanejamento] >= cascata.get(i).getVazaoDefluenteMaxima()) {
                vazoesDefluentes[i][mesPlanejamento] = cascata.get(i).getVazaoDefluenteMaxima();
            }

            if (vazoesDefluentes[i][mesPlanejamento] <= cascata.get(i).getVazaoDefluenteMinima()) {
                vazoesDefluentes[i][mesPlanejamento] = cascata.get(i).getVazaoDefluenteMinima();
            }

        }
    }

    // converte o cromossomo da segunda fase
    // a partir dos dados normalizados da fita de cromossomo (volumes) a função faz a conversão para volumes em hm3
    public double[][] converteCromossomo(List<Hidroeletrica> cascata) {
        double[][] cromossomoReal = new double[cromossomo.length][cromossomo[0].length];
        double volumeMinimo;
        double volumeMaximo;

        for (int i = 0; i < cromossomo.length; i++) {
            volumeMaximo = cascata.get(i).getVolumeMaximo();
            volumeMinimo = cascata.get(i).getVolumeMinimo();
            for (int j = 0; j < cromossomo[0].length; j++) {
                cromossomoReal[i][j] = volumeMinimo + (volumeMaximo - volumeMinimo) * cromossomo[i][j];
            }
        }

        return cromossomoReal;
    }
    
    public double[][] converteCromossomoIdeaProfRicardo(List<Hidroeletrica> cascata, double[][] cromossomo) {
        double[][] cromossomoReal = new double[cromossomo.length][cromossomo[0].length];
        double volumeMinimo;
        double volumeMaximo;

        for (int i = 0; i < cromossomo.length; i++) {
            volumeMaximo = cascata.get(i).getVolumeMaximo();
            volumeMinimo = cascata.get(i).getVolumeMinimo();
            for (int j = 0; j < cromossomo[0].length; j++) {
                cromossomoReal[i][j] = volumeMinimo + (volumeMaximo - volumeMinimo) * cromossomo[i][j];
            }
        }

        return cromossomoReal;
    }

    public double simulacaoOperacaoEnergeticaCascata(ConfiguracaoTeste ct, String nomeDoArquivo) {
        this.cascata = ct.getCascata();
        this.porcentagemPotenciaInstalada = ct.getPorcentagemPotenciaInstalada();
        this.porcentagemVazaoNatural = ct.getPorcentagemVazaoNatural();
        double DEMANDA = 0;

        for (int i = 0; i < cascata.size(); i++) {
            DEMANDA += porcentagemPotenciaInstalada * cascata.get(i).getPotenciaEfetiva()[0] * cascata.get(i).getNumueroDeConjuntoDeMaquinas()[0];
        }

        double[] vazaoEmborcacao = porcentagemVazaoNatural(vazaoNaturalEmborcacao);
        double[] vazaoItumbiara = porcentagemVazaoNatural(vazaoNaturalItumbiara);
        double[] vazaoSaoSimao = porcentagemVazaoNatural(vazaoNaturalSaoSimao);

        List<double[]> vazoesNaturais = new ArrayList<>();
        vazoesNaturais.add(vazaoEmborcacao);
        vazoesNaturais.add(vazaoItumbiara);
        vazoesNaturais.add(vazaoSaoSimao);

        volumesFinais = converteCromossomo(cascata);
        volumesIniciais = calculaVolumesIniciaisCascata(cascata, volumesFinais);
        volumesMedios = calculaVolumesMediosCascata(cascata, volumesIniciais, volumesFinais);
        vazoesIncrementais = calculaVazaoIncremental(cascata, vazoesNaturais);
        vazoesDefluentes = calculaVazaoDefluenteCascata(cascata, volumesIniciais, volumesFinais, vazoesIncrementais);
        cotasMontanteCascata = calculaCotaMontanteCascata(cascata, volumesMedios);
        cotasJusanteCascata = calculaCotaJusanteCascata(cascata, vazoesDefluentes);
        alturasDeQuedaLiquidaCascata = calculaAlturaDeQuedaLiquidaCascata(cascata, cotasMontanteCascata, cotasJusanteCascata);
        engolimentosMaximoCascata = calculaEngolimentoMaximoCascata(cascata, volumesMedios, vazoesDefluentes, 1);
        alturaQuedaLiquidaEngolimento = calculaAlturaEngolimentoMaximoCascata(cascata, volumesMedios, vazoesDefluentes, 1);
        potenciaMaxima = calculaPotenciaMaximaCascata(cascata, alturaQuedaLiquidaEngolimento);
        geracaoHidraulicaMaxContinua = calculaGeracaoHidraulicaMaxContinuaCascata(cascata, potenciaMaxima);
        limitesMaxVazaoTurbinada = calculaLimiteMaxVazaoTurbinada(cascata, volumesMedios, geracaoHidraulicaMaxContinua);
        vazoesTurbinadas = calculaVazaoTurbinada(cascata, vazoesDefluentes, limitesMaxVazaoTurbinada, engolimentosMaximoCascata);
        vazoesVertidas = calculaVazaoVertida(cascata, vazoesDefluentes, limitesMaxVazaoTurbinada, engolimentosMaximoCascata);
        
        geracaoHidraulicaCascata = calculaGeracaoHidraulicaCascata(cascata, alturasDeQuedaLiquidaCascata, vazoesTurbinadas);

        this.geracaoHidraulicaMensal = new double[cromossomo[0].length];
        this.geracaoHidraulicaMensal = calculaGeracaoHidraulicaMensalCascata(geracaoHidraulicaCascata);

        double custoDaOPeracao = calculaCustoOperacaoCascataResultado(cascata, DEMANDA, geracaoHidraulicaMensal, 0.08);
        this.custoDaOperacao = custoDaOPeracao;
        gravarArquivoTXT(nomeDoArquivo);

        return custoDaOPeracao;
    }

    // converte os volumes em hm3 em valores normalizados entre 0 e 1 (fita de cromossomo)
    public double[][] converteCromossomo(List<Hidroeletrica> cascata, double[][] volumesFinais) {
        double volumeMaximo;
        double volumeMinimo;
        double[][] cromossomoConvertido = new double[volumesFinais.length][volumesFinais[0].length];

        for (int i = 0; i < volumesFinais.length; i++) {
            for (int j = 0; j < volumesFinais[0].length; j++) {
                volumeMaximo = cascata.get(i).getVolumeMaximo();
                volumeMinimo = cascata.get(i).getVolumeMinimo();
                cromossomoConvertido[i][j] = (volumesFinais[i][j] - volumeMinimo)/(volumeMaximo - volumeMinimo);
            }
        }

        return cromossomoConvertido;
    }

    // calcula o custo com penalização para a segunda fase
    public double simulacaoOperacaoEnergeticaCascataPenalizacao() {
        double DEMANDA = 0;
        for (int i = 0; i < cascata.size(); i++) {
            DEMANDA += porcentagemPotenciaInstalada * cascata.get(i).getPotenciaEfetiva()[0] * cascata.get(i).getNumueroDeConjuntoDeMaquinas()[0];
        }

        double[] vazaoEmborcacao = porcentagemVazaoNatural(vazaoNaturalEmborcacao);
        double[] vazaoItumbiara = porcentagemVazaoNatural(vazaoNaturalItumbiara);
        double[] vazaoSaoSimao = porcentagemVazaoNatural(vazaoNaturalSaoSimao);

        List<double[]> vazoesNaturais = new ArrayList<>();
        vazoesNaturais.add(vazaoEmborcacao);
        vazoesNaturais.add(vazaoItumbiara);
        vazoesNaturais.add(vazaoSaoSimao);

        volumesFinais = converteCromossomo(cascata);
        volumesIniciais = calculaVolumesIniciaisCascata(cascata, volumesFinais);
        volumesMedios = calculaVolumesMediosCascata(cascata, volumesIniciais, volumesFinais);
        vazoesIncrementais = calculaVazaoIncremental(cascata, vazoesNaturais);
        
        vazoesDefluentes = calculaVazaoDefluenteCascata(cascata, volumesIniciais, volumesFinais, vazoesIncrementais);
        corrigeConflitos();
        cotasMontanteCascata = calculaCotaMontanteCascata(cascata, volumesMedios);
        cotasJusanteCascata = calculaCotaJusanteCascata(cascata, vazoesDefluentes);
        alturasDeQuedaLiquidaCascata = calculaAlturaDeQuedaLiquidaCascata(cascata, cotasMontanteCascata, cotasJusanteCascata);
        engolimentosMaximoCascata = calculaEngolimentoMaximoCascata(cascata, volumesMedios, vazoesDefluentes, 1);
        alturaQuedaLiquidaEngolimento = calculaAlturaEngolimentoMaximoCascata(cascata, volumesMedios, vazoesDefluentes, 1);
        potenciaMaxima = calculaPotenciaMaximaCascata(cascata, alturaQuedaLiquidaEngolimento);
        geracaoHidraulicaMaxContinua = calculaGeracaoHidraulicaMaxContinuaCascata(cascata, potenciaMaxima);
        limitesMaxVazaoTurbinada = calculaLimiteMaxVazaoTurbinada(cascata, volumesMedios, geracaoHidraulicaMaxContinua);
        vazoesTurbinadas = calculaVazaoTurbinada(cascata, vazoesDefluentes, limitesMaxVazaoTurbinada, engolimentosMaximoCascata);
        vazoesVertidas = calculaVazaoVertida(cascata, vazoesDefluentes, limitesMaxVazaoTurbinada, engolimentosMaximoCascata);
        geracaoHidraulicaCascata = calculaGeracaoHidraulicaCascata(cascata, alturasDeQuedaLiquidaCascata, vazoesTurbinadas);
        double[] geracaoHidrualicaMensal = calculaGeracaoHidraulicaMensalCascata(geracaoHidraulicaCascata);
        this.custoDaOperacao = calculaCustoOperacaoCascata(cascata, DEMANDA, geracaoHidrualicaMensal, 0.08);
        
  //      System.out.println("Custo da Operação = " + custoDaOperacao);
      //  double avaliacao = custoDaOperacao;
        
       double avaliacao = custoDaOperacao + 1000 * Math.pow((somaVazoesDefluentesMinimas[0] * cascata.get(0).getVazaoDefluenteMinima()
                + somaVazoesDefluentesMinimas[1] * cascata.get(1).getVazaoDefluenteMinima()), 2)
                + Math.pow((somaVazoesVertidas[0] + somaVazoesVertidas[1] + somaVazoesVertidas[2]), 2);

//        double avaliacao = custoDaOperacao + 10 * Math.pow((somaVazoesDefluentesMinimas[0] * cascata.get(0).getVazaoDefluenteMinima()
//                + somaVazoesDefluentesMinimas[1] * cascata.get(1).getVazaoDefluenteMinima()), 2)
//                + Math.pow((somaVazoesVertidas[0] + somaVazoesVertidas[1] + somaVazoesVertidas[2]), 2);

//        return avaliacao;
            
          return custoDaOperacao;
    }
    
    
    // calcula o custo com penalização para a segunda fase
    public double simulacaoOperacaoEnergeticaCascataPSO() {
        double DEMANDA = 0;
        for (int i = 0; i < cascata.size(); i++) {
            DEMANDA += porcentagemPotenciaInstalada * cascata.get(i).getPotenciaEfetiva()[0] * cascata.get(i).getNumueroDeConjuntoDeMaquinas()[0];
        }

        double[] vazaoEmborcacao = porcentagemVazaoNatural(vazaoNaturalEmborcacao);
        double[] vazaoItumbiara = porcentagemVazaoNatural(vazaoNaturalItumbiara);
        double[] vazaoSaoSimao = porcentagemVazaoNatural(vazaoNaturalSaoSimao);

        List<double[]> vazoesNaturais = new ArrayList<>();
        vazoesNaturais.add(vazaoEmborcacao);
        vazoesNaturais.add(vazaoItumbiara);
        vazoesNaturais.add(vazaoSaoSimao);

        volumesFinais = cromossomo;
        volumesIniciais = calculaVolumesIniciaisCascata(cascata, volumesFinais);
        volumesMedios = calculaVolumesMediosCascata(cascata, volumesIniciais, volumesFinais);
        vazoesIncrementais = calculaVazaoIncremental(cascata, vazoesNaturais);
        vazoesDefluentes = calculaVazaoDefluenteCascata(cascata, volumesIniciais, volumesFinais, vazoesIncrementais);
        corrigeConflitos();
        cotasMontanteCascata = calculaCotaMontanteCascata(cascata, volumesMedios);
        cotasJusanteCascata = calculaCotaJusanteCascata(cascata, vazoesDefluentes);
        alturasDeQuedaLiquidaCascata = calculaAlturaDeQuedaLiquidaCascata(cascata, cotasMontanteCascata, cotasJusanteCascata);
        engolimentosMaximoCascata = calculaEngolimentoMaximoCascata(cascata, volumesMedios, vazoesDefluentes, 1);
        alturaQuedaLiquidaEngolimento = calculaAlturaEngolimentoMaximoCascata(cascata, volumesMedios, vazoesDefluentes, 1);
        potenciaMaxima = calculaPotenciaMaximaCascata(cascata, alturaQuedaLiquidaEngolimento);
        geracaoHidraulicaMaxContinua = calculaGeracaoHidraulicaMaxContinuaCascata(cascata, potenciaMaxima);
        limitesMaxVazaoTurbinada = calculaLimiteMaxVazaoTurbinada(cascata, volumesMedios, geracaoHidraulicaMaxContinua);
        vazoesTurbinadas = calculaVazaoTurbinada(cascata, vazoesDefluentes, limitesMaxVazaoTurbinada, engolimentosMaximoCascata);
        vazoesVertidas = calculaVazaoVertida(cascata, vazoesDefluentes, limitesMaxVazaoTurbinada, engolimentosMaximoCascata);
        geracaoHidraulicaCascata = calculaGeracaoHidraulicaCascata(cascata, alturasDeQuedaLiquidaCascata, vazoesTurbinadas);
        double[] geracaoHidrualicaMensal = calculaGeracaoHidraulicaMensalCascata(geracaoHidraulicaCascata);
        this.custoDaOperacao = calculaCustoOperacaoCascata(cascata, DEMANDA, geracaoHidrualicaMensal, 0.08);
        
        return custoDaOperacao;
    }
    
    
    
    public void gravarArquivoTXT(String nomeDoArquivo) {
        try {
            FileWriter arquivo = new FileWriter("C:/Artigo/" + nomeDoArquivo + ".txt");
//            String caminhoDoArquivo;  
//            JFileChooser janelaParaSalvarArquivo = new JFileChooser();
//            janelaParaSalvarArquivo.setDialogTitle("Salvar arquivo VAZOES.DAT convertido");
//            int retorno = janelaParaSalvarArquivo.showSaveDialog(null);
//            if (retorno == JFileChooser.APPROVE_OPTION) {
//                caminhoDoArquivo = janelaParaSalvarArquivo.getSelectedFile().getAbsolutePath();
//            } else {
//                caminhoDoArquivo = "C:/"; //caminho default
//            }
//            
//            FileWriter arquivo = new FileWriter(caminhoDoArquivo + ".txt");
            PrintWriter gravarArquivo = new PrintWriter(arquivo);

            gravarArquivo.printf("Resultado Otimização AG%n");

            gravarArquivo.printf("%nCusto Total da Operação:%n");
            gravarArquivo.printf(String.format("%.10f", custoDaOperacao) + "%n");

            gravarArquivo.printf("%nComplementação Térmica no Período:%n");
            gravarArquivo.printf(String.format("%.10f", complementacaoTermicaTotal) + "MW%n");

            gravarArquivo.printf("%nCromossomo Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", cromossomo[0][i]) + "%n");
            }

            gravarArquivo.printf("%nCromossomo Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", cromossomo[1][i]) + "%n");
            }

            gravarArquivo.printf("%nCromossomo Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", cromossomo[2][i]) + "%n");
            }

            gravarArquivo.printf("%nGeracação Hidráulica Período:%n");
            for (int i = 0; i < geracaoHidraulicaMensal.length; i++) {
                gravarArquivo.printf(String.format("%.10f", geracaoHidraulicaMensal[i]) + "%n");
            }

            gravarArquivo.printf("%n%nComplementação Térmica Período:%n");
            for (int i = 0; i < complementacaoTermicaMensal.length; i++) {
                gravarArquivo.printf(String.format("%.10f", complementacaoTermicaMensal[i]) + "%n");
            }

            gravarArquivo.printf("%nVazao Natural da Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", vazaoNaturalEmborcacao[i]) + "%n");
            }

            gravarArquivo.printf("%nVazao Natural da Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", vazaoNaturalItumbiara[i]) + "%n");
            }

            gravarArquivo.printf("%nVazao Natural da Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", vazaoNaturalSaoSimao[i]) + "%n");
            }

            gravarArquivo.printf("%nVolume Final da Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", volumesFinais[0][i]) + "%n");
            }

            gravarArquivo.printf("%nVolume Final da Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", volumesFinais[1][i]) + "%n");
            }

            gravarArquivo.printf("%nVolume Final da Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", volumesFinais[2][i]) + "%n");
            }

            System.out.println("88888888888888gravando no arquivo888888888888888888888888888");
            for (int i = 0; i < volumesFinais.length; i++) {
                System.out.println();
                for (int j = 0; j < volumesFinais[0].length; j++) {
                    System.out.println(String.format("%.10f", volumesFinais[i][j]));
                }
            }

            gravarArquivo.printf("%nVolume Inicial da Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", volumesIniciais[0][i]) + "%n");
            }

            gravarArquivo.printf("%nVolume Inicial da Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", volumesIniciais[1][i]) + "%n");
            }

            gravarArquivo.printf("%nVolume Inicial da Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", volumesIniciais[2][i]) + "%n");
            }

            gravarArquivo.printf("%nVolume Médio da Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", volumesMedios[0][i]) + "%n");
            }

            gravarArquivo.printf("%nVolume Médio da Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", volumesMedios[1][i]) + "%n");
            }

            gravarArquivo.printf("%nVolume Médio da Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", volumesMedios[2][i]) + "%n");
            }


            gravarArquivo.printf("%nVazao Incremantal da Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", vazoesIncrementais[0][i]) + "%n");
            }

            gravarArquivo.printf("%nVazao Incremental da Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", vazoesIncrementais[1][i]) + "%n");
            }

            gravarArquivo.printf("%nVazao Incrementais da Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", vazoesIncrementais[2][i]) + "%n");
            }

            gravarArquivo.printf("%nVazao Defluente da Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", vazoesDefluentes[0][i]) + "%n");
            }

            gravarArquivo.printf("%nVazao Defluente da Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", vazoesDefluentes[1][i]) + "%n");
            }

            gravarArquivo.printf("%nVazao Defluente da Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", vazoesDefluentes[2][i]) + "%n");
            }

            gravarArquivo.printf("%nCota Montante Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", cotasMontanteCascata[0][i]) + "%n");
            }

            gravarArquivo.printf("%nCota Montante Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", cotasMontanteCascata[1][i]) + "%n");
            }

            gravarArquivo.printf("%nCota Montante da Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", cotasMontanteCascata[2][i]) + "%n");
            }

            gravarArquivo.printf("%nCota Jusante Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", cotasJusanteCascata[0][i]) + "%n");
            }

            gravarArquivo.printf("%nCota Jusante Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", cotasJusanteCascata[1][i]) + "%n");
            }

            gravarArquivo.printf("%nCota Jusante da Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", cotasJusanteCascata[2][i]) + "%n");
            }

            gravarArquivo.printf("%nAltura de Queda Líquida Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", alturasDeQuedaLiquidaCascata[0][i]) + "%n");
            }

            gravarArquivo.printf("%nAltura de Queda Líquida Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", alturasDeQuedaLiquidaCascata[1][i]) + "%n");
            }

            gravarArquivo.printf("%nAltura de Queda Líquida Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", alturasDeQuedaLiquidaCascata[2][i]) + "%n");
            }

            gravarArquivo.printf("%nEngolimento Máximo Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", engolimentosMaximoCascata[0][i]) + "%n");
            }

            gravarArquivo.printf("%nEngolimento Máximo Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", engolimentosMaximoCascata[1][i]) + "%n");
            }

            gravarArquivo.printf("%nEngolimento Máximo Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", engolimentosMaximoCascata[2][i]) + "%n");
            }

            gravarArquivo.printf("%nGeração Hidráulica Máxima Contínua Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", geracaoHidraulicaMaxContinua[0][i]) + "%n");
            }

            gravarArquivo.printf("%nGeração Hidráulica Máxima Contínua Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", geracaoHidraulicaMaxContinua[1][i]) + "%n");
            }

            gravarArquivo.printf("%nGeração Hidráulica Máxima Contínua Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", geracaoHidraulicaMaxContinua[2][i]) + "%n");
            }

            gravarArquivo.printf("%nLimite Máximo da Vazão Turbinada Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", limitesMaxVazaoTurbinada[0][i]) + "%n");
            }

            gravarArquivo.printf("%nLimite Máximo da Vazão Turbinada Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", limitesMaxVazaoTurbinada[1][i]) + "%n");
            }

            gravarArquivo.printf("%nLimite Máximo da Vazão Turbinada Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", limitesMaxVazaoTurbinada[2][i]) + "%n");
            }

            gravarArquivo.printf("%nVazão Turbinada Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", vazoesTurbinadas[0][i]) + "%n");
            }

            gravarArquivo.printf("%nVazão Turbinada Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", vazoesTurbinadas[1][i]) + "%n");
            }

            gravarArquivo.printf("%nVazão Turbinada Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", vazoesTurbinadas[2][i]) + "%n");
            }

            gravarArquivo.printf("%nVazão Vertida Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", vazoesVertidas[0][i]) + "%n");
            }

            gravarArquivo.printf("%nVazão Vertida Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", vazoesVertidas[1][i]) + "%n");
            }

            gravarArquivo.printf("%nVazão Vertida Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", vazoesVertidas[2][i]) + "%n");
            }

            gravarArquivo.printf("%nPotência Máxima Usina 1:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", potenciaMaxima[0][i]) + "%n");
            }

            gravarArquivo.printf("%nPotência Máxima Usina 2:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", potenciaMaxima[1][i]) + "%n");
            }

            gravarArquivo.printf("%nPotência Máxima Usina 3:%n");
            for (int i = 0; i < cromossomo[0].length; i++) {
                gravarArquivo.printf(String.format("%.10f", potenciaMaxima[2][i]) + "%n");
            }

            arquivo.close();
            gravarArquivo.close();
        } catch (Exception e) {
            System.out.println("ERRO! \n" + e);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //idea professor Ricardo
    public double[] calculaComplementacaoTermica(double[][] cromossomo) {
        double[][] volumesFinais = converteCromossomoIdeaProfRicardo(this.cascata, cromossomo);

        double demanda = 0;
        for (int i = 0; i < cascata.size(); i++) {
            demanda += porcentagemPotenciaInstalada * cascata.get(i).getPotenciaEfetiva()[0] * cascata.get(i).getNumueroDeConjuntoDeMaquinas()[0];
        }

        double[][] vazoesNaturais = {vazaoNaturalEmborcacao, vazaoNaturalItumbiara, vazaoNaturalSaoSimao};

        this.volumesFinais = volumesFinais;
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

        for (int i = 0; i < vazoesNaturais.length; i++) {
            for (int j = 0; j < vazoesNaturais[0].length; j++) {
                // caso seja o mês inicial
                if (j == 0) {
                    volumesIniciais[i][j] = this.cascata.get(i).getVolumeMaximo();
                } else {
                    volumesIniciais[i][j] = this.volumesFinais[i][j - 1];
                }

                if (i == 0) {
                    vazoesDefluentes[i][j] = calculaVazaoDefluente(this.cascata.get(i), volumesIniciais[i][j], volumesFinais[i][j], 0, vazoesNaturais[i][j], 0);
                } else {
                    vazoesDefluentes[i][j] = calculaVazaoDefluente(this.cascata.get(i), volumesIniciais[i][j], volumesFinais[i][j], vazoesDefluentes[i - 1][j], vazoesNaturais[i][j], 0);
                }

                volumesMedios[i][j] = (volumesIniciais[i][j] + this.volumesFinais[i][j]) / 2.0;
                cotasMontante[i][j] = calculaCotaMontante(this.cascata.get(i), volumesMedios[i][j]);
                cotasJusante[i][j] = calculaCotaJusante(this.cascata.get(i), vazoesDefluentes[i][j]);
                alturasDeQuedaLiquida[i][j] = calculaAlturaQuedaLiquida(this.cascata.get(i), cotasMontante[i][j], cotasJusante[i][j]);
                engolimentoMaximo[i][j] = calculaEngolimentoMaximo(this.cascata.get(i), volumesMedios[i][j], vazoesDefluentes[i][j], 1);
                alturaQuedaLiquidaEngolimento[i][j] = calculaAlturaQuedaLiquidaDoEngolimentoMaximo(this.cascata.get(i), volumesMedios[i][j], vazoesDefluentes[i][j], 1);
                potenciaMaxima[i][j] = calculaPotenciaMaxima(this.cascata.get(i), alturaQuedaLiquidaEngolimento[i][j]);
                geracaoHidraulicaMaxContinua[i][j] = calculaGeracaoHidraulicaMaximaContinua(this.cascata.get(i), potenciaMaxima[i][j]);
                limiteMaxVazaoTurbinada[i][j] = calculaLimiteMaximoVazaoTurbinada(this.cascata.get(i), volumesMedios[i][j], geracaoHidraulicaMaxContinua[i][j]);
                vazaoTurbinada[i][j] = calculaVazaoTurbinada(vazoesDefluentes[i][j], limiteMaxVazaoTurbinada[i][j], engolimentoMaximo[i][j]);
                vazaoVertida[i][j] = calculaVazaoVertida(vazoesDefluentes[i][j], limiteMaxVazaoTurbinada[i][j], engolimentoMaximo[i][j]);
                geracaoHidraulica[i][j] = calculaGeracaoHidraulica(this.cascata.get(i), alturasDeQuedaLiquida[i][j], vazaoTurbinada[i][j]);
            }
        }

        geracoesHidraulicaConjunto = new double[vazoesNaturais[0].length];;
        for (int i = 0; i < geracaoHidraulica[0].length; i++) {
            for (int j = 0; j < geracaoHidraulica.length; j++) {
                geracoesHidraulicaConjunto[i] += geracaoHidraulica[j][i];
            }
        }

        double soma = 0;
        //System.out.println("\nComplementação Térmica");
        for (int i = 0; i < geracoesHidraulicaConjunto.length; i++) {
            complementacaoTermica[i] = demanda - geracoesHidraulicaConjunto[i];
            soma += complementacaoTermica[i];
            //    System.out.println(String.format("%.10f", complementacaoTermica[i]));
        }

        mediaComplementacaoTermica = soma / geracoesHidraulicaConjunto.length;
        //System.out.println("Media DA CT = " + mediaComplementacaoTermica);

        return complementacaoTermica;
    }

    public double CalculaMediaComplementacaoTermica(double[] complementacaoTermicaMensal) {
        double mediaComplementacaoTermica = 0;
        double soma = 0;
        for (int i = 0; i < complementacaoTermicaMensal.length; i++) {
            soma += complementacaoTermicaMensal[i];
        }
        mediaComplementacaoTermica = soma / complementacaoTermicaMensal.length;

        return mediaComplementacaoTermica;
    }

    public void corrigeConflitosIdeaProfRicardo(int usina, int indice) {
        double[][] vazoesNaturais = {vazaoNaturalEmborcacao, vazaoNaturalItumbiara, vazaoNaturalSaoSimao};

        if (volumesFinais[usina][indice] >= this.cascata.get(usina).getVolumeMaximo()) {
            volumesFinais[usina][indice] = this.cascata.get(usina).getVolumeMaximo();
        }

        if (volumesFinais[usina][indice] <= this.cascata.get(usina).getVolumeMinimo()) {
            volumesFinais[usina][indice] = this.cascata.get(usina).getVolumeMinimo();
        }

        if (indice == 0) {
            System.out.println("VI = " + volumesIniciais[usina][indice]);
            volumesIniciais[usina][indice] = this.cascata.get(usina).getVolumeMaximo();
        } else {
            volumesIniciais[usina][indice] = volumesFinais[usina][indice - 1];
        }

        if (usina == 0) {
            vazoesDefluentes[usina][indice] = calculaVazaoDefluente(this.cascata.get(usina), volumesIniciais[usina][indice], volumesFinais[usina][indice], 0, vazoesNaturais[usina][indice], 0);
        } else {
            vazoesDefluentes[usina][indice] = calculaVazaoDefluente(this.cascata.get(usina), volumesIniciais[usina][indice], volumesFinais[usina][indice], vazoesDefluentes[usina - 1][indice], vazoesNaturais[usina][indice], 0);
        }
    }
    
    
    
    public double[][] inicializaIndividuo() {
        Random r = new Random();
        
        double demanda = 0;
        for (int i = 0; i < cascata.size(); i++) {
            demanda += porcentagemPotenciaInstalada * cascata.get(i).getPotenciaEfetiva()[0] * cascata.get(i).getNumueroDeConjuntoDeMaquinas()[0];
        }
        
        
        double[][] vazoesNaturais = {vazaoNaturalEmborcacao, vazaoNaturalItumbiara, vazaoNaturalSaoSimao};
        volumesFinais = new double[vazoesNaturais.length][vazoesNaturais[0].length];
        double[][] vazoesIncrementais = calculaVazaoIncremental(cascata, vazoesNaturais);
        volumesIniciais = new double[vazoesNaturais.length][vazoesNaturais[0].length];
        double[][] volumesMedios = new double[vazoesNaturais.length][vazoesNaturais[0].length];
        vazoesDefluentes = new double[vazoesNaturais.length][vazoesNaturais[0].length];
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
        
        for (int i = 0; i < vazoesNaturais.length; i++) {
            for (int j = 0; j < vazoesNaturais[0].length; j++) {
                // caso seja o mês inicial
                if (j == 0) {
                    volumesIniciais[i][j] = cascata.get(i).getVolumeMaximo();
                } else {
                    volumesIniciais[i][j] = volumesFinais[i][j - 1];
                }

                //calculo dos limites max e min da vazão defluente
                double engolimentoMaximoVolumeInicial = calculaEngolimentoMaximo(cascata.get(i), volumesIniciais[i][j], cascata.get(i).getVazaoDefluenteMinima(), 1);
                double vazaoDefluenteMaxima = engolimentoMaximoVolumeInicial + 0.1 * engolimentoMaximoVolumeInicial;

                if (volumesIniciais[i][j] >= cascata.get(i).getVolumeMaximo()) {
                    //gero um número aleatório entre a vazao natural afluente e a vazaoDefluenteMáxima para ser a vazao defluente
                    vazoesDefluentes[i][j] = r.nextDouble() * ((vazaoDefluenteMaxima - vazoesNaturais[i][j])) + vazoesNaturais[i][j];
                } else {
                    //gero um número aleatório entre a vazao defluente mínima e a vazaoDefluenteMáxima para ser a vazao defluente
                    vazoesDefluentes[i][j] = r.nextDouble() * ((vazaoDefluenteMaxima - cascata.get(i).getVazaoDefluenteMinima())) + cascata.get(i).getVazaoDefluenteMinima();
                }

                if (i == 0) {
                    volumesFinais[i][j] = volumesIniciais[i][j] + (vazoesIncrementais[i][j] - vazoesDefluentes[i][j]) * (2628000.0 / 1000000.0);
                } else {
                    volumesFinais[i][j] = volumesIniciais[i][j] + (vazoesIncrementais[i][j] + vazoesDefluentes[i - 1][j] - vazoesDefluentes[i][j]) * (2628000.0 / 1000000.0);
                }
                corrigeConflitosIdeaProfRicardo(i, j);

                volumesMedios[i][j] = (volumesIniciais[i][j] + volumesFinais[i][j]) / 2.0;
                cotasMontante[i][j] = calculaCotaMontante(cascata.get(i), volumesMedios[i][j]);
                cotasJusante[i][j] = calculaCotaJusante(cascata.get(i), vazoesDefluentes[i][j]);
                alturasDeQuedaLiquida[i][j] = calculaAlturaQuedaLiquida(cascata.get(i), cotasMontante[i][j], cotasJusante[i][j]);
                engolimentoMaximo[i][j] = calculaEngolimentoMaximo(cascata.get(i), volumesMedios[i][j], vazoesDefluentes[i][j], 1);
                alturaQuedaLiquidaEngolimento[i][j] = calculaAlturaQuedaLiquidaDoEngolimentoMaximo(cascata.get(i), volumesMedios[i][j], vazoesDefluentes[i][j], 1);
                potenciaMaxima[i][j] = calculaPotenciaMaxima(cascata.get(i), alturaQuedaLiquidaEngolimento[i][j]);
                geracaoHidraulicaMaxContinua[i][j] = calculaGeracaoHidraulicaMaximaContinua(cascata.get(i), potenciaMaxima[i][j]);
                limiteMaxVazaoTurbinada[i][j] = calculaLimiteMaximoVazaoTurbinada(cascata.get(i), volumesMedios[i][j], geracaoHidraulicaMaxContinua[i][j]);
                vazaoTurbinada[i][j] = calculaVazaoTurbinada(vazoesDefluentes[i][j], limiteMaxVazaoTurbinada[i][j], engolimentoMaximo[i][j]);
                vazaoVertida[i][j] = calculaVazaoVertida(vazoesDefluentes[i][j], limiteMaxVazaoTurbinada[i][j], engolimentoMaximo[i][j]);
                geracaoHidraulica[i][j] = calculaGeracaoHidraulica(cascata.get(i), alturasDeQuedaLiquida[i][j], vazaoTurbinada[i][j]);
                custoDaOperacao = calculaCustoOperacaoCascata(cascata, demanda, geracoesHidraulicaConjunto, 0.08);
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

        double[][] cromossomo = converteCromossomo(cascata, volumesFinais);
        
        for (int i = 0; i < cromossomo.length; i++) {
            System.out.println("\n");
            for (int j = 0; j < cromossomo[0].length; j++) {
                System.out.println(String.format("%.10f", cromossomo[i][j]));
                
            }
            
        }

        return cromossomo;
    }

    public double[] getGeracaoHidraulicaMensal() {
        return geracaoHidraulicaMensal;
    }

    public void setGeracaoHidraulicaMensal(double[] geracaoHidraulicaMensal) {
        this.geracaoHidraulicaMensal = geracaoHidraulicaMensal;
    }

    public double[] getComplementacaoTermicaMensal() {
        return complementacaoTermicaMensal;
    }

    public void setComplementacaoTermicaMensal(double[] complementacaoTermicaMensal) {
        this.complementacaoTermicaMensal = complementacaoTermicaMensal;
    }

    public double getCustoDaOperacao() {
        return custoDaOperacao;
    }

    public void setCustoDaOperacao(double custoDaOperacao) {
        this.custoDaOperacao = custoDaOperacao;
    }
}

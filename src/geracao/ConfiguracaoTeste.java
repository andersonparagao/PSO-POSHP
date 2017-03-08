/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geracao;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;
import usina.Hidroeletrica;

/**
 *
 * @author Anderson Aragao
 */
public final class ConfiguracaoTeste {
    
    private List<Hidroeletrica> usinasDoSistema = inicializaUsinas();
    private List<Hidroeletrica> cascata;
    private int quantidadeDeAnos;
    private double porcentagemVazaoNatural;
    private double porcentagemPotenciaInstalada;
    private double[][] vazoesNaturais;
    
    
    public ConfiguracaoTeste(int quantidadeDeAnos, double porcentagemVazaoNatural, double porcentagemPotenciaInstalada) {
        this.quantidadeDeAnos = quantidadeDeAnos;
        this.porcentagemVazaoNatural = porcentagemVazaoNatural;
        this.porcentagemPotenciaInstalada = porcentagemPotenciaInstalada;
        defineCascata();
    }

    
    public List<Hidroeletrica> getCascata() {
        return cascata;
    }

    
    public void setCascata(List<Hidroeletrica> cascata) {
        this.cascata = cascata;
    }

    
    public int getQuantidadeDeAnos() {
        return quantidadeDeAnos;
    }

    
    public void setQuantidadeDeAnos(int quantidadeDeAnos) {
        this.quantidadeDeAnos = quantidadeDeAnos;
    }

    
    public double getPorcentagemVazaoNatural() {
        return porcentagemVazaoNatural;
    }

    
    public void setPorcentagemVazaoNatural(double porcentagemVazaoNatural) {
        this.porcentagemVazaoNatural = porcentagemVazaoNatural;
    }

    
    public double getPorcentagemPotenciaInstalada() {
        return porcentagemPotenciaInstalada;
    }

    
    public void setPorcentagemPotenciaInstalada(double porcentagemPotenciaInstalada) {
        this.porcentagemPotenciaInstalada = porcentagemPotenciaInstalada;
    }
    
    public List inicializaUsinas() {
        String caminho = System.getProperty("user.dir").replace("\\", "/") + "/HIDR.DAT";
        LeArquivoHidroeletricas leitorDeArquivoHidroeletricas = new LeArquivoHidroeletricas();
        List<Hidroeletrica> listaDeHidroeletricas;
        List<Integer> dadosHexaConvertidosEmInteiro;
        
        DataInputStream dadosHexadecimal = leitorDeArquivoHidroeletricas.CarregaDadosDasHidroeletricas(caminho);
        dadosHexaConvertidosEmInteiro = leitorDeArquivoHidroeletricas.ConverteBytesEmInteiro(dadosHexadecimal);
        listaDeHidroeletricas = leitorDeArquivoHidroeletricas.separaDadosDeCadaUsina(dadosHexaConvertidosEmInteiro);
        return listaDeHidroeletricas;
    }
    
    public void defineCascata(){
//        Hidroeletrica emborcacao = usinasDoSistema.get(18);
//        emborcacao.setVazaoDefluenteMaxima(10e6);
//        emborcacao.setVazaoDefluenteMinima(77);
//        
//        Hidroeletrica itumbiara = usinasDoSistema.get(25);
//        itumbiara.setVazaoDefluenteMaxima(10e6);
//        itumbiara.setVazaoDefluenteMinima(254);
//        
//        Hidroeletrica saoSimao = usinasDoSistema.get(27);
//        saoSimao.setVazaoDefluenteMaxima(10e6);
//        saoSimao.setVazaoDefluenteMinima(408);
//        
//        this.cascata = new ArrayList<>();
//        this.cascata.add(emborcacao);
//        this.cascata.add(itumbiara);
//        this.cascata.add(saoSimao);

        Hidroeletrica emborcacao = new Hidroeletrica();
        emborcacao.setConjuntoMaquinas(1);
        emborcacao.setNumueroDeConjuntoDeMaquinas(new int[]{4});
        emborcacao.setPotenciaEfetiva(new double[]{298.0});
        emborcacao.setEngolimentoEfetivo(new int[]{262});
        emborcacao.setAlturaEfetiva(new double[]{130.30});
        emborcacao.setPolinomioCotaVolume(new double[]{568.09, 1.4506E-02, -1.2028E-06, 5.8303E-11, -1.1245E-15});
        List<double[]> polinomiosJusante = new ArrayList<>();
        polinomiosJusante.add(new double[] {519.78, 3.9966E-03, -1.0987E-06, 2.3438E-10, -1.7646E-14});
        emborcacao.setListaDePolinomiosJusante(polinomiosJusante);
        emborcacao.setVolumeMinimo(4669);
        emborcacao.setVolumeMaximo(17190);
        emborcacao.setVazaoDefluenteMinima(77);
        emborcacao.setVazaoDefluenteMaxima(1E20);
        emborcacao.setCotaCanalFuga(521.9);
        emborcacao.setTipoPerda(1);
        emborcacao.setPerdaValor(0.0127);
        emborcacao.setProdutibilidadeEspecifica(0.008731);
        emborcacao.setTaxaEif(0.02917);
        emborcacao.setIndisponibilidadeProgramada(0.12122);
        emborcacao.setTipoTurbina(1);

        
        Hidroeletrica itumbiara = new Hidroeletrica();
        itumbiara.setConjuntoMaquinas(1);
        itumbiara.setNumueroDeConjuntoDeMaquinas(new int[]{6});
        itumbiara.setPotenciaEfetiva(new double[]{380.0});
        itumbiara.setEngolimentoEfetivo(new int[]{537});
        itumbiara.setAlturaEfetiva(new double[]{80.20});
        itumbiara.setPolinomioCotaVolume(new double[]{471.16, 7.2805E-03, -5.6098E-07, 2.59776E-11, -4.845359E-16});
        polinomiosJusante = new ArrayList<>();
        polinomiosJusante.add(new double[] {433.0, 1.59584E-03, -8.177386E-08, 3.1735E-12, 0.0});
        itumbiara.setListaDePolinomiosJusante(polinomiosJusante);
        itumbiara.setVolumeMinimo(4573.0);
        itumbiara.setVolumeMaximo(17027.0);
        itumbiara.setVazaoDefluenteMinima(254);
        itumbiara.setVazaoDefluenteMaxima(1E20);
        itumbiara.setCotaCanalFuga(435.6);
        itumbiara.setTipoPerda(1);
        itumbiara.setPerdaValor(0.0120);
        itumbiara.setProdutibilidadeEspecifica(0.008829);
        itumbiara.setTaxaEif(0.02917);
        itumbiara.setIndisponibilidadeProgramada(0.12122);
        itumbiara.setTipoTurbina(1);
        
        
        Hidroeletrica saoSimao = new Hidroeletrica();
        saoSimao.setConjuntoMaquinas(1);
        saoSimao.setNumueroDeConjuntoDeMaquinas(new int[]{6});
        saoSimao.setPotenciaEfetiva(new double[]{280.0});
        saoSimao.setEngolimentoEfetivo(new int[]{437});
        saoSimao.setAlturaEfetiva(new double[]{70.90});
        saoSimao.setPolinomioCotaVolume(new double[]{358.33, 8.6173E-03, -8.8427E-07, 5.2933E-11, -1.2420E-15});
        polinomiosJusante = new ArrayList<>();
        polinomiosJusante.add(new double[] {315.59, 2.3503E-03, -1.3803E-07, 5.2340E-12, -7.8594E-17});
        saoSimao.setListaDePolinomiosJusante(polinomiosJusante);
        saoSimao.setVolumeMinimo(7000.0);
        saoSimao.setVolumeMaximo(12540.0);
        saoSimao.setVazaoDefluenteMinima(480);
        saoSimao.setVazaoDefluenteMaxima(1E20);
        saoSimao.setCotaCanalFuga(328.10);
        saoSimao.setTipoPerda(1);
        saoSimao.setPerdaValor(0.0062);
        saoSimao.setProdutibilidadeEspecifica(0.009025);
        saoSimao.setTaxaEif(0.02917);
        saoSimao.setIndisponibilidadeProgramada(0.12122);
        saoSimao.setTipoTurbina(1);
        
        this.cascata = new ArrayList<>();
        this.cascata.add(emborcacao);
        this.cascata.add(itumbiara);
        this.cascata.add(saoSimao);
    }    
}

package usina;


import java.util.List;


/**
 *
 * @author Anderson Aragão
 */
public class Hidroeletrica {
  

// dados relatiovos ao cadastro 
    private String nomeUsina;
    private int sistema;
    private int posto;
    private int empresa;
    private int jusante;
    private int desvio;
    private int codigoUsina;
    private String data;
    

    // reservatório
    private double volumeMinimo;
    private double volumeMaximo;
    private double volumeVertedouro;
    private double volumeDesvio;
    private double cotaMinima;
    private double cotaMaxima;
    private double polinomioCotaVolume[];
    private double polinomioAreaCota[];
    private byte evaporacaoMensal[];
    private String regulacao;

    
    // dados da usina
    private int conjuntoMaquinas;
    private int numueroDeConjuntoDeMaquinas[];
    private double potenciaEfetiva[];
    private double alturaEfetiva[];
    private int engolimentoEfetivo[];
    private double produtibilidadeEspecifica;
    private double perdaValor;
    private int tipoPerda;
    private double polinomioJusante[];
    private double cotaCanalFuga;
    private double fatorCargaMinimo;
    private double fatorCargaMaximo;
    private int influenciaVertimentoCanalFuga;
    private int volumeMinimoHistorico;
    private int numeroDaUnidadeBase;
    private int tipoTurbina;
    private int representacaoConjunto;
    private double taxaEif;
    private double indisponibilidadeProgramada;
    private int numeroDePolinomiosJusante;
    private List<double[]> listaDePolinomiosJusante;
    private double[] alturaDeReferenciaPolinomioJusante;
    private String observação;
    private double vazaoDefluenteMinima;
    private double vazaoDefluenteMaxima;
    

    public String getNomeUsina() {
        return nomeUsina;
    }

    public void setNomeUsina(String nomeUsina) {
        this.nomeUsina = nomeUsina;
    }

    public int getSistema() {
        return sistema;
    }

    public void setSistema(int sistema) {
        this.sistema = sistema;
    }

    public int getPosto() {
        return posto;
    }

    public void setPosto(int posto) {
        this.posto = posto;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public int getJusante() {
        return jusante;
    }

    public void setJusante(int jusante) {
        this.jusante = jusante;
    }

    public int getDesvio() {
        return desvio;
    }

    public void setDesvio(int desvio) {
        this.desvio = desvio;
    }

    public int getCodigoUsina() {
        return codigoUsina;
    }

    public void setCodigoUsina(int codigoUsina) {
        this.codigoUsina = codigoUsina;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getVolumeMinimo() {
        return volumeMinimo;
    }

    public void setVolumeMinimo(double volumeMinimo) {
        this.volumeMinimo = volumeMinimo;
    }

    public double getVolumeMaximo() {
        return volumeMaximo;
    }

    public void setVolumeMaximo(double volumeMaximo) {
        this.volumeMaximo = volumeMaximo;
    }

    public double getVolumeVertedouro() {
        return volumeVertedouro;
    }

    public void setVolumeVertedouro(double volumeVertedouro) {
        this.volumeVertedouro = volumeVertedouro;
    }

    public double getVolumeDesvio() {
        return volumeDesvio;
    }

    public void setVolumeDesvio(double volumeDesvio) {
        this.volumeDesvio = volumeDesvio;
    }

    public double getCotaMinima() {
        return cotaMinima;
    }

    public void setCotaMinima(double cotaMinima) {
        this.cotaMinima = cotaMinima;
    }

    public double getCotaMaxima() {
        return cotaMaxima;
    }

    public void setCotaMaxima(double cotaMaxima) {
        this.cotaMaxima = cotaMaxima;
    }

    public double[] getPolinomioCotaVolume() {
        return polinomioCotaVolume;
    }

    public void setPolinomioCotaVolume(double[] polinomioCotaVolume) {
        this.polinomioCotaVolume = polinomioCotaVolume;
    }

    public double[] getPolinomioAreaCota() {
        return polinomioAreaCota;
    }

    public void setPolinomioAreaCota(double[] polinomioAreaCota) {
        this.polinomioAreaCota = polinomioAreaCota;
    }

    public byte[] getEvaporacaoMensal() {
        return evaporacaoMensal;
    }

    public void setEvaporacaoMensal(byte[] evaporacaoMensal) {
        this.evaporacaoMensal = evaporacaoMensal;
    }

    public String getRegulacao() {
        return regulacao;
    }

    public void setRegulacao(String regulacao) {
        this.regulacao = regulacao;
    }

    public int getConjuntoMaquinas() {
        return conjuntoMaquinas;
    }

    public void setConjuntoMaquinas(int conjuntoMaquinas) {
        this.conjuntoMaquinas = conjuntoMaquinas;
    }

    public int[] getNumueroDeConjuntoDeMaquinas() {
        return numueroDeConjuntoDeMaquinas;
    }

    public void setNumueroDeConjuntoDeMaquinas(int[] numueroDeConjuntoDeMaquinas) {
        this.numueroDeConjuntoDeMaquinas = numueroDeConjuntoDeMaquinas;
    }

    public double[] getPotenciaEfetiva() {
        return potenciaEfetiva;
    }

    public void setPotenciaEfetiva(double[] potenciaEfetiva) {
        this.potenciaEfetiva = potenciaEfetiva;
    }

    public double[] getAlturaEfetiva() {
        return alturaEfetiva;
    }

    public void setAlturaEfetiva(double[] alturaEfetiva) {
        this.alturaEfetiva = alturaEfetiva;
    }

    public int[] getEngolimentoEfetivo() {
        return engolimentoEfetivo;
    }

    public void setEngolimentoEfetivo(int[] engolimentoEfetivo) {
        this.engolimentoEfetivo = engolimentoEfetivo;
    }

    public double getProdutibilidadeEspecifica() {
        return produtibilidadeEspecifica;
    }

    public void setProdutibilidadeEspecifica(double produtibilidadeEspecifica) {
        this.produtibilidadeEspecifica = produtibilidadeEspecifica;
    }

    public double getPerdaValor() {
        return perdaValor;
    }

    public void setPerdaValor(double perdaValor) {
        this.perdaValor = perdaValor;
    }

    public int getTipoPerda() {
        return tipoPerda;
    }

    public void setTipoPerda(int tipoPerda) {
        this.tipoPerda = tipoPerda;
    }

    public double[] getPolinomioJusante() {
        return polinomioJusante;
    }

    public void setPolinomioJusante(double[] polinomioJusante) {
        this.polinomioJusante = polinomioJusante;
    }

    public double getCotaCanalFuga() {
        return cotaCanalFuga;
    }

    public void setCotaCanalFuga(double cotaCanalFuga) {
        this.cotaCanalFuga = cotaCanalFuga;
    }

    public double getFatorCargaMinimo() {
        return fatorCargaMinimo;
    }

    public void setFatorCargaMinimo(double fatorCargaMinimo) {
        this.fatorCargaMinimo = fatorCargaMinimo;
    }

    public double getFatorCargaMaximo() {
        return fatorCargaMaximo;
    }

    public void setFatorCargaMaximo(double fatorCargaMaximo) {
        this.fatorCargaMaximo = fatorCargaMaximo;
    }

    public int getInfluenciaVertimentoCanalFuga() {
        return influenciaVertimentoCanalFuga;
    }

    public void setInfluenciaVertimentoCanalFuga(int influenciaVertimentoCanalFuga) {
        this.influenciaVertimentoCanalFuga = influenciaVertimentoCanalFuga;
    }

    public int getVolumeMinimoHistorico() {
        return volumeMinimoHistorico;
    }

    public void setVolumeMinimoHistorico(int volumeMinimoHistorico) {
        this.volumeMinimoHistorico = volumeMinimoHistorico;
    }

    public int getNumeroDaUnidadeBase() {
        return numeroDaUnidadeBase;
    }

    public void setNumeroDaUnidadeBase(int numeroDaUnidadeBase) {
        this.numeroDaUnidadeBase = numeroDaUnidadeBase;
    }

    public int getTipoTurbina() {
        return tipoTurbina;
    }

    public void setTipoTurbina(int tipoTurbina) {
        this.tipoTurbina = tipoTurbina;
    }

    public int getRepresentacaoConjunto() {
        return representacaoConjunto;
    }

    public void setRepresentacaoConjunto(int representacaoConjunto) {
        this.representacaoConjunto = representacaoConjunto;
    }

    public double getTaxaEif() {
        return taxaEif;
    }

    public void setTaxaEif(double taxaEif) {
        this.taxaEif = taxaEif;
    }

    public double getIndisponibilidadeProgramada() {
        return indisponibilidadeProgramada;
    }

    public void setIndisponibilidadeProgramada(double indisponibilidadeProgramada) {
        this.indisponibilidadeProgramada = indisponibilidadeProgramada;
    }

    public int getNumeroDePolinomiosJusante() {
        return numeroDePolinomiosJusante;
    }

    public void setNumeroDePolinomiosJusante(int numeroDePolinomiosJusante) {
        this.numeroDePolinomiosJusante = numeroDePolinomiosJusante;
    }

    public List<double[]> getListaDePolinomiosJusante() {
        return listaDePolinomiosJusante;
    }

    public void setListaDePolinomiosJusante(List<double[]> listaDePolinomiosJusante) {
        this.listaDePolinomiosJusante = listaDePolinomiosJusante;
    }

    public double[] getAlturaDeReferenciaPolinomioJusante() {
        return alturaDeReferenciaPolinomioJusante;
    }

    public void setAlturaDeReferenciaPolinomioJusante(double[] alturaDeReferenciaPolinomioJusante) {
        this.alturaDeReferenciaPolinomioJusante = alturaDeReferenciaPolinomioJusante;
    }

    public String getObservação() {
        return observação;
    }

    public void setObservação(String observação) {
        this.observação = observação;
    }

    public double getVazaoDefluenteMinima() {
        return vazaoDefluenteMinima;
    }

    public void setVazaoDefluenteMinima(double vazaoDefluenteMinima) {
        this.vazaoDefluenteMinima = vazaoDefluenteMinima;
    }

    public double getVazaoDefluenteMaxima() {
        return vazaoDefluenteMaxima;
    }

    public void setVazaoDefluenteMaxima(double vazaoDefluenteMaxima) {
        this.vazaoDefluenteMaxima = vazaoDefluenteMaxima;
    }
}

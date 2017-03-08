package geracao;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import usina.Hidroeletrica;

/**
 *
 * Classe criada para ler o arquivo HIDR.DAT e converter os números hexadecimais
 * em informações das hidroelétricas
 *
 * @author Anderson Aragão
 * 
 */
public class LeArquivoHidroeletricas {

    private List<Integer> listaComDadosDasUsinasHidroeletricas = new ArrayList<>();
    private List<Hidroeletrica> listaDeHidroeletricas = new ArrayList<>();
    private DataInputStream arquivoEmHexadecimal;

    // como iremos percorrer o dado byte a byte, preciso definir onde inicia o dado e onde termina dentro da listaComDadosDasUsinasHidroeletricas
    private int inicioDado, fimDado;

    // -------------- lista de métodos GET e SET de cada um dos atributos da classe LeArquivoHidroeketricas -------------- //
    public List<Hidroeletrica> getHidroeletricas() {
        return listaDeHidroeletricas;
    }

    public void setHidroeletricas(List<Hidroeletrica> listaDeHidroeletricas) {
        this.listaDeHidroeletricas = listaDeHidroeletricas;
    }

    public List<Integer> getListaComDadosDasUsinasHidroeletricas() {
        return listaComDadosDasUsinasHidroeletricas;
    }

    public void setListaComDadosDasUsinasHidroeletricas(List<Integer> listaComDadosDasUsinasHidroeletricas) {
        this.listaComDadosDasUsinasHidroeletricas = listaComDadosDasUsinasHidroeletricas;
    }

    public List<Hidroeletrica> getListaDeHidroeletricas() {
        return listaDeHidroeletricas;
    }

    public void setListaDeHidroeletricas(List<Hidroeletrica> listaDeHidroeletricas) {
        this.listaDeHidroeletricas = listaDeHidroeletricas;
    }

    public DataInputStream getArquivoEmHexadecimal() {
        return arquivoEmHexadecimal;
    }

    public void setArquivoEmHexadecimal(DataInputStream arquivoEmHexadecimal) {
        this.arquivoEmHexadecimal = arquivoEmHexadecimal;
    }

    public int getInicioDado() {
        return inicioDado;
    }

    public void setInicioDado(int inicioDado) {
        this.inicioDado = inicioDado;
    }

    public int getFimDado() {
        return fimDado;
    }

    public void setFimDado(int fimDado) {
        this.fimDado = fimDado;
    }
    // -------------- fim da lista de métodos GET e SET de cada um dos atributos da classe LeArquivoHidroeketricas -------------- //

    /**
     * Método que recebe como parâmetro o caminho do arquivo e retorna um
     * dataInputStream com o dado em Hexadecimal
     *
     * @param caminhoDoArquivo caminho do arquivo HIDR.DAT
     * @return Arquivo em Hexadecimal
     *
     */
    public DataInputStream CarregaDadosDasHidroeletricas(String caminhoDoArquivo) {
        try {
            // tenta carregar o arquivo
            FileInputStream arquivoParaSerLido = new FileInputStream(caminhoDoArquivo);
            arquivoEmHexadecimal = new DataInputStream(arquivoParaSerLido);
        } catch (Exception e) {
            // caso não consiga gera o erro
            System.out.println("Caminho errado");
        }
        return arquivoEmHexadecimal;
    }

    /**
     *
     * Método que recebe como parâmetro um dataInputStream com o dado em
     * Hexadecimal e converte em uma lista de números inteiros
     *
     * @param arquivoEmHexadecimal arquivo HIDR.DAT em Hexadecimal
     * @return Uma lista com os dados Hexadecimal convertidos em Inteiros
     *
     *
     */
    public List ConverteBytesEmInteiro(DataInputStream arquivoEmHexadecimal) {
        try {
            int valorDoByte = 0;
            while (valorDoByte != -1) {
                valorDoByte = arquivoEmHexadecimal.read();
                listaComDadosDasUsinasHidroeletricas.add(valorDoByte);
            }
            arquivoEmHexadecimal.close();
        } catch (IOException e) {
            System.out.println("Erro " + e);

        }
        return listaComDadosDasUsinasHidroeletricas;
    }

    /**
     * Método que recebe como parâmetro um vetor de quatro números inteiros
     * (cada um representa um byte de um inteiro de 32 bits) e retorna uma
     * String que representa o número binário da combinação dos 4 números
     * inteiros. Para mais informações consulte converão de inteiro em binário
     * 32 bits.
     *
     * @param valoresInteiros vetor de inteiros com quatro posições, onde cada
     * inteiro representa um byte (representação de um inteiro de 32 bits
     * considerada no arquivo HIDR.DAT)
     * @return Uma String com o dado Inteiro convertido em Binário
     *
     */
    public String decimalParaBinario(int[] valoresInteiros) {
        String[] cadaByteEmBinario = new String[4];
        String valorBinario;
        for (int i = 0; i < valoresInteiros.length; i++) {
            valorBinario = "";
            cadaByteEmBinario[i] = Integer.toBinaryString(valoresInteiros[i]);
            if (cadaByteEmBinario[i].length() < 8) {
                for (int j = 0; j < 8 - cadaByteEmBinario[i].length(); j++) {
                    valorBinario += "0";
                }
            }
            cadaByteEmBinario[i] = valorBinario + cadaByteEmBinario[i];
        }
        valorBinario = cadaByteEmBinario[3] + cadaByteEmBinario[2] + cadaByteEmBinario[1] + cadaByteEmBinario[0];
        return valorBinario;
    }

    /**
     * Método que recebe como parâmetro uma String representando o dado em
     * binário e retorna um double resultante da conversão do binário para um
     * número real de 32 bits. Para maiores informações consulte o link:
     * https://www.h-schmidt.net/FloatConverter/IEEE754.html
     *
     * @param valorBinario String com a representação binária do número real
     * (representação de 32 bits)
     * @return Um double representação real de 32bits do número binário
     *
     *
     */
    public double binarioParaFloat32bits(String valorBinario) {
        String sinalBinario = valorBinario.substring(0, 1);
        String expoenteBinario = valorBinario.substring(1, 9);
        String mantissaBinario = valorBinario.substring(9, 32);

        int expoente = Integer.parseInt(expoenteBinario, 2) - 127;
        double mantissaConvertida = 1;
        for (int i = 0; i < mantissaBinario.length(); i++) {
            if (mantissaBinario.charAt(i) == '1') {
                mantissaConvertida += Math.pow(2, (-i - 1));
            }
        }
        double valor_convertido = (double) ((Math.pow(-1, Integer.parseInt(sinalBinario)) * mantissaConvertida) * Math.pow(2, expoente));
        return valor_convertido;
    }

    /**
     * Método que gera um texto a partir do código ASCII de cada inteiro de um
     * dado intervalo da variável listaComDadosDasHidroeletricas. Para mais
     * informações sobre o código ASCII consulte o link:
     * https://www.ieee.li/computer/ascii.htm
     *
     * @return Uma String que representa a frase/palavra definida em um dado
     * intervalo
     *
     *
     */
    public String geraTextoPeloCodigoASCII() {
        String textoGerado = "";
        int codigoASCII;
        for (int i = inicioDado; i <= fimDado; i++) {
            //  System.out.println("Indice = " + i);
            codigoASCII = listaComDadosDasUsinasHidroeletricas.get(i);
            textoGerado += (char) codigoASCII;
        }
        return textoGerado;
    }

    /**
     * Método que gera um inteiro a partir de um intevalo de números inteiros da
     * variável listaComDadosDasHidroeletricas. Para mais informações consulte o
     * link: Para maiores informações consulte o link:
     * https://www.h-schmidt.net/FloatConverter/IEEE754.html
     *
     * @return Um número inteiro de 32 bits
     *
     *
     */
    public int Inteiro() {
        int valorInteiro = 0;
        int iterador = 0;
        int valoresInteriros[] = new int[4];
        for (int i = inicioDado; i <= fimDado; i++) {
            //   System.out.println("Indice = " + i);
            valoresInteriros[iterador] = listaComDadosDasUsinasHidroeletricas.get(i);
            iterador++;
        }
        valorInteiro = binarioParaInteiro(decimalParaBinario(valoresInteriros));
        return valorInteiro;
    }

    /**
     * Método que gera um inteiro a partir de um número binário. Para maiores
     * informações consulte o link:
     * https://www.h-schmidt.net/FloatConverter/IEEE754.html
     *
     * @param valorBinario String que representa um dado binário
     * @return Um número inteiro de 32 bits, resultante da conversoã do binário
     *
     *
     */
    public int binarioParaInteiro(String valorBinario) {
        int valorInteiro = 0;
        for (int i = 1; i < valorBinario.length(); i++) {
            if (valorBinario.charAt(i) == '1') {
                valorInteiro += (int) Math.pow(2, valorBinario.length() - i - 1);
            }
        }
        return valorInteiro;
    }

    /**
     * Método que gera um número real double a partir de um intevalo de números
     * inteiros da variável listaComDadosDasHidroeletricas. Para mais
     * informações consulte o link: Para maiores informações consulte o link:
     * https://www.h-schmidt.net/FloatConverter/IEEE754.html
     *
     * @return Um número real de 32 bits
     *
     *
     */
    public double Real() {
        double valorReal;
        int[] valoresInteiros = new int[4];
        int iterador = 0;
        for (int i = inicioDado; i <= fimDado; i++) {
            //   System.out.println("Indice = " + i);
            valoresInteiros[iterador] = (int) listaComDadosDasUsinasHidroeletricas.get(i);
            iterador++;
        }
        valorReal = binarioParaFloat32bits(decimalParaBinario(valoresInteiros));
        return valorReal;
    }

    /**
     * Método que gera um número inteiro e desloca os índices quatro casas (um
     * pulo) na listaComDadosDasUsinasHidroeletricas (1 para cada byte do
     * inteiro de 32 bits)
     *
     * @return Um número inteiro de 32 bits
     *
     *
     */
    public int converteEmInteiroComUmPulo() {
        inicioDado = fimDado + 1;
        fimDado = inicioDado + 3;
        return Inteiro();
    }

    /**
     * Método que gera um número double e desloca os índices quatro casas na
     * listaComDadosDasUsinasHidroeletricas (1 para cada byte do inteiro de 32
     * bits)
     *
     * @return Um número inteiro de 32 bits
     *
     *
     */
    public double converteEmDoubleComUmPulo() {
        inicioDado = fimDado + 1;
        fimDado = inicioDado + 3;
        return Real();
    }

    /**
     *
     * Método que desloca os índices na listaComDadosDasUsinasHidroeletricas 300
     * casas
     *
     *
     */
    public void puloNaAlturaDeQuedaEfetiva() {
        this.inicioDado = fimDado + 297;
        this.fimDado = inicioDado + 3;
    }

    /**
     * Método que gera um número inteiro e desloca os índices 12 casas (3 pulos)
     * na listaComDadosDasUsinasHidroeletricas (1 para cada byte do inteiro de
     * 32 bits)
     *
     * @return Um número inteiro de 32 bits
     *
     *
     */
    public int converteEmInteiroComTresPulos() {
        inicioDado = fimDado + 9;
        fimDado = inicioDado + 3;
        return Inteiro();
    }

    /**
     *
     * Método que desloca os índices 3 casas na
     * listaComDadosDasUsinasHidroeletricas (1 para cada byte do inteiro de 32
     * bits)
     *
     */
    public void pulaRegulacao() {
        this.inicioDado = fimDado + 3;
        this.fimDado = inicioDado;
    }

    /**
     *
     * Método que desloca os índices 4 casas na
     * listaComDadosDasUsinasHidroeletricas (1 para cada byte do inteiro de 32
     * bits)
     *
     */
    public void pularCanalFuga() {
        this.inicioDado = fimDado + 1;
        this.fimDado = inicioDado + 3;
    }

    /**
     *
     * Método que desloca os índices 9 casas na
     * listaComDadosDasUsinasHidroeletricas (1 para cada byte do inteiro de 32
     * bits)
     *
     */
    public void pulaData() {
        this.inicioDado = fimDado + 1;
        this.fimDado = inicioDado + 8;
    }

    /**
     *
     * Método que desloca os índices 44 casas na
     * listaComDadosDasUsinasHidroeletricas (1 para cada byte do inteiro de 32
     * bits)
     *
     *
     */
    public void pulaObservacao() {
        this.inicioDado = fimDado + 1;
        this.fimDado = inicioDado + 43;
    }

    /**
     * Método que percorre todos os dados da
     * listaComDadosDasUsinasHidroeletricas e cria uma lista em que cada
     * elemento representa uma usina hidroelétrica como suas características
     * particulares.
     *
     * @param dadosDoArquivoEmInteiro lista em que cada elemento é um número
     * inteiro (1 byte)
     * @return Uma lista de hidroelétrica
     *
     *
     */
    public List<Hidroeletrica> separaDadosDeCadaUsina(List<Integer> dadosDoArquivoEmInteiro) {

        // 792 é a quantidade de dados que estou lendo em cada usina hidroelétrica
        double quantidadeDeUsinas = (dadosDoArquivoEmInteiro.size() - 1) / 792;

        System.out.println(quantidadeDeUsinas);
        Hidroeletrica hidroeletrica;
        byte[] evaporacao;
        List<Hidroeletrica> listaDeHidroeletricas = new ArrayList<>();
        double[] polinomioCotaVolume;
        double[] polinomioAreaCota;
        double[] polinomioDeJusante;
        double[] potenciaEfetiva;
        double[] alturaDeQuedaEfetiva;
        List<double[]> listaDePolinomioJusante;
        double[] referenciasPolinomioJusante;

        int[] engolimentoEfetivo;
        int[] conjuntoDeMaquinas;
        int[] numerosInteiros;
        int contador = 1;

        while (contador <= quantidadeDeUsinas) {
            hidroeletrica = new Hidroeletrica();
            evaporacao = new byte[12];
            polinomioCotaVolume = new double[5];
            polinomioAreaCota = new double[5];
            potenciaEfetiva = new double[5];
            alturaDeQuedaEfetiva = new double[5];
            referenciasPolinomioJusante = new double[6];

            numerosInteiros = new int[5];
            conjuntoDeMaquinas = new int[5];
            engolimentoEfetivo = new int[5];
            listaDePolinomioJusante = new ArrayList<double[]>();

            hidroeletrica.setCodigoUsina(contador);

            // se irá começar a percorrer o arquivo ou se ele já está completo
            if (inicioDado == 0) {
                fimDado = inicioDado + 11;
            } else {
                inicioDado = inicioDado + 1;
                fimDado = inicioDado + 11;
            }

            // nome da usina
            hidroeletrica.setNomeUsina(geraTextoPeloCodigoASCII());

            // posto
            hidroeletrica.setPosto(converteEmInteiroComUmPulo());

            // Sistema
            hidroeletrica.setSistema(converteEmInteiroComTresPulos());

            // empresa
            hidroeletrica.setEmpresa(converteEmInteiroComUmPulo());

            // jusante
            hidroeletrica.setJusante(converteEmInteiroComUmPulo());

            // desvio
            hidroeletrica.setDesvio(converteEmInteiroComUmPulo());
            //=================================================================================================   

            //============================= RESERVATÓRIO =======================================================
            // vol min
            hidroeletrica.setVolumeMinimo(converteEmDoubleComUmPulo());

            // vol max
            hidroeletrica.setVolumeMaximo(converteEmDoubleComUmPulo());

            // vol vert
            hidroeletrica.setVolumeVertedouro(converteEmDoubleComUmPulo());

            // vol desvio
            hidroeletrica.setVolumeDesvio(converteEmDoubleComUmPulo());

            // cota min
            hidroeletrica.setCotaMinima(converteEmDoubleComUmPulo());

            // cota max
            hidroeletrica.setCotaMaxima(converteEmDoubleComUmPulo());

            //polinomio Cota x Volume
            for (int i = 0; i < polinomioCotaVolume.length; i++) {
                polinomioCotaVolume[i] = converteEmDoubleComUmPulo();
            }
            hidroeletrica.setPolinomioCotaVolume(polinomioCotaVolume);

            // polinomio Area x Cota
            for (int i = 0; i < polinomioAreaCota.length; i++) {
                polinomioAreaCota[i] = converteEmDoubleComUmPulo();
            }
            hidroeletrica.setPolinomioAreaCota(polinomioAreaCota);

            //evaporacao
            for (int i = 0; i < evaporacao.length; i++) {
                evaporacao[i] = (byte) converteEmInteiroComUmPulo();
            }
            hidroeletrica.setEvaporacaoMensal(evaporacao);
            //==================================================================================================

            //================================= DADOS DAS USINAS ===============================================        /*
            // # - numero do conjunto (aquivo matlab)
            hidroeletrica.setConjuntoMaquinas(converteEmInteiroComUmPulo());

            for (int i = 0; i < conjuntoDeMaquinas.length; i++) {
                // #maq - numero de máquinas do conjunto i
                conjuntoDeMaquinas[i] = converteEmInteiroComUmPulo();
            }
            hidroeletrica.setNumueroDeConjuntoDeMaquinas(conjuntoDeMaquinas);

            for (int i = 0; i < potenciaEfetiva.length; i++) {
                // PotEf(MW)
                potenciaEfetiva[i] = converteEmDoubleComUmPulo();
            }
            hidroeletrica.setPotenciaEfetiva(potenciaEfetiva);

            //System.out.println("Altura Efetiva");
            // HEf(m)  - ler 5 dados double
            puloNaAlturaDeQuedaEfetiva();
            for (int i = 0; i < alturaDeQuedaEfetiva.length; i++) {
                alturaDeQuedaEfetiva[i] = converteEmDoubleComUmPulo();
            }
            hidroeletrica.setAlturaEfetiva(alturaDeQuedaEfetiva);

            // qef
            for (int i = 0; i < engolimentoEfetivo.length; i++) {
                engolimentoEfetivo[i] = converteEmInteiroComUmPulo();
            }
            hidroeletrica.setEngolimentoEfetivo(engolimentoEfetivo);

            // Produt Específica
            hidroeletrica.setProdutibilidadeEspecifica(converteEmDoubleComUmPulo());

            // Perda Valor
            hidroeletrica.setPerdaValor(converteEmDoubleComUmPulo());

            // NO DE POLINÔMIOS A JUSANTE
            hidroeletrica.setNumeroDePolinomiosJusante(converteEmInteiroComUmPulo());

            // quantidade de polinomio de jusante: 6
            for (int i = 0; i < 6; i++) {
                polinomioDeJusante = new double[5];
                for (int j = 0; j < polinomioDeJusante.length; j++) {
                    polinomioDeJusante[j] = converteEmDoubleComUmPulo();
                }
                listaDePolinomioJusante.add(polinomioDeJusante);
            }
            hidroeletrica.setListaDePolinomiosJusante(listaDePolinomioJusante);

            // aqui são as referencias dos polinomios a jusante
            for (int i = 0; i < referenciasPolinomioJusante.length; i++) {
                referenciasPolinomioJusante[i] = converteEmDoubleComUmPulo();
            }
            hidroeletrica.setAlturaDeReferenciaPolinomioJusante(referenciasPolinomioJusante);

            // canal de fuga
            pularCanalFuga();
            hidroeletrica.setCotaCanalFuga(Real());

            // fator de carga mmin
            hidroeletrica.setFatorCargaMinimo(converteEmDoubleComUmPulo());

            // fator de carga max
            hidroeletrica.setFatorCargaMaximo(converteEmDoubleComUmPulo());

            // Infliencia do Vertimento no Canal de Fuga
            hidroeletrica.setInfluenciaVertimentoCanalFuga(converteEmInteiroComUmPulo());

            // Vazão Mínima do Histórico
            hidroeletrica.setVolumeMinimoHistorico(converteEmInteiroComUmPulo());

            // no de Unidade de Base
            hidroeletrica.setNumeroDaUnidadeBase(converteEmInteiroComUmPulo());

            // Tipo de turbina
            hidroeletrica.setTipoTurbina(converteEmInteiroComUmPulo());

            // Representação do conjunto
            hidroeletrica.setRepresentacaoConjunto(converteEmInteiroComUmPulo());

            // TEIF
            hidroeletrica.setTaxaEif(converteEmDoubleComUmPulo());

            // IP
            hidroeletrica.setIndisponibilidadeProgramada(converteEmDoubleComUmPulo());

            // TIPO PERDA
            hidroeletrica.setTipoPerda(converteEmInteiroComUmPulo());

            pulaData();
            hidroeletrica.setData(geraTextoPeloCodigoASCII());

            pulaObservacao();
            hidroeletrica.setObservação(geraTextoPeloCodigoASCII());

            pulaRegulacao();
            hidroeletrica.setRegulacao(geraTextoPeloCodigoASCII());
            contador++;

            //excluindo as usinas que não tem nenhum dado
            if (!(hidroeletrica.getNomeUsina().equals("            "))) {
                listaDeHidroeletricas.add(hidroeletrica);
            }
        }
        return listaDeHidroeletricas;
    }
}

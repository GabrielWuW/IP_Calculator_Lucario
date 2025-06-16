package br.dev.gabriel.classificadora.Model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.util.SubnetUtils;
import br.dev.gabriel.classificadora.Utils.*;

public class SubRedeInfos {
    // Armazenando ip e classe
    private String ipCidr;
    private String classe;

    // Construtor que recebe o IP e a classe
    public SubRedeInfos(String ipCidr, String classe) {
        this.ipCidr = ipCidr;
        this.classe = classe;
    }

    // Método para obter a base CIDR de acordo com a classe
    private int getCidrBasePorClasse(String classe) {
        return switch (classe) {
            case "A" -> 8;
            case "B" -> 16;
            case "C" -> 24;
            default -> throw new IllegalArgumentException("Classe " + classe + " não suporta sub Redes");
        };
    }

    // Método para calcular o número de sub-redes possíveis
    public String calcularNumeroSubRedes() {
        // Verifica se o IP e o cidr estão no formato correto
        String[] partes = ipCidr.split("/");
        if (partes.length != 2) {
            throw new IllegalArgumentException("Ops, verifique seu IP...");
        }

        // Verifica se o CIDR é válido
        int cidrAtual;
        try {
            cidrAtual = Integer.parseInt(partes[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("CIDR inválido.");
        }

        // Obtendo a base CIDR de acordo com a classe
        int cidrBase = getCidrBasePorClasse(classe);

        // Se o cidr atual for menor que a base, retorna mensagem de erro
        if (cidrAtual < cidrBase) {
            return "Ops, verifique seu CIDR...";
        }

        // Calcula o número de sub-redes possíveis
        // A fórmula é 2^(CIDR atual - CIDR base)
        int subRedes = (int) Math.pow(2, cidrAtual - cidrBase);
        return String.valueOf(subRedes);
    }

    // Criando uma lista: ["RedeX", "IpInicio", "IpFinal", "Broadcast", "Rede"]
    public List<List<String>> obterSubRedesCompletas() {
        List<List<String>> listaSubRedes = new ArrayList<>();

        String[] partes = ipCidr.split("/");
        if (partes.length != 2) {
            throw new IllegalArgumentException("Ops, verifique seu IP...");
        }

        String baseIP = partes[0];
        int cidrAtual = Integer.parseInt(partes[1]);
        int cidrBase = getCidrBasePorClasse(classe);

        if (cidrAtual < cidrBase) {
            throw new IllegalArgumentException("CIDR atual não pode ser menor que o CIDR base da classe");
        }

        int totalSubRedes = (int) Math.pow(2, cidrAtual - cidrBase);
        int tamanhoBloco = 32 - cidrAtual;

        long baseIPInt = IpUtils.ipToLong(baseIP);

        for (int i = 0; i < totalSubRedes; i++) {
            long subRedeIPInt = baseIPInt + ((long) i << tamanhoBloco);
            String subRedeIP = IpUtils.longToIp(subRedeIPInt);
            String cidrSubRede = subRedeIP + "/" + cidrAtual;

            SubnetUtils subnetUtils = new SubnetUtils(cidrSubRede);
            subnetUtils.setInclusiveHostCount(false);

            // Criando objeto para facilitar obter dados da sub-rede
            SubRedeDetalhes detalhes = new SubRedeDetalhes(subnetUtils.getInfo());

            List<String> subRede = new ArrayList<>();
            subRede.add("Rede" + (i + 1));                   // Nome da rede
            subRede.add(detalhes.getIpInicio());             // IP início válido
            subRede.add(detalhes.getIpFinal());              // Último IP válido
            subRede.add(detalhes.getBroadcast());            // Broadcast
            subRede.add(detalhes.getRede());                  // Endereço da rede

            listaSubRedes.add(subRede);
        }

        return listaSubRedes;
    }

    public static class SubRedeDetalhes {
        private final SubnetUtils.SubnetInfo info;

        public SubRedeDetalhes(SubnetUtils.SubnetInfo info) {
            this.info = info;
        }

        // Último IP válido da sub-rede
        public String getIpFinal() {
            return info.getHighAddress();
        }

        // Primeiro IP válido da sub-rede
        public String getIpInicio() {
            return info.getLowAddress();
        }

        // IP de broadcast da sub-rede
        public String getBroadcast() {
            return info.getBroadcastAddress();
        }

        // IP da rede da sub-rede
        public String getRede() {
            return info.getNetworkAddress();
        }
    }
}
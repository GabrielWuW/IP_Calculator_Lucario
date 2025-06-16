package br.dev.gabriel.classificadora.Model;

public class SubRedeInfos {
    //Armazenando ip e classe
    private String ipCidr;
    private String classe;

    //Construtor que recebe o IP e a classe
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

        //Obtendo a base CIDR de acordo com a classe
        int cidrBase = getCidrBasePorClasse(classe);

        //Se o cidr atual for menor que a base, retorna mensagem de erro
        if (cidrAtual < cidrBase) {
            return "Ops, verifique seu CIDR...";
        }

        // Calcula o número de sub-redes possíveis
        // A fórmula é 2^(CIDR atual - CIDR base)
        int subRedes = (int) Math.pow(2, cidrAtual - cidrBase);
        return String.valueOf(subRedes);
    }
}
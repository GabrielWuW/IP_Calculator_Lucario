package br.dev.gabriel.classificadora.Model;

public class MascaraInfos {
    String ipCidr;

    MascaraInfos(String ipCidr) {
        this.ipCidr = ipCidr;
    }

    public String mascaraBinario() {
        // Separando o IP do CIDR
        String[] partes = ipCidr.split("/");
        if (partes.length != 2) {
            throw new IllegalArgumentException("Ops, verifique seu IP...");
        }

        // Obtendo o CIDR
        int cidr;
        try {
            cidr = Integer.parseInt(partes[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("CIDR inválido: " + partes[1]);
        }

        // Verificando se o CIDR está no intervalo válido
        if (cidr < 0 || cidr > 32) {
            throw new IllegalArgumentException("CIDR deve estar entre 0 e 32");
        }

        // Construindo a máscara de sub-rede em binário
        StringBuilder mascaraBinaria = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            if (i < cidr) {
                // Colocando 1s até o CIDR
                mascaraBinaria.append("1");
            } else {
                // Colocando 0s após o CIDR
                mascaraBinaria.append("0");
            }
            // Adicionando ponto a cada 8 numeros, menos no último grupo.
            if ((i + 1) % 8 == 0 && i < 31) {
                mascaraBinaria.append(".");
            }
        }

        return mascaraBinaria.toString();
    }

    public String mascaraDecimal() {
        //Obtendo e separando a máscara binária
        String mascaraBin = mascaraBinario();
        String[] blocosBinarios = mascaraBin.split("\\.");

        //Criando StringBuilder para armazenar a máscara decimal
        StringBuilder mascaraDecimal = new StringBuilder();

        //Enquanto o numero de blocos for menor que 4, converte cada bloco binário para decimal
        for (int i = 0; i < blocosBinarios.length; i++) {
            //Convertendo o bloco binário para decimal
            int decimal = Integer.parseInt(blocosBinarios[i], 2);
            //Adicionando o bloco decimal ao StringBuilder
            mascaraDecimal.append(decimal);
            //Adicionando ponto a cada 8 números, menos no último bloco
            if (i < blocosBinarios.length - 1) {
                mascaraDecimal.append(".");
            }
        }
        return mascaraDecimal.toString();
    }
}
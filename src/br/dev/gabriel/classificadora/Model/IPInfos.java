package br.dev.gabriel.classificadora.Model;

public class IPInfos {

    private String classe;
    private String mascaraBinaria;

    public IPInfos(String ipCidr) {
        this.classe = encontraClasse(ipCidr);
        MascaraInfos mascara = new MascaraInfos(ipCidr);
        this.mascaraBinaria = mascara.mascaraBinario();
    }

    private String encontraClasse(String ipCidr) {
        String[] partes = ipCidr.split("/");
        if (partes.length != 2) {
            throw new IllegalArgumentException("Ops, verifique seu IP...");
        }

        String[] octetos = partes[0].split("\\.");
        if (octetos.length != 4) {
            throw new IllegalArgumentException("Seu IP está com um formato inválido...");
        }

        int primeiroOcteto;
        try {
            primeiroOcteto = Integer.parseInt(octetos[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Primeiro octeto inválido: " + octetos[0]);
        }

        if (primeiroOcteto >= 0 && primeiroOcteto <= 127) {
            return "A";
        } else if (primeiroOcteto >= 128 && primeiroOcteto <= 191) {
            return "B";
        } else if (primeiroOcteto >= 192 && primeiroOcteto <= 223) {
            return "C";
        } else if (primeiroOcteto >= 224 && primeiroOcteto <= 239) {
            return "D";
        } else if (primeiroOcteto >= 240 && primeiroOcteto <= 255) {
            return "E";
        } else {
            throw new IllegalArgumentException("Não foi possível classificar seu IP :(");
        }
    }

    public String getClasse() {
        return classe;
    }

    public String getMascaraBinaria() {
        return mascaraBinaria;
    }
}
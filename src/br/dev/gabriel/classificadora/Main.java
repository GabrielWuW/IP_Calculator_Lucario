package br.dev.gabriel.classificadora;

import br.dev.gabriel.classificadora.Model.IPInfos;

public class Main {
    public static void main(String[] args) {
        IPInfos ip = new IPInfos("192.168.0.0/24");
        System.out.println("Seu ip é classe: " + ip.getClasse());
        System.out.println("A mascara em binario é: " + ip.getMascaraBinaria());
        System.out.println("A mascara em decimal é: " + ip.getMascaraDecimal());
        System.out.println("Número de sub-redes possíveis: " + ip.getNumeroSubRedes());
    }
}
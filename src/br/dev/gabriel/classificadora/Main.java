package br.dev.gabriel.classificadora;

import br.dev.gabriel.classificadora.Model.IPInfos;

public class Main {
    public static void main(String[] args) {
        IPInfos ip = new IPInfos("192.168.15.1/24");
        System.out.println("Seu ip é classe: " + ip.getClasse());
        System.out.println("A mascara em binario é: " + ip.getMascaraBinaria());
    }
}
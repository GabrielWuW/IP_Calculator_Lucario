package br.dev.gabriel.classificadora;

import br.dev.gabriel.classificadora.Model.IPInfos;

public class Main {
    public static void main(String[] args) {
        IPInfos ip = new IPInfos("255.168.15.1/24");
        System.out.println("Seu ip é classe: " + ip.classe);
    }
}

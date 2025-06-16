package br.dev.gabriel.classificadora;

import br.dev.gabriel.classificadora.Model.IPInfos;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IPInfos ip = new IPInfos("192.168.0.0/28");

        System.out.println("Seu ip é classe: " + ip.getClasse());
        System.out.println("A mascara em binario é: " + ip.getMascaraBinaria());
        System.out.println("A mascara em decimal é: " + ip.getMascaraDecimal());
        System.out.println("Número de sub-redes possíveis: " + ip.getNumeroSubRedes());

        System.out.println("\nDetalhes completos das sub-redes:");
        List<List<String>> subRedes = ip.getSubRedesCompletas();
        if (subRedes != null && !subRedes.isEmpty()) {
            for (List<String> subRede : subRedes) {
                // Rede1: IpInicio: 192.168.0.1, IpFinal: 192.168.0.14, Broadcast: 192.168.0.15, Rede: 192.168.0.0
                System.out.printf(
                    "%s: IpInicio: %s, IpFinal: %s, Broadcast: %s, Rede: %s%n",
                    subRede.get(0), // RedeX
                    subRede.get(1), // IpInicio
                    subRede.get(2), // IpFinal
                    subRede.get(3), // Broadcast
                    subRede.get(4)  // Rede
                );
            }
        } else {
            System.out.println("Nenhuma sub-rede detalhada encontrada.");
        }
    }
}
package br.dev.gabriel.classificadora.Utils;

public class IpUtils {

    public static long ipToLong(String ip) {
        String[] octetos = ip.split("\\.");
        long resultado = 0;
        for (int i = 0; i < 4; i++) {
            resultado <<= 8;
            resultado |= Integer.parseInt(octetos[i]) & 0xFF;
        }
        return resultado;
    }

    public static String longToIp(long ip) {
        return String.format("%d.%d.%d.%d",
                (ip >> 24) & 0xFF,
                (ip >> 16) & 0xFF,
                (ip >> 8) & 0xFF,
                ip & 0xFF);
    }
}
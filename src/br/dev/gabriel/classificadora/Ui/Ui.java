package br.dev.gabriel.classificadora.Ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;

import br.dev.gabriel.classificadora.Model.IPInfos;

public class Ui {
    // Componentes da interface
    private JLabel tituloPagina;
    private JLabel textIP;
    private JTextField ipFornecido;
    private JButton btnCalcularIp;
    private JLabel infosIp;

    // Mostrar os resultados
    private JLabel resultadoClasse;
    private JLabel resultadoMascaraBinaria;
    private JLabel resultadoMascaraDecimal;
    private JLabel resultadoNumeroSubRedes;

    public Ui() {
        criarTela();
    }

    public void criarTela() {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Erro ao aplicar o tema: " + e.getMessage());
        }

        JFrame tela = new JFrame("Calculator IP Lucario");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(1200, 700);
        tela.setResizable(false);
        tela.setLayout(null);

        Container container = tela.getContentPane();

        tituloPagina = new JLabel("Super Calculadora de IPs");
        tituloPagina.setBounds(0, 30, 1200, 30);
        tituloPagina.setFont(new Font("Segoe UI", Font.BOLD, 24));
        tituloPagina.setHorizontalAlignment(JLabel.CENTER);
        tituloPagina.setForeground(Color.WHITE);

        ImageIcon icon = new ImageIcon("resources/lucario.png");
        JLabel labelImagem = new JLabel(icon);
        labelImagem.setBounds(10, 60, 1200, 120);
        labelImagem.setHorizontalAlignment(JLabel.CENTER);

        textIP = new JLabel();
        textIP.setBounds(50, 170, 200, 30);
        textIP.setFont(new Font("Segoe UI", Font.BOLD, 18));

        ipFornecido = new JTextField();
        ipFornecido.setBounds(50, 210, 300, 40);
        ipFornecido.setFont(new Font("Segoe UI", Font.BOLD, 16));

        btnCalcularIp = new JButton("Calcular!");
        btnCalcularIp.setBounds(400, 210, 150, 40);
        btnCalcularIp.setFont(new Font("Segoe UI", Font.BOLD, 16));

        infosIp = new JLabel();
        infosIp.setBounds(50, 270, 200, 30);
        infosIp.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JPanel painelIP = new JPanel();
        painelIP.setBounds(50, 300, 500, 300);
        painelIP.setBackground(new Color(40, 40, 40));
        painelIP.setLayout(null);

        resultadoClasse = new JLabel();
        resultadoClasse.setBounds(20, 20, 460, 30);
        resultadoClasse.setFont(new Font("Segoe UI", Font.BOLD, 16));
        painelIP.add(resultadoClasse);

        resultadoMascaraBinaria = new JLabel();
        resultadoMascaraBinaria.setBounds(20, 60, 460, 30);
        resultadoMascaraBinaria.setFont(new Font("Segoe UI", Font.BOLD, 16));
        painelIP.add(resultadoMascaraBinaria);

        resultadoMascaraDecimal = new JLabel();
        resultadoMascaraDecimal.setBounds(20, 100, 460, 30);
        resultadoMascaraDecimal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        painelIP.add(resultadoMascaraDecimal);

        resultadoNumeroSubRedes = new JLabel();
        resultadoNumeroSubRedes.setBounds(20, 140, 460, 30);
        resultadoNumeroSubRedes.setFont(new Font("Segoe UI", Font.BOLD, 16));
        painelIP.add(resultadoNumeroSubRedes);

        // Segundo painel
        JPanel painelScroll = new JPanel();
        painelScroll.setLayout(new BoxLayout(painelScroll, BoxLayout.Y_AXIS));
        painelScroll.setBackground(new Color(40, 40, 40));

        JScrollPane scrollPane = new JScrollPane(painelScroll);
        scrollPane.setBounds(600, 300, 500, 300);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Ação do botão para calcular o IP
        btnCalcularIp.addActionListener(e -> {
            try {
                String ip = ipFornecido.getText();

                IPInfos ipInfos = new IPInfos(ip);
                resultadoClasse.setText("Classe: " + ipInfos.getClasse());
                resultadoMascaraBinaria.setText("Máscara Binária: " + ipInfos.getMascaraBinaria());
                resultadoMascaraDecimal.setText("Máscara Decimal: " + ipInfos.getMascaraDecimal());
                resultadoNumeroSubRedes.setText("Número de Sub-redes: " + ipInfos.getNumeroSubRedes());
                painelScroll.removeAll();

                java.util.List<java.util.List<String>> subRedes = ipInfos.getSubRedesCompletas();
                if (subRedes != null && !subRedes.isEmpty()) {
                    for (java.util.List<String> subRede : subRedes) {
                        JPanel caixaSubRede = new JPanel();
                        caixaSubRede.setLayout(new BoxLayout(caixaSubRede, BoxLayout.Y_AXIS));
                        caixaSubRede.setBackground(new Color(30, 30, 30));
                        caixaSubRede.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));
                        caixaSubRede.setMaximumSize(new java.awt.Dimension(500, Integer.MAX_VALUE));

                        JLabel rede = new JLabel(subRede.get(0));
                        JLabel ipInicio = new JLabel("IP Inicial: " + subRede.get(1));
                        JLabel ipFinal = new JLabel("IP Final: " + subRede.get(2));
                        JLabel broadcast = new JLabel("Broadcast: " + subRede.get(3));
                        JLabel redeBase = new JLabel("Rede: " + subRede.get(4));

                        Font fonte = new Font("Segoe UI", Font.PLAIN, 14);
                        Color corTexto = Color.WHITE;
                        for (JLabel label : new JLabel[] { rede, ipInicio, ipFinal, broadcast, redeBase }) {
                            label.setFont(fonte);
                            label.setForeground(corTexto);
                            label.setAlignmentX(JLabel.LEFT_ALIGNMENT);
                            caixaSubRede.add(label);
                            caixaSubRede.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 5)));
                        }

                        painelScroll.add(caixaSubRede);
                        painelScroll.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 10)));
                    }
                } else {
                    JLabel nenhum = new JLabel("Ops, nenhuma sub-rede encontrada.");
                    nenhum.setFont(new Font("Segoe UI", Font.ITALIC, 14));
                    nenhum.setForeground(Color.LIGHT_GRAY);
                    painelScroll.add(nenhum);
                }

                painelScroll.revalidate();
                painelScroll.repaint();

            } catch (IllegalArgumentException ex) {
                javax.swing.JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro ao processar IP",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(null,
                        "Erro inesperado: " + ex.getMessage(),
                        "Erro interno",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        container.add(tituloPagina);
        container.add(labelImagem);
        container.add(textIP);
        container.add(ipFornecido);
        container.add(btnCalcularIp);
        container.add(infosIp);
        container.add(painelIP);
        container.add(scrollPane);

        tela.setVisible(true);
    }
}
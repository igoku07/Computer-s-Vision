import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Tela {

    public static void main(String[] args){
        //Janela Principal
        JFrame janela = new JFrame("Visao Computacional");

        janela.setSize(600, 500);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Paineil de cima
        JPanel barraCima = new JPanel();

        barraCima.setLayout(new GridLayout(1, 2));
        barraCima.setBackground(Color.DARK_GRAY);

        JButton abrir = new JButton("Abrir");

        JButton salvar = new JButton("Salvar");

        for (JButton botao : new JButton[]{abrir, salvar}) {
            botao.setFont(new Font("SansSerif", Font.BOLD, 14));
            botao.setBackground(new Color(240, 242, 246));
            botao.setForeground(new Color(49, 51, 63));

            botao.setContentAreaFilled(false); // Remove o efeito padrão
            botao.setOpaque(true);            // Mostra a cor de fundo

            botao.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(210, 214, 220)),
                    BorderFactory.createEmptyBorder(8, 18, 8, 18)
            ));

            botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        barraCima.add(abrir);
        barraCima.add(salvar);

        //Painel da Direita
        JPanel barraInfo = new JPanel();

        barraInfo.setLayout(new GridLayout(3,1));
        barraInfo.setBackground(Color.white);

        JLabel textoTeste = new JLabel("Informacoes da imagem");

        barraInfo.add(textoTeste);


        //config finais da janela
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        painelPrincipal.add(barraCima, BorderLayout.NORTH);
        // Depois, a imagem entra no CENTER desse painel.

        janela.add(painelPrincipal, BorderLayout.CENTER);
        janela.add(barraInfo, BorderLayout.EAST);


        janela.setVisible(true);

        // Fundo principal branco e lateral cinza-clara
        painelPrincipal.setBackground(Color.WHITE);
        barraInfo.setBackground(new Color(240, 242, 246));
        barraCima.setBackground(Color.WHITE);

        // Largura da lateral
        barraInfo.setPreferredSize(new Dimension(220, 0));

        // Espaço entre o conteúdo e as bordas:
        // cima, esquerda, baixo, direita
        barraInfo.setBorder(
                BorderFactory.createEmptyBorder(20, 15, 20, 15)
        );

        barraCima.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        // Fonte do texto
        textoTeste.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        textoTeste.setForeground(new Color(49, 51, 63));
    }
}
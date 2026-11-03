/**
 * Monta a janela principal, o botão de abertura,
 * o painel da imagem e os campos de informações.
 *
 * @author Felipe Estima Correia Urzi
 * @author Igor Dias da Silva
 * @author Pedro Henrique Freire
 * @author Thierry Nadjarian
 * @version 20261102
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tela {

    public Tela() {
        // Janela principal
        JFrame janela = new JFrame("Visao Computacional");

        janela.setSize(600, 550);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel de cima
        JPanel barraCima = new JPanel(new GridLayout(1, 2));
        barraCima.setBackground(Color.WHITE);

        JButton abrir = new JButton("Abrir");
        JButton salvar = new JButton("Salvar");

        for (JButton botao : new JButton[]{abrir, salvar}) {
            botao.setFont(new Font("Tahoma", Font.BOLD, 14));
            botao.setBackground(new Color(240, 242, 246));
            botao.setForeground(new Color(49, 51, 63));

            botao.setContentAreaFilled(false);
            botao.setOpaque(true);

            botao.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            new Color(210, 214, 220)
                    ),
                    BorderFactory.createEmptyBorder(8, 18, 8, 18)
            ));

            botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        barraCima.add(abrir);
        // barraCima.add(salvar);

        barraCima.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        // Painel da direita
        JPanel barraInfo = new JPanel(new GridLayout(3, 1));
        barraInfo.setBackground(new Color(240, 242, 246));
        barraInfo.setPreferredSize(new Dimension(220, 0));

        barraInfo.setBorder(
                BorderFactory.createEmptyBorder(20, 15, 20, 15)
        );

        JLabel textoTeste = new JLabel("Informacoes da imagem");
        textoTeste.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textoTeste.setForeground(new Color(49, 51, 63));

        barraInfo.add(textoTeste);

        JLabel textoPixel = new JLabel(
                "<html>Pixel<br>Passe o mouse sobre a imagem</html>"
        );

        textoPixel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textoPixel.setForeground(new Color(49, 51, 63));

        barraInfo.add(textoPixel);

        // Painel da imagem
        PainelImagem painelImagem = new PainelImagem(textoPixel);

        abrir.addActionListener(
                new ButtonAction(janela, painelImagem, textoTeste)
        );

        // Painel principal: barra em cima e imagem no centro
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.WHITE);

        painelPrincipal.add(barraCima, BorderLayout.NORTH);
        painelPrincipal.add(painelImagem, BorderLayout.CENTER);

        // Divisão entre imagem e informações
        JSplitPane proporcao = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                painelPrincipal,
                barraInfo
        );

        proporcao.setResizeWeight(0.50);

        janela.add(proporcao, BorderLayout.CENTER);
        janela.setVisible(true);
    }
}
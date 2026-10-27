import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tela {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Janela principal
            JFrame janela = new JFrame("Visao Computacional");

            janela.setSize(600, 500);
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

            // Painel da imagem
            JPanel painelImagem = new JPanel(new BorderLayout());
            painelImagem.setBackground(Color.WHITE);

            JLabel labelImagem = new JLabel();
            labelImagem.setHorizontalAlignment(SwingConstants.CENTER);

            try {
                BufferedImage imagem = ImageIO.read(
                        new File(
                                "C:\\Users\\ra00359600.PUCSP\\Downloads\\imagem.png"
                        )
                );

                if (imagem != null) {
                    labelImagem.setIcon(new ImageIcon(imagem));
                } else {
                    labelImagem.setText("Formato de imagem não reconhecido");
                }

            } catch (IOException e) {
                labelImagem.setText("Não foi possível abrir a imagem");
                e.printStackTrace();
            }

            painelImagem.add(labelImagem, BorderLayout.CENTER);

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
        });
    }
}
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ButtonAction implements ActionListener {

    private final JFrame janela;
    private final PainelImagem painelImagem;
    private final JLabel textoTeste;

    public ButtonAction(
            JFrame janela,
            PainelImagem painelImagem,
            JLabel textoTeste
    ) {
        this.janela = janela;
        this.painelImagem = painelImagem;
        this.textoTeste = textoTeste;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser seletor = new JFileChooser();

        int resultado = seletor.showOpenDialog(janela);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivo = seletor.getSelectedFile();

            try {
                BufferedImage imagem = ImageIO.read(arquivo);

                if (imagem != null) {
                    painelImagem.setImagem(imagem);

                    InfoImage informacoes = new InfoImage(imagem, arquivo);

// Protege caracteres do nome para exibição no JLabel com HTML
                    String nome = informacoes.getNome()
                            .replace("&", "&amp;")
                            .replace("<", "&lt;")
                            .replace(">", "&gt;");

                    textoTeste.setText(
                            "<html>Informacoes da imagem<br><br>"
                                    + "Nome: " + nome + "<br>"
                                    + "Tamanho: " + informacoes.getTamanho() + "<br>"
                                    + "Formato: " + informacoes.getFormato() + "<br>"
                                    + "Largura: " + informacoes.getLargura() + " px<br>"
                                    + "Altura: " + informacoes.getAltura() + " px<br>"
                                    + "Dimensões: " + informacoes.getResolucao()
                                    + "</html>"
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            janela,
                            "Formato de imagem não reconhecido."
                    );
                }

            } catch (IOException erro) {
                JOptionPane.showMessageDialog(
                        janela,
                        "Não foi possível abrir a imagem."
                );
                erro.printStackTrace();
            }
        }
    }
}

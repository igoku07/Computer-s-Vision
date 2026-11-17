/**
 * Controlador da ação do botão "Abrir".
 * Abre o seletor de arquivos do sistema operacional, tenta carregar a imagem
 * escolhida e, quando a leitura é bem-sucedida, atualiza o painel de exibição
 * e os dados apresentados na lateral da janela. A classe também trata
 * arquivos que não são reconhecidos como imagens e falhas de leitura.
 *
 * @author Felipe Estima Correia Urzi
 * @author Igor Dias da Silva
 * @author Pedro Henrique Freire
 * @author Thierry Nadjarian
 * @version 20261102
 */
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
        // Abre o seletor padrão de arquivos para o usuário escolher uma imagem.
        JFileChooser seletor = new JFileChooser();

        int resultado = seletor.showOpenDialog(janela);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivo = seletor.getSelectedFile();

            try {
                // O ImageIO tenta decodificar o arquivo em uma imagem raster.
                BufferedImage imagem = ImageIO.read(arquivo);

                if (imagem != null) {
                    // Atualiza a área de visualização com a imagem recém-carregada.
                    painelImagem.setImagem(imagem);

                    // Centraliza a consulta dos metadados em um objeto InfoImage.
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

/**
 * Reúne informações sobre uma imagem carregada e sobre seu arquivo de origem.
 *
 * Disponibiliza métodos para consultar nome, tamanho em bytes (formatado em
 * bytes, KiB ou MiB), formato identificado pelo ImageIO, largura, altura e
 * resolução. Esta classe não cria a interface gráfica; ela concentra a
 * obtenção dos metadados utilizados por ButtonAction.
 *
 * @author Felipe Estima Correia Urzi
 * @author Igor Dias da Silva
 * @author Pedro Henrique Freire
 * @author Thierry Nadjarian
 * @version 20261102
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class InfoImage
{
    private final BufferedImage InfoImage;
    private final File arquivo;

    public InfoImage(BufferedImage Imagem, File arquivo)
    {
        InfoImage = Imagem;
        this.arquivo = arquivo;
    }

    public int getAltura()
    {
        return InfoImage.getHeight();
    }

    public int getLargura()
    {
        return InfoImage.getWidth();
    }

    public String getResolucao()
    {
        return getLargura() + "X" + getAltura();
    }

    public String getNome()
    {
        return arquivo.getName();
    }

    // Converte o tamanho do arquivo para uma unidade legível.
    public String getTamanho()
    {
        long bytes = arquivo.length();

        if (bytes < 1024) {
            return bytes + " bytes";
        }

        if (bytes < 1024 * 1024) {
            return String.format("%.2f KiB", bytes / 1024.0);
        }

        return String.format("%.2f MiB", bytes / (1024.0 * 1024));
    }

    // Consulta os leitores registrados no ImageIO para identificar o formato.
    public String getFormato()
    {
        // Identifica o formato pelo conteúdo do arquivo
        try (ImageInputStream entrada =
                     ImageIO.createImageInputStream(arquivo)) {

            if (entrada == null) {
                return "Não identificado";
            }

            Iterator<ImageReader> leitores =
                    ImageIO.getImageReaders(entrada);

            if (leitores.hasNext()) {
                ImageReader leitor = leitores.next();

                try {
                    return leitor.getFormatName().toUpperCase(Locale.ROOT);
                } finally {
                    leitor.dispose();
                }
            }

        } catch (IOException erro) {
            return "Não identificado";
        }

        return "Não identificado";
    }

    @Override
    public String toString()
    {
        return getResolucao();
    }
}
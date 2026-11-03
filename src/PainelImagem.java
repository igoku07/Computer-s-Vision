import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PainelImagem extends JPanel {

    private BufferedImage imagem;

    public PainelImagem() {
        setBackground(Color.WHITE);
        setMinimumSize(new Dimension(0, 0));
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagem == null) {
            g.setColor(Color.DARK_GRAY);
            g.drawString("Clique em Abrir para escolher uma imagem", 10, 25);
            return;
        }

        if (getWidth() <= 0 || getHeight() <= 0) {
            return;
        }

        // Calcula a proporção para caber no painel
        double escalaX = (double) getWidth() / imagem.getWidth();
        double escalaY = (double) getHeight() / imagem.getHeight();
        double escala = Math.min(escalaX, escalaY);

        int largura = Math.max(1, (int) (imagem.getWidth() * escala));
        int altura = Math.max(1, (int) (imagem.getHeight() * escala));

        // Centraliza a imagem
        int x = (getWidth() - largura) / 2;
        int y = (getHeight() - altura) / 2;

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );

        g2.drawImage(imagem, x, y, largura, altura, null);
        g2.dispose();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class PainelImagem extends JPanel {

    private BufferedImage imagem;
    private final JLabel textoPixel;

    public PainelImagem(JLabel textoPixel) {
        this.textoPixel = textoPixel;

        setBackground(Color.WHITE);
        setMinimumSize(new Dimension(0, 0));

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mostrarPixel(e.getX(), e.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mostrarPixel(e.getX(), e.getY());
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                limparPixel();
            }
        });
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
        limparPixel();
        repaint();
    }

    private void limparPixel() {
        textoPixel.setText(
                "<html>Pixel<br>Passe o mouse sobre a imagem</html>"
        );
    }

    private Rectangle calcularAreaImagem() {
        if (imagem == null || getWidth() <= 0 || getHeight() <= 0) {
            return new Rectangle();
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

        return new Rectangle(x, y, largura, altura);
    }

    private void mostrarPixel(int mouseX, int mouseY) {
        Rectangle area = calcularAreaImagem();

        // Ignora as faixas que ficam fora da imagem
        if (imagem == null || !area.contains(mouseX, mouseY)) {
            limparPixel();
            return;
        }

        // Converte a posição no painel para a imagem original
        int x = (int) (
                (mouseX - area.x) * (double) imagem.getWidth() / area.width
        );

        int y = (int) (
                (mouseY - area.y) * (double) imagem.getHeight() / area.height
        );

        Color cor = new Color(imagem.getRGB(x, y), true);

        textoPixel.setText(
                "<html>Pixel<br><br>"
                        + "X: " + x + " | Y: " + y + "<br>"
                        + "R: " + cor.getRed() + "<br>"
                        + "G: " + cor.getGreen() + "<br>"
                        + "B: " + cor.getBlue()
                        + "</html>"
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagem == null) {
            g.setColor(Color.DARK_GRAY);
            g.drawString(
                    "Clique em Abrir para escolher uma imagem", 10, 25
            );
            return;
        }

        Rectangle area = calcularAreaImagem();

        if (area.isEmpty()) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );

        g2.drawImage(
                imagem,
                area.x,
                area.y,
                area.width,
                area.height,
                null
        );

        g2.dispose();
    }
}
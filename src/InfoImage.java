import java.awt.image.BufferedImage;

public class InfoImage
{
    private BufferedImage InfoImage;

    public InfoImage(BufferedImage Imagem)
    {
        InfoImage = Imagem;
    }

    public int getAltura()
    {
        int h = InfoImage.getHeight();
        return h;
    }

    public int getLargura()
    {
        int w = InfoImage.getWidth();
        return w;
    }

    public String getResolucao()
    {
        return "" + getLargura() + "X" + getAltura();
    }

    @Override
    public String toString()
    {
        return "" + getLargura() + "X" + getAltura();
    }
}

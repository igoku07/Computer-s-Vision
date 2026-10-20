import java.until.*;
import javax.imageio.ImageIO;

public class InfoImage
{
    private BufferedImage InfoImage;
    

    public InfoImage(BufferedImage Imagem)
    {
        InfoImage = Imagem;
    }

    public getAltura()
    {
        int h = InfoImage.getHeight();
        return h;
    }

    public getLargura()
    {
        int w = InfoImage.getWidth();
        return w; 

    }

    public String getResolucao()
    {
        return "" + getLargura() + "X" + getAltura();
    }

    public void toString()
    {
        System.out.println(""+ getLargura() + "X" + getAltura());

    }

}
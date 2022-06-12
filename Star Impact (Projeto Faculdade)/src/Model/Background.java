package Model;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

//BACKGROUND
public class Background 
{
    
    public BufferedImage imagem;
    
    //ATRIBUTOS DO BACKGROUND
    public int posX;
    public int posY;
    
    public int velX;
    
    //CONSTRUTOR DO BACKGROUND
    public Background()
    {
        //TENTA CARREGAR A IMAGEM DO BACKGROUND
        try
        {
            
            imagem = ImageIO.read(getClass().getResource("/Imagens/background.jpg"));
            
        }
        catch(Exception e)
        {
            
            e.printStackTrace();
            
        }
        
        posX = 0;
        posY = 0;
        velX = 0;
        
    }        
    
}

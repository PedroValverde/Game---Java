package Model;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Tiro 
{
    
    public Image imagem;
    
    public int posX;
    public int posY;
    
    public int width = 1024; 
    public int height;
    
    public int velX = 4;
    
    public boolean isVisivel;
    
    public Tiro(int posX,int posY)
    {
        
        this.posX = posX;
        this.posY = posY;
        
        ImageIcon referencia = new ImageIcon("src\\Imagens\\tirosimples.png");
        imagem = referencia.getImage();
        
        this.width = imagem.getWidth(null);
        this.height = imagem.getHeight(null);
        
        isVisivel= true;
        
    }  
    
    public void update()
    {
        
        this.posX += velX;
        if(this.posX > 1024)
        {
            
            isVisivel = false;
            
        }   
        
        
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getBounds() {
	return new Rectangle(posX, posY, width, height);
    }        
    
    
}

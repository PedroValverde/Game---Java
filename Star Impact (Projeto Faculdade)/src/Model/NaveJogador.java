package Model;

import Resource.Recursos;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/* Representa o personagem do jogo */
public class NaveJogador { 
        //SPRITE DA NAVE
	public BufferedImage sprite;
        
        //CADA FRAMES DO SPRITE
	public BufferedImage[] listaFrames;
        
        //ATRIBUTOS DO SPRITE
        int indiceAtual;
        int qtdLinhasFrame;
        int qtdColunasFrame; 
        int qtdFrames;
        public BufferedImage imagAtual;
        
        long startTime, endTime, deltaTime, tempoDecorrido, intervaloEntreFrames;
        
        //ATRIBUTOS DA NAVE JOGADOR
	public static int posX;
	public static int posY;
	public int raio;
	public int velX;
	public int velY;
        public int width;
        public int height;
        
        public boolean isVisivel;
        
        public static List<Tiro> tiros; 
        
        
        //CONSTRUTOR
	public NaveJogador() {
		//INICIALIZA ARRAY DE FRAMES E OUTROS ATRIBUTOS
		qtdLinhasFrame = 5;
		qtdColunasFrame = 2;
		qtdFrames = qtdLinhasFrame*qtdColunasFrame;
		listaFrames = new BufferedImage[qtdFrames];
		indiceAtual = 0;
		width = 100;
		height = 100;
		posX=30;
		posY=218;
		raio=50;
		velX=4;
		velY=4;
		tempoDecorrido = 0;
		intervaloEntreFrames = 20;
                
                tiros = new ArrayList<Tiro>();
                
		//FAZ O CARREGAMENTO DA IMAGEM
		try {
			sprite = ImageIO.read(getClass().getResource("/Imagens/navespritesheet.png"));
                        
                        //PERCORRE AS 5 LINHAS(0-4)
                        for(int i=0; i<qtdLinhasFrame; i++)
                        {
                            //PERCORRE AS 2 COLUNAS (0-1)
                            for(int j=0; j<qtdColunasFrame; j++)
                            {
                                
				int x1 = j*width;
				int y1 = i*height;
				int x2 = x1+width;
				int y2 = y1+height;      
                                listaFrames[i*qtdColunasFrame+j]=Recursos.getInstance().cortarImagem(x1, y1, x2, y2, sprite); 
                                
                            }    
                            
                        }    

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}         
        
	public void atira() 
        {
                
                this.tiros.add(new Tiro(posX + width, posY + height/3));
		
	}        
        
        //METODO PARA ATUALIZAR SPRITES A CADA FRAME
        public void mudarFrame()
        {
            
            startTime = System.currentTimeMillis();
            deltaTime = startTime - endTime;
            tempoDecorrido = tempoDecorrido + deltaTime;
            if(tempoDecorrido >= intervaloEntreFrames)
            {
                
                indiceAtual++;
                tempoDecorrido = 0;
                
            }    
            if(indiceAtual>=qtdFrames -1) indiceAtual = 0;
            imagAtual = listaFrames[indiceAtual];
            
            endTime = startTime;
            
        } 
        
        
    //getters e setters    
    public static List<Tiro> getTiros() 
    {
	return tiros;
    } 

    public static int getPosX() {
        return posX;
    }

    public static void setPosX(int posX) {
        NaveJogador.posX = posX;
    }

    public static int getPosY() {
        return posY;
    }

    public static void setPosY(int posY) {
        NaveJogador.posY = posY;
    }

    public int getRaio() {
        return raio;
    }

    public void setRaio(int raio) {
        this.raio = raio;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
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

    public boolean isIsVisivel() {
        return isVisivel;
    }

    public void setIsVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }
        
        
}

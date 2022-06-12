package Model;

import Game.Game;
import Principal.Principal;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;


public class Menu extends JPanel
{
    
    //ATRIBUTOS DE MENU
    int posX;
    int posY;
    int width;
    int height;
    private Image Imagem;
    
    //VARIAVEIS BOOLEANAS DE MENU REPRESENTANDO AS TECLAS DE NAVEGAÇÃO
    public static boolean cima,baixo,enter;
    
    //LISTA DE STRING CHAMADA ESCOLHA RECEBENDO NOVOJOGO E SAIR
    public static String[] escolha = {"novojogo","sair"};
    
    //VARIAVEIS DE CONTROLE DA LISTA
    
    //ESCOLHA ATUAL INICIA COMO 0
    public int escolhaatual = 0;
    
    //ESCOLHA ATUAL INICIA RECEBENDO O TAMANHO DA LISTA -1;
    public int escolhafinal = escolha.length -1;       

    //METODO CONSTRUTOR
    public Menu(int posX, int posY, int width, int height, Image Imagem) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.Imagem = Imagem;
    }    
    
    //METODO PARA ATUALIZAR FRAMES DO MENU
    public void update()
    {
        //SE CIMA VERDADE
        if(cima)
        {
            //SETE CIMA COMO FALSO
            cima = false;
            
            //DECREMENTE A ESCOLHA ATUAL
            escolhaatual--;
            
            //SE ESCOLHA ATUAL MENOR QUE 0
            if(escolhaatual < 0)
            {
                //ESCOLHA ATUAL RECEBE A ESCOLHA FINAL
                escolhaatual = escolhafinal;
                
            }   
            
        }  
        
        //SE BAIXO VERDADE
        if(baixo)
        {
            //SETE BAIXO COMO FALSO    
            baixo = false;
            
            //INCREMENTE A ESCOLHA ATUAL
            escolhaatual++;
                
            //SE ESCOLHA ATUAL MAIOR QUE ESCOLHA FINAL
            if(escolhaatual > escolhafinal)
            {
                //ESCOLHAATUAL RECEBE ZERO    
                escolhaatual = 0;
                    
            }    
                
        }   
           
        //SE ENTER VERDADE
        if(enter)
        {
            //SETE ENTER COMO FALSO    
            enter = false;
            
            //SE LISTA ESCOLHA PASSANDO POSICAO ESCOLHA ATUAL FOR IGUAL A NOVO JOGO    
            if(escolha[escolhaatual] == "novojogo")
            {
                //SETE O GAMESTATE COMO GAME    
                Game.gamestate = "game";
                    
            }
            //SENAO SE LISTA ESCOLHA PASSANDO POSICAO ESCOLHA ATUAL FOR IGUAL A SAIR
            else if(escolha[escolhaatual] == "sair")
            {
                //SAIA DO JOGO    
                System.exit(0);
                    
            }    
                
        }             
        
    }
    
    //DESENHA ELEMENTOS DO MENU
    public void paintComponent(Graphics g)
    {
        
        super.paintComponent(g);
        //DESENHA ELEMENTOS DO MENU
        g.setColor(new Color(0,20,0));
        g.fillRect(0, 0, (Principal.ALTURA_TELA*3)*2, (Principal.LARGURA_TELA*3)*2);
        
        g.setFont(new Font("arial",Font.BOLD,22));
        
        g.setColor(new Color(110,200,10));
        
        g.drawString("Novo Jogo", Principal.LARGURA_TELA/2-50, Principal.ALTURA_TELA/2+20);
        g.drawString("Sair", Principal.LARGURA_TELA/2-50, Principal.ALTURA_TELA/2+40);
        
        if(escolha[escolhaatual] == "novojogo")
        {
            
            g.drawString(">", Principal.LARGURA_TELA/2-60, Principal.ALTURA_TELA/2+20);
            
        }
        else if (escolha[escolhaatual] == "sair")
        {
            
            g.drawString(">", Principal.LARGURA_TELA/2-60, Principal.ALTURA_TELA/2+40);
            
        }    
        
        
    }        
    
}

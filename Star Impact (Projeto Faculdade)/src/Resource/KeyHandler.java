package Resource;

import Game.Game;
import Model.Menu;
import Model.NaveJogador;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener 
{

    public boolean CimaPressed, BaixoPressed, EsquerdaPressed, DireitaPressed; 
    
    Menu menu;
    
    Game game;
    
    NaveJogador navejogador = new NaveJogador();
    
    long ProximoTiro = 0;
    long RecoilTempo = 280;
    
    @Override
    public void keyTyped(KeyEvent e) 
    {
        
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        
        int codigo = e.getKeyCode();
        
        if(codigo == KeyEvent.VK_UP)
        {
            //SE TECLADO PRECIONADO PARA CIMA E O GAMESTATE FOR IGUAL A GAME
            if(game.gamestate == "game")
            {
                
                CimaPressed = true;
                
            }
            //SE NAO SE TECLADO PRECIONADO PARA CIMA E GAMESTATE FOR IGUAL A MENU
            else if(game.gamestate == "menu")
            {
                //MENU.CIMA = TRUE;
                menu.cima = true;
                
            }    
            
        }   
        
        if(codigo == KeyEvent.VK_DOWN)
        {
            //SE TECLADO PRESSIONADO PARA BAIXO E O GAMESTATE FOR IGUAL A GAME
            if(game.gamestate == "game")
            {
                
                BaixoPressed = true;
                
            }
            //SE NAO SE TECLADO PRECIONADO PARA CIMA E GAMESTATE FOR IGUAL A MENU
            else if(game.gamestate == "menu")
            {
                //MENU.BAIXO = TRUE;
                menu.baixo = true;
                
            } 
            
        }  

        if(codigo == KeyEvent.VK_LEFT)
        {
            
            EsquerdaPressed = true;
            
        }  

        if(codigo == KeyEvent.VK_RIGHT)
        {
            
            DireitaPressed = true;
            
        }          
        //SE TECLADO PRESSIONADO ENTER
        if(codigo == KeyEvent.VK_ENTER)
        {
            //SE O GAMESTATE FOR IGUAL A MENU
            if(game.gamestate == "menu")
            {
                
                menu.enter = true;
                
            }    
            
        } 
        
        if(codigo == KeyEvent.VK_ESCAPE)
        {
            
            game.gamestate = "menu";
            
        }    
        
        if(codigo == KeyEvent.VK_Z)
        {

            if(System.currentTimeMillis() > ProximoTiro)
            {
                
                ProximoTiro = System.currentTimeMillis() + RecoilTempo;
                navejogador.atira();
                
            }    
            
        }           
        
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        
        int codigo = e.getKeyCode();
        
        if(codigo == KeyEvent.VK_UP)
        {
            
            CimaPressed = false;
            
        }   
        
        if(codigo == KeyEvent.VK_DOWN)
        {
            
            BaixoPressed = false;
            
        }  

        if(codigo == KeyEvent.VK_LEFT)
        {
            
            EsquerdaPressed = false;
            
        }  

        if(codigo == KeyEvent.VK_RIGHT)
        {
            
            DireitaPressed = false;
            
        } 
  
    }
    
    
}

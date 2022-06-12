package Game;

import Model.Background;
import Model.Menu;
import Model.NaveInimiga;
import Model.NaveJogador;
import Model.Tiro;
import Principal.Principal;
import javax.swing.JPanel;
import Resource.KeyHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

public class Game extends JPanel
{

    //INSTANCIA OBJETO TECLADO DE KEYHANDLER
    KeyHandler teclado = new KeyHandler(); 
    
    //NAVE JOGADOR
    public NaveJogador navejogador;
    
    //NAVE INIMIGA
    private NaveInimiga[] naveinimiga;

    public int qtdInimigos;
    
    //BACKGROUND
    public Background backgrounda;
    public Background backgroundb;     
    
    //VARIAVEL BOOLEANA ISRODANDO INICIALIZADA TRUE;
    private boolean isRodando = true;    
    
    //VARIAVEL GAMESTATE INICIALIZADA COMO MENU
    public static String gamestate = "menu";
    
    //VARIAVEL MENU
    public static Menu menu;
    
    //VARIAVEL THREAD DO TIPO THREAD
    Thread thread;
    
    //VARIAVEL FPS INICIALIZADA COM 0
    int fps = 0;
    
    //CONSTRUTOR
    public Game()
    {
        //INSTANCIA NAVE DO JOGADOR 
        navejogador = new NaveJogador();
        
        qtdInimigos = 15;
        
        naveinimiga = new NaveInimiga[qtdInimigos];
        
        for (int i = 0; i < qtdInimigos; i++) {
            naveinimiga[i] = new NaveInimiga();
        }
        
        //INSTANCIA BACKGROUND A E BACKGROUND B 
        backgrounda = new Background();
        backgroundb = new Background();      
        
        //REPOSICIONA FUNDO B
        backgroundb.posX = backgroundb.posX + 1024;
        
        //SETA VELOCIDADE DO BACKGROUND
        backgrounda.velX = backgrounda.velX - 6;
        backgroundb.velX = backgroundb.velX - 6;        
        
        //ISNTANCIA NAVE INIMIGA
  
        //INSTANCIA MENU
        menu = new Menu(0, 0, 0, 0, null);
        
        addKeyListener(teclado);
        
        //RECEBE FOCO
        setFocusable(true);
        
        //THREAD INICIA GAMELOOP(RUNNING)
        new Thread(new Runnable()
        {@Override
         public void run()
         {
             long lastTime = System.nanoTime();
             double amountofTicks = 60.0;
             double ns = 1000000000 / amountofTicks;
             double delta = 0;
             int frames = 0;
             double timer = System.currentTimeMillis();
             while(isRodando)
             {
                 
                 long now = System.nanoTime();
                 delta +=(now - lastTime)/ns;
                 lastTime = now;
                 if(delta>= 1)
                 {
                     
                    handlerEvents();
                    update();
                    render();
                     
                    frames++;
                    delta--;
                 }    
                 
                 
                 if(System.currentTimeMillis()- timer>=1000)
                 {
                     
                     System.out.println("FPS: "+ frames);
                     fps = frames;
                     frames = 0;
                     timer+=1000;
                     
                 }    
             }    
             
         }        
        }).start();
        
        
    }        

    //METODO PARA INICIAR O JOGO
    public synchronized void start()
    {
        
        thread = new Thread((Runnable) this);
        isRodando = true;
        thread.start();
        stop();    
        
        
    }        
    
    //METODO PARA PARAR O JOGO
    public synchronized void stop()
    {
        
        isRodando = false;
        try
        {
            
            thread.join();
            
        }
        catch(InterruptedException e)
        {
            
            e.printStackTrace();
            
        }    
        
    }        
    
    //AÇÕES DO JOGADOR
    public void handlerEvents()
    {
        
        //se teclado cima apertado = true
        if(teclado.CimaPressed ==  true)
        {
                    
            navejogador.posY -= navejogador.velY;
                    
        }   
                
        //se teclado baixo apertado = true
        if(teclado.BaixoPressed ==  true)
        {
                    
            navejogador.posY += navejogador.velY;
                    
        }                  
                
        //se teclado esquerda apertado = true
        if(teclado.EsquerdaPressed ==  true)
        {
                    
            navejogador.posX -= navejogador.velX;

                    
        }  
                
        //se teclado direita apertado = true
        if(teclado.DireitaPressed ==  true)
        {
                    
            navejogador.posX += navejogador.velX;
                    
        }                                   
    } 
    
    //METODO PARA ATUALIZAR FRAMES
    public void update()
    {
        //SE O GAMESTATE FOR IGUAL A GAME
        if(gamestate == "game")
        {
            
            navejogador.mudarFrame();
        
            //TESTE FUNDO
            testeFundo();
        
            //TESTE COLISAO TELA
            testeColisaoTela();
            
            for (int j = 0; j < qtdInimigos; j++) {
                naveinimiga[j].posX += naveinimiga[j].velX;
                naveinimiga[j].posY += naveinimiga[j].velY;
            }            
            
            //MOVIMENTO ALEATORIO NAVE INIMIGA
            for (int x = 0; x < qtdInimigos; x++) {
                naveinimiga[x].mudarFrame();
            }
            
            testeColisaoInimigo();
            
            //INICIA TIRO
            iniciaTiros();
        
            //MOVIMENTO DO BACKGROUND
            backgrounda.posX += backgrounda.velX;
            backgroundb.posX += backgroundb.velX;   
            
  
            
            
        } 
        //SE GAMESTATE FOR IGUAL A MENU
        else if (gamestate == "menu")
        {
            //CHAME MENU UPDATE
            menu.update();
            
        }    
        
    }
    
    public void render()
    {
        
        repaint();
        
    }
    
    //METODOS         
    public void iniciaTiros()
    {
            //LISTA DE TIROS TIROS RECEBE NAVEJOGADOR GET TIROS
            List<Tiro> tiros = navejogador.getTiros();
            
            //PARA I MENOR QUE TAMANHO LISTA TIROS
            for(int i = 0; i < tiros.size(); i++)
            {
                //TIRO T PARSE PARA TIRO PEGUE O INDICE LISTA TIROS
                Tiro t = (Tiro)tiros.get(i); 
                
                //SE VISIVEL
                if(t.isVisivel)
                {
                    //TIRO ATUALIZE
                    t.update();
                    
                } 
                else
                {
                    //LISTA TIROS REMOVA E DECREMENTE I
                    tiros.remove(i);
                    i--;
                }    
                
            }            
        
    }              
    
    //REPOSICIONAR FUNDO
    public void testeFundo()
    {
        
        if((backgrounda.posX+1024) <= 0)//SE SAIU PELA ESQUERDA DA TELA
        {
            
            //REPOSICIONA BACKGROUNDA PARA TRAS DO BAKGROUNDB
            backgrounda.posX = backgroundb.posX + 1024;
            
        }	
        if((backgroundb.posX+1024) <= 0)//SE SAIU PELA ESQUERDA DA TELA
        {
            
            //REPOSICIONA BACKGROUNDA PARA TRAS DO BAKGROUNDB
            backgroundb.posX = backgrounda.posX + 1024;
            
        }           
        
    }        
    
    //IMPEDE NAVE DE FUGIR DA AREA DO JOGO
    public void testeColisaoTela()
    {
        
        if(navejogador.posX+(navejogador.raio*2) >= Principal.LARGURA_TELA)
        {
            
            navejogador.posX = navejogador.posX - navejogador.velX;
            
        } 
        
        if(navejogador.posX <= 0)
        {
            
            navejogador.posX = navejogador.posX + navejogador.velX;
            
        }   
        
        if(navejogador.posY+(navejogador.raio*2) >= Principal.ALTURA_TELA )
        {
            
            navejogador.posY = navejogador.posY - navejogador.velY;
            
        }
        
        if(navejogador.posY <= 0)
        {
            
            navejogador.posY = navejogador.posY + navejogador.velY;
            
        }   
    }    
    
    public void testeColisaoInimigo() 
    {

        for (int x2 = 0; x2 < qtdInimigos; x2++) {
            if (naveinimiga[x2].posX + naveinimiga[x2].width >= Principal.LARGURA_TELA || naveinimiga[x2].posX <= 0) {
                naveinimiga[x2].velX = (naveinimiga[x2].velX * -1);
                if (naveinimiga[x2].velY < 70) {
                    naveinimiga[x2].velY += 3;
                }
            }
            if (naveinimiga[x2].posY >= Principal.ALTURA_TELA) {
                naveinimiga[x2].posY = 0;
            }
        }

    }      
    
    //METODOS ESPECIAIS
    public void paintComponent(Graphics g)
    {
        
        super.paintComponent(g);
        
        //SE GAMESTATE FOR IGUAL A GAME
        if(gamestate == "game")
        {
            g.drawImage(backgrounda.imagem, backgrounda.posX, backgrounda.posY, null);
            g.drawImage(backgroundb.imagem, backgroundb.posX, backgroundb.posY, null);         
            g.drawImage(navejogador.imagAtual, navejogador.posX, navejogador.posY, null);      
               
            for (int x3 = 0; x3 < qtdInimigos; x3++) {
                g.drawImage(naveinimiga[x3].getSprite(), naveinimiga[x3].posX, naveinimiga[x3].posY, null);
            }           
            
            //LISTA TIRO TIROS RECEBE NAVEJOGADOR GET TIROS
            List<Tiro> tiros =  navejogador.getTiros();
            
            //PARA I MENOR QUE TAMANHO LISTA TIROS
            for(int i = 0; i < tiros.size(); i++)
            {
                //TIRO T PARSE TIRO PEGUE INDICE LISTA TIROS
		Tiro t = (Tiro)tiros.get(i); 
                //DESENHA
		g.drawImage(t.getImagem(), t.getPosX(), t.getPosY(), this);                
                
            }    
            
            //CONTADOR FPS
            g.setColor(new Color(110,200,10));
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("FPS: "+fps, 5, 20);
        }
        //SE NAO SE GAMESTATE FOR IGUAL A MENU
        else if(gamestate == "menu")
        {
            //CHAME MENU PAINTCOMPONENT
            menu.paintComponent(g);
            
        }    
        
    }        
}


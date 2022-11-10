package Claudio;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;

class JuegoSnakeJava extends JFrame implements KeyListener{

   
    private int windowWidth = 800;
    private int windowHeight = 600;
    private Snake snake;
    private Frutita frutita;
    private int Score;
    private long goal;
    private int tiempoDemora = 40;
   
   
    public static void main(String[] args) {
        new JuegoSnakeJava();
    }
   
    public JuegoSnakeJava() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(windowWidth, windowHeight);
        this.setResizable(false);
        this.setLocation(100, 100);
        this.setVisible(true);
       
        this.createBufferStrategy(2);
        this.addKeyListener(this);
       
        inicializoObjetos();
       
        while(true) {
            juego();
            sleep();
        }
        
    }
   
    private void inicializoObjetos() {
        snake = new Snake();
        snake.crecerColaSnake();
        frutita = new Frutita();
        frutita.nuevaFrutita();
        Score=0;
        
    }
   
    private void juego() {
        
        snake.muevoSnake();
        chequearColision();
        dibujoPantalla();
        
    }
   
    private void dibujoPantalla() {
        
        BufferStrategy bf = this.getBufferStrategy();
        Graphics g = null;
       
        try {
            g = bf.getDrawGraphics();
                       
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, windowWidth, windowHeight);
            
            frutita.dibujoFrutita(g);
            snake.dibujoSnake(g);
            muestroPuntos(g);
            
        } finally {
            g.dispose();
        }
               
        bf.show();
     
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void chequearColision(){
        if(snake.getLargo().get(0).equals(frutita.getFrutita())) {
            frutita.nuevaFrutita();
            snake.crecerColaSnake();
            Score+=10;
        }
        
        if(snake.getLargo().get(0).x < 0 || snake.getLargo().get(0).x > 39 ||
            snake.getLargo().get(0).y < 1 || snake.getLargo().get(0).y > 29) {
            inicializoObjetos();
        }
        
        
        for(int n = 1; n < snake.getLargo().size(); n++) {
            if(snake.getLargo().get(0).equals(snake.getLargo().get(n)) && snake.getLargo().size() > 2) {
                inicializoObjetos();
            }
        }
    }
    
    private void muestroPuntos(Graphics g){
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("Score: " + Score, 20, 50);
            
    }
    
    private void sleep(){
        goal = ( System.currentTimeMillis() + tiempoDemora );
        while(System.currentTimeMillis() < goal) {
        
        }
    }

    
    @Override
    public void keyPressed(KeyEvent e) {

        int tecla = e.getKeyCode();
   
        switch (tecla){
            case KeyEvent.VK_UP:
                snake.direccion("ARR");
                break;
            case KeyEvent.VK_DOWN:
                snake.direccion("ABA");
                break;
            case KeyEvent.VK_LEFT:
                snake.direccion("IZQ");
                break;
            case KeyEvent.VK_RIGHT:
                snake.direccion("DER");
                break;
            case KeyEvent.VK_E:
                System.exit(0);
        
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    
    }

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

}

class Snake {
    
    private ArrayList<Point> largo = new ArrayList<Point>();
    private int snakeX=0;
    private int snakeY=0;
    
    public Snake(){
        largo.add(new Point(20, 15));
    }
    
    public ArrayList<Point> getLargo()
    {
        return largo;
    }
    
    public void dibujoSnake(Graphics g){
        
        for(int n = 0; n < largo.size(); n++) {
            g.setColor(Color.BLACK);
            Point p = largo.get(n);
            g.fillRect(p.x*20, p.y*20, 20, 20);
        }
        
    }
    
    public void muevoSnake() {
        for(int n = largo.size()-1; n > 0; n--) {
            largo.get(n).setLocation(largo.get(n-1));
            }
        largo.get(0).x += snakeX;
        largo.get(0).y += snakeY;
    }
    
    public void crecerColaSnake () {
            largo.add(new Point());
        }
    
    public void direccion(String d)
    {
        switch(d){
            case "ARR":
                snakeX = 0;
                snakeY = -1;
                break;
            case "ABA":
                snakeX = 0;
                snakeY = 1;
                break;
            case "IZQ":
                snakeX = -1;
                snakeY = 0;
                break;
            case "DER":
                snakeX = 1;
                snakeY = 0;
                break;
            
        }
    }
}

class Frutita {

    private Random random;
    private Point frutita;
    
    public Frutita(){
        random = new Random();
        frutita = new Point();
    }

    public void nuevaFrutita() {
        frutita.x = random.nextInt(39);
        frutita.y = random.nextInt(28) + 1;
    }

    public void dibujoFrutita(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(frutita.x*20, frutita.y*20, 20, 20);
    }
    
    public Point getFrutita()
    {
        return frutita;
    }
    
}
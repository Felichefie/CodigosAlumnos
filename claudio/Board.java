import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;

class Board extends JPanel implements ActionListener, KeyListener{
    Snake snake;
    Food food;

    public static void main(String args[]){
        Board b = new Board();
        b.initBoard();
    }

    public Board(){
        setPreferredSize(new Dimension(Config.SIZE_WIN_W,Config.SIZE_WIN_H));
        setBackground(Color.GRAY);
    }

    public void initBoard(){
        snake = new Snake();
        food = new Food(Config.SIZE_SEG, Color.RED, new Point(10, 10));
        
        JFrame f=new JFrame("SNAKE");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(Config.SIZE_WIN_W,Config.SIZE_WIN_H);
        f.add(this);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.addKeyListener(this);
        f.add(this);
       
        Timer t=new Timer(200,this);
        t.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawSquares(g);
        snake.draw(g);
        food.draw(g);
    }

    public void drawSquares(Graphics g){
        g.setColor(Color.BLACK);
        for(int i=0;i<Config.SIZE_WIN_W;i+=Config.SIZE_SEG){
            g.drawLine(i,0,i,Config.SIZE_WIN_H);
        }
        for(int j=0;j<Config.SIZE_WIN_H;j+=Config.SIZE_SEG){
            g.drawLine(0,j,Config.SIZE_WIN_W,j);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        moveSnake();
        repaint();
    }

    private void moveSnake(){
        final int HEAD = 0;
        int xHead, yHead, x, y;
        ArrayList<Point> body = new ArrayList<Point>();
        body = snake.getBody();

        for(int i = body.size()-1; i>0; i--){
            x = body.get(i-1).getX();
            body.get(i).setX(x);
            y = body.get(i-1).getY();
            body.get(i).setY(y);
        }
        
        xHead = body.get(HEAD).getX();
        yHead = body.get(HEAD).getY();

        switch(snake.getDir()){
            case DOWN:
                body.get(HEAD).setY(++yHead);
                break;
            case LEFT:
                body.get(HEAD).setX(--xHead);
                break;
            case RIGHT:
                body.get(HEAD).setX(++xHead);
                break;
            case UP:
                body.get(HEAD).setY(--yHead);
                break;
            default:
                break;
        }
        if(snake.getBody().get(HEAD).compare(food.getPoint()) == true){
            food.randomNewFood();
            int xTail = body.get(body.size() - 1).getX();
            int yTail = body.get(body.size() - 1).getY();
            Point newSeg = new Point(xTail, yTail);
            snake.getBody().add(newSeg);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println( keyCode);
        switch(keyCode){
            case KeyEvent.VK_RIGHT :
                snake.setDir(Snake.Dir.RIGHT);
                break;
            case KeyEvent.VK_LEFT :
                snake.setDir(Snake.Dir.LEFT);
                break;
            case KeyEvent.VK_UP :
                snake.setDir(Snake.Dir.UP);
                break;
            case KeyEvent.VK_DOWN :
                snake.setDir(Snake.Dir.DOWN);
                break;
            default:
                System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}

class Snake{
    private Color colorBody, colorHead;
    private ArrayList<Point> body=new ArrayList<Point>();
    private int speed;
    enum Dir { LEFT, RIGHT, UP, DOWN };
    private Dir dir; 

    public Snake(){
        body.add(new Point(6,1));
        body.add(new Point(5,1));
        body.add(new Point(4,1));
        colorBody = Color.GREEN;
        colorHead = Color.RED;
        speed = 1;
        dir = Dir.RIGHT;
    }

    public ArrayList<Point> getBody(){
        return body;
    } 
    public Color getColorBody(){
        return colorBody;
    }
    public int getSpeed(){
        return speed;
    }
    public void draw(Graphics g){
        for(int i=0; i<body.size(); i++){
            Point p = body.get(i);
            if(i == 0){
                g.setColor(colorHead);
                g.fillRect(p.getX()*Config.SIZE_SEG,p.getY()*Config.SIZE_SEG,Config.SIZE_SEG,Config.SIZE_SEG);
            } else {
                g.setColor(colorBody);
                g.fillRect(p.getX()*Config.SIZE_SEG,p.getY()*Config.SIZE_SEG,Config.SIZE_SEG,Config.SIZE_SEG);
            }
        }
    }
    public Dir getDir() {
        return dir;
    }
    public void setDir(Dir dir) {
        this.dir = dir;
    }
}

class Food{
    private Color color;
    private int size;
    private Point p;

    public Food(int size, Color color, Point p){
        this.size=size;
        this.color=color;
        this.p=p;
    }
    public void setPoint(Point p){
        this.p=p;
    }
    public Point getPoint(){
        return p;
    }
    public void randomNewFood(){
        double x=Math.random()*Config.SIZE_X_SEG;
        double y=Math.random()*Config.SIZE_Y_SEG;
        p.setX((int)x);
        p.setY((int)y);
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(p.getX()*Config.SIZE_SEG,p.getY()*Config.SIZE_SEG,size,size);
    }
    public Color getColor() {
        return color;
    }
}

class Point{
    private int x;
    private int y;
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }

    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public boolean compare (Point p){
        if(p.x == x && p.y == y)
            return true;
        return false;
    }

}

class Config{
    public static final int SIZE_SEG=10;
    public static final int SIZE_WIN_H=500;
    public static final int SIZE_WIN_W=600;
    public static final int SIZE_X_SEG=SIZE_WIN_W/SIZE_SEG;
    public static final int SIZE_Y_SEG=SIZE_WIN_H/SIZE_SEG;
}
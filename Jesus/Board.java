import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.swing.Timer;
import javax.swing.WindowConstants;


class Board extends JPanel implements ActionListener{
    Snake snake;
    public static void main(String arg[]){
        Board b= new Board();
    }
    public Board(){
        setPreferredSize(new Dimension(Config.SIZE_WIN_W,Config.SIZE_WIN_H));
        setBackground(Color.LIGHT_GRAY);

        JFrame f=new JFrame("snake");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(Config.SIZE_WIN_W, Config.SIZE_WIN_H);
        f.add(this);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.add(this);
        Timer t=new Timer(1000,this);
        t.start();
        snake =new Snake();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(snake.getColor());
        for(Point p: snake.getBody()){
            g.fillOval(p.getX()*Config.SIZE_SEG,p.getY()*Config.SIZE_SEG,Config.SIZE_SEG,Config.SIZE_SEG);
        }
        for(int i=0;i<Config.SIZE_WIN_W;i+=Config.SIZE_SEG){
            g.drawLine(i, 0, i, Config.SIZE_WIN_H);
        }
        for(int j=0;j<Config.SIZE_WIN_H;j+=Config.SIZE_SEG){
            g.drawLine(0,j ,Config.SIZE_WIN_W, j);
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Point> body= new ArrayList<Point>();
        body = snake.getBody();
        int HEAD=0;
        for(int i=body.size()-1;i>0;i--){
            int x=body.get(i-1).getX();
            body.get(i).setX(x);
            int y=body.get(i-1).getY();
            body.get(i).setY(y);
        }
        int y= body.get(HEAD).getY();
        y+=1;
        body.get(HEAD).setY(y);
        repaint();
    }
}
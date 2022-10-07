import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.Graphics;

public class Espacio extends JPanel{
    ArrayList<Particula> particulas = new ArrayList<Particula>();
    public Espacio(){
        setPreferredSize(new Dimension(400,300));
        for(int i=0;i<100;i++){
            double x, y;
            x = Math.random()*399;
            y = Math.random()*299;
            particulas.add(new Particula((int)x, (int)y));
        }
    }
    public void paintComponent(Graphics g){
        Particula p;
        for(int i=0;i<100;i++){
            p = particulas.get(i);
            g.setColor(p.getColor());
            g.fillOval(p.getX(),p.getY(),2,2);
            System.out.println(p.getX());
        }
    }
    public static void main(String args[]){
        JFrame window = new JFrame("Particulas");
        window.setVisible(true);
        window.setSize(new Dimension(400,300));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        Espacio espacio = new Espacio();
        window.add(espacio);
    }
}

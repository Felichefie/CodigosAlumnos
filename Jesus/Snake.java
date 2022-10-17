import java.util.ArrayList;
import java.awt.Color;


class Snake{
    private Color color;
    private ArrayList<Point>body=new ArrayList<Point>();
    private int speed;
    public Snake(){
        body.add(new Point(5,2));
        body.add(new Point(4,2));
        body.add(new Point(3, 2));
        color=Color.GREEN;
        speed=1;
    }
    public ArrayList<Point> getBody(){
        return body;
    }
    public int getSpeed(){
        return speed;
    }
    public Color getColor(){
        return color;
    }
}
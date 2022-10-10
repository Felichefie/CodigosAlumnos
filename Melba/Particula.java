import java.awt.Color;
class Particula{
    private int x,y;
    private Color color;
    public Particula(int x, int y){
        this.x=x;
        this.y=y;
        color=Color.RED;
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x=x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y=y;
    }
    public Color getColor(){
        return color;
    }

    public void move (){
        // caclulate Rando between -1 an +1
        x += Math.random() > 0.5 ? 1 : -1;
        y += Math.random() > 0.5 ? 1 : -1;
    }
}
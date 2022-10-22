class Point{
    private int x,y;
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    /*public void setPoint(Point p){
        this = p;
    }*/
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Boolean areTheSame(Point p){
        if(x==p.getX() && y==p.getY()){
            return true;
        }else{
            return false;
        }
    }
}
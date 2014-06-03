public class Entity {
    
    protected int _hp;
    protected int _xcor;
    protected int _ycor;
    protected Ability _special;
   
    public int getX() { return _xcor; }
    public int getY() { return _ycor; }
    public int getHP() { return _hp; }
   
    public void setX(int x) { _xcor = x; }
    public void setY(int y) { _ycor = y; } 

}
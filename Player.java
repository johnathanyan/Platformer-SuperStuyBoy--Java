public class Player extends Entity {
     
    public Player(int x, int y) {
	_xcor = x;
	_ycor = y;
	_hp = 100;
	//_specialAttack = a;
    }
    
    public static void main(String[] args) {
	Player p = new Player(0,0);
	System.out.println(p.getX());
    }
}
import java.awt.*;
import java.util.Random;


public class Zombie extends Creature implements Movable, Aggressor {

    Random rand;

    public Zombie() {
        rand = new Random();
        _health = 1;
    }

    public void attack(Creature target) {
        if(target instanceof Animal) {
            target.takeDamage(10);
            _health++;
        }

    }


    public Boolean isAlive() {

        return _health > 0;
    }

    public Shape getShape() {
        return Shape.Square;
    }


    public Color getColor() {

        return new Color(0, 0, 255);
    }


    public void move() {
        switch(rand.nextInt(2)) {
            case 0:
                _location.x++;
                break;
            case 1:
                _location.x--;
                break;
            default:
                break;
        }
    }
}

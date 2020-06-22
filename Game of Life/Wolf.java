import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wolf extends Creature implements Movable, Aggressor, Aware, Spawner{
    Random rand;
    Direction direction;
    Creature creatures;

    public Wolf() {
        Random rand = new Random();
        int randomDirection = rand.nextInt(4);
        if(randomDirection == 0) {
            direction = Direction.Up;
        }
        else if(randomDirection == 1) {
            direction = Direction.Down;
        }
        else if(randomDirection == 2) {
            direction = Direction.Left;
        }
        else{
            direction = Direction.Right;
        }

        _health = 1;
    }


    public void attack(Creature target) {
        if(target instanceof Animal) {
            target.takeDamage(5);
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

        return new Color(117, 117, 125);
    }


    public void move() {

        if(direction == Direction.Up){
            _location.y--;
        }
        else if(direction == Direction.Right){
            _location.x++;
        }
        else if(direction == Direction.Down){
            _location.y++;
        }
        else if(direction == Direction.Left){
            _location.x--;
        }


    }


    public void senseNeighbors(Creature above, Creature below, Creature left, Creature right) {

        if (direction == Direction.Up) {
            if (above instanceof Animal) {
                direction = Direction.Up;
            }
            else if (right instanceof Animal) {
                direction = Direction.Right;
            }
            else if (below instanceof Animal) {
                direction = Direction.Down;
            }
            else if (left instanceof Animal) {
                direction = Direction.Left;
            }
        }
        else if (direction == Direction.Right) {
            if (right instanceof Animal) {
                direction = Direction.Right;
            }
            else if (below instanceof Animal) {
                direction = Direction.Down;
            }
            else if (left instanceof Animal) {
                direction = Direction.Left;
            }
            else if (above instanceof Animal) {
                direction = Direction.Up;
            }
        }
        else if (direction == Direction.Down) {
            if (right instanceof Animal) {
                direction = Direction.Down;
            }
            else if (below instanceof Animal) {
                direction = Direction.Left;
            }
            else if (left instanceof Animal) {
                direction = Direction.Up;
            }
            else if (above instanceof Animal) {
                direction = Direction.Right;
            }
        }
        else if (direction == Direction.Left) {
            if (right instanceof Animal) {
                direction = Direction.Left;
            }
            else if (below instanceof Animal) {
                direction = Direction.Up;
            }
            else if (left instanceof Animal) {
                direction = Direction.Right;
            }
            else if (above instanceof Animal) {
                direction = Direction.Down;
            }
        }
    }



    public Creature spawnNewCreature() {
        return null;
    }
}

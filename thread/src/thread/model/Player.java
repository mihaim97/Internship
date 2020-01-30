package thread.model;

public class Player {

    private int health;

    public Player(int health){
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void takeDmg(){
        health--;
        System.out.println(Thread.currentThread().getName() + " " + health);
    }

}

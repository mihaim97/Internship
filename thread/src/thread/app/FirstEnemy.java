package thread.app;

import thread.model.Player;

public class FirstEnemy implements Runnable {

    private Player player;
    private int dmgCount;
    private String enemyName;

    public FirstEnemy(String enemyName, Player player){
        this.player = player;
        this.enemyName = enemyName;
    }

    @Override
    public void run() {
        while (player.getHealth() > 0) {
            player.takeDmg();
            dmgCount++;
        }
        if(player.getHealth() == 0)
            System.out.println(Thread.currentThread().getName() + " " + dmgCount);
    }
}

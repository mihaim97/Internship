package thread.app;

import thread.model.Player;

public class AppMain {

    public static void main(String[] args) {
        Player player1 = new Player(100);
        FirstEnemy enemy1Task = new FirstEnemy("Enemy1", player1);
        FirstEnemy enemy2Task = new FirstEnemy("Enemy1", player1);
        Thread thread1 = new Thread(enemy1Task,"Thread 1");
        Thread thread2 = new Thread(enemy2Task, "Thread 2");

        thread1.start();
        thread2.start();



    }
}

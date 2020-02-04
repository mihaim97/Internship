package thread.app;

import jdk.management.jfr.RecordingInfo;
import thread.api.ApiCall;
import thread.api.BestEmployee;
import thread.api.Employee;
import thread.api.EmployeeInfo;
import thread.model.Mail;
import thread.model.Player;

import java.util.concurrent.*;

public class AppMain {

    public static void main(String[] args) {
      /*  Player player1 = new Player(100);
        FirstEnemy enemy1Task = new FirstEnemy("Enemy1", player1);
        FirstEnemy enemy2Task = new FirstEnemy("Enemy1", player1);
        Thread thread1 = new Thread(enemy1Task,"Thread 1");
        Thread thread2 = new Thread(enemy2Task, "Thread 2");

        thread1.start();
        thread2.start();
        */

      /*  Mail mail = new Mail("Message");
        Thread thread1 = new Thread(new Sender(mail));
        Thread thread2 = new Thread(new Receiver(mail));

        thread1.start();
        thread2.start();*/

        Employee employee = new BestEmployee();
        Callable<Employee> apiCall = new ApiCall(employee);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Employee> employeeResult = executor.submit(apiCall);

        Thread thread = new Thread(new EmployeeInfo(employee));
        thread.start();

        try{
            employeeResult.get();
        }catch(InterruptedException e ){
            e.printStackTrace();
        }catch(ExecutionException e){
            e.printStackTrace();
        }

        executor.shutdown();


    }
}

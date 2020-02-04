package thread.api;

import java.util.concurrent.Callable;

public class ApiCall implements Callable<Employee> {

    private Employee employee;

    public ApiCall(){}

    public ApiCall(Employee emp){this.employee = emp;}

    @Override
    public Employee call() throws Exception {
        synchronized (employee){
            System.out.println("Waiting for Result");
            Thread.sleep(3000);
            employee.setInfo("Employee 1");
            return employee;
        }
    }
}

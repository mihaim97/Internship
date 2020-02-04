package thread.api;

public class EmployeeInfo implements Runnable {

    private Employee employee;

    public EmployeeInfo(){}

    public EmployeeInfo(Employee emp){ this.employee = emp;}

    @Override
    public void run() {
        synchronized (employee){
            employee.info();
        }
    }
}

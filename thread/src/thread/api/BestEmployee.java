package thread.api;

public class BestEmployee implements Employee {

    private String name;

    @Override
    public void info() {
        System.out.println(name);
    }

    @Override
    public void setInfo(String name) {
        this.name = name;
    }
}

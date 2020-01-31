package thread.app;

import thread.model.Mail;

public class Sender implements Runnable {

    private Mail mail;

    public Sender (Mail mail){
        this.mail = mail;
    }

    @Override
    public void run() {
        synchronized (mail){
            mail.send();
            try {
                mail.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

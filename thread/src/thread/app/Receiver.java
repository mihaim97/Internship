package thread.app;

import thread.model.Mail;

public class Receiver implements Runnable{

    private Mail mail;

    public Receiver(Mail mail){
        this.mail = mail;
    }

    @Override
    public void run() {
        synchronized (mail){
            mail.notify();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mail.receive();
        }
    }
}

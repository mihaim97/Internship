package thread.model;

public class Mail {

    private String msgToSend;

    public Mail(String msg){
        this.msgToSend = msg;
    }

    public void send(){
        if(msgToSend == null) return;
        System.out.println("Sending :" + msgToSend);
    }

    public void receive(){
        System.out.println("Receiving :" + msgToSend);
    }

}

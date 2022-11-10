import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

class Client{
    String ip;
    int port;
    DataInputStream dis;
    DataOutputStream dos;
    Socket socket;
    Thread listenerServer;
    Thread sending;
    Scanner scanner;

    public Client(String ip,int port){
        this.ip=ip;
        this.port=port;
        scanner=new Scanner(System.in);
    }
    public void initSocket(){
        try {
            socket=new Socket(ip,port);
            dis=new DataInputStream(socket.getInputStream());
            dos=new DataOutputStream(socket.getOutputStream());
            listenerServer=new Thread(new Runnable(){
                @Override
                public void run(){
                    String msg;
                    try {
                        msg= dis.readUTF();
                        System.out.println(msg);
                    } catch (IOException e) {
                        
                        e.printStackTrace();
                    }
                    
                }
            });
            sending=new Thread(new Runnable(){
                @Override
                public void run(){
                    String msg;
                    msg= scanner.nextLine();
                    System.out.println(msg);
                    try {
                        dos.writeUTF(msg);
                    } catch (IOException e) {
                        
                        e.printStackTrace();
                    }
                }
            });

        } catch (UnknownHostException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    
    
}



package socketstuff;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread implements Runnable{
    private ServerSocket serv; 
    private PrintWriter out; 
    private BufferedReader in;
    private Socket client; 
    private ArrayList <ClientThread> clients = new ArrayList();
     
    public ClientThread(ServerSocket orgserv, Socket client, ArrayList<ClientThread> clients){
        
        try{
            this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.out = new PrintWriter(client.getOutputStream(), true);
            this.client = client; 
            this.serv = orgserv;
            this.clients = clients;
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    @Override
    public void run(){ 
        try{
            while (true){
                String fromClient = in.readLine(); 
                if(fromClient != null){
                    System.out.println("Sending stuff...");
                    if(clients.isEmpty()){
                        //System.out.println("Sending message");
                        out.println("[!] Nobody is currently in the chatroom [!]");
                    }
                    else{ 
                    for(ClientThread e: clients){
                        //System.out.println("Sending message...");
                        if(e.client != this.client){
                            e.out.println(fromClient+"\n");
                        }
                    }
                    }
                }
                else{
                    client.close();
                    break;
                }
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    public void update(ArrayList<ClientThread> clients){
        this.clients = clients;
    }
}


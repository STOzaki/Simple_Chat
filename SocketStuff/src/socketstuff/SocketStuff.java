/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketstuff;

import java.net.Socket;
import java.net.ServerSocket; 
import java.io.IOException; 
import java.util.ArrayList;


    
public class SocketStuff {
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(2000); 
        ArrayList <ClientThread> clients = new ArrayList(); 
//        ArrayList <Thread> threads = new ArrayList(); 
        while (true){
            Socket client = server.accept();
            ClientThread cli = new ClientThread(server, client, clients);
            clients.add(cli);
            Thread thr = new Thread(cli);
            thr.start();
            for(ClientThread e: clients){
                e.update(clients);
            }
        }
  
    }   
}

package GameServer;


import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class gameThread implements Runnable {

    private Socket player1, player2;
    private PrintWriter sender1, sender2;
    private BufferedReader reader1, reader2;
    private TextArea mainArea;

    public gameThread(Socket player1, Socket player2, TextArea mainArea) {
        this.player1 = player1;
        this.player2 = player2;
        this.mainArea = mainArea;
    }

    @Override
    public void run() {
        try {
            sender1 = new PrintWriter(player1.getOutputStream());
            sender2 = new PrintWriter(player2.getOutputStream());
            reader1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
            reader2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
            sender1.println("DONE");
            sender1.flush();
            sender2.println("DONE");
            sender2.flush();
            String command1,command2;
            command1 = reader1.readLine();
            command2 = reader2.readLine();
            System.out.println(command1+" "+command2);
            createServer.setPlayer1(command1);
            createServer.setPlayer2(command2);
            sender1.println(command2);
            sender2.println(command1);
            sender1.flush();
            sender2.flush();

            showInServer(" ("+new SimpleDateFormat("HH:mm:ss").format(new Date())+"): " +createServer.getPlayer1()+" vs "+createServer.getPlayer2()+" started");

            while(true){
                if((command1 = reader1.readLine())!=null){
                    manipulateAndSend(command1,sender2);
                }
                if((command2 = reader2.readLine())!=null){
                    manipulateAndSend(command2,sender1);
                }
                if(reader1.readLine().equals("Finished") || reader2.readLine().equals("Finished")){
                    break;
                }
            }
        } catch (Exception e) {
            showInServer("Error Creating Game Room!");
            e.printStackTrace();
        }
    }

    private void showInServer(String text){
        Platform.runLater(()->mainArea.appendText(text+"\n"));
    }

    private void manipulateAndSend(String coms, PrintWriter to){
        String[] command = coms.split(",");
        if(command[1].equals("1")){command[1] = "2";}
        else{command[1] = "2";}
        to.println(command[0]+","+command[1]);
        to.flush();
    }
}


import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThread extends Thread{
    Socket sock;
    Vector<ServerThread> clients;
    InputStream ip;
    DataInputStream dis;
    DataOutputStream dos;
    OutputStream op ;
    BankAccount bankAccount;
    public ServerThread(Socket client_socket, Vector<ServerThread> clients, BankAccount bankAccount){
        try{
        this.sock=client_socket;
		ip=sock.getInputStream();
        dis= new DataInputStream(ip);
        op=sock.getOutputStream();
    	dos= new DataOutputStream(op);
        this.clients=clients;
        clients.add(this);
        this.bankAccount=bankAccount;
        this.start();
        }
        catch(IOException e){
            System.out.println("Connection: "+e.getMessage());
        }
    }

    public ClientAccount find(int account_Number){
        for(ClientAccount account: bankAccount.getAccounts()){
            if(account.getAccountNumber()==account_Number){
                return account;
            }
        }
        return null;
    }

    public void run(){
        synchronized(bankAccount){
        try{
            
            String value1= new String(dis.readUTF());
            String value2= new String(dis.readUTF());
            String value3= new String(dis.readUTF());
            
            int account_Number=Integer.parseInt(value1);
            String operation=value2;
            double amount=Double.parseDouble(value3);
            String message="";
            if(operation.equals("DEPOSIT")){
                message=bankAccount.deposit(account_Number, amount);
                dos.writeUTF(message);
            }
            else if(operation.equals("WITHDRAW")){
                message=bankAccount.withdraw(account_Number, amount);
                dos.writeUTF(message);
            }
            if(message.equals("transaction complete")){
                ClientAccount account=find(account_Number);
                dos.writeUTF(Integer.toString(account_Number));
                dos.writeUTF(Integer.toString(account.getNumberOfDeposits()));
                dos.writeUTF(Integer.toString(account.getNumberOfWithdraws()));
                dos.writeUTF(Double.toString(account.getCurrentBalance()));


            }

            ip.close();
            dis.close();
            op.close();
            dos.close();
            sock.close();

        } 
        catch(EOFException e){
        System.out.println("EOF: "+e.getMessage()); 
	    }
	    catch(IOException e){
	    	System.out.println("readLine: "+e.getMessage());
        }
    }
}
}

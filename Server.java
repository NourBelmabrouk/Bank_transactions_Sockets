import java.io.*;
import java.net.*;
import java.util.*;
public class Server {
    private static BankAccount bankAccount=new BankAccount();
    public static BankAccount retrieveBankAccount() {
        try {

            FileInputStream fileIn = new FileInputStream("BankAccount.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            ClientAccount clientAccount = (ClientAccount) objectIn.readObject();
            bankAccount.getAccounts().add(clientAccount);
            clientAccount = (ClientAccount) objectIn.readObject();
            while (clientAccount != null) {
                clientAccount = (ClientAccount) objectIn.readObject();
                bankAccount.getAccounts().add(clientAccount);

            }
            objectIn.close();

        }
        catch (FileNotFoundException ex) {
            bankAccount=new BankAccount();
        } 
        catch(EOFException e){
            return bankAccount;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return bankAccount;
    }

    public static void main(String[] args) {
        try {
            bankAccount = retrieveBankAccount();
		ServerSocket  s1= new ServerSocket(9000);
		Vector<ServerThread> clients=new Vector<ServerThread>();
		while(true){
		    System.out.println("Server is open");
			Socket client_Socket= s1.accept();
			System.out.println("A client has reached out");
            new ServerThread(client_Socket,clients,bankAccount);
		   }
	    } catch(IOException e){
	    	System.out.println("s1: "+e.getMessage());
	    }
	}
}
		
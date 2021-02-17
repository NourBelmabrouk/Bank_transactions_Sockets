import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket s1=new Socket("localhost",9000);
        DataOutputStream dos;
        OutputStream op ;
        InputStream ip;
        DataInputStream dis;
        BufferedReader BufferedReader = new BufferedReader(new InputStreamReader(System.in));
        
        
        //sending the 3 values
        try{
            op=s1.getOutputStream();
    	    dos= new DataOutputStream(op);
            System.out.println("Enter your account number: ");
            String accountNumber = BufferedReader.readLine();
            dos.writeUTF(accountNumber);
            System.out.println("Enter the operation: ");
            String operation  = BufferedReader.readLine();
            dos.writeUTF(operation);
            System.out.println("Enter the amount: ");
            String  amount  = BufferedReader.readLine();
            dos.writeUTF(amount);
            
            
		    ip=s1.getInputStream();
		    dis= new DataInputStream(ip);

            String value1= new String(dis.readUTF());
            System.out.println(value1);
            if(value1.equals("transaction complete")){
            String value2= new String(dis.readUTF());
            String value3= new String(dis.readUTF());
            String value4= new String(dis.readUTF());
            String value5= new String(dis.readUTF());

            System.out.println("Your new account information:");
            System.out.println("Account number: "+value2 );
            System.out.println("number of deposits: "+value3);
            System.out.println("number of withdraws: "+value4);
            System.out.println("Balance: "+value5);
            }
            op.close();
            dos.close();
            ip.close();
            dis.close();
            s1.close();

            


        }
        catch(IOException e){
            System.out.println("Connection: "+e.getMessage());
        }
    }
    
}

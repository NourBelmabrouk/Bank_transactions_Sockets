import java.io.*;
import java.util.*;




public class BankAccount {
     private static List<ClientAccount> accounts=new ArrayList<ClientAccount>();

     public BankAccount(){

     }

    public BankAccount(List<ClientAccount> accounts){
        BankAccount.accounts = accounts;
    }

    public void setAccounts(List<ClientAccount> accounts){
        BankAccount.accounts=accounts;
    }

    public List<ClientAccount> getAccounts(){
        return accounts;
    }

    public String deposit(int account_Number, double amount){
        int i=0;
        for(ClientAccount account: accounts){
            if(account.getAccountNumber()==account_Number){
                System.out.println("account found");
                i=1;
                account.setNumberOfDeposits(account.getNumberOfDeposits()+1);
                account.setCurrentBalance(account.getCurrentBalance()+amount);
                break;
            }
        }
        if(i==0){
            ClientAccount desired_account=new ClientAccount(account_Number, 1, 0, amount);
            accounts.add(desired_account);
        }
        
        updateFile();
        return "transaction complete";
    }

    public String withdraw(int account_Number, double amount){
        ClientAccount desired_account=null;
        for(ClientAccount account: accounts){
            if(account.getAccountNumber()==account_Number){
                desired_account=account;
                break;
            }
        }
        if(desired_account==null){
            return "Your account does not exist";
        }
        else if(desired_account.getCurrentBalance()<amount){
            return "you don't have enough credit to do this transaction";
        }
        else{
            desired_account.setNumberOfWithdraws(desired_account.getNumberOfWithdraws()+1);
            desired_account.setCurrentBalance(desired_account.getCurrentBalance()-amount);
            updateFile();
            
        return "transaction complete";
        }

    }

    public void updateFile(){
        File f = new File("BankAccount.ser");
             FileOutputStream of;
            try {
                of = new FileOutputStream(f);
                ObjectOutputStream objf = new ObjectOutputStream(of);
                for(ClientAccount account: accounts){
                 objf.writeObject(account);
                }
                 objf.close();
                    System.out.println("update finished");
                    
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }
    }
}

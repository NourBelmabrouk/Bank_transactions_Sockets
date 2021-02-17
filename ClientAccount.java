import java.io.Serializable;

public class ClientAccount implements Serializable{

    private int account_Number;
    private int number_of_Deposits;
    private int number_of_Withdraws;
    private double current_Balance;

    public ClientAccount(int account_Number,int number_of_Deposits, int number_of_Withdraws, double current_Balance){
        this.account_Number=account_Number;
        this.number_of_Deposits=number_of_Deposits;
        this.number_of_Withdraws=number_of_Withdraws;
        this.current_Balance=current_Balance;
    }
    
    public int getAccountNumber() {
        return account_Number;
    }

    public void setAccountNumber(int accountNumber) {
        account_Number = accountNumber;
    }

    public int getNumberOfDeposits() {
        return number_of_Deposits;
    }

    public void setNumberOfDeposits(int numberOfDeposits) {
        number_of_Deposits = numberOfDeposits;
    }

    public int getNumberOfWithdraws() {
        return number_of_Withdraws;
    }

    public void setNumberOfWithdraws(int numberOfWithdraws) {
        number_of_Withdraws = numberOfWithdraws;
    }

    public Double getCurrentBalance() {
        return current_Balance;
    }

    public void setCurrentBalance(Double currentBalance) {
        current_Balance = currentBalance;
    }

}



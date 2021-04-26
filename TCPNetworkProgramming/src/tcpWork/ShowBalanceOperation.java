package tcpWork;

public class ShowBalanceOperation extends CardOperation {
    private String serNum;
    private double balance;

    public ShowBalanceOperation() {

    }
    public ShowBalanceOperation(String serNum) {
        this.serNum = serNum;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

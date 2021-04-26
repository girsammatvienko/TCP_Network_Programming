package tcpWork;

public class PayMoneyOperation extends CardOperation{
    private String serNum;
    private double money;
    public PayMoneyOperation() {

    }
    public PayMoneyOperation(String serNum, double money) {
        this.serNum = serNum;
        this.money = money;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}

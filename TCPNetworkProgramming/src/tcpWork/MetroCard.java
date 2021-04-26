package tcpWork;

import java.io.Serializable;

public class MetroCard implements Serializable {
    private String serNum;
    private User usr;
    private String college;
    private double balance;

    public MetroCard() {
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "No: " + serNum + "\nUser: " + usr +
                "\nCollege: " + college +
                "\nBalance: " + balance;
    }
}

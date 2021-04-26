package tcpWork;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private boolean work = true;
    private MetroCardBank bnk = null;
    private Socket s = null;

    public ClientHandler(MetroCardBank bnk, Socket s) {
        this.bnk = bnk;
        this.s = s;
        this.work = true;
        try {
            this.is = new ObjectInputStream(s.getInputStream());
            this.os = new ObjectOutputStream(s.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void run() {
        synchronized (bnk) {
            System.out.println("Client Handler Started for: " + s);
            while (work) {
                Object obj;
                try {
                    obj = is.readObject();
                    processOperation(obj);
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Error: " + e);
                }
            }
            try {
                System.out.println("Client Handler Stopped for: " + s);
                s.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }

    private void processOperation(Object obj) throws IOException, ClassNotFoundException {
        if (obj instanceof StopOperation) {
            finish();
        } else if (obj instanceof AddMetroCardOperation) {
            addCard(obj);
        } else if (obj instanceof AddMoneyOperation) {
            addMoney(obj);
        } else if (obj instanceof PayMoneyOperation) {
            payMoney(obj);
        }
        else if (obj instanceof RemoveCardOperation) {
            removeCard(obj);
        } else if (obj instanceof ShowBalanceOperation) {
            showBalance(obj);
        } else {
            error();
        }
    }

    private void showBalance(Object obj) throws IOException {
    ShowBalanceOperation op = (ShowBalanceOperation) obj;
    List<MetroCard> storage = bnk.getStore();
        os.writeObject(storage.get(bnk.findMetroCard(op.getSerNum())).getBalance());
        os.flush();
    }

    private void payMoney(Object obj) throws IOException {
        PayMoneyOperation op = (PayMoneyOperation) obj;
        boolean res = bnk.getMoney(op.getSerNum(), 8);
        os.writeObject(res? "Money was successfully debited":"Debiting error");
        os.flush();
    }

    private void addMoney(Object obj)
            throws IOException {
        AddMoneyOperation op = (AddMoneyOperation) obj;
        boolean res = bnk.addMoney(op.getSerNum(), op.getMoney());
        if (res) {
            os.writeObject("Money was added");
        } else {
            os.writeObject("Cannot change the balance");
        }
        os.flush();
    }

    private void error() throws IOException {
        os.writeObject("Bad Operation");
        os.flush();
    }

    private void finish() throws IOException {
        work = false;
        os.writeObject("Finish Work " + s);
        os.flush();
    }

    private void addCard(Object obj)
            throws IOException, ClassNotFoundException {
        bnk.addCard(((AddMetroCardOperation) obj).getCrd());
        os.writeObject("Card Added");
        os.flush();
    }


    private void removeCard(Object obj) throws IOException, ClassNotFoundException {
            RemoveCardOperation op = (RemoveCardOperation) obj;
            boolean res = bnk.removeCard(op.getSerNum());
            if (res) {
                os.writeObject("Metro card has been successfully removed: " + op.getSerNum());
            } else {
                os.writeObject("Cannot remove card" + op.getSerNum());
            }
            os.flush();
        }



}

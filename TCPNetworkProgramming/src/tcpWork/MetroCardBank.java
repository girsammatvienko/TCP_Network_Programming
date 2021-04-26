package tcpWork;

import java.util.ArrayList;
import java.util.List;

public class MetroCardBank {
    List<MetroCard> store;

    public MetroCardBank() {
        store = new ArrayList<>();
    }

    public int findMetroCard(String serNum) {
        for(int i = 0;i < store.size();i++) {
            if(store.get(i).getSerNum().equals(serNum)) return i;
        }
        return -1;
    }

    public int numCards() { return store.size();}

    public void addCard(MetroCard newCard) {
        store.add(newCard);
    }

    public boolean removeCard(String serNum) {
        return store.remove(store.remove(findMetroCard(serNum)));
    }

    public boolean addMoney(String serNum, double money) {
        if(findMetroCard(serNum) == -1) return false;
        MetroCard card = store.get(findMetroCard(serNum));
        card.setBalance(card.getBalance() + money);
        return true;
    }

    public boolean getMoney(String serNum, double money) {
        if(findMetroCard(serNum) == -1) return false;
        MetroCard card = store.get(findMetroCard(serNum));
        card.setBalance(card.getBalance() - money);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder("List of MetroCards:");
        for (MetroCard c : store) {
            buf.append("\n\n" + c);
        }
        return buf.toString();
    }

    public List<MetroCard> getStore() {
        return store;
    }
}

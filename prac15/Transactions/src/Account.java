public class Account
{
    private long money;
    private String accNumber;
    private boolean locked = false;

    public Account(long money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
    }

    public void lock() {
        this.locked = true;
    }

    public void unlock() {
        this.locked = false;
    }

    public boolean isLocked() {
        return locked;
    }

    public void addMoney(long money) {
        this.money += money;
    }

    public void removeMoney(long money) {
        this.money -= money;
    }

    public Long getMoney() {
        return money;
    }
}

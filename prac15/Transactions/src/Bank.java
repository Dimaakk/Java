import java.util.HashMap;
import java.util.Random;

public class Bank
{
    private HashMap<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<String, Account>();
        accounts.put("1", new Account(10000, "1"));
        accounts.put("2", new Account(20000, "2"));
        accounts.put("3", new Account(30000, "3"));
    }

    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum)
            throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        if(!accounts.get(fromAccountNum).isLocked() && !accounts.get(toAccountNum).isLocked()) {
            if(amount>50000)
                if(isFraud(fromAccountNum, toAccountNum)) {
                    accounts.get(fromAccountNum).lock();
                    accounts.get(toAccountNum).lock();
                    return;
                }
            accounts.get(fromAccountNum).removeMoney(amount);
            accounts.get(toAccountNum).addMoney(amount);
        }
        else {
            System.out.println("Счет(а) заблокированы");
        }

    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public synchronized long getBalance(String accountNum)
    {
        return accounts.get(accountNum).getMoney();
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Thread thread1 = new Thread(() -> {
            try {
//                Thread.sleep(1000);
                bank.transfer("1", "2", 1000);
                System.out.println("Balance of account with number 1: " + Long.toString(bank.getBalance("1")) + "\n" + "Balance of account with number 2: " + Long.toString(bank.getBalance("2")) + "\n////////////////////////////////");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
//                Thread.sleep(1000);
                bank.transfer("2", "3", 1000);
                System.out.println("Balance of account with number 2: " + Long.toString(bank.getBalance("2")) + "\n" + "Balance of account with number 3: " + Long.toString(bank.getBalance("3")) + "\n////////////////////////////////");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
//                Thread.sleep(1000);
                bank.transfer("3", "1", 1000);
                System.out.println("Balance of account with number 3: " + Long.toString(bank.getBalance("3")) + "\n" + "Balance of account with number 1: " + Long.toString(bank.getBalance("1")) + "\n////////////////////////////////");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
    }
}

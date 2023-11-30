public class Main {
    public static void main(String[] args) {
        final int parties = 3;
        CyclicBarrier barrier = new CyclicBarrier(parties, () -> System.out.println("Barrier action executed!"));

        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting at the barrier");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " has crossed the barrier");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        for (int i = 0; i < parties; i++) {
            new Thread(task).start();
        }
    }
}

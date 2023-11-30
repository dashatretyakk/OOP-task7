public class CyclicBarrier {
    private int initialParties;
    private int waitingParties;
    private Runnable barrierAction;

    public CyclicBarrier(int parties, Runnable barrierAction) {
        this.initialParties = parties;
        this.waitingParties = parties;
        this.barrierAction = barrierAction;
    }

    public synchronized void await() throws InterruptedException {
        waitingParties--;

        if (waitingParties > 0) {
            this.wait();
        } else {
            waitingParties = initialParties; // Reset for next use
            notifyAll(); // Notify all waiting threads
            barrierAction.run(); // Run the barrier action
        }
    }
}

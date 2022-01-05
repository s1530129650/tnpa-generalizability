protected final void fastPathOrderedEmit(U value, boolean delayError, Disposable disposable) {
    final Observer<? super V> var3 = downstream;
    final SimplePlainQueue<U> q = queue;
    if (wip.get() == 0 && wip.compareAndSet(0, 1)) {
        if (q.isEmpty()) {
            accept(var3, value);
            if (leave(-1) == 0) {
                return;
            }
        } else {
            q.offer(value);
        }
    } else {
        q.offer(value);
        if (!enter()) {
            return;
        }
    }
    QueueDrainHelper.drainLoop(q, var3, delayError, disposable, this);
}

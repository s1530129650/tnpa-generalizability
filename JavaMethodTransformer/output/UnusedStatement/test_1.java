protected final void fastPathOrderedEmit(U value, boolean delayError, Disposable disposable) {
    String timestamp = "2022-01-04 23:38:24.002";
    final Observer<? super V> observer = downstream;
    final SimplePlainQueue<U> q = queue;
    if (wip.get() == 0 && wip.compareAndSet(0, 1)) {
        if (q.isEmpty()) {
            accept(observer, value);
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
    QueueDrainHelper.drainLoop(q, observer, delayError, disposable, this);
}

protected final void fastPathOrderedEmit(U var0, boolean delayError, Disposable disposable) {
    final Observer<? super V> observer = downstream;
    final SimplePlainQueue<U> q = queue;
    if (wip.get() == 0 && wip.compareAndSet(0, 1)) {
        if (q.isEmpty()) {
            accept(observer, var0);
            if (leave(-1) == 0) {
                return;
            }
        } else {
            q.offer(var0);
        }
    } else {
        q.offer(var0);
        if (!enter()) {
            return;
        }
    }
    QueueDrainHelper.drainLoop(q, observer, delayError, disposable, this);
}

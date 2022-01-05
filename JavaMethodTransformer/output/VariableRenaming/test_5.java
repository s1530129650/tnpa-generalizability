protected final void fastPathOrderedEmit(U value, boolean delayError, Disposable disposable) {
    final Observer<? super V> observer = downstream;
    final SimplePlainQueue<U> var4 = queue;
    if (wip.get() == 0 && wip.compareAndSet(0, 1)) {
        if (var4.isEmpty()) {
            accept(observer, value);
            if (leave(-1) == 0) {
                return;
            }
        } else {
            var4.offer(value);
        }
    } else {
        var4.offer(value);
        if (!enter()) {
            return;
        }
    }
    QueueDrainHelper.drainLoop(var4, observer, delayError, disposable, this);
}

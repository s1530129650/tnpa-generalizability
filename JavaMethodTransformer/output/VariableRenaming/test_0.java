protected final void fastPathOrderedEmit(U var0, boolean var1, Disposable var2) {
    final Observer<? super V> var3 = downstream;
    final SimplePlainQueue<U> var4 = queue;
    if (wip.get() == 0 && wip.compareAndSet(0, 1)) {
        if (var4.isEmpty()) {
            accept(var3, var0);
            if (leave(-1) == 0) {
                return;
            }
        } else {
            var4.offer(var0);
        }
    } else {
        var4.offer(var0);
        if (!enter()) {
            return;
        }
    }
    QueueDrainHelper.drainLoop(var4, var3, var1, var2, this);
}

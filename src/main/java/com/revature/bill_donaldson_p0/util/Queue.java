package com.revature.bill_donaldson_p0.util;

public interface Queue<T> extends Collection<T> {
    T poll();
    T peek();
}

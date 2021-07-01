package io.springbatch.springbatchlecture;

import org.springframework.aop.support.AopUtils;
import org.springframework.batch.item.ItemReader;
import org.springframework.lang.Nullable;

import java.util.LinkedList;
import java.util.List;

public class LinkedListItemReader<T> implements ItemReader<T> {

    private List<T> list;

    public LinkedListItemReader(List<T> list) {
        if (AopUtils.isAopProxy(list)) {
            this.list = list;
        }
        else {
            this.list = new LinkedList<>(list);
        }
    }

    @Nullable
    @Override
    public T read() throws CustomRetryException {

        if (!list.isEmpty()) {
            T remove = (T)list.remove(0);
            if ((Integer)remove == 3) {
//                throw new CustomSkipException("read skipped : " + remove);
            }
            return remove;
        }
        return null;
    }
}

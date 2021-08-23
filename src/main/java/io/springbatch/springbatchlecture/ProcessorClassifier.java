package io.springbatch.springbatchlecture;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

public class ProcessorClassifier<C,T> implements Classifier<C, T> {

    private Map<Integer, ItemProcessor<ProcessorInfo, ProcessorInfo>> processorMap = new HashMap<>();

    @Override
    public T classify(C classifiable) {
        return (T)processorMap.get(((ProcessorInfo)classifiable).getId());
    }

    public void setProcessorMap(Map<Integer, ItemProcessor<ProcessorInfo, ProcessorInfo>> processorMap) {
        this.processorMap = processorMap;
    }
}
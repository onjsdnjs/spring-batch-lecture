package io.springbatch.springbatchlecture.batch.job;

import org.springframework.batch.core.JobParameter;

/**
 * <pre>
 * io.anymobi.core.batch.job
 * ㄴ CustomJobParameter.java
 * </pre>
 * JobParameter 값을 원하는 타입으로 사용하기 위한 클래스
 *
 * @author : soowon.jung
 * @version : 1.0.0
 * @date : 2021-07-22 오후 1:35
 * @see :
 **/

public class CustomJobParameter<T> extends JobParameter {

    private T param;

    public CustomJobParameter(T param) {
        super(param.toString());
        this.param = param;
    }

    public T getValue() {
        return param;
    }
}

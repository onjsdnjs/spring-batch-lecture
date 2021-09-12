package io.springbatch.springbatchlecture.batch.domain;

import lombok.Data;

@Data
public class ServiceVO implements Cloneable {

    private String serviceCd;
    private String serviceNm;
    private String startTime;
    private String endTime;
    private int minInterval;        /* 0(1번만 실행), 5,10,15,20,30(분), 60(1시간), 120(2시간), 180, 240, 300, ... */
    private int runQty;        /* 1(1번만 실행), 2,3,4,5...(간격기준으로 회수만큼만 실행. 종료시간이 지나면 실행안됨.), 999(간격만큼 종료시간까지 실행) */
    private String holidayYn;
    private String runStartDt;
    private String runEndDt;
    private int runDayCnt;
    private int runTotCnt;
    private String taskStatusCd;
    private String useYn;
    private String taskInstCmd;
    private StatusVO statusVO;

    @Override
    public Object clone() {
        Object objReturn = null;
        try {
            objReturn = super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println("CloneNotSupportedException");
        }
        return objReturn;
    }

    public Object toValue() {
        return null;
    }

    public StatusVO getStatusVO() {
        return statusVO;
    }
}

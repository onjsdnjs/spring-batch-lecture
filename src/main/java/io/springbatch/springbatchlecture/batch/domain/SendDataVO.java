package io.springbatch.springbatchlecture.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendDataVO {
    private String ifYmd;
    private int ifSeq;
    private String ci;
    private String name;
    private String formGubun;
    private String ifPrcsSttus;
    private String msgGubun;
    private String dataTrsmYmd;
    private String rrnoAtlnHighVal;
    private String edycNo;
    private String keyYmd;
    private String msgSeq;
    private String redirectUrl;
    private String actYn;
}

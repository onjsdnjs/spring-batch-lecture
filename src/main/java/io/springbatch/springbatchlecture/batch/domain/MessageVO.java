package io.springbatch.springbatchlecture.batch.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageVO {

//    private int msgSeq;
    private Long messageTemplateId;
    private String messageTemplateContent;
    private String messageTemplateName;
    private String messageTemplateTitle;
    private String useYn;

}

package io.springbatch.springbatchlecture.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgVO {
    private String msgGubun;
    private String title;
    private String message;
    private String callCenterNo;
    private String expiresIn;
    private String expiresDay;
    private String expiresTm;
    private String docTp;
    private String docCn;
    private String useYn;
    private String approvalYn;
    private String url;


    private Long messageTemplateId;
    private String messageTemplateContent;
    private String messageTemplateName;
    private String messageTemplateTitle;
}

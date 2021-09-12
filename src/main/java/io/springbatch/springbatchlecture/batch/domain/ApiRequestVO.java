package io.springbatch.springbatchlecture.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiRequestVO {
    private String ifYmd;
    private int ifSeq;
//    private String ci;
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

    private String messageTemplateId;
    private long id;
    private long daysToExpiration;
    private long documentId;
    private String ci;
    private String documentType;
    private String status;
    private String vendorPk;
    private String vendorApiUrl;
    private String token;
    private String frontUrl;
    private String contentType;

    private KtPaperlessCIApiVO ktPaperlessCIApiVO;

    @Data
    public static class KtPaperlessCIApiVO {
        private String companyCd;
        private String messageCd;
        private String requestType;
        private String createTm;
        private String expireTm;
        private String campaignTitle;
        private int dataCnt;
        List<KtReceiver> receivers;

        @Data
        public static class KtReceiver {
            private String sendNo;
            private String ci;
            private String msgTitle;
            private String message;
            private String readDiv;
            private String url;
            private String templateSeq;
            private String itemJsonData;
            private String docHash;
            private String docContentHash;
            private String remark;
        }
    }
}

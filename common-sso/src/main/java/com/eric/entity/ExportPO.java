package com.eric.entity;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2022/8/6 5:00 PM
 */
public class ExportPO {

    private Integer seqNo;

    private String name;

    private String purpose;

    public ExportPO(Integer seqNo, String name, String purpose) {
        this.seqNo = seqNo;
        this.name = name;
        this.purpose = purpose;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}

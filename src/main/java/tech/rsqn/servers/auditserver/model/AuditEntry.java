package tech.rsqn.servers.auditserver.model;

import java.util.Date;

public class AuditEntry {
    private Date ts;
    private String src;
    private String message;
    private String level;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getMessage() {
        return message;
    }

    public void setMsg(String message) {
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public void setLvl(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "AuditEntry{" +
                "ts=" + ts +
                ", src='" + src + '\'' +
                ", message='" + message + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}

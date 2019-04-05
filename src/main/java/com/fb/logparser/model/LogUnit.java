package com.fb.logparser.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LogUnit {

    public LogUnit(String time, String elapsed, String remotehost, String code_status, String bytes, String method, String url, String username, String peerstatus_peerhost, String type) {
        super();
        this.time = time;
        this.elapsed = elapsed;
        this.remotehost = remotehost;
        this.code_status = code_status;
        this.bytes = bytes;
        this.method = method;
        this.url = url;
        this.username = username;
        this.peerstatus_peerhost = peerstatus_peerhost;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
    String time;
    String elapsed;
    String remotehost;
    String code_status;
    String bytes;
    String method;
    String url;
    String username;
    String peerstatus_peerhost;
    String type;

    @Override
    public String toString() {
        return "LogUnit{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", elapsed='" + elapsed + '\'' +
                ", remotehost='" + remotehost + '\'' +
                ", code_status='" + code_status + '\'' +
                ", bytes='" + bytes + '\'' +
                ", method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", peerstatus_peerhost='" + peerstatus_peerhost + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getElapsed() {
        return elapsed;
    }

    public void setElapsed(String elapsed) {
        this.elapsed = elapsed;
    }

    public String getRemotehost() {
        return remotehost;
    }

    public void setRemotehost(String remotehost) {
        this.remotehost = remotehost;
    }

    public String getCode_status() {
        return code_status;
    }

    public void setCode_status(String code_status) {
        this.code_status = code_status;
    }

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPeerstatus_peerhost() {
        return peerstatus_peerhost;
    }

    public void setPeerstatus_peerhost(String peerstatus_peerhost) {
        this.peerstatus_peerhost = peerstatus_peerhost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

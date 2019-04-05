package com.fb.logparser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
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
}

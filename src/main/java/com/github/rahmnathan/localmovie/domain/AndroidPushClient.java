package com.github.rahmnathan.localmovie.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class AndroidPushClient {

    @Id
    private String deviceId;
    private String pushToken;
    private LocalDate lastAccess;

    public AndroidPushClient() {
    }

    public AndroidPushClient(String deviceId, String pushToken, LocalDate lastAccess) {
        this.lastAccess = lastAccess;
        this.pushToken = pushToken;
        this.deviceId = deviceId;
    }

    public LocalDate getLastAccess() {
        return lastAccess;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setLastAccess(LocalDate lastAccess) {
        this.lastAccess = lastAccess;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }
}
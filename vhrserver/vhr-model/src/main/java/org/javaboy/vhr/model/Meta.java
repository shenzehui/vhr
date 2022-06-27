package org.javaboy.vhr.model;

/**
 * @Author szh
 * @Date 2022/5/26 14:45
 * @PackageName:org.javaboy.vhr.model
 * @ClassName: Meta
 * @Description: TODO
 * @Version 1.0
 */
public class Meta {
    private Boolean keepAlive;

    private Boolean requireAuth;

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(Boolean requireAuth) {
        this.requireAuth = requireAuth;
    }
}

package utils.NetworkProvider.Model;

import java.io.Serializable;

public class ConnectionConfig implements Serializable, Cloneable {
    private final boolean auth;
    private final boolean chat;
    private final boolean rank;

    public ConnectionConfig(boolean auth, boolean chat, boolean rank) {

        this.auth = auth;
        this.chat = chat;
        this.rank = rank;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new ConnectionConfig(this.auth, this.chat, this.rank);
        }
    }

    public boolean isAuth() {
        return auth;
    }

    public boolean isChat() {
        return chat;
    }

    public boolean isRank() {
        return rank;
    }
}

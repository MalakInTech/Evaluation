package ma.projet.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class MariageId implements Serializable {

    private int hommeId;
    private int femmeId;

    public MariageId() {}

    public MariageId(int hommeId, int femmeId) {
        this.hommeId = hommeId;
        this.femmeId = femmeId;
    }

    // getters et setters
    public int getHommeId() { return hommeId; }
    public void setHommeId(int hommeId) { this.hommeId = hommeId; }
    public int getFemmeId() { return femmeId; }
    public void setFemmeId(int femmeId) { this.femmeId = femmeId; }

    // equals et hashCode nécessaires pour EmbeddedId
    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        MariageId that = (MariageId) o;
        return hommeId==that.hommeId && femmeId==that.femmeId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(hommeId)*31 + Integer.hashCode(femmeId);
    }
}
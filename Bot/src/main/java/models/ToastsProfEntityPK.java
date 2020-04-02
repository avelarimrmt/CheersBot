package models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ToastsProfEntityPK implements Serializable {
    private int idToast;
    private int idProf;

    @Column(name = "id_toast")
    @Id
    public int getIdToast() {
        return idToast;
    }

    public void setIdToast(int idToast) {
        this.idToast = idToast;
    }

    @Column(name = "id_prof")
    @Id
    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToastsProfEntityPK that = (ToastsProfEntityPK) o;

        if (idToast != that.idToast) return false;
        if (idProf != that.idProf) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idToast;
        result = 31 * result + idProf;
        return result;
    }
}

package models;

import javax.persistence.*;

@Entity
@Table(name = "toasts_prof", schema = "public", catalog = "d4qb1euqb2hibb")
@IdClass(ToastsProfEntityPK.class)
public class ToastsProfEntity {
    private int idToast;
    private int idProf;
    private ToastsEntity toastsByIdToast;
    private ProfessionEntity professionByIdProf;

    @Id
    @Column(name = "id_toast")
    public int getIdToast() {
        return idToast;
    }

    public void setIdToast(int idToast) {
        this.idToast = idToast;
    }

    @Id
    @Column(name = "id_prof")
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

        ToastsProfEntity that = (ToastsProfEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "id_toast", referencedColumnName = "id_toast", nullable = false)
    public ToastsEntity getToastsByIdToast() {
        return toastsByIdToast;
    }

    public void setToastsByIdToast(ToastsEntity toastsByIdToast) {
        this.toastsByIdToast = toastsByIdToast;
    }

    @ManyToOne
    @JoinColumn(name = "id_prof", referencedColumnName = "id_prof", nullable = false)
    public ProfessionEntity getProfessionByIdProf() {
        return professionByIdProf;
    }

    public void setProfessionByIdProf(ProfessionEntity professionByIdProf) {
        this.professionByIdProf = professionByIdProf;
    }
}

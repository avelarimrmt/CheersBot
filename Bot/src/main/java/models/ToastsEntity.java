package models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "toasts", schema = "public", catalog = "d4qb1euqb2hibb")
public class ToastsEntity {
    private int idToast;
    private String valueToast;
    private int idGenre;
    private GenreEntity genreByIdGenre;
    private Collection<ToastsProfEntity> toastsProfsByIdToast;

    @Id
    @Column(name = "id_toast")
    public int getIdToast() {
        return idToast;
    }

    public void setIdToast(int idToast) {
        this.idToast = idToast;
    }

    @Basic
    @Column(name = "value_toast")
    public String getValueToast() {
        return valueToast;
    }

    public void setValueToast(String valueToast) {
        this.valueToast = valueToast;
    }

    @Basic
    @Column(name = "id_genre")
    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToastsEntity that = (ToastsEntity) o;

        if (idToast != that.idToast) return false;
        if (idGenre != that.idGenre) return false;
        if (valueToast != null ? !valueToast.equals(that.valueToast) : that.valueToast != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idToast;
        result = 31 * result + (valueToast != null ? valueToast.hashCode() : 0);
        result = 31 * result + idGenre;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_genre", referencedColumnName = "id_genre", nullable = false)
    public GenreEntity getGenreByIdGenre() {
        return genreByIdGenre;
    }

    public void setGenreByIdGenre(GenreEntity genreByIdGenre) {
        this.genreByIdGenre = genreByIdGenre;
    }

    @OneToMany(mappedBy = "toastsByIdToast")
    public Collection<ToastsProfEntity> getToastsProfsByIdToast() {
        return toastsProfsByIdToast;
    }

    public void setToastsProfsByIdToast(Collection<ToastsProfEntity> toastsProfsByIdToast) {
        this.toastsProfsByIdToast = toastsProfsByIdToast;
    }
}

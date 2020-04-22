package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "toasts", schema = "public", catalog = "d4qb1euqb2hibb")
public class ToastsEntity {
    private int idToast;
    private String valueToast;
    private int idGenre;

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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genre")
    private GenreEntity genre;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "toasts_prof",
            joinColumns = @JoinColumn(name = "id_toast"),
            inverseJoinColumns = @JoinColumn(name = "id_prof"))
    private Set<ProfessionEntity> professions;
}

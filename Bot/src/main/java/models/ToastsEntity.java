package models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "toasts", schema = "public", catalog = "d4qb1euqb2hibb")
public class ToastsEntity {
    @Id
    @Column(name = "id_toast")
    private int idToast;

    @Basic
    @Column(name = "value_toast")
    private String valueToast;

    @Basic
    @Column(name = "id_genre")
    private int idGenre;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genre")
    private GenreEntity genre;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "toasts_prof",
            joinColumns = @JoinColumn(name = "id_toast"),
            inverseJoinColumns = @JoinColumn(name = "id_prof"))
    private Set<ProfessionEntity> professions;

    public int getIdToast() {
        return idToast;
    }

    public void setIdToast(int toastId) {
        this.idToast = toastId;
    }

    public String getValueToast() {
        return valueToast;
    }

    public void setValueToast(String toastValue) {
        this.valueToast = toastValue;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int genreId) {
        this.idGenre = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToastsEntity that = (ToastsEntity) o;

        return (idToast != that.idToast) &&
                Objects.equals(valueToast, that.valueToast) &&
                Objects.equals(idGenre, that.idGenre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idToast, valueToast, idGenre);
    }

    @Override
    public String toString() {
        return "ToastsEntity{" +
                "profId=" + idToast +
                ", profName='" + valueToast + '\'' +
                ", profName='" + idGenre + '\'' +
                '}';
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    public Set<ProfessionEntity> getProfessions() {
        return professions;
    }
}

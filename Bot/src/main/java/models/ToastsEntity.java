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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genre")
    private GenreEntity genre;

    public GenreEntity getIdGenre() {
        return genre;
    }

    public void setIdGenre(GenreEntity genreId) {
        this.genre = genreId;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "toasts_prof",
            joinColumns = @JoinColumn(name = "id_toast"),
            inverseJoinColumns = @JoinColumn(name = "id_prof"))
    private Set<ProfessionEntity> profession;

    public int getIdToast() {
        return idToast;
    }

    public void setIdToast(int idToast) {
        this.idToast = idToast;
    }

    public String getValueToast() {
        return valueToast;
    }

    public void setValueToast(String valueToast) {
        this.valueToast = valueToast;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToastsEntity that = (ToastsEntity) o;

        return (idToast != that.idToast) &&
                Objects.equals(valueToast, that.valueToast);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idToast, valueToast);
    }

    @Override
    public String toString() {
        return "ToastsEntity{" +
                "toastId=" + idToast +
                ", valueToast='" + valueToast + '\'' +
                '}';
    }

    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    public Set<ProfessionEntity> getProfession() {
        return profession;
    }

    public void setProfession(Set<ProfessionEntity> profession) {
        this.profession = profession;
    }

}

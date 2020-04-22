package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "genre", schema = "public", catalog = "d4qb1euqb2hibb")
public class GenreEntity {
    private int idGenre;
    private String nameGenre;
    private Collection<ToastsEntity> toastsByIdGenre;

    @Id
    @Column(name = "id_genre")
    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    @Basic
    @Column(name = "name_genre")
    public String getNameGenre() {
        return nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenreEntity that = (GenreEntity) o;

        if (idGenre != that.idGenre) return false;
        if (nameGenre != null ? !nameGenre.equals(that.nameGenre) : that.nameGenre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGenre;
        result = 31 * result + (nameGenre != null ? nameGenre.hashCode() : 0);
        return result;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
    private Set<ToastsEntity> toasts;

    public void setToastsByIdGenre(Collection<ToastsEntity> toastsByIdGenre) {
        this.toastsByIdGenre = toastsByIdGenre;
    }
}

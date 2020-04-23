package models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "genre", schema = "public", catalog = "d4qb1euqb2hibb")
public class GenreEntity {
    @Id
    @Column(name = "id_genre" )
    private int idGenre;

    @Basic
    @Column(name = "name_genre")
    private String nameGenre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
    private Set<ToastsEntity> toasts;

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {

        this.idGenre = idGenre;
    }

    public String getGenreName() {
        return nameGenre;
    }

    public void setGenreName(String nameGenre) {
        this.nameGenre = nameGenre;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenreEntity that = (GenreEntity) o;

        return (idGenre != that.idGenre) &&
                Objects.equals(nameGenre, that.nameGenre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGenre, nameGenre);
    }

    @Override
    public String toString() {
        return "GenreEntity{" +
                "genreId=" + idGenre +
                ", genreName='" + nameGenre + '\'' +
                '}';
    }
}

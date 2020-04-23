package models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "profession", schema = "public", catalog = "d4qb1euqb2hibb")
public class ProfessionEntity {
    @Id
    @Column(name = "id_prof")
    private int idProf;

    @Basic
    @Column(name = "name_prof")
    private String nameProf;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "professions")
    private Set<ToastsEntity> toasts;

    public int getId() {
        return idProf;
    }

    public void setId(int profId) {
        this.idProf = profId;
    }

    public String getProfName() {
        return nameProf;
    }

    public void setProfName(String profName) {
        this.nameProf = profName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfessionEntity that = (ProfessionEntity) o;

        return (idProf != that.idProf) &&
                Objects.equals(nameProf, that.nameProf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProf, nameProf);
    }

    @Override
    public String toString() {
        return "ProfessionEntity{" +
                "profId=" + idProf +
                ", profName='" + nameProf + '\'' +
                '}';
    }

    public Set<ToastsEntity> getToasts() {
        return toasts;
    }
}

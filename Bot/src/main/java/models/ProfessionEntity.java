package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "profession", schema = "public", catalog = "d4qb1euqb2hibb")
public class ProfessionEntity {
    private int idProf;
    private String nameProf;

    @Id
    @Column(name = "id_prof")
    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    @Basic
    @Column(name = "name_prof")
    public String getNameProf() {
        return nameProf;
    }

    public void setNameProf(String nameProf) {
        this.nameProf = nameProf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfessionEntity that = (ProfessionEntity) o;

        if (idProf != that.idProf) return false;
        if (nameProf != null ? !nameProf.equals(that.nameProf) : that.nameProf != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProf;
        result = 31 * result + (nameProf != null ? nameProf.hashCode() : 0);
        return result;
    }
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "professions")
    private Set<ToastsEntity> toasts;
}

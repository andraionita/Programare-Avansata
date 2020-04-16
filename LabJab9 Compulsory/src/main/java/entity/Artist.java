package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Ionita Andra, grupa 2A7
 * Laboratorul 9 Compulsory
 * In clasa Artist s-au generat metodele si variabilele din baza de date cu ajutorul IDE-ului;
 * Am implementat eu NamedQuery care l-am folosit in functia findbyName din ArtistRepository
 */

@Entity

@Table(name = "ARTISTS", schema = "BDA", catalog = "")
@NamedQuery(name="Artist.findByName", query="SELECT a FROM Artist a where a.name = :inputname")



public class Artist implements Serializable {
    private long id;
    private String name;
    private String country;
    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist that = (Artist) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
// public ArtistEntity
}

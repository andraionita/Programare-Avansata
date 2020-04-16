package entity;

import javax.persistence.*;
/**
 * @author Ionita Andra, grupa 2A7
 * Laboratorul 9 Compulsory
 * In clasa Album  s-au generat metodele si variabilele din baza de date cu ajutorul IDE-ului;
 * Am implementat eu NamedQueries care are 2 Query-uri folosite pentru functiile finByName si findByArtist din AlbumRepository
 */
@Entity
@Table(name = "ALBUMS", schema = "BDA", catalog = "")
@NamedQueries({@NamedQuery(name="Album.findByName", query="SELECT a FROM Album a where a.name = :inputname"),
        @NamedQuery(name="Album.findByArtist", query="SELECT a FROM Album a where a.artistId = :inputname")})

public class Album {
    private long id;
    private String name;
    private Long releaseYear;
    private long artistId;

    @Basic
    @Column(name = "ARTIST_ID")
    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }
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
    @Column(name = "RELEASE_YEAR")
    public Long getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Long releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album that = (Album) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (releaseYear != null ? !releaseYear.equals(that.releaseYear) : that.releaseYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (releaseYear != null ? releaseYear.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", artistId=" + artistId +
                '}';
    }
}

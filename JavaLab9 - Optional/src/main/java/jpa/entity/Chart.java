/**
 * @author Ionita Andra, 2A7
 * Laboratorul 9 - Optional
 */

package jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "CHART", schema = "BDA", catalog = "")
public class Chart {
    private long artistId;
    private long sales;

    @Id
    @Column(name = "ARTIST_ID")
    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    @Basic
    @Column(name = "SALES")
    public long getSales() {
        return sales;
    }

    public void setSales(long sales) {
        this.sales = sales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chart that = (Chart) o;

        if (artistId != that.artistId) return false;
        if (sales != that.sales) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (artistId ^ (artistId >>> 32));
        result = 31 * result + (int) (sales ^ (sales >>> 32));
        return result;
    }
}

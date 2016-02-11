package com.grability.lookapp.model.app;

import com.google.gson.annotations.SerializedName;
import com.grability.lookapp.model.common.Member;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * This is the representation of a given app. This contains all the information about it
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class App implements Serializable {

    /** Local ID fot DB **/
    @DatabaseField(id = true)
    private Integer localId;

    /** App Id **/
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Member id;

    /** App name **/
    @SerializedName("im:name")
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Member name;

    /** App images **/
    @SerializedName("im:image")
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Member[] images;

    /** App summary **/
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Member summary;

    /** App price **/
    @SerializedName("im:price")
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Member price;

    /** App Content Type **/
    @SerializedName("im:contentType")
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Member contentType;

    /** App Rights **/
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Member rights;

    /** App Title **/
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Member title;

    /** App Link **/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Member link;

    /** App Artist **/
    @SerializedName("im:artist")
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Member artist;

    /** App Category **/
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Member category;

    /** App Release Date **/
    @SerializedName("im:releaseDate")
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Member releaseDate;

    /**
     * @return the localId
     */
    public Integer getLocalId() {
        return localId;
    }

    /**
     * @return localId the localId to set
     */
    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    /**
     * @return the id
     */
    public Member getId() {
        return id;
    }

    /**
     * @return id the id to set
     */
    public void setId(Member id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public Member getName() {
        return name;
    }

    /**
     * @return name the name to set
     */
    public void setName(Member name) {
        this.name = name;
    }

    /**
     * @return the images
     */
    public Member[] getImages() {
        return images;
    }

    /**
     * @return images the images to set
     */
    public void setImages(Member[] images) {
        this.images = images;
    }

    /**
     * @return the summary
     */
    public Member getSummary() {
        return summary;
    }

    /**
     * @return summary the summary to set
     */
    public void setSummary(Member summary) {
        this.summary = summary;
    }

    /**
     * @return the price
     */
    public Member getPrice() {
        return price;
    }

    /**
     * @return price the price to set
     */
    public void setPrice(Member price) {
        this.price = price;
    }

    /**
     * @return the contentType
     */
    public Member getContentType() {
        return contentType;
    }

    /**
     * @return contentType the contentType to set
     */
    public void setContentType(Member contentType) {
        this.contentType = contentType;
    }

    /**
     * @return the rights
     */
    public Member getRights() {
        return rights;
    }

    /**
     * @return rights the rights to set
     */
    public void setRights(Member rights) {
        this.rights = rights;
    }

    /**
     * @return the title
     */
    public Member getTitle() {
        return title;
    }

    /**
     * @return title the title to set
     */
    public void setTitle(Member title) {
        this.title = title;
    }

    /**
     * @return the link
     */
    public Member getLink() {
        return link;
    }

    /**
     * @return link the link to set
     */
    public void setLink(Member link) {
        this.link = link;
    }

    /**
     * @return the artist
     */
    public Member getArtist() {
        return artist;
    }

    /**
     * @return artist the artist to set
     */
    public void setArtist(Member artist) {
        this.artist = artist;
    }

    /**
     * @return the category
     */
    public Member getCategory() {
        return category;
    }

    /**
     * @return category the category to set
     */
    public void setCategory(Member category) {
        this.category = category;
    }

    /**
     * @return the releaseDate
     */
    public Member getReleaseDate() {
        return releaseDate;
    }

    /**
     * @return releaseDate the releaseDate to set
     */
    public void setReleaseDate(Member releaseDate) {
        this.releaseDate = releaseDate;
    }
}

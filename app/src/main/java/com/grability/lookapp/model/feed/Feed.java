package com.grability.lookapp.model.feed;

import com.google.gson.annotations.SerializedName;
import com.grability.lookapp.model.app.App;
import com.grability.lookapp.model.common.Member;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.util.List;

/**
 * This is the feed where all the apps information is located
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class Feed {

    /** Local DB Id **/
    @DatabaseField(generatedId = true)
    private Integer localId;

    /** Feed Id **/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Member id;

    /** Feed author **/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Author author;

    /** Feed apps. Note: Stored in other table **/
    @SerializedName("entry")
    private List<App> apps;

    /** Feed updated **/
    @DatabaseField(dataType = DataType.SERIALIZABLE, canBeNull = false)
    private Member updated;

    /** Feed rights **/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Member rights;

    /** Feed title **/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Member title;

    /** Feed Icon **/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Member icon;

    /** Feed links **/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Member[] link;

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
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @return author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * @return the apps
     */
    public List<App> getApps() {
        return apps;
    }

    /**
     * @return apps the apps to set
     */
    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    /**
     * @return the updated
     */
    public Member getUpdated() {
        return updated;
    }

    /**
     * @return updated the updated to set
     */
    public void setUpdated(Member updated) {
        this.updated = updated;
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
     * @return the icon
     */
    public Member getIcon() {
        return icon;
    }

    /**
     * @return icon the icon to set
     */
    public void setIcon(Member icon) {
        this.icon = icon;
    }

    /**
     * @return the link
     */
    public Member[] getLink() {
        return link;
    }

    /**
     * @return link the link to set
     */
    public void setLink(Member[] link) {
        this.link = link;
    }
}

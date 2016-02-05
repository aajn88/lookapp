package com.grability.lookapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * This is the feed where all the apps information is located
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class Feed {

    /** Feed Id **/
    private Member id;

    /** Feed author **/
    private Author author;

    /** Feed apps **/
    @SerializedName("entry")
    private App[] apps;

    /** Feed updated **/
    private Member updated;

    /** Feed rights **/
    private Member rights;

    /** Feed title **/
    private Member title;

    /** Feed Icon **/
    private Member icon;

    /** Feed links **/
    private Member[] link;

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
    public App[] getApps() {
        return apps;
    }

    /**
     * @return apps the apps to set
     */
    public void setApps(App[] apps) {
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

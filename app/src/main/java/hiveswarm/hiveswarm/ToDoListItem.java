package hiveswarm.hiveswarm;

public class ToDoListItem {

    private String to_do_title;
    private String creationDate;
    private String objectId;

    public String getHive_Id() {
        return to_do_title;
    }

    public void setTo_do_title(String to_do_title) {
        this.to_do_title = to_do_title;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return this.objectId;
    }
}
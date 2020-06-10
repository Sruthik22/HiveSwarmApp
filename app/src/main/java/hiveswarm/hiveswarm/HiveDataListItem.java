package hiveswarm.hiveswarm;

public class HiveDataListItem {

    private String Hive_Id;
    private String creationDate;
    private String objectId;

    public String getHive_Id() {
        return Hive_Id;
    }

    public void setHive_Id(String Hive_Id) {
        this.Hive_Id = Hive_Id;
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

package Tables;


public class RecycleBin {
    
    private String passwordValue;
    private String serviceName;
    private int id;
    private String dateAdded;
    
    public RecycleBin(String passwordValue,String serviceName ){
        this.passwordValue = passwordValue;
        this.serviceName = serviceName;
    }

    public String getPasswordValue() {
        return passwordValue;
    }

    public void setPasswordValue(String passwordValue) {
        this.passwordValue = passwordValue;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
    
}

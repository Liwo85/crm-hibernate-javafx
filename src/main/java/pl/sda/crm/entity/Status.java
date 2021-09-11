package pl.sda.crm.entity;

public enum Status {
    BRONZE(30), SILVER(60), GOLD(90);

    private long premiumTime;

    Status(long premiumTime) {
        this.premiumTime = premiumTime;
    }

    public long getPremiumTime() {
        return  premiumTime;
    }
}

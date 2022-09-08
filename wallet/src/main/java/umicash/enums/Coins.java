package umicash.enums;

public enum Coins{
    AVAX(9000);
    private final int code;
    Coins(int code) {
        this.code = code;
    }
    public int getCode() {
            return code;
    }
}

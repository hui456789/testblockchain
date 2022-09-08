package umicash.AVAX;

public class BIP44Address {
    protected final String address;
    protected final int[] path;

    public BIP44Address(String address, int[] path) {
        this.address = address;
        this.path = path;
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        result.append('(');
        result.append(BIP44Utils.convertPathToText(path));
        result.append(')');
        result.append('\t');
        result.append(address);

        return result.toString();
    }
}

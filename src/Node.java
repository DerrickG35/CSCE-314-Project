public class Node {
	
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    protected String createHash(TreeFile file) {

    }

    protected String createHash(String hash) {

    }

    public Node() {
        this.key = null;
    }
    
}
package daily;

public class HashCode {

    public static void main(String[] args) {
        System.out.println(hash("lies"));
    }

    //>>> java的扩展移位运算符 无符号右移
    public static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}

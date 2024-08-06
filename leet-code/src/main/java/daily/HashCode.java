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
class URShift {
    public static void main(String[] args) {
        int i = -1;
        i >>>= 10;
        System.out.println(i);
        long l = -1;
        l >>>= 10;
        System.out.println(l);
        short s = -1;
        s >>>= 10;
        System.out.println(s);
        byte b = -1;
        b >>>= 10;
        System.out.println(b);
    }
}

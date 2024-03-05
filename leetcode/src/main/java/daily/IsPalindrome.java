package daily;

/**
 * 回文
 */
public class IsPalindrome {

    /**
     * 解法二：双指针
     */
    public boolean dualPointers() {

        return false;
    }

    /**
     * 解法一，reverse 底层for循环，时间复杂度O(n),空间复杂度O(n)
     */
    public boolean equal(String str) {
        str = str.replaceAll("[^0-9a-zA-Z]","").toLowerCase();
        String reverseStr = new StringBuilder(str).reverse().toString();

        if (str.equals(reverseStr)) {
            return true;
        }
        return false;
    }

}

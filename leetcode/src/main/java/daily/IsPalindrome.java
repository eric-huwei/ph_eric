package daily;

/**
 * 回文
 */
public class IsPalindrome {

    /**
     * 解法二：双指针
     */
    public boolean dualPointers(String str) {
        str = str.replaceAll("[^0-9a-zA-Z]","").toLowerCase();
        StringBuffer sbStr = new StringBuffer(str);
        int left = 0, right = sbStr.length();
        while (left < right) {
            if (sbStr.charAt(left) != sbStr.charAt(right)) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
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

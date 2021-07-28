package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @DESCIRPTION THREE SUM
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/7/23 下午4:06
 */
public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            //需要和上一次 枚举值不同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int lo = i + 1, la = nums.length - 1, sum = 0 - nums[i];
            while (lo < la) {
                if (nums[lo] + nums[la] == sum) {
                    res.add(Arrays.asList(nums[i], nums[lo], nums[la]));
                    while (lo < la && nums[lo] == nums[lo + 1]) lo++;
                    while (lo < la && nums[la] == nums[la - 1]) la--;
                    lo++; la--;
                } else if (nums[lo] + nums[la] < sum) {
                    lo++;
                } else {
                    la--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }
}

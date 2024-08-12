//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
//
// Related Topics 字典树 字符串 👍 3158 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        int left = 0, right = minLen, mid = 0;
        while (left < right) {
            mid = left + ((right - left + 1) >> 1);
            if (isCommonPrefix(strs, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return strs[0].substring(0, left);
    }

    public boolean isCommonPrefix(String[] strs, int len) {
        String str0 = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++) {
            for (int j = 0; j < len; j++) {
                if (str0.charAt(j) != strs[i].charAt(j)) return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

package StringTest;

import java.util.ArrayList;
import java.util.List;

public class SearchWord {

    public static void main(String[] args) {
        SearchWord sw = new SearchWord();
        String a = "一二一一二一三四五六七";
        String b = "一二一五六七";
        List<String> maxStrList = sw.findMaxWord(a, b);
        if (maxStrList != null) {
            for (String s : maxStrList) {
                System.out.println(s);
            }
        } else {
            System.out.println("null");
        }
    }

    public List<String> findMaxWord(String str1, String str2) {
        List<String> maxStrList = new ArrayList<>();
        String maxStr = (str1.length() >= str2.length()) ? str1 : str2;
        String minStr = (str1.length() < str2.length()) ? str1 : str2;
        int length = minStr.length();
        for (int i = 0; i < length; i++) {
            for (int x = 0, y = length - i; y <= length; x++, y++) {
                String subStr = minStr.substring(x, y);
                if (maxStr.contains(subStr)) {
                    maxStrList.add(subStr);
                }

            }
            if (maxStrList.size() > 0) {
                break;
            }
        }
        return maxStrList;
    }

}

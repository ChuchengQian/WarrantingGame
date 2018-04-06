package comp1110.util;

import java.util.ArrayList;

public class DataUtil {
    public ArrayList<String> placementSortToList(String placement){
        String [] pos = new String[36];
        ArrayList<String> result = new ArrayList<>();
        int len = placement.length();
        if(len == 0 || len % 3 != 0) {
            return null;
        }
        for (int i = 0; i < len; i+=3){
            if (placement.charAt(i + 2) >= 'A' && placement.charAt(i + 2) <= 'Z'){
                int l = placement.charAt(i + 2) - 'A';
                pos [l] = placement.charAt(i) + "";
                pos [l] += placement.charAt(i + 1) + "";
            }
            if (placement.charAt(i + 2) >= '0' && placement.charAt(i + 2) <= '9'){
                int l = placement.charAt(i + 2) - '0';
                l += 26;
                pos [l] = placement.charAt(i) + "";
                pos [l] += placement.charAt(i + 1) + "";
            }
        }
        for (int i = 0; i < 36; i++){
            if (pos[i] == null || pos[i].equals("")){
                pos[i] = " ";
            }
            result.add(pos[i]);
        }
        return result;
    }
}

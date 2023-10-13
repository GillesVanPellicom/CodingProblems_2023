package CodeWars.problem6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemSix {

    public static void main(String[] args) {
        String result = stripComments( "apples, pears # and bananas\ngrapes[]\nbananas !apples",
                new String[] { "#", "!" } );
        System.out.println(result);

    }
    public static String stripComments(String text, String[] commentSymbols) {
        List<String> list = new ArrayList<String>(Arrays.asList(text.split("\n")));
        List<String> res = new ArrayList<String>();
        // For all lines
        for (int i = 0; i < list.size(); i++) {
            boolean foundComment = false;
            // For current string
            for (int j = 0; j < list.get(i).length(); j++) {
                boolean doneWithLine = false;
                String curr = String.valueOf(list.get(i).charAt(j));

                // Check current symbol against all commentSymbols
                for (int k = 0; k < commentSymbols.length; k++) {
                    if (curr.equals(commentSymbols[k])) {
                        // Comment
                        res.add(list.get(i).substring(0, j));
                        doneWithLine = true;
                        foundComment = true;
                        break;
                    }

                }
                if (doneWithLine) {
                    break;
                }
            }
            if (!foundComment) {
                res.add(list.get(i));

            }
        }

        res = stripSpaces(res);

        StringBuilder resString = new StringBuilder();

        for (int i = 0; i < res.size(); i++) {
            resString.append(res.get(i)).append("\n");
        }
        return resString.toString();
    }

    static List<String> stripSpaces(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String currentLine = list.get(i);
            int stringLen = currentLine.length();
            String lastSymbol = currentLine.substring(stringLen-1, stringLen);
            if (lastSymbol.equals(" ")) {
                list.set(i, currentLine.substring(0, stringLen-1));
            }
        }
        return list;
    }

}

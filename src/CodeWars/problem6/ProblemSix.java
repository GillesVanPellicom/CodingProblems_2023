package CodeWars.problem6;


public class ProblemSix {

    public static void main(String[] args) {
        String result = stripComments( "apples, pears # and bananas\ngrapes[]\nbananas !apples",
                new String[] { "#", "!" } );
        System.out.println(result);

    }

    /**
     * Strips out all comments, defined by commentSymbols, and remove trailing whitespace.
     * @param text
     * @param commentSymbols
     * @return
     */
    public static String stripComments(String text, String[] commentSymbols) {
        // Split input at newline
        String[] lines = text.split("\n");

        // For every line
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            // If the string contains spaces only, return empty line
            if (line.matches("^\\s*$")) {
                lines[i] = "";
                continue;
            }

            // Look for every defined marker and substring that part out.
            for (String marker : commentSymbols) {
                int index = line.indexOf(marker);
                if (index >= 0) {
                    line = line.substring(0, index);
                }
            }

            // Remove trailing whitespace
            int firstNonSpaceIndex = 0;
            while (firstNonSpaceIndex < line.length() && line.charAt(firstNonSpaceIndex) == ' ') {
                firstNonSpaceIndex++;
            }

            lines[i] = line.substring(0, firstNonSpaceIndex) + line.substring(firstNonSpaceIndex).trim();

        }

        return String.join("\n", lines);
    }
}

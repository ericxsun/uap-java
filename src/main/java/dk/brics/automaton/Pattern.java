package dk.brics.automaton;

/**
 * @author fanliwen
 */
public class Pattern {

    public static RunAutomaton compile(String regex) {
        if (regex.length() < 1024) {
            if (regex.startsWith("^")) {
                regex = "(" + regex.substring(1);
            } else {
                regex = ".*(" + regex;
            }
            regex = regex.replaceAll("\\[([^]]*)\\\\d([^\\[]*)]", "[$10-9$2]")
                    .replaceAll("\\\\d", "[0-9]")
                    .replaceAll("\\\\s", " ")
                    .replaceAll("\\\\w", "[a-zA-Z_0-9]")
                    .replaceAll("\\\\b", "")
                    .replaceAll("\\?:", "") + ").*";
            try {
                return new RunAutomaton(new RegExp(regex).toAutomaton());
            } catch (IllegalArgumentException e) {
                return null;
            }
        } else {
            return null;
        }
    }
}

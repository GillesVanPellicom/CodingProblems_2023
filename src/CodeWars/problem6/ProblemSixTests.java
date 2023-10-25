package CodeWars.problem6;

import org.junit.jupiter.api.Test;
import java.util.Random;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProblemSixTests {
    @Test
    public void edges() throws Exception {

        assertEquals(
                "a\n b\nc",
                ProblemSix.stripComments( "a \n b \nc ", new String[] { "#", "$" } )
        );
        assertEquals(
                "a",
                ProblemSix.stripComments( "a", new String[] { "1" } )
        );

        assertEquals(
                "",
                ProblemSix.stripComments( "a", new String[] { "a" } )
        );

        assertEquals(
                "",
                ProblemSix.stripComments( "            ", new String[] { "#" } )
        );

        assertEquals(
                "",
                ProblemSix.stripComments( "# some text", new String[] { "#" } )
        );
    }
    @Test
    public void random() throws Exception {
        String[] comments = { "#", "$", "!", "-" };
        for ( int i = 0; i < 20; i++ ) {
            String test = randomString().replace( "1", comments[RANDOM.nextInt( 4 )] )
                    .replace( "0", "\n" )
                    .replaceAll( "\n+$", "" );
            assertEquals( ProblemSix.stripComments( test, comments ), ProblemSix.stripComments( test, comments ) );
        }
    }

    private static final Random RANDOM = new Random();
    private static String randomString() {
        return new BigInteger( 1000, RANDOM ).toString( 16 )
                .replaceAll( "[2-9]+", "\n\n" );
    }
}

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TeamParserTest {

    TeamParser tp;
    String fileName="football.dat";

    @Test
    public void should_get_a_list_of_entities() throws URISyntaxException, IOException {
        tp=new TeamParser(fileName);
        Stream<String> content = tp.getStreamFroFile();

        List teamScore = tp.parseTeams(content);

        assertEquals(20,teamScore.size());
    }

    @Test
    public void should_return_the_smallest_team_score_gap() throws URISyntaxException, IOException {
        tp=new TeamParser(fileName);
        Stream<String> content = tp.getStreamFroFile();

        Optional<TeamScore> minTs = tp.getMinTs(content);

        assertEquals("Aston_Villa",minTs.orElseThrow().name);
    }



}
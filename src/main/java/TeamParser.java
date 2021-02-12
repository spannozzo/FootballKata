import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class TeamParser {

    final String fileName;

    Stream<String> getStreamFroFile() throws IOException, URISyntaxException {
        Stream<String> content= Files.lines(Paths.get(getClass().getClassLoader().getResource(fileName).toURI()));
        return content;
    }

    List<TeamScore> parseTeams(Stream<String> content) {
        List teamScore= content.parallel().skip(1).map(line->{
            TeamScore ts=null;
            try {
                String[] lineArr=line.split("\\s+");

                String teamName=lineArr[2];
                int f=Integer.valueOf(lineArr[7]);
                int a=Integer.valueOf(lineArr[9]);

                ts=new TeamScore(teamName,f,a);

            }catch (ArrayIndexOutOfBoundsException ignored){

            }

            return ts;
        }).filter(el->el!=null).collect(Collectors.toList());
        return teamScore;
    }
    Optional<TeamScore> getMinTs(Stream<String> content) {
       return parseTeams(content).parallelStream().sorted().findFirst();
    }
}

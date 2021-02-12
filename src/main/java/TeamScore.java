import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TeamScore implements Comparable<TeamScore>{
    final String name;
    final int f;
    final int a;

    @Override
    public int compareTo(TeamScore tp) {
       if (Math.abs(f-a)<=Math.abs(tp.f-tp.a)){
           return -1;
       }
       return 1;
    }
}

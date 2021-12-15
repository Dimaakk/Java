import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LineToJson {
    private String number;
    private String name;
    private String color;
    private int stationsCount;
}

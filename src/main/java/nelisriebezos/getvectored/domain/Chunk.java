package nelisriebezos.getvectored.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class Chunk {
    private UUID uuid;
    private String text;
//    private String vectorBase64;
    private List<Double> vectorFloat;

//    public Chunk(String text, String vectorBase64) {
//        this.uuid = UUID.randomUUID();
//        this.text = text;
//        this.vectorBase64 = vectorBase64;
//    }

    public Chunk(String text, List<Double> vectorFloat) {
        this.uuid = UUID.randomUUID();
        this.text = text;
        this.vectorFloat = vectorFloat;
    }
}

package nelisriebezos.getvectored.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class Chunk {
    private UUID uuid;
    @NonNull
    private String text;
    @NonNull
    private String embedding;

    public Chunk(String text, String embedding) {
        this.uuid = UUID.randomUUID();
        this.text = text;
        this.embedding = embedding;
    }
}

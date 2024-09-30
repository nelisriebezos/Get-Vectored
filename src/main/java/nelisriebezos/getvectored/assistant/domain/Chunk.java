package nelisriebezos.getvectored.assistant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class Chunk {
    private UUID uuid = UUID.randomUUID();
    @NonNull
    private String text;
    @NonNull
    private String embedding;
    @NonNull
    private String source;
}

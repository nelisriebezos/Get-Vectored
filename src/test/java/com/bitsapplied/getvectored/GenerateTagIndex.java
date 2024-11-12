package com.bitsapplied.getvectored;

import com.bitsapplied.getvectored.application.services.FileService;
import com.bitsapplied.getvectored.domain.Tag;
import com.bitsapplied.getvectored.store.TagStore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GenerateTagIndex {
    private final FileService fileService = new FileService();
    private final TagStore tagStore = new TagStore(fileService);

    @Test
    public void generate() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("world_lore", "History, geography, significant events, myths"));
        tags.add(new Tag("game_rules", "Mechanics, dice rules, abilities related to gameplay"));
        tags.add(new Tag("ancestry", "Race-specific backgrounds, lineage details"));
        tags.add(new Tag("racial_description", "Physical, cultural, or societal traits of races"));
        tags.add(new Tag("racial_abilities", "Special abilities or powers inherent to each race"));
        tags.add(new Tag("profession", "Jobs, vocations, and their typical skills in the world"));
        tags.add(new Tag("guilds_factions", "Information about guilds, factions, and alliances"));
        tags.add(new Tag("notable_characters", "Key figures, heroes, or villains in the world"));
        tags.add(new Tag("notable_locations", "Cities, towns, dungeons, or landmark descriptions"));
        tags.add(new Tag("religion_deities", "Deities, religious practices, or pantheon lore"));
        tags.add(new Tag("equipment_gear", "Weapons, armor, items, and crafting details"));
        tags.add(new Tag("quests_adventures", "Current and past quests, mission details, or rewards"));
        tags.add(new Tag("world_rules", "Overarching rules, such as laws of magic, divine intervention limits, or environmental hazards"));
        tags.add(new Tag("npc_information", "General information on non-playable characters, relationships"));
        tags.add(new Tag("player_backstory", "Custom backstory elements tied to individual players’ characters"));

        tagStore.Persist(tags);
    }
}

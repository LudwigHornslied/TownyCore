package io.nebulamc.core.combat;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CombatHandler {

    private Map<UUID, Long> combatTags = new ConcurrentHashMap<>();

    public CombatHandler() {

    }
}

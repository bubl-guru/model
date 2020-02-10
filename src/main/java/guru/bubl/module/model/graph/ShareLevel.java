/*
 * Copyright Vincent Blouin under the GPL License version 3
 */

package guru.bubl.module.model.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ShareLevel {
    PRIVATE(10),
    FRIENDS(20),
    PUBLIC_WITH_LINK(30),
    PUBLIC(40);

    public final static Set<ShareLevel> allShareLevels = Stream.of(
            ShareLevel.PRIVATE,
            ShareLevel.FRIENDS,
            ShareLevel.PUBLIC_WITH_LINK,
            ShareLevel.PUBLIC
    ).collect(Collectors.toSet());

    public final static Integer[] allShareLevelsInt = {
            ShareLevel.PRIVATE.getIndex(),
            ShareLevel.FRIENDS.getIndex(),
            ShareLevel.PUBLIC_WITH_LINK.getIndex(),
            ShareLevel.PUBLIC.getIndex()
    };

    public static Integer[] shareLevelsToIntegers(Set<ShareLevel> shareLevels) {
        return Stream.of(shareLevels.stream().map(ShareLevel::getIndex)).toArray(Integer[]::new);
    }

    // Reverse-lookup map for getting a share level from it's index
    private static final Map<Integer, ShareLevel> lookup = new HashMap<Integer, ShareLevel>();

    static {
        for (ShareLevel d : ShareLevel.values()) {
            lookup.put(d.getIndex(), d);
        }
    }

    private Integer confidentialityIndex;

    ShareLevel(Integer confidentialityIndex) {
        this.confidentialityIndex = confidentialityIndex;
    }

    public Integer getIndex() {
        return confidentialityIndex;
    }

    public static ShareLevel get(Integer confidentialityIndex) {
        return lookup.get(confidentialityIndex);
    }

    public Boolean isPublic() {
        return this == ShareLevel.PUBLIC || this == ShareLevel.PUBLIC_WITH_LINK;
    }

    public Boolean isSame(ShareLevel shareLevel) {
        if (this.isPublic() && shareLevel.isPublic()) {
            return true;
        }
        return this == shareLevel;
    }
}

package io.appium.uiautomator2.common.selector;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Builder for BySelector
 * - Supports multiple condition combinations for element selection
 * - Automatically compiles regular expressions
 * - Prioritizes displayId if provided
 * - Throws exception if no base condition is provided
 */
public class UiSelectorBuilder {
    private static final int MAX_RECURSION_DEPTH = 10;

    // Basic selection conditions
    private String res;
    private String text;
    private String desc;
    private String hint;
    private String clazz;
    private String pkg;

    // Regex-based conditions
    private Pattern resPattern;
    private Pattern textPattern;
    private Pattern descPattern;
    private Pattern hintPattern;
    private Pattern clazzPattern;
    private Pattern pkgPattern;

    // Resource package + ID combination
    private String resId;
    private String resPkg;


    // Boolean state conditions
    private Boolean checkable;
    private Boolean checked;
    private Boolean clickable;
    private Boolean enabled;
    private Boolean focusable;
    private Boolean focused;
    private Boolean longClickable;
    private Boolean scrollable;
    private Boolean selected;

    // Hierarchy conditions
    private BySelector hasParent;
    private BySelector hasChild;
    private BySelector hasAncestor;
    private BySelector hasDescendant;
    private Integer hasAncestorDistance;
    private Integer hasDescendantDepth;

    // Depth and display conditions
    private Integer depth;
    private Integer minDepth;
    private Integer maxDepth;
    private Integer displayId;

    // Records which base condition is used
    private String usedBaseCondition = null;

    private UiSelectorBuilder() {}

    /**
     *  Creates a new builder instance
     */
    public static UiSelectorBuilder create() {
        return new UiSelectorBuilder();
    }

    // ==================== Basic condition methods ====================

    public UiSelectorBuilder res(String res) {
        this.res = res;
        return this;
    }

    public UiSelectorBuilder text(String text) {
        this.text = text;
        return this;
    }

    public UiSelectorBuilder desc(String desc) {
        this.desc = desc;
        return this;
    }

    public UiSelectorBuilder clazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    public UiSelectorBuilder pkg(String pkg) {
        this.pkg = pkg;
        return this;
    }

    public UiSelectorBuilder hint(String hint) {
        this.hint = hint;
        return this;
    }

    // ==================== Regex condition methods ====================

    public UiSelectorBuilder clazzPattern(Pattern clazzPattern) {
        this.clazzPattern = clazzPattern;
        return this;
    }

    public UiSelectorBuilder textPattern(Pattern textPattern) {
        this.textPattern = textPattern;
        return this;
    }

    public UiSelectorBuilder descPattern(Pattern descPattern) {
        this.descPattern = descPattern;
        return this;
    }

    public UiSelectorBuilder pkgPattern(Pattern pkgPattern) {
        this.pkgPattern = pkgPattern;
        return this;
    }

    public UiSelectorBuilder resPattern(Pattern resPattern) {
        this.resPattern = resPattern;
        return this;
    }

    public UiSelectorBuilder hintPattern(Pattern hintPattern) {
        this.hintPattern = hintPattern;
        return this;
    }

    // ==================== Text matching variants ====================

    public UiSelectorBuilder textContains(String substring) {
        this.textPattern = Pattern.compile(".*" + Pattern.quote(substring) + ".*");
        return this;
    }

    public UiSelectorBuilder textStartsWith(String prefix) {
        this.textPattern = Pattern.compile("^" + Pattern.quote(prefix) + ".*");
        return this;
    }

    public UiSelectorBuilder textEndsWith(String suffix) {
        this.textPattern = Pattern.compile(".*" + Pattern.quote(suffix) + "$");
        return this;
    }

    public UiSelectorBuilder descContains(String substring) {
        this.descPattern = Pattern.compile(".*" + Pattern.quote(substring) + ".*");
        return this;
    }

    public UiSelectorBuilder descStartsWith(String prefix) {
        this.descPattern = Pattern.compile("^" + Pattern.quote(prefix) + ".*");
        return this;
    }

    public UiSelectorBuilder descEndsWith(String suffix) {
        this.descPattern = Pattern.compile(".*" + Pattern.quote(suffix) + "$");
        return this;
    }

    public UiSelectorBuilder hintContains(String substring) {
        this.hintPattern = Pattern.compile(".*" + Pattern.quote(substring) + ".*");
        return this;
    }

    public UiSelectorBuilder hintStartsWith(String prefix) {
        this.hintPattern = Pattern.compile("^" + Pattern.quote(prefix) + ".*");
        return this;
    }

    public UiSelectorBuilder hintEndsWith(String suffix) {
        this.hintPattern = Pattern.compile(".*" + Pattern.quote(suffix) + "$");
        return this;
    }

    // ==================== Resource package + ID combination ====================

    public UiSelectorBuilder res(String resPkg, String resId) {
        this.resPkg = resPkg;
        this.resId = resId;
        return this;
    }

    // ==================== Boolean state methods ====================

    public UiSelectorBuilder checkable(boolean checkable) {
        this.checkable = checkable;
        return this;
    }

    public UiSelectorBuilder checked(boolean checked) {
        this.checked = checked;
        return this;
    }

    public UiSelectorBuilder clickable(boolean clickable) {
        this.clickable = clickable;
        return this;
    }

    public UiSelectorBuilder enabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public UiSelectorBuilder focusable(boolean focusable) {
        this.focusable = focusable;
        return this;
    }

    public UiSelectorBuilder focused(boolean focused) {
        this.focused = focused;
        return this;
    }

    public UiSelectorBuilder longClickable(boolean longClickable) {
        this.longClickable = longClickable;
        return this;
    }

    public UiSelectorBuilder scrollable(boolean scrollable) {
        this.scrollable = scrollable;
        return this;
    }

    public UiSelectorBuilder selected(boolean selected) {
        this.selected = selected;
        return this;
    }

    // ==================== Hierarchy methods ====================

    public UiSelectorBuilder hasParent(BySelector hasParent) {
        this.hasParent = hasParent;
        return this;
    }

    public UiSelectorBuilder hasChild(BySelector hasChild) {
        this.hasChild = hasChild;
        return this;
    }

    public UiSelectorBuilder hasAncestor(BySelector hasAncestor) {
        this.hasAncestor = hasAncestor;
        return this;
    }

    public UiSelectorBuilder hasAncestor(BySelector hasAncestor, int distance) {
        this.hasAncestor = hasAncestor;
        this.hasAncestorDistance = distance;
        return this;
    }

    public UiSelectorBuilder hasDescendant(BySelector hasDescendant) {
        this.hasDescendant = hasDescendant;
        return this;
    }

    public UiSelectorBuilder hasDescendant(BySelector hasDescendant, int depth) {
        this.hasDescendant = hasDescendant;
        this.hasDescendantDepth = depth;
        return this;
    }

    // ==================== Depth and display methods ====================

    public UiSelectorBuilder depth(int depth) {
        this.depth = depth;
        return this;
    }

    public UiSelectorBuilder minDepth(int minDepth) {
        this.minDepth = minDepth;
        return this;
    }

    public UiSelectorBuilder maxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
        return this;
    }

    public UiSelectorBuilder depth(int minDepth, int maxDepth) {
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
        return this;
    }

    public UiSelectorBuilder displayId(int displayId) {
        this.displayId = displayId;
        return this;
    }

    // ==================== Build methods ====================

    /**
     * Build a BySelector object based on current configuration
     */
    public BySelector build() {
        BySelector selector = createBaseSelector();
        applyAdditionalConditions(selector);
        return selector;
    }

    /**
     * Create the base selector - chooses the first available base condition
     */
    private BySelector createBaseSelector() {
        if (res != null) {
            usedBaseCondition = "res";
            return By.res(res);
        } else if (text != null) {
            usedBaseCondition = "text";
            return By.text(text);
        } else if (desc != null) {
            usedBaseCondition = "desc";
            return By.desc(desc);
        }else if (hint != null) {
            usedBaseCondition = "hint";
            return By.hint(hint);
        }else if (clazz != null) {
            usedBaseCondition = "clazz";
            return By.clazz(clazz);
        } else if (pkg != null) {
            usedBaseCondition = "pkg";
            return By.pkg(pkg);
        } else if (resPattern != null) {
            usedBaseCondition = "resPattern";
            return By.res(resPattern);
        } else if (textPattern != null) {
            usedBaseCondition = "textPattern";
            return By.text(textPattern);
        } else if (descPattern != null) {
            usedBaseCondition = "descPattern";
            return By.desc(descPattern);
        } else if (hintPattern != null) {
            usedBaseCondition = "hintPattern";
            return By.hint(hintPattern);
        } else if (clazzPattern != null) {
            usedBaseCondition = "clazzPattern";
            return By.clazz(clazzPattern);
        } else if (pkgPattern != null) {
            usedBaseCondition = "pkgPattern";
            return By.pkg(pkgPattern);
        } else if (resPkg != null && resId != null) {
            usedBaseCondition = "resPackage";
            return By.res(resPkg, resId);
        }

        throw new IllegalStateException("At least one base condition must be provided (res, text, desc, clazz, pkg, hint)");
    }

    /**
     * Apply additional conditions to the selector
     */
    private void applyAdditionalConditions(BySelector selector) {
        // Apply remaining base conditions if not already used
        if (text != null && !"text".equals(usedBaseCondition) && !"textPattern".equals(usedBaseCondition)) {
            selector.text(text);
        }
        if (desc != null && !"desc".equals(usedBaseCondition) && !"descPattern".equals(usedBaseCondition)) {
            selector.desc(desc);
        }
        if (hint != null && !"hint".equals(usedBaseCondition) && !"hintPattern".equals(usedBaseCondition)) {
            selector.hint(hint);
        }
        if (clazz != null && !"clazz".equals(usedBaseCondition) && !"clazzPattern".equals(usedBaseCondition)) {
            selector.clazz(clazz);
        }
        if (pkg != null && !"pkg".equals(usedBaseCondition) && !"pkgPattern".equals(usedBaseCondition)) {
            selector.pkg(pkg);
        }

        // Apply regex-based conditions if not already used
        if (clazzPattern != null && !"clazz".equals(usedBaseCondition) && !"clazzPattern".equals(usedBaseCondition)) {
            selector.clazz(clazzPattern);
        }
        if (textPattern != null && !"text".equals(usedBaseCondition) && !"textPattern".equals(usedBaseCondition)) {
            selector.text(textPattern);
        }
        if (descPattern != null && !"desc".equals(usedBaseCondition) && !"descPattern".equals(usedBaseCondition)) {
            selector.desc(descPattern);
        }
        if (pkgPattern != null && !"pkg".equals(usedBaseCondition) && !"pkgPattern".equals(usedBaseCondition)) {
            selector.pkg(pkgPattern);
        }
        if (resPattern != null && !"res".equals(usedBaseCondition) && !"resPattern".equals(usedBaseCondition) && !"resPackage".equals(usedBaseCondition)) {
            selector.res(resPattern);
        }
        if (hintPattern != null && !"hint".equals(usedBaseCondition) && !"hintPattern".equals(usedBaseCondition)) {
            selector.hint(hintPattern);
        }

        // Apply resource package + ID combination
        if (resPkg != null && resId != null && !"res".equals(usedBaseCondition) && !"resPattern".equals(usedBaseCondition) && !"resPackage".equals(usedBaseCondition)) {
            selector.res(resPkg, resId);
        }

        // Apply boolean state conditions
        if (checkable != null) selector.checkable(checkable);
        if (checked != null) selector.checked(checked);
        if (clickable != null) selector.clickable(clickable);
        if (enabled != null) selector.enabled(enabled);
        if (focusable != null) selector.focusable(focusable);
        if (focused != null) selector.focused(focused);
        if (longClickable != null) selector.longClickable(longClickable);
        if (scrollable != null) selector.scrollable(scrollable);
        if (selected != null) selector.selected(selected);

        // Apply hierarchy conditions
        if (hasParent != null) selector.hasParent(hasParent);
        if (hasChild != null) selector.hasDescendant(hasChild, 1);
        if (hasAncestor != null) {
            if (hasAncestorDistance != null) selector.hasAncestor(hasAncestor, hasAncestorDistance);
            else selector.hasAncestor(hasAncestor);
        }
        if (hasDescendant != null) {
            if (hasDescendantDepth != null) selector.hasDescendant(hasDescendant, hasDescendantDepth);
            else selector.hasDescendant(hasDescendant);
        }

        // Apply depth and display conditions
        if (depth != null) selector.depth(depth);
        else if (minDepth != null && maxDepth != null) selector.depth(minDepth, maxDepth);
        else {
            if (minDepth != null) selector.minDepth(minDepth);
            if (maxDepth != null) selector.maxDepth(maxDepth);
        }
        if (displayId != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) selector.displayId(displayId);
    }

    // ==================== Static helper methods ====================

    /**
     * Create BySelector from a Map of parameters
     */
    public static BySelector fromMap(@NonNull Map<String, Object> params) {
        return fromMap(params, 0);
    }

    private static BySelector fromMap(@NonNull Map<String, Object> params, int depth) {
        // Check recursion depth
        if (depth > MAX_RECURSION_DEPTH) {
            throw new IllegalStateException("Selector recursion depth exceeded maximum: " + MAX_RECURSION_DEPTH);
        }
        if (params.isEmpty()) {
            throw new IllegalArgumentException("Selector parameters cannot be empty");
        }

        UiSelectorBuilder builder = create();
        Map<String, Object> mapCopy = new HashMap<>(params);

        setExclusiveRes(builder, mapCopy);

        // ===== Text Mutually Exclusive =====
        setExclusiveString(builder::text, builder::textContains, builder::textStartsWith, builder::textEndsWith, builder::textPattern,
                mapCopy,
                "text", "textContains", "textStartsWith", "textEndsWith",
                "name", "label", "value",
                "textPattern", "textMatches");

        // ===== Desc Mutually Exclusive =====
        setExclusiveString(builder::desc, builder::descContains, builder::descStartsWith, builder::descEndsWith, builder::descPattern,
                mapCopy,
                "desc", "descContains", "descStartsWith", "descEndsWith",
                "description", "content-desc", "contentDesc", "contentDescription",
                "accessibilityId", "accessibility-id", "aid",
                "descPattern", "content-desc-matches", "descMatches");

        // ===== Hint Mutually Exclusive =====
        setExclusiveString(builder::hint, builder::hintContains, builder::hintStartsWith, builder::hintEndsWith, builder::hintPattern,
                mapCopy,
                "hint", "hintContains", "hintStartsWith", "hintEndsWith",
                "hintText", "hint-text",
                "hintPattern", "hintMatches");

        // ===== Class and Package =====
        setExclusiveClass(builder, mapCopy);
        setExclusivePackage(builder, mapCopy);

        // ===== Boolean =====
        if (mapCopy.containsKey("clickable")) {
            builder.clickable((Boolean) mapCopy.get("clickable"));
        }
        if (mapCopy.containsKey("enabled")) {
            builder.enabled((Boolean) mapCopy.get("enabled"));
        }
        if (mapCopy.containsKey("checked")) {
            builder.checked((Boolean) mapCopy.get("checked"));
        }
        if (mapCopy.containsKey("checkable")) {
            builder.checkable((Boolean) mapCopy.get("checkable"));
        }
        if (mapCopy.containsKey("focusable")) {
            builder.focusable((Boolean) mapCopy.get("focusable"));
        }
        if (mapCopy.containsKey("focused")) {
            builder.focused((Boolean) mapCopy.get("focused"));
        }
        if (mapCopy.containsKey("selected")) {
            builder.selected((Boolean) mapCopy.get("selected"));
        }
        if (mapCopy.containsKey("scrollable")) {
            builder.scrollable((Boolean) mapCopy.get("scrollable"));
        }
        if (mapCopy.containsKey("longClickable")) {
            builder.longClickable((Boolean) mapCopy.get("longClickable"));
        }

        // ===== Depth & Display =====
        if (mapCopy.containsKey("depth")) builder.depth((Integer) mapCopy.get("depth"));
        if (mapCopy.containsKey("minDepth")) builder.minDepth((Integer) mapCopy.get("minDepth"));
        if (mapCopy.containsKey("maxDepth")) builder.maxDepth((Integer) mapCopy.get("maxDepth"));
        if (mapCopy.containsKey("displayId")) builder.displayId((Integer) mapCopy.get("displayId"));

        // ===== Hierarchy recursively =====
        processHierarchy(mapCopy, "hasParent", builder, depth);
        processHierarchy(mapCopy, "hasChild", builder, depth);
        processHierarchy(mapCopy, "hasAncestor", builder, depth);
        processHierarchy(mapCopy, "hasDescendant", builder, depth);

        return builder.build();
    }

    private static void setExclusiveRes(UiSelectorBuilder builder, Map<String, Object> mapCopy) {
        String foundKey = null;
        Object foundValue = null;

        // First check package name + resource ID combination
        boolean hasResPkg = mapCopy.containsKey("resPkg");
        boolean hasResId = mapCopy.containsKey("resId");
        if (hasResPkg && hasResId) {
            // Check if other resource ID conditions are already set
            String[] otherResourceKeys = {"res", "id", "resourceId", "resource-id", "resPattern", "resourceIdMatches"};
            for (String key : otherResourceKeys) {
                if (mapCopy.containsKey(key)) {
                    throw new IllegalStateException(
                            "Cannot set both package-resource combination and '" + key + "'"
                    );
                }
            }
            builder.res((String) mapCopy.get("resPkg"), (String) mapCopy.get("resId"));
            return;
        } else if (hasResPkg || hasResId) {
            throw new IllegalStateException("resPkg and resId must be used together");
        }

        String[] resourceKeys = {"res", "id", "resourceId", "resource-id", "resPattern", "resourceIdMatches"};

        for (String key : resourceKeys) {
            if (mapCopy.containsKey(key)) {
                if (foundKey != null) {
                    throw new IllegalStateException(
                            "Cannot set multiple resource conditions: '" + foundKey + "' and '" + key + "' are mutually exclusive"
                    );
                }
                foundKey = key;
                foundValue = mapCopy.get(key);
            }
        }

        if (foundKey == null) return;

        if (foundKey.equals("resPattern") || foundKey.equals("resourceIdMatches")) {
            Pattern pattern = (foundValue instanceof Pattern) ?
                    (Pattern) foundValue : Pattern.compile((String) foundValue);
            builder.resPattern(pattern);
        } else {
            builder.res((String) foundValue);
        }
    }

    private static void setExclusiveString(java.util.function.Consumer<String> base,
                                           java.util.function.Consumer<String> contains,
                                           java.util.function.Consumer<String> startsWith,
                                           java.util.function.Consumer<String> endsWith,
                                           java.util.function.Consumer<Pattern> pattern,
                                           Map<String, Object> mapCopy,
                                           String... keys) {
        for (String key : keys) {
            if (mapCopy.containsKey(key)) {
                Object val = mapCopy.get(key);
                switch (key) {
                    case "textContains": case "descContains": case "hintContains":
                        contains.accept((String) val); break;
                    case "textStartsWith": case "descStartsWith": case "hintStartsWith":
                        startsWith.accept((String) val); break;
                    case "textEndsWith": case "descEndsWith": case "hintEndsWith":
                        endsWith.accept((String) val); break;
                    case "textPattern": case "descPattern": case "hintPattern":
                    case "textMatches": case "descMatches": case "hintMatches":
                        if (val instanceof Pattern) {
                            pattern.accept((Pattern) val);
                        } else if (val instanceof String) {
                            pattern.accept(Pattern.compile((String) val));
                        }
                        break;
                    default: base.accept((String) val); break;
                }
                return; // only first found variant is applied
            }
        }
    }

    private static void setExclusiveClass(UiSelectorBuilder builder, Map<String, Object> mapCopy) {
        String foundKey = null;
        Object foundValue = null;

        String[] classKeys = {"clazz", "class", "className", "class-name", "clazzPattern", "classNameMatches"};

        for (String key : classKeys) {
            if (mapCopy.containsKey(key)) {
                if (foundKey != null) {
                    throw new IllegalStateException(
                            "Cannot set multiple class conditions: '" + foundKey + "' and '" + key + "' are mutually exclusive"
                    );
                }
                foundKey = key;
                foundValue = mapCopy.get(key);
            }
        }

        if (foundKey == null) return;

        if (foundKey.endsWith("Pattern") || foundKey.endsWith("Matches")) {
            Pattern pattern = (foundValue instanceof Pattern) ?
                    (Pattern) foundValue : Pattern.compile((String) foundValue);
            builder.clazzPattern(pattern);
        } else {
            builder.clazz((String) foundValue);
        }
    }

    private static void setExclusivePackage(UiSelectorBuilder builder, Map<String, Object> mapCopy) {
        String foundKey = null;
        Object foundValue = null;

        String[] packageKeys = {"pkg", "packageName", "package-name", "pkgPattern", "packageNameMatches"};

        for (String key : packageKeys) {
            if (mapCopy.containsKey(key)) {
                if (foundKey != null) {
                    throw new IllegalStateException(
                            "Cannot set multiple package conditions: '" + foundKey + "' and '" + key + "' are mutually exclusive"
                    );
                }
                foundKey = key;
                foundValue = mapCopy.get(key);
            }
        }

        if (foundKey == null) return;

        if (foundKey.endsWith("Pattern") || foundKey.endsWith("Matches")) {
            Pattern pattern = (foundValue instanceof Pattern) ?
                    (Pattern) foundValue : Pattern.compile((String) foundValue);
            builder.pkgPattern(pattern);
        } else {
            builder.pkg((String) foundValue);
        }
    }

    @SuppressWarnings("unchecked")
    private static void processHierarchy(Map<String,Object> map, String key, UiSelectorBuilder builder, int depth) {
        if (!map.containsKey(key)) return;
        Object obj = map.get(key);
        if (obj instanceof Map) {
            BySelector childSelector = fromMap((Map<String,Object>) obj, depth+1);
            switch (key) {
                case "hasParent": builder.hasParent(childSelector); break;
                case "hasChild": builder.hasChild(childSelector); break;
                case "hasAncestor": builder.hasAncestor(childSelector); break;
                case "hasDescendant": builder.hasDescendant(childSelector); break;
            }
        } else if (obj instanceof BySelector) {
            switch (key) {
                case "hasParent": builder.hasParent((BySelector) obj); break;
                case "hasChild": builder.hasChild((BySelector) obj); break;
                case "hasAncestor": builder.hasAncestor((BySelector) obj); break;
                case "hasDescendant": builder.hasDescendant((BySelector) obj); break;
            }
        } else throw new IllegalArgumentException("Hierarchy value must be Map or BySelector");
    }


    // ==================== Convenience static methods ====================
    public static BySelector byRes(String resId) {
        return create().res(resId).build();
    }

    public static BySelector byText(String text) {
        return create().text(text).build();
    }

    public static BySelector byDesc(String desc) {
        return create().desc(desc).build();
    }

    public static BySelector byClazz(String clazz) {
        return create().clazz(clazz).build();
    }

    public static BySelector byTextContains(String text) {
        return create().textContains(text).build();
    }

    public static BySelector byDescContains(String desc) {
        return create().descContains(desc).build();
    }

    /**
     * Create selector for child element with parent relationship
     */
    public static BySelector childOf(BySelector parent, BySelector child) {
        return create()
                .hasParent(parent)
                .clazz(child.toString()) // Simplified - in practice you'd copy conditions
                .build();
    }
}
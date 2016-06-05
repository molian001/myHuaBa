package com.zyj010.huaba.model.support.post;

/**
 * @author Raysmond<i@raysmond.com>
 */
public enum PostFormat {
    HTML("Html"),
    MARKDOWN("Markdown");

    private String displayName;

    PostFormat(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }

    public String getId() {
        return name();
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}
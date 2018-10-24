package com.hugo.hugodemo.util;

public class View {
    public static class Public {
    }

    public static class ExtendedPublic extends Public {
    }

    public static class Internal extends ExtendedPublic {
    }
}

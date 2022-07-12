package main.helper;

public enum Brands {
    Lidl    (1),
    Aldi    (2),
    Edeka   (3),
    Kaufhof (4),
    Penny   (5),
    Bauhaus (6),
    unknown (7)
    ;

    private final int code;

    Brands(int code) {
        this.code = code;
    }
}

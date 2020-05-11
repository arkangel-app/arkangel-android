package com.arkangel.arkangel.Clases;

public class Sentimiento {
    private static final Sentimiento ourInstance = new Sentimiento();
   public static String animo_id;
   public static String causa_id;
   public static  String mensaje;
    public static Boolean send_parent;

    public static Sentimiento getInstance() {
        return ourInstance;
    }

    private Sentimiento() {
    }
}

package transfer.util;

/**
 * Interface defining operation constants for various actions related to Zaposleni, Posao, and Zadatak.
 */
public interface Operation {
    
    // Login Operations
    public static final int LOGIN_MENADZER = 0;
    public static final int LOGIN_ZAPOSLENI = 1;
    
    // Zaposleni Operations
    public static final int ADD_ZAPOSLENI = 2;
    public static final int GET_ZAPOSLENI = 3;
    public static final int UPDATE_ZAPOSLENI = 4;
    public static final int DELETE_ZAPOSLENI = 5;
    public static final int GET_ALL_ZAPOSLENI = 6;

    // Zadatak Operations
    public static final int ADD_ZADATAK = 7;
    public static final int GET_ZADATAK = 8;
    public static final int UPDATE_ZADATAK = 9;
    public static final int DELETE_ZADATAK = 10;
    public static final int GET_ALL_ZADATAK = 11;

    // Posao Operations
    public static final int ADD_POSAO = 12;
    public static final int GET_POSAO = 13;
    public static final int UPDATE_POSAO = 14;
    public static final int DELETE_POSAO = 15;
    public static final int GET_ALL_POSAO = 16;
    
    // Menadzer Operations
    public static final int ADD_MENADZER = 17;
    public static final int GET_MENADZER = 18;
    public static final int UPDATE_MENADZER = 19;
    public static final int DELETE_MENADZER = 20;
    public static final int GET_ALL_MENADZER = 21;
    
    // Menadzer Operations
    public static final int ADD_ZAPOSLENJE = 22;
    public static final int DELETE_ZAPOSLENJE = 23;
    public static final int GET_ALL_ZAPOSLENJE = 24;
    
    // Menadzer Operations
    public static final int ADD_PRIPADNOSTZP = 25;
    public static final int DELETE_PRIPADNOSTZP = 26;
    public static final int GET_ALL_PRIPADNOSTZP = 27;
}

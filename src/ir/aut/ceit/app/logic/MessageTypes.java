package ir.aut.ceit.app.logic;

public class MessageTypes {
    /**
     * Version of communication protocol
     */
    public static final byte PROTOCOL_VERSION = 1;
    /**
     * Code of request login message
     */
    public static final byte BOOLEAN_TYPE = 0;
    public static final byte TEXT_MESSAGE = 1;
    public static final byte PLAYER_COORDINATION = 2;
    public static final byte NAME_MESSAGE = 3;
    public static final byte ACCEPT_MESSAGE = 4;
    public static final byte REJECT_MESSAGE = 5;
}
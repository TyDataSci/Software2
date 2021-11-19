package util;


public class ControllerTabState {
    private static String state = "isAllTab";

    public static String getState() {return state;
    }

    public static void setState(String newState) {
            state = newState;
    }
}


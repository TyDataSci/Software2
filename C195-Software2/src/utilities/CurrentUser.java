package utilities;

import dao.UserDAO;

public class CurrentUser {
    private static int currentUserID = 1;
    private static String currentUser;

    public static int getCurrentUserID() {
        return currentUserID;
    }
    public static String getCurrentUser() {
        return UserDAO.getUserMap().get(currentUserID);
    }

    public static void setCurrentUserID(int currentUserID) {
        CurrentUser.currentUserID = currentUserID;
    }

}

package emailApp.view;

public enum FontSize {
    SMALL,
    MEDIUM,
    LARGE;

    public static String getCssPath(FontSize fontSize) {
        switch (fontSize){
            case SMALL:
                return "Css/fontSmall.css";
            case MEDIUM:
                return "Css/fontMedium.css";
            case LARGE:
                return "Css/fontBig.css";
            default:
                return null;
        }
    }
}

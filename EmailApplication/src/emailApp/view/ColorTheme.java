package emailApp.view;

public enum ColorTheme {
    LIGHT,
    DEFAULT,
    DARK;

    public static String getCssPath(ColorTheme colorTheme){
        switch (colorTheme){
	        case LIGHT:
	            return "Css/themeLight.css";
	        case DEFAULT:
	            return "Css/themeDefault.css";
	        case DARK:
	            return "Css/themeDark.css";
	        default:
	            return null;
        }
    }
}

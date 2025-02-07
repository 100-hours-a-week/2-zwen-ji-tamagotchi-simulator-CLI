package system;

public class DeviceSetting {
    public static final String deviceStatrImage = """
            ⠀⠀⣠⡴⠶⠒⠶⢦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢐⡰⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⢠⡞⠋⠀⠀⠀⠀⠀⠙⢧⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠎⠁⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣄⣀⣀⠀⠀⠀
            ⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠋⠉⠈⠉⠉⠳⣦⠀
            ⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠋⠀⠀⢹⣀⠤⣀⠀⠀⠀⠀⣰⠏⠀⠀⠀⠀⠀⠀⠀⠘⣇
            ⠸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⡀⠀⠀⠀⠀⠀⠀⠀⠖⠀⠀⠀⠀⠀⢸⡀⡀⠀⠈⠁⠀⠈⢷⠀⠀⢰⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿
            ⠀⢻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⡄⠀⠀⠀⠀⠀⠰⢁⠀⡈⠢⠀⠀⠀⢳⡐⠀⠀⠀⣀⡤⠋⠀⢀⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡏
            ⠀⠀⠻⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⢦⣄⡀⠀⠀⠀⠀⠚⠀⠀⠀⠀⠀⠈⠧⠖⠋⠉⠁⠀⠀⢀⣾⠃⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠀
            ⠀⠀⠀⠘⢷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢙⠿⠖⠛⠛⠋⠉⠉⠙⠛⠓⠶⠤⣄⡀⠀⠀⠀⣀⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠁⠀
            ⠀⠀⠀⠀⠀⠘⠳⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠳⣤⠾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠟⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠈⠙⠳⢦⣄⣀⡠⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⠞⠁⠀⠀⠀⠀
            ⠀⠀⠀⠀⡴⠋⠉⠑⣆⠀⠀⢀⡟⠁⠀⠀⠊⣹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡤⠾⠋⠀⠀⠀⠀⠀⠀⠀
            ⣠⠚⠉⠲⠇⠀⠀⠀⢸⠀⠀⣼⠀⠠⠠⠀⠑⠊⠀⠀⠀⠀⣄⣀⠀⠀⡀⠀⠀⠀⠀⡌⡡⡇⠀⠈⢟⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⣇⠀⠀⠀⠀⠀⠀⢀⡟⠀⠀⢻⡀⠄⠀⠀⠨⠀⠀⠀⠀⠀⢸⡄⢀⡟⠀⠀⠀⠀⠀⠈⠒⠁⠀⠀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠈⠲⠤⣄⣀⡀⠀⡜⠀⠀⠀⠈⠳⣦⣾⠉⢳⡄⠀⠀⠀⠀⠀⠳⠊⠀⠀⠀⠀⠀⠀⠇⠀⠀⠀⢐⢸⠇⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠈⠉⠁⠀⠀⠀⠀⠀⠘⡇⠀⠀⠹⡦⢤⣤⣀⣀⣀⡀⠀⠀⠀⠀⢀⣤⠶⠶⣦⢀⣤⠏⠀⠀⢈⠀⣈⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠋⠉⠙⢿⡄⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠻⠋⠀⠀⢀⡟⠉⠀⠀⠀⠀⠀⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⣾⠁⠀⢀⠀⢀⡾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣤⡞⠁⠀⢠⢞⡛⡝⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⣇⠀⠀⡏⠀⢸⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⢿⢸⠰⣌⠻⡒⢖⢆⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠙⣆⡀⠈⠁⢻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⢦⣀⡿⠀⠀⠀⠘⡎⡓⢬⠱⣉⡮⣺⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠒⠒⠚⢷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠏⠀⠀⢻⠃⠀⠀⠀⠀⢷⠽⠶⠗⠓⠊⠁⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡆⠀⠉⣶⠦⠤⢤⣤⣤⠤⠴⢿⡀⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠲⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠉⠋⠉⠀⠀⠀⠀⠀⠀⠀⡠⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠠⢠⢣⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢍⢎⣱⠒⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠒⠣⠁⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠊⠐⠺⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""";


    /* Out Put Settings */
    public static final int CONSOLE_WIDTH = 48;
    public static final String CONSOLE_COLOR = "\u001B[36m";
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String DIM = "\u001B[2m";

    public static final String TOP_FRAME = "╭"+ "─".repeat(DeviceSetting.CONSOLE_WIDTH)+ "╮"+DeviceSetting.RESET;
    public static final String BOT_FRAME = "╰"+ "─".repeat(DeviceSetting.CONSOLE_WIDTH)+ "╯"+DeviceSetting.RESET;

    public static final String COMMAND_LINE_DESCRIPTION = "  ▶ %d : %s";
    public static final String COMMAND_SYSTEM_GUIDE ="      View pet.Pet's Status (s)          Quit(q)";

    public static final String LABEL_LEVEL= "   Lv.";
    public static final String LABEL_EXP=" ".repeat(24) + "Exp : ";
    public static final String LABEL_SICKNESS = "   아픔 : ";
    public static final String LABEL_SATISFACTION = "   배부른 정도 : ";
    public static final String LABEL_HAPPINESS = "   행복도 : ";
    public static final String LABEL_HATCH_PROCESS = "부화까지 %d 남음.";



    /* Pet Logic Params */
    public static final int EXP_INCREASE_MIN = 5;
    public static final int EXP_INCREASE_MAX = 15;

    public static final int STAT_INCREASE_MIN = 1;
    public static final int STAT_INCREASE_MAX = 10;


    /* System Message */
    public static final String STARTING_GUIDE = "press any button to start";
    public static final String MOVE_ON_GUIDE = " ".repeat(48)+ "enter ▼\n";


    public static final String COMMAND_FORMAT_DENY = "유효하지 않은 입력!";
    public static final String COMMAND_BOUND_DENY = "유효하지 않은 입력!";

    public static final String SHUT_DOWN_NORMALLY = "디바이스를 정상적으로 종료합니다.";
    public static final String SHUT_DOWN_ERROR = "디바이스 종료 중 오류가 발생했습니다: ";

    public static  int INDEX_FIXER(int num){
        return num-1;
    }
}
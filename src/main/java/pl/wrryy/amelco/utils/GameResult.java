package pl.wrryy.amelco.utils;

public class GameResult {

    public static int getHomeResult(double result){
    String home = Double.toString(result).split("\\.")[0];
        return Integer.valueOf(home);
    }
    public static int getAwayResult(double result){
    String home = Double.toString(result).split("\\.")[1];
        return Integer.valueOf(home);
    }
    public static double getGameResult(int home, int away){
        return Double.valueOf(home+"" + "." +away+"");
    }

}

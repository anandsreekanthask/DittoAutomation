package utils;

public class NumberFormatter {

    public String getCleansedCover(String cover){

        return cover.replaceAll("[^0-9]", "");

    }


}

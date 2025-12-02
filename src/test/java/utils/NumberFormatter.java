package utils;

public class NumberFormatter {

    /**
     * The utility is used to cleanse insurance cover value and return the digit
     * @param cover the base cover captured from UI ex: 50 L
     * @return cleansed number from the cover ex: 50
     */
    public String getCleansedCover(String cover){

        return cover.replaceAll("[^0-9]", "");

    }

    /**
     * The utility is used to cleanse the premium amount to get rid of currency and commas
     * @param amount the premium captured from UI ex: $1,200.45
     * @return cleansed premium amount ex: 1200.45
     */
    public String getCleansedPremium(String amount){

        return amount.replaceAll("[₹,\\s]", "");

    }


}

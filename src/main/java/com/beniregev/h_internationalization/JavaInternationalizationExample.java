package com.beniregev.h_internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

public class JavaInternationalizationExample {
    public static void main(String[] args) {
        //default locale
        ResourceBundle bundle = ResourceBundle.getBundle("ApplicationMessages");
        //Get ResourceBundle with Locale that are already defined
        ResourceBundle bundleFR = ResourceBundle.getBundle("ApplicationMessages", Locale.FRANCE);
        //Get resource bundle when Locale needs to be created
        ResourceBundle bundleIL = ResourceBundle.getBundle("ApplicationMessages", new Locale("he", "IL"));
        ResourceBundle bundleSWE = ResourceBundle.getBundle("ApplicationMessages", new Locale("sv", "SE"));

        //lets print some messages
        printMessages(bundle);
        printMessages(bundleFR);
        printMessages(bundleIL);
        printMessages(bundleSWE);

    }

    private static void printMessages(ResourceBundle bundle) {
        System.out.println(bundle.getString("CountryName"));
        System.out.println(bundle.getString("CurrencyCode"));
    }

}

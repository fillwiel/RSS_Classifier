package com.rssreader.reader;

import com.rssreader.core.models.entities.RssFeed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Reader {
    static RssFeed rssFeed = new RssFeed();

    public static void main(String[] args) {
        System.out.println(readRSS("http://feeds.feedburner.com/dobreprogramy/Aktualnosci"));
        printLocalRssFeed();
        //SpringApplication.run(RssReader.class, args);
    }
    public static void rssGo() {
        System.out.println(readRSS("http://feeds.feedburner.com/dobreprogramy/Aktualnosci"));
        printLocalRssFeed();
    }
    // iterates through rssFeed and prints array list to the console
    private static void printLocalRssFeed() {
        /* fancy java 8 iteration , maybe applicable later
        rssFeed.getTitles().forEach((temp) -> {

            System.out.println(temp);
        });
        */

        for (int i = 0; i < rssFeed.getTitles().size() ; i++){
            System.out.println(rssFeed.getTitles().get(i));
            System.out.println("============");
            System.out.println(rssFeed.getDescriptions().get(i));
            System.out.println("------------------------------------------------------");
        }
    }

    public static String readRSS(String urlAddress) {
        try {
            URL rssUrl = new URL(urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String sourceCode = "";
            String line, tempDescriptionString;
            while ((line = in.readLine()) != null) {
                if (line.contains("<title>")) {
                    // old code adding string to print output
                    int firstPos = line.indexOf("<title>");
                    String temp = line.substring(firstPos);
                    temp = temp.replace("<title>", "");
                    int lastPos = temp.indexOf("</title>");
                    temp = temp.substring(0, lastPos);
                    sourceCode += temp + "\n";

                    // new - append RssFeed title list
                    rssFeed.addTitle(temp);
                }
                else if(line.contains("<description>")){
                    int firstPos = line.indexOf("<description>");
                    String temp = line.substring(firstPos);
                    temp = temp.replace("<description>", "");
                    if(line.contains("</description>")) {
                        int lastPos = temp.indexOf("</description>");
                        temp = temp.substring(0, lastPos);
                        rssFeed.addDescription(temp);
                    }
                    else {
                        tempDescriptionString = temp + "\n";
                        while ((line = in.readLine()) != null) {
                            if(!line.contains("</description>")) {
                                tempDescriptionString += line + "\n";
                            }
                            else{
                                int lastPos = line.indexOf("</description>");
                                temp = line.substring(0, lastPos);
                                tempDescriptionString += temp;
                                rssFeed.addDescription(tempDescriptionString);
                                break;
                            }
                        }
                    }

                }
            }
            in.close();
            return sourceCode;
        } catch(MalformedURLException ue){
            System.out.println("Malformed URL");
        } catch (IOException ioe){
            System.out.println("Somethning went wrong while reading the content. IOException");
        }
        return null;
    }
    //reads RssFeed from Url
    public static RssFeed readRssFeed(String urlAddress) {
        try {
            URL rssUrl = new URL(urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String sourceCode = "";
            String line, tempDescriptionString;
            while ((line = in.readLine()) != null) {
                if (line.contains("<title>")) {
                    // old code adding string to print output
                    int firstPos = line.indexOf("<title>");
                    String temp = line.substring(firstPos);
                    temp = temp.replace("<title>", "");
                    int lastPos = temp.indexOf("</title>");
                    temp = temp.substring(0, lastPos);
                    sourceCode += temp + "\n";

                    // new - append RssFeed title list
                    rssFeed.addTitle(temp);
                }
                else if(line.contains("<description>")){
                    int firstPos = line.indexOf("<description>");
                    String temp = line.substring(firstPos);
                    temp = temp.replace("<description>", "");
                    if(line.contains("</description>")) {
                        int lastPos = temp.indexOf("</description>");
                        temp = temp.substring(0, lastPos);
                        rssFeed.addDescription(temp);
                    }
                    else {
                        tempDescriptionString = temp + "\n";
                        while ((line = in.readLine()) != null) {
                            if(!line.contains("</description>")) {
                                tempDescriptionString += line + "\n";
                            }
                            else{
                                int lastPos = line.indexOf("</description>");
                                temp = line.substring(0, lastPos);
                                tempDescriptionString += temp;
                                rssFeed.addDescription(tempDescriptionString);
                                break;
                            }
                        }
                    }

                }
            }
            in.close();
            return rssFeed;
        } catch(MalformedURLException ue){
            System.out.println("Malformed URL");
        } catch (IOException ioe){
            System.out.println("Somethning went wrong while reading the content. IOException");
        }
        return null;
    }
}

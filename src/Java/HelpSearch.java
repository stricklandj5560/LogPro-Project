//Jonathan Strickland
//LogPro Project
//Completed on 02/10/2020

package Java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class HelpSearch {



    /** Return a list of search results based on the passed data. */
    public static ArrayList<String[]> getSearchResults(String search, String userType) {

        ArrayList<String []> fullList = getListOfHelpResults(); //Get a list of all help results.
        ArrayList<String[]> returnList = new ArrayList<String[]>();

        //User types: admin, student, and instructor.
        if(userType.equals("admin")) { //Display admin search results.

            //Get rid of all the other datatype help results.
            for(int i = 0; i < fullList.size(); i++) {

                if(fullList.get(i)[0].equals("0") || fullList.get(i)[0].equals("1")) { //If the user type is not an admin, remove the result.

                    fullList.remove(i);
                    i--; //Compensate for the item removed.

                }

            }


        } else if(userType.equals("instructor")) { //Display instructor search results.


            for(int i = 0; i < fullList.size(); i++) {

                if(fullList.get(i)[0].equals("0") || fullList.get(i)[0].equals("2")) { //If the user type is not an instructor, remove the result.

                    fullList.remove(i);
                    i--; //Compensate for the item removed.

                }

            }


        } else { //Display student search results.

            for(int i = 0; i < fullList.size(); i++) {

                if(fullList.get(i)[0].equals("1") || fullList.get(i)[0].equals("2")) {//If the user type is not a student, remove the result.

                   fullList.remove(i);
                   i--; //Compensate for the item removed.

                }

            }


        }

        //Filter by key words.

        for(String s[] : fullList) {

            boolean added = false; //Array has not been added to returnlist.

            if(!(search.trim().equals(""))) { //If the search has text, look for key words.

                String[] keyWords = s[3].split("%"); //Get a list of key words for the search.

                for (int i = 0; i < keyWords.length; i++) { //Loop through the keywords of the line.

                    //Look through the passed search and try to find matching keywords.

                    if (search.toLowerCase().contains(keyWords[i])) { //Search contains a keyword, add result to returnlist.

                        if(!added) { //if string has not already been added.

                            returnList.add(s);
                            added = true;

                        }

                    }
                }

            } else { //add all of the results.

                returnList.add(s);

            }

        }

        return returnList;

    }


    public static ArrayList<String[]> getListOfHelpResults() {

        ArrayList<String[]> returnList = new ArrayList<String[]>();

        String helpFile = "";

        if(new OperatingSystem().isMac) { //Mac uses "/"; Windows uses "\".

            helpFile = "src/HelpData/QA.txt";

        } else {

            helpFile = "src\\HelpData\\QA.txt";

        }


        FileTool ft = new FileTool(helpFile);

        //Get the help file as a list.
        ArrayList<String> helpData = ft.getFileAsList();


        for(String s : helpData) { //Loop through the file and split each line into an array.

            returnList.add(s.split("\\*")); //regex: "*"

        }

        return returnList;

    }




}

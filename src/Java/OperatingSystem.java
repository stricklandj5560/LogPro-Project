package Java;

public class OperatingSystem {


    public boolean isWindows;
    public boolean isMac;

    private String osName = null;


    /**
     * Determines the operating system of the computer running the program.
     */
    public OperatingSystem() {

        osName = System.getProperty("os.name");

        try {

            if (osName.startsWith("Windows")) { //If the OS is Windows.

                isWindows = true;
                isMac = false;

            } else {

                isMac = true;
                isWindows = false;


            }

        } catch(Exception e) {

            e.printStackTrace();

        }
    }
}


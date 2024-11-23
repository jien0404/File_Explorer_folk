/*
     This project specially focused on an academic purpose only.
*/
/*
    Project Name: File Manager
    Developed By:-  Md. Rabiul Islam && Md. Mahmudul Hasan
                   Student ID : 200234 && 200229
*/
package filemanager;
/*
    This project is to build a system that can show the all files and folders
    in a computer systems. Those file and folder are catagorized as partition.
    User should navigate all the directories and open those file and directories.
    User also can do the following operation on file and folders such as (copy, 
    move, paste,delete and rename;
    Ordering of the file in ascending and descending also done.
    For eye care, it has the feature of night and day mode.
    Directory path for a specific folder is also shown in above.
*/
public class FileManager {
    public static void main(String[] args) {
      MainFrame mainFrame = new MainFrame();
      mainFrame.mainStart();                                           //Program starts from here
    }  
}
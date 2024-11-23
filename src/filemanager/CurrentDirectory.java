/*
    All action regarding to current directory occurs here.
    60% of the total project work done here.
*/
package filemanager;

import java.io.*;

class CurrentDirectory {
    static String  currentDirectoryName="/";
    static String[] allCurrentContentNames;
    static int folderOrFile[];
    static File currentFile;
    static String sourceFile;
    static String destinationFile;
    static String onlyFileName;
    static int setSort=0;
    
    /*
        this function make a copy of the orginal file and move to the destination
        dierctories or file
    */
    static void copySourceToDestination(File sourceLocation , File targetLocation)
    throws IOException {

        if (sourceLocation.isDirectory()) {
            targetLocation.mkdir();
            String[] children = sourceLocation.list();
            for (int i=0; i<children.length; i++) {
                copySourceToDestination(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } 
        else {
            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);
            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }
    
    //sub delete function of orginal delete function
    static boolean deleteFileNew(File deleteFilename) throws IOException {
        if(deleteFilename.isDirectory()) {
            String[] children = deleteFilename.list();
            for(int i=0; i<children.length; i++) {
                deleteFileNew(new File(deleteFilename,children[i]));
            }
            return deleteFilename.delete();
        }
        else {
            return deleteFilename.delete();
        }
    }
    
    //set where a copied or moved file to place;
    static void setDestinationFile() {
        if(currentDirectoryName.equals("/")) destinationFile = currentDirectoryName+onlyFileName;
       else destinationFile = currentDirectoryName+"/"+onlyFileName;
    }
    
    /*
        When copy or move action occur souce file path is set in below function
    */
    static void setSourceFile(int index) {
       onlyFileName = allCurrentContentNames[index];
       if(currentDirectoryName.equals("/")) sourceFile = currentDirectoryName+allCurrentContentNames[index];
       else sourceFile  = currentDirectoryName+"/"+allCurrentContentNames[index];
       System.out.println(sourceFile);
    }
    
    /*
        creating new directory with built in function. Operating system does not 
        give acces directly to create new directories.
    */
    static boolean createNewDirectory(String newDirectoryName){
        if(currentDirectoryName.equals("/")){
            newDirectoryName = currentDirectoryName+newDirectoryName;
        }
        else newDirectoryName  = currentDirectoryName+"/"+newDirectoryName;
        File newDirectory = new File(newDirectoryName);
        boolean x1 =  newDirectory.mkdir();
        CenterPanel.jPanel.removeAll();
        CenterPanel.refreshPanelContent();
        return x1;
        
    }
    
    /*
        if it is folder or derectory then recursively delete folder and it's 
        subfolder with file else just delete the file and return
    */
    static boolean deleteFile(int index) {
        String fileName3;
        boolean x = false;
        if(currentDirectoryName.equals("/")) {
            fileName3 = currentDirectoryName+allCurrentContentNames[index];
        }
        else {
            fileName3 = currentDirectoryName+"/"+allCurrentContentNames[index];
        }
        File f1 = new File(fileName3);
        try{
             x = deleteFileNew(f1);
        }
        catch(IOException ioe){
            System.out.println("An error occur on deletefilenew Fucntion.");
        }
        return x;
    }
    
    /*
        set parent directory of a child directory. It uses (/) as separator to
        find parent directory relative path from child directory relative path
    */
    static void setBackDirectoryName() {
        String tempDirectory="";
        int len = currentDirectoryName.length();
        for(int i=len-1; i>=0; i--) {
            if(currentDirectoryName.charAt(i)=='/') {
                for(int j=0; j<i; j++) {
                    tempDirectory+=currentDirectoryName.charAt(j);
                }
                break;
            }
        }
        if(tempDirectory.equals("")) currentDirectoryName = "/";
        else currentDirectoryName = tempDirectory;
    }
    
    //set next directory name as current directory
    static void setCurrentDirectoryName(String newCurrentDirectoryName) {
        if(currentDirectoryName.equals("/")) {
               currentDirectoryName = currentDirectoryName+newCurrentDirectoryName;
        }
        else {
                currentDirectoryName = currentDirectoryName+"/"+newCurrentDirectoryName;
        }
    }
    
    //set partition directory as current directory
    static void setDirectoryName(String partition) {
        currentDirectoryName = partition;
    }
    
    //find all content of current directory
    static void findAllContentName() {
        currentFile = new File(currentDirectoryName);
        allCurrentContentNames = currentFile.list();   
            if(setSort==1){
            int len=allCurrentContentNames.length;
            String arr[]= currentFile.list();
            int j=0;
            for(int i=len-1;i>=0;i--){
                arr[j]=allCurrentContentNames[i];
                j++;
                
            }
            for(int i=0;i<len;i++){
                allCurrentContentNames[i]=arr[i];
                System.out.println(allCurrentContentNames[i].length());
            }
            
        }else if(setSort==2){
            int len=allCurrentContentNames.length;
            String arr[]= currentFile.list();
            for(int i=0;i<len;i++){
                for(int j=i+1;j<len;j++){
                    if(allCurrentContentNames[i].length()>allCurrentContentNames[j].length()){
                        arr[i]=allCurrentContentNames[j];
                        allCurrentContentNames[j]=allCurrentContentNames[i];
                        allCurrentContentNames[i]=arr[i];
                    }
                }
            }
        }
    }
    
    //decide each current directory content file or folder
    static void findAllContentFolderOrFile() {
       String fileName2;
       int len = allCurrentContentNames.length;
       folderOrFile = new int[len];
       for(int i=0; i<len; i++) {
           if(currentDirectoryName.equals("/")) {
                fileName2 = currentDirectoryName+allCurrentContentNames[i];
            }
            else {
                fileName2 = currentDirectoryName+"/"+allCurrentContentNames[i];
            }
            File f1 = new File(fileName2);
           if( f1.isDirectory()==true) folderOrFile[i] = 1;
           else folderOrFile[i] = 0;
        }
    }
}

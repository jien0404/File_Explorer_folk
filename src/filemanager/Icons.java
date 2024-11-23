/*
    Creation of this class is to easier the operation of all icon and image.
    Finding extension is also it's parts.
*/
package filemanager;

import javax.swing.ImageIcon;


class Icons {
    //Including all neccessary imageicon from the icons folder for further use
    private ImageIcon bmpIcon = new ImageIcon("icons/bmp.png");
    private ImageIcon csvIcon = new ImageIcon("icons/csv.png");
    private ImageIcon docIcon = new ImageIcon("icons/doc.png");
    private ImageIcon docxIcon = new ImageIcon("icons/docx.png");
    private ImageIcon folderIcon = new ImageIcon("icons/folder.png");
    ImageIcon folder2Icon = new ImageIcon("icons/folder2.png");
    private ImageIcon gifIcon = new ImageIcon("icons/gif.png");
    private ImageIcon htmlIcon = new ImageIcon("icons/html.png");
    private ImageIcon jpgIcon = new ImageIcon("icons/jpg.png");
    private ImageIcon mp3Icon = new ImageIcon("icons/mp3.png");
    private ImageIcon mp4Icon = new ImageIcon("icons/mp4.png");
    private ImageIcon pdfIcon = new ImageIcon("icons/pdf.png");
    private ImageIcon pptIcon = new ImageIcon("icons/ppt.png");
    private ImageIcon psdIcon = new ImageIcon("icons/psd.png");      
    private ImageIcon rarIcon = new ImageIcon("icons/rar.png");
    private ImageIcon txtIcon = new ImageIcon("icons/txt.png");
    private ImageIcon xlsIcon = new ImageIcon("icons/xls.png");
    private ImageIcon zipIcon = new ImageIcon("icons/zip.png");
    private ImageIcon genIcon = new ImageIcon("icons/gen.png");
    ImageIcon listIcon = new ImageIcon("icons/list.png");
    ImageIcon gridIcon = new ImageIcon("icons/grid.png");
    ImageIcon backIcon = new ImageIcon("icons/back.png");
    ImageIcon deleteIcon = new ImageIcon("icons/delete.png");
    ImageIcon localDisk = new ImageIcon("icons/localdisk.png");
    ImageIcon cdDrive = new ImageIcon("icons/cddrive.png");
    ImageIcon usbDrive = new ImageIcon("icons/usbdrive.png");
    ImageIcon mainFramIcon = new ImageIcon("icons/main.png");
    
    /*
        This function extract the file extension from a file name.
        uses (.) to separate the file name and it's extension.
    */
    String findExtension(String fileName) {
        int fileNameLength = fileName.length(),indicator=0;
        String extens="";
       // System.out.println(fileNameLength + " "+fileName);
        for(int i=fileNameLength-1; indicator==0 && i>=0 ;i--) {
            if(fileName.charAt(i)=='.') {
                for(int j=i+1; j<fileNameLength; j++) {
                    extens+=fileName.charAt(j);
                }
                indicator++;
            }
        }
        return extens;
    }
    /*
        This function return imageicon according to file extensions
    */
    ImageIcon getImageIcon(int index) {
        if(CurrentDirectory.folderOrFile[index]==1) {
            return folderIcon;
        }
        else{
            String extension;
            extension = findExtension(CurrentDirectory.allCurrentContentNames[index]);
            if(extension.equals("bmp")){
                return bmpIcon;
            }
            else if(extension.equals("csv")) {
                return csvIcon;
            }
            else if(extension.equals("doc")) {
                return docIcon;
            }
            else if(extension.equals("docx")) {
                return docxIcon;
            }
            else if(extension.equals("folder")) {
                return folderIcon;
            }
            else if(extension.equals("folder2")) {
                return folder2Icon;
            }
            else if(extension.equals("gif")) {
                return gifIcon;
            }
            else if(extension.equals("html")) {
                return htmlIcon;
            }
            else if(extension.equals("jpg")) {
                return jpgIcon;
            }
            else if(extension.equals("mp3")) {
                return mp3Icon;
            }
            else if(extension.equals("mp4")) {
                return mp4Icon;
            }
            else if(extension.equals("pdf")) {
                return pdfIcon;
            }
            else if(extension.equals("ppt")) {
                return pptIcon;
            }
            else if(extension.equals("psd")) {
                return psdIcon;
            }
            else if(extension.equals("rar")) {
                return rarIcon;
            }
            else if(extension.equals("txt")) {
                return txtIcon;
            }
            else if(extension.equals("xls")) {
                return xlsIcon;
            }
            else if(extension.equals("zip")) {
                return zipIcon;
            }
            else {
                return genIcon;
            }
        }
    }
    /*
        This function return imageicon according to partition type name
    */
    ImageIcon getImageIconForPartition(String type){
        if(type.equals("Local Disk")){
            return localDisk;
        }
        else if(type.equals("CD Drive")) {
            return cdDrive;
        }
        else if(type.equals("USB Drive")){
            return usbDrive;
        }
        return localDisk;
    }
}

/*
    All of the popup action and methods for each file or folder 
    is found here.
*/
package filemanager;

import java.awt.Desktop;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public class PopupMenu implements ActionListener {
    
    int index2;
    static int moveStatus=-1,moveFlag=0;
    Icons icon = new Icons();
    JPopupMenu popUp = new JPopupMenu();
    JMenuItem open = new JMenuItem("Open");
    JMenuItem delete = new JMenuItem("Delete");
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem close = new JMenuItem("Close");
    JMenuItem move = new JMenuItem("Move");
    JMenuItem rename = new JMenuItem("Rename   ");
    
    //pop up menu showinge function. also sensitive to night mode
    void showPopUpMenu(DirectoryLabel m,int x, int y,int i) {
       if(MainFrame.modeStatus==0) {
           popUp.show(m, x, y);
           popUp.setBackground(Color.white);
           open.setBackground(Color.white);
           copy.setBackground(Color.white);
           move.setBackground(Color.white);
           delete.setBackground(Color.white);
           close.setBackground(Color.white);
           rename.setBackground(Color.white);
           popUp.setForeground(Color.black);
           open.setForeground(Color.black);
           copy.setForeground(Color.black);
           move.setForeground(Color.black);
           delete.setForeground(Color.black);
           close.setForeground(Color.black);
           rename.setForeground(Color.black);
       }
       else {
           popUp.show(m, x, y);
           popUp.setBackground(Color.black);
           open.setBackground(Color.black);
           copy.setBackground(Color.black);
           move.setBackground(Color.black);
           delete.setBackground(Color.black);
           close.setBackground(Color.black);
           rename.setBackground(Color.black);
           
           popUp.setForeground(Color.white);
           open.setForeground(Color.white);
           copy.setForeground(Color.white);
           move.setForeground(Color.white);
           delete.setForeground(Color.white);
           close.setForeground(Color.white);
           rename.setForeground(Color.white);
       }
        index2 = i;
    }
    /*
        Different type of action in popupmenu is done here based on user 
        Choice.
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==open) {
            if(CurrentDirectory.folderOrFile[index2]==1) {
                //open file if it is directory
                CenterPanel.jPanel.removeAll();
                CurrentDirectory.setCurrentDirectoryName(CurrentDirectory.allCurrentContentNames[index2]);
                MainFrame.refreshTextpathDirectory();
                CenterPanel.refreshPanelContent();
            }
            else {
                //open file if it is ordinary readable file
                String fileName = CurrentDirectory.currentDirectoryName;
                if(fileName.equals("/")) fileName = fileName+CurrentDirectory.allCurrentContentNames[index2];
                else fileName=fileName+"/"+CurrentDirectory.allCurrentContentNames[index2];
                File f1 = new File(fileName);
                try {
                    Desktop.getDesktop().open(f1);
                }
                catch(Exception ev) {
                    System.out.println("File can't be open");
                }
            }
        }
        else if(e.getSource()==copy) {
            moveStatus = 0;
            CurrentDirectory.setSourceFile(index2);         //setting the path that file to copy
        }
        else if(e.getSource()==move) {
            
            moveStatus = 1;
            moveFlag = 1;
            CurrentDirectory.setSourceFile(index2);         //setting the path that file to move
        }
        else if(e.getSource()==delete) {
            //working on file deletation
            int confirmDelete = JOptionPane.showConfirmDialog(CenterPanel.jPanel,"Sure To Delete \""+CurrentDirectory.allCurrentContentNames[index2]+"\"?\n You can't recover this again." , "Warning...!!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon.getImageIcon(index2));
                    if(confirmDelete == JOptionPane.YES_OPTION) {
                        if(CurrentDirectory.deleteFile(index2)){
                            //recursive deletation function calls from here in condintion of the if
                            JOptionPane.showMessageDialog(CenterPanel.jPanel,"\""+CurrentDirectory.allCurrentContentNames[index2]+"\" deleted succesfully." , "Deleted..!!", JOptionPane.PLAIN_MESSAGE, icon.getImageIcon(index2));
                            CenterPanel.jPanel.removeAll();
                            CenterPanel.refreshPanelContent();
                        }
                        else  JOptionPane.showMessageDialog(CenterPanel.jPanel,"\""+CurrentDirectory.allCurrentContentNames[index2]+"\" can't deleted." , "Failed..!!", JOptionPane.PLAIN_MESSAGE, icon.getImageIcon(index2));
                    }
        }
        else if(e.getSource()==rename) {
             if(CurrentDirectory.folderOrFile[index2]==0){
                 //work on file renaming
                 String newFileName = (String) JOptionPane.showInputDialog(CenterPanel.jPanel,"Enter new file name for \n\""+CurrentDirectory.allCurrentContentNames[index2]+"\"", "Renaming..!!",JOptionPane.PLAIN_MESSAGE,icon.getImageIcon(index2),null,null);
                 String fileExtension = icon.findExtension(CurrentDirectory.allCurrentContentNames[index2]);
                 File f1 = new File(CurrentDirectory.currentFile,CurrentDirectory.allCurrentContentNames[index2]);
                 f1.renameTo(new File(CurrentDirectory.currentFile,newFileName+"."+fileExtension));
                 
             }
             else {
                 //work on folder renaming
                 String newFolderName = (String) JOptionPane.showInputDialog(CenterPanel.jPanel,"Enter new folder name for \n\""+CurrentDirectory.allCurrentContentNames[index2]+"\"", "Renaming..!!",JOptionPane.PLAIN_MESSAGE,icon.folder2Icon,null,null);
                  File f1 = new File(CurrentDirectory.currentFile,CurrentDirectory.allCurrentContentNames[index2]);
                 f1.renameTo(new File(CurrentDirectory.currentFile,newFolderName));
             }
            CenterPanel.jPanel.removeAll();
            CenterPanel.refreshPanelContent(); 
        }
        else if(e.getSource()==close) {
            //null
        }
    }
}

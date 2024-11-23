/*
    All of the object and functionalities of this classs is to add on the 
    westPanel of the MainFrame content pane.
*/

package filemanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class WestPanel extends JPanel implements MouseListener,ActionListener {
    
    static JButton backButton = new JButton();
    static JButton thisPcButton =new JButton();
    JPopupMenu eastPopupMenu = new JPopupMenu();
    static JMenuItem neww = new JMenuItem("Create New Folder");
    static JMenuItem paste = new JMenuItem("Paste");
    Icons icon = new Icons();
    
    /*
        This funcition add all initial elements in west panel of borderlayout 
        of main Frame main content pane.
    */
    
    void initialElementToWestPanel(){
        MainFrame.mainContainer.add(this,MainFrame.bLayout.WEST);
        addMouseListener(this);
        this.setBackground(Color.white);
        backButton.setIcon(icon.backIcon);
        backButton.setText("Back");
        backButton.addActionListener(this);
        this.add(backButton);
        neww.addActionListener(this);
        paste.addActionListener(this);
        eastPopupMenu.add(neww);
        eastPopupMenu.add(paste);
        this.add(backButton);
        MainFrame.mainContainer.revalidate();
        MainFrame.mainContainer.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==backButton) {
           /*
                This button is works only to set the directory of parent for 
                a child directory
           */
            if(!(CurrentDirectory.currentDirectoryName.equals("/"))){
                CenterPanel.jPanel.removeAll();
                CurrentDirectory.setBackDirectoryName();
                MainFrame.refreshTextpathDirectory();
                CenterPanel.refreshPanelContent();
            }
        }
       else if(e.getSource()==neww) {
           /*
                this condition blocks is for creating new directory first taking
                new directory name and then creating new directory with the user
                given name.
           */
           String newDirectoryName = (String) JOptionPane.showInputDialog(this,"Enter new folder name", "Create New Folder",JOptionPane.PLAIN_MESSAGE,icon.folder2Icon,null,null);
           if(newDirectoryName!=null) {
               CurrentDirectory.createNewDirectory(newDirectoryName);
           }
       }
       else if(e.getSource()==paste){
           /*
                Paste operation works for both move and copy;
                But both work is same.
           */
           if(PopupMenu.moveStatus==0) {
                CurrentDirectory.setDestinationFile();
                try{
                    CurrentDirectory.copySourceToDestination(new File(CurrentDirectory.sourceFile),new File(CurrentDirectory.destinationFile));
                }
                catch(IOException ioe) {
                    System.out.println("An system error occur.");
                }
                CenterPanel.jPanel.removeAll();
                CenterPanel.refreshPanelContent();
           }
           else if(PopupMenu.moveStatus==1 && PopupMenu.moveFlag ==1) {
               /*
                    Move operation is diveded in two operation internally. 
                    1) First doing copy operation from source to destination
                    2) Secondly delete the source duplicate file 
               */
               
               //copying the orginale file 
                CurrentDirectory.setDestinationFile();
                try{
                    CurrentDirectory.copySourceToDestination(new File(CurrentDirectory.sourceFile),new File(CurrentDirectory.destinationFile));
                }
                catch(IOException ioe) {
                    System.out.println("An system error occur.");
                }
                CenterPanel.jPanel.removeAll();
                CenterPanel.refreshPanelContent();
                
               //perform delete operation on source file
               try{
                   CurrentDirectory.deleteFileNew(new File(CurrentDirectory.sourceFile));
               }
               catch(IOException ioe){
                   System.out.println("An occur on deleteFileNew function.");
               }
               PopupMenu.moveFlag = 0;
           } 
       }
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        if(e.getClickCount()==1 && SwingUtilities.isRightMouseButton(e)) {
            /*
                Showing popup menu that have 'paste' and 'create new folder' option.
                Condition is based on day or night mode.
            */
            if(MainFrame.modeStatus==0) {
                eastPopupMenu.show(this,e.getX(),e.getY());
                neww.setBackground(Color.white);
                neww.setForeground(Color.black);
                paste.setBackground(Color.white);
                paste.setForeground(Color.black);
            }
            else {
                eastPopupMenu.show(this,e.getX(),e.getY());
                neww.setBackground(Color.black);
                neww.setForeground(Color.white);
                paste.setBackground(Color.black);
                paste.setForeground(Color.white);
                
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
        
    }
    
    @Override
    public void mouseEntered(MouseEvent e){
        
    }
    
    @Override
    public void mouseExited(MouseEvent e){
        
    }
}


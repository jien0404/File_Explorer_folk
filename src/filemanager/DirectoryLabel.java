/*
    Include all properties to show a file or directory in front of user
    and simple methods with mouse listener
*/
package filemanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

 public class DirectoryLabel extends JLabel implements MouseListener {
     
    int index;
    int rightClicked=0;
    PopupMenu pm = new PopupMenu();
    
    //First constructor only initilize with jLabel's constructor with a filename
    DirectoryLabel(String fileName) {
        super(fileName);
    }
    
    //second constructor specially need in gridview
    DirectoryLabel(String fileName,ImageIcon img, int x) {
        super(fileName,img,x);
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        
    }
    @Override
    public void mouseReleased(MouseEvent e){
        
    }
    @Override
    public void mouseClicked(MouseEvent e){
        if(e.getClickCount()==2 && SwingUtilities.isLeftMouseButton(e)) {
            if(CurrentDirectory.folderOrFile[index]==1) {
                                                                     //open folder or dircetory on double click
                CenterPanel.jPanel.removeAll();
                CurrentDirectory.setCurrentDirectoryName(CurrentDirectory.allCurrentContentNames[index]);
                MainFrame.refreshTextpathDirectory();
                CenterPanel.refreshPanelContent();
            }
            else{
                                                                      //open ordinary file on double click
                String fileName = CurrentDirectory.currentDirectoryName;
                if(fileName.equals("/")) fileName = fileName+CurrentDirectory.allCurrentContentNames[index];
                else fileName=fileName+"/"+CurrentDirectory.allCurrentContentNames[index];
                File f1 = new File(fileName);
                try {
                    Desktop.getDesktop().open(f1);
                }
                catch(Exception ev) {
                    System.out.println("File can't be open");
                }
            }
        }
        else if(e.getClickCount()==1 && SwingUtilities.isLeftMouseButton(e)){
            rightClicked = 1;
            //System.out.println("left click.");
        }
        else if(e.getClickCount()==1 && SwingUtilities.isRightMouseButton(e) && rightClicked==1) {
            pm.popUp.add(pm.open);
            pm.popUp.addSeparator();
            pm.popUp.add(pm.rename);
            pm.popUp.add(pm.copy);
            pm.popUp.add(pm.move);
            pm.popUp.addSeparator();
            pm.popUp.add(pm.delete);
            pm.popUp.addSeparator();
            pm.popUp.add(pm.close);   
            pm.open.addActionListener(pm);
            pm.copy.addActionListener(pm);
            pm.move.addActionListener(pm);
            pm.delete.addActionListener(pm);
            pm.close.addActionListener(pm);
            pm.rename.addActionListener(pm);
            pm.showPopUpMenu(this,e.getX(), e.getY(),index);
            //System.out.println("Popup triggered.");
        }
    }
    @Override
    public void mouseEntered(MouseEvent e){
                                            //adding hover effect only background change to red color
        this.setBackground(Color.red);
        this.setOpaque(true);
        this.revalidate();
        this.repaint();
    }
    @Override
    public void mouseExited(MouseEvent e){
                                                        //stoping hover effect 
        this.rightClicked = 0;
        this.setOpaque(false);
        this.revalidate();
        this.repaint();
    }
}
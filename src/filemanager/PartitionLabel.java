/*
    Functionality about a partition and its require information and action 
    performed on popupmenu is here.
    Also the byte to GB,MB,KB and TB method presented here.
*/
package filemanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class PartitionLabel extends JLabel implements MouseListener,ActionListener{
     
    int rightClicked;
    long totalSpace,freeSpace;
    
    JPopupMenu jpm = new JPopupMenu();
    JMenuItem open = new JMenuItem("Open");
    JMenuItem details = new JMenuItem("Details");
    Icons im = new Icons();
    String partitionName,partitionType;
    
    //initialize partitio name, type, total space and free space
    PartitionLabel(String pName,String pType, ImageIcon img,long tSpace, long fSpace,int x) {
        super(pName+" "+pType,img,x);
        partitionName = pName;
        partitionType = pType;
        totalSpace = tSpace;
        freeSpace = fSpace;
    }
    
    //takes byte as a number and convert it to appropiate KB, MB, GB, or in TB
    String byteToSize(long x) {
        String xy="";
        if(x>1024) {
            x=x/1024; //KB
            if(x>1024) {
                x=x/1024; //MB
                if(x>1024) {
                    x = x/1024; //GB
                    xy+=' ';
                    xy+=x;
                    xy+=" GB ";
                    return xy;
                }
                xy+=' ';
                xy+=x;
                xy+=" MB ";
                return xy;
                
            }
            xy+=' ';
            xy+=x;
            xy+=" KB ";
            return xy;
        }
        xy+=' ';
        xy+=x;
        xy+=" B ";
        return xy;
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
             CenterPanel.jPanel.removeAll();
             CurrentDirectory.setDirectoryName(partitionName);
             MainFrame.refreshTextpathDirectory();
             CenterPanel.refreshPanelContent();
         }
         else if(e.getClickCount()==1 && SwingUtilities.isRightMouseButton(e)){
             
            jpm.show(SouthPanel.centerPanel,e.getX(),e.getY());
         }
         else if(e.getClickCount()==1 && SwingUtilities.isLeftMouseButton(e)) {
            rightClicked = 1;
         }
    }
    
    @Override
    public void mouseEntered(MouseEvent e){
        this.setBackground(Color.red);
        this.setOpaque(true);
        this.revalidate();
        this.repaint();
    }
    
    @Override
    public void mouseExited(MouseEvent e){
        rightClicked = 0;
        this.setOpaque(false);
        this.revalidate();
        this.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==open) {
             CenterPanel.jPanel.removeAll();
             CurrentDirectory.setDirectoryName(partitionName);
             MainFrame.refreshTextpathDirectory();
             CenterPanel.refreshPanelContent();
         }
         else if(e.getSource()==details) {
            JOptionPane.showMessageDialog(
            SouthPanel.centerPanel,"File Name : "+partitionName+
            "\n File Type : "+partitionType+ "\n Usage : "+
            byteToSize(totalSpace-freeSpace)+ "\n Free Space : "+
            byteToSize(freeSpace)+ "\n Total Space : "+byteToSize(totalSpace),
           "Drive Information", JOptionPane.PLAIN_MESSAGE, 
           im.getImageIconForPartition(partitionType));
         }
    }
}

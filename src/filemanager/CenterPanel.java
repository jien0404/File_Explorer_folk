
package filemanager;

import java.awt.*;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

class CenterPanel {
    static JPanel jPanel = new JPanel();
    static DirectoryLabel[] directoryLabel;
    static Icons labelIcon = new Icons();
    static int layoutState=1;
    
    //set view style.
    static void setView(int i) {
        layoutState = i;
    }
    
    //refresh the all elements of the center element to add new elements
    static void refreshPanelContent() { 
        GridBagConstraints c = new GridBagConstraints();
        int y=0;
        if(layoutState==1) {
            jPanel.setLayout(new GridBagLayout());
           
        }
       else if(layoutState==2){
            jPanel.setLayout(new FlowLayout());
        }
        CurrentDirectory.findAllContentName();
        int len = CurrentDirectory.allCurrentContentNames.length;
        if(len==0) {
            jPanel.add(new JLabel("This Folder is Empty."));
            jPanel.revalidate();
            jPanel.repaint();
            return;
        }
        CurrentDirectory.findAllContentFolderOrFile();
        directoryLabel = new DirectoryLabel[len];
        for(int i=0; i<len; i++) {
            String fileName = CurrentDirectory.allCurrentContentNames[i];
            //System.out.println(i+" "+fileName);
            if(layoutState==1) {
                directoryLabel[i] = new DirectoryLabel(fileName,labelIcon.getImageIcon(i),JLabel.LEFT);
                directoryLabel[i].index = i;
                directoryLabel[i].addMouseListener(directoryLabel[i]);
                //directoryLabel[i].setSize(750,50);
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = y++;
                c.gridwidth = 3;
                c.weightx = 0.5;
                c.weighty = 0;
                // c.anchor = GridBagConstraints.FIRST_LINE_START;
                jPanel.add(directoryLabel[i],c);
            }
            else if(layoutState==2) {
                directoryLabel[i] = new DirectoryLabel(fileName);
                directoryLabel[i].index = i;
                directoryLabel[i].addMouseListener(directoryLabel[i]);
                directoryLabel[i].setIcon(labelIcon.getImageIcon(i));
                directoryLabel[i].setVerticalTextPosition(SwingConstants.BOTTOM);
                directoryLabel[i].setHorizontalTextPosition(SwingConstants.CENTER);
                jPanel.add(directoryLabel[i]);
            }
            if(MainFrame.modeStatus==0) {
                directoryLabel[i].setBackground(Color.white);
                directoryLabel[i].setForeground(Color.black);
            }
            else {
                directoryLabel[i].setBackground(Color.black);
                directoryLabel[i].setForeground(Color.white);
            }
            jPanel.revalidate();
            jPanel.repaint();
        }
    }
}

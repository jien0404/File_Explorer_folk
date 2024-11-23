/*
    All of the object and functionalities of this classs is to add on the 
    westPanel of the MainFrame content pane.
*/

package filemanager;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.FileSystemView;

public class SouthPanel{
    
    static JPanel southPanel,centerPanel;
    static JTextField inform,copyRight;
    static PartitionLabel[] partition;
    static Icons icon = new Icons();
    static BorderLayout layout = new BorderLayout();
    
    //adding initial content to the south panel
    static void addingSouthPanelContents() {
        southPanel  = new JPanel();
        centerPanel = new JPanel();
        inform =new JTextField();
        copyRight = new JTextField();
        MainFrame.mainContainer.add(southPanel,MainFrame.bLayout.SOUTH);
        southPanel.setBackground(Color.white);
        southPanel.setBounds(0,0,100,100);
        southPanel.setLayout(layout);
        inform.setText("All Drives and Device");
        inform.setEditable(false);
        copyRight.setText("@2023 Developed by Rabiul & Mahmudul, @2024 Developed by jien0404");
        copyRight.setEditable(false);
        southPanel.add(copyRight,layout.SOUTH);
        southPanel.add(inform,layout.NORTH);
        southPanel.add(centerPanel,layout.CENTER);
        centerPanel.setLayout(new GridLayout(1,0));
        refreshSouthPanel();
    }
    /*
        this function mainly focused to refresh the south panel of the mainframe
        main content pane. When usb drive is putted of or removed this function
        should be called
    */
    static void refreshSouthPanel() {
        File[] drives = File.listRoots();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        if (drives.length > 0) {
            partition = new PartitionLabel[drives.length];
            for (int i=0; i<drives.length; i++) {
                    partition[i] = new PartitionLabel(drives[i].toString(),fsv.getSystemTypeDescription(drives[i]),icon.getImageIconForPartition(fsv.getSystemTypeDescription(drives[i])), drives[i].getTotalSpace(),
                            drives[i].getFreeSpace(),JLabel.CENTER);
                    partition[i].setVerticalTextPosition(SwingConstants.BOTTOM);
                    partition[i].setHorizontalTextPosition(SwingConstants.CENTER);
                    partition[i].jpm.add(partition[i].open);
                    partition[i].jpm.add(partition[i].details);
                    partition[i].open.addActionListener(partition[i]);
                    partition[i].details.addActionListener(partition[i]);
                    partition[i].setText(partition[i].partitionType+" "+partition[i].partitionName);
                    partition[i].addMouseListener(partition[i]);
                    partition[i].repaint();
                    partition[i].revalidate();
                    centerPanel.add(partition[i]);
                    if(MainFrame.modeStatus==0) {
                        partition[i].setBackground(Color.white);
                        partition[i].setForeground(Color.black);
                    }
                    else {
                        partition[i].setBackground(Color.black);
                        partition[i].setForeground(Color.white);
                    }
            }
        }
        southPanel.repaint();
        southPanel.revalidate();
    }
    
}

package filemanager;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.border.LineBorder;

class MainFrame extends JFrame implements ActionListener{
    static int modeStatus = 0;
    static JTextField pathDirectory = new JTextField( );
    static Container mainContainer = new Container(); 
    Font newFont = new Font("Arial",Font.ITALIC,16);
    LineBorder lineBorder = new LineBorder(Color.BLACK,3,true); //It's for path textarea border
    static BorderLayout bLayout = new BorderLayout();
    public JMenuBar menuBur = new JMenuBar();
    public JMenu view = new JMenu("View");
    public JMenu sort= new JMenu(" Sort");
    public JMenu mode = new JMenu("Mode");
    public JMenuItem descending = new JMenuItem("Descending");
    public JMenuItem ascending = new JMenuItem("Ascending");
    public JMenuItem listView = new JMenuItem("List View");
    public JMenuItem gridView = new JMenuItem("Grid View");
    public JMenuItem day = new JMenuItem("Day Mode");
    public JMenuItem night = new JMenuItem("Night Mode");
    Icons icon = new Icons();
    static JScrollPane scroll = new JScrollPane(CenterPanel.jPanel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    WestPanel west = new WestPanel();
    SouthPanel south = new SouthPanel();
    //function that add all initial elements to the starting frame.
    void mainStart() {
        this.setVisible(true);
        this.setSize(800,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("File Manager");
        this.setIconImage(icon.mainFramIcon.getImage());
        this.setJMenuBar(menuBur);
        menuBur.add(view);
        menuBur.add(sort);
        menuBur.add(mode);
        sort.add(descending);
        sort.add(ascending);
        mode.add(day);
        mode.add(night);
        
        view.add(listView);
        view.add(gridView);
        listView.setIcon(icon.listIcon);
        gridView.setIcon(icon.gridIcon);
        descending.addActionListener(this);
        ascending.addActionListener(this);
        listView.addActionListener(this);
        gridView.addActionListener(this);
        day.addActionListener(this);
        night.addActionListener(this);
        
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        mainContainer = this.getContentPane();
        mainContainer.setLayout(bLayout);
        mainContainer.add(pathDirectory,bLayout.NORTH);
        mainContainer.add(scroll, bLayout.CENTER);
        
        CenterPanel.refreshPanelContent();
        pathDirectory.setEditable(false);
        refreshTextpathDirectory();
        pathDirectory.setForeground(Color.WHITE);
        pathDirectory.setFont(newFont);
        pathDirectory.setBorder(lineBorder); //Rounded Border
        pathDirectory.setBackground(Color.BLACK);
        pathDirectory.setHorizontalAlignment(JTextField.CENTER);
        west.initialElementToWestPanel();
        south.addingSouthPanelContents();
    }
    /*
        this portion of code executed when user click on night mode
                 
    */
    void setAllContentsToNightMode() {
        modeStatus = 1;
                                                    //setting inner class object background and foreground in night mode
        view.setBackground(Color.BLACK);
        view.setForeground(Color.WHITE);
        sort.setBackground(Color.BLACK);
        sort.setForeground(Color.WHITE);
        mode.setBackground(Color.BLACK);
        mode.setForeground(Color.WHITE);
        ascending.setBackground(Color.BLACK);
        ascending.setForeground(Color.WHITE);
        descending.setBackground(Color.BLACK);
        descending.setForeground(Color.WHITE);
        listView.setBackground(Color.BLACK);
        listView.setForeground(Color.WHITE);
        gridView.setBackground(Color.BLACK);
        gridView.setForeground(Color.WHITE);
        day.setBackground(Color.BLACK);
        day.setForeground(Color.WHITE);
        night.setBackground(Color.BLACK);
        night.setForeground(Color.WHITE);
        menuBur.setBackground(Color.black);
        menuBur.setForeground(Color.white);
        scroll.setBackground(Color.black);
        scroll.setForeground(Color.white);
        west.setBackground(Color.black);
        west.setForeground(Color.WHITE);   
                                                                       //setting outer class object background and foreground in night mode
        CenterPanel.jPanel.removeAll();
        CenterPanel.refreshPanelContent();
        SouthPanel.centerPanel.removeAll();
        SouthPanel.refreshSouthPanel();
        CenterPanel.jPanel.setBackground(Color.black);
        CenterPanel.jPanel.setForeground(Color.WHITE);
        SouthPanel.inform.setBackground(Color.black);
        SouthPanel.inform.setForeground(Color.white);
        SouthPanel.copyRight.setBackground(Color.black);
        SouthPanel.copyRight.setForeground(Color.white);
        SouthPanel.centerPanel.setBackground(Color.black);
        SouthPanel.centerPanel.setForeground(Color.white);
        SouthPanel.southPanel.setBackground(Color.black);
        SouthPanel.southPanel.setForeground(Color.white);
    }
    /*
        this portion of code executed when user click on day mode
        and it also default mode
    */
    void setAllContentsToDayMode(){
        modeStatus = 0;
                                                       //Setting inner class object background and forground
        view.setBackground(Color.white);
        view.setForeground(Color.BLACK);
        sort.setBackground(Color.white);
        sort.setForeground(Color.BLACK);
        mode.setBackground(Color.white);
        mode.setForeground(Color.BLACK);
        ascending.setBackground(Color.WHITE);
        ascending.setForeground(Color.BLACK);
        descending.setBackground(Color.WHITE);
        descending.setForeground(Color.BLACK);
        listView.setBackground(Color.WHITE);
        listView.setForeground(Color.BLACK);
        gridView.setBackground(Color.WHITE);
        gridView.setForeground(Color.black);
        day.setBackground(Color.white);
        day.setForeground(Color.BLACK);
        night.setBackground(Color.white);
        night.setForeground(Color.BLACK);
        menuBur.setBackground(Color.white);
        menuBur.setForeground(Color.black);
        
        scroll.setBackground(Color.white);
        scroll.setForeground(Color.black);
        west.setBackground(Color.white);
        west.setForeground(Color.black);
                                                          //Setting outer class object background and forground
        CenterPanel.jPanel.removeAll();
        CenterPanel.refreshPanelContent();
        SouthPanel.centerPanel.removeAll();
        SouthPanel.refreshSouthPanel();
        CenterPanel.jPanel.setBackground(Color.white);
        CenterPanel.jPanel.setForeground(Color.black);
        SouthPanel.inform.setBackground(Color.white);
        SouthPanel.inform.setForeground(Color.black);
        SouthPanel.copyRight.setBackground(Color.white);
        SouthPanel.copyRight.setForeground(Color.black);
        SouthPanel.centerPanel.setBackground(Color.white);
        SouthPanel.centerPanel.setForeground(Color.black);
        SouthPanel.southPanel.setBackground(Color.white);
        SouthPanel.southPanel.setForeground(Color.black);
    }
   //when directory changed, this function is called to update the directory path
    static void refreshTextpathDirectory() {
         pathDirectory.setText(CurrentDirectory.currentDirectoryName);
         pathDirectory.revalidate();
         pathDirectory.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==listView) {
            CenterPanel.setView(1);
            CenterPanel.jPanel.removeAll();
            CenterPanel.refreshPanelContent();
        }
        else if(e.getSource()==gridView) {
            CenterPanel.setView(2);
            CenterPanel.jPanel.removeAll();
            CenterPanel.refreshPanelContent();
        }
        else if(e.getSource() ==descending){
            CurrentDirectory.setSort=1;
            CenterPanel.jPanel.removeAll();
            CenterPanel.refreshPanelContent();
        }else if(e.getSource() ==ascending){
            CurrentDirectory.setSort=0;
            CenterPanel.jPanel.removeAll();
            CenterPanel.refreshPanelContent();
        }
        else if(e.getSource() == day) {                
            setAllContentsToDayMode();
        }
        else if(e.getSource()== night) {
            setAllContentsToNightMode();
        }
    }
}

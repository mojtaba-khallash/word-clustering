package ir.ac.iust.nlp.wordclustering;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Mojtaba Khallash
 */
public class WClustering extends javax.swing.JFrame {

    private HashMap<String, List<String>> map;
    private HashMap<String, List<String>> current_map;
    
    /**
     * Creates new form WClustering
     */
    public WClustering() {
        initComponents();
        
        File cn = new File(System.getProperty("user.dir") + File.separator + "Source");
        if (cn.exists()) {
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboClusterNo.getModel();
            model.removeAllElements();
            
            File[] files = cn.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    try {
                        int num = Integer.parseInt(files[i].getName());
                        model.addElement(num);
                    }
                    catch (Exception ex) {}
                }
            }
        }

        pnlContainResult.setVisible(false);

        int maxBit = 1;
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboClusterID.getModel();
        map = ReadFile.GetList(chkWord.isSelected(), Integer.parseInt(cboClusterNo.getSelectedItem().toString()));
        current_map = map;
        Iterator<String> iter = current_map.keySet().iterator();
        int count = 0;
        while(iter.hasNext()) {
            String val = iter.next();
            int len = val.length();
            if (maxBit < len)
                maxBit = len;
            model.addElement(val);
            count++;
        }
        lblNOC.setText(String.valueOf(count));
        
        int current = maxBit;
        int min = 1;
        int step = 1;
        SpinnerNumberModel num_model = new SpinnerNumberModel(current, min, maxBit, step); 
        spNumBit.setModel(num_model);
        
        setDrop();
    }
    
    private void setDrop() {
        FileDrop fd;
        fd = new FileDrop(null, txtInputCoNLLFile, new FileDrop.Listener() {
            @Override
            public void filesDropped(java.io.File[] files) {
                if (files.length > 0) {
                    try {
                        boolean dropped = false;
                        for (int i = 0; i < files.length; i++) {
                            if (files[i].isFile()) {
                                txtInputCoNLLFile.setText(files[i].getCanonicalPath());
                                dropped = true;
                                break;
                            }
                        }
                        if (dropped == false) {
                            JOptionPane.showMessageDialog(null, "File needed.");
                        }
                    } // end try
                    catch (java.io.IOException e) {
                    }
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener
        
        fd = new FileDrop(null, txtOutputPath, new FileDrop.Listener() {
            @Override
            public void filesDropped(java.io.File[] files) {
                if (files.length > 0) {
                    try {
                        if (files[0].isFile()) {
                            files[0] = files[0].getParentFile();
                        }

                        txtOutputPath.setText(files[0].getCanonicalPath() + File.separator);
                    } // end try
                    catch (java.io.IOException e) {
                    }
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener
    }
    
    boolean stop_update = false;
    
    private void SetClusterList() {
        int maxBit = Integer.parseInt(spNumBit.getValue().toString());

        Iterator<String> iter = map.keySet().iterator();

        current_map = new HashMap<String, List<String>>();
        while(iter.hasNext()) {
            String strVal = iter.next();
            String newVal = strVal;
            if (strVal.length() > maxBit) {
                newVal = strVal.substring(0, maxBit);
            }
                
            List<String> newList = current_map.get(newVal);
            List<String> list = map.get(strVal);
            if (list == null)
            {
                System.out.println(strVal + " - " + newVal);
            }
            if (newList == null)
                newList = new LinkedList<String>(list);
            else
                newList.addAll(list);
            current_map.put(newVal, newList);
        }        

        stop_update = true;
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboClusterID.getModel();
        model.removeAllElements();
        stop_update = false;
        
        int count = 0;
        iter = current_map.keySet().iterator();
        while(iter.hasNext()) {
            model.addElement(iter.next());
            count++;
        }        
        lblNOC.setText(String.valueOf(count));
    }

    private void SetWordList(String key) {
        List<String> list = current_map.get(key);
        
        DefaultListModel model = (DefaultListModel) lstWords.getModel();
        model.clear();

        for (int i = 0; i < list.size(); i++) {
            model.add(i, list.get(i));
        }
        lblNOV.setText(String.valueOf(list.size()) + "  ");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlList = new javax.swing.JPanel();
        cboClusterID = new javax.swing.JComboBox(new DefaultComboBoxModel());
        lblClusterID = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstWords = new javax.swing.JList(new DefaultListModel());
        pnlStatusBar = new javax.swing.JPanel();
        lblNOCTitle = new javax.swing.JLabel();
        lblNOV = new javax.swing.JLabel();
        lblNWs = new javax.swing.JLabel();
        lblNOC = new javax.swing.JLabel();
        lblNumBit = new javax.swing.JLabel();
        spNumBit = new javax.swing.JSpinner();
        chkWord = new javax.swing.JCheckBox();
        lblClusterNo = new javax.swing.JLabel();
        cboClusterNo = new javax.swing.JComboBox();
        pnlSearch = new javax.swing.JPanel();
        lblEnterWord = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        rbFullMatch = new javax.swing.JRadioButton();
        rbContain = new javax.swing.JRadioButton();
        pnlFullMatchResult = new javax.swing.JPanel();
        lblClusterResult = new javax.swing.JLabel();
        pnlContainResult = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstClusterResults = new javax.swing.JList(new DefaultListModel());
        jScrollPane2 = new javax.swing.JScrollPane();
        lstWordResults = new javax.swing.JList(new DefaultListModel());
        btnSearch = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblInputCoNLLFile = new javax.swing.JLabel();
        btnBrowseInputCoNLLFile = new javax.swing.JButton();
        txtInputCoNLLFile = new javax.swing.JTextField();
        lblOutputPath = new javax.swing.JLabel();
        btnBrowseOutputPath = new javax.swing.JButton();
        txtOutputPath = new javax.swing.JTextField();
        btnStartTagging = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Word Clustering");

        cboClusterID.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboClusterID_ValueChanged(evt);
            }
        });

        lblClusterID.setText("Cluster ID:");

        jScrollPane1.setViewportView(lstWords);

        pnlStatusBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNOCTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNOCTitle.setText("  Number of Clusters:");

        lblNOV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblNWs.setText("Number of Words in Cluster:");

        javax.swing.GroupLayout pnlStatusBarLayout = new javax.swing.GroupLayout(pnlStatusBar);
        pnlStatusBar.setLayout(pnlStatusBarLayout);
        pnlStatusBarLayout.setHorizontalGroup(
            pnlStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatusBarLayout.createSequentialGroup()
                .addComponent(lblNOCTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNOC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNWs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNOV))
        );
        pnlStatusBarLayout.setVerticalGroup(
            pnlStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNOV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblNWs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
            .addComponent(lblNOCTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblNOC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lblNumBit.setText("Number of Bits:");

        spNumBit.setMaximumSize(new java.awt.Dimension(100, 100));
        spNumBit.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spNumBit_Changed(evt);
            }
        });

        chkWord.setSelected(true);
        chkWord.setText("Use Word");
        chkWord.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkWord_stateChanged(evt);
            }
        });

        lblClusterNo.setText("Number of Cluster:");

        cboClusterNo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "100" }));
        cboClusterNo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboClusterNo_ValueChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlListLayout = new javax.swing.GroupLayout(pnlList);
        pnlList.setLayout(pnlListLayout);
        pnlListLayout.setHorizontalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addGroup(pnlListLayout.createSequentialGroup()
                        .addComponent(lblClusterNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboClusterNo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblClusterID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboClusterID, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlListLayout.createSequentialGroup()
                        .addComponent(chkWord)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNumBit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spNumBit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlListLayout.setVerticalGroup(
            pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumBit)
                    .addComponent(spNumBit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkWord))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(pnlListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClusterID)
                    .addComponent(cboClusterID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClusterNo)
                    .addComponent(cboClusterNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Cluster List", pnlList);

        lblEnterWord.setText("Enter Word:");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch_KeyReleased(evt);
            }
        });

        buttonGroup1.add(rbFullMatch);
        rbFullMatch.setSelected(true);
        rbFullMatch.setText("Full Match");
        rbFullMatch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbFullMatch_SelectionChanged(evt);
            }
        });

        buttonGroup1.add(rbContain);
        rbContain.setText("Contain");
        rbContain.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbContain_SelectionChanged(evt);
            }
        });

        lblClusterResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClusterResult.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlFullMatchResultLayout = new javax.swing.GroupLayout(pnlFullMatchResult);
        pnlFullMatchResult.setLayout(pnlFullMatchResultLayout);
        pnlFullMatchResultLayout.setHorizontalGroup(
            pnlFullMatchResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblClusterResult, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlFullMatchResultLayout.setVerticalGroup(
            pnlFullMatchResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblClusterResult, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );

        lstClusterResults.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstClusterResultslst_Clicked(evt);
            }
        });
        lstClusterResults.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstClusterResults_ValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(lstClusterResults);

        lstWordResults.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstWordResultslst_Clicked(evt);
            }
        });
        lstWordResults.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstWordResults_ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstWordResults);

        javax.swing.GroupLayout pnlContainResultLayout = new javax.swing.GroupLayout(pnlContainResult);
        pnlContainResult.setLayout(pnlContainResultLayout);
        pnlContainResultLayout.setHorizontalGroup(
            pnlContainResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainResultLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlContainResultLayout.setVerticalGroup(
            pnlContainResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlFullMatchResult, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addComponent(lblEnterWord)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch))
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addComponent(rbFullMatch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbContain))
                    .addComponent(pnlContainResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEnterWord)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbContain)
                    .addComponent(rbFullMatch, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFullMatchResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContainResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Search", pnlSearch);

        lblInputCoNLLFile.setText("Input CoNLL File:");

        btnBrowseInputCoNLLFile.setText("...");
        btnBrowseInputCoNLLFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseInputCoNLLFileActionPerformed(evt);
            }
        });

        txtInputCoNLLFile.setEditable(false);

        lblOutputPath.setText("Output Path:");

        btnBrowseOutputPath.setText("...");
        btnBrowseOutputPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseOutputPathActionPerformed(evt);
            }
        });

        txtOutputPath.setEditable(false);

        btnStartTagging.setText("Start Tagging");
        btnStartTagging.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartTaggingActionPerformed(evt);
            }
        });

        jScrollPane4.setEnabled(false);

        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane4.setViewportView(txtLog);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInputCoNLLFile)
                            .addComponent(lblOutputPath))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtOutputPath)
                            .addComponent(txtInputCoNLLFile))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBrowseOutputPath, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(btnBrowseInputCoNLLFile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnStartTagging)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInputCoNLLFile)
                    .addComponent(btnBrowseInputCoNLLFile)
                    .addComponent(txtInputCoNLLFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOutputPath)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBrowseOutputPath)
                        .addComponent(txtOutputPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStartTagging)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tagger", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboClusterID_ValueChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboClusterID_ValueChanged
        if (stop_update == false)
            SetWordList(evt.getItem().toString());
    }//GEN-LAST:event_cboClusterID_ValueChanged

    private void txtSearch_KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch_KeyReleased
        if (evt.getKeyCode() == 10) {
            btnSearch.doClick();
        }
    }//GEN-LAST:event_txtSearch_KeyReleased

    private void rbFullMatch_SelectionChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbFullMatch_SelectionChanged
        pnlFullMatchResult.setVisible(true);
        lblClusterResult.setText("");
        pnlContainResult.setVisible(false);
    }//GEN-LAST:event_rbFullMatch_SelectionChanged

    private void rbContain_SelectionChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbContain_SelectionChanged
        pnlFullMatchResult.setVisible(false);
        pnlContainResult.setVisible(true);
        DefaultListModel model;

        model = (DefaultListModel) lstWordResults.getModel();
        model.clear();

        model = (DefaultListModel) lstClusterResults.getModel();
        model.clear();
    }//GEN-LAST:event_rbContain_SelectionChanged
    
    boolean lock = false;

    private void lstClusterResultslst_Clicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstClusterResultslst_Clicked
        lock = false;
    }//GEN-LAST:event_lstClusterResultslst_Clicked

    private void lstClusterResults_ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstClusterResults_ValueChanged
        if (lock == false) {
            lock = true;
            lstWordResults.setSelectedIndex(lstClusterResults.getSelectedIndex());
            lstWordResults.setBounds(lstClusterResults.getBounds());
        } else {
            lock = false;
        }
    }//GEN-LAST:event_lstClusterResults_ValueChanged

    private void lstWordResultslst_Clicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstWordResultslst_Clicked
        lock = false;
    }//GEN-LAST:event_lstWordResultslst_Clicked

    private void lstWordResults_ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstWordResults_ValueChanged
        if (lock == false) {
            lock = true;
            lstClusterResults.setSelectedIndex(lstWordResults.getSelectedIndex());
            lstClusterResults.setBounds(lstWordResults.getBounds());
        } else {
            lock = false;
        }
    }//GEN-LAST:event_lstWordResults_ValueChanged

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String verb = txtSearch.getText();
        verb = verb.replaceAll("ي", "ی");
        lblClusterResult.setText("");

        DefaultListModel verbModel = (DefaultListModel) lstWordResults.getModel();
        verbModel.clear();
        DefaultListModel clusterModel = (DefaultListModel) lstClusterResults.getModel();
        clusterModel.clear();
        int ind = 0;

        Iterator<String> iter = current_map.keySet().iterator();
        while (iter.hasNext()) {
            String id = iter.next();
            List<String> list = current_map.get(id);
            if (rbFullMatch.isSelected() == true) {
                boolean find = false;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(verb)) {
                        lblClusterResult.setText(id);
                        find = true;
                        break;
                    }
                }
                if (find == true) {
                    break;
                }
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).indexOf(verb) != -1) {
                        verbModel.add(ind, list.get(i));
                        clusterModel.add(ind, String.valueOf(id));
                        ind++;
                    }
                }
            }
        }

        if (rbFullMatch.isSelected() == true && lblClusterResult.getText().length() == 0) {
            lblClusterResult.setText("Not Found.");
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void spNumBit_Changed(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spNumBit_Changed
        SetClusterList();
    }//GEN-LAST:event_spNumBit_Changed

    private void chkWord_stateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkWord_stateChanged
        int maxBit = 1;
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboClusterID.getModel();
        model.removeAllElements();
        map = ReadFile.GetList(chkWord.isSelected(), Integer.parseInt(cboClusterNo.getSelectedItem().toString()));
        current_map = map;
        Iterator<String> iter = current_map.keySet().iterator();
        int count = 0;
        while(iter.hasNext()) {
            String val = iter.next();
            int len = val.length();
            if (maxBit < len)
                maxBit = len;
            model.addElement(val);
            count++;
        }
        lblNOC.setText(String.valueOf(count));
        
        int current = maxBit;
        int min = 1;
        int step = 1;
        SpinnerNumberModel num_model = new SpinnerNumberModel(current, min, maxBit, step); 
        spNumBit.setModel(num_model);
    }//GEN-LAST:event_chkWord_stateChanged

    private void cboClusterNo_ValueChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboClusterNo_ValueChanged

        if (cboClusterNo.getModel().getSize() == 0)
            return;
        
        int maxBit = 1;
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboClusterID.getModel();
        model.removeAllElements();
        map = ReadFile.GetList(chkWord.isSelected(), Integer.parseInt(cboClusterNo.getSelectedItem().toString()));
        current_map = map;
        Iterator<String> iter = current_map.keySet().iterator();
        int count = 0;
        while(iter.hasNext()) {
            String val = iter.next();
            int len = val.length();
            if (maxBit < len)
                maxBit = len;
            model.addElement(val);
            count++;
        }
        lblNOC.setText(String.valueOf(count));
        
        int current = maxBit;
        int min = 1;
        int step = 1;
        SpinnerNumberModel num_model = new SpinnerNumberModel(current, min, maxBit, step); 
        spNumBit.setModel(num_model);
    }//GEN-LAST:event_cboClusterNo_ValueChanged

    private void btnBrowseInputCoNLLFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseInputCoNLLFileActionPerformed
        txtInputCoNLLFile.setText(showFileDialog(txtInputCoNLLFile.getText(), false, null));
    }//GEN-LAST:event_btnBrowseInputCoNLLFileActionPerformed

    private void btnBrowseOutputPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseOutputPathActionPerformed
        txtOutputPath.setText(showFileDialog(txtOutputPath.getText(), true, null));
    }//GEN-LAST:event_btnBrowseOutputPathActionPerformed

    private void btnStartTaggingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartTaggingActionPerformed
        String Introduction = "-----------------------------------------------------------------------------\n" +
                              "                           WordClustering 1.0\n" +
                              "-----------------------------------------------------------------------------\n" +
                              "                            Mojtaba Khallash\n\n" +
                              "             Iran University of Science and Technology (IUST)\n" +
                              "                                 Iran\n" +
                              "-----------------------------------------------------------------------------\n";
        
        PrintStream out = new PrintStream(new OutputStream() {

            private StringBuffer buffer = new StringBuffer();

            @Override
            public void write(int b)
                    throws IOException {
                this.buffer.append((char) b);
                txtLog.setText(buffer.toString());
                txtLog.setCaretPosition(txtLog.getDocument().getLength() - 1);
            }
        });
        out.println(Introduction);
        ApplyInCoNLL.out = out;
        try {
            ApplyInCoNLL.Start(txtInputCoNLLFile.getText(), txtOutputPath.getText() + "output_wc.conll",
                    Integer.parseInt(cboClusterNo.getSelectedItem().toString()),
                    Integer.parseInt(spNumBit.getValue().toString()),
                    chkWord.isSelected());
        }
        catch(Exception ex) {
            out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnStartTaggingActionPerformed

    private String showFileDialog(String currentDir, boolean isFolder,
            FileNameExtensionFilter filter) {
        JFileChooser fc = new JFileChooser();
        if (currentDir.length() == 0) {
            fc.setCurrentDirectory(new java.io.File("."));
        } else {
            fc.setCurrentDirectory(new java.io.File(currentDir));
        }
        fc.setMultiSelectionEnabled(false);
        if (filter != null) {
            fc.setFileFilter(filter);
        }
        String title = "Select File";
        if (isFolder == true) {
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            title = "Select Folder";
        }

        if (fc.showDialog(this, title) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            String path = file.getPath();
            if (isFolder == true && path.lastIndexOf(File.separator) != path.length() - 1) {
                path = path + File.separator;
            }

            return path;
        } else {
            return currentDir;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WClustering.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WClustering.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WClustering.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WClustering.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new WClustering().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowseInputCoNLLFile;
    private javax.swing.JButton btnBrowseOutputPath;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnStartTagging;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboClusterID;
    private javax.swing.JComboBox cboClusterNo;
    private javax.swing.JCheckBox chkWord;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblClusterID;
    private javax.swing.JLabel lblClusterNo;
    private javax.swing.JLabel lblClusterResult;
    private javax.swing.JLabel lblEnterWord;
    private javax.swing.JLabel lblInputCoNLLFile;
    private javax.swing.JLabel lblNOC;
    private javax.swing.JLabel lblNOCTitle;
    private javax.swing.JLabel lblNOV;
    private javax.swing.JLabel lblNWs;
    private javax.swing.JLabel lblNumBit;
    private javax.swing.JLabel lblOutputPath;
    private javax.swing.JList lstClusterResults;
    private javax.swing.JList lstWordResults;
    private javax.swing.JList lstWords;
    private javax.swing.JPanel pnlContainResult;
    private javax.swing.JPanel pnlFullMatchResult;
    private javax.swing.JPanel pnlList;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JPanel pnlStatusBar;
    private javax.swing.JRadioButton rbContain;
    private javax.swing.JRadioButton rbFullMatch;
    private javax.swing.JSpinner spNumBit;
    private javax.swing.JTextField txtInputCoNLLFile;
    private javax.swing.JTextArea txtLog;
    private javax.swing.JTextField txtOutputPath;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
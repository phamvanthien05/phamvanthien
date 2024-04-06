import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class textView extends JFrame {
    private JPanel panel1, panel2;
    private JButton btSave, btClean, btReadFile, btdemsltu;
    private JTextArea textArea;

    Controller controller = new Controller(this);

    documents tm = new documents();

    public textView() {
        this.setTitle("Text editor");
        this.setSize(1030, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        panel1 = new JPanel();
        panel1.setBounds(2, 2, 300, 760);
        panel1.setBorder(new LineBorder(Color.BLACK, 1));
        panel1.setLayout(null);

        panel2 = new JPanel();
        panel2.setBounds(305, 2, 600, 760);
        panel2.setBorder(new LineBorder(Color.blue, 1));
        panel2.setLayout(null);

        textArea = new JTextArea();
        textArea.setBounds(5, 5, 580, 740);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane jScrollPane = new JScrollPane(textArea);
        jScrollPane.setBounds(5,5,590,750);
        panel2.add(jScrollPane);

        btSave = new JButton("Luu");
        btSave.setBounds(910, 2, 90, 30);
        btSave.addActionListener(controller);

        btClean = new JButton("Xoa");
        btClean.setBounds(910, 40, 90, 30);
        btClean.addActionListener(controller);

        btReadFile = new JButton("Doc file");
        btReadFile.setBounds(910, 78, 90, 30);
        btReadFile.addActionListener(controller);

        btdemsltu = new JButton("Dem slg tu");
        btdemsltu.setBounds(910, 116,90, 30);
        btdemsltu.addActionListener(controller);

        this.add(btSave);
        this.add(btClean);
        this.add(btReadFile);
        this.add(btdemsltu);
        this.add(panel1);
        this.add(panel2);

        taoCayThuMuc();
    }

    public synchronized void save(){
        Thread thread = new Thread(()->{
            tm.saveToFile(textArea.getText());
        });
        thread.start();
    }

    public synchronized void clean(){
        Thread thread = new Thread(()->{
            textArea.setText("");
        });
        thread.start();
    }

    public synchronized void read(){
        Thread thread = new Thread(()->{
            textArea.setText(tm.readFile());
        });
        thread.start();
    }
    public synchronized void demsolgtu(){
        Thread thread = new Thread(()->{
            JOptionPane.showMessageDialog(null, "Tong so tu: " + tm.demTongSoTu(textArea.getText()),
                    "Thong bap", JOptionPane.INFORMATION_MESSAGE);
        });
        thread.start();
    }

    private synchronized void taoCayThuMuc (){
        Thread thread = new Thread(()->{
            String directoryPath = "/data.txt";
            File directory = new File(directoryPath);

            if (!directory.exists() || !directory.isDirectory()){
                JOptionPane.showMessageDialog(null, "Thu muc khong ton tai!", "Thong bao", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            DefaultMutableTreeNode root = new DefaultMutableTreeNode(directory.getName());
            DefaultTreeModel treeModel = new DefaultTreeModel(root);

            duyetThuMuc(directory, root);

            JTree tree = new JTree(treeModel);
            JScrollPane jScrollPane = new JScrollPane(tree);
            jScrollPane.setBounds(5, 5, 290, 750);

            panel1.add(jScrollPane);
        });
        thread.start();
    }

    private synchronized void duyetThuMuc(File directory, DefaultMutableTreeNode root){
        Thread thread = new Thread(()->{
            File[] files = directory.listFiles();
            if (files != null){
                for (File file : files){
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());
                    if (file.isDirectory()){
                        duyetThuMuc(file, node);
                    }
                    root.add(node);
                }
            }
        });
        thread.start();
    }

}
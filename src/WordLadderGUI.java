import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-PA4
 * Date: 3/19/13
 * Time: 9:48 AM
 * Java Class: PACKAGE_NAME
 */
public class WordLadderGUI extends JFrame implements ActionListener {

    //WordLadder
    WordLadder wordLadder = null;

    //GUI Labels
    private JLabel lblInputFile          = new JLabel("Input File Name ");
    private JLabel lblSource             = new JLabel("Source Word ");
    private JLabel lblDestination        = new JLabel("Destination Word ");
    private JTextArea taResults          = new JTextArea(8,23);
    private JScrollPane scrollPane       = new JScrollPane(taResults);
    //GUI Inputs
    private JTextField tfInputFile       = new JTextField(25);
    private JTextField tfSource          = new JTextField(5);
    private JTextField tfDestination     = new JTextField(5);

    //GUI Buttons
    private JButton btSubmit             = new JButton("Submit");
    private JButton btClose              = new JButton("Close");
    private JButton btClear              = new JButton("Clear");

    public WordLadderGUI(){

        JPanel inPanel = new JPanel(new GridLayout(6, 2, 1, 1));
        JPanel btPanel = new JPanel(new GridLayout(1, 2, 5, 1));
        JPanel resultsPanel = new JPanel();

        //Add components to inPanel
        inPanel.add(lblInputFile);
        inPanel.add(tfInputFile);
        inPanel.add(lblSource);
        inPanel.add(tfSource);
        inPanel.add(lblDestination);
        inPanel.add(tfDestination);

        //add components to btPanel
        btPanel.add(btSubmit);
        //btPanel.add(btClose);
        btPanel.add(btClear);

        //add actionlisteners to buttons
        btSubmit.addActionListener(this);
        //btClose.addActionListener(this);
        btClear.addActionListener(this);

        //add components to results
        resultsPanel.add(scrollPane);

        //add panels
        add(inPanel);
        add(btPanel);
        add(resultsPanel);

        //Set defaults
        tfInputFile.setText("C:\\ics340\\words.txt");
        tfSource.setText("");
        tfDestination.setText("");

    }

    public static void main(String[] args) {

        JFrame frame = new WordLadderGUI();
        frame.setLayout(new FlowLayout());
        frame.setTitle("WordLadderGUI");
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
       Object obj = actionEvent.getSource();

        if (obj == btSubmit){

              wordLadder = new WordLadder(tfInputFile.getText(), tfSource.getText(), tfDestination.getText());

        }

        if (obj == btClear){
            clearData();
        }


    }

    private void clearData() {
        tfSource.setText("");
        tfDestination.setText("");
    }
}

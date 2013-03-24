
//Code Genarated by JGuiD
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.text.View;

public class WordLadderGUI extends JFrame implements ActionListener {
    Growler view;
    JFrame frame;
    TextArea taDictionary;
     TextField tfSourc;
     Label lblSourc;
     //Label lblDestination;
     Label lblDestinatio;
     TextField tfSourc_6;
     Label lblFileNam;
     TextField tfFilePat;
     JButton btLoadTextFiel;
     JButton btLoadFil;
     JButton btFindPat;
     JLabel lblDictCoun;
     JLabel lblIndexing1;
     JPanel testpanel;
    JButton btClear;
      //JLabel lblIndexing2;
     //JLabel lblIndexing3;
     //JLabel lblIndexing4;
     JLabel lblFindPat;
     TextField untitled_16;
     JLabel lblCos;
     JLabel lblProgres;
    Label lblWordSize;
    TextField tfWordSize;
    WordLadder wordLadder = null;
    WordCollection guiDictionary = null;
    ArrayList<String> results = new ArrayList<String>();


   public WordLadderGUI()
   {
     getContentPane().setLayout(null);
     setupGUI();
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   void setupGUI()
   {
    taDictionary = new TextArea();
	taDictionary.setLocation(0, 0);
	taDictionary.setSize(106, 567);
	taDictionary.setBackground(new Color(-1));
	taDictionary.setText("");
	taDictionary.setRows(5);
	taDictionary.setColumns(1);
	getContentPane().add(taDictionary);

	tfSourc = new TextField();
	tfSourc.setLocation(224,90);
	tfSourc.setSize(266,25);
	tfSourc.setBackground( new Color(-1) );
	tfSourc.setText("");
	tfSourc.setColumns(10);
	getContentPane().add(tfSourc);

	lblSourc = new Label();
	lblSourc.setLocation(106,90);
	lblSourc.setSize(119,25);
	lblSourc.setText("Source Word:");
	getContentPane().add(lblSourc);
    /*
	lblDestinatio = new Label();
	lblDestinatio.setLocation(106,97);
	lblDestinatio.setSize(119,25);
	lblDestinatio.setText("Destination Wor");
	getContentPane().add(lblDestinatio);
    */

	lblDestinatio = new Label();
	lblDestinatio.setLocation(106,120);
	lblDestinatio.setSize(119, 25);
	lblDestinatio.setText("Destination Word:");
	getContentPane().add(lblDestinatio);

    lblWordSize = new Label();
       lblWordSize.setLocation(106,27);           //106,120
       lblWordSize.setSize(119,25);
       lblWordSize.setText("Word Size:");
    getContentPane().add(lblWordSize);

       tfWordSize = new TextField();
       tfWordSize.setLocation(224,27);          //224,120
       tfWordSize.setSize(263,25);
       tfWordSize.setBackground( new Color(-1) );
       tfWordSize.setText("5");
       tfWordSize.setColumns(10);
       getContentPane().add(tfWordSize);

	tfSourc_6 = new TextField();
	tfSourc_6.setLocation(226,120);
	tfSourc_6.setSize(263,25);
	tfSourc_6.setBackground( new Color(-1) );
	tfSourc_6.setText("");
	tfSourc_6.setColumns(10);
	getContentPane().add(tfSourc_6);

	lblFileNam = new Label();
	lblFileNam.setLocation(104,0);
	lblFileNam.setSize(119,25);
	lblFileNam.setText("FilePath:");
	getContentPane().add(lblFileNam);

	tfFilePat = new TextField();
	tfFilePat.setLocation(224,0);
	tfFilePat.setSize(266, 25);
	tfFilePat.setBackground(new Color(-1));

       //OS Detection
       if (System.getProperty("os.name").startsWith("Windows")) {
           // includes: Windows 2000,  Windows 95, Windows 98, Windows NT, Windows Vista, Windows XP
           tfFilePat.setText("c:\\ics340\\words.txt");
           System.out.println("Detected Windows: " + System.getProperty("os.name"));
       } else {
           tfFilePat.setText("/Users/jasonedstrom/ics340/d1.txt");
           System.out.println("Detected Mac OS X: " + System.getProperty("os.name"));
       }

	tfFilePat.setColumns(10);
	getContentPane().add(tfFilePat);

	btLoadTextFiel = new JButton();
	btLoadTextFiel.setLocation(108,50);      //108,27
	btLoadTextFiel.setSize(198,32);
	btLoadTextFiel.setText("Load Words from Text Field");
	getContentPane().add(btLoadTextFiel);

	btLoadFil = new JButton();
	btLoadFil.setLocation(306,50);         //306,27
	btLoadFil.setSize(183,32);
	btLoadFil.setText("Load Words from File");
	getContentPane().add(btLoadFil);

	btFindPat = new JButton();
	btFindPat.setLocation(106,160);
	btFindPat.setSize(384,38);
	btFindPat.setText("Find Path");
	getContentPane().add(btFindPat);

	lblDictCoun = new JLabel();
	lblDictCoun.setLocation(108,513);
	lblDictCoun.setSize(300,27);
	lblDictCoun.setForeground( new Color(-65536) );
	lblDictCoun.setText("Words in Dictionary = 0 words");
	getContentPane().add(lblDictCoun);

	lblIndexing1 = new JLabel();
	lblIndexing1.setLocation(107,454);
	lblIndexing1.setSize(130,27);
	lblIndexing1.setForeground( new Color(-16777216) );
	lblIndexing1.setText("");
	getContentPane().add(lblIndexing1);

	lblFindPat = new JLabel();
	lblFindPat.setLocation(108,540);
	lblFindPat.setSize(250,27);
	lblFindPat.setForeground( new Color(-14646771) );
	lblFindPat.setText("Time to find Path: 0 milliseconds");
	getContentPane().add(lblFindPat);


	lblCos = new JLabel();
	lblCos.setLocation(360,540);
	lblCos.setSize(175,27);
	lblCos.setForeground( new Color(-16777216) );
	lblCos.setText("Cost of Path: 0.0");
	getContentPane().add(lblCos);

	lblProgres = new JLabel();
	lblProgres.setLocation(108,484);
	lblProgres.setSize(371,26);
	lblProgres.setForeground( new Color(-14646771) );
	lblProgres.setText("Time to Build Graph: 0 milliseconds");
	getContentPane().add(lblProgres);

       testpanel = new JPanel();
       testpanel.setLocation(106,200);
       testpanel.setSize(350,200);
       testpanel.setForeground( new Color(-14646771) );
       //testpanel.setText("Test Location");
       getContentPane().add(testpanel);

     btClear = new JButton();
       btClear.setLocation(355, 513);
       btClear.setSize(125, 25);
       btClear.setText("Clear Results");
       getContentPane().add(btClear);

       //add actionlisteners to buttons
       btFindPat.addActionListener(this);
       btLoadTextFiel.addActionListener(this);
       btLoadFil.addActionListener(this);
       btClear.addActionListener(this);

	setTitle("WordLadderGUI");
	setSize(500,600);
	setVisible(true);
	setResizable(false);
	
	
   }
    public static void main( String args[] )
   {
     new WordLadderGUI();
   }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();

        if (obj == btFindPat){

            testpanel.removeAll();
            validate();
            repaint();
            if (taDictionary.getText().isEmpty()){
            JOptionPane.showMessageDialog(frame,"Please load words so they appear in Text Area before Finding Path. ");

            }else if( (tfSourc.getText().isEmpty()) || (tfSourc_6.getText().isEmpty())){
                JOptionPane.showMessageDialog(frame,"Please enter Source and Destination words before Finding Path. ");
            }else if (testpanel.getComponents().length != 0){
                JOptionPane.showMessageDialog(frame,"Please clear results before continuing.");
            }else{
            wordLadder.findPath(tfSourc.getText(), tfSourc_6.getText(), Integer.parseInt(tfWordSize.getText()));
            lblFindPat.setText("Time to find Path: " + String.valueOf(wordLadder.getTimeForPath()) + " milliseconds");
            lblCos.setText("Cost of Path: " + String.valueOf(wordLadder.g.getCost()));
            results = wordLadder.getResults();
            Collections.reverse(results);
                int x = 10;
                int y = 20;

              for (String s: results){
                  x +=20;
                  y +=20;
                  JLabel _lbl = new JLabel(s);
                  _lbl.setLocation(x,y);
                  _lbl.setSize(100, 26);

                  if ( results.indexOf(s) == 0){
                      _lbl.setForeground(new Color(-14646771));
                  } else if (results.indexOf(s) == (results.size()-1)){
                      _lbl.setForeground( new Color(-8254711) );
                  } else {
                      _lbl.setForeground( new Color(-16777216) );
                  }
                  testpanel.add(_lbl);
                  testpanel.repaint();

              }
                if (wordLadder.g.getCost() == 0){
                    //if (wordLadder.g.getGraphError() != null){
                    JLabel _lbl = new JLabel(wordLadder.g.getGraphError());
                    _lbl.setLocation(x,y);
                    _lbl.setSize(100, 26);
                    testpanel.add(_lbl);
                    testpanel.repaint();
                   // }

                    }


            }
           /*if (taDictionary.getText().isEmpty()){
           wordLadder = new WordLadder(tfFilePat.getText(), tfSourc.getText(), tfSourc_6.getText());
            }else if (!taDictionary.getText().isEmpty()){
               ArrayList<String> taList = new ArrayList<String>();
               StringTokenizer stringTokenizer = new StringTokenizer(taDump, "\t\n\r\f,\"");
               while (stringTokenizer.hasMoreTokens()) {
                   String token = stringTokenizer.nextToken();
                   taList.add(token);
               }
             */
            //}
        }

        if (obj == btLoadFil){
            clearData();
            //wordLadder.setWords_size(Integer.parseInt(tfWordSize.getText()));
            //String size = tfWordSize.getText();
            //int intSize = Integer.parseInt(size);
            //WordLadderGUI.showMessage("Loading words of" + tfWordSize.getText() + " characters from file: " + tfFilePat.getText(), Color.GREEN, Color.GREEN);
            lblIndexing1.setText("Indexing...");
            System.out.println("Loading words of " + tfWordSize.getText() + " characters from file: " + tfFilePat.getText());
            wordLadder = new WordLadder(tfFilePat.getText(), Integer.parseInt(tfWordSize.getText()));
            //wordLadder.
            guiDictionary = new WordCollection(wordLadder.getWordList());
            WordLadderGUI.showMessage("Displaying " + wordLadder.getWordList().size() + " words from file with length of " + tfWordSize.getText(), Color.GREEN, Color.GREEN);
            System.out.println("Displaying " + wordLadder.getWordList().size() + " words from file with length of " + tfWordSize.getText());
            taDictionary.setText(guiDictionary.toString());
            lblIndexing1.setText("Indexing... done.");
            lblDictCoun.setText("Words in Dictionary = " + wordLadder.getWordList().size() +" words");
            wordLadder.buildGraph();
            System.out.println("Graph Built");
            lblProgres.setText("Time to Build Graph: " + wordLadder.getTimeForGraph() + " milliseconds");
        }

        if (obj == btLoadTextFiel){
            wordLadder.setWords_size(Integer.parseInt(tfWordSize.getText()));
            String taDump = taDictionary.getText();
            System.out.println("Loading Words from Text Area");
            lblIndexing1.setText("Indexing...");
            ArrayList<String> taList = new ArrayList<String>();
            StringTokenizer stringTokenizer = new StringTokenizer(taDump, "\t\n\r\f,\"");
            while (stringTokenizer.hasMoreTokens()) {
                String token = stringTokenizer.nextToken();
                taList.add(token);
            }
            guiDictionary = new WordCollection();
            guiDictionary.setWords(taList);
            wordLadder = new WordLadder(taList);
            WordLadderGUI.showMessage("Loading " + wordLadder.getWordList().size() + " words from Text Field", Color.GREEN, Color.GREEN);
            lblIndexing1.setText("Indexing... done.");
            lblDictCoun.setText("Words in Dictionary = " + wordLadder.getWordList().size() +" words");
            wordLadder.buildGraph();
            System.out.println("Graph Built");
            lblProgres.setText("Time to Build Graph: " + wordLadder.getTimeForGraph() + " milliseconds");
        }
        if (obj == btClear){
            testpanel.removeAll();
            testpanel.validate();
            testpanel.repaint();
            wordLadder = null;
            lblCos.setText("Cost of Path: 0.0");
            lblCos.repaint();
            lblDictCoun.setText("Words in Dictionary = 0 words");
            lblFindPat.setText("Time to find Path: 0 milliseconds");
            tfSourc.setText("");
            tfSourc_6.setText("");
            taDictionary.setText("");
            results = null;
            lblProgres.setText("Time to Build Graph: 0 milliseconds");
        }

    }


    public static void showMessage(String message, Color messageColor, Color borderColor)
    {
        Growler myGrowler = new Growler(message , messageColor , borderColor);
        myGrowler.setLocationRelativeTo(null);
        myGrowler.setVisible(true);
    }


    private void clearData() {
        tfSourc.setText("");
        tfSourc_6.setText("");
        taDictionary.setText("");
        //tfWordSize.setText("");
    }
}


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class FridgeScreen
{

    private static JFrame myframe;
    private static Fridge myfridge;


    public static void addComponentsToPane(Container pane)
    {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        addText("Spoiler Alert!", pane);
        addAButton("1 Whats in my Fridge?", pane);
        addAButton("2 Alerts", pane);
        addAButton("3 New Item", pane);
        addAButton("4 Remove Item", pane);

    }


    private static void addText(String text, Container container)
    {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);
        container.add(label);
    }


    private static void addAButton(String text, Container container)
    {

        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        container.add(button);
    }


    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static JFrame createAndShowGUI()
    {

        class keyList
            implements KeyListener
        {
            public void keyTyped(KeyEvent ev)
            {
                char press = ev.getKeyChar();
                if (press == '1')
                {

                    myframe.removeKeyListener(this);
                    hideGUI();
                    whatsFridgeScreen();

                }
                else if (press == '2')
                {
                    myframe.removeKeyListener(this);
                    hideGUI();
                    alertFridgeScreen();
                }
                else if (press == '3')
                {
                    myframe.removeKeyListener(this);
                    hideGUI();
                    newFridgeScreen();
                }
                else if (press == '4')
                {
                    myframe.removeKeyListener(this);
                    hideGUI();
                    removeFridgeScreen();
                }

            }


            public void keyPressed(KeyEvent e)
            {
            }


            public void keyReleased(KeyEvent e)
            {

            }


            public void resetGUI()
            {
                myframe.getContentPane().removeAll();
                myframe.repaint();

                addComponentsToPane(myframe.getContentPane());

                // Display the window.
                myframe.pack();
                myframe.setVisible(true);
                myframe.setFocusable(true);
                myframe.setFocusableWindowState(true);
                myframe.addKeyListener(new keyList());
            }


            public void removeFridgeScreen()
            {

                class retLis
                    implements KeyListener
                {
                    public void keyTyped(KeyEvent e)
                    {
                        char pressed = e.getKeyChar();

                        if (pressed > 'a' && pressed < 'z')
                        {

                            myframe.removeKeyListener(this);
                            resetGUI();
                        }
                        else if (pressed >= '0' && pressed <= '9')
                        {
                            if (pressed - 48 < myfridge.size())
                            {
                                myfridge.deleteFoodAt(pressed - 48);
                                myframe.removeKeyListener(this);
                                hideGUI();
                                removeFridgeScreen();
                            }
                        }

                    }


                    public void keyPressed(KeyEvent e)
                    {
                    }


                    public void keyReleased(KeyEvent e)
                    {

                    }
                }

                JTextArea foodList =
                    new JTextArea(
                        "To remove an item, press the number that appears next to it"
                            + myfridge.toString()
                            + "\n\n Press any lowercase letter to return to menu");
                foodList.setPreferredSize(new Dimension(300, 300));

                foodList.setAlignmentX(Component.CENTER_ALIGNMENT);
                myframe.addKeyListener(new retLis());
                myframe.add(foodList);
                myframe.revalidate();
                myframe.repaint();

            }


            public void alertFridgeScreen()
            {
                class retLis
                    implements KeyListener
                {
                    public void keyTyped(KeyEvent e)
                    {
                        char pressed = e.getKeyChar();

                        if (pressed == '1' || pressed == '2' || pressed == '3'
                            || pressed == '4')
                        {
                            myframe.removeKeyListener(this);
                            resetGUI();
                        }

                    }


                    public void keyPressed(KeyEvent e)
                    {
                    }


                    public void keyReleased(KeyEvent e)
                    {

                    }
                }
                // Now do things
                JTextArea foodList =
                    new JTextArea("SPOILERALERT!!!!\n\n"
                        + myfridge.expFoodinString(1)
                        + "\n\n Press any number 1-4 to return to menu");
                foodList.setPreferredSize(new Dimension(300, 300));

                foodList.setAlignmentX(Component.CENTER_ALIGNMENT);
                myframe.addKeyListener(new retLis());
                myframe.add(foodList);
                myframe.revalidate();
                myframe.repaint();
            }


            public void newFridgeScreen()
            {

                class retLis
                    implements KeyListener
                {
                    public void keyTyped(KeyEvent e)
                    {
                        char pressed = e.getKeyChar();

                        if (pressed == '1' || pressed == '2' || pressed == '3'
                            || pressed == '4')
                        {
                            myframe.removeKeyListener(this);
                            resetGUI();
                        }
                        else if (pressed == '5')
                        {
                            myframe.removeKeyListener(this);
                            hideGUI();
                            newFridgeScreen();
                        }

                    }


                    public void keyPressed(KeyEvent e)
                    {
                    }


                    public void keyReleased(KeyEvent e)
                    {

                    }
                }
                // Start doing things here
                String result =
                    (String)JOptionPane
                        .showInputDialog(
                            myframe,
                            "Enter Name, (number) days until expiration, owner, leave blank if you dont want to add anything",
                            "Add a new food",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "");

                JTextField resultField = new JTextField("REMOVE SCREEN");

                if (result != null)
                {
                    Scanner scan = new Scanner(result);
                    scan.useDelimiter(",");
                    String name = scan.next();
                    int days = scan.nextInt();
                    String owner = scan.next();

                    Food newFood = new Food(name);
                    newFood.setExpDate(days);
                    newFood.setOwner(owner);
                    myfridge.addFood(newFood);
                    String combined =
                        name + "\n" + days + " days\n" + "Owned by: " + owner;
                    resultField.setText("Succesfully Added food: " + combined);
                }
                else
                {
                    resultField
                        .setText("Add failed, hit 5 to try again or 1-4 to return to menu");
                }

                /*
                 * String textinfield = ""; JTextArea foodList = new JTextArea(
                 * "This is where you can add new things to your fridge!");
                 * foodList.setPreferredSize(new Dimension(300, 300));
                 * JTextField nameField = new JTextField("Food");
                 * nameField.setEditable(true); nameField.setEnabled(true);
                 * nameField.setPreferredSize(new Dimension(300, 300)); class
                 * editLis implements KeyListener { JTextField field; String
                 * text; public editLis(JTextField f, String st) { super();
                 * field = f; text = st; } public void keyTyped(KeyEvent e) {
                 * char pressed = e.getKeyChar(); if (pressed == 13) {
                 * field.setEnabled(false); field.setEditable(false); text =
                 * field.getText(); } } public void keyPressed(KeyEvent e) { }
                 * public void keyReleased(KeyEvent e) { } }
                 * nameField.addKeyListener(new editLis(nameField,
                 * textinfield));
                 * foodList.setAlignmentX(Component.CENTER_ALIGNMENT);
                 * nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
                 */
                // resultField.setEditable(false);
                // resultField.setEnabled(false);
                resultField.setAlignmentX(Component.CENTER_ALIGNMENT);
                myframe.addKeyListener(new retLis());
                myframe.add(resultField);
                // myframe.add(nameField);
                myframe.revalidate();
                myframe.repaint();

            }


            public void whatsFridgeScreen()
            {

                class retLis
                    implements KeyListener
                {
                    public void keyTyped(KeyEvent e)
                    {
                        char pressed = e.getKeyChar();

                        if (pressed == '1' || pressed == '2' || pressed == '3'
                            || pressed == '4')
                        {
                            myframe.removeKeyListener(this);
                            resetGUI();
                        }

                    }


                    public void keyPressed(KeyEvent e)
                    {
                    }


                    public void keyReleased(KeyEvent e)
                    {

                    }
                }

                JTextArea foodList =
                    new JTextArea(myfridge.toString()
                        + "\n\n Press any number 1-4 to return to menu");
                foodList.setPreferredSize(new Dimension(300, 300));

                foodList.setAlignmentX(Component.CENTER_ALIGNMENT);
                myframe.addKeyListener(new retLis());
                myframe.add(foodList);
                myframe.revalidate();
                myframe.repaint();

            }


            private void hideGUI()
            {
                myframe.getContentPane().removeAll();

                myframe.repaint();

            }

        }

        // Create and validate();
        // myframe.reset up the window.
        JFrame frame = new JFrame("BoxLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));

        // Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        // Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setFocusableWindowState(true);
        frame.addKeyListener(new keyList());

        return frame;

    }


    // handles response to going to screen 1

    public static void main(String[] args)
    {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                myfridge = new Fridge();
                Food foo = new Food();
                foo.setName("PIZZA");
                foo.setExpDate(4);
                myfridge.addFood(foo);
                foo = new Food("Chinese Take-Out");
                foo.setExpDate(1);
                myfridge.addFood(foo);
                myframe = createAndShowGUI();

            }
        });

    }
}

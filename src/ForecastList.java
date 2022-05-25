import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static java.nio.file.AccessMode.READ;

/**
 * Darragh Walker
 * Final Assignment 26/03/2022
 * Main driver for Weather class
 * listing and sorting contents of a file and displaying them in a Text area using JButtons and allowing that text area to be saved in a new file
 */

public class ForecastList extends JFrame {
    JFrame f = new JFrame();                     //class members
    JTextArea area = new JTextArea();
    JTextField save = new JTextField();
    JButton ReadData = new JButton("Read CSV");
    JButton SortDataT = new JButton("Display maxtp Temp");
    JButton SortDataR = new JButton("Display Rain");
    JButton SortDataW = new JButton("Display Mean Wind Speed");
    JButton SortDataS = new JButton("Display Sun");
    JButton SaveData = new JButton("Save");
    int flag = 0;

    public ForecastList() {              //initializing buttons and textarea
        f.setSize(1200, 800);                           //initializing text field
        f.add(area);
        f.add(ReadData);
        f.add(SortDataS);
        f.add(SortDataR);
        f.add(SortDataT);
        f.add(SortDataW);
        f.add(SaveData);
        f.add(save);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        ReadData.setBounds(30, 70, 200, 30);            //setting button bounds and size
        SortDataT.setBounds(30, 200, 200, 30);
        SortDataR.setBounds(30, 260, 200, 30);
        SortDataW.setBounds(30, 320, 200, 30);
        SortDataS.setBounds(30, 380, 200, 30);
        SaveData.setBounds(30, 500, 200, 30);

        JScrollPane scrollBar = new JScrollPane(area);                                              //adding scrollbar
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        f.add(scrollBar);
        scrollBar.setBounds(303, 30, 711, 615);

        readBtn pressed = new readBtn();                                    //adding action listeners
        ReadData.addActionListener(pressed);
        SortDataT.addActionListener(pressed);
        SortDataR.addActionListener(pressed);
        SortDataW.addActionListener(pressed);
        SortDataS.addActionListener(pressed);
        SaveData.addActionListener(pressed);

        area.setBounds(300, 30, 700, 600);
        save.setBounds(30,540,200,25);

    }


    public static void main(String[] args) throws IOException {

        ForecastList F = new ForecastList();                //creating new window
    }

    private class readBtn implements ActionListener{
        ArrayList<Weather> forecast = new ArrayList<>();        //instance of forecast
        String nameFile;
        File file;

        @Override
        public void actionPerformed(ActionEvent e) {                //event listener
            if (e.getSource() == ReadData) {
                flag = 1;                                       //setting flag to 1 so other buttons are accessible
                String line = null;
                long lineNumber = 0;
                JFileChooser chooser = new JFileChooser();              //new file chooser
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setMultiSelectionEnabled(false);
                chooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        if (file.isDirectory()) {
                            return true;
                        }

                        String fileName = file.getName().toLowerCase();
                        if (fileName.endsWith(".csv")) {                //file names must end with .csv
                            return true;
                        }
                        return false; // Reject any other files
                    }

                    @Override
                    public String getDescription() {
                        return "Excel csv Files";
                    }
                });
                int return_value = chooser.showOpenDialog(null);
                if(return_value == chooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile();
                    area.append("You have opened "+f+"\n");
                    nameFile = file.getAbsolutePath();
                }
                try {
                    FileReader reader = new FileReader(nameFile);               //reading the file
                    BufferedReader br = new BufferedReader(reader);
                    try {
                        br.readLine();          //nulls the firstline
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    while (true) {
                        try {
                            if (!((line = br.readLine()) != null)) break;
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        lineNumber++;
                        String[] strings = line.split(",");             //splits the line using commas
                        try {
                            Weather weather = new Weather(Double.parseDouble(strings[0]), Double.parseDouble(strings[1]), Double.parseDouble(strings[2]),       //adding objects to arraylist
                                    Double.parseDouble(strings[3]), Double.parseDouble(strings[4]), Double.parseDouble(strings[5]),
                                    Double.parseDouble(strings[6]), Double.parseDouble(strings[7]), Double.parseDouble(strings[8]),
                                    Double.parseDouble(strings[9]), Double.parseDouble(strings[10]), Double.parseDouble(strings[11]));
                            forecast.add(weather);
                            area.append(String.valueOf(weather));
                        } catch (NumberFormatException ex) {
                            System.out.println(" ERROR IN CSV FILE LINE # --- " + lineNumber);                //fault control
                            ex.printStackTrace();
                        }
                    }
                    br.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, e);                 //fault control
                }
            }
            if (e.getSource() == SortDataT) {
                if (flag == 1) {
                    Collections.sort(forecast);                 //sorts data
                    area.append("\n\n\n\n\tForecast Sorted in Ascending Max Temperature\n");
                    for (Weather weather : forecast) {           //appends sorted to area
                        area.append(String.valueOf(weather));
                    }
                } else {
                    area.append("Please Choose a CSV File before sorting\n");
                }
            }
            if (e.getSource() == SortDataR) {
                if (flag == 1) {
                    Collections.sort(forecast, new RainSort());     //sorts data
                    area.append("\n\n\n\n\tForecast Sorted in Descending Rainfall\n");
                    for (Weather weather : forecast) {           //appends sorted to area
                        area.append(String.valueOf(weather));
                    }
                } else {
                    area.append("Please Choose a CSV File before sorting\n");
                }
            }
            if (e.getSource() == SortDataW) {
                if (flag == 1) {
                    Collections.sort(forecast, new WindSort());     //sorts data
                    area.append("\n\n\n\n\tForecast Sorted in Descending Mean Windspeed\n");
                    for (Weather weather : forecast) {           //appends sorted to area
                        area.append(String.valueOf(weather));
                    }
                } else {
                    area.append("Please Choose a CSV File before sorting\n");
                }
            }
            if (e.getSource() == SortDataS) {
                if (flag == 1) {
                    Collections.sort(forecast, new SunSort());      //sorts data
                    area.append("\n\n\n\n\tForecast Sorted in Ascending Sunshine Duration \n");
                    for (Weather weather : forecast) {           //appends sorted to area
                        area.append(String.valueOf(weather));
                    }
                } else {
                    area.append("Please Choose a CSV File before sorting\n");
                }
            }
            if (e.getSource() == SaveData) {
                if (flag == 1) {
                    String filePath = "E:\\college backup\\School work\\java\\";            //where file is too be stored
                    String fileType = ".csv";
                    String wr = save.getText();                                 //gets text from text area
                    File file = new File(wr+fileType);
                    String path = file.getPath();
                    try (
                            RandomAccessFile stream = new RandomAccessFile(path,"rw");      //
                            FileChannel channel = stream.getChannel();) {

                        byte[] strBytes = area.getText().getBytes();
                        ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);

                        buffer.put(strBytes);
                        buffer.flip();
                        channel.write(buffer);              //writes data to file
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    area.append("Save Complete.\n");
                }


            else{
                area.append("Please Choose a CSV File before sorting\n");
            }
        }
        }
    }
}
